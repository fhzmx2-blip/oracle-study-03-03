package com.sist.vo;
/*
 *    NO                                        NOT NULL NUMBER
      EMPNO                                              NUMBER
      WORK_DATE                                          DATE
      CHECK_IN                                           DATE
      CHECK_OUT                                          DATE
      STATUS                                             VARCHAR2(20)
 */
import java.util.*;

import lombok.Data;
@Data
public class AttendVO {
   private int no,empno;
   private Date work_date,check_in,check_out;
   private String status;
}