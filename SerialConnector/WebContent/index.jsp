<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:p="http://primefaces.prime.com.tr/ui">
<h:head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>Controle remoto de Carro</title>
</h:head>
<h:body>
	<div>
		<p align="center" style="vertical-align: middle; height: 100%">
			<button onclick="location.href='/SerialConnector/car/?comm=fw'">
				<img src="images/forward.png" alt="forward" />
			</button>
			<br />
			<button onclick="location.href='/SerialConnector/car/?comm=left'">
				<img src="images/left.png" alt="left" />
			</button>
			<button onclick="location.href='/SerialConnector/car/?comm=right'">
				<img src="images/right.png" alt="right" />
			</button>
			<br />
			<button onclick="location.href='/SerialConnector/car/?comm=bw'">
				<img src="images/backward.png" alt="backward" />
			</button>
		</p>

	</div>
</h:body>
</html>
