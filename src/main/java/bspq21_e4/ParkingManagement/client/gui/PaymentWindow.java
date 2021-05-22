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
import java.util.Locale;
import java.util.ResourceBundle;

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
import bspq21_e4.ParkingManagement.server.rsh.GuestUserRSH;
import bspq21_e4.ParkingManagement.server.rsh.PremiumUserRSH;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;


/**
 * @class PaymentWindow
 * Window which allows to make payments for the time spent in the parking
 * @author BSPQ21-E4
 */
public class PaymentWindow extends JFrame {

	private JPanel panelContenidos;
	private JPanel panelVisa;
	private JPanel panelPaypal;

	private JMenuBar menu;
	private JMenu menuUsuarios;
	private JMenuItem menuItem;

	private JComboBox<String> cbPayMethod;
	private String noSelectableOptionPay = "Payment Method";
	
	private static ResourceBundle resourceBundle;
	
	public ResourceBundle getResourceBundle(){
		return resourceBundle; 
	}


	public PaymentWindow(PremiumUser u) {
		resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
		initialize(u);
	}
	
	/**
	 * Creating the application.
	 */
	public void initialize(final PremiumUser u) {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 250);
		this.setMinimumSize(new Dimension(750, 500));

		panelContenidos = new JPanel();
		panelContenidos.setBackground(Color.WHITE);
		setContentPane(panelContenidos);
		panelContenidos.setLayout(new BorderLayout(15, 15));

		// PanelCentral
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setLayout(new GridLayout());
		panelContenidos.add(panelCentral, BorderLayout.CENTER);

		JPanel panelCb = new JPanel();
		panelCb.setBackground(Color.WHITE);
		panelCentral.add(panelCb, BorderLayout.WEST);

		panelPaypal = new JPanel();
		panelPaypal.setBackground(Color.WHITE);
		panelCentral.add(panelPaypal, BorderLayout.EAST);
		panelPaypal.setVisible(false);

		panelVisa = new JPanel();
		panelVisa.setBackground(Color.WHITE);
		panelCentral.add(panelVisa, BorderLayout.EAST);
		panelVisa.setVisible(false);

		cbPayMethod = new JComboBox<String>();
		cbPayMethod.setBounds(10, 50, 122, 40);

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
				case 1:
					panelVisa.setVisible(false);
					panelPaypal.setVisible(true);

					break;
				case 2:

					panelPaypal.setVisible(false);
					panelVisa.setVisible(true);

