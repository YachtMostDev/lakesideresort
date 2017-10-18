function roomToString(room){
    var result = "";
    result += "Room: " + room.roomNumber;
    result += " | Size: " + room.roomSize;
    result += " | Type: " + room.roomType;
    return result;
}
function roomToTable(room){
    var result = "<tr>";
    result += "<td>DB ID</td>";
    result += "<td>" + room.roomNumber + "</td>";
    result += "<td>" + room.roomSize + "</td>";
    result += "<td>" + room.roomType + "</td>";
    result += "<td><button type=\"button\" onclick=\"getSingle(" + room.roomNumber + ")\" class=\"btn btn-info\"><span class=\"glyphicon glyphicon-search\"></span></button> <button type=\"button\" class=\"btn btn-danger\"><span class=\"glyphicon glyphicon-remove\"></span></button></td>"
    result += "</tr>"
    return result;

}
function getSingle(id){
    // get query met id;
    console.log("getSingle: " + id)
    var api = "http://localhost:8080/api/room/" + id;
    $.get(api, function(data){

    })
}
function apiLoadRooms() {
	console.log("api GET Rooms");
	var api = "http://localhost:8080/api/room";
	$.get(api, function (data) {
		if (data) {

            $("#roomlist").empty();
            for(var i = 0; i < data.length; i++){
                var item = data[i];
                //var s = JSON.stringify(item);
                var s = roomToString(item);
                $("#roomlist").append('<li class="list-group-item">' + s + '</li>');

                var roomTableEntry = roomToTable(item);
                $("#tableBody").append(roomTableEntry);
		    }
			//console.log(data);
		} else {
			return null;
		}
	})
}