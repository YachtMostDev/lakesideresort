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
    $("#room-div").css('display', 'block');
    $("#room-div-title").html("Create Room");
    $("#roomnumber").val("");
    $("#roomtype").val("");
    $("#roomsize").val("");
    $("#form-room").attr('action', 'http://localhost:8080/api/room');
}
function hideRoomDiv(){
    $("#room-div").css('display', 'none');
}
function fillUpdateDiv(room){
    $("#form-room").attr('action', 'http://localhost:8080/api/room/' + room.roomNumber);
    $("#roomnumber").val(room.roomNumber);
    $("#roomtype").val(room.roomType);
    $("#roomsize").val(room.roomSize);
}
function confirmDelete(id){
    var msg = "Delete room: " + id + "?"
    var r = confirm(msg);
    if (r == true) {
        apiDeleteRoom(id);
    }
}
// API FUNCTIONALITY
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