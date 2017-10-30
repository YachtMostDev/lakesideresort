var monthDifference = 0;
var roomId;
var roomData;
var monthNames = ["JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE",
  "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"
];

/**Increase efficiency by:
    - getting only the relevant information (startDate, endDate and id's)
    - creating a roomlist with only relevant information
    - creating a get in which you only get the room corresponding with the given id
**/

function prepareCalendar(id){

    roomId = id;

    $.ajax({
        type: "GET",
        url:"/api/booking/",
        dataType: 'json',
        async:true,
        success: function(data) {
            monthDifference = 0;
            drawCalendar(id, data);
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
    var currentMonth = today.getMonth()%12;

    document.getElementById("month").innerHTML = monthNames[currentMonth];
    document.getElementById("year").innerHTML = today.getFullYear();
    deleteRows();

    var roomList = [];

    if (roomData != null){
        for (i=0; i < roomData.length; i++){
            var startDate = (new Date(roomData[i].startDate.year, monthNames.indexOf(roomData[i].startDate.month), roomData[i].startDate.dayOfMonth)).getMonth();
            var endDate = (new Date(roomData[i].endDate.year, monthNames.indexOf(roomData[i].endDate.month), roomData[i].endDate.dayOfMonth)).getMonth();
            var month = today.getMonth();

            if (roomData[i].room.id == id){
                if (startDate <= month && endDate >= month){
//                    console.log(roomData[i]);
                    roomList.push(roomData[i]);
                }
            }
        }
    }

//    if(roomList != []){
//        console.log(roomList);
//    }

    for (var index = 1; index-start <= daysOfMonth; index++){
        var td = document.createElement("td");
        var a = document.createElement("a");
        var div = document.createElement("div");
        if (index - start > 0){
            for(i=0; i < roomList.length; i++){
                var startDate = new Date(roomData[i].startDate.year, monthNames.indexOf(roomData[i].startDate.month), roomData[i].startDate.dayOfMonth);
                var endDate = new Date(roomData[i].endDate.year, monthNames.indexOf(roomData[i].endDate.month), roomData[i].endDate.dayOfMonth);
                var currentDate = new Date(today.getFullYear(), today.getMonth(), index-start);
                if(startDate <= currentDate && endDate >= currentDate){
                    td.className = "red";
                    a.href = "#";
                }
            }
        }
        row.appendChild(td);
        td.appendChild(a);
        div.className = "fullSize";
        div.innerHTML = (index - start < 1)? "": index-start;
        if (index - start < 1){
            td.className = "unavailable";
        }
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