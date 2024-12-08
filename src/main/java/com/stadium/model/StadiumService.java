package com.stadium.model;

import java.sql.Timestamp;
import java.util.List;

public class StadiumService {
	private StadiumDAO_interface dao;

	public StadiumService() {
		dao = new StadiumJDBCDAO();
	}

	public StadiumVO addStdm(String stdmName, String stdmAddr, Integer locId, 
			Double longitude, Double latitude, String stdmIntro, Integer courtCount, Integer courtPrice,
			Boolean oprSta, byte[] stdmPic, Integer admId, Integer businessHr
			) {

		StadiumVO stdmVO = new StadiumVO();

//		stadiumVO.setStdmId(stdmId);
		stdmVO.setStdmName(stdmName);
		stdmVO.setStdmAddr(stdmAddr);
		stdmVO.setLocId(locId);
		stdmVO.setLongitude(longitude);    
		stdmVO.setLatitude(latitude);     
		stdmVO.setStdmIntro(stdmIntro);    
		stdmVO.setCourtCount(courtCount);     
		stdmVO.setCourtPrice(courtPrice);     
		stdmVO.setOprSta(oprSta);         
		stdmVO.setStdmPic(stdmPic);        
		stdmVO.setAdmId(admId);              
		stdmVO.setBusinessHr(businessHr);     
//		stadiumVO.setStdmStartTime(stdmStartTime);
		dao.insert(stdmVO);

		return stdmVO;
	}

//	public StadiumVO updateStdm(Integer stdmId, String stdmName, String stdmAddr, Integer locId, 
//			Double longitude, Double latitude, String stdmIntro, Integer courtCount, Integer courtPrice,
//			Boolean oprSta, byte[] stdmPic, Integer admId, Integer businessHr, 
//			Timestamp stdmStartTime) 
	
	public StadiumVO updateStdm(Integer stdmId, String stdmName, String stdmAddr, Integer locId, 
			Double longitude, Double latitude, String stdmIntro, Integer courtCount, Integer courtPrice,
			Boolean oprSta, byte[] stdmPic, Integer admId, Integer businessHr){

		StadiumVO stdmVO = new StadiumVO();

		stdmVO.setStdmId(stdmId);
		stdmVO.setStdmName(stdmName);
		stdmVO.setStdmAddr(stdmAddr);
		stdmVO.setLocId(locId);
		stdmVO.setLongitude(longitude);    
		stdmVO.setLatitude(latitude);     
		stdmVO.setStdmIntro(stdmIntro);    
		stdmVO.setCourtCount(courtCount);     
		stdmVO.setCourtPrice(courtPrice);     
		stdmVO.setOprSta(oprSta);         
		stdmVO.setStdmPic(stdmPic);        
		stdmVO.setAdmId(admId);              
		stdmVO.setBusinessHr(businessHr);     
//		stdmVO.setStdmStartTime(stdmStartTime);
		dao.update(stdmVO);

		return stdmVO;
	}

	public void deleteStdm(Integer stdmId) {
		dao.delete(stdmId);
	}

	public StadiumVO getOneStdm(Integer stdmId) {
		return dao.findByPrimaryKey(stdmId);
	}

	public List<StadiumVO> getAll() {
		return dao.getAll();
	}




}
