package BloodBankSystem.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class WorkerFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JMenuItem mi_add,mi_update;
	private JMenuItem donor_add,donor_update,donor_delete;
	private JMenuItem mi_donate,mi_request;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkerFrame frame = new WorkerFrame();
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
	public WorkerFrame() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(WorkerFrame.class.getResource("/BloodBankSystem/Images/worker.png")));
		setTitle("Worker Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mi_bloodbags = new JMenu("Blood Bags");
		menuBar.add(mi_bloodbags);
		
		JMenuItem mi_add = new JMenuItem("Add");
		mi_add.addActionListener(this);
		mi_add.setIcon(new ImageIcon(WorkerFrame.class.getResource("/BloodBankSystem/Images/add.png")));
		mi_bloodbags.add(mi_add);
		
		JMenuItem mi_update = new JMenuItem("Update");
		mi_update.addActionListener(this);
		mi_update.setIcon(new ImageIcon(WorkerFrame.class.getResource("/BloodBankSystem/Images/update.png")));
		mi_bloodbags.add(mi_update);
		
		JMenu mi_donor = new JMenu("Donors/Receivers");
		menuBar.add(mi_donor);
		
		JMenuItem donor_add = new JMenuItem("Add Donor/Receiver");
		donor_add.addActionListener(this);
		donor_add.setIcon(new ImageIcon(WorkerFrame.class.getResource("/BloodBankSystem/Images/add.png")));
		mi_donor.add(donor_add);
		
		JMenuItem donor_update = new JMenuItem("Update Donor/Receiver");
		donor_update.addActionListener(this);
		donor_update.setIcon(new ImageIcon(WorkerFrame.class.getResource("/BloodBankSystem/Images/update.png")));
		mi_donor.add(donor_update);
		
		JMenuItem donor_delete = new JMenuItem("Delete Donor/Receiver");
		donor_delete.addActionListener(this);
		donor_delete.setIcon(new ImageIcon(WorkerFrame.class.getResource("/BloodBankSystem/Images/delete.png")));
		mi_donor.add(donor_delete);
		
		JMenu mi_blood = new JMenu("Blood");
		menuBar.add(mi_blood);
		
		JMenuItem mi_donate = new JMenuItem("Donate");
		mi_donate.addActionListener(this);
		mi_blood.add(mi_donate);
		
		JMenuItem mi_request = new JMenuItem("Request");
		mi_request.addActionListener(this);
		mi_blood.add(mi_request);
		
		JMenu mi_report = new JMenu("Report Management");
		menuBar.add(mi_report);
		
		JMenuItem view_donor = new JMenuItem("Donors");
		view_donor.addActionListener(this);
		mi_report.add(view_donor);
		
		JMenuItem view_receiver = new JMenuItem("Receivers");
		view_receiver.addActionListener(this);
		mi_report.add(view_receiver);
		
		JMenuItem view_bloodbags = new JMenuItem("Blood Bags");
		view_bloodbags.addActionListener(this);
		mi_report.add(view_bloodbags);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String item_text=e.getActionCommand();
		if(item_text.equalsIgnoreCase("add"))
		{
			Add_BloodBags ab=new Add_BloodBags();
			ab.setVisible(true);		
			
		}
		if(item_text.equalsIgnoreCase("update"))
		{
			Update_BloodBags ub=new Update_BloodBags();
			ub.setVisible(true);		
			
		}
		if(item_text.equalsIgnoreCase("Add Donor/Receiver"))
		{
			Add_Donor_Receiver ad=new Add_Donor_Receiver();
			ad.setVisible(true);		
			
		}
		if(item_text.equalsIgnoreCase("Update Donor/Receiver"))
		{
			Update_Donor_Receiver ud=new Update_Donor_Receiver();
			ud.setVisible(true);		
			
		}
		if(item_text.equalsIgnoreCase("Delete Donor/Receiver"))
		{
			Delete_Donor_Receiver dd=new Delete_Donor_Receiver();
			dd.setVisible(true);		
			
		}
		if(item_text.equalsIgnoreCase("Donate"))
		{
			Blood_Donate bd=new Blood_Donate();
			bd.setVisible(true);		
			
		}
		if(item_text.equalsIgnoreCase("Request"))
		{
		
			Blood_Request br=new Blood_Request();
			br.setVisible(true);
			
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

