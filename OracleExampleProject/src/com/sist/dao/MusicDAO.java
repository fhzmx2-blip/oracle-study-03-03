package com.sist.dao;

import java.util.*;

import com.sist.vo.MovieVO;
import com.sist.vo.MusicVO;

import java.sql.*;

public class MusicDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static MusicDAO dao;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	public MusicDAO() {
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
	
	public List<MusicVO> musicListData(int page){
		List<MusicVO> list = new ArrayList<MusicVO>();
		try {
			getConnection();
			String sql="SELECT no, title, singer, album FROM genie_music ORDER BY no OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
			ps=conn.prepareStatement(sql);
			int start=(page*20)-20;
			ps.setInt(1, start);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				MusicVO vo = new MusicVO();
				
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSinger(rs.getString(3));
				vo.setAlbum(rs.getString(4));
				
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
	
	public int musicTotalPage() {
		int total=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT (*)/20.0) FROM genie_music";
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

	
	public MusicVO musicDetailData(int mno) {
		MusicVO vo=new MusicVO();
		try {
			getConnection();
			String sql="SELECT no, title, singer, album, state, idcrement FROM genie_music WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, mno);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setTitle(rs.getString(2));
		    vo.setSinger(rs.getString(3));
		    vo.setAlbum(rs.getString(4));
		    vo.setState(rs.getString(5));
		    vo.setIdcrement(rs.getInt(6));
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		
		return vo;
	}
	
	public List<MusicVO> musicFindData(String col, String fd){
		List<MusicVO> list = new ArrayList<MusicVO>();
		try {
			getConnection();
			String sql="SELECT no, title, singer, album FROM genie_music WHERE "+col+" LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1,fd);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				MusicVO vo = new MusicVO();
				
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSinger(rs.getString(3));
				vo.setAlbum(rs.getString(4));
								
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
