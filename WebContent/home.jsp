<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reusable WEB CONTENT MANAGER</title>
</head>
<body background="./layout/resources/images/iiitb.jpg">
	<h1 align="middle">Reusable WEB CONTENT MANAGER</h1>
	<div align="right">
	
	<h2>Login details </h2>
	<s:form action="loginAction" autocomplete="off">

		<s:textfield key="username" label="Username" />
		<s:password key="password" label="Password" />

		<s:submit />
	</s:form>
	</div>
</body>
</html>