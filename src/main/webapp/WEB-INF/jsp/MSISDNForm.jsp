<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="context" value="${pageContext.request.contextPath}" />

<!doctype html>

<html dir="${param.lang == 'ar' ? 'rtl' : 'ltr'}">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
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
<body data-lang-param="${pageContext.response.locale}"
data-key="${key}"
data-interfacee="${interfacee}"
data-transid="${t_id}">

<div class="container-fluid back">
		<div class="langDropdown" dir="ltr">
			<select class="browser-default select-lang-drpdwn-fp" id="langlist">
				<option value="en" class="fontBlack">English</option>
				<option value="ar" class="fontBlack"><spring:message code="lang.arabic" /></option>
			</select>
		</div>
            <h1 class="logo"><spring:message code="title" /></h1>
            <input type="hidden" id="operator" value='${key}'/>
	<%-- 		<h2 ><spring:message code="headertext" /></h2> --%>
<div class="container">
		<div class="error"></div>
		 <div style="font-size: 120%;padding-bottom: 4%;"><div style="color: antiquewhite;float: left;padding-right: 4%;"><spring:message code="SelectOp" /></div><select class="browser-default select-lang-drpdwn-fp" id="oplist">
				<option value="1" class="fontBlack">STC</option>
				<option value="2" class="fontBlack">Mobily</option>
				<option value="3" class="fontBlack">Zain</option>
        </select></div>
		<form id="frm-mobile-verification" onsubmit="return subscribe()">
			<div  class="form-heading"><spring:message code="mobileNumberTitle" /></div>

			<div class="form-row">
				<input  type="tel" id="msisdn" class="form-input"
					placeholder="<spring:message code="placeholder" />">
			</div>
			<input  type="submit" class="btnSubmit" value="<spring:message code="subscribe" />">
		</form>
<%--  		<a href="<?php echo $url; ?>" class="button primary-button" style="margin: auto;">خروج</a> --%>
	</div>
	<p class="desc">
	<c:if test="${key == 1}">
 <spring:message code="disclaimerStc" />
    </c:if>
    	<c:if test="${key == 2}">
<spring:message code="disclaimerMobily" />
    </c:if>
    	<c:if test="${key == 3}">
<spring:message code="disclaimerZain" />
    </c:if>
	</p>
</div>


<!-- <div class="footer">
  <p>&copy; way2game</p>
</div> -->
<div class="modal"><!-- Place at bottom of page --></div>
</body>
<script src="${context}/resources/javascript-files/msisdnForm.js"></script>
</html>
