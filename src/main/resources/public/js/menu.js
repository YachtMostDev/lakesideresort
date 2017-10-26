$(document).ready(function(){
    var pathArray = window.location.pathname.split( '/' );
    $(".navbar-nav").each(function(i, obj) {
        obj.removeClass("active");
        if(obj.Text() == pathArray[0]){
            obj.addClass("active");
        }
    });
});