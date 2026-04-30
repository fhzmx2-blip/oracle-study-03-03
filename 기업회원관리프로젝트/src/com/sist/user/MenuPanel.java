package com.sist.user;
// 메뉴 제작
import java.awt.*;
import javax.swing.*;
public class MenuPanel extends JPanel{
   JButton b1,b2,b3,b4,b5,b6,b7;
   public MenuPanel()
   {
	   b1=new JButton("홈");
	   b2=new JButton("로그인");
	   b7=new JButton("로그아웃");
	   b3=new JButton("회원가입");
	   b4=new JButton("마이페이지");
	   b5=new JButton("관리자페이지");
	   b6=new JButton("종료");
	   setLayout(new GridLayout(1,7,5,5));
	    
	    
	    
	    
	   
	   
   }
   public void init()
   {
	   removeAll();
	   add(b1);
	   if(UserMainFrame.bLogin==true)
	   {
		    add(b7);
		    if(UserMainFrame.isAdmin=='n')
		    {
		    	add(b4);
		    }
		    else
		    {
		    	add(b5);
		    }
	   }
	   else
	   {
		   add(b2);
		   add(b3);
	   
	   }  
	   add(b6);
	   
   }
}