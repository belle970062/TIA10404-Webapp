package com.stadium.model;

import java.sql.*;
import java.util.*;


public class StadiumJDBCDAO implements StadiumDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/stadium01?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";



	private static final String INSERT_STMT = 
	"INSERT INTO stadium (stdm_name, stdm_addr, loc_id, longitude, latitude, stdm_intro, court_count, court_price, opr_sta, stdm_pic, adm_id, business_hr) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
	"SELECT stdm_id, stdm_name, stdm_addr, loc_id, longitude, latitude, stdm_intro, court_count, court_price, opr_sta, stdm_pic, adm_id, business_hr, stdm_start_time FROM stadium ORDER BY stdm_id";
	private static final String GET_ONE_STMT = 
	"SELECT stdm_id, stdm_name, stdm_addr, loc_id, longitude, latitude, stdm_intro, court_count, court_price, opr_sta, stdm_pic, adm_id, business_hr, stdm_start_time FROM stadium WHERE stdm_id = ?";
	private static final String DELETE = 
	"DELETE FROM stadium WHERE stdm_id = ?";
	private static final String UPDATE = 
	"UPDATE stadium SET stdm_id=?, stdm_name=?, stdm_addr=?, loc_id=?, longitude=?, latitude=?, stdm_intro=?, court_count=?, court_price=?, opr_sta=?, stdm_pic=?, adm_id=?, business_hr=? WHERE stdm_id = ?";
	
	@Override
	public void insert(StadiumVO stadiumVO) {
	
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
	
			pstmt.setString(1, stadiumVO.getStdmName());       
			pstmt.setString(2, stadiumVO.getStdmAddr());      
			pstmt.setInt(3, stadiumVO.getLocId());             
			pstmt.setDouble(4, stadiumVO.getLongitude());     
			pstmt.setDouble(5, stadiumVO.getLatitude());       
			pstmt.setString(6, stadiumVO.getStdmIntro());      
			pstmt.setInt(7, stadiumVO.getCourtCount());        
			pstmt.setInt(8, stadiumVO.getCourtPrice());        
			pstmt.setBoolean(9, stadiumVO.getOprSta());        
			pstmt.setBytes(10, stadiumVO.getStdmPic());        
			pstmt.setInt(11, stadiumVO.getAdmId());            
			pstmt.setInt(12, stadiumVO.getBusinessHr());       
//			pstmt.setTimestamp(13, stadiumVO.getStdmStartTime());  
	
			pstmt.executeUpdate();
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	
	}
	
	@Override
	public void update(StadiumVO stadiumVO) {
	
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
	
			pstmt.setInt(1, stadiumVO.getStdmId());
			pstmt.setString(2, stadiumVO.getStdmName());       
			pstmt.setString(3, stadiumVO.getStdmAddr());      
			pstmt.setInt(4, stadiumVO.getLocId());             
			pstmt.setDouble(5, stadiumVO.getLongitude());     
			pstmt.setDouble(6, stadiumVO.getLatitude());       
			pstmt.setString(7, stadiumVO.getStdmIntro());      
			pstmt.setInt(8, stadiumVO.getCourtCount());        
			pstmt.setInt(9, stadiumVO.getCourtPrice());        
			pstmt.setBoolean(10, stadiumVO.getOprSta());        
			pstmt.setBytes(11, stadiumVO.getStdmPic());        
			pstmt.setInt(12, stadiumVO.getAdmId());            
			pstmt.setInt(13, stadiumVO.getBusinessHr());       
			pstmt.setTimestamp(13, stadiumVO.getStdmStartTime());  
	
			pstmt.executeUpdate();
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	
	}
	
	@Override
	public void delete(Integer stdmId) {
	
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
	
			pstmt.setInt(1, stdmId);
	
			pstmt.executeUpdate();
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	
	}
	
	@Override
	public StadiumVO findByPrimaryKey(Integer stdmId) {
	
		StadiumVO stadiumVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
	
			pstmt.setInt(1, stdmId);
	
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// stadiumVO 也稱為 Domain objects
				stadiumVO = new StadiumVO();
				stadiumVO.setStdmId(rs.getInt("stdm_id"));             
				stadiumVO.setStdmName(rs.getString("stdm_name"));      
				stadiumVO.setStdmAddr(rs.getString("stdm_addr"));      
				stadiumVO.setLocId(rs.getInt("loc_id"));              
				stadiumVO.setLongitude(rs.getDouble("longitude"));    
				stadiumVO.setLatitude(rs.getDouble("latitude"));     
				stadiumVO.setStdmIntro(rs.getString("stdm_intro"));    
				stadiumVO.setCourtCount(rs.getInt("court_count"));     
				stadiumVO.setCourtPrice(rs.getInt("court_price"));     
				stadiumVO.setOprSta(rs.getBoolean("opr_sta"));         
				stadiumVO.setStdmPic(rs.getBytes("stdm_pic"));        
				stadiumVO.setAdmId(rs.getInt("adm_id"));              
				stadiumVO.setBusinessHr(rs.getInt("business_hr"));     
				stadiumVO.setStdmStartTime(rs.getTimestamp("stdm_start_time"));  
	
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return stadiumVO;
	}
	
	@Override
	public List<StadiumVO> getAll() {
		List<StadiumVO> list = new ArrayList<StadiumVO>();
		StadiumVO stadiumVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// stadiumVO 也稱為 Domain objects
				stadiumVO = new StadiumVO();
				stadiumVO.setStdmId(rs.getInt("stdm_id"));             
				stadiumVO.setStdmName(rs.getString("stdm_name"));      
				stadiumVO.setStdmAddr(rs.getString("stdm_addr"));      
				stadiumVO.setLocId(rs.getInt("loc_id"));              
				stadiumVO.setLongitude(rs.getDouble("longitude"));    
				stadiumVO.setLatitude(rs.getDouble("latitude"));     
				stadiumVO.setStdmIntro(rs.getString("stdm_intro"));    
				stadiumVO.setCourtCount(rs.getInt("court_count"));     
				stadiumVO.setCourtPrice(rs.getInt("court_price"));     
				stadiumVO.setOprSta(rs.getBoolean("opr_sta"));         
				stadiumVO.setStdmPic(rs.getBytes("stdm_pic"));        
				stadiumVO.setAdmId(rs.getInt("adm_id"));              
				stadiumVO.setBusinessHr(rs.getInt("business_hr"));     
				stadiumVO.setStdmStartTime(rs.getTimestamp("stdm_start_time"));  
				list.add(stadiumVO); // Store the row in the list
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
	
		StadiumJDBCDAO dao = new StadiumJDBCDAO();
	
	// 新增
		StadiumVO stadiumVO1 = new StadiumVO();
		stadiumVO1.setStdmName("飆汗羽球_信義總部");
		stadiumVO1.setStdmAddr("110台北市信義區松勤街100號");
		stadiumVO1.setLocId(1); 
//		stadiumVO1.setLongitude(121.56672000); 
//		stadiumVO1.setLatitude(25.03189000); 
		stadiumVO1.setStdmIntro("北市最牛羽球館，給你最極致的羽球饗宴");
		stadiumVO1.setCourtCount(10); 
		stadiumVO1.setCourtPrice(600); 
//		stadiumVO1.setOprSta(true); 
//		stadiumVO1.setStdmPic(null); 
		stadiumVO1.setAdmId(101); 
		stadiumVO1.setBusinessHr(8);
//		stadiumVO1.getStdmStartTime();
		dao.insert(stadiumVO1);
	
	
		// 修改
		StadiumVO stadiumVO2 = new StadiumVO();
		stadiumVO2.setStdmId(7001); 
		stadiumVO2.setStdmName("飆汗羽球_大甲格鬥館");
		stadiumVO2.setStdmAddr("437台中市大甲區中山路一段876號");
		stadiumVO2.setLocId(2); 
		stadiumVO2.setLongitude(120.62902000); 
		stadiumVO2.setLatitude(24.34826000); 
		stadiumVO2.setStdmIntro("呼風喚羽大甲格鬥館");
		stadiumVO2.setCourtCount(5); 
		stadiumVO2.setCourtPrice(450); 
		stadiumVO2.setOprSta(true); 
		stadiumVO2.setStdmPic(null); 
		stadiumVO2.setAdmId(102); 
		stadiumVO2.setBusinessHr(1); 
//		stadiumVO2.getStdmStartTime();
		dao.update(stadiumVO2);
	
	
		// 刪除
		dao.delete(7014);
	
		// 查詢單一紀錄
		StadiumVO stadiumVO3 = dao.findByPrimaryKey(1);
		System.out.print(stadiumVO3.getStdmId() + ",");            
		System.out.print(stadiumVO3.getStdmName() + ",");          
		System.out.print(stadiumVO3.getStdmAddr() + ",");          
		System.out.print(stadiumVO3.getLocId() + ",");             
		System.out.print(stadiumVO3.getLongitude() + ",");         
		System.out.print(stadiumVO3.getLatitude() + ",");          
		System.out.print(stadiumVO3.getStdmIntro() + ",");         
		System.out.print(stadiumVO3.getCourtCount() + ",");        
		System.out.print(stadiumVO3.getCourtPrice() + ",");       
		System.out.print(stadiumVO3.getOprSta() + ",");            
		System.out.print(stadiumVO3.getAdmId() + ",");             
		System.out.print(stadiumVO3.getBusinessHr() + ",");        
		System.out.println(stadiumVO3.getStdmStartTime());         
		System.out.println("---------------------");
	
		// 查詢所有紀錄
		List<StadiumVO> list = dao.getAll();
		for (StadiumVO aStadium : list) {
		    System.out.print(aStadium.getStdmId() + ",");            
		    System.out.print(aStadium.getStdmName() + ",");          
		    System.out.print(aStadium.getStdmAddr() + ",");          
		    System.out.print(aStadium.getLocId() + ",");             
		    System.out.print(aStadium.getLongitude() + ",");         
		    System.out.print(aStadium.getLatitude() + ",");          
		    System.out.print(aStadium.getStdmIntro() + ",");         
		    System.out.print(aStadium.getCourtCount() + ",");        
		    System.out.print(aStadium.getCourtPrice() + ",");        
		    System.out.print(aStadium.getOprSta() + ",");            
		    System.out.print(aStadium.getAdmId() + ",");             
		    System.out.print(aStadium.getBusinessHr() + ",");        
		    System.out.println(aStadium.getStdmStartTime());         
	
		}
	}

}
