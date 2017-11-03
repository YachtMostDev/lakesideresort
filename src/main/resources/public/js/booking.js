$(document).ready(onDocumentReady);
function onDocumentReady(){
    client = Stomp.over(new SockJS('/gs-guide-websocket'));
    client.connect({}, function (frame) {
        client.subscribe('/booking', function (data) {
            setData(JSON.parse(data.body));
        });
    });

    $.get("/api/booking", function (data) {
        setData(data);
    });

    dataTable = $('#bookingtable').DataTable({
        columns: [
            { "data": "bookingnumber" },
            { "data": function(data, type, dataToSet){
                return data.guest.firstName + " " + data.guest.surName;
            }},
            { "data": "room.roomNumber" },
            { "data": "startDate" },
            { "data": "endDate" }
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
}

// GENERAL FUNCTIONS
function createBookingDiv(){
//    $("#booking-div").css('display', 'block');
    $("#modal-title").html("Create Booking");
    $("#searchSuggestions").css('display', 'none');
    $("#bookingnumber").val("");
    $("#guestNumber").val("");
    $("#guestNumberSearch").val("");
    $("#dateStart").val("");
    $("#dateEnd").val("");
    $("#roomnumber").val("");
    $("#btnsubmit").attr('onclick', 'processFormPost();');
    $("#confirmbutton").css('display', 'none');
}

function fillUpdateModal(booking){
    $("#btnsubmit").attr('onclick', 'processFormPut(' + booking.bookingnumber + ');');
    $("#bookingnumber").val(booking.bookingnumber);
    $("#searchSuggestions").css('display', 'none');
    $("#guestNumber").val(booking.guest.guestNumber);
    $("#guestNumberSearch").val(booking.guest.firstName + " " + booking.guest.surName);
    $("#roomnumber").val(booking.room.roomNumber);
    $("#dateStart").val(booking.startDate);
    $("#dateEnd").val(booking.endDate);
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
    var searchString = $("#guestNumberSearch").val();
    emptySearchSuggestions();
    if(searchString.length >= 3) {
        $.get("http://localhost:8080/api/guest/search/" + searchString, function (data) {
            for(var index = 0; index < data.length && index < 5; index++){
                var suggestionDiv = $("<div></div>");
                suggestionDiv.attr("data-id",data[index].guestNumber);
                suggestionDiv.attr("data-value",data[index].firstName + " " + data[index].surName);
                suggestionDiv.text(data[index].firstName + " " + data[index].surName);

                var locationSpan = $("<span></span>");
                locationSpan.text(getLocationString(data[index]));

                suggestionDiv.append(locationSpan);
                suggestionDiv.addClass('searchSuggestion');
                $("#searchSuggestions").append(suggestionDiv);
                $(".searchSuggestion").click(function(event){
                    event.stopPropagation();
                    event.stopImmediatePropagation();
                    $("#guestNumber").val(event.target.getAttribute("data-id"));
                    $("#guestNumberSearch").val(event.target.getAttribute("data-value"));
                    emptySearchSuggestions();
                    // console.log(event.target.getAttribute("data-id"));
                });
            }
            if(data.length > 5){
                $("#searchSuggestions").append("<div style='color: gray; font-size: 12px; padding: 2px; margin: 5px 10px;'> And " + (data.length - 5) + " more</div>");
            }
            if(data.length > 0) $("#searchSuggestions").css('display', 'block');
        });
    }
}

function getLocationString(guest){
    var locationString = "";

    if(guest.address && guest.city) {
        locationString = guest.address + ", " + guest.city;
    } else if(guest.city) {
        locationString = guest.city;
    } else if(guest.address) {
        locationString = guest.address;
    }
    return locationString;
}

function processFormPost(){
//    console.log("processFormPost");
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

function setData(data){

    data = data.map(function(object){
        object.startDate = object.startDate = "" + object.startDate.year + "-" + zeroPad(object.startDate.monthValue, 2) + "-" + zeroPad(object.startDate.dayOfMonth, 2);
        object.endDate = object.endDate = "" + object.endDate.year + "-" + zeroPad(object.endDate.monthValue, 2) + "-" + zeroPad(object.endDate.dayOfMonth, 2);
        return object;
    });

    $("#bookingtable").DataTable().clear();
    $("#bookingtable").DataTable().rows.add(data);
    $("#bookingtable").DataTable().columns.adjust().draw();
}

function apiGetBooking(id){
    var api = "http://localhost:8080/api/booking/" + id;
    $.get(api, function(data){
        if (data){
            $('#myModal').modal('toggle');
            data.startDate = "" + data.startDate.year + "-" + zeroPad(data.startDate.monthValue, 2) + "-" + zeroPad(data.startDate.dayOfMonth, 2);
            data.endDate = "" + data.endDate.year + "-" + zeroPad(data.endDate.monthValue, 2) + "-" + zeroPad(data.endDate.dayOfMonth, 2);

            fillUpdateModal(data);
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
            $('#myModal').modal('toggle');
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
            $('#myModal').modal('toggle'); 
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
            roomid = response;
        }
    });
    return roomid;
}
