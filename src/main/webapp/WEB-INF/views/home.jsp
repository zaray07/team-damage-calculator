<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Team Damage Calculator</title>
</head>
<body>
	<form:form method="post" commandName="formAttributeServerLog">
           	Wklej tu swojego server loga:
            <BR>
		<form:textarea path="serverLogText" name="text" ROWS="5"></form:textarea>
		<BR>
		<INPUT TYPE="SUBMIT" VALUE="Submit">
	</form:form>
	<br>

</body>
</html>

