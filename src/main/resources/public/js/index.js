/**
 * Created by njvan on 26-Oct-17.
 */
var guestsArrivingToday = [];

$(document).ready(function(){
    $.ajax({
        url:"/api/guest/today",
        type:"get",
        success: function(result) {
            guestsArrivingToday = result;
            document.getElementById("guests-arriving").innerHTML = result.length;
        }
    });
});

function showGuests(){
    console.log("hoi");
    var table = document.getElementById("guest-list-table");
    table.innerHTML = "";
    for(var index in guestsArrivingToday){
        var newLi = document.createElement("li");
        newLi.innerHTML = guestsArrivingToday[index].firstName + " " + guestsArrivingToday[index].surName;
        table.appendChild(newLi);
    }
    document.getElementById("guest-list").style.display = "block";
    document.getElementById("showGuests").style.display = "none";
    document.getElementById("hideGuests").style.display = "block";
}

function hideGuests(){
    document.getElementById("guest-list").style.display = "none";
    document.getElementById("showGuests").style.display = "block";
    document.getElementById("hideGuests").style.display = "none";
}