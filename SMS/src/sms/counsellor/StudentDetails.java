package sms.counsellor;
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

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.sql.*;
public class StudentDetails extends JFrame implements ActionListener,KeyListener{

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtemail;
	private JTextField txtphone;
	private JTextArea txtaddress;
	private JComboBox<String> cmbcourse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDetails frame = new StudentDetails();
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
	public StudentDetails() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StudentDetails.class.getResource("/sms/images/student.png")));
		setTitle("StudentDetails");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 968, 707);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		
		lblNewLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 21));
		lblNewLabel.setBounds(108, 111, 143, 40);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Lucida Calligraphy", Font.BOLD, 21));
		lblNewLabel_1.setBounds(108, 180, 143, 40);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone");
		lblNewLabel_2.setFont(new Font("Lucida Calligraphy", Font.BOLD, 21));
		lblNewLabel_2.setBounds(108, 248, 143, 40);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Course");
		lblNewLabel_3.setFont(new Font("Lucida Calligraphy", Font.BOLD, 21));
		lblNewLabel_3.setBounds(111, 317, 140, 40);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setFont(new Font("Lucida Calligraphy", Font.BOLD, 21));
		lblNewLabel_4.setBounds(108, 393, 143, 40);
		contentPane.add(lblNewLabel_4);
		
		txtname = new JTextField();
		txtname.addKeyListener(this);
		txtname.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		txtname.setBounds(261, 115, 304, 40);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		txtemail = new JTextField();
		
		txtemail.setBounds(261, 185, 304, 40);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		txtphone = new JTextField();
		txtphone.addKeyListener(this);
		txtphone.setBounds(261, 253, 304, 40);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		txtphone.addKeyListener(this);
		
		 txtaddress = new JTextArea();
		txtaddress.setBounds(261, 405, 304, 94);
		contentPane.add(txtaddress);
		
		JButton btnsubmit = new JButton("Submit");
		btnsubmit.setIcon(new ImageIcon(StudentDetails.class.getResource("/sms/images/submit.png")));
		btnsubmit.setBackground(new Color(250, 128, 114));
		btnsubmit.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnsubmit.setBounds(190, 547, 201, 67);
		btnsubmit.addActionListener(this);
		contentPane.add(btnsubmit);
		
		cmbcourse = new JComboBox();
		
		cmbcourse.setModel(new DefaultComboBoxModel(new String[] {"Select Course"}));
		fillCombo();    //calling method to fill course name from course table 
		cmbcourse.setBounds(261, 331, 304, 40);
		contentPane.add(cmbcourse);
		
         ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/SMS/images/student.jpg"));
		
		//creating imageicon objectwith image
		
		Image i2=ic.getImage().getScaledInstance(954, 670, Image.SCALE_DEFAULT);
		
		ImageIcon ic1=new ImageIcon(i2);
		
		
		JLabel lblimage = new JLabel("");
		lblimage.setBounds(0, 0, 954, 670);
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
	
	
	public void actionPerformed(ActionEvent e) {
		
	  String name=txtname.getText();  //to fetch value from name field
	  String email=txtemail.getText();
	  String phone=txtphone.getText();
	  
      String course=(String)cmbcourse.getSelectedItem();
	  
	  String address=txtaddress.getText();
	  
	  if(name.isEmpty() || email.isEmpty() || phone.isEmpty()||course.isEmpty()|| address.isEmpty())  //bydefault true
		 {		 
		            JOptionPane.showMessageDialog(this,"Please provide Details");
	      }
		 
	  else {
		  if(phone.length()!=10)
		  {
			  JOptionPane.showMessageDialog(this, "Must be 10 digits");
		  }
		 Connection con=DBConnection.openConnection();   
       	PreparedStatement ps=null;
       	String insertQuery="insert into student_details(name, email, phone, course_name, address,date)values (?,?,?,?,?,?)"; 
       	
       	try
     	{
     		ps=con.prepareStatement(insertQuery); 
     		 ps.setString(1, name);
        		ps.setString(2,email );
        		ps.setString(3, phone);
        		ps.setString(4,course);
        		ps.setString(5,address);
        		
		  java.util.Date d=new java.util.Date();
		  long dt=d.getTime();   //long value of todays date
		  java.sql.Date sqlDate=new java.sql.Date(dt);
		  ps.setDate(6,sqlDate);
		  int result = ps.executeUpdate();
		  if(result>0)
   		{
   		     JOptionPane.showMessageDialog(this, "Student  Added successfully");
   		     
   		     txtname.setText("");
   		     txtemail.setText("");
   		     txtphone.setText("");
   		     txtaddress.setText(""); 
   		     cmbcourse.setSelectedItem("Select Course");
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
			 catch(SQLException se) {
				 se.printStackTrace();
			 }
		 
       
		 }
	  }
	  
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
		
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
		if(e.getSource()==txtphone)
	    {
			 if(!(Character.isDigit(c)  || c==KeyEvent.VK_BACK_SPACE))
			   {
				   e.consume();
				   JOptionPane.showMessageDialog(this, "Only digits are allowed","Data Error",JOptionPane.ERROR_MESSAGE);
			   } //if for fees
			}

	}

		
	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
