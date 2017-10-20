$(document).ready(function(){

    $('#guestTable').DataTable({
        "ajax":{"url":"/api/guest","dataSrc":""},
        "columns": [
            { "data": "guestNumber" },
            { "data": "firstName" },
            { "data": "surName" },
            { "data": "address" },
            { "data": "postalCode" },
            { "data": "city" },
            { "data": "country" },
            { "data": "phoneNumber" },
            { "data": "mailAddress" }
        ]
    });

    $("#submitGuest").click(function(e){
      var formData = $("#guestForm").serializeArray().reduce(function(result, object){ result[object.name] = object.value; return result}, {});
      console.log(formData);
      //console.log(JSON.stringify( $("#guestForm").serializeArray()));
      $.ajax({
          url:"/api/guest",
          type:"post",
          data: JSON.stringify(formData),
          contentType: "application/json; charset=utf-8"
      });
      $('#guestTable').DataTable().ajax.reload();
    });

    $("#submitEdit").click(function(e){
        var formData = $("#editForm").serializeArray().reduce(function(result, object){ result[object.name] = object.value; return result}, {});
        var guestNumber = formData.guestNumber;
        delete formData.guestNumber;
        for(var key in formData){
            if(formData[key] == "" || formData == null) delete formData[key];
        }
        console.log(formData);
        console.log(guestNumber);
        $.ajax({
            url:"/api/guest/" + guestNumber,
            type:"put",
            data: JSON.stringify(formData),
            contentType: "application/json; charset=utf-8"
        });
        $('#guestTable').DataTable().ajax.reload();
    });

    $("#submitDelete").click(function(e){
        var formData = $("#deleteForm").serializeArray().reduce(function(result, object){ result[object.name] = object.value; return result}, {});
        var guestNumber = formData.guestNumber;
        console.log(formData);
        console.log(guestNumber);
        $.ajax({
            url:"/api/guest/" + guestNumber,
            type:"delete",
            data: JSON.stringify(formData),
            contentType: "application/json; charset=utf-8"
        });
        $('#guestTable').DataTable().ajax.reload();
    });
});