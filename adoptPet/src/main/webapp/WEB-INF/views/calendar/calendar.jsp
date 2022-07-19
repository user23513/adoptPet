<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href='../../fullcalendar/lib/main.css' rel='stylesheet' />
<script src='../../fullcalendar/lib/main.js'></script>
<script>
 let schedules = [];

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
	
	//다큐먼트가 로딩되면 패치로 서버 요청
    fetch('/calendarList.do')
    .then(function (result) {
      return result.json();
    })
    .then(result => {
    		result.forEach(schedule => {
    			let event = {}
    			event.title = schedule.title;
    			event.start = schedule.startDate;
    			event.end = schedule.endDate;
    			schedules.push(event);
    		});
  
    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      initialDate: new Date(),
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectMirror: true,
      select: function(arg) {
        var title = prompt('봉사일정을 등록하세요:');
        if (title) {
        // DB에 입력처리
         fetch('/CalandarInsert.do' , {
        	 method: 'post',
        	 headers: {
        		 'Content-type': 'application/x-www-form-urlencoded'
        	 },
        	 body: "title="+title&"start="+arg.startStr&"end="+arg.endStr
         })	
        .then(result => {
        	 calendar.addEvent({
                 title: title,
                 start: arg.start,
                 end: arg.end,
                 allDay: arg.allDay
               })
        })
        }
        }
        calendar.unselect()
      },
      eventClick: function(arg) {
        if (confirm('일정을 삭제하겠습니까?')) {
          // DB에 삭제 처리
          fetch('/calendarDelete.do', {
        	  method : 'post',
        	  headers : {
        		  'Content-type': 'application/x-www-form-urlencoded'
        	  }),
        	  body : "title="+arg.event.title
          }
          .then(result => {
        	  calendar.addEvent({
        		  title:title
        		  arg.event.remove();
        	  })
        	  
          })
        }
      },
      editable: true,
      dayMaxEvents: true, // allow "more" link when too many events
      events: schedules;
    });

    calendar.render();
  });

</script>
<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 1100px;
	margin: 0 auto;
}
</style>


<title>봉사활동</title>
</head>
<body>
	<div id='calendar'></div>

</body>
</html>