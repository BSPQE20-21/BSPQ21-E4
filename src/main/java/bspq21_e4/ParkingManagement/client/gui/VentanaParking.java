
package bspq21_e4.ParkingManagement.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bspq21_e4.ParkingManagement.server.data.GuestUser;
import bspq21_e4.ParkingManagement.server.data.Parking;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.Slot;
import bspq21_e4.ParkingManagement.server.data.SlotAvailability;
import bspq21_e4.ParkingManagement.server.data.User;
import bspq21_e4.ParkingManagement.server.rsh.GuestUserRSH;
import bspq21_e4.ParkingManagement.server.rsh.PremiumUserRSH;
import bspq21_e4.ParkingManagement.server.rsh.SlotRSH;
import bspq21_e4.ParkingManagement.server.rsh.UserRSH;
import jakarta.ws.rs.core.GenericType;

public class VentanaParking extends JFrame {
	private static final long serialVersionUID = -464873001356522418L;
	private Slot selectedSlot;
	private JPanel contentPanel;
	private JButton selectSlot;
	private JList<Slot> slotL;
	private DefaultListModel slotDL;
	private JMenuBar menu;
	private JMenu menuUsuarios;
	private JMenuItem menuItem;
	private List<Slot> slotAL = SlotRSH.getInstance().checkSlots();

	public VentanaParking(User u) {
		setResizable(false);
		initialize(u);
	}

	public void initialize(final User u) {



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
		List<Slot> slotList = SlotRSH.getInstance().checkSlots();
		for (int i = 0; i < slotList.size(); i++) {
			if (slotList.get(i).getSl().toString() == "GREEN") {
				slotDL.add(counter, slotList.get(i));
				counter++;
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

		JButton selectSlot = new JButton("Select");

		selectSlot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// System.out.println(resourceBundle.getString("select_try"));
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
		
		

		// Panel izquierdo --> superior
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.WHITE);
		panelSuperior.setLayout(new BorderLayout(0,0));
		contentPanel.add(panelSuperior, BorderLayout.NORTH);
		menu = new JMenuBar();
		menuUsuarios = new JMenu(u.getPlate());
		menu.add(menuUsuarios);
		menuItem = new JMenuItem("Sign out");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
				
				AuthWindow vi = new AuthWindow();
				vi.setVisible(true);
			}
		});

		menuUsuarios.add(menuItem);
		
		
		JMenuItem menuItem2 = new JMenuItem("Delete User");
		menuItem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if(u instanceof PremiumUser) {
					UserRSH.getInstance().deleteUser(u);
					PremiumUserRSH.getInstance().deletePremiumUser((PremiumUser) u);
					List<PremiumUser> listaComprobacion = PremiumUserRSH.getInstance().checkPremiumUsers();
					
					for (PremiumUser user : listaComprobacion) {
						if(user.getPlate().equals(u.getPlate())) {
							JOptionPane.showMessageDialog(null, "Error. User cannot be deleted");
						}else {
							dispose();
							AuthWindow v = new AuthWindow();
							v.setVisible(true);
						}
					}

					
				}else if(u instanceof GuestUser) {
					GuestUserRSH.getInstance().deleteGuestUser((GuestUser) u);
				}

				
			}
		});
		menuUsuarios.add(menuItem2);
		
		JMenuItem menuItem3 = new JMenuItem("Modify");
		menuItem3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		menuUsuarios.add(menuItem3);
		
		JMenuItem menuItem4 = new JMenuItem("Exit");
		
		menuItem4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);				
			}
		});
		
		menuUsuarios.add(menuItem4);
		

		
		
		panelSuperior.add(menu);

	}

}