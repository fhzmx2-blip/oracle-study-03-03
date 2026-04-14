package com.sist.dao;
// 사용자 요청 => 오라클 연결후 처리 
/*
 *   데이터베이스는 읽어 오는 단위 ROW
 *   
 */
import java.sql.*;
import java.util.*;

import com.sist.vo.FoodVO;

//  DAO = Service = 브라우저 
public class FoodDAO {
    // 오라클 => 필요한 객체 
	// 연결 객체 
	private Connection conn;
	// 송수신 : SQL전송 => 데이터 받기
	private PreparedStatement ps;
	// 한사람당 1개의 Connection 사용 
	private static FoodDAO dao; // 싱글턴 
	// 오라클 주소 
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	// thin => 드라이버 : 연결만 해주는 드라이버 (무료)
	// 드라이버 설정 => 한번만 설정 
	public FoodDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	// 싱글턴 => 100% 
	public static FoodDAO newInstance()
	{
		if(dao==null)
			dao=new FoodDAO();
		return dao;
	}
	
    // 오라클 연결 
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
			// conn hr/happy => 오라클로 명령 전송 
		}catch(Exception ex) {}
	}
	// 오라클 닫기 
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			// 오라클 => exit
		}catch(Exception ex) {}
	}
	
	//------------------------------- 공통 
	/*
	 *    foodInsert(...)
	 *   public void foodInsert(
	 *    int no,
          String name,
          String type,
          String phone,
          String address,
          String parking,
          String poster,
          String time,
          String content,
          String theme,
          String price,
          double score
	 *   
	 *   )
	 *   
	 *   NO      NOT NULL NUMBER         
	NAME             VARCHAR2(100)  
	TYPE             VARCHAR2(100)  
	PHONE            VARCHAR2(30)   
	ADDRESS          VARCHAR2(260)  
	SCORE            NUMBER(2,1)    
	PARKING          VARCHAR2(200)  
	POSTER           VARCHAR2(260)  
	TIME             VARCHAR2(50)   
	CONTENT          CLOB           
	THEME            VARCHAR2(4000) 
	PRICE            VARCHAR2(100) 
	 */
	public void foodInsert(FoodVO vo)
	{
		try
		{
			// 1. 연결 
			getConnection();
			// 2. SQL문장 
			String sql="INSERT INTO food VALUES("
					  +"?,?,?,?,?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);// sql문장을 오라클 전송
			// 실행전에 ?에 값을 채운다 
			ps.setInt(1, vo.getNo());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getType());
			ps.setString(4, vo.getPhone());
			ps.setString(5, vo.getAddress());
			ps.setDouble(6, vo.getScore());
			ps.setString(7, vo.getParking());
			ps.setString(8, vo.getPoster());
			ps.setString(9, vo.getTime());
			ps.setString(10, vo.getContent());
			ps.setString(11, vo.getTheme());
			ps.setString(12, vo.getPrice());
			
			// 실행 
			ps.executeUpdate(); // commit 
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
	
	
	
}




