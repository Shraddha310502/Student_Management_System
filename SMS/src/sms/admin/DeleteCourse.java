package sms.admin;
import java.awt.event.*;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sms.dbinfo.DBConnection;
import sms.gui.LoginFrame;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Toolkit;

public class DeleteCourse extends JFrame implements ActionListener,KeyListener{

	private JPanel contentPane;
	private JTextField txtname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteCourse frame = new DeleteCourse();
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
	public DeleteCourse() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteCourse.class.getResource("/sms/images/course.png")));
		setTitle("DeleteCourse");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 914, 652);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Course Name");
		lblNewLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 21));
		lblNewLabel.setBounds(93, 169, 196, 47);
		contentPane.add(lblNewLabel);
		
		txtname = new JTextField();
		txtname.setBounds(332, 171, 241, 45);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JButton btndelete = new JButton("Delete");
		btndelete.setBackground(new Color(250, 128, 114));
		btndelete.setFont(new Font("Lucida Calligraphy", Font.BOLD, 21));
		btndelete.setBounds(204, 307, 175, 47);
		btndelete.addActionListener(this);
		btndelete.addKeyListener(this);  //to press enter key
		contentPane.add(btndelete);
		
		JLabel lblNewLabel_1 = new JLabel("Delete Course");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblNewLabel_1.setBounds(264, 54, 269, 34);
		contentPane.add(lblNewLabel_1);
		
        ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/sms/images/addcourse.jpg"));
		
		//creating imageicon objectwith image
		
		Image i2=ic.getImage().getScaledInstance(945, 613, Image.SCALE_DEFAULT);
		
		ImageIcon ic1=new ImageIcon(i2);
		
		JLabel lblimage = new JLabel("");
		lblimage.setBounds(0, 0, 900, 615);
		lblimage.setIcon(ic1);
		contentPane.add(lblimage);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		deleteCourse();
	
		
	}
	
	public void deleteCourse() 
	{
		
	String courseName=txtname.getText().trim();   //String method  -->to remove leading and trailing
		
		if(courseName.length()==0) {
			JOptionPane.showMessageDialog(this, "Please provide Course Name");
		}
		
		else {
			
			int choice=JOptionPane.showConfirmDialog(this, "Do you wish to delete " +courseName +" Course");
			//System.out.println(choice);
			if(choice==0) {
			Connection con=DBConnection.openConnection();
			PreparedStatement ps=null;
			String deleteQuery="delete from course_details where name=?";
			try {
				
				ps=con.prepareStatement(deleteQuery);
				ps.setString(1, courseName);
				int result=ps.executeUpdate();
				if(result>0) {
					JOptionPane.showMessageDialog(this, courseName+ " Deleted successfully");
				}
				
				else {
					JOptionPane.showMessageDialog(this, courseName+ " Do not exists in the records"," No Data Found Error",
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		int keyCode=e.getKeyCode();
//		System.out.println(keyCode);
		if(keyCode==10) {
			deleteCourse();
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
