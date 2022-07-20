<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript" src="/js/bootstrap.js"></script>

<link href='../adoptPet/fullcalendar/lib/main.css' rel='stylesheet' />
<script src='../adoptPet/fullcalendar/lib/main.js'></script>
</head>

	<script>
		//let schedules = [];
		//데이터 가져와서

		document.addEventListener('DOMContentLoaded', function() {
			$(function () {
				var request = $.ajax({
					url:"/full-calendar/calendar-admin", // 변경하기
					method: "GET",
					dataType:"json"
				});
				request.done(function (data) {
	                    console.log(data); // log 로 데이터 찍어주기.
	 

			var calendarEl = document.getElementById('calendar');
			var calendar = new FullCalendar.Calendar(calendarEl, {

				headerToolbar : {
					left : 'prev,next today,addEventButton',
					center : 'title', // YYYY년 MM월
					right : 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
					
				},
				customButtons : {
					addEventButton : { // 일정추가 버튼 설정
						text : "일정추가", // 버튼 내용
						click : function() { // 버튼 클릭 시 이벤트 추가
							$("#calendarModal").modal("show"); // modal 나타내기

							$("#addCalendar").on(
									"click",
									function() { // modal의 추가 버튼 클릭 시
										var content = $("#calendar_content").val();
										var start_date = $("#calendar_start_date").val();
										var end_date = $("#calendar_end_date").val();

										//내용 입력 여부 확인
										if (content == null || content == "") {
											alert("내용을 입력하세요.");
										} else if (start_date == ""
												|| end_date == "") {
											alert("날짜를 입력하세요.");
										} else if (new Date(end_date)
												- new Date(start_date) < 0) { // date 타입으로 변경 후 확인
											alert("종료일이 시작일보다 먼저입니다.");
										} else { // 정상적인 입력 시
											var obj = {
												"title" : content,
												"start" : start_date,
												"end" : end_date
											}//전송할 객체 생성

											console.log(obj); //서버로 해당 객체를 전달해서 DB 연동 가능
										}
									});
						}
					}
				},
				locale: 'ko'
				editable : true
			});
			calendar.render(); //달력을 활성화 시켜주세요
		});

		/*   	// 서버에 요청
		 fetch('calendarList.do') // schedules가 db에 있는 모든 리스트
		 .then(function (result) {
		 console.log(result.json();
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
		 fetch('calendarDelete.do', {
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
		 */

		// }); // document.addEventListener('DOMContentLoaded', function ()
	</script>
	<body>
	<div id='calendar'></div>
	<button type="button" onclick="location.href='volReviewForm.do'"></button>
	
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

</body>
</html>