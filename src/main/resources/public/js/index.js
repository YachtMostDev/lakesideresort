/**
 * Created by njvan on 26-Oct-17.
 */
var guestsArrivingToday = [];
var availableRooms = [];
var occupiedRooms = [];
var client;

function dateToString(date){
    return date.getFullYear() + "-" + zeroPad((date.getMonth() + 1),2) + "-" + zeroPad(date.getDate(),2);
}

function zeroPad(num, places) {
    var zero = places - num.toString().length + 1;
    return Array(+(zero > 0 && zero)).join("0") + num;
}

$(document).ready(function(){
    client = Stomp.over(new SockJS('/gs-guide-websocket'));
    client.connect({}, function (frame) {
        client.subscribe('/room', function (data) {
            console.log(JSON.parse(data.body));
        });
    });

    $.ajax({
        url:"/api/guest/today",
        type:"get",
        success: function(result) {
            guestsArrivingToday = result;
            document.getElementById("guests-arriving").innerHTML = result.length;
        }
    });
    var today = new Date();
    $.ajax({
        url:"/api/availability/byDate/" + dateToString(today),
        type:"get",
        success: function(result) {
            for(var key in result){
                if(result[key][dateToString(today)]){
                    occupiedRooms.push(key);
                } else {
                    availableRooms.push(key);
                }
            }
            document.getElementById("rooms-occupied").innerHTML = occupiedRooms.length;
            document.getElementById("rooms-available").innerHTML = availableRooms.length;
        }
    });
});

function showGuests(){
    var table = document.getElementById("guest-list-table");
    table.innerHTML = "";
    for(var index in guestsArrivingToday){
        var newLi = document.createElement("li");
        newLi.innerHTML = guestsArrivingToday[index].firstName + " " + guestsArrivingToday[index].surName;
        table.appendChild(newLi);
    }
    hideRooms();
    document.getElementById("guest-list").style.display = "block";
    document.getElementById("showGuests").style.display = "none";
    document.getElementById("hideGuests").style.display = "block";
}

function hideGuests(){
    document.getElementById("guest-list").style.display = "none";
    document.getElementById("showGuests").style.display = "block";
    document.getElementById("hideGuests").style.display = "none";
}

function showRooms(){
    var occupiedTable = document.getElementById("occupied-list-table");
    var availableTable = document.getElementById("available-list-table");
    occupiedTable.innerHTML = "";
    availableTable.innerHTML = "";
    for(var index in occupiedRooms){
        var newLi = document.createElement("li");
        newLi.innerHTML = occupiedRooms[index];
        occupiedTable.appendChild(newLi);
    }
    for(var index in availableRooms){
        var newLi = document.createElement("li");
        newLi.innerHTML = availableRooms[index];
        availableTable.appendChild(newLi);
    }
    hideGuests();
    document.getElementById("room-list").style.display = "block";
    document.getElementById("showRooms").style.display = "none";
    document.getElementById("hideRooms").style.display = "block";
}

function hideRooms(){
    document.getElementById("room-list").style.display = "none";
    document.getElementById("showRooms").style.display = "block";
    document.getElementById("hideRooms").style.display = "none";
}