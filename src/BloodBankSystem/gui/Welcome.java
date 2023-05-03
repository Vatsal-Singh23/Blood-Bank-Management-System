package BloodBankSystem.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;


import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class Welcome extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JMenu mnNewMenu;
	private JMenuItem mi_login;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome frame = new Welcome();
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
	public Welcome() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Welcome.class.getResource("/BloodBankSystem/Images/welcome6.png")));
		setTitle("Welcome");
		setBackground(new Color(255, 255, 204));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Login");
		mnNewMenu.addActionListener(this);
		menuBar.add(mnNewMenu);
		
		JMenuItem mi_login = new JMenuItem("Login");
		mi_login.addActionListener(this);
		mi_login.setIcon(new ImageIcon(Welcome.class.getResource("/BloodBankSystem/Images/login1.png")));
		mnNewMenu.add(mi_login);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Welcome.class.getResource("/BloodBankSystem/Images/Welcome.jpg")));
		lblNewLabel.setBounds(300,300, 300, 300);
		contentPane.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		String item_text=e.getActionCommand();
		if(item_text.equalsIgnoreCase("login"))
		{
		    Login l=new Login();
			l.setVisible(true);		
			
		}
		
	}

}
