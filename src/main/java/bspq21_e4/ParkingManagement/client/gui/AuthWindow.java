package bspq21_e4.ParkingManagement.client.gui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bspq21_e4.ParkingManagement.server.data.GuestUser;
import bspq21_e4.ParkingManagement.server.data.Parking;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.rsh.GuestUserRSH;
import bspq21_e4.ParkingManagement.server.rsh.ParkingRSH;
import bspq21_e4.ParkingManagement.server.rsh.PremiumUserRSH;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

/**
 * @class AuthWindow Initial window that allows both premium and guest users to
 *        enter the application if they are already registered. If they are not
 *        registered they can access the register window @see
 *        bspq21_e4.ParkingManagement.client.gui.RegisterWindow;
 * @author BSPQ21-E4
 */
public class AuthWindow extends JFrame {
	public JTextField tfEmail;
	public JTextField tfPlate;
	private JLabel lbEmail;
	private JLabel lbPlate;
	private JPanel panelContenidos;
	private JLabel lb;
	private JComboBox<String> cbParking;
	private String noSelectableOptionName;
	private String parkingSeleccionado;
	private static ResourceBundle resourceBundle;

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	/**
	 * Creating the application.
	 */
	public AuthWindow() {
		setResizable(false);
		resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
		initialize();
	}

