<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>Pick MI (Mate&amp;Idea)</title>
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
body {
   color: #fff;
   background: #63738a;
   font-family: 'Roboto', sans-serif;
}

.form-control {
   width: 290px;
   height: 40px;
   box-shadow: none;
   color: #969fa4;
}

.form-control:focus {
   border-color: #5cb85c;
}

.form-control, .btn {
   border-radius: 3px;
}

.signup-form {
   width: 400px;
   margin: 0 auto;
   padding: 30px 0;
}

.signup-form h2 {
   color: #636363;
   margin: 0 0 15px;
   position: relative;
   text-align: center;
}

.signup-form h2:before, .signup-form h2:after {
   content: "";
   height: 2px;
   width: 30%;
   background: #d4d4d4;
   position: absolute;
   top: 50%;
   z-index: 2;
}

.signup-form h2:before {
   left: 0;
}

.signup-form h2:after {
   right: 0;
}

.signup-form .hint-text {
   color: #999;
   margin-bottom: 30px;
   text-align: center;
}

.signup-form form {
   color: #999;
   border-radius: 3px;
   margin-bottom: 15px;
   background: #f2f3f7;
   box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
   padding: 30px;
}

.signup-form .form-group {
   margin-bottom: 20px;
}

.signup-form input[type="checkbox"] {
   margin-top: 3px;
}

.signup-form .btn {
   font-size: 16px;
   font-weight: bold;
   min-width: 140px;
   outline: none !important;
}

.signup-form .row div:first-child {
   padding-right: 10px;
}

.signup-form .row div:last-child {
   padding-left: 10px;
}

.signup-form a {
   color: #fff;
   text-decoration: underline;
}

.signup-form a:hover {
   text-decoration: none;
}

.signup-form form a {
   color: #5cb85c;
   text-decoration: none;
}

.signup-form form a:hover {
   text-decoration: underline;
}

#pwcheck {
   display: inline;
}

#numcheck {
   display: inline;
}
</style>
<script type="text/javascript">
   $(function() {
      $("#alert-success").hide();
      $("#alert-danger").hide();
      $("input").keyup(function() {
         var key = $("#key").val();
         var num = $("#num").val();
         if (key != "" || num != "") {
            if (key == num) {
               $("#alert-success").show();
               $("#alert-danger").hide();
               $("#submit").removeAttr("disabled");
            } else {
               $("#alert-success").hide();
               $("#alert-danger").show();
               $("#submit").attr("disabled", "disabled");
            }
         }
      });
   });
</script>

</head>
<body>
<div class="signup-form">

<!-- <form action="/authentic" method="post"> -->
<div class="form-group">
<p>인증번호를 입력해 주세요</p> 
<input id="key" type="hidden" class="form-control" name="key" value="${key }" >
<input id="num" type="text" class="form-control" name="num" placeholder="인증번호 입력" required="required">

<div class="alert alert-success" id="alert-success"> 인증번호가 일치합니다. <button type="button" class="btn btn-info" onclick="location.href = '/login';">확인</button></div>
<div class="alert alert-danger" id="alert-danger">인증번호가 일치하지 않습니다.</div>
</div>
<!-- </form> -->
</div>

</body>
</html>