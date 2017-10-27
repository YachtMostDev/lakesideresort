var input = {
    "101": {
        "2017-10-20": false,
        "2017-10-21": true,
        "2017-10-22": true,
        "2017-10-23": true,
        "2017-10-24": false
    },
    "102":{
        "2017-10-20": true,
        "2017-10-21": true,
        "2017-10-22": false,
        "2017-10-23": false,
        "2017-10-24": false
    },
    "103":{
        "2017-10-20": false,
        "2017-10-21": false,
        "2017-10-22": false,
        "2017-10-23": false,
        "2017-10-24": false
    }
};

$(document).ready(function(){
    var table = $("#booking-overview");

    // Create headers
    var head = $("<thead></thead>");
    head.append("<td></td>");
    table.append(head);

    // Create numbered headers
    var firstRoom = Object.values(input)[0];
    for(var day in firstRoom){
        head.append("<td>" + day + "</td>");
    }

    // Create body
    var body = $("<tbody></tbody>");
    table.append(body);
    for(var roomNumber in input){
        // Room number row
        var tr = $("<tr></tr>");
        body.append(tr);
        tr.append("<td>" + roomNumber + "</td>");

        var room = input[roomNumber];
        for(var day in room){
            var td = $("<td></td>");

            var booked = room[day];
            if(booked) td.addClass("booked");

            tr.append(td);
        }
    }
});