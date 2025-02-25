package sms.gui;

import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sms.admin.AdminFrame;
import sms.counsellor.CounsellorFrame;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class LoginFrame extends JFrame implements ActionListener 
{

	private JPanel contentPane;
	private JTextField userid;
	private JPasswordField userpass;
	private final ButtonGroup roles = new ButtonGroup();
	private JRadioButton rdadmin,rdcounsellor;  //? null 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setResizable(false);
		setTitle("login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 966, 657);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Id");
		lblNewLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(79, 106, 112, 36);
		contentPane.add(lblNewLabel);
		
		userid = new JTextField();
		userid.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		userid.setBounds(274, 108, 248, 36);
		contentPane.add(userid);
		userid.setColumns(10);
		
		JLabel lblLabelName = new JLabel("User Pass");
		lblLabelName.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
		lblLabelName.setForeground(new Color(0, 0, 0));
		lblLabelName.setBounds(81, 194, 159, 27);
		contentPane.add(lblLabelName);
		
		userpass = new JPasswordField();
		userpass.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		userpass.setBounds(274, 191, 248, 36);
		contentPane.add(userpass);
		
		JButton btnSubmit = new JButton("Submit");
		
		btnSubmit.setBackground(new Color(250, 128, 114));
		btnSubmit.setForeground(new Color(0, 0, 0));
		btnSubmit.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnSubmit.setBounds(227, 346, 169, 58);
		
		btnSubmit.addActionListener(this);    //register listener with source 
		
		contentPane.add(btnSubmit);
		
		rdadmin = new JRadioButton("Admin");
		roles.add(rdadmin);
		rdadmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdadmin.setBounds(172, 285, 103, 21);
		contentPane.add(rdadmin);
		
	    rdcounsellor = new JRadioButton("Counsellor");
		roles.add(rdcounsellor);
		rdcounsellor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdcounsellor.setBounds(337, 285, 103, 21);
		contentPane.add(rdcounsellor);
		
		
		ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/sms/images/wel.jpg"));
		
		//creating imageicon objectwith image
		
		Image i2=ic.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
		
		ImageIcon ic1=new ImageIcon(i2);
		
		
		JLabel lblimage = new JLabel("");
		lblimage.setBounds(587, 227, 306, 310);
		lblimage.setIcon(ic1);
		contentPane.add(lblimage);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		 String id=userid.getText(); //to fetch the value from textbox  
		 char[] pwd =userpass.getPassword();  //to fetch value from password field 
		 
		 
		 String password=String.valueOf(pwd);
		 String userRole="";
		 
		 if(rdadmin.isSelected()==true)
		 {
			 userRole=rdadmin.getText();
		 }
		 if(rdcounsellor.isSelected()==true)
		 {
			 userRole=rdcounsellor.getText();     // to fetch value from RadioButton 
		 }
		 
		 if(id.isEmpty() || password.isEmpty() || userRole.isEmpty())  //bydefault true
		 {		 
		            JOptionPane.showMessageDialog(this,"Please provide ID and password with Role");
	      }
		 
		 else {
			 if (id.equalsIgnoreCase("Precursor") && password.equals("Lucknow") && userRole.equals("Admin"))
			 {
//				System.out.println("Hello Admin"); 
				AdminFrame frame=new AdminFrame();
				frame.setVisible(true);
				this.dispose();  //to close the login frame
			 }
			 else  if (id.equalsIgnoreCase("Shraddha") && password.equals("@Shraddha12") && userRole.equals("Counsellor"))
			 {
//				 System.out.println("Hello Counsellor");
				 CounsellorFrame frame=new CounsellorFrame();
					frame.setVisible(true);
					this.dispose();
					
			 }
			 
			 else {
				 JOptionPane.showMessageDialog(this,"Invalid Credentials");
			 }
		
		 }
	
	}
}
