<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset='utf-8' />
  <link href='main.css' rel='stylesheet' />
  <script src='main.js'></script>
  <script>
  	// 라이브러리 기본 js 세팅
    // let schedules = [];

    document.addEventListener('DOMContentLoaded', function () {
      var calendarEl = document.getElementById('calendar');
      // new FullCalendar.calendar(대상 DOM 객체, {속성:속성값, 속성2:속성값2..})

      // 서버에 요청
        fetch('FrontController') // schedules가 db에 있는 모든 리스트
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

          // 원래 코드 ~ calendar.render();
          var calendar = new FullCalendar.Calendar(calendarEl, {
            headerToolbar: {
              left: 'prev,next today',
              center: 'title',
              right: 'dayGridMonth,timeGridWeek,timeGridDay'
            },
            initialDate: new Date(), //'2020-09-12', // 초기 로딩 날짜
            navLinks: true, // can click day/week names to navigate views
            selectable: true,
            selectMirror: true,
            // 이벤트명 : function(){} : 각 날짜에 대한 이벤트를 통해 처리할 내용
            select: function (arg) {
            	// console.log(arg)
              var title = prompt('이벤트를 등록하세요:');
            // title 값이 있을때,  화면 conlendar.addEvent() json 형식으로 일정을 추가  
            console.log(title);
            console.log(arg);
              if (title) {
                // DB에 입력처리
                fetch('FrontController', {
                    method: 'post',
                    headers: {
                      'Content-type': 'application/x-www-form-urlencoded'
                    },
                    body: `cmd=insert&title=${title}&start=${arg.startStr}&end=${arg.endStr}`
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
            // 존재하는 일정 클릭시
            eventClick: function (arg) {
              if (confirm('일정을 삭제하겠습니까?')) {
                // 삭제
				console.log(arg)
                fetch('FrontController', {
                    method: 'post',
                    headers: {
                      'Content-type': 'application/x-www-form-urlencoded'
                    },
                    body: `cmd=delete&title=${arg.event.title}`
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
<title>봉사일정</title>
</head>
<body>
	<div id='calendar'></div> <!-- 기본 달력 생성 -->

	
	
</body>
</html>