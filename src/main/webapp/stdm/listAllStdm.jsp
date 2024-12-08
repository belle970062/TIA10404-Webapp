<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.stadium.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    StadiumService stdmSvc = new StadiumService();
    List<StadiumVO> list = stdmSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有場館資料 - listAllStdm.jsp</title>

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
	width: 800px;
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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有場館資料 - listAllStdm.jsp</h3>
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
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="stdmVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${stdmVO.stdmName}</td>
			<td>${stdmVO.stdmAddr}</td>
			<td>${stdmVO.locId}</td>
			<td>${stdmVO.stdmIntro}</td>
			<td>${stdmVO.courtCount}</td>
			<td>${stdmVO.courtPrice}</td> 
			<td>${stdmVO.admId}</td>
			<td>${stdmVO.businessHr}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/stdm/stdm.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="stdmId"  value="${stdmVO.stdmId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/stdm/stdm.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="stdmId"  value="${stdmVO.stdmId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>