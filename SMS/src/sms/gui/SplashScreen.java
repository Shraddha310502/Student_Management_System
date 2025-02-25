package sms.gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

public class SplashScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)   {
		
		//event dispatcher thread
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreen frame = new SplashScreen();
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
	public SplashScreen() {
		setTitle("SplashScreen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1025, 721);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 79, 79));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		loadFrame();
		
		JLabel lblNewLabel = new JLabel("   Student Management System");
		lblNewLabel.setForeground(new Color(255, 140, 0));
		lblNewLabel.setBackground(new Color(188, 143, 143));
		lblNewLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 40));
		lblNewLabel.setBounds(150, 84, 751, 305);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome's You !! ");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_1.setFont(new Font("Lucida Calligraphy", Font.BOLD, 40));
		lblNewLabel_1.setBounds(299, 336, 406, 146);
		contentPane.add(lblNewLabel_1);
		
		
		
		ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/sms/images/splash.jpg"));
		
		//creating imageicon objectwith image
		
		Image i2=ic.getImage().getScaledInstance(1011, 674, Image.SCALE_DEFAULT);
		
		ImageIcon ic1=new ImageIcon(i2);
		
		
		JLabel lblbgimage = new JLabel("");
		lblbgimage.setBounds(0, 0, 1011, 674);
		lblbgimage.setIcon(ic1);
		contentPane.add(lblbgimage);
	}
	
	public void loadFrame() {
		
		Thread t=new Thread(new Runnable()  {
			public void run() {
				try {
					Thread.sleep(5000);
				
				LoginFrame frame=new LoginFrame();
				frame.setVisible(true);
				SplashScreen.this.dispose();
			}
				catch(InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		}
		);
		t.start();
			
}
}
