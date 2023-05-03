package BloodBankSystem.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

public class OwnerFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JMenuItem mi_add,mi_update,mi_delete;
	private JMenuItem mi_donor,mi_receiver_1,mi_bloodbags;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OwnerFrame frame = new OwnerFrame();
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
	public OwnerFrame() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(OwnerFrame.class.getResource("/BloodBankSystem/Images/owner.png")));
		setTitle("OwnerFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mi_worker = new JMenu("Workers");
		menuBar.add(mi_worker);
		
		JMenuItem mi_add = new JMenuItem("Add");
		mi_add.addActionListener(this);//register the listener
		mi_add.setIcon(new ImageIcon(OwnerFrame.class.getResource("/BloodBankSystem/Images/add.png")));
		mi_worker.add(mi_add);
		
		JMenuItem mi_update = new JMenuItem("Update");
		mi_update.addActionListener(this);//register the listener
		mi_update.setIcon(new ImageIcon(OwnerFrame.class.getResource("/BloodBankSystem/Images/update.png")));
		mi_worker.add(mi_update);
		
		JMenuItem mi_delete = new JMenuItem("Delete");
		mi_delete.addActionListener(this);//register the listener
		mi_delete.setIcon(new ImageIcon(OwnerFrame.class.getResource("/BloodBankSystem/Images/delete.png")));
		mi_worker.add(mi_delete);
		
		JMenu mi_report = new JMenu("Report Management");
		menuBar.add(mi_report);
		
		JMenuItem mi_donor = new JMenuItem("Donors");
		mi_donor.addActionListener(this);//register the listener
		mi_report.add(mi_donor);
		
		JMenuItem mi_receiver_1 = new JMenuItem("Receivers");
		mi_receiver_1.addActionListener(this);//register the listener
		mi_report.add(mi_receiver_1);
		
		JMenuItem mi_bloodbags = new JMenuItem("Blood Bags");
		mi_bloodbags.addActionListener(this);
		mi_report.add(mi_bloodbags);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String item_text=e.getActionCommand();
		if(item_text.equalsIgnoreCase("add"))
		{
			Add_Worker ac=new Add_Worker();
			ac.setVisible(true);		
			
		}
		if(item_text.equalsIgnoreCase("update"))
		{
			Update_Worker uc=new Update_Worker();
			uc.setVisible(true);		
			
		}
		if(item_text.equalsIgnoreCase("delete"))
		{
			Delete_Worker dc=new Delete_Worker();
			dc.setVisible(true);		
			
		}
		if(item_text.equalsIgnoreCase("Donors"))
		{
		    View_Donors vd=new View_Donors();
			vd.setVisible(true);		
			
		}
		if(item_text.equalsIgnoreCase("Receivers"))
		{
		    View_Receivers vr=new View_Receivers();
			vr.setVisible(true);		
			
		}
		if(item_text.equalsIgnoreCase("Blood Bags"))
		{
		    View_BloodBags vb=new View_BloodBags();
			vb.setVisible(true);		
			
		}
		
	}
}
