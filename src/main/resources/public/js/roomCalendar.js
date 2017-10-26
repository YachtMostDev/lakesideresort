var monthDifference = 0;
var roomId;
var roomData;
var monthNames = ["January", "February", "March", "April", "May", "June",
  "July", "August", "September", "October", "November", "December"
];

function prepareCalendar(id){

    roomId = id;

    $.ajax({
        type: "GET",
        url:"/api/booking",
        dataType: 'json',
        async:true,
        success: function(data) {
            drawCalendar(id, data);
            //console.log(booking[0].guest);
            //console.log(booking[0].startDate);
            //console.log(booking[1].startDate);
        }
    });
}

function drawCalendar(id, data){
    var calendar = document.getElementById("calendar");
    var row = document.createElement("tr");
    var today = new Date();

    //Calculates days of the month
    var daysOfMonth = new Date(new Date(today.getFullYear(), today.getMonth() + 1 + monthDifference, 1) -1).getDate();
    var start = new Date(today.getYear(), today.getMonth()+monthDifference, 2).getDay();

    roomData = data;
    today.setMonth(today.getMonth()+monthDifference);
    var month = monthNames[today.getMonth()%12]
    document.getElementById("month").innerHTML = month;
    document.getElementById("year").innerHTML = today.getFullYear();
    deleteRows();

    //console.log(roomData);

    //console.log("year: " + (roomData[0].startDate.year == today.getFullYear()));
    //console.log(roomData[0].startDate.year);
    //console.log(today.getFullYear());

    //console.log("month: " + (roomData[0].startDate.month == month.toUpperCase()));
    //console.log(month.toUpperCase());
    //console.log(roomData[0].startDate.month);

    //console.log("day: " + (roomData[0].startDate.dayOfMonth == today.getDate()));
    //console.log(roomData[0].startDate.dayOfMonth);
    //console.log(today.getDate());
    //console.log(id);
    //console.log(roomData[0].room.id);

    var roomList = [];

    for (i=0; i < roomData.length; i++){
        if (roomData[i].room.id == id){
            if ((roomData[i].startDate.year == today.getFullYear()) || (roomData[i].endDate.year == today.getFullYear()) ){
                if( (roomData[i].startDate.month == month.toUpperCase()) || (roomData[i].endDate.month == month.toUpperCase()) ){
                    //console.log(roomData[i]);
                    roomList.push(roomData[i]);
                }
            }
        }
    }

    if(roomList != []){
        console.log(roomList);
    }

    for (var index = 1; index-start <= daysOfMonth; index++){
        var td = document.createElement("td");
        var a = document.createElement("a");
        var div = document.createElement("div");
        for(i=0; i < roomList.length; i++){
            if(index >= roomData[0].startDate.dayOfMonth && index <= index)
            {
                td.className = "red";
            }
        }
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

function lastMonth(){
    monthDifference--;
    drawCalendar(roomId, roomData);
}

function nextMonth(){
    monthDifference++;
    drawCalendar(roomId, roomData);
}