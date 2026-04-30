package com.sist.user;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;
import com.sist.dao.*;
import com.sist.vo.*;
public class SawonList extends JPanel
implements ActionListener
{
   JTable table;
   DefaultTableModel model;
   JButton b1,b2,b3;
   JLabel la=new JLabel("0 page / 0 pages");
   JButton b4,b5;
   int curpage=1;
   int totalpage=0;
   EmpDAO dao=new EmpDAO();
   public SawonList()
   {
	   b1=new JButton("사원 추가");
	   b2=new JButton("사원 수정");
	   b3=new JButton("사원 삭제");
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
	   JPanel p=new JPanel();
	   p.add(b1);p.add(b2);p.add(b3);
	   add("North",p);
	   add("Center",js);
	   
	   b4=new JButton("이전");
	   b5=new JButton("다음");
	   JPanel pp=new JPanel();
	   pp.add(b4);pp.add(la);pp.add(b5);
	   add("South",pp);
	   print();
	   
	   b4.addActionListener(this);
	   b5.addActionListener(this);
	   
   }
   // 현재 페이지 / 총페이지 => 변경 => 메모리 유지 
   public void print()
   {
	   for(int i=model.getRowCount()-1;i>=0;i--)
	   {
		   model.removeRow(i);
	   }
	   
	   List<EmpVO> list=dao.empListData(curpage);
	   totalpage=dao.empTotalPage();
	   // 목록 
	   for(EmpVO vo:list)
	   {
		   String[] data={
			  String.valueOf(vo.getEmpno()),
			  vo.getEname(),
			  vo.getJob(),
			  vo.getDbday(),
			  vo.getDvo().getDname(),
			  vo.getDvo().getLoc()
		   };
		   model.addRow(data);
	   }
	   // 페이지
	   la.setText(curpage+" page / "+totalpage+" pages");
	   
   }
   @Override
   public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	  if(e.getSource()==b4)
	  {
		  if(curpage>1)
		  {
			  curpage--;
			  print();
		  }
	  }
	  else if(e.getSource()==b5)
	  {
		  if(curpage<totalpage)
		  {
			  curpage++;
			  print();
		  }
	  }
   }
}