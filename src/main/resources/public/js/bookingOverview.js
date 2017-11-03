var from;
var to;
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
    from = moment(new Date()).startOf('month');
    from.add(monthOffset, 'month');
    from.add(1, 'hour');
    to = moment(from).endOf('month');

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
    var head = $("<thead></thead>");
    head.append("<td>Rooms</td>");
    table.append(head);

    for(var i = moment(from); i <= to; i.add(1,"day")){
        head.append("<td>" + i.date() + "</td>");
    }

    // Create body
    var body = $("<tbody></tbody>");
    table.append(body);

    if(Object.keys(input).length < 1){
        var colspan = to.format('D');
        var tr = $("<tr><td></td></tr>");
        var td = $("<td colspan=" + colspan + ">No rooms available...</td>")
        tr.append(td);
        body.append(tr);
    } else {
        for(var roomNumber in input){
            // Room number row
            var tr = $("<tr></tr>");
            body.append(tr);
            tr.append("<td>" + roomNumber + "</td>");

            var room = input[roomNumber];
            console.log(room);
            for(var i = moment(from); i <= to; i.add(1,"day")){
                var td = $("<td></td>");

                var booked = room[i.toISOString().slice(0,10)];
                if(booked) td.addClass("booked");

                tr.append(td);
            }
        }
    }
}