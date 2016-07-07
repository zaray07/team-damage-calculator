<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Team Damage Calculator</title>
</head>
<body>
	<c:forEach items="${players}" var="player">
		<h3>${player.playerName}</h3>
		<p>${player.playerDamageDone}</p>
	</c:forEach>
</body>
</html>