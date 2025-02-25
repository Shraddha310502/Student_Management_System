package sms.gui;
import java.sql.*;
import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;
import sms.dbinfo.DBConnection;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.*;
import javax.swing.JButton;
public class AllCourses extends JFrame implements ActionListener,KeyListener{

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllCourses frame = new AllCourses();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AllCourses() {
		setTitle("All courses");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 930, 715);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(250, 128, 114));
		panel.setBounds(74, 45, 765, 84);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Available Courses");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
		lblNewLabel.setBounds(236, 21, 447, 40);
		panel.add(lblNewLabel);
		 scrollPane = new JScrollPane();
			scrollPane.setBounds(74, 149, 765, 200);
			contentPane.add(scrollPane);
		
		
		
		//Table heading colors
		
		table = new JTable();
		table.setBackground(new Color(255, 250, 205));
		table.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		JTableHeader header=table.getTableHeader();
//		header.setForeground(new Color(200,100,200)); 
		header.setFont(new Font("Aerial",Font.BOLD,20));
		header.setBackground(Color.CYAN);
		
		
		
		JButton btncourse = new JButton("View Courses");
		btncourse.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btncourse.setBackground(new Color(240, 128, 128));
		btncourse.setBounds(296, 450, 245, 65);
		btncourse.addActionListener(this);
		btncourse.addKeyListener(this);
		contentPane.add(btncourse);
	}
	
	public void populateTable() {
		
		Connection con=DBConnection.openConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String selectQuery="select * from course_details";
		
		try {
			
			ps=con.prepareStatement(selectQuery);
			rs= ps.executeQuery();  
			TableModel tableModel=DbUtils.resultSetToTableModel(rs);
			table.setModel( tableModel);
			
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		populateTable();
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		// TODO Auto-generated method stub
		
	     	int keyCode=e.getKeyCode();
	    	if(keyCode==10) {
			populateTable();
			scrollPane.setViewportView(table);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
