package sms.counsellor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import sms.dbinfo.DBConnection;
import sms.gui.LoginFrame;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
public class UpdateStudent extends JFrame implements ActionListener,ItemListener
{

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtemail;
	private JTextArea txtaddress;
	private JComboBox<String> cmbs_no;
	private JLabel lblimage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateStudent frame = new UpdateStudent();
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
	public UpdateStudent() {
		setTitle("Update Student");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 915, 659);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 cmbs_no = new JComboBox();
		cmbs_no.setFont(new Font("Times New Roman", Font.BOLD, 15));
		cmbs_no.setModel(new DefaultComboBoxModel(new String[] {"Serial_Number"}));
		cmbs_no.addItemListener(this);
	 	 fillCombo();
		cmbs_no.setBounds(296, 77, 225, 36);
		contentPane.add(cmbs_no);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 23));
		lblNewLabel.setBounds(93, 198, 153, 36);
		contentPane.add(lblNewLabel);
		
		txtname = new JTextField();
		txtname.setEditable(false);
		txtname.setForeground(new Color(220, 20, 60));
		txtname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtname.setBounds(296, 201, 225, 36);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Phone");
		lblNewLabel_1.setFont(new Font("Lucida Calligraphy", Font.BOLD, 23));
		lblNewLabel_1.setBounds(93, 279, 153, 36);
		contentPane.add(lblNewLabel_1);
		
		txtphone = new JTextField();
		txtphone.setForeground(new Color(220, 20, 60));
		txtphone.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		txtphone.setBounds(296, 282, 225, 36);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Lucida Calligraphy", Font.BOLD, 23));
		lblNewLabel_2.setBounds(93, 359, 153, 36);
		contentPane.add(lblNewLabel_2);
		
		txtemail = new JTextField();
		txtemail.setForeground(new Color(220, 20, 60));
		txtemail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtemail.setBounds(296, 359, 225, 36);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setFont(new Font("Lucida Calligraphy", Font.BOLD, 23));
		lblNewLabel_3.setBounds(93, 431, 153, 36);
		contentPane.add(lblNewLabel_3);
		
		 txtaddress = new JTextArea();
		txtaddress.setForeground(new Color(220, 20, 60));
		txtaddress.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 13));
		txtaddress.setBounds(296, 431, 225, 66);
		contentPane.add(txtaddress);
		
		JButton btnupdate = new JButton("Update");
		btnupdate.setBackground(new Color(250, 128, 114));
		btnupdate.addActionListener(this);
		btnupdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnupdate.setBounds(215, 532, 153, 36);
		contentPane.add(btnupdate);
		
		 ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/sms/images/student.jpg"));
			
			//creating imageicon objectwith image
			
			Image i2=ic.getImage().getScaledInstance(954, 670, Image.SCALE_DEFAULT);
			
			ImageIcon ic1=new ImageIcon(i2);
		
		lblimage = new JLabel("");
		lblimage.setBounds(0, 0, 901, 622);
		lblimage.setIcon(ic1);
		contentPane.add(lblimage);
	}
	public void fillCombo()
	{
	
	Connection con=DBConnection.openConnection();
	PreparedStatement ps=null;  //compiled query reference it will hold
	ResultSet rs=null;    //resultant dataset reference it will hold
	String selectQuery="select * from student_details";   //*means all columns with with all records
	try {
		
		ps=con.prepareStatement(selectQuery);
		rs= ps.executeQuery();   //only for select query 
		while(rs.next()==true)  //it will return true when table contains data ,it will not fetch data , only watch it 
		{
			String s_no=rs.getString("serial_number");  //fetch the value from serial_number  column of STUDENT details 
			cmbs_no.addItem(s_no);  //add the fetched value in comboBox 
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
		// TODO Auto-generated method student

		  String name=txtname.getText();  //to fetch value from name field
		  String phone=txtphone.getText();
		  String email=txtemail.getText();
		  String address=txtaddress.getText();
		  
		  String serial_no=(String)cmbs_no.getSelectedItem();
		  if(name.isEmpty()|| phone.isEmpty() || email.isEmpty()||address.isEmpty()|| serial_no.equalsIgnoreCase("Serial_Number"))
		  {
			  JOptionPane.showMessageDialog(this, "Please fill details");
			  
		  }	
		  
		  else {
			  
			  Connection con=DBConnection.openConnection();
			  PreparedStatement ps=null;
			  String updateQuery="update student_details set phone=?,email=? ,address=? where serial_number=?";
			  try {
				  
				  ps=con.prepareStatement(updateQuery);
				  
				  ps.setString(1, phone);
				  ps.setString(2,email);
				  ps.setString(3, address);
				  ps.setInt(4, Integer.parseInt(serial_no));
				 int result=ps.executeUpdate();
				 if(result>0) {
					 JOptionPane.showMessageDialog(this," course details updated successfully");
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
		// TODO Auto-generated method stub
		
		int state=e.getStateChange();
		if(state==1)  //1 means current state
		{
			String serial_no=(String)cmbs_no.getSelectedItem();
			if(serial_no.equalsIgnoreCase("Serial_Number"))
			{
				JOptionPane.showMessageDialog(this, "Pls select valid s_no");
			}
			Connection con=DBConnection.openConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String select_Query="select * from student_details where serial_number=?";
			
			try {
				ps=con.prepareStatement(select_Query);
				ps.setString(1, serial_no);
				rs=ps.executeQuery();
				rs.next();
				String cname=rs.getString("name");
				String cphone=rs.getString("phone");  //to fetch the value from fees column
				String cemail=rs.getString("email");   //to fetch the value using column number
				String caddress=rs.getNString("address");
				txtname.setText(cname);
				txtphone.setText(cphone);
				txtemail.setText(cemail);
				txtaddress.setText(caddress);
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
