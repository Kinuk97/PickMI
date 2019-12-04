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

.modal-body {
	padding: 20px 50px;
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
	
	var clickDate = null;

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
		
		$("#scheduleForm button[type='submit']").on("click", function() {
			$("#scheduleForm").append($("#scheduleForm input[type='hidden']").val(""));
		});
		
		// 일정 추가 버튼 
		$("#addBtn").on("click", function() {
			addSchedule();
		});

		$("div .cal-schedule span").on("click", function() {
			clickDate = new Date(year, month - 1, $(this).parent().prev().text()).format("yyyy-MM-dd");
			console.log(clickDate);

			$("#schedule_date").text(new Date(year, month - 1, $(this).parent().prev().text()));
			$("#writeFormModal").modal();
		});
		//일정 아이콘 클릭시 모달 보이기
		$("div .cal-schedule #scheduleView").on("click", function() {
			$("#viewSchedule").modal();
		});

	});
	
	Date.prototype.format = function(f) {
	    if (!this.valueOf()) return " ";
	 
	    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
	    var d = this;
	     
	    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
	        switch ($1) {
	            case "yyyy": return d.getFullYear();
	            case "yy": return (d.getFullYear() % 1000).zf(2);
	            case "MM": return (d.getMonth() + 1).zf(2);
	            case "dd": return d.getDate().zf(2);
	            case "E": return weekName[d.getDay()];
	            case "HH": return d.getHours().zf(2);
	            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
	            case "mm": return d.getMinutes().zf(2);
	            case "ss": return d.getSeconds().zf(2);
	            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
	            default: return $1;
	        }
	    });
	};
	 
	String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
	String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
	Number.prototype.zf = function(len){return this.toString().zf(len);};

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
		$.ajax({
			type : "post",
			url : "/schedule/list",
			data : { "proj_no" : "${proj_no}", "year" : year, "month" : month },
			dataType : "json",
			success : function(data) {
				console.log(data);
				
				$("#cal_top_year").text(year);
				$("#cal_top_month").text(month);
				for (var i = firstDay.getDay(); i < firstDay.getDay() + lastDay.getDate(); i++) {
					$tdDay.eq(i).text(++dayCount);
// 					if (${scheduleList[0].write_date.getDate()} == i) {
						
// 					}
				}
				for (var i = 0; i < 42; i += 7) {
					$tdDay.eq(i).css("color", "red");
				}
				for (var i = 6; i < 42; i += 7) {
					$tdDay.eq(i).css("color", "blue");
				}
			},
			error : function(e) {
				console.log(e);
			}
		});
		
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
// 		var scheduleList = ${scheduleList};
		
// 		console.log(scheduleList);
		
		let date = new Date("${scheduleList[0].write_date}");
		let year = date.getFullYear();
		let month = date.getMonth() + 1;
		let day = date.getDate();
		
		
	}

	//스케줄 그리기
	function drawSche() {
		setData();
		var dateMatch = null;
		for (var i = firstDay.getDay(); i < firstDay.getDay() + lastDay.getDate(); i++) {
// 			console.log(i);
			
			
			
			
// 			var txt = "";
// 			txt = jsonData[year];
// 			if (txt) {
// 				txt = jsonData[year][month];
// 				if (txt) {
// 					txt = jsonData[year][month][i];
// 					dateMatch = firstDay.getDay() + i - 1;
// 					$tdSche.eq(dateMatch).text(txt);
// 				}
// 			}
			$tdSche.eq(i).append($("<span id=\"add\" class='glyphicon glyphicon-plus'></span>"));
		}
	}

	function addSchedule() {
		alert($("#scheduleContent").val());
		$.ajax({
			type : "post",
			url : "/schedule/add",
			data : {
				"proj_no" : "${param.proj_no}",
				"schedule_date" : clickDate,
				"title" : $("#scheduleTitle").val(),
				"content" : $("#scheduleContent").val()
			},
			dataType : "json",
			success : function(data) {
				console.log(data);
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	
// 	function list() {
// 		$.ajax({
// 			type : "post",
// 			url : "/schedule/list",
// 			data : {
// 				"proj_no" : "${proj_no}",  "due_date" : "${due_date}"
// 			}
// 			dateType : "json",
// 			success :
// 		})
// 	}
</script>

<div class="container">
${param.proj_no}
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
<div id="writeFormModal" class="modal fade">
	<div class="modal-dialog modal-lg">

		<div class="modal-content">
			<div class="modal-header">
				일정 추가
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<!-- 테스트용 프로젝트 번호 -->
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"><span id="scheduleDate"></span>일정</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="title" id="scheduleTitle">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">상세 설명</label>
					<div class="col-sm-10">
						<textarea  id="scheduleContent" name="content" class="form-control" style="resize: none;" rows="10"></textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-12 text-center">
						<button id="addBtn" class="btn btn-info">ADD</button>
					</div>
				</div>
				<div style="clear: both;"></div>
			</div>
		</div>
	</div>
</div>

<!-- 일정 상세보기 -->
<div id="viewSchedule" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
  
    <div class="modal-content">
	  <div class="modal-header">
	    	일정 보기<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  </div>
	  <div class="modal-body">
	  	<form>
			<div class="form-group">
           	 	<label for="recipient-name" class="control-label">일정 간략내용</label>
          	</div>
         </form>
	  </div>
    </div>
  </div>
</div>


<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>
