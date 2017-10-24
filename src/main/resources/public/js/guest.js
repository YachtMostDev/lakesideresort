$(document).ready(function(){

    $('#guestTable').DataTable({
        "ajax":{"url":"/api/guest","dataSrc":""},
        "columns": [
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
        $.ajax({
            url:"/api/guest/" + guestNumber,
            type:"delete",
            data: JSON.stringify(formData),
            contentType: "application/json; charset=utf-8"
        });
        $('#guestTable').DataTable().ajax.reload();
    });
});

function submitGuest(){
      var formData = $("#guestForm").serializeArray().reduce(function(result, object){ result[object.name] = object.value; return result}, {});
      $.ajax({
          url:"/api/guest",
          type:"post",
          data: JSON.stringify(formData),
          contentType: "application/json; charset=utf-8"
      });
      $('#guestTable').DataTable().ajax.reload();
}

//$("#submitEdit").click(function(e){
function submitEdit(){
    var formData = $("#guestForm").serializeArray().reduce(function(result, object){ result[object.name] = object.value; return result}, {});
    var guestNumber = formData.guestNumber;
    delete formData.guestNumber;
    for(var key in formData){
        if(formData[key] == "" || formData == null) delete formData[key];
    }
    $.ajax({
        url:"/api/guest/" + guestNumber,
        type:"put",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8"
    });
    $('#guestTable').DataTable().ajax.reload();
}

//$("#submitDelete").click(function(e){
function submitDelete(){
    var formData = $("#guestForm").serializeArray().reduce(function(result, object){ result[object.name] = object.value; return result}, {});
    var guestNumber = formData.guestNumber;
    $.ajax({
        url:"/api/guest/" + guestNumber,
        type:"delete",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8"
    });
    $('#guestTable').DataTable().ajax.reload();
}

function createGuest(){
    $("#btnsubmit").attr('onclick', 'submitGuest();');
}

function editGuest(){
    $("#btnsubmit").attr('onclick', 'submitEdit();');
}

function deleteGuest(){
    $("#btnsubmit").attr('onclick', 'submitDelete()');
}

function refreshTable(){

}