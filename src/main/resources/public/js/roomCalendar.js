var monthDifference = 0;
var roomId;
var monthNames = ["JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE",
  "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"
];

/**Increase efficiency by:
    - getting only the relevant information (startDate, endDate and id's)
    - creating a roomlist with only relevant information
    - creating a get in which you only get the room corresponding with the given id
**/

function retrieveDataForCalendar(id){
    roomId = id;

    var today = new Date();
    var startOfMonth = new Date(today.getFullYear(), today.getMonth() + monthDifference, 1, 23);
    var endOfMonth = new Date(new Date(startOfMonth.getFullYear(), startOfMonth.getMonth() + 1, 1) - 1);

    $.ajax({
        type: "GET",
        url:"/api/availability/byRoom/" + id + "/" + startOfMonth.toISOString().slice(0,10) + "/" + endOfMonth.toISOString().slice(0,10),
        dataType: 'json',
        async:true,
        success: function(data) {
            drawCalendar(data, startOfMonth, endOfMonth);
        }
    });
}

function drawCalendar(data, startOfMonth, endOfMonth){
    var calendar = document.getElementById("calendar");
    var row = document.createElement("tr");

    deleteRows();

    document.getElementById("month").innerHTML = monthNames[startOfMonth.getMonth() % 12];
    document.getElementById("year").innerHTML = "" + startOfMonth.getFullYear();

    var dateArray = [];

    for(var forStartOfMonth = new Date(startOfMonth); forStartOfMonth <= endOfMonth; forStartOfMonth.setDate(forStartOfMonth.getDate()+1)){
        dateArray.push(new Date(forStartOfMonth));
    }

    var count = 0;
    var temp = ((startOfMonth.getDay() == 0)?7:startOfMonth.getDay())*-1+1;
    for (var index = temp; index < endOfMonth.getDate(); index++){
        count++;
        var td = document.createElement("td");
        var a = document.createElement("a");
        var div = document.createElement("div");
        if (index >= 0){
            if(data[dateArray[index].toISOString().slice(0,10)]){
                td.style.backgroundColor = "red";
                a.style.color = "white";
            }
            div.innerHTML = dateArray[index].getDate();
        } else {
            div.innerHTML = "";
        }
        row.appendChild(td);
        td.appendChild(a);
        div.className = "fullSize";
        a.appendChild(div);
        if(count % 7 == 0){
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
    retrieveDataForCalendar(roomId);
}

function nextMonth(){
    monthDifference++;
    retrieveDataForCalendar(roomId);
}