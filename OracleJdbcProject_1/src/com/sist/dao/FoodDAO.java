package com.sist.dao;

import java.sql.*;
import java.util.*;

import com.sist.vo.FoodVO;

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static FoodDAO dao;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";

	public FoodDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static FoodDAO newInstance() {
		if (dao == null)
			dao = new FoodDAO();
		return dao;
	}

	// 오라클 연결
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
			// conn hr/happy => 오라클로 명령 전송
		} catch (Exception ex) {
		}
	}

	// 오라클 닫기
	public void disConnection() {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
		}
	}

	// ------------------------------- 공통
	/*
	 * foodInsert(...) public void foodInsert( int no, String name, String type,
	 * String phone, String address, String parking, String poster, String time,
	 * String content, String theme, String price, double score
	 * 
	 * )
	 * 
	 * NO NOT NULL NUMBER NAME VARCHAR2(100) TYPE VARCHAR2(100) PHONE VARCHAR2(30)
	 * ADDRESS VARCHAR2(260) SCORE NUMBER(2,1) PARKING VARCHAR2(200) POSTER
	 * VARCHAR2(260) TIME VARCHAR2(50) CONTENT CLOB THEME VARCHAR2(4000) PRICE
	 * VARCHAR2(100)
	 */
	public void foodInsert(FoodVO vo) {
		try {

			getConnection();

			String sql = "INSERT INTO food VALUES(" + "?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);// sql문장을 오라클 전송

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

			ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
	}

	public List<FoodVO> foodFindData(String type) {
		List<FoodVO> list = new ArrayList<FoodVO>();
		try {
			getConnection();
			String sql = "SELECT no,name,type,address,phone FROM food WHERE type LIKE '%" 
						+ type + "%'ORDER BY no ASC";
			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				FoodVO vo = new FoodVO();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setType(rs.getString(3));
				vo.setAddress(rs.getString(4));
				vo.setPhone(rs.getString(5));
				list.add(vo);
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return list;
	}

}
