$(document).ready(function(){
    $.ajax({
        url: "http://127.0.0.1:8080/bootstrap/index.html"
    }).then(function(data){
        for(i = 0; i < data.length; i++){
            $("#roomSelect").append("<option><option>")
                .attr("value", data[i].roomNumber)
                .text("Room " + data[i].roomNumber);
        }
    });

});