					break;
				}

			}
		});
		panelCb.setLayout(null);
		panelCb.add(cbPayMethod);
		
		JLabel lbImporte = new JLabel();
		//preguntar a fabri mañana a ver como calcular el importe
		

		panelPaypal.setLayout(new GridLayout(0, 2, 0, 25));

		JLabel label = new JLabel("");
		panelPaypal.add(label);

		JLabel label_1 = new JLabel("");
		panelPaypal.add(label_1);

		JLabel lbEmail = new JLabel(getResourceBundle().getString("email"));

		panelPaypal.add(lbEmail);
		JTextField tfEmail = new JTextField();
		panelPaypal.add(tfEmail);

		JLabel lbPassword = new JLabel(getResourceBundle().getString("password"));

		panelPaypal.add(lbPassword);
		JPasswordField pfPassword = new JPasswordField();
		panelPaypal.add(pfPassword);

		JLabel lbName = new JLabel("Name");
		lbName.setBounds(0, 1, 81, 34);
		JTextField tfName = new JTextField();
		tfName.setBounds(81, 1, 81, 34);

		JLabel lbCardNumber = new JLabel("Card Number");
		lbCardNumber.setBounds(0, 35, 81, 34);
		JTextField tfCardNumber = new JTextField();
		tfCardNumber.setBounds(81, 35, 81, 34);

		JLabel lbExpiration = new JLabel("Expiration");
		lbExpiration.setBounds(0, 69, 81, 34);
		JTextField tfExpiration = new JTextField();
		tfExpiration.setBounds(81, 69, 81, 34);

		JLabel lbCvv = new JLabel("CVV");
		lbCvv.setBounds(0, 103, 81, 34);
		JTextField tfCVV = new JTextField(3);
		tfCVV.setBounds(81, 103, 81, 34);
		panelVisa.setLayout(null);

		panelVisa.add(lbName);
		panelVisa.add(tfName);

		panelVisa.add(lbCardNumber);
		panelVisa.add(tfCardNumber);

		panelVisa.add(lbExpiration);
		panelVisa.add(tfExpiration);

		panelVisa.add(lbCvv);
		panelVisa.add(tfCVV);

		// Panel Botones
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(Color.WHITE);
		panelInferior.setLayout(new GridLayout(1, 3));
		panelContenidos.add(panelInferior, BorderLayout.SOUTH);

		JButton btnReturn = new JButton(getResourceBundle().getString("return"));
		btnReturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});

		panelInferior.add(btnReturn);

		JButton btnPay = new JButton(getResourceBundle().getString("pay"));
		btnPay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// este boton lo que tiene que hacer es liberar el slot tras haber abonadoel
				// importe correspondiente

			}
		});

		panelInferior.add(btnPay);

		// Panel izquierdo --> superior
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.WHITE);
		panelSuperior.setLayout(new BorderLayout(0, 0));
		panelContenidos.add(panelSuperior, BorderLayout.NORTH);
		menu = new JMenuBar();
		menuUsuarios = new JMenu(u.getPlate());
		menu.add(menuUsuarios);
		menuItem = new JMenuItem(getResourceBundle().getString("signOut"));
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();

				AuthWindow vi = new AuthWindow();
				vi.setVisible(true);
			}
		});

		menuUsuarios.add(menuItem);

		JMenuItem menuItem2 = new JMenuItem(getResourceBundle().getString("deleteUser"));
		menuItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				PremiumUserRSH.getInstance().deletePremiumUser(u);

				List<PremiumUser> listaComprobacion = PremiumUserRSH.getInstance().checkPremiumUsers();

				for (PremiumUser user : listaComprobacion) {
					if (user.getPlate().equals(u.getPlate())) {
						JOptionPane.showMessageDialog(null, getResourceBundle().getString("errorDelUser"));
					} else {
						dispose();
						AuthWindow v = new AuthWindow();
						v.setVisible(true);
						break;
					}
				}

			}
		});
		menuUsuarios.add(menuItem2);

		JMenuItem menuItem3 = new JMenuItem(getResourceBundle().getString("modify"));
		menuItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		menuUsuarios.add(menuItem3);

		JMenuItem menuItem4 = new JMenuItem(getResourceBundle().getString("exit"));

		menuItem4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		menuUsuarios.add(menuItem4);
		
		JMenuItem menuItem5 = new JMenuItem(getResourceBundle().getString(getResourceBundle().getString("bookingHistory")));
		
		menuItem5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				HistoryWindow v = new HistoryWindow(u);
				v.setVisible(true);
				
			}
		});
		
		menuUsuarios.add(menuItem5);

		panelSuperior.add(menu);

	}
	
	public PaymentWindow(GuestUser u) {
		resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
		initializeGU(u);
	}
	
	/**
	 * Creating the application.
	 */
	public void initializeGU(final GuestUser u) {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 250);
		this.setMinimumSize(new Dimension(750, 500));

		panelContenidos = new JPanel();
		panelContenidos.setBackground(Color.WHITE);
		setContentPane(panelContenidos);
		panelContenidos.setLayout(new BorderLayout(15, 15));

		// PanelCentral
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setLayout(new GridLayout());
		panelContenidos.add(panelCentral, BorderLayout.CENTER);

		JPanel panelCb = new JPanel();
		panelCb.setBackground(Color.WHITE);
		panelCentral.add(panelCb, BorderLayout.WEST);

		panelPaypal = new JPanel();
		panelPaypal.setBackground(Color.WHITE);
		panelCentral.add(panelPaypal, BorderLayout.EAST);
		panelPaypal.setVisible(false);

		panelVisa = new JPanel();
		panelVisa.setBackground(Color.WHITE);
		panelCentral.add(panelVisa, BorderLayout.EAST);
		panelVisa.setVisible(false);

		cbPayMethod = new JComboBox<String>();
		cbPayMethod.setBounds(10, 50, 122, 40);

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
				case 1:
					panelVisa.setVisible(false);
					panelPaypal.setVisible(true);

					break;
				case 2:

					panelPaypal.setVisible(false);
					panelVisa.setVisible(true);

					break;
				}

			}
		});
		panelCb.setLayout(null);
		panelCb.add(cbPayMethod);
		
		JLabel lbImporte = new JLabel();
		//preguntar a fabri mañana a ver como calcular el importe
		

		panelPaypal.setLayout(new GridLayout(0, 2, 0, 25));

		JLabel label = new JLabel("");
		panelPaypal.add(label);

		JLabel label_1 = new JLabel("");
		panelPaypal.add(label_1);

		JLabel lbEmail = new JLabel(getResourceBundle().getString("email"));

		panelPaypal.add(lbEmail);
		JTextField tfEmail = new JTextField();
		panelPaypal.add(tfEmail);

		JLabel lbPassword = new JLabel(getResourceBundle().getString("password"));

		panelPaypal.add(lbPassword);
		JPasswordField pfPassword = new JPasswordField();
		panelPaypal.add(pfPassword);

		JLabel lbName = new JLabel("Name");
		lbName.setBounds(0, 1, 81, 34);
		JTextField tfName = new JTextField();
		tfName.setBounds(81, 1, 81, 34);

		JLabel lbCardNumber = new JLabel("Card Number");
		lbCardNumber.setBounds(0, 35, 81, 34);
		JTextField tfCardNumber = new JTextField();
		tfCardNumber.setBounds(81, 35, 81, 34);

		JLabel lbExpiration = new JLabel("Expiration");
		lbExpiration.setBounds(0, 69, 81, 34);
		JTextField tfExpiration = new JTextField();
		tfExpiration.setBounds(81, 69, 81, 34);

		JLabel lbCvv = new JLabel("CVV");
		lbCvv.setBounds(0, 103, 81, 34);
		JTextField tfCVV = new JTextField(3);
		tfCVV.setBounds(81, 103, 81, 34);
		panelVisa.setLayout(null);

		panelVisa.add(lbName);
		panelVisa.add(tfName);

		panelVisa.add(lbCardNumber);
		panelVisa.add(tfCardNumber);

		panelVisa.add(lbExpiration);
		panelVisa.add(tfExpiration);

		panelVisa.add(lbCvv);
		panelVisa.add(tfCVV);

		// Panel Botones
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(Color.WHITE);
		panelInferior.setLayout(new GridLayout(1, 3));
		panelContenidos.add(panelInferior, BorderLayout.SOUTH);

		JButton btnReturn = new JButton(getResourceBundle().getString("return"));
		btnReturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});

		panelInferior.add(btnReturn);

		JButton btnPay = new JButton(getResourceBundle().getString("pay"));
		btnPay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// este boton lo que tiene que hacer es liberar el slot tras haber abonadoel
				// importe correspondiente

			}
		});

		panelInferior.add(btnPay);

		// Panel izquierdo --> superior
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.WHITE);
		panelSuperior.setLayout(new BorderLayout(0, 0));
		panelContenidos.add(panelSuperior, BorderLayout.NORTH);
		menu = new JMenuBar();
		menuUsuarios = new JMenu(u.getPlate());
		menu.add(menuUsuarios);
		menuItem = new JMenuItem(getResourceBundle().getString("signOut"));
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();

				AuthWindow vi = new AuthWindow();
				vi.setVisible(true);
			}
		});

		menuUsuarios.add(menuItem);

		JMenuItem menuItem2 = new JMenuItem(getResourceBundle().getString("deleteUser"));
		menuItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				GuestUserRSH.getInstance().deleteGuestUser(u);
				List<GuestUser> listaComprobacion = GuestUserRSH.getInstance().checkGuestUsers();

				for (GuestUser user : listaComprobacion) {
					if (user.getPlate().equals(u.getPlate())) {
						JOptionPane.showMessageDialog(null, getResourceBundle().getString("errorDelUser"));
					} else {
						dispose();
						AuthWindow v = new AuthWindow();
						v.setVisible(true);
						break;
					}
				}

			}
		});
		menuUsuarios.add(menuItem2);

		JMenuItem menuItem3 = new JMenuItem(getResourceBundle().getString("modify"));
		menuItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		menuUsuarios.add(menuItem3);

		JMenuItem menuItem4 = new JMenuItem(getResourceBundle().getString("exit"));

		menuItem4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		menuUsuarios.add(menuItem4);
		
		JMenuItem menuItem5 = new JMenuItem(getResourceBundle().getString(getResourceBundle().getString("bookingHistory")));
		
		menuItem5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				HistoryWindow v = new HistoryWindow(u);
				v.setVisible(true);
				
			}
		});
		
		menuUsuarios.add(menuItem5);

		panelSuperior.add(menu);

	}



}
