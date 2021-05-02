package bspq21_e4.ParkingManagement.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bspq21_e4.ParkingManagement.server.data.Parking;
import bspq21_e4.ParkingManagement.server.data.Slot;
import bspq21_e4.ParkingManagement.server.data.SlotAvailability;

public class VentanaParking extends JFrame {
	private static final long serialVersionUID = -464873001356522418L;
	private Slot selectedSlot;
	private JPanel contentPanel;
	private JButton selectSlot;
	private JList<Slot> slotL;
	private DefaultListModel slotDL;
	private List<PremiumUser> slotAL = SlotRSH.getInstance().checkSlots();
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
	resourceBundle = ResourceBundle.getBundle("SystemMessages",	Locale.forLanguageTag("en"));
	
	public VentanaParking() {
		setResizable(false);
	}

	public void initialize() {
		
//		Parking p = new Parking(1,"Bilbao",5, 3, 2, 1);
//		Slot s1 = new Slot(1, 1, SlotAvailability.GREEN,p);
//		Slot s2 = new Slot(2, 1, SlotAvailability.YELLOW,p);
//		Slot s3 = new Slot(3, 1, SlotAvailability.RED,p);
//		Slot s4 = new Slot(4, 1, SlotAvailability.GREEN,p);
//		Slot s5 = new Slot(5, 1, SlotAvailability.GREEN,p);
//		
//		slotAL.add(s1);
//		slotAL.add(s2);
//		slotAL.add(s3);
//		slotAL.add(s4);
//		slotAL.add(s5);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 250);
		this.setMinimumSize(new Dimension(250, 250));
		
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		setContentPane(contentPanel);
		contentPanel.setLayout(new BorderLayout(15, 15));
		
		JPanel panelDer = new JPanel();
		panelDer.setBackground(Color.WHITE);
		panelDer.setLayout(new BorderLayout(0, 0));
		contentPanel.add(panelDer, BorderLayout.SOUTH);
		
		JPanel panelCent = new JPanel();
		panelCent.setBackground(Color.WHITE);
		panelCent.setLayout(new BorderLayout(0, 0));
		contentPanel.add(panelCent, BorderLayout.CENTER);
		
		final JList<Slot> slotL = new JList<>();
		DefaultListModel<Slot> slotDL = new DefaultListModel<>();
		int counter = 0;
		for(int i = 0; i<slotAL.size(); i++) {
			if(slotAL.get(i).getSl().toString() == "GREEN"){
				slotDL.add(counter, slotAL.get(i));
				counter ++;
			}
		}
		
		slotL.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					selectedSlot = (slotL.getSelectedValue());
	            }
			}
			
		});
		
		slotL.setModel(slotDL);
		
		JButton selectSlot = new JButton(resourceBundle.getString("select_button"));
		
		selectSlot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(resourceBundle.getString("select_try"));
				System.out.println(selectedSlot.toString());
//				try {
//					PremiumUser user = null;
//					boolean found = false;
//					List<PremiumUser> userList = PremiumUserRSH.getInstance().checkPremiumUsers();
//					for (PremiumUser u : userList) {
//						if (u.getEmail().equals(tfEmail.getText())) {
//							System.out.println(u);
//							user = u;
//							found = true;
//						}
//					}
//
//					if (!found) {
//						JOptionPane.showMessageDialog(null, "User not found");
//					}else {
//						if (!user.getPlate().equals(tfPlate.getText())) {
//							JOptionPane.showMessageDialog(null, "Unknown plate");
//						}else {
//							if (PremiumUserConnected.getConnectedUsers().isEmpty()) {
//								PremiumUserConnected.getConnectedUsers().add(user);
//								dispose();
//								//to implement slots and parking view
//							} else {
//								for (PremiumUser u : PremiumUserConnected.getConnectedUsers()) {
//									if (user.equals(u)) {
//										JOptionPane.showMessageDialog(null, "This user is already connected");
//										tfEmail.setText("");
//										tfPlate.setText("");
//									} else {
//										PremiumUserConnected.getConnectedUsers().add(user);
//										dispose();
//										//to implement parking view;
//									}
//								}
//							}
//						}
//					}
//				} catch (Exception e1) {
//					e1.printStackTrace();
//				}

			}
		});
		
		
		panelDer.add(selectSlot);
		panelCent.add(slotL);
		
		this.setVisible(true);

	}
	public static void main(String[] args) throws ParseException {
		VentanaParking vp = new VentanaParking();
		vp.initialize();
	}
}