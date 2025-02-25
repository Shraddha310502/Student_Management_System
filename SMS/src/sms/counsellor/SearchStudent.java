package sms.counsellor;
import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sms.dbinfo.DBConnection;
import sms.gui.LoginFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.Color;
public class SearchStudent extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txts_no;
	private JLabel lblname;
	private JLabel lblemail;
	private JLabel lblphone;
	private JLabel lbladdress;
	private JLabel lblcourse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchStudent frame = new SearchStudent();
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
	public SearchStudent() {
		setTitle("SearchStudent");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 986, 691);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Serial_Number");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(250, 128, 114));
		lblNewLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));
		lblNewLabel.setBounds(89, 97, 258, 49);
		contentPane.add(lblNewLabel);
		
		txts_no = new JTextField();
		txts_no.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txts_no.setBounds(449, 106, 169, 38);
		contentPane.add(txts_no);
		txts_no.setColumns(10);
		
		JButton btnsearch = new JButton("Search");
		btnsearch.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnsearch.addActionListener(this);
		btnsearch.setBounds(682, 104, 106, 40);
		contentPane.add(btnsearch);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Lucida Calligraphy", Font.BOLD, 23));
		lblNewLabel_1.setBounds(114, 195, 133, 38);
		contentPane.add(lblNewLabel_1);
		
		 lblname = new JLabel("");
		 lblname.setOpaque(true);
		 lblname.setBackground(new Color(221, 160, 221));
		 lblname.setForeground(new Color(0, 0, 0));
		lblname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblname.setBounds(339, 195, 205, 38);
		contentPane.add(lblname);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Lucida Calligraphy", Font.BOLD, 23));
		lblNewLabel_2.setBounds(114, 284, 133, 32);
		contentPane.add(lblNewLabel_2);
		
		 lblemail = new JLabel("");
		 lblemail.setOpaque(true);
		 lblemail.setBackground(new Color(221, 160, 221));
		 lblemail.setForeground(new Color(0, 0, 0));
		lblemail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblemail.setBounds(339, 285, 205, 38);
		contentPane.add(lblemail);
		
		JLabel lblNewLabel_3 = new JLabel("Phone");
		lblNewLabel_3.setFont(new Font("Lucida Calligraphy", Font.BOLD, 23));
		lblNewLabel_3.setBounds(114, 367, 133, 38);
		contentPane.add(lblNewLabel_3);
		
		 lblphone = new JLabel("");
		 lblphone.setOpaque(true);
		 lblphone.setBackground(new Color(221, 160, 221));
		 lblphone.setForeground(new Color(0, 0, 0));
		lblphone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblphone.setBounds(339, 367, 205, 38);
		contentPane.add(lblphone);
		
		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setFont(new Font("Lucida Calligraphy", Font.BOLD, 23));
		lblNewLabel_4.setBounds(114, 450, 133, 38);
		contentPane.add(lblNewLabel_4);
		
		 lbladdress = new JLabel("");
		 lbladdress.setOpaque(true);
		 lbladdress.setBackground(new Color(221, 160, 221));
		 lbladdress.setForeground(new Color(0, 0, 0));
		lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbladdress.setBounds(339, 450, 205, 38);
		contentPane.add(lbladdress);
		
		JLabel lblNewLabel_6 = new JLabel("Course");
		lblNewLabel_6.setFont(new Font("Lucida Calligraphy", Font.BOLD, 23));
		lblNewLabel_6.setBounds(114, 538, 133, 38);
		contentPane.add(lblNewLabel_6);
		
		 lblcourse = new JLabel("");
		 lblcourse.setBackground(new Color(221, 160, 221));
		 lblcourse.setOpaque(true);
		 lblcourse.setForeground(new Color(0, 0, 0));
		lblcourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblcourse.setBounds(339, 538, 205, 38);
		contentPane.add(lblcourse);
		

        ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/sms/images/student.jpg"));
		
		//creating imageicon objectwith image
		
		Image i2=ic.getImage().getScaledInstance(954, 670, Image.SCALE_DEFAULT);
		
		ImageIcon ic1=new ImageIcon(i2);
		
		
		JLabel lblimage = new JLabel("");
		lblimage.setBounds(0, 0, 972, 654);
		lblimage.setIcon(ic1);
		contentPane.add(lblimage);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		searchStudent();
		
	}
	
	public void searchStudent() 
	{
		 String serial_no=txts_no.getText();
		 if(serial_no.isEmpty()) 
		 {
			 JOptionPane.showMessageDialog(this, "Please enter serial_no");
		 }
		 else
		 {
		Connection con=DBConnection.openConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String selectQuery="select * from student_details where serial_number=?";
		try
		{
			ps=con.prepareStatement(selectQuery);
			ps.setInt(1, Integer.parseInt(serial_no));
			rs=ps.executeQuery();
			if(rs.next()) 
			{
			String cname=rs.getString("name");
			String cphone=rs.getString("phone");  
			String cemail=rs.getString("email"); 	
			String ccourse=rs.getString("course_name");
			String caddress=rs.getString("address");
			lblname.setText(cname);
			lblemail.setText(cphone);
			lblphone.setText(cemail);
			lbladdress.setText(caddress);
			lblcourse.setText(ccourse);
		}
		
			else {
				JOptionPane.showMessageDialog(this, "No Such Record Exist");
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
		
	}
}
