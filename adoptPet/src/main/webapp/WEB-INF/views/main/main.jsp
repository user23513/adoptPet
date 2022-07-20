<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

</style>
</head>
<body>
	 <img width="100%" alt="HTML" src="https://img.freepik.com/free-photo/group-portrait-of-adorable-puppies_53876-64778.jpg?w=1060&t=st=1658234811~exp=1658235411~hmac=4a35e0f659d120723c57fa46f13c043c52aa7b30adad3c99e929a17a7072f163">
	<div id="googleMap" style="width:500px; height: 300px;"></div>
	<script>
   function myMap(){
      var mapOptions = { 
            center:new google.maps.LatLng(35.86911582324232,128.59325935232815),
            level:3,
            zoom:20
      };
 
      var map = new google.maps.Map( 
             document.getElementById("googleMap") 
            , mapOptions );
   }
</script> 
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAD2hrjGpgsEXIDgmQ2lk2h7-eEBH-XiRc&callback=myMap"></script>
</body>
</html>