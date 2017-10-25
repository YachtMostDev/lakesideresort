$(document).ready(function(){

    // Fill the table with data
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

    // Functionality for interaction when clicking on rows of the table
    $('#guestTable tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            deselect();
            $(this).addClass('selected');
            var table = $('#guestTable').DataTable();
            var data = table.row(this).data();
            apiGetSingleGuest(data.guestNumber);
            $('#myModal').modal('toggle');
        }
    });
});

// Submit the guestdata in the form to the database
function submitGuest(){
      var formData = $("#guestForm").serializeArray().reduce(function(result, object){result[object.name] = object.value; return result}, {});
      console.log(formData)
      $.ajax({
          url:"/api/guest",
          type:"post",
          data: JSON.stringify(formData),
          contentType: "application/json; charset=utf-8",
          success: function(result) {
              updateTable();
          }
      });
      deselect();
      $('#myModal').modal('toggle');
}

// Make modal ready for creating a guest
function createGuest(){
    $("#confirmbutton").css('display', 'none');
    $("#btnsubmit").attr('onclick', 'submitGuest();');
    document.getElementById("modal-title").innerHTML="Create Guest";
}

// Submit the edited data in the form to the database
function submitEdit(id){
    var formData = $("#guestForm").serializeArray().reduce(function(result, object){ result[object.name] = object.value; return result}, {});
    var guestNumber = id;
    for(var key in formData){
        if(formData[key] == "" || formData == null) delete formData[key];
    }
    $.ajax({
        url:"/api/guest/" + guestNumber,
        type:"put",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8",
        success: function(result) {
            updateTable();
        }
    });
    deselect();
    $('#myModal').modal('toggle');
}

// Delete the guest in the database with the corresponding id
function submitDelete(id){
    var formData = $("#guestForm").serializeArray().reduce(function(result, object){ result[object.name] = object.value; return result}, {});
    var guestNumber = id;
    $.ajax({
        url:"/api/guest/" + guestNumber,
        type:"delete",
        data: JSON.stringify(formData),
        contentType: "application/json; charset=utf-8",
        success: function(result) {
            updateTable();
        }
    });

    $('#myModal').modal('toggle');
    deselect();
}

// Fill the form with guestdata when updating the guest
function fillUpdateDiv(guest){
    $("#btndelete").attr('onclick', 'submitDelete(' + guest.guestNumber + ');');
    $("#btnsubmit").attr('onclick', 'submitEdit(' + guest.guestNumber + ');');
    document.getElementById("modal-title").innerHTML="Edit Guest";
    $("#firstName").val(guest.firstName);
    $("#surName").val(guest.surName);
    $("#address").val(guest.address);
    $("#postalCode").val(guest.postalCode);
    $("#city").val(guest.city);
    $("#country").val(guest.country);
    $("#phoneNumber").val(guest.phoneNumber);
    $("#mailAddress").val(guest.mailAddress);
    $("#confirmbutton").css('display', 'inline-block');
    var elem = '<button type="button" class="btn btn-danger" onclick="submitDelete(' + guest.guestNumber + ');">Confirm delete</button>';
    $('#confirmbutton').popover({animation:true, content:elem, html:true});
}

// Get the data of a guest using an id
function apiGetSingleGuest(id){
    var api = "http://localhost:8080/api/guest/" + id;
    $.get(api, function(data){
        if (data){
            fillUpdateDiv(data);
        }
    });
}

// Deselect all items in the table
function deselect(){
    $('#guestTable tr.selected').removeClass('selected');
    document.getElementById("guestForm").reset();
}

function updateTable(){
    $('#guestTable').DataTable().ajax.reload();
}