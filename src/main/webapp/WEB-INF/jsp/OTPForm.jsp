<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Security Tags -->
<!-- Security Tags -->
<c:set var="context" value="${pageContext.request.contextPath}" />
<!doctype html>
<html lang="en" dir="${param.lang == 'ar' ? 'rtl' : 'ltr'}">
<head>
<title>SuperCombo</title>
	<script src="${context}/resources/javascript-files/jquery-3.2.1.min.js" type="text/javascript"></script>
<link href="${context}/resources/css/style1.css" type="text/css" rel="stylesheet" />
<style>
.footer {
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  background-color: black;
  color: white;
  text-align: center;
}
.langDropdown{
    float: right;
}
select#langlist {
      min-height: 2vh;
    border-radius: 5px 5px;
}
/* Start by setting display:none to make this hidden.
   Then we position it in relation to the viewport window
   with position:fixed. Width, height, top and left speak
   for themselves. Background we set to 80% white with
   our animation centered, and no-repeating */
.modal {
    display:    none;
    position:   fixed;
    z-index:    1000;
    top:        0;
    left:       0;
    height:     100%;
    width:      100%;
    background: rgba( 255, 255, 255, .8 ) 
                url('http://i.stack.imgur.com/FhHRx.gif') 
                50% 50% 
                no-repeat;
}

/* When the body has the loading class, we turn
   the scrollbar off with overflow:hidden */
body.loading .modal {
    overflow: hidden;   
}

/* Anytime the body has the loading class, our
   modal element will be visible */
body.loading .modal {
    display: block;
}
</style>
</head>
<body  data-mobile="${not empty param.mobile ? param.mobile : 'null'}"
data-key-value="${not empty param.key ? param.key : 'null'}"
data-lang-param="${pageContext.response.locale}">
<div class="container-fluid back">
	<div class="langDropdown" dir="ltr">
			<select class="browser-default select-lang-drpdwn-fp" id="langlist">
				<option value="en" class="fontBlack">English</option>
				<option value="ar" class="fontBlack"><spring:message code="lang.arabic" /></option>
			</select>
		</div>
        <h1 class="logo"><spring:message code="title" /></h1>

<div class="container">
	<div class="error"></div>
	<div class="success"></div>
	<form id="frm-mobile-verification">
	<div class="form-row">
		<label  class='required'><spring:message code="optSend" /></label>		
	</div>

	<div class="form-row">
		<input  type="tel"  id="mobileOtp" class="form-input" placeholder="<spring:message code="enterOTP" />">		
	</div>

		<input type="button" class="btnVerify" value="<spring:message code="verify" />" onClick="verifyOTP();">		
</form>
<%-- <a href="<?php echo $url; ?>" class="button primary-button" style="margin: auto;">خروج</a> --%>
</div>
	<%-- <p class="desc"  ><spring:message code="disclaimer" /></p> --%></div>


<!-- <div class="footer">
  <p>&copy; way2game</p>
</div> -->
<div class="modal"><!-- Place at bottom of page --></div>
</body>
<script src="${context}/resources/javascript-files/otpForm.js"></script>
</html>