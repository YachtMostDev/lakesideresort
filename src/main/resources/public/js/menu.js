$(document).ready(function(){
    var pathArray = window.location.pathname.split( '/' );
    var currentPage = pathArray[1].toLowerCase()

    $(".navbar-nav li").each(function(index) {
        $(this).removeClass("active");
        var linkText = $(this).children("a").first().attr("href").toLowerCase();
        var currentLink = linkText.split("/")[1];

        if(currentLink == currentPage){
            $(this).addClass("active");
        }
    });
});