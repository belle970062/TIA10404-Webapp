<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.stadium.model.*"%>

<html>
<head>
<title>IBM Stdm: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Stdm: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Stdm: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllStdm.jsp'>List</a> all Stdms.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="stdm.do" >
        <b>輸入場館編號 (如1):</b>
        <input type="text" name="stadiumId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="stdmSvc" scope="page" class="com.stadium.model.StadiumService" />
   
  <li>
     <FORM METHOD="post" ACTION="stdm.do" >
       <b>選擇場館編號:</b>
       <select size="1" name="stadiumId">
         <c:forEach var="stdmVO" items="${stdmSvc.all}" > 
          <option value="${stdmVO.stdmId}">${stdmVO.stdmId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="stdm.do" >
       <b>選擇場館名稱:</b>
       <select size="1" name="stdmId">
         <c:forEach var="stdmVO" items="${stdmSvc.all}" > 
          <option value="${stdmVO.stdmId}">${stdmVO.stdmName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>場地管理</h3>

<ul>
  <li><a href='addStdm.jsp'>Add</a> a new Stdm.</li>
</ul>

</body>
</html>