package sms.counsellor;
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
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

public class CounsellorFrame extends JFrame implements ActionListener,WindowListener{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CounsellorFrame frame = new CounsellorFrame();
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
	public CounsellorFrame() {
		this.addWindowListener(this);  
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnStudent = new JMenu("Student");
		menuBar.add(mnStudent);
		
		JMenuItem mi_addstudent = new JMenuItem("Add");
		mi_addstudent.setIcon(new ImageIcon(CounsellorFrame.class.getResource("/sms/images/add.png")));
		mi_addstudent.addActionListener(this);
		mnStudent.add(mi_addstudent);
		
		JMenuItem mi_updatestudent = new JMenuItem("Update");
		mi_updatestudent.setIcon(new ImageIcon(CounsellorFrame.class.getResource("/sms/images/update.png")));
		mi_updatestudent.addActionListener(this);
		mnStudent.add(mi_updatestudent);
		
		JMenuItem mi_searchstudent = new JMenuItem("Search");
		mi_searchstudent.setIcon(new ImageIcon(CounsellorFrame.class.getResource("/sms/images/search.png")));
		mi_searchstudent.addActionListener(this);
		mnStudent.add(mi_searchstudent);
		
		JMenuItem mi_deletestudent = new JMenuItem("Delete");
		mi_deletestudent.setIcon(new ImageIcon(CounsellorFrame.class.getResource("/sms/images/delete.png")));
		mi_deletestudent.addActionListener(this);
		mnStudent.add(mi_deletestudent);
		
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CounsellorFrame");
		lblNewLabel.setBounds(39, 103, 262, 96);
		contentPane.add(lblNewLabel);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
System.out.println("item is being clicked");
		
		String caption=e.getActionCommand();  //return the text written on the menuItem
		
		switch(caption){
			case "Add" :
				                 StudentDetails studentFrame=new  StudentDetails();
				                 studentFrame.setVisible(true);
				                 break;
			case "Update":
				         UpdateStudent updateFrame=new UpdateStudent();
				         updateFrame.setVisible(true);
				break;
			case "Search":
				SearchStudent searchFrame=new SearchStudent();
				searchFrame.setVisible(true);
				break;
			case "Delete": 
				     DeleteStudent deleteFrame=new DeleteStudent();
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
	}
