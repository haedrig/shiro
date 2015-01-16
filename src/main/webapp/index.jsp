<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	Hello,
	<shiro:principal />
	, how are you today?
	<a href="logout">logout</a>
	
	
	<shiro:hasPermission name="user:admin">
		<br/>
		<br/>
		<br/>
		<a href="admin.jsp">Administer the system</a>
	</shiro:hasPermission>
</html>