<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.stadium.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	StadiumVO stdmVO = (StadiumVO) request.getAttribute("stadiumVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>場館資料 - listOneStdm.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>場館資料 - listOneStdm.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>場館名稱</th>
		<th>場館地址</th>
		<th>地點編號</th>
		<th>場館簡介</th>
		<th>球場數量</th>
		<th>球場價格</th>
		<th>營業時間</th>
		<th>管理員編號</th>
	</tr>
	<tr>
		<td><%=stdmVO.getStdmName()%></td>
		<td><%=stdmVO.getStdmAddr()%></td>
		<td><%=stdmVO.getLocId()%></td>
		<td><%=stdmVO.getStdmIntro()%></td>
		<td><%=stdmVO.getCourtCount()%></td>
		<td><%=stdmVO.getCourtPrice()%></td>
		<td><%=stdmVO.getAdmId()%></td>
		<td><%=stdmVO.getBusinessHr()%></td>
	</tr>
</table>

</body>
</html>