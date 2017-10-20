// OBJECT CONVERTERS
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
function createRoomDiv(){
//    $("#room-div").css('display', 'block');
    $("#modal-title").html("Create Room");
    $("#roomnumber").val("");
    $("#roomtype").val("");
    $("#roomsize").val("");
    $("#btnsubmit").attr('onclick', 'processFormPost();');
}
function hideRoomModal(){
    //$("#room-div").css('display', 'none');
    // hide modal
    $('#myModal').modal('toggle');
}
function fillUpdateDiv(room){
    $("#btnsubmit").attr('onclick', 'processFormPut(' + room.roomNumber + ');');
    $("#roomnumber").val(room.roomNumber);
    $("#roomtype").val(room.roomType);
    $("#roomsize").val(room.roomSize);
}
function confirmDelete(id){
    var msg = "Delete room: " + id + "?"
    var r = confirm(msg);
    if (r) {
        apiDeleteRoom(id);
    }
}
function processFormPost(){
    console.log("processFormPost");
    var rn = parseInt($("#roomnumber").val());
    var rs = $("#roomsize").val();
    var rt = $("#roomtype").val();
    var availableFrom = "2017-02-03";
    var room = {
        "roomNumber" : rn,
        "roomSize" : rs,
        "roomType" : rt,
        "availableFrom" : availableFrom
    }
    console.log("apiPostRoom with obj: " + JSON.stringify(room));
    apiPostRoom(room);
}
function processFormPut(id){
    console.log("processFormPut: " + id);

}
function zeroPad(num, places) {
  var zero = places - num.toString().length + 1;
  return Array(+(zero > 0 && zero)).join("0") + num;
}

// API FUNCTIONALITY
function onDocumentReady(){
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
            $('#myModal').modal('toggle');
        }
    });
    apiLoadDatatables();
}
function apiLoadDatatables(){
    var api = "http://localhost:8080/api/room";

    $.get(api, function (dataSet) {
        //console.log("Adding dataset to table: \n" + JSON.stringify(dataSet));
        dataSet = dataSet.map(function(object){
            object.availableFrom = "" + object.availableFrom.year + "-" + zeroPad(object.availableFrom.monthValue, 2) + "-" + zeroPad(object.availableFrom.dayOfMonth, 2);
            return object;
        });
        //console.log("Adding dataset to table: \n" + JSON.stringify(dataSet));
        $("#roomtable").DataTable().clear();
        $("#roomtable").DataTable().rows.add(dataSet);
        $("#roomtable").DataTable().columns.adjust().draw();


    });
}
function apiGetSingleRoom(id){
    // get query met id;
    console.log("getSingle: " + id)
    var api = "http://localhost:8080/api/room/" + id;
    $.get(api, function(data){
        if (data){
            $("#room-div").css('display','block');
            $("#room-div-title").html("Update Room");
            fillUpdateDiv(data);
        } else {
            $("#room-div").css('display', 'none');
        }
    });
}
function apiLoadRooms() {
	var api = "http://localhost:8080/api/room";
	$.get(api, function (data) {
		if (data) {
            $("#tableBody").empty();
            for(var i = 0; i < data.length; i++){
                var item = data[i];

                var roomTableEntry = roomToTable(item);
                $("#tableBody").append(roomTableEntry);
		    }
            console.log("result of api call: " + data);
		    return data;
		}
	});
}
function apiDeleteRoom(id){
    var api = "http://localhost:8080/api/room/" + id;
    $.ajax({
        url: api,
        type: 'DELETE',
        success: function(response){
            console.log(response);
            apiLoadRooms();
        }
    });
}
function apiPostRoom(data){
    $.ajax ({
        url: 'http://localhost:8080/api/room',
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function(response){
            console.log("POST room request success");
            console.log("Response: " + response);
            hideRoomModal();
        },
        error: function(req, status, err){
            console.log("Error during POST");
            console.log("req: " + JSON.stringify(req));
            console.log("status: " + JSON.stringify(status));
            console.log("err: " + JSON.stringify(err));
        }
    });
    apiLoadDatatables();
}
function apiPutRoom(id, data){
    $.ajax ({
        url: 'http://localhost:8080/api/room/' + id,
        type: "PUT",
        data: data,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function(response){
            console.log("PUT room request success");
            console.log("Response: " + response);
        }
    });
}