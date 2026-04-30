package com.sist.user;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class SawonControlForm extends JPanel{
	JTabbedPane tp=new JTabbedPane();
	public SawonControlForm() {
		setLayout(new BorderLayout());
		tp.addTab("사원관리", new JPanel());
		tp.addTab("사원검색", new JPanel());
		tp.addTab("근태관리", new JPanel());
		tp.addTab("급여관리", new JPanel());
		tp.addTab("부서관리", new JPanel());
		tp.addTab("인사관리", new JPanel());
		add("Center",tp);
	}

}
