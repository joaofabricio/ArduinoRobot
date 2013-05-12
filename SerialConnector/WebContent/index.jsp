<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Controle de Carro</title>
</head>
<body>


	<div>
		<p align="center">
			<button onclick="location.href='/SerialConnector/CarServlet?comm=fw'">up</button>
			<br />
			<button onclick="location.href='/SerialConnector/CarServlet?comm=left'">left</button>
			<button onclick="location.href='/SerialConnector/CarServlet?comm=right'">right</button>
			<br />
			<button onclick="location.href='/SerialConnector/CarServlet?comm=bw'">down</button>
		</p>

	</div>

</body>
</html>