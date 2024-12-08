package com.stadium.model;

import java.sql.Timestamp;

import oracle.sql.TIMESTAMP;

//stdmName&Addr 本來是char先改為string
public class StadiumVO {
	private Integer stdmId;
	private String stdmName;
	private String stdmAddr;
	private Integer locId;
	private Double longitude;
	private Double latitude;
	private String stdmIntro;
	private Integer courtCount;
	private Integer courtPrice;
	private Boolean oprSta;
	private byte[] stdmPic;
	private Integer admId;
	private Integer businessHr;
	private Timestamp stdmStartTime;
	public Integer getStdmId() {
		return stdmId;
	}
	public void setStdmId(Integer stdmId) {
		this.stdmId = stdmId;
	}
	public String getStdmName() {
		return stdmName;
	}
	public void setStdmName(String stdmName) {
		this.stdmName = stdmName;
	}
	public String getStdmAddr() {
		return stdmAddr;
	}
	public void setStdmAddr(String stdmAddr) {
		this.stdmAddr = stdmAddr;
	}
	public Integer getLocId() {
		return locId;
	}
	public void setLocId(Integer locId) {
		this.locId = locId;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getStdmIntro() {
		return stdmIntro;
	}
	public void setStdmIntro(String stdmIntro) {
		this.stdmIntro = stdmIntro;
	}
	public Integer getCourtCount() {
		return courtCount;
	}
	public void setCourtCount(Integer courtCount) {
		this.courtCount = courtCount;
	}
	public Integer getCourtPrice() {
		return courtPrice;
	}
	public void setCourtPrice(Integer courtPrice) {
		this.courtPrice = courtPrice;
	}
	public Boolean getOprSta() {
		return oprSta;
	}
	public void setOprSta(Boolean oprSta) {
		this.oprSta = oprSta;
	}
	public byte[] getStdmPic() {
		return stdmPic;
	}
	public void setStdmPic(byte[] stdmPic) {
		this.stdmPic = stdmPic;
	}
	public Integer getAdmId() {
		return admId;
	}
	public void setAdmId(Integer admId) {
		this.admId = admId;
	}
	public Integer getBusinessHr() {
		return businessHr;
	}
	public void setBusinessHr(Integer businessHr) {
		this.businessHr = businessHr;
	}
	public Timestamp getStdmStartTime() {
		return stdmStartTime;
	}
	public void setStdmStartTime(Timestamp stdmStartTime) {
		this.stdmStartTime = stdmStartTime;
	}
	
	
	
}

	