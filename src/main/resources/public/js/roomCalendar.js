var monthDifference = 0;
var monthNames = ["January", "February", "March", "April", "May", "June",
  "July", "August", "September", "October", "November", "December"
];

/*function getRoom(roomID){
    $.ajax({
        type: "GET",
        url:"/api/room/" + roomID,
        dataType: 'json',
        async:false,
        success: function(data) {
            room = data;
        }
    });
}*/

function setRoom(id){
    $('#calendarContainer').attr('room', id);
    drawCalendar(id);
}

function drawCalendar(id){
    var calendar = document.getElementById("calendar");
    var row = document.createElement("tr");
    var today = new Date();

    console.log($("#calendarContainer").attr("room"));

    //Calculates days of the month
    var daysOfMonth = new Date(new Date(today.getFullYear(), today.getMonth() + 1 + monthDifference, 1) -1).getDate();
    var start = new Date(today.getYear(), today.getMonth()+monthDifference, 2).getDay();

    today.setMonth(today.getMonth()+monthDifference);

    document.getElementById("month").innerHTML = monthNames[today.getMonth()%12];
    document.getElementById("year").innerHTML = today.getFullYear();

    deleteRows();

    for (var index = 1; index-start <= daysOfMonth; index++){
        var td = document.createElement("td");
        var a = document.createElement("a");
        var div = document.createElement("div");
        td.className = "red";
        row.appendChild(td);
        div.className = "fullSize";
        td.appendChild(a)
        a.href = "#";
        div.innerHTML = (index - start < 1)? "": index-start;
        a.appendChild(div);
        if(index % 7 == 0){
           row.className = "tableRow";
           calendar.appendChild(row);
           row = document.createElement("tr");
        }
    }
    row.className = "tableRow";
    calendar.appendChild(row);
}

function deleteRows(){
    $('.tableRow').remove();
}

function lastMonth(room){
    monthDifference--;
    drawCalendar();
}

function nextMonth(room){
    monthDifference++;
    drawCalendar();
}