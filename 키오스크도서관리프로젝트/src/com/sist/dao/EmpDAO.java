package com.sist.dao;
import java.util.*;

import com.sist.vo.EmpVO;

import java.sql.*;
public class EmpDAO {
	  private Connection conn; // Socket => 연결 담당 
	  private PreparedStatement ps; // BufferedReader , OutputStream 
	  // 송(SQL문장) 수신(오라클에서 결과값 받기)
	  private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	  
	  // 1. 드라이버 등록 
	  public EmpDAO()
	  {
		  try
		  {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
	  }
	  // 2. 오라클 연결 
	  public void getConnection()
	  {
		  try
		  {
			  conn=DriverManager.getConnection(URL,"hr",
					         "happy");
			  // conn => SQL PLus
		  }catch(Exception ex) {}
	  }
	  // 3. 오라클 연결 해제 
	  public void disConnection()
	  {
		  try
		  {
			  if(ps!=null) ps.close();
			  if(conn!=null) conn.close();
		  }catch(Exception ex) {}
	  }
	  // 기능 
	  //1. 사원 목록 => 추가 , 수정 , 삭제 
	  public List<EmpVO> empListData(int page)
	  {
		  List<EmpVO> list=
				  new ArrayList<EmpVO>();
		  try
		  {
			  getConnection();
			  String sql="SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD'),"
					    +"emp2.deptno,dname,loc "
					    +"FROM emp2 JOIN dept2 "
					    +"ON emp2.deptno=dept2.deptno "
					    +"ORDER BY hiredate ASC "
					    +"OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
			  // 문장 전송
			  ps=conn.prepareStatement(sql);
			  ps.setInt(1, (page*20)-20);
			  // 실행 후 결과값 
			  ResultSet rs=ps.executeQuery();
			  while(rs.next())
			  {
				  EmpVO vo=new EmpVO();
				  vo.setEmpno(rs.getInt(1));
				  vo.setEname(rs.getString(2));
				  vo.setJob(rs.getString(3));
				  vo.setDbday(rs.getString(4));
				  vo.setDetpno(rs.getInt(5));
				  vo.getDvo().setDname(rs.getString(6));
				  vo.getDvo().setLoc(rs.getString(7));
				  list.add(vo);
			  }
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return list;
	  }
	  public int empTotalPage()
	  {
		  int total=0;
		  try
		  {
			  getConnection();
			  String sql="SELECT CEIL(COUNT(*)/20.0) "
					    +"FROM emp2";
			  ps=conn.prepareStatement(sql);
			  ResultSet rs=ps.executeQuery();
			  rs.next();
			  total=rs.getInt(1);
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return total;
	  }
	  //2. 사원 검색 => 부서별
	  public List<EmpVO> empFindData(String dname)
	  {
		  List<EmpVO> list=
				  new ArrayList<EmpVO>();
		  try
		  {
			  getConnection();
			  String sql="SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD'),"
					    +"emp2.deptno,dname,loc "
					    +"FROM emp2 JOIN dept "
					    +"ON emp2.deptno=dept.deptno "
					    +"AND dept.dname=?";
			  // "AND dept.dname="+dname => 오류 
			  // "AND dept.dname='"+dname+"'"
			  // 문장 전송
			  // => 정수 / 실수 => 대입 
			  // => 문자 / 날짜 => ''가 필요 => ?
			  // 날짜 검색 
			  // => SYSDATE => 값이 틀리다 
			  // => TRUNC(SYSDATE) 소수점 (시간,분,초)
			  ps=conn.prepareStatement(sql);
			  ps.setString(1, dname);
			  // 실행 후 결과값 
			  ResultSet rs=ps.executeQuery();
			  while(rs.next())
			  {
				  EmpVO vo=new EmpVO();
				  vo.setEmpno(rs.getInt(1));
				  vo.setEname(rs.getString(2));
				  vo.setJob(rs.getString(3));
				  vo.setDbday(rs.getString(4));
				  vo.setDetpno(rs.getInt(5));
				  vo.getDvo().setDname(rs.getString(6));
				  vo.getDvo().setLoc(rs.getString(7));
				  list.add(vo);
			  }
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return list;
	  }
	  public List<String> empGetDeptno()
	  {
		  List<String> list=new ArrayList<String>();
		  try
		  {
			  getConnection();
			  String sql="SELECT DISTINCT dname "
					    +"FROM dept "
					    +"ORDER BY deptno ASC";
			  ps=conn.prepareStatement(sql);
			  ResultSet rs=ps.executeQuery();
			  while(rs.next())
			  {
				  list.add(rs.getString(1));
			  }
			  rs.close();
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		  finally
		  {
			  disConnection();
		  }
		  return list;
	  }
	  // 로그인 
	  
	  //3. 부서 이동 => Combo
	  //4. 출퇴근 관리 => 출근 / 퇴근 => 통계 
	  //5. 급여 
	  //6. => 대출 / 도서 검색 / 도서 수정 , 추가 , 삭제 ...
	  //7. => 구매 / 상품 검색 / 상품 수정 / 통계 => 회원 등급 결정
	  
}