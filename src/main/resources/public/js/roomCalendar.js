$(document).ready(function(){
    var calendar = document.getElementById("calendar");
    var row = document.createElement("tr");
    var today = new Date();
    var monthDifference = 0;
    var daysOfMonth = new Date(new Date(today.getFullYear(), today.getMonth() + 1 + monthDifference, 1) -1).getDate();
    console.log(daysOfMonth + " " + (today.getMonth()+monthDifference));
    var start = new Date(today.getYear(), today.getMonth()+monthDifference, 2).getDay();
    for (var index = 1; index-start <= daysOfMonth; index++){
        //console.log(index);
        var td = document.createElement("td");
        td.className = "red";
        td.innerHTML = (index - start < 1)? "": index-start;
        row.appendChild(td);
        if(index % 7 == 0){
           calendar.appendChild(row);
           row = document.createElement("tr");
        }
    }
    calendar.appendChild(row);
});