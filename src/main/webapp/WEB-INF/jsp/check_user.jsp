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
</head>
<body>
<div class="container-fluid back">
<div id="status" class="form-heading" style="text-align: center;"></div>
</div>
</body>
</html>