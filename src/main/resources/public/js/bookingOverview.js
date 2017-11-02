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

    var fromString = from.toISOString().slice(0,10);
    var toString = to.toISOString().slice(0,10);

    var url = "/api/availability/all/" + fromString + "/" + toString;

    $.get(url, function (input) {
        buildOverview(table, input);
    });
}

function buildOverview(table, input){
    // Create headers
// Create headers
    var from = moment(new Date()).startOf('month');
    var to = moment(from).endOf('month');

    var head = $("<thead></thead>");
    head.append("<td>Rooms</td>");
    table.append(head);

    // Create numbered headers
    var firstRoom = Object.values(input)[0];

    for(from = moment(new Date()).startOf('month');from <= to; from.add(1,"day")){
        head.append("<td>" + from.date() + "</td>");
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
        console.log(room);
        for(from = moment(new Date()).startOf('month');from <= to; from.add(1,"day")){
            var td = $("<td></td>");

            var booked = room[from.toISOString().slice(0,10)];
            if(booked) td.addClass("booked");

            tr.append(td);
        }
    }
}