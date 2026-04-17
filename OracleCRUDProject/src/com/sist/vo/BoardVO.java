package com.sist.vo;

import java.sql.Date;
import java.util.*;

import lombok.Data;
import oracle.sql.*;
@Data
public class BoardVO {
	private int no, hit;
	private String name, subject, content, pwd, dbday;
}
