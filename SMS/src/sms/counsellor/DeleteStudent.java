package sms.counsellor;
import java.awt.event.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DeleteStudent extends JFrame implements ActionListener 
{

	private JPanel contentPane;
	private JTextField txtphone;
	private JTextField txtcourse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteStudent frame = new DeleteStudent();
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
	public DeleteStudent() {
		setTitle("Delete Student");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 889, 566);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Phone");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel.setBounds(142, 143, 154, 38);
		contentPane.add(lblNewLabel);
		
		txtphone = new JTextField();
		txtphone.setForeground(new Color(255, 20, 147));
		txtphone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtphone.setBounds(302, 143, 204, 33);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Course");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_1.setBounds(142, 242, 154, 38);
		contentPane.add(lblNewLabel_1);
		
		txtcourse = new JTextField();
		txtcourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtcourse.setBounds(302, 242, 204, 38);
		contentPane.add(txtcourse);
		txtcourse.setColumns(10);
		
		JButton btndelete = new JButton("Delete");
		btndelete.setBackground(new Color(250, 128, 114));
		btndelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btndelete.setBounds(223, 351, 154, 48);
		btndelete.addActionListener(this);
		contentPane.add(btndelete);
		
		 ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/sms/images/student.jpg"));
			
			//creating imageicon objectwith image
			
			Image i2=ic.getImage().getScaledInstance(954, 670, Image.SCALE_DEFAULT);
			
			ImageIcon ic1=new ImageIcon(i2);
		JLabel lblimage = new JLabel("");
		lblimage.setBounds(0, 0, 875, 529);
		lblimage.setIcon(ic1);
		contentPane.add(lblimage);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		deleteCourse();
	
		
	}
	
	public void deleteCourse() 
	{
		String phone=txtphone.getText();
		String courseName=txtcourse.getText().trim();
		
		if(phone.isBlank() || courseName.isBlank()) {
			JOptionPane.showMessageDialog(this, "Pls fill all fields");
			
		}
		else {
			int choice=JOptionPane.showConfirmDialog(this, "Do you wish to delete record " );
			if(choice==0) {
				Connection con=DBConnection.openConnection();
				PreparedStatement ps=null;
				String deleteQuery="delete from student_details where phone=? and course_name=?";
				
				try {
					
					ps=con.prepareStatement(deleteQuery);
					ps.setString(1, phone);
					ps.setString(2, courseName);
					int result=ps.executeUpdate();
					if(result>0) {
						JOptionPane.showMessageDialog(this, " Record Deleted successfully ");
					}
					
					else {
						JOptionPane.showMessageDialog(this," Do not exists in the records"," No Data Found Error",
								 JOptionPane.ERROR_MESSAGE);
					
					}
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				finally {
					{
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
				
				
			}
			
		}
	

		
	}

