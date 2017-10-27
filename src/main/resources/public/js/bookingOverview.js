var input = {
    "101": {
        "2017-10-10": false,
        "2017-10-11": false,
        "2017-10-12": false,
        "2017-10-13": true,
        "2017-10-14": true,
        "2017-10-15": true,
        "2017-10-16": true,
        "2017-10-17": true,
        "2017-10-18": true,
        "2017-10-19": true,
        "2017-10-20": true,
        "2017-10-21": true,
        "2017-10-22": true,
        "2017-10-23": true,
        "2017-10-24": true,
        "2017-10-25": false,
        "2017-10-26": false,
        "2017-10-27": false,
        "2017-10-28": false,
        "2017-10-29": false
    },
    "102":{
        "2017-10-10": true,
        "2017-10-11": true,
        "2017-10-12": true,
        "2017-10-13": true,
        "2017-10-14": true,
        "2017-10-15": true,
        "2017-10-16": false,
        "2017-10-17": false,
        "2017-10-18": false,
        "2017-10-19": false,
        "2017-10-20": false,
        "2017-10-21": false,
        "2017-10-22": false,
        "2017-10-23": true,
        "2017-10-24": true,
        "2017-10-25": true,
        "2017-10-26": true,
        "2017-10-27": true,
        "2017-10-28": true,
        "2017-10-29": true
    },
    "103":{
        "2017-10-10": false,
        "2017-10-11": false,
        "2017-10-12": true,
        "2017-10-13": true,
        "2017-10-14": true,
        "2017-10-15": true,
        "2017-10-16": true,
        "2017-10-17": true,
        "2017-10-18": true,
        "2017-10-19": false,
        "2017-10-20": false,
        "2017-10-21": true,
        "2017-10-22": true,
        "2017-10-23": false,
        "2017-10-24": false,
        "2017-10-25": false,
        "2017-10-26": true,
        "2017-10-27": true,
        "2017-10-28": true,
        "2017-10-29": true
    }
};

var from;
var table;
var monthOffset = 0;

$(document).ready(function(){
    $("#previousMonth").click(function(){
        monthOffset--;
        refresh();
    });
    $("#nextMonth").click(function(){
        monthOffset++;
        refresh();
    });
    table = $("#booking-overview");
    refresh();
});

function refresh(){
    table.empty();
    // Get current month
    var from = moment(new Date()).startOf('month');
    from.add(monthOffset, 'month');
    var to = moment(from).endOf('month');

    $("#month").text(from.format('MMMM'));

    var url = "/api/availability/all/" + from.toISOString().slice(0,10) + ":" + to.toISOString().slice(0,10);

    $.get(url, function (input) {
        buildOverview(table, input);
    });
}

function buildOverview(table, input){
    // Create headers
    var head = $("<thead></thead>");
    head.append("<td>Rooms</td>");
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
}