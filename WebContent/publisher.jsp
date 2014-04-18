<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>publisher</title>

</head>
<body>

<h2 align="middle">publisher</h2>
	<h5 align="right"><a href="logout">Logout</a></h5>
	<br>
	<s:form action="publisher" method="post"  enctype="multipart/form-data"
		autocomplete="off">
		<s:textarea key="text" rows="35" cols="120"/>
		<s:textfield key="filename" />
		<s:submit label="SAVE AND VALIDATE"/>
	
	
	</s:form>
</body>
</html>