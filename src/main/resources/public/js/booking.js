$(document).ready(onDocumentReady);
function onDocumentReady(){
    dataTable = $('#bookingtable').DataTable({
        columns: [
            { "data": "bookingnumber" },
            { "data": function(data, type, dataToSet){
                return data.guest.surName + "," + data.guest.firstName;
            }},
            { "data": "room.roomNumber" }
        ]
    });
     $('#datePickerStart').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
     $('#datePickerEnd').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#bookingtable tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            $('#bookingtable tr.selected').removeClass('selected');
            $(this).addClass('selected');
            var table = $('#bookingtable').DataTable();
            var data = table.row( this ).data();

            // get booking and show modal with correct values
            apiGetBooking(data.bookingnumber);
        }
    });
    initializeGuestSearch();
    apiLoadDatatables();
}

function initializeGuestSearch(){

}

// OBJECT CONVERTERS
var deleteID = -1;
function bookingToTable(booking){
    var result = "<tr>";
    result += "<td>DB ID</td>";
    result += "<td>" + booking.bookingNumber + "</td>";
    result += "<td>" + guest.guestNumber + "</td>";
    result += "<td>" + room.roomnumber + "</td>";
    result += "<td><button type=\"button\" onclick=\"apiGetBooking(" + booking.bookingNumber + ")\" class=\"btn btn-info\"><span class=\"glyphicon glyphicon-pencil\"></span></button>";
    result += "<button type=\"button\" onclick=\"confirmDelete(" + booking.bookingNumber + ")\" class=\"btn btn-danger\"><span class=\"glyphicon glyphicon-remove\"></span></button></td>";
    result += "</tr>"
    return result;
}
// GENERAL FUNCTIONS
function createBookingDiv(){
//    $("#booking-div").css('display', 'block');
    $("#modal-title").html("Create Booking");
    $("#bookingnumber").val("");
    $("#guestNumber").val("");
    $("#dateStart").val("");
    $("#dateEnd").val("");
    $("#roomnumber").val("");
    $("#btnsubmit").attr('onclick', 'processFormPost();');
    $("#confirmbutton").css('display', 'none');
}

function hideBookingModal(){
    //$("#booking-div").css('display', 'none');
    // hide modal
    $('#myModal').modal('toggle');
}
function fillUpdateModal(booking){
    $("#btnsubmit").attr('onclick', 'processFormPut(' + booking.bookingnumber + ');');
    $("#bookingnumber").val(booking.bookingnumber);
    $("#guestNumber").val(booking.guest.guestNumber);
    $("#roomnumber").val(booking.room.roomNumber);
    $("#modal-title").html("Update Booking");
    $("#confirmbutton").css('display', 'inline-block');
    deleteID = booking.bookingnumber;
    var elem = '<button type="button" class="btn btn-danger" onclick="apiDeleteBooking(' + booking.bookingnumber + ');">Confirm delete</button>';
    $('#confirmbutton').popover({animation:true, content:elem, html:true, container:myModal});
}

function confirmDelete(id){
    var msg = "Delete booking: " + id + "?"
    var r = confirm(msg);
    if (r) {
        apiDeleteBooking(id);
    }
}
function emptySearchSuggestions(){
    $("#searchSuggestions").empty();
    $("#searchSuggestions").css('display', 'none');
}
function updateSearch(){
    var value = $("#guestNumber").val();
    emptySearchSuggestions();
    if(value.length >= 3) {
        $.get("http://localhost:8080/api/guest/search/" + $("#guestNumber").val(), function (data) {
            for(var index in data){
                $("#searchSuggestions").append('<div style="padding: 5px 10px"><a style="cursor: pointer">' + data[index].firstName + " " + data[index].surName + '</a></div>')
            }
            $("#searchSuggestions").css('display', 'block');
        });
    }
}
function processFormPost(){
    console.log("processFormPost");
    var bn = parseInt($("#bookingNumber").val());
    var gn = $("#guestNumber").val();
    var rn = $("#roomnumber").val();

    var startDate = $("#dateStart").val();
    var endDate = $("#dateEnd").val();
    var roomid = apiFindRoomIdByRoomNumber(rn);//find room by roomnumber
    var guest = {
        "guestNumber" : gn
    };

    var room = {
        "id" : roomid
    };

    var booking = {
        "guest" : guest,
        "room" : room,
        "startDate" : startDate,
        "endDate" : endDate
    };
 console.log(JSON.stringify(booking));
    console.log("apiPostBooking with obj: " + JSON.stringify(room));
    apiPostBooking(booking);
}

