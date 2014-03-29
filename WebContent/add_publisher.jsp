<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Publisher</title>

</head>
<body>

	<h3 align="middle">Reusable WEB CONTENT MANAGER</h3>
	<br>
	<h3 align="middle">Add the Publisher details</h3>
	<br>
	<s:form action="addpublisher" method="post" enctype="multipart/form-data"
		autocomplete="off">

		<s:textfield key="username" label="Username" />
		<s:textfield key="name" label="Name" />
		<s:password key="password" label="Password" />
		<s:submit label="Add" />
	</s:form>
	<br>
	<a href="adminhome.jsp"> BACK </a>


</body>
</html>