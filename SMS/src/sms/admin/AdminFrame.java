package sms.admin;
import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sms.gui.AllCourses;
import sms.gui.CourseWiseStudent;
import sms.gui.DateWiseAdmission;
import sms.gui.LoginFrame;

import javax.swing.JLabel;
import java.awt.Frame;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

public class AdminFrame extends JFrame implements ActionListener ,WindowListener{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame();
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
	public AdminFrame() {
		this.addWindowListener(this);    //register the listener with frame
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCourse = new JMenu("Course");
		menuBar.add(mnCourse);
		
		JMenuItem miaddcourse = new JMenuItem("Add");
		miaddcourse.setIcon(new ImageIcon(AdminFrame.class.getResource("/sms/images/add.png")));
		miaddcourse.addActionListener(this);
		mnCourse.add(miaddcourse);
		
		JMenuItem miupdatecourse = new JMenuItem("Update");
		miupdatecourse.setIcon(new ImageIcon(AdminFrame.class.getResource("/sms/images/update.png")));
		miupdatecourse.addActionListener(this);
		mnCourse.add(miupdatecourse);
		
		JMenuItem mideletecourse = new JMenuItem("Delete");
		mideletecourse.setIcon(new ImageIcon(AdminFrame.class.getResource("/sms/images/delete.png")));
		mideletecourse.addActionListener(this);
		mnCourse.add(mideletecourse);
		
		JMenu mnreports = new JMenu("Reports");
		menuBar.add(mnreports);
		
		JMenuItem mi_allcourses = new JMenuItem("All_courses");
		mi_allcourses.addActionListener(this);
		mnreports.add(mi_allcourses);
		
		JMenuItem mi_datewiseadmission = new JMenuItem("Date_Wise_admission");
		mi_datewiseadmission.addActionListener(this);
		mnreports.add(mi_datewiseadmission);
		
		JMenuItem mi_coursewisestudent = new JMenuItem("Course_wise_students");
		mi_coursewisestudent.addActionListener(this);
		mnreports.add(mi_coursewisestudent);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("AdminFrame");
		lblNewLabel.setBackground(new Color(255, 182, 193));
		lblNewLabel.setBounds(53, 57, 303, 112);
		contentPane.add(lblNewLabel);
	}


	public void actionPerformed(ActionEvent ae) {
		
		System.out.println("item is being clicked");
		
		String caption=ae.getActionCommand();  //return the text written on the menuItem
		
		switch(caption){
			case "Add" :
				                 CourseDetails courseFrame=new   CourseDetails();
				                 courseFrame.setVisible(true);
				                 break;
			case "Update": 
				UpdateCourse updateFrame=new UpdateCourse();
				updateFrame.setVisible(true);
				break;
			case "Delete": 
				          DeleteCourse deleteFrame=new DeleteCourse();
				          deleteFrame.setVisible(true);
				          break;
				                 
			
		}
		
		if(caption=="All_courses") {
			AllCourses ac=new AllCourses();
			ac.setVisible(true);
		}
		if(caption=="Date_Wise_admission") {
			DateWiseAdmission dw=new DateWiseAdmission();
			dw.setVisible(true);
		}
		if(caption=="Course_wise_students") {
			CourseWiseStudent cw=new CourseWiseStudent();
			cw.setVisible(true);
					
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		this.dispose();
		LoginFrame frame=new LoginFrame();
		frame.setVisible(true);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