function processFormPut(id){
//    console.log("processFormPut: " + id);
    var bn = parseInt($("#bookingNumber").val());
    var gn = $("#guestNumber").val();
    var rn = $("#roomnumber").val();

    var roomid = apiFindRoomIdByRoomNumber(rn);//find room by roomnumber
   // console.log('found room id: ' + roomid);

    var startDate = $("#dateStart").val();
    var endDate = $("#dateEnd").val();

    var guest = {
        "guestNumber" : gn
    }

    var room = {
        "id" : roomid
    }

    var booking = {
        "guest" : guest,
        "room" : room,
        "startDate" : startDate,
        "endDate" : endDate
    }
    console.log("api put: " + id + "  " + JSON.stringify(booking));
    apiPutBooking(id, booking);
   console.log("apiPostBooking with obj: " + JSON.stringify(booking));
}
function zeroPad(num, places) {
  var zero = places - num.toString().length + 1;
  return Array(+(zero > 0 && zero)).join("0") + num;
}

function apiLoadDatatables(){
    var api = "http://localhost:8080/api/booking";

    $.get(api, function (dataSet) {

       // console.log("Adding dataset to table: \n" + JSON.stringify(dataSet));
        $("#bookingtable").DataTable().clear();
        $("#bookingtable").DataTable().rows.add(dataSet);
        $("#bookingtable").DataTable().columns.adjust().draw();
    });
}

function apiGetBooking(id){
    // get query met id;
//    console.log("getSingle: " + id)
    var api = "http://localhost:8080/api/booking/" + id;
    $.get(api, function(data){
        if (data){
//            $("#booking-div").css('display','block');
            $('#myModal').modal('toggle');
//            $("#booking-div-title").html("Update Booking");
//            console.log('data for update: ' + JSON.stringify(data));
//            console.log('data for update: ' + JSON.stringify(data));
         //   console.log("got data:");
         //   console.log(data);
            data.startDate = "" + data.startDate.year + "-" + zeroPad(data.startDate.monthValue, 2) + "-" + zeroPad(data.startDate.dayOfMonth, 2);
            data.endDate = "" + data.endDate.year + "-" + zeroPad(data.endDate.monthValue, 2) + "-" + zeroPad(data.endDate.dayOfMonth, 2);

            fillUpdateModal(data);
        }
    });
}
function apiLoadBooking() {
	var api = "http://localhost:8080/api/booking";
	$.get(api, function (data) {
		if (data) {
            $("#tableBody").empty();
            for(var i = 0; i < data.length; i++){
                var item = data[i];

                var bookingTableEntry = bookingToTable(item);
                $("#tableBody").append(bookingTableEntry);
		    }
//            console.log("result of api call: " + data);
		    return data;
		}
	});
}
function apiDeleteBooking(){
if (deleteID > -1){
    var api = "http://localhost:8080/api/booking/" + deleteID;
    $.ajax({
        url: api,
        type: 'DELETE',
        success: function(response){
//            console.log(response);
            hideBookingModal()
            apiLoadDatatables();
        }
      });
    }
}
function apiPostBooking(data){
console.log(JSON.stringify(data));
    $.ajax ({
        url: 'http://localhost:8080/api/booking',
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function(response){
            console.log("POST booking request success");
            console.log("Response: " + response);
            hideBookingModal();
            apiLoadDatatables();
        },
        error: function(req, status, err){
            console.log("Error during POST");
            console.log("req: " + JSON.stringify(req));
            console.log("status: " + JSON.stringify(status));
            console.log("err: " + JSON.stringify(err));
        }
    });
}
function apiPutBooking(id, data){
    $.ajax ({
        url: 'http://localhost:8080/api/booking/' + id,
        type: "PUT",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function(response){
            hideBookingModal();
            apiLoadDatatables();
        }
    });
}
function apiFindRoomIdByRoomNumber(rn){
    var roomid = -1;
    $.ajax({
        url: 'http://localhost:8080/api/room/search/' + rn,
        type: "GET",
        async: false,
        success: function(response){
          //  console.log('search result: ' + response);
            roomid = response;
        }
    });
    return roomid;
}
