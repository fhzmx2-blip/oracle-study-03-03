package com.sist.user;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;
import com.sist.dao.*;
import com.sist.vo.*;
public class SawonFind extends JPanel
implements ActionListener
{
   JTable table;
   DefaultTableModel model;
   
   EmpDAO dao=new EmpDAO();
   public SawonFind()
   {
	  
	   String[] col={"사번","이름","직위",
			   "입사일","부서명","근무지"};
	   String[][] row=new String[0][6];
	   model=new DefaultTableModel(row,col)
	   {

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return column==2 || column==4 || column==5;
			}
		   
	   };
	   table=new JTable(model);
	   JScrollPane js=new JScrollPane(table);
	   setLayout(new BorderLayout());
	   
	   add("Center",js);
	   
	   
	   print();
	   
	   
   }
   // 현재 페이지 / 총페이지 => 변경 => 메모리 유지 
   public void print()
   {
	   for(int i=model.getRowCount()-1;i>=0;i--)
	   {
		   model.removeRow(i);
	   }
	   
	   
	   // 목록 
//	   for(EmpVO vo:list)
//	   {
//		   String[] data={
//			  String.valueOf(vo.getEmpno()),
//			  vo.getEname(),
//			  vo.getJob(),
//			  vo.getDbday(),
//			  vo.getDvo().getDname(),
//			  vo.getDvo().getLoc()
//		   };
//		   model.addRow(data);
//	   }
	   // 페이지
	
	   
   }
   @Override
   public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	  
   }
}
