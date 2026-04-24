package com.sist.main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import java.util.List;
import com.sist.dao.*;
import com.sist.vo.*;

public class MusicFind extends JFrame implements ActionListener {
	JLabel titleLa;
	JTable table;
	DefaultTableModel model;
	TableColumn column;
	JComboBox box;
	JTextField tf;
	JButton b;

	MusicDAO dao = new MusicDAO();

	public MusicFind() {

		titleLa = new JLabel("음악 목록", JLabel.CENTER);// <table>
		titleLa.setFont(new Font("맑은 고딕", Font.BOLD, 30)); // <h3></h3>

		box = new JComboBox();
		box.addItem("제목");
		box.addItem("아티스트");
		box.addItem("앨범");

		tf = new JTextField(20);
		b = new JButton("검색");

		String[] col = { "번호", "제목", "아티스트", "앨범"};// <tr><th></th>....</tr>
		String[][] row = new String[0][5];
		// 한줄에 5개 데이터를 첨부
		model = new DefaultTableModel(row, col) // 데이터 관리
		{

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
			// 익명의 클래스 => 포함 클래스 => 상속없이 오버라이딩 => 클릭 => 편집기 => 편집방지

		};
		table = new JTable(model); // 테이블 모양 관리
		JScrollPane js = new JScrollPane(table);
		for (int i = 0; i < col.length; i++) {
			column = table.getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(50);
			} else if (i == 1) {
				column.setPreferredWidth(350);
			} else if (i == 2) {
				column.setPreferredWidth(100);
			} else if (i == 3) {
				column.setPreferredWidth(150);
			} else if (i == 4) {
				column.setPreferredWidth(50);
			}
		}
		table.getTableHeader().setReorderingAllowed(false);
		table.setShowVerticalLines(false);
		table.setRowHeight(30);
		table.getTableHeader().setBackground(Color.pink);

		// 배치
		setLayout(null);
		titleLa.setBounds(10, 15, 820, 50);
		add(titleLa);

		js.setBounds(10, 110, 800, 450);
		add(js);

		JPanel p = new JPanel();
		p.add(box);
		p.add(tf);
		p.add(b);

		p.setBounds(10, 70, 350, 35);
		add(p);

		setSize(850, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		b.addActionListener(this);
		tf.addActionListener(this);
	}

	public void print(String col, String fd) {
		for (int i = model.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);
		}

		List<MusicVO> list = dao.musicFindData(col, fd);
		for (MusicVO vo : list) {
			String[] data = { String.valueOf(vo.getNo()), vo.getTitle(), vo.getSinger(), vo.getAlbum() };
			model.addRow(data);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MusicFind();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == b || e.getSource() == tf) {
			String fd = tf.getText();
			// 입력값 읽기
			if (fd.trim().length() < 1) {
				// 입력이 안됨
				tf.requestFocus();
				return;
			}

			String[] column = { "title", "singer", "album" };
			int index = box.getSelectedIndex();
			System.out.println(fd);
			System.out.println(column[index]);
			print(column[index], fd);
		}
	}

}