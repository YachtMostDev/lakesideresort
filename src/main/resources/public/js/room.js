// OBJECT CONVERTERS
var baseURL = "http://localhost:8080/api/room/";
var deleteID = -1;
var client;

function roomToTable(room){
    var result = "<tr>";
    result += "<td>DB ID</td>";
    result += "<td>" + room.roomNumber + "</td>";
    result += "<td>" + room.roomSize + "</td>";
    result += "<td>" + room.roomType + "</td>";
    result += "<td><button type=\"button\" onclick=\"apiGetSingleRoom(" + room.roomNumber + ")\" class=\"btn btn-info\"><span class=\"glyphicon glyphicon-pencil\"></span></button>";
    result += "<button type=\"button\" onclick=\"confirmDelete(" + room.roomNumber + ")\" class=\"btn btn-danger\"><span class=\"glyphicon glyphicon-remove\"></span></button></td>";
    result += "</tr>"
    return result;
}

// GENERAL FUNCTIONS
function setErrorRoomnumberDiv(){
    console.log("setting ")
    $("#roomnumberdiv").addClass('has-error has-feedback');
}
function resetRoomnumberDiv(){
    $("#roomnumberdiv").removeClass('has-error has-feedback');
}
function createRoomDiv(){
    $("#modal-title").html("Create Room");
    $("#roomnumber").val("");
    $("#roomtype").val("");
    $("#roomsize").val("");
    $("#date").val("");
    $("#btnsubmit").attr('onclick', 'processFormPost();');
    $("#confirmbutton").css('display', 'none');
    $("#calendarContainer").css('display', 'none');
}
function hideRoomModal(){
    $('#myModal').modal('toggle');
}
function fillUpdateModal(room){
    $("#btnsubmit").attr('onclick', 'processFormPut(' + room.id + ');');
    $("#roomnumber").val(room.roomNumber);
    $("#roomtype").val(room.roomType);
    $("#roomsize").val(room.roomSize);
    $("#date").val(room.availableFrom);
    $("#modal-title").html("Update Room");
    $("#confirmbutton").css('display', 'inline-block');
    $("#calendarContainer").css('display', 'inline-block');
    deleteID = room.id;
    var elem = '<button type="button" class="btn btn-danger" onclick="apiDeleteRoom();">Confirm delete</button>';
    $('#confirmbutton').popover({
        animation:true,
        content:elem,
        html:true,
        container:myModal
    });
}
function confirmDelete(id){
    var msg = "Delete room: " + id + "?"
    var r = confirm(msg);
    if (r) {
        apiDeleteRoom(id);
    }
}
function processFormPost(e){
    e.preventDefault();

    var rn = $("#roomnumber").val();
    var rs = $("#roomsize").val();
    var rt = $("#roomtype").val();
    var af = $("#date").val();

    var room = {
        "roomNumber" : rn,
        "roomSize" : rs,
        "roomType" : rt,
        "availableFrom" : af
    };
    apiPostRoom(room);
}
function processFormPut(id){
    var roomNumber = $("#roomnumber").val();
    var roomSize = $("#roomsize").val();
    var roomType = $("#roomtype").val();
    var availableFrom = $("#date").val();

    var room = {
        id : id,
        roomNumber : roomNumber,
        roomSize : roomSize,
        roomType : roomType,
        availableFrom : availableFrom
    }
    apiPutRoom(id, room);
}
function zeroPad(num, places) {
  var zero = places - num.toString().length + 1;
  return Array(+(zero > 0 && zero)).join("0") + num;
}

// API FUNCTIONALITY
function onDocumentReady(){
    client = Stomp.over(new SockJS('/gs-guide-websocket'));
    client.connect({}, function (frame) {
        client.subscribe('/room', function (data) {
            setData(JSON.parse(data.body));
        });
    });

    $('#roomtable').DataTable({
        columns: [
            { "data": "roomNumber" },
            { "data": "roomType" },
            { "data": "roomSize" },
            { "data": "availableFrom" }
        ]
    });
    $('#roomtable tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            $('#roomtable tr.selected').removeClass('selected');
            $(this).addClass('selected');
            var table = $('#roomtable').DataTable();
            var data = table.row( this ).data();
            apiGetSingleRoom(data.id);
            retrieveDataForCalendar(data.id);
        }
    });
    apiLoadDatatables();
    $('#datePicker').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $("#roomnumber").keyup(function () {
        resetRoomnumberDiv();
    });
}
function setData(data){
    data = data.map(function(object){
        object.availableFrom = "" + object.availableFrom.year + "-" + zeroPad(object.availableFrom.monthValue, 2) + "-" + zeroPad(object.availableFrom.dayOfMonth, 2);
        return object;
    });
    $("#roomtable").DataTable().clear();
    $("#roomtable").DataTable().rows.add(data);
    $("#roomtable").DataTable().columns.adjust().draw();
}

function apiLoadDatatables(){
    var api = baseURL;
    $.get(api, function (dataSet) {
        if (dataSet){
            setData(dataSet);
        }
    });
}
function apiGetSingleRoom(id){
    var api = baseURL + id;
    $.get(api, function(data){
        if (data){
            $('#myModal').modal('toggle');
            data.availableFrom = "" + data.availableFrom.year + "-" + zeroPad(data.availableFrom.monthValue, 2) + "-" + zeroPad(data.availableFrom.dayOfMonth, 2);
            fillUpdateModal(data);
        }
    });
}
function apiLoadRooms() {
	var api = baseURL;
	$.get(api, function (data) {
		if (data) {
            $("#tableBody").empty();
            for(var i = 0; i < data.length; i++){
                var item = data[i];
                var roomTableEntry = roomToTable(item);
                $("#tableBody").append(roomTableEntry);
		    }
		    return data;
		}
	});
}
function apiDeleteRoom(){
    if (deleteID > -1){
        var api = baseURL + deleteID;
        $.ajax({
            url: api,
            type: 'DELETE',
            success: function(response){
                hideRoomModal()
                // apiLoadDatatables();
            }
        });
    }
}
function apiPostRoom(data){
    $.ajax ({
        url: baseURL,
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function(response){
            if (response){
                hideRoomModal();
                // apiLoadDatatables();
            } else {
                setErrorRoomnumberDiv();
            }
        },
        error: function(req, status, err){
            console.log("Error during POST");
            console.log("req: " + JSON.stringify(req));
            console.log("status: " + JSON.stringify(status));
            console.log("err: " + JSON.stringify(err));
        }
    });
}
function apiPutRoom(id, data){
    $.ajax ({
        url: baseURL + id,
        type: "PUT",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function(response){
            hideRoomModal();
            // apiLoadDatatables();
        }
    });
}