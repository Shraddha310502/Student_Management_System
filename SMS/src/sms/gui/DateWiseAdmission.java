package sms.gui;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;
import sms.dbinfo.DBConnection;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;
public class DateWiseAdmission extends JFrame implements ActionListener,KeyListener{

	private JPanel contentPane;
	private JScrollPane scrollPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DateWiseAdmission frame = new DateWiseAdmission();
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
	
	private JDateChooser dc;  //global instance variable
	private JTable table;

	public DateWiseAdmission() {
		setTitle("DatewiseAdmission");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 912, 678);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    dc = new JDateChooser();
		dc.setDateFormatString("yyyy-MM-dd");
		dc.setBounds(180, 73, 307, 41);
		contentPane.add(dc);
		
		JButton btngo = new JButton("Go");
		btngo.setFont(new Font("Tahoma", Font.BOLD, 17));
		btngo.setBounds(550, 73, 85, 41);
		btngo.addActionListener(this);
		btngo.addKeyListener(this);
		contentPane.add(btngo);
		
		 scrollPane = new JScrollPane();
		scrollPane.setBounds(73, 175, 753, 407);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(255, 250, 205));
		JTableHeader header=table.getTableHeader();
//		header.setForeground(new Color(200,100,200)); 
		
		header.setBackground(Color.CYAN);
		
		
		scrollPane.setViewportView(table);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		populateTable();
		
	}
	
	public void populateTable() {
		
		java.util.Date d=dc.getDate();  //to fetch the date from JDateChooser
		
		if(d==null) {
			JOptionPane.showMessageDialog(this, "Please Select the Date");
		}
		else {
		System.out.println("Util Date is "+d);
		long dt=d.getTime();
		java.sql.Date sqlDate=new java.sql.Date(dt);
//		System.out.println("SQL Date is "+sqlDate);
		
		Connection con=DBConnection.openConnection();
		PreparedStatement ps=null;
		
		ResultSet rs=null;
		String sql="select * from student_details where date=?";
		try {
			ps=con.prepareStatement(sql);
             
			 ps.setDate(1,sqlDate);
			 rs=ps.executeQuery();
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
