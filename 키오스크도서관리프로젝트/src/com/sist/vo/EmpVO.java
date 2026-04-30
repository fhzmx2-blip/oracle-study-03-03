package com.sist.vo;
/*
 *   CRUD : 기본 기능 
 *   VO / DAO => 주력 
 *   ---------
 */
// 부서 => 사원 => JOIN 
/*
 *   EMPNO                                     NOT NULL NUMBER
     ENAME                                     NOT NULL VARCHAR2(50)
     JOB                                       NOT NULL VARCHAR2(50)
     HIREDATE                                  NOT NULL DATE
     SAL                                       NOT NULL NUMBER(10,2)
     DEPTNO                                    NOT NULL NUMBER
 */

import java.util.Date;

import lombok.Data;
// 오라클(JOIN) = 자바(포함 클래스)
// MyBatis / JPA 
@Data
public class EmpVO {
  private int empno,sal,detpno;
  private String ename,job,dbday,isadmin;
  private Date hiredate;
  private DeptVO dvo=new DeptVO();
}