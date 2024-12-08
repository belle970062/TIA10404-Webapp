package com.stadium.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.stadium.model.*;

public class StadiumServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("stadiumId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入場地編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				//路徑要修
				RequestDispatcher failureView = req.getRequestDispatcher("/stdm/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer stadiumId = null;
			try {
				stadiumId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("場地編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				//路徑要修
				RequestDispatcher failureView = req.getRequestDispatcher("/stdm/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			StadiumService stdmSvc = new StadiumService();
			StadiumVO stdmVO = stdmSvc.getOneStdm(stadiumId);
			if (stdmVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				//路徑要修
				RequestDispatcher failureView = req.getRequestDispatcher("/stdm/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("stadiumVO", stdmVO); // 資料庫取出的stdmVO物件,存入req
			//路徑要修
			String url = "/stdm/listOneStdm.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneStdm.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllStdm.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer stadiumId = Integer.valueOf(req.getParameter("stadiumId"));

			/*************************** 2.開始查詢資料 ****************************************/
			StadiumService stdmSvc = new StadiumService();
			StadiumVO stdmVO = stdmSvc.getOneStdm(stadiumId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("stadiumVO", stdmVO); // 資料庫取出的StdmVO物件,存入req
			//路徑要修
			String url = "/stdm/update_stdm_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_stdm_input.jsp
			successView.forward(req, res);
		}

		byte[] stdmPic = null;
		Boolean oprSta = true;
		if ("update".equals(action)) { // 來自update_stdm_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer stdmId = Integer.valueOf(req.getParameter("stdmId").trim());

			String stdmName = req.getParameter("stdmName");
			String stdmNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (stdmName == null || stdmName.trim().length() == 0) {
				errorMsgs.add("場地名稱: 請勿空白");
			} else if (!stdmName.trim().matches(stdmNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("場地名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String stdmAddr = req.getParameter("stdmAddr").trim();
			if (stdmAddr == null || stdmAddr.trim().length() == 0) {
				errorMsgs.add("地址請勿空白");
			}
			
			String stdmIntro = req.getParameter("stdmIntro").trim();
			if (stdmIntro == null || stdmIntro.trim().length() == 0) {
				errorMsgs.add("簡介請勿空白");
			}

			Integer courtCount = null;
			try {
				courtCount = Integer.valueOf(req.getParameter("courtCount").trim());
			} catch (NumberFormatException e) {
				courtCount = 0;
				errorMsgs.add("球場數量請填數字.");
			}

			Integer courtPrice = null;
			try {
				courtPrice = Integer.valueOf(req.getParameter("courtPrice").trim());
			} catch (NumberFormatException e) {
				courtPrice = 0;
				errorMsgs.add("球場價格請填數字.");
			}
			
			Integer businessHr = null;
			try {
				businessHr = Integer.valueOf(req.getParameter("businessHr").trim());
			} catch (NumberFormatException e) {
				businessHr = 0;
				errorMsgs.add("營業時間請填數字.");
			}
			

//			TIMESTAMP 資料由mySQL自動產生
//			java.sql.Date hiredate = null;
//			try {
//				hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//			} catch (IllegalArgumentException e) {
//				hiredate = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期!");
//			}

			//等到FK建立再註解掉，因為資料是從別張表格抓進來的(或是用下拉式選單避免例外)
			Integer locId = Integer.valueOf(req.getParameter("locId").trim());
			
			Integer admId = Integer.valueOf(req.getParameter("admId").trim());

			StadiumVO stdmVO = new StadiumVO();

			
			stdmVO.setStdmId(stdmId);
			stdmVO.setStdmName(stdmName);
			stdmVO.setStdmAddr(stdmAddr);
			stdmVO.setLocId(locId);
			Double longitude = 121.56672000;
			stdmVO.setLongitude(longitude);    
			Double latitude = 25.03189000;
			stdmVO.setLatitude(latitude);     
			stdmVO.setStdmIntro(stdmIntro);    
			stdmVO.setCourtCount(courtCount);     
			stdmVO.setCourtPrice(courtPrice);     
			stdmVO.setOprSta(oprSta);         
			stdmVO.setStdmPic(stdmPic);        
			stdmVO.setAdmId(admId);              
			stdmVO.setBusinessHr(businessHr);     
//			stadiumVO.setStdmStartTime(stdmStartTime);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("stadiumVO", stdmVO); // 含有輸入格式錯誤的stdmVO物件,也存入req
				//路徑待修
				RequestDispatcher failureView = req.getRequestDispatcher("/stdm/update_stdm_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			StadiumService stdmSvc = new StadiumService();
			StadiumVO stdmVO1 = stdmSvc.getOneStdm(stdmId);
			stdmVO1 = stdmSvc.updateStdm(stdmId, stdmName, stdmAddr, locId, longitude, latitude, stdmIntro, courtCount, courtPrice, oprSta, stdmPic, admId, businessHr);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("stadiumVO", stdmVO1); // 資料庫update成功後,正確的的stdmVO物件,存入req
			//路徑待修
			String url = "/stdm/listOneStdm.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneStdm.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addStdm.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String stdmName = req.getParameter("stdmName");
			String stdmNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (stdmName == null || stdmName.trim().length() == 0) {
				errorMsgs.add("場地名稱: 請勿空白");
			} else if (!stdmName.trim().matches(stdmNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("場地名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String stdmAddr = req.getParameter("stdmAddr").trim();
			if (stdmAddr == null || stdmAddr.trim().length() == 0) {
				errorMsgs.add("地址請勿空白");
			}
			
			String stdmIntro = req.getParameter("stdmIntro").trim();
			if (stdmIntro == null || stdmIntro.trim().length() == 0) {
				errorMsgs.add("簡介請勿空白");
			}

			Integer courtCount = null;
			try {
				courtCount = Integer.valueOf(req.getParameter("courtCount").trim());
			} catch (NumberFormatException e) {
				courtCount = 0;
				errorMsgs.add("球場數量請填數字.");
			}

			Integer courtPrice = null;
			try {
				courtPrice = Integer.valueOf(req.getParameter("courtPrice").trim());
			} catch (NumberFormatException e) {
				courtPrice = 0;
				errorMsgs.add("球場價格請填數字.");
			}
			
			Integer businessHr = null;
			try {
				businessHr = Integer.valueOf(req.getParameter("businessHr").trim());
			} catch (NumberFormatException e) {
				businessHr = 0;
				errorMsgs.add("營業時間請填數字.");
			}
			

//			TIMESTAMP 資料由mySQL自動產生
//			java.sql.Date hiredate = null;
//			try {
//				hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//			} catch (IllegalArgumentException e) {
//				hiredate = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期!");		
//			}
			
			//等到FK建立再註解掉，因為資料是從別張表格抓進來的(或是用下拉式選單避免例外)
			Integer locId = Integer.valueOf(req.getParameter("locId").trim());
			
			Integer admId = Integer.valueOf(req.getParameter("admId").trim());
			
			StadiumVO stdmVO = new StadiumVO();

//			stadiumVO.setStdmId(stdmId);
			stdmVO.setStdmName(stdmName);
			stdmVO.setStdmAddr(stdmAddr);
			stdmVO.setLocId(locId);
			Double longitude = 121.56672000;
			stdmVO.setLongitude(longitude);    
			Double latitude = 25.03189000;
			stdmVO.setLatitude(latitude);     
			stdmVO.setStdmIntro(stdmIntro);    
			stdmVO.setCourtCount(courtCount);     
			stdmVO.setCourtPrice(courtPrice);     
			stdmVO.setOprSta(oprSta);         
			stdmVO.setStdmPic(stdmPic);        
			stdmVO.setAdmId(admId);              
			stdmVO.setBusinessHr(businessHr);     
//			stadiumVO.setStdmStartTime(stdmStartTime);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("stadiumVO", stdmVO); // 含有輸入格式錯誤的stdmVO物件,也存入req
				//路徑待修
				RequestDispatcher failureView = req.getRequestDispatcher("/stdm/addStdm.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			StadiumService stdmSvc = new StadiumService();
			stdmVO = stdmSvc.addStdm(stdmName, stdmAddr, locId, longitude, latitude, stdmIntro, courtCount, courtPrice, oprSta, stdmPic,  admId, businessHr);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			//路徑要修
			String url = "/stdm/listAllStdm.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllStdm.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllStdm.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer stadiumId = Integer.valueOf(req.getParameter("stadiumId"));

			/*************************** 2.開始刪除資料 ***************************************/
			StadiumService stdmSvc = new StadiumService();
			stdmSvc.deleteStdm(stadiumId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			//路徑要修
			String url = "/Stdm/listAllStdm.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
