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
    vertical-align: top;
    margin-left: 5px;
    margin-right: 5px;
    margin-top: 3px;
}

.progress_meter {
    background-color: #5bc0de;
    height: 100%;
}

.scheduleText {
	height: 84%;
}

.check {
	vertical-align: top;
    margin: 0 5px;
	cursor: pointer;
}

#checkModifyContent {
	margin: 0px;
	margin-top: -3px;
}

.modifyFormBtn {
	cursor: pointer;
	margin: 2px 10px;
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
		
		// 일정 수정 폼 버튼
		$("#showTitleForm").on("click", function() {
			$("#schedule_title_form").show();
		});
		// 일정 내용 수정 폼 버튼
		$("#showContentForm").on("click", function() {
			$("#schedule_content_form").show();
		});
		// 일정 수정 폼 숨기는 버튼
		$("#hideTitleForm").on("click", function() {
			$("#schedule_title_form").hide();
		});
		// 일정 내용 수정 폼 숨기는 버튼
		$("#hideContentForm").on("click", function() {
			$("#schedule_content_form").hide();
		});
		
		// 수정 적용 버튼
		$("#modifyTitleBtn").on("click", function() {
			$.ajax({
				type : "post",
				url : "/schedule/modify",
				data : { "scheduleno" : clickScheduleno , "title" : $("#sc_title_form").val(), "kinds" : "1" },
				dataType : "text",
				success : function(data) {
					$("#sc_title_form").val("");
					
					viewSchedule();
				},
				error : function(e) {
					console.log(e);
				}
			});
		});
		
		// 수정 적용 버튼
		$("#modifyContentBtn").on("click", function() {
			$.ajax({
				type : "post",
				url : "/schedule/modify",
				data : { "scheduleno" : clickScheduleno , "content" : $("#sc_content_form").val(), "kinds" : "2" },
				dataType : "text",
				success : function(data) {
					$("#sc_content_form").val("");
					
					viewSchedule();
				},
				error : function(e) {
					console.log(e);
				}
			});
		});
		
		// 일정 삭제하는 버튼
		$("#scheduleRemoveBtn").on("click", function() {
			$.ajax({
				type : "post",
				url : "/schedule/remove",
				data : { "scheduleno" : clickScheduleno },
				dataType : "text",
				success : function(data) {
					$("#viewSchedule").modal('toggle');
					getNewInfo();
				},
				error : function(e) {
					console.log(e);
				}
			});
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
		
		// 수정도 마찬가지로 기한 작성하는 폼 띄우기
		$("#showDue_dateForm").on("click", function() {
			$("#addDue_dateBtn").click();
		});
		
		// 기한 폼 다시 숨기는 버튼 (취소버튼)
		$("#cancelBtn-due_date").on("click", function() {
			$("#schedule_due_dateForm").hide();
		});
		
		// 기한 추가 버튼
		$("#saveDue_dateBtn").on("click", function() {
			$.ajax({
				type : "post",
				url : "/schedule/modify",
				data : { "scheduleno" : clickScheduleno, "due_date" : $("#datepicker").val(), "kinds" : "3" },
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

		// 기한 삭제 버튼
		$("#removeDue_dateBtn").on("click", function() {
			$.ajax({
				type : "post",
				url : "/schedule/modify",
				data : { "scheduleno" : clickScheduleno, "kinds" : "3" },
				dataType : "text",
				success : function(data) {
					viewSchedule(clickScheduleno);
				},
				error : function(e) {
					console.log(e);
				}
			});
		});
		
		// 할 일 추가 버튼
		$("#addCheckListBtn").on("click", function() {
			$("#schedule_checkListForm").show();
		});
		
		// 할 일 전부 삭제하는 버튼
		$("#removeAllCheckListBtn").on("click", function() {
// 			alert(clickScheduleno);
			$.ajax({
				type : "post",
				url : "/schedule/remove/allCheck",
				data : { "scheduleno" : clickScheduleno },
				dataType : "text",
				success : function(data) {
					viewSchedule(clickScheduleno);
				},
				error : function(e) {
					console.log(e);
				}
			});
		});
		
		// 할 일 삭제하는 버튼
		$("#checkList").on("click", ".checkRemoveBtn", function() {
			$.ajax({
				type : "post",
				url : "/schedule/remove/check",
				data : { "checkno" : $(this).data("checkno") },
				dataType : "text",
				success : function(data) {
					viewSchedule(clickScheduleno);
				},
				error : function(e) {
					console.log(e);
				}
			});
		});
		
		// 할 일 수정하는 폼 나오게하는 버튼
		$("#checkList").on("click", ".checkModifyBtn", function() {
			if (!modifyCheck) {
				let checkSpan = $(this).prev();
				let content = checkSpan.text();
				
				$(checkSpan).html("<input data-checkno='" + $(this).data("checkno") + "' type='text' placeholder='수정을 다시 누르면 취소합니다' id='checkModifyContent'/>");
				$(checkSpan).append($("<span class='check glyphicon glyphicon-ok' id='modifyconfirmBtn'></span>"));
				$("#checkModifyContent").focus();
				modifyCheck = true;
			} else {
				viewSchedule();
				modifyCheck = false;
			}
		});
		
		// 할 일 수정 적용하는 버튼
		$("#checkList").on("click", "#modifyconfirmBtn", function() {
			$.ajax({
				type : "post",
				url : "/schedule/modify/check",
				data : { "checkno" : $(this).prev().data("checkno"), "check_content" : $(this).prev().val() },
				dataType : "text",
				success : function(data) {
					viewSchedule(clickScheduleno);
				},
				error : function(e) {
					console.log(e);
				}
			});
		});
		
		// 할 일 작성 취소 버튼
		$("#cancelBtn-checkList").on("click", function() {
			$("#schedule_checkListForm").hide();
		});
		
		// 할 일 저장 버튼
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
				},
				error : function(e) {
					console.log(e);
				}
			});
		});
		
		// 할 일 클릭 이벤트
		$("#schedule_checkList").on("change", "input[type='checkbox']", function() {
			if($(this).is(":checked")) {
				$(this).attr("checked", "checked");
				checkedList($(this).data("checkno"), true);
	        } else {
				$(this).attr("checked", "");
				checkedList($(this).data("checkno"), false);
	        }
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
					$("#writeFormModal").modal('toggle');
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
	var modifyCheck;
	// 일정 상세보기
	function viewSchedule(scheduleno) {
		$("#schedule_title_form").hide();
		$("#schedule_content_form").hide();
		$("#schedule_due_dateForm").hide();
		$("#schedule_checkListForm").hide();
		modifyCheck = false;
		
		$.ajax({
			type : "post",
			url : "/schedule/view",
			data : { "scheduleno" : clickScheduleno },
			dataType : "json",
			success : function(data) {
				$("#schedule_title").html("");
				$("#schedule_content").html("");
				$("#schedule_due_date").html("");
				
				// 모달에 상세보기 창 보여주기
				$("#schedule_title").append($("<div id='sc_title'>" + data.title + "</div>"));
				$("#schedule_content").append($("<div id='sc_content'>" + data.content + "</div>"));
				
				if (data.due_date != undefined) {
					$("#schedule_due_date").append($("<div id='sc_due_date'>" + data.due_date + "</div>"));
					$("[data-category='3']").show();
					$("#addDue_dateBtn").hide();
				} else {
					$("#sc_due_date").remove();
					$("[data-category='3']").hide();
					$("#addDue_dateBtn").show();
				}
				
				if (data.checkList.length != 0) {
				$("[data-category='4']").show();
					$("#checkList").html("");
					for (var i = 0; i < data.checkList.length; i++) {
						var list = data.checkList;
						if (list[i].do_check == 1) {
							$("#checkList").append($("<input data-checkno='" + list[i].checkno + "' type='checkbox' checked='checked'>"));
						} else {
							$("#checkList").append($("<input data-checkno='" + list[i].checkno + "' type='checkbox'>"));
						}
						$("#checkList").append($("<span>" + list[i].check_content + "</span>"));
						$("#checkList").append($("<span data-checkno='" + list[i].checkno + "' class='check glyphicon glyphicon-edit checkModifyBtn'></span>"))
						$("#checkList").append($("<span data-checkno='" + list[i].checkno + "' class='check glyphicon glyphicon-remove checkRemoveBtn'></span>"))
						
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
					$("[data-category='4']").hide();
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
				
				getNewInfo();
			},
			error : function(e) {
				console.log(e);
			}
		})
	}
	
	// 진행바 채우기
	function onProgress() {
		var checkedCnt = $("input[checked='checked']").length;
		var checkboxCnt = $("input[type='checkbox']").length;
		
		var percentage = parseInt(((checkedCnt / checkboxCnt) * 100),10);
		
		$(".progress_meter").css("width", percentage + "%");
		$(".progress_label").text(percentage + "%");
	}
	
	// 체크박스 클릭하면 체크여부 변경해주는 기능
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
	    	<div>일정 보기<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button><button id="scheduleRemoveBtn" type="button" class='btn btn-danger' style="float: right; margin-top: -6px;">일정 삭제</button></div>
		  <div style="clear: both;"></div>
	  </div>
	  <div class="modal-body">
		<div class="form-group">
			<h3 data-category="1">일정<span class="check glyphicon glyphicon-edit" id="showTitleForm"></span></h3><div id="schedule_title"></div>
			<h3 data-category="2">내용<span class="check glyphicon glyphicon-edit" id="showContentForm"></span></h3><div id="schedule_content"></div>
			<h3 data-category="3">기한<span class="check glyphicon glyphicon-edit" id="showDue_dateForm"></span><span class="check glyphicon glyphicon-trash" id="removeDue_dateBtn"></span></h3><div id="schedule_due_date"></div>
			<h3 data-category="4">할 일<span class="check glyphicon glyphicon-trash" id="removeAllCheckListBtn"></span></h3>
			<div id="schedule_checkList">
				<div class="progress">
  					<div class="progress_meter"><span class="progress_label"></span></div>
				</div>
				<div id="checkList"></div>
			</div>
			<br>
			<div id="schedule_title_form" hidden="hidden">
				<h3>일정 수정</h3>
				<input type="text" id="sc_title_form" placeholder="수정할 내용을 입력해주세요"  style="margin: 0;"/><span id="modifyTitleBtn" class="modifyFormBtn glyphicon glyphicon-ok"></span><span id="hideTitleForm" class="modifyFormBtn glyphicon glyphicon-remove"></span>
			</div>
			<div id="schedule_content_form" hidden="hidden">
				<h3>내용 수정</h3>
				<input type="text" id="sc_content_form" placeholder="수정할 내용을 입력해주세요" style="margin: 0;"/><span id="modifyContentBtn" class="modifyFormBtn glyphicon glyphicon-ok"></span><span id="hideContentForm" class="modifyFormBtn glyphicon glyphicon-remove"></span>
			</div>
			<div id="schedule_due_dateForm" hidden="hidden">
				<div>
					<h3>기한 정하기</h3>
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
<!-- 			<button id="addPlaceBtn" class="btn btn-info">만남 장소 선택</button> -->
       	</div>
	  </div>
    </div>
  </div>
</div>

<jsp:include page="/WEB-INF/views/layouts/footer.jsp"></jsp:include>
