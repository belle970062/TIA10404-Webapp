<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.stadium.model.*"%>

<% // 從 Servlet 中取得的 stdmVO 物件
	StadiumVO stdmVO = (StadiumVO) request.getAttribute("stadiumVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>場館資料新增 - addStdm.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>場館資料新增 - addStdm.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤訊息顯示 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="stdm.do" name="form1">
<table>
	<tr>
		<td>場館名稱:</td>
		<td><input type="text" name="stdmName" value="<%= (stdmVO == null) ? "" : stdmVO.getStdmName() %>" size="45"/></td>
	</tr>
	<tr>
		<td>場館地址:</td>
		<td><input type="text" name="stdmAddr" value="<%= (stdmVO == null) ? "" : stdmVO.getStdmAddr() %>" size="45"/></td>
	</tr>
	<tr>
		<td>地點編號:</td>
		<td><input type="text" name="locId" value="<%= (stdmVO == null) ? "" : stdmVO.getLocId() %>" size="45"/></td>
	</tr>
	<tr>
		<td>場館簡介:</td>
		<td><textarea name="stdmIntro" rows="4" cols="45"><%= (stdmVO == null) ? "" : stdmVO.getStdmIntro() %></textarea></td>
	</tr>
	<tr>
		<td>球場數量:</td>
		<td><input type="text" name="courtCount" value="<%= (stdmVO == null) ? "" : stdmVO.getCourtCount() %>" size="45"/></td>
	</tr>
	<tr>
		<td>球場價格:</td>
		<td><input type="text" name="courtPrice" value="<%= (stdmVO == null) ? "" : stdmVO.getCourtPrice() %>" size="45"/></td>
	</tr>
	<tr>
		<td>營業時間:</td>
		<td><input type="text" name="businessHr" value="<%= (stdmVO == null) ? "" : stdmVO.getBusinessHr() %>" size="45"/></td>
	</tr>
	<tr>
		<td>管理員編號:</td>
		<td><input type="text" name="admId" value="<%= (stdmVO == null) ? "" : stdmVO.getAdmId() %>" size="45"/></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</FORM>

</body>
</html>

