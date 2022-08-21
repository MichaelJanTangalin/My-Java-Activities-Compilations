package MachineProblems;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class MachineProblem4 {

	MachineProblem4() throws SQLException {

		// Michael Jan R. Tangalin
		// 2BSIT-1

		JFrame frame = new JFrame("Machine Problem 4");

		JMenuBar JMB = new JMenuBar();

		JMenu menu = new JMenu("File");
		JMenuItem item1 = new JMenuItem("Home");
		JMenuItem item2 = new JMenuItem("Insert");
		JMenuItem item3 = new JMenuItem("Update");
		JMenuItem item4 = new JMenuItem("Delete");
		frame.setJMenuBar(JMB);
		JMB.add(menu);

		menu.add(item1);
		menu.add(item2);
		menu.add(item3);
		menu.add(item4);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		JLabel heading = new JLabel("Delete Student Records");
		heading.setBounds(150, 10, 300, 100);

		panel.add(heading);
		heading.setFont(new Font("Segoe Script", Font.BOLD, 24));
		heading.setForeground(Color.BLACK);

		// FRAME SETTINGS
		panel.setBackground(new Color(99, 195, 242));
		frame.add(panel);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(600, 450);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Cursor TXTcursor = new Cursor(Cursor.TEXT_CURSOR);

		// STUDENT FIELDS

		JLabel studentNo = new JLabel("Student No: ");
		panel.add(studentNo);
		studentNo.setBounds(89, 95, 300, 100);
		studentNo.setFont(new Font("Segoe Script", Font.PLAIN, 20));
		studentNo.setForeground(Color.BLACK);

		JTextField studentNoTxt = new JTextField();
		studentNoTxt.setBounds(230, 130, 165, 30);
		panel.add(studentNoTxt);
		studentNoTxt.setFont(new Font("Times New Roman", Font.BOLD, 15));
		studentNoTxt.requestFocusInWindow();
		studentNoTxt.setCursor(TXTcursor);

		JLabel studentName = new JLabel("Name: ");
		panel.add(studentName);
		studentName.setBounds(145, 140, 300, 100);
		studentName.setFont(new Font("Segoe Script", Font.PLAIN, 20));
		studentName.setForeground(Color.BLACK);

		JTextField studentNameTxt = new JTextField();
		studentNameTxt.setBounds(230, 175, 250, 30);
		panel.add(studentNameTxt);
		studentNameTxt.setFont(new Font("Times New Roman", Font.BOLD, 15));
		studentNameTxt.setEditable(false);

		JLabel address = new JLabel("Address: ");
		panel.add(address);
		address.setBounds(124, 185, 300, 100);
		address.setFont(new Font("Segoe Script", Font.PLAIN, 20));
		address.setForeground(Color.BLACK);

		JTextField addressTxt = new JTextField();
		addressTxt.setBounds(230, 220, 250, 30);
		panel.add(addressTxt);
		addressTxt.setFont(new Font("Times New Roman", Font.BOLD, 15));
		addressTxt.setEditable(false);

		// BUTTONS

		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

		JButton Search = new JButton("Search");
		Search.setBounds(400, 130, 80, 30);
		panel.add(Search);
		Search.setBackground(new Color(194, 211, 237));
		Search.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		Search.setForeground(Color.BLACK);
		Search.setCursor(cursor);
		Search.setVisible(true);

		JButton Delete = new JButton("Delete");
		Delete.setBounds(120, 320, 100, 30);
		panel.add(Delete);
		Delete.setBackground(new Color(189, 21, 21));
		Delete.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		Delete.setForeground(Color.WHITE);
		Delete.setCursor(cursor);
		Delete.setVisible(true);

		JButton clear = new JButton("Clear");
		clear.setBounds(250, 320, 100, 30);
		panel.add(clear);
		clear.setBackground(Color.WHITE);
		clear.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		clear.setForeground(Color.BLACK);
		clear.setCursor(cursor);
		clear.setVisible(true);

		JButton Exit = new JButton("Exit");
		Exit.setBounds(380, 320, 100, 30);
		panel.add(Exit);
		Exit.setBackground(new Color(17, 189, 40));
		Exit.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		Exit.setForeground(Color.BLACK);
		Exit.setCursor(cursor);
		Exit.setVisible(true);

		// CONNECTION
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/neu_db", "root", "");

		Search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent search) {
				String studentNum = studentNoTxt.getText();
				String SName = "student_Name";
				String Saddress = "address";

				try {
					// Class.forName("com.mysql.jdbc.Driver");

					String sql = "SELECT * FROM students WHERE student_ID = ?";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setInt(1, Integer.parseInt(studentNum));
					ResultSet rsl = stmt.executeQuery();

					if (!rsl.next()) {
						JOptionPane.showMessageDialog(null, "Student does not exist");
						studentNoTxt.setText(null);
						studentNameTxt.setText(null);
						addressTxt.setText(null);
					} else {
						studentNameTxt.setText(rsl.getString(SName));
						addressTxt.setText(rsl.getString(Saddress));
					}

				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, error.getMessage());
				}
			}

		});

		Delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent insert) {
				String studentNum = studentNoTxt.getText();

				try {
					// Class.forName("com.mysql.jdbc.Driver");

					String sql = "DELETE FROM students WHERE student_ID = ?";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setInt(1, Integer.parseInt(studentNum));
					stmt.executeUpdate();

					JOptionPane.showMessageDialog(null, "Record Successfully Deleted!");
					studentNoTxt.setText(null);
					studentNameTxt.setText(null);
					addressTxt.setText(null);

				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, error.getMessage());
				}

			}

		});

		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent clear) {
				studentNoTxt.setText(null);
				studentNameTxt.setText(null);
				addressTxt.setText(null);
			}

		});

		Exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent exit) {
				System.exit(0);

			}

		});

		item1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent menu) {
				new MachineProblemsCOMP();
				frame.setVisible(false);
			}

		});

		item2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent menu) {
				new MachineProblem3();
				frame.setVisible(false);
			}

		});

		item3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent menu) {
				new MachineProblem5();
				frame.setVisible(false);
			}

		});

	}

}
