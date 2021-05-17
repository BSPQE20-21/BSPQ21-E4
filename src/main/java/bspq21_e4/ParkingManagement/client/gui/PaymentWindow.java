package bspq21_e4.ParkingManagement.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import bspq21_e4.ParkingManagement.server.data.GuestUser;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.User;
import bspq21_e4.ParkingManagement.server.rsh.GuestUserRSH;
import bspq21_e4.ParkingManagement.server.rsh.PremiumUserRSH;
import bspq21_e4.ParkingManagement.server.rsh.UserRSH;

public class PaymentWindow extends JFrame{
	
	private JPanel panelContenidos;
	private JPanel panelVisa;
	private JPanel panelPaypal;
	
	private JMenuBar menu;
	private JMenu menuUsuarios;
	private JMenuItem menuItem;
	
	private JComboBox<String> cbPayMethod;
	private String noSelectableOptionPay = "Payment Method"; 
	
	
	public PaymentWindow(User u) {
		initialize(u);
	}
	
	public void initialize(final User u) {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 250);
		this.setMinimumSize(new Dimension(250, 250));
		
		panelContenidos = new JPanel();
		panelContenidos.setBackground(Color.WHITE);
		setContentPane(panelContenidos);
		panelContenidos.setLayout(new BorderLayout(15, 15));
		
		
		//PanelCentral 
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setLayout(new BorderLayout());
		panelContenidos.add(panelCentral, BorderLayout.CENTER);
		
		panelPaypal = new JPanel();
		panelPaypal.setBackground(Color.WHITE);
		panelPaypal.setLayout(new GridLayout(2,2));
		panelCentral.add(panelPaypal, BorderLayout.SOUTH);
		panelPaypal.setVisible(false);

		
		panelVisa = new JPanel();
		panelVisa.setBackground(Color.WHITE);
		panelVisa.setLayout(new GridLayout(4,2));
		panelCentral.add(panelVisa, BorderLayout.SOUTH);
		panelVisa.setVisible(false);

		
		
		
		cbPayMethod = new JComboBox<String>();
		
		cbPayMethod.setModel(new DefaultComboBoxModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private boolean seleccionPermitida = true;
			@Override
			public void setSelectedItem(Object objeto) {
				if (!noSelectableOptionPay.equals(objeto)) {
					super.setSelectedItem(objeto);

				} else if (seleccionPermitida) {
					seleccionPermitida = false;
					super.setSelectedItem(objeto);
				}

			}
		});
		
		cbPayMethod.addItem(noSelectableOptionPay);
		cbPayMethod.addItem("Paypal");
		cbPayMethod.addItem("Visa");
		
	
		
		

		

		
		cbPayMethod.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectionPago = cbPayMethod.getSelectedIndex();
				

				
				switch (selectionPago) {
				case 0:
					panelVisa.setVisible(false);
					panelPaypal.setVisible(true);
	


					break;
				case 1:

					panelPaypal.setVisible(false);
					panelVisa.setVisible(true);

					break;
				}
				
			}
		});
		panelCentral.add(cbPayMethod);

		JLabel  lbEmail= new JLabel("Email");
		JTextField tfEmail = new JTextField();
		
		JLabel  lbPassword= new JLabel("Password");
		JPasswordField pfPassword = new JPasswordField();
		
		panelPaypal.add(lbEmail);
		panelPaypal.add(tfEmail);
		
		panelPaypal.add(lbPassword);
		panelPaypal.add(pfPassword);
		
		

		
		JLabel lbName = new JLabel("Name");
		JTextField tfName = new JTextField();
		
		JLabel lbCardNumber = new JLabel("Card Number");
		JTextField tfCardNumber = new JTextField();
		
		JLabel lbExpiration = new JLabel("Expiration");
		JTextField tfExpiration = new JTextField();
		
		
		JLabel  lbCvv= new JLabel("CVV");
		JTextField tfCVV = new JTextField(3);
		
		panelCentral.add(lbName);
		panelCentral.add(tfName);
		
		panelCentral.add(lbCardNumber);
		panelCentral.add(tfCardNumber);
		
		panelCentral.add(lbExpiration);
		panelCentral.add(tfExpiration);
		
		panelCentral.add(lbCvv);
		panelCentral.add(tfCVV);
		//Panel Botones
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(Color.WHITE);
		panelInferior.setLayout(new GridLayout(1,3));
		panelContenidos.add(panelInferior, BorderLayout.SOUTH);
		
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		
		panelInferior.add(btnReturn);
		
		
		
		
		
		
		
		
		
		
		// Panel izquierdo --> superior
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.WHITE);
		panelSuperior.setLayout(new BorderLayout(0,0));
		panelContenidos.add(panelSuperior, BorderLayout.NORTH);
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
