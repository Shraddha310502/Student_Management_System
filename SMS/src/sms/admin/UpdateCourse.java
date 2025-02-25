package sms.admin;
import java.sql.*;
import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sms.dbinfo.DBConnection;
import sms.gui.LoginFrame;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;

public class UpdateCourse extends JFrame implements ActionListener,ItemListener {

	private JPanel contentPane;
	private JComboBox<String> cmbcourse;
	private JLabel lblNewLabel_1;
	private JTextField txtfees;
	private JTextField txtduration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCourse frame = new UpdateCourse();
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
	public UpdateCourse() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateCourse.class.getResource("/sms/images/update.png")));
		setTitle("UpdateCourse");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 959, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UPDATE COURSE ");
		lblNewLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 26));
		lblNewLabel.setBounds(298, 48, 337, 62);
		contentPane.add(lblNewLabel);
		
		 cmbcourse = new JComboBox();
		
		cmbcourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmbcourse.setModel(new DefaultComboBoxModel(new String[] {"Select Course"}));
		
		cmbcourse.addItemListener(this);   //register the listener
		
      	 fillCombo();
		cmbcourse.setBounds(316, 160, 221, 37);
		contentPane.add(cmbcourse);
		
		lblNewLabel_1 = new JLabel("Fees");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_1.setBounds(99, 274, 138, 37);
		contentPane.add(lblNewLabel_1);
		
		txtfees = new JTextField();
		txtfees.setFont(new Font("Tahoma", Font.ITALIC, 15));
		txtfees.setForeground(new Color(220, 20, 60));
		txtfees.setBounds(271, 274, 198, 37);
		contentPane.add(txtfees);
		txtfees.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Duration");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_2.setBounds(99, 357, 138, 37);
		contentPane.add(lblNewLabel_2);
		
		txtduration = new JTextField();
		txtduration.setFont(new Font("Tahoma", Font.ITALIC, 15));
		txtduration.setForeground(new Color(220, 20, 60));
		txtduration.setBounds(271, 357, 198, 41);
		contentPane.add(txtduration);
		txtduration.setColumns(10);
		
		JButton btnupdate = new JButton("Update");
		btnupdate.setBackground(new Color(250, 128, 114));
		btnupdate.setForeground(new Color(0, 0, 0));
		btnupdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnupdate.setBounds(197, 479, 152, 47);
		btnupdate.addActionListener(this);
		contentPane.add(btnupdate);
		
		
ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/sms/images/addcourse.jpg"));
		
		//creating imageicon objectwith image
		
		Image i2=ic.getImage().getScaledInstance(945, 613, Image.SCALE_DEFAULT);
		
		ImageIcon ic1=new ImageIcon(i2);
		JLabel lblimage = new JLabel("");
		lblimage.setBounds(0, 0, 945, 613);
		lblimage.setIcon(ic1);
		contentPane.add(lblimage);
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
	public void actionPerformed(ActionEvent e) {
		
		String fees=txtfees.getText();  //to fetch value from name field
		  String duration=txtduration.getText();
		  
		  String course_Name=(String)cmbcourse.getSelectedItem();
		  if(fees.isEmpty()|| duration.isEmpty() || course_Name.equalsIgnoreCase("Select Course"))
		  {
			  JOptionPane.showMessageDialog(this, "Please fill details");
			  
		  }	
		  
		  else {
			  
			  Connection con=DBConnection.openConnection();
			  PreparedStatement ps=null;
			  String updateQuery="update course_details set fees=?,duration=? where name=?";
			  try {
				  
				  ps=con.prepareStatement(updateQuery);
				  ps.setInt(1, Integer.parseInt(fees));
				  ps.setString(2, duration);
				  ps.setString(3, course_Name);
				 int result=ps.executeUpdate();
				 if(result>0) {
					 JOptionPane.showMessageDialog(this, course_Name+" course details updated successfully");
				 }
				  
			  }
			  catch(SQLException se) {
				  se.printStackTrace();
			  }
			  finally {
					try {
						
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
	
	public void itemStateChanged(ItemEvent e) {
		
		//System.out.println("ComboClick");
		int state=e.getStateChange();
		if(state==1)  //1 means current state
		{
			String courseName=(String)cmbcourse.getSelectedItem();
			if(courseName.equalsIgnoreCase("Select Course"))
			{
				JOptionPane.showMessageDialog(this, "Pls select valid course");
			}
			Connection con=DBConnection.openConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String select_Query="select * from course_details where name=?";
			
			try {
				ps=con.prepareStatement(select_Query);
				ps.setString(1, courseName);
				rs=ps.executeQuery();
				rs.next();
				String cfees=rs.getString("fees");  //to fetch the value from fees column
				String cduration=rs.getString(3);   //to fetch the value using column number
				
				txtfees.setText(cfees);
				txtduration.setText(cduration);
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
}
