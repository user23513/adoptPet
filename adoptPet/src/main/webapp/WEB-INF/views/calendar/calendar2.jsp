<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript" src="/js/bootstrap.js"></script>

<link href='../adoptPet/fullcalendar/lib/main.css' rel='stylesheet' />
<script src='../adoptPet/fullcalendar/lib/main.js'></script>


<script>
	let schedules = [];

	document.addEventListener('DOMContentLoaded', function () {
  	var calendarEl = document.getElementById('calendar');
    
  	// 서버에 요청
    fetch('/calendarList.do') // schedules가 db에 있는 모든 리스트
      .then(function (result) {
        return result.json();
      })
      .then(function (result) {
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
          select: function (arg) {
            var title = prompt('봉사일정을 등록하세요:');
            console.log(title);
            console.log(arg);
            if (title) {
              // DB에 입력처리
              fetch('calendarInsert.do', {
                  method: 'post',
                  headers: {
                    'Content-type': 'application/x-www-form-urlencoded'
                  },
                  body: "title="+title+"&start="+arg.startStr+"&end="+arg.endStr
                })
                .then(result => result.json())
                .then(result => {
                  console.log(result);

                  // 화면에 그려주는 부분
                  calendar.addEvent({
                    title: title,
                    start: arg.start,
                    end: arg.end,
                    allDay: arg.allDay
                  })

                })
                .catch(err => console.error(err));

            }
            calendar.unselect();
          },
          eventClick: function (arg) {
            if (confirm('일정을 삭제하겠습니까?')) {
              // 삭제
				console.log(arg)
              fetch('/calendarDelete.do', {
                  method: 'post',
                  headers: {
                    'Content-type': 'application/x-www-form-urlencoded'
                  },
                  body: "title="+arg.event.title
                })
                .then(result => result.json())
                .then(result => {
              	  console.log(result);
              	  if(result.retCode == 'Success'){
	                	  arg.event.remove();
              	  }
                })
                .catch(err => console.log(err))
              
            }
          },
          editable: true,
          dayMaxEvents: true, // allow "more" link when too many events
          events: schedules
        });

        calendar.render();

        console.log(schedules);
      }) // schedules => [{},{},{}]

      .catch(function (err) {
        console.error(err);
      })


	 }); // document.addEventListener('DOMContentLoaded', function ()
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