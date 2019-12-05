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
	padding-left: 10px;
	padding-right: 10px;
	padding-top: 4px;
	padding-bottom: 4px;
	width: 14%;
}

div .cal-schedule {
	height: 125px;
	width: 150px;
}

div .cal-schedule span:not([id="scheduleView"]) {
	position: relative;
	top: 105px;
}

.modal-body {
	padding: 20px 50px;
}

#scheduleView {
	display: block;
}

.ui-datepicker-month {
	padding: 0;
	vertical-align: middle;
}

.ui-datepicker-year {
	padding: 0;
	vertical-align: middle;
}

#datepicker {
	vertical-align: -webkit-baseline-middle;
}

input[type="checkbox"] {
	vertical-align: middle;
    margin-left: 5px;
}

.progress_meter {
    background-color: #5bc0de;
    height: 100%;
}

.scheduleText {
	height: 84%;
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
	var clickScheduleno = null;
	
	$(document).ready(function() {
		drawCalendar();
		initDate();
		drawDays();
		
		// =============================================== 클릭 이벤트 추가하는 코드 ===============================================
		$("#movePrevMonth").on("click", function() {
			movePrevMonth();
		});
		$("#moveNextMonth").on("click", function() {
			moveNextMonth();
		});
		
		// 일정 추가 submit 버튼 
		$("#addBtn").on("click", function() {
			addSchedule();
		});

		// 일정 추가하는 버튼
		$("div .cal-schedule").on("click", ".add", function() {
			clickDate = new Date(year, month - 1, $(this).parent().parent().prev().text()).format("yyyy-MM-dd");

			$("#schedule_date").text(new Date(year, month - 1, $(this).parent().prev().text()));
			$("#writeFormModal").modal();
		});
		
		//일정 아이콘 클릭시 모달 보이기
		$("div .cal-schedule").on("click", "#scheduleView", function() {
			clickDate = new Date(year, month - 1, $(this).parent().prev().text()).format("yyyy-MM-dd");
			
			clickScheduleno = $(this).data("scheduleno");
			
			viewSchedule();
			
			$("#viewSchedule").modal();
		});
		
		// 기한 폼 생성 버튼
		$("#addDue_dateBtn").on("click", function() {
			$("#schedule_due_dateForm").show();
			
			$('#datepicker').datepicker({
				dateFormat: 'yy-mm-dd' //Input Display Format 변경
				,constrainInput: false //입력제한
                ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
                ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
                ,changeYear: true //콤보박스에서 년 선택 가능
                ,changeMonth: true //콤보박스에서 월 선택 가능                
                ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
                ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
                ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
                ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
                ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
                ,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
                ,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)     
			}).bind('keydown', false);
			
// 			 $('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
			//From의 초기값을 오늘 날짜로 설정
//             $('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
            //To의 초기값을 내일로 설정
//             $('#datepicker2').datepicker('setDate', '+1D'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
		});
		
		// 기한 폼 다시 숨기는 버튼 (취소버튼)
		$("#cancelBtn-due_date").on("click", function() {
			$("#schedule_due_dateForm").hide();
		});
		
		// 기한 추가 버튼
		$("#saveDue_dateBtn").on("click", function() {
			$.ajax({
				type : "post",
				url : "/schedule/modify/due_date",
				data : { "scheduleno" : clickScheduleno, "due_date" : $("#datepicker").val() },
				dataType : "text",
				success : function(data) {
					$("#schedule_due_dateForm").hide();
					$("#datepicker").val("");
					viewSchedule(clickScheduleno);
				},
				error : function(e) {
					console.log(e);
				}
			});
		});
		
		// 체크리스트 추가 버튼
		$("#addCheckListBtn").on("click", function() {
			$("#schedule_checkListForm").show();
		});
		
		// 체크리스트 작성 취소 버튼
		$("#cancelBtn-checkList").on("click", function() {
			$("#schedule_checkListForm").hide();
		});
		
		// 체크리스트 저장 버튼
		$("#saveCheckListBtn").on("click", function() {
			$.ajax({
				type : "post",
				url : "/schedule/add/checkList",
				data : { "scheduleno" : clickScheduleno, "check_content" : $("#check_content").val() },
				dataType : "text",
				success : function(data) {
					$("#check_content").val("");
					$("#schedule_checkListForm").hide();
					viewSchedule(clickScheduleno);
					getNewInfo();
				},
				error : function(e) {
					console.log(e);
				}
			});
		});
		
		// 체크리스트 클릭 이벤트
		$("#schedule_checkList").on("change", "input[type='checkbox']", function() {
			if($(this).is(":checked")) {
				$(this).attr("checked", "checked");
				checkedList($(this).data("checkno"), true);
	        } else {
				$(this).attr("checked", "");
				checkedList($(this).data("checkno"), false);
	        }
			
			getNewInfo();
		});
		
		// 지도 추가 버튼
		$("#addPlaceBtn").on("click", function() {
			
		});
		
		// ==============================================================================================================
	});
	
	// =============================================== 날짜 포맷 함수 ===============================================
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

	// ==============================================================================================================
	
	//Calendar 그리기
	function drawCalendar() {
		var setTableHTML = "";
		setTableHTML += '<table class="calendar">';
		setTableHTML += '<tr><th>SUN</th><th>MON</th><th>TUE</th><th>WED</th><th>THU</th><th>FRI</th><th>SAT</th></tr>';
		for (var i = 0; i < 6; i++) {
			setTableHTML += '<tr height="100">';
			for (var j = 0; j < 7; j++) {
				setTableHTML += '<td>';
				setTableHTML += '    <div class="cal-day"></div>';
				setTableHTML += '    <div class="cal-schedule overtext"></div>';
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
			data : { "proj_no" : "${param.proj_no}", "curYear" : year, "curMonth" : month },
			dataType : "json",
			success : function(data) {
				$("#cal_top_year").text(year);
				$("#cal_top_month").text(month);
				for (var i = firstDay.getDay(); i < firstDay.getDay() + lastDay.getDate(); i++) {
					$tdDay.eq(i).text(++dayCount);
// 					$tdSche.eq(i).append($("<span id=\"add\" class='glyphicon glyphicon-plus add'></span>"));
					
					var div = $("<div class='scheduleText'></div>");
					
					div.append($("<span id=\"add\" class='glyphicon glyphicon-plus add'></span>"));
					
					for (var j = 0; j < data.length; j++) {
						let schedule_date = data[j].schedule_date.split(" ")[1].replace(",", "");
						if (schedule_date == dayCount) {
							div.html("");
							div.append($("<div>" + data[j].title + "</div>"));
							
							if (data[j].due_date != undefined) {
								div.append($("<div>기한 : " + data[j].due_date + "</div>"));
							}
							
							if (data[j].cntCheckList != 0) {
								div.append($("<div>할 일 : " + data[j].cntCheckList + " / " + data[j].cntChecked + "</div>"));
							}
							
							$tdSche.eq(i).append($("<span id=\"scheduleView\" class='label label-info scheduleView' data-scheduleno='" + data[j].scheduleno + "'>일정보기</span>"));
							break;
						}
					} // 일정 반복문
					
					$tdSche.eq(i).prepend(div);
				} // 날짜 반복문
				
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
	}

	function addSchedule() {
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
				if (data.result) {
					$("#scheduleTitle").val("");
					$("#scheduleContent").val("");
					$("#writeFormModal").modal('hide');
					getNewInfo();
				} else {
					alert("이미 일정이 등록되어있습니다.");
				}
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	
	function viewSchedule(scheduleno) {
		$.ajax({
			type : "post",
			url : "/schedule/view",
			data : { "scheduleno" : clickScheduleno },
			dataType : "json",
			success : function(data) {
				// 모달에 상세보기 창 보여주기
				$("#schedule_title").text("일정 : " + data.title);
				$("#schedule_content").text("설명 : " + data.content);
				
				if (data.due_date != undefined) {
					$("#schedule_due_date").text("기한 : " + data.due_date);
					$("#addDue_dateBtn").hide();
				} else {
					$("#schedule_due_date").text("");
					$("#addDue_dateBtn").show();
				}
				
				if (data.checkList.length != 0) {
					$("#checkList").html("");
					for (var i = 0; i < data.checkList.length; i++) {
						var list = data.checkList;
						$("#checkList").append(list[i].check_content);
						if (list[i].do_check == 1) {
							$("#checkList").append($("<input data-checkno='" + list[i].checkno + "' type='checkbox' checked='checked'>"));
						} else {
							$("#checkList").append($("<input data-checkno='" + list[i].checkno + "' type='checkbox'>"));
						}
						
						$("#checkList").append("<br>");
					}
					onProgress();
					$(".progress").show();
					$("#checkList").show();
					// ====================== 기능하는지 확인, 수정,삭제구현=============================================================
					if (data.checkList.length > 9) {
						$("#addCheckListBtn").hide();
					}
				} else {
					$(".progress").hide();
					$("#checkList").hide();
					$("#addCheckListBtn").show();
				}
				
				if (data.place != undefined) {
// 					$("#schedule_checkList").append();
					$("#addPlaceBtn").hide();
				} else {
					$("#addPlaceBtn").show();
				}
				
			},
			error : function(e) {
				console.log(e);
			}
		})
	}
	
	function onProgress() {
		var checkedCnt = $("input[checked='checked']").length;
		var checkboxCnt = $("input[type='checkbox']").length;
		
		var percentage = parseInt(((checkedCnt / checkboxCnt) * 100),10);
		
		$(".progress_meter").css("width", percentage + "%");
		$(".progress_label").text(percentage + "%");
	}
	
	function checkedList(checkno, check) {
		$.ajax({
			type : "post",
			url : "/schedule/update/check",
			data : { "checkno" : checkno, "do_check" : check },
			dataType : "text",
			success : function(data) {
				viewSchedule(clickScheduleno);
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
</script>

<div class="container">
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
				<div class="form-group">
					<label for="scheduleTitle" class="col-sm-2 control-label"><span id="scheduleDate"></span>일정</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="title" id="scheduleTitle">
					</div>
				</div>
				<div class="form-group">
					<label for="scheduleContent" class="col-sm-2 control-label">상세 설명</label>
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
		<div class="form-group">
			<div id="schedule_title"></div>
			<br>
			<div id="schedule_content"></div>
			<br>
			<div id="schedule_due_date"></div>
			<br>
			<div id="schedule_checkList">
				<div class="progress">
  					<div class="progress_meter"><span class="progress_label"></span></div>
				</div>
				<div id="checkList"></div>
			</div>
			<br>
			<div id="schedule_due_dateForm" hidden="hidden">
				<div>
					<h2>기한 추가</h2>
					<input type="text" id="datepicker" autocomplete="off">
					<button class="btn btn-primary" id="saveDue_dateBtn">저장</button>
					<button id="cancelBtn-due_date" class="btn btn-warning" hidden="hidden">취소</button>
				</div>
			</div>
			<br>
			<div id="schedule_checkListForm" hidden="hidden">
				<div>
					<h2>체크리스트 추가</h2>
					<input type="text" class="form-control" id="check_content" style="display: inline; width: 78%;">
					<button class="btn btn-primary" id="saveCheckListBtn" style="vertical-align: baseline;">저장</button>
					<button class="btn btn-warning" id="cancelBtn-checkList" style="vertical-align: baseline;">취소</button>
				</div>
			</div>
			<br>
			<!-- 장소 추가하는 입력 폼 DIV -->
<!-- 			<div id="schedule_content"></div> -->
			
			<button id="addDue_dateBtn" class="btn btn-info">기한 추가</button>
			<button id="addCheckListBtn" class="btn btn-info">체크리스트 추가</button>
			<button id="addPlaceBtn" class="btn btn-info">만남 장소 선택</button>
       	</div>
	  </div>
    </div>
  </div>
</div>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>
