<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/WEB-INF/views/layouts/header.jsp"></jsp:include>

<script type="text/javascript">
	var today = null;
	var year = null;
	var month = null;
	var firstDay = null;
	var lastDay = null;
	var $tdDay = null;
	var $tdSche = null;

	//calendar 그리기
	function drawCalendar() {
		var setTableHTML = "";
		setTableHTML += '<table class="calendar">';
		setTableHTML += '<tr class="schedule-header"><th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th></tr>';
		for (var i = 0; i < 6; i++) {
			// tr 높이 지정
			setTableHTML += '<tr height="100">';
			for (var j = 0; j < 7; j++) {
				setTableHTML += '<td style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap">';
				setTableHTML += '    <div class="cal-day"></div>';
				setTableHTML += '    <div class="cal-schedule"></div>';
				setTableHTML += '</td>';
			}
			setTableHTML += '</tr>';
		}
		setTableHTML += '</table>';
		$("#cal_tab").html(setTableHTML);
	}

	//날짜 초기화
	function initDate() {
		$tdDay = $("td div.cal-day")
		$tdSche = $("td div.cal-schedule")
		dayCount = 0;
		today = new Date();
		year = today.getFullYear();
		month = today.getMonth() + 1;
		firstDay = new Date(year, month - 1, 1);
		lastDay = new Date(year, month, 0);
	}

	//calendar 날짜표시
	function drawDays() {
		$("#cal_top_year").text(year);
		$("#cal_top_month").text(month);
		for (var i = firstDay.getDay(); i < firstDay.getDay() + lastDay.getDate(); i++) {
			$tdDay.eq(i).text(++dayCount);
		}
		for (var i = 0; i < 42; i += 7) {
			$tdDay.eq(i).css("color", "red");
		}
		for (var i = 6; i < 42; i += 7) {
			$tdDay.eq(i).css("color", "blue");
		}
	}

	//calendar 월 이동
	function movePrevMonth() {
		month--;
		if (month <= 0) {
			month = 12;
			year--;
		}
		if (month < 10) {
			month = String("0" + month);
		}
		getNewInfo();
	}

	function moveNextMonth() {
		month++;
		if (month > 12) {
			month = 1;
			year++;
		}
		if (month < 10) {
			month = String("0" + month);
		}
		getNewInfo();
	}

	function getNewInfo() {
		for (var i = 0; i < 42; i++) {
			$tdDay.eq(i).text("");
		}
		dayCount = 0;
		firstDay = new Date(year, month - 1, 1);
		lastDay = new Date(year, month, 0);
		drawDays();
	}
	
	$(window).ready(function() {
		// 달력 만들기
		drawCalendar();
		// 날짜 초기화 (오늘 날짜)
		initDate();
		// 일 그리기
		drawDays();
		$("#movePrevMonth").on("click", function() {
			movePrevMonth();
		});
		$("#moveNextMonth").on("click", function() {
			moveNextMonth();
		});
	});
</script>
<style type="text/css">
/* 상단바 */
.cal_top {
	text-align: center;
	font-size: 30px;
}

/* 달력을 가운데 정렬 */
.cal {
	text-align: center;
}

/* 달력 부분 */
table.calendar {
	border: 1px solid #46b8da;
	display: inline-table;
	text-align: right;
}

/* td 하나 */
table.calendar td {
	vertical-align: top;
	border: 1px solid #46b8da;
	width: 100px;
}

/* 스케쥴러 헤더 */
table th {
	border: 1px solid #46b8da;
	text-align: center;
}
</style>

<div class="container">
	<div class="cal_top">
		<a href="#" id="movePrevMonth"><span id="prevMonth"
			class="cal_tit">&lt;</span></a> <span id="cal_top_year"></span> <span
			id="cal_top_month"></span> <a href="#" id="moveNextMonth"><span
			id="nextMonth" class="cal_tit">&gt;</span></a>
	</div>
	<div id="cal_tab" class="cal"></div>

</div>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>
