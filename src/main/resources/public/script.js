function getGuest(){
$.get("http://localhost:8080/api/guest",function(data){
console.log(data);
})
}