	/**
	 * Initializing the contents of the frame.
	 */
	public void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 250);
		this.setMinimumSize(new Dimension(750, 500));

		panelContenidos = new JPanel();
		panelContenidos.setBackground(Color.WHITE);
		setContentPane(panelContenidos);
		panelContenidos.setLayout(new BorderLayout(15, 15));

		// Panel derecho
		JPanel panelDer = new JPanel();
		panelDer.setBackground(Color.WHITE);
		panelDer.setLayout(new BorderLayout(0, 0));
		panelContenidos.add(panelDer, BorderLayout.EAST);

		// Panel derecho inferior
		JPanel panelDerInf = new JPanel();
		panelDerInf.setBackground(Color.WHITE);
		panelDerInf.setLayout(new GridLayout(3, 1, 0, 5));
		panelDer.add(panelDerInf, BorderLayout.SOUTH);

		noSelectableOptionName = getResourceBundle().getString("parkingName");
		cbParking = new JComboBox<String>();

		cbParking.setModel(new DefaultComboBoxModel<String>() {

			private static final long serialVersionUID = 1L;
			private boolean seleccionPermitida = true;

			@Override
			public void setSelectedItem(Object objeto) {
				if (!noSelectableOptionName.equals(objeto)) {
					super.setSelectedItem(objeto);

				} else if (seleccionPermitida) {
					seleccionPermitida = false;
					super.setSelectedItem(objeto);
				}

			}
		});

		cbParking.addItem(noSelectableOptionName);

		cbParking.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// checkparkings
				List<Parking> nomParking = ParkingRSH.getInstance().checkParkings();
				for (Parking p : nomParking) {
					cbParking.addItem(p.getNombre());
				}

				parkingSeleccionado = cbParking.getSelectedItem().toString();

			}

		});

		JButton btnLogin = new JButton(getResourceBundle().getString("login"));

		/**
		 * This action listener is related with the login button The user introduces the
		 * email and the plate and the method checkPremiumUsers() @see
		 * bspq21_e4.ParkingManagement.server.rsh.PremiumUserRSH gets the list of
		 * premium users from the DB This way it will be checked whether the regarding
		 * user email and plate correspond to any of the DB If the credentials are okay
		 * the window with the available slots will be shown @see
		 * bspq21_e4.ParkingManagement.client.gui.VentanaParking
		 * 
		 */
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					PremiumUser user = null;
					boolean found = false;
					List<PremiumUser> userList = PremiumUserRSH.getInstance().checkPremiumUsers();
					for (PremiumUser u : userList) {
						if (u.getEmail().equals(tfEmail.getText())) {
							user = u;
							found = true;

						}
					}

					if (!found) {
						JOptionPane.showMessageDialog(null, getResourceBundle().getString("userNotFound"));

					} else if (cbParking.getSelectedItem().toString().equals(noSelectableOptionName)) {
						JOptionPane.showMessageDialog(null, getResourceBundle().getString("parkingNameError"));

					} else {

						if (!user.getPlate().equals(tfPlate.getText())) {
							JOptionPane.showMessageDialog(null, getResourceBundle().getString("unknownPlate"));
						} else {

							dispose();
							new VentanaParking(user, parkingSeleccionado).setVisible(true);

						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		JButton btnRegistrarse = new JButton(getResourceBundle().getString("register"));

		/**
		 * This action listener is related with the register button If the user is not
		 * registered and clicks the register button, the register window will be
		 * displayed
		 * 
		 * @see bspq21_e4.ParkingManagement.client.gui.RegisterWindow
		 */
		btnRegistrarse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterWindow frame = new RegisterWindow();
				frame.setVisible(true);
				dispose();
			}
		});

		JButton btnGuestUser = new JButton(getResourceBundle().getString("logAsGuest"));

		/**
		 * This action listener is related with the log as guest button If the user is
		 * not premium and clicks the log as guest button he will be able of logging
		 * just by entering a car plate Afterwards the available slots will be
		 * shown @see bspq21_e4.ParkingManagement.client.gui.VentanaParking
		 */
		btnGuestUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (tfPlate.getText().equals("")) {
					JOptionPane.showMessageDialog(null, getResourceBundle().getString("errorNullPlate"));
					tfPlate.setText("");

				} else if (cbParking.getSelectedItem().toString().equals(noSelectableOptionName)) {
					JOptionPane.showMessageDialog(null, getResourceBundle().getString("parkingNameError"));

				} else {

					String plate = tfPlate.getText();

					GuestUser user = null;
					boolean found = false;

					List<GuestUser> userList = GuestUserRSH.getInstance().checkGuestUsers();
					if (userList.size() != 0) {
						for (GuestUser u : userList) {
							if (u.getPlate().equals(tfPlate.getText())) {
								found = true;
								JOptionPane.showMessageDialog(null, getResourceBundle().getString("guestUserExists"));
								user = u;

								VentanaParking v = new VentanaParking(user, parkingSeleccionado);
								v.setVisible(true);
								dispose();
								break;
							}

						}
					}
					if (!found) {
						JOptionPane.showMessageDialog(null, getResourceBundle().getString("guestUserFirstTime"));
						GuestUser newUser = new GuestUser();
						newUser.setPlate(plate);

						GuestUserRSH.getInstance().saveGuestUsers(newUser);

						VentanaParking v = new VentanaParking(newUser, parkingSeleccionado);
						v.setVisible(true);
						dispose();
					}

				}

			}
		});

		JLabel lb2 = new JLabel("");
		panelDerInf.add(btnLogin);
		panelDerInf.add(btnRegistrarse);
		panelDerInf.add(lb2);
		panelDerInf.add(btnGuestUser);

		// Panel Central(Usuario y contraseÃ¯Â¿Â½a) solo los texfield y labels
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setLayout(new BorderLayout(0, 0));
		panelContenidos.add(panelCentral, BorderLayout.CENTER);

		// Panel superior
		JPanel panelCentralSup = new JPanel();
		panelCentralSup.setBackground(Color.WHITE);
		panelCentralSup.setLayout(new GridLayout(1, 1));
		panelCentral.add(panelCentralSup, BorderLayout.NORTH);

		// Panel central inferior
		JPanel panelCentralInf = new JPanel();
		panelCentralInf.setBackground(Color.WHITE);
		panelCentralInf.setLayout(new GridLayout(3, 2, 5, 5));
		panelCentral.add(panelCentralInf, BorderLayout.SOUTH);

		lbEmail = new JLabel(getResourceBundle().getString("email"));
		lbPlate = new JLabel(getResourceBundle().getString("plate"));

		tfEmail = new JTextField();
		tfPlate = new JTextField();

		panelCentralInf.add(lbEmail);
		panelCentralInf.add(tfEmail);
		panelCentralInf.add(lbPlate);
		panelCentralInf.add(tfPlate);
		panelCentralInf.add(cbParking);

	}

	public static void main(String[] args) {
		AuthWindow n = new AuthWindow();
		n.setVisible(true);
	}

}
