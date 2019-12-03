<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/WEB-INF/views/layouts/header.jsp"></jsp:include>

<!-- add styles -->
<link href="/resources/css/jquery-ui.min.css" rel="stylesheet"
	type="text/css" />
<!-- add scripts -->
<script src="/resources/js/jquery-ui.min.js"></script>


<style type="text/css">
table {
	width: 100%;
}

.cal_top {
	text-align: center;
	font-size: 30px;
}

.cal {
	text-align: center;
}

table.calendar {
	border: 1px solid black;
	display: inline-table;
	text-align: left;
}

table.calendar td {
	vertical-align: top;
	border: 1px solid #46b8da;
	width: 100px;
	padding-left: 10px;
	padding-top: 4px;
}

div .cal-schedule {
	height: 125px;
}

div .cal-schedule span {
	position: relative;
	top: 80%;
}
</style>
<script type="text/javascript">
	var today = null;
	var year = null;
	var month = null;
	var firstDay = null;
	var lastDay = null;
	var $tdDay = null;
	var $tdSche = null;
	var jsonData = null;
	$(document).ready(function() {
		drawCalendar();
		initDate();
		drawDays();
		drawSche();
		$("#movePrevMonth").on("click", function() {
			movePrevMonth();
		});
		$("#moveNextMonth").on("click", function() {
			moveNextMonth();
		});

		$("div .cal-schedule span").on("click", function() {
			$(".modal").modal();
		});

	});

	//Calendar 그리기
	function drawCalendar() {
		var setTableHTML = "";
		setTableHTML += '<table class="calendar">';
		setTableHTML += '<tr><th>SUN</th><th>MON</th><th>TUE</th><th>WED</th><th>THU</th><th>FRI</th><th>SAT</th></tr>';
		for (var i = 0; i < 6; i++) {
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
		if (month < 10) {
			month = "0" + month;
		}
		firstDay = new Date(year, month - 1, 1);
		lastDay = new Date(year, month, 0);
	}

	//calendar 날짜표시
	function drawDays() {
		$("#cal_top_year").text(year);
		$("#cal_top_month").text(month);
		for (var i = firstDay.getDay(); i < firstDay.getDay()
				+ lastDay.getDate(); i++) {
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

	//정보갱신
	function getNewInfo() {
		for (var i = 0; i < 42; i++) {
			$tdDay.eq(i).text("");
			$tdSche.eq(i).text("");
		}
		dayCount = 0;
		firstDay = new Date(year, month - 1, 1);
		lastDay = new Date(year, month, 0);
		drawDays();
		drawSche();
	}

	//데이터 등록
	function setData() {
		jsonData = {
			"2019" : {
				"07" : {
					"17" : "제헌절"
				},
				"08" : {
					"7" : "칠석",
					"15" : "광복절",
					"23" : "처서"
				},
				"09" : {
					"13" : "추석",
					"23" : "추분"
				}
			}
		}
	}

	//스케줄 그리기
	function drawSche() {
		setData();
		var dateMatch = null;
		for (var i = firstDay.getDay(); i < firstDay.getDay()
				+ lastDay.getDate(); i++) {
			var txt = "";
			txt = jsonData[year];
			if (txt) {
				txt = jsonData[year][month];
				if (txt) {
					txt = jsonData[year][month][i];
					dateMatch = firstDay.getDay() + i - 1;
					$tdSche.eq(dateMatch).text(txt);
				}
			}
			$tdSche.eq(i).append(
					$("<span class='glyphicon glyphicon-plus'></span>"));
		}
	}

	function addSchedule() {
		$.ajax({
			type : "post",
			url : "/schedule/add",
			data : {
				"proj_no" : "${proj_no}"
			},
			dataType : "json",
			success : function(data) {

			},
			error : function(e) {
				console.log(e);
			}
		});
	}
</script>

<div class="container">
	<div id="datepicker"></div>

	<div class="cal_top">
		<a href="#" id="movePrevMonth"><span id="prevMonth"
			class="cal_tit">&lt;</span></a> <span id="cal_top_year"></span> <span
			id="cal_top_month"></span> <a href="#" id="moveNextMonth"><span
			id="nextMonth" class="cal_tit">&gt;</span></a>
	</div>
	<div id="cal_tab" class="cal"></div>
</div>

<!-- 일정 작성 -->
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
  
    <div class="modal-content">
	  <div class="modal-header">
	    	일정 추가<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  </div>
	  <div class="modal-body">
			<br><br><br><br><br><br><br><br><br>
			Test
			<br><br><br><br><br><br><br><br><br>
	  </div>
    </div>
  </div>
</div>

<!-- 일정 상세보기 -->
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
  
    <div class="modal-content">
	  <div class="modal-header">
	    	일정 보기<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  </div>
	  <div class="modal-body">
			<br><br><br><br><br><br><br><br><br>
			Test
			<br><br><br><br><br><br><br><br><br>
	  </div>
    </div>
  </div>
</div>


<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>
