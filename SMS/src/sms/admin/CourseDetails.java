package sms.admin;
import java.sql.*;
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

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.*;
import java.io.IOException;
import java.awt.Toolkit;
import javax.swing.ImageIcon;


public class CourseDetails extends JFrame  implements ActionListener , KeyListener 
{

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtfees;
	private JTextField txtduration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseDetails frame = new CourseDetails();
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
	public CourseDetails() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CourseDetails.class.getResource("/sms/images/course.png")));
		setTitle("CourseDetails");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 967, 685);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Course Name");
		lblNewLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 21));
		lblNewLabel.setBounds(97, 143, 196, 47);
		contentPane.add(lblNewLabel);
		
		txtname = new JTextField();
		txtname.addKeyListener(this);
		txtname.setForeground(new Color(199, 21, 133));
		txtname.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		txtname.setBounds(350, 148, 278, 42);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Course Fees");
		lblNewLabel_1.setFont(new Font("Lucida Calligraphy", Font.BOLD, 21));
		lblNewLabel_1.setBounds(97, 234, 196, 47);
		contentPane.add(lblNewLabel_1);
		
		txtfees = new JTextField();
		txtfees.addKeyListener(this);
		txtfees.setForeground(new Color(199, 21, 133));
		txtfees.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		txtfees.setBounds(350, 238, 278, 47);
		contentPane.add(txtfees);
		txtfees.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Course Duration");
		lblNewLabel_2.setFont(new Font("Lucida Calligraphy", Font.BOLD, 21));
		lblNewLabel_2.setBounds(97, 327, 206, 47);
		contentPane.add(lblNewLabel_2);
		
		txtduration = new JTextField();
		txtduration.setForeground(new Color(199, 21, 133));
		txtduration.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		txtduration.setBounds(350, 331, 278, 47);
		contentPane.add(txtduration);
		txtduration.setColumns(10);
		
		JButton btnsubmit = new JButton("Submit");
		btnsubmit.setIcon(new ImageIcon(CourseDetails.class.getResource("/sms/images/submit.png")));
		btnsubmit.addActionListener(this);
		btnsubmit.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnsubmit.setBackground(new Color(250, 128, 114));
		btnsubmit.setBounds(259, 454, 245, 85);
		
		contentPane.add(btnsubmit);
		

		ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/sms/images/addcourse.jpg"));
		
		//creating imageicon objectwith image
		
		Image i2=ic.getImage().getScaledInstance(953, 648, Image.SCALE_DEFAULT);
		
		ImageIcon ic1=new ImageIcon(i2);
		
		JLabel lblimage = new JLabel("");
		lblimage.setBounds(0, 0, 953, 648);
		lblimage.setIcon(ic1);
		contentPane.add(lblimage);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char c=e.getKeyChar();
		
//		System.out.println("key typed "+c);
		
		if(e.getSource()==txtname)
		{
			   if(!(Character.isAlphabetic(c) || c==KeyEvent.VK_BACK_SPACE))
			   {
				   e.consume();
				   JOptionPane.showMessageDialog(this, "Only alphabets are allowed","Data Error",JOptionPane.ERROR_MESSAGE);
			   }   //if for txtname
					   
		}
		if(e.getSource()==txtfees)
	    {
			 if(!(Character.isDigit(c)  || c==KeyEvent.VK_BACK_SPACE))
			   {
				   e.consume();
				   JOptionPane.showMessageDialog(this, "Only digits are allowed","Data Error",JOptionPane.ERROR_MESSAGE);
			   } //if for fees
			}
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		
//		int code=e.getKeyCode();
//		System.out.println("Key Code is "+code);
		// TODO Auto-generated method stub               
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name=txtname.getText();
		String fees=txtfees.getText();
		String duration=txtduration.getText();

		 if(name.isEmpty() || fees.isEmpty() || duration.isEmpty())  //bydefault true
		 {		 
		            JOptionPane.showMessageDialog(this,"Please provide Details");
	      }
		 
		 else {
         	Connection con=DBConnection.openConnection();   
         	PreparedStatement ps=null;
         	String insertQuery="insert into course_details(name, fees, duration)values (?,?,?)";    // ? is known as placeholder
         	try
         	{
         		ps=con.prepareStatement(insertQuery);     //it prepares the query by passing it to RDBMS and DBMS compiler / parser
         		//will compile the query and give the reference of that compiled query to ps 
         		//PreparedStatement-->interface
         		//prepareStatement-->method
         		
         		ps.setString(1, name);
         		ps.setInt(2,Integer.parseInt(fees) );
         		ps.setString(3, duration);
//         		System.out.println(ps) ;    //ps has value in the reference query
         		
         		int result = ps.executeUpdate();    //it will ask to DBMS to execute the query
         		if(result>0)
         		{
         		     JOptionPane.showMessageDialog(this, "Course Added successfully");
         		     
         		     txtname.setText("");
         		     txtfees.setText("");
         		     txtduration.setText("");
         		     
         		}
         		
         		
         		
         	}
         	catch(SQLException se)
         	{
         		JOptionPane.showMessageDialog(this, name+ " CourseName already exists ","Duplicate value error",JOptionPane.ERROR_MESSAGE);
         		se.printStackTrace();
         	}
         	finally {
         		try {
         			if(ps!=null)
         		ps.close();
         			if(con!=null)
         				con.close();
         		}
         		catch(SQLException ie) {
         			ie.printStackTrace();
         			
         		}
         	}
		 }
		
	}
}
