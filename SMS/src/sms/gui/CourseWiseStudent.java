package sms.gui;
import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;
import sms.dbinfo.DBConnection;

import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import java.sql.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
public class CourseWiseStudent extends JFrame implements ItemListener{

	private JPanel contentPane;
	private JComboBox<String> cmbcourse;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseWiseStudent frame = new CourseWiseStudent();
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
	public CourseWiseStudent() {
		setTitle("CourseWiseStudent");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 964, 674);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 cmbcourse = new JComboBox();
		cmbcourse.setModel(new DefaultComboBoxModel(new String[] {"Courses"}));
		cmbcourse.addItemListener(this); 
		fillCombo();
		
	    
		cmbcourse.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbcourse.setBounds(236, 64, 233, 48);
		
		contentPane.add(cmbcourse);
		
	    scrollPane= new JScrollPane();
		scrollPane.setBounds(82, 183, 748, 380);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(255, 250, 205));
		JTableHeader header=table.getTableHeader();
//		header.setForeground(new Color(200,100,200)); 
		
		header.setBackground(Color.CYAN);
	
	}
	
	public void fillCombo()
	{
		Connection con=DBConnection.openConnection();
		PreparedStatement ps=null;  //compiled query reference it will hold
		ResultSet rs=null;    //resultant dataset reference it will hold
		String selectQuery="select * from course_details";   //*means all columns with with all records
		try {
			
			ps=con.prepareStatement(selectQuery);
	
			rs= ps.executeQuery();   //only for select query 
			while(rs.next()==true)  //it will return true when table contains data ,it will not fetch data , only watch it 
			{
				String courseName=rs.getString("name");  //fetch the value from name column of course details 
				cmbcourse.addItem(courseName);  //add the fetched value in comboBox 
			}
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
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		int state=e.getStateChange();
		if(state==1)  //1 means current state
		{
			String courseName=(String)cmbcourse.getSelectedItem();
			if(courseName.equalsIgnoreCase("Courses"))
			{
				JOptionPane.showMessageDialog(this, "Pls select valid course");
			}
			else {
			Connection con=DBConnection.openConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String select_Query="select * from course_details where name=?";
			try {
				ps=con.prepareStatement(select_Query);
				ps.setString(1, courseName);
				rs=ps.executeQuery();
				rs.next();
			}
				catch(SQLException se)
				{
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
			populateTable();
			 scrollPane.setViewportView(table);
			}
		}
	
	}
	
		
	
	  public void populateTable() { 
			
		  String course_Name=(String)cmbcourse.getSelectedItem();

		  
	  
	  Connection con=DBConnection.openConnection(); 
	  PreparedStatement ps=null;
	  
	  ResultSet rs=null;
	  String sql="select * from student_details where course_name=?"; 
	  try {
	  ps=con.prepareStatement(sql);
	  
	  ps.setString(1,course_Name); 
	  rs=ps.executeQuery(); 
	  TableModel tableModel=DbUtils.resultSetToTableModel(rs); 
	  table.setModel( tableModel);
	  
	  } 
	  catch(SQLException se) 
	  { 
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
