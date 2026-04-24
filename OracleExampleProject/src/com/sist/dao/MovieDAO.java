package com.sist.dao;

import java.sql.*;
import java.util.*;

import com.sist.vo.MovieVO;

public class MovieDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static MovieDAO dao;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";

	public MovieDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception ex) {
		}
	}

	public void disConnection() {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
		}
	}
	
	public List<MovieVO> movieListData(int page){
		List<MovieVO> list = new ArrayList<MovieVO>();
		try {
			getConnection();
			String sql="SELECT mno, title, genre, actor, regdate FROM movie ORDER BY mno OFFSET ? ROWS FETCH NEXT 20 ROWs ONLY";
			ps=conn.prepareStatement(sql);
			int start=(page*20)-20;
			ps.setInt(1, start);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				MovieVO vo = new MovieVO();
				
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setGenre(rs.getString(3));
				vo.setActor(rs.getString(4));
				vo.setRegdate(rs.getString(5));
				
				list.add(vo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			disConnection();
		}
		return list;
	}
	
	public int movieTotalPage() {
		int total=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT (*)/20.0) FROM movie";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return total;
	}
	
	public MovieVO movieDetailData(int mno) {
		MovieVO vo=new MovieVO();
		try {
			getConnection();
			String sql="SELECT mno, title, actor, genre, grade, regdate, director FROM movie WHERE mno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, mno);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setMno(rs.getInt(1));
			vo.setTitle(rs.getString(2));
		    vo.setActor(rs.getString(3));
		    vo.setGenre(rs.getString(4));
		    vo.setGrade(rs.getString(5));
		    vo.setRegdate(rs.getString(6));
		    vo.setDirector(rs.getString(7));
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		
		return vo;
	}
	
	public List<MovieVO> movieFindData(String col, String fd){
		List<MovieVO> list = new ArrayList<MovieVO>();
		try {
			getConnection();
			String sql="SELECT mno, title, actor, regdate, genre FROM movie WHERE "+col+" LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1,fd);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				MovieVO vo = new MovieVO();
				
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setGenre(rs.getString(3));
				vo.setActor(rs.getString(4));
				vo.setRegdate(rs.getString(5));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			disConnection();
		}
		return list;
	}
}
