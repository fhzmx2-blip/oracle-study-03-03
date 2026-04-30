package com.sist.user;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.sist.vo.*;
import com.sist.dao.*;
public class SawonMainForm extends JFrame{
    CardLayout card=new CardLayout();
    LoginForm login=new LoginForm();
    SawonControlForm scf=new SawonControlForm();
    public SawonMainForm()
    {
    	setLayout(card);
    	add("SCF",scf);
    	add("LOGIN",login);
    	
    	setSize(800, 600);
    	setVisible(true);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
        {
        	UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        }catch(Exception ex) {}
        new SawonMainForm();
	}

}