<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${message!=null}">
		<label style="color: red;">login error</label>
	</c:if>
	<form method="post" action="login">
		<table>
			<tbody>
				<tr>
					<td>Login:</td>
					<td><input type="text" name="j_username" id="j_username"
						size="30" maxlength="40" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="j_password" id="j_password"
						size="30" maxlength="32" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Login" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>