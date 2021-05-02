package bspq21_e4.ParkingManagement.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bspq21_e4.ParkingManagement.client.main.ClientSide;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.PremiumUserConnected;
import bspq21_e4.ParkingManagement.server.rsh.PremiumUserRSH;

public class AuthWindow extends JFrame {
	private static final long serialVersionUID = -464873001356522418L;
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
	resourceBundle = ResourceBundle.getBundle("SystemMessages",	Locale.forLanguageTag("en"));

	public JTextField tfEmail;
	public JTextField tfPlate;
	private JLabel lbEmail;
	private JLabel lbPlate;
	private JPanel panelContenidos;
	private JLabel lb;

	public AuthWindow(ClientSide client) {
		setResizable(false);
		initialize(client);
	}

	public void initialize(final ClientSide client) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 250);
		this.setMinimumSize(new Dimension(250, 250));

		panelContenidos = new JPanel();
		panelContenidos.setBackground(Color.WHITE);
		setContentPane(panelContenidos);
		panelContenidos.setLayout(new BorderLayout(15, 15));

//Panel derecho
		JPanel panelDer = new JPanel();
		panelDer.setBackground(Color.WHITE);
		panelDer.setLayout(new BorderLayout(0, 0));
		panelContenidos.add(panelDer, BorderLayout.EAST);

		// Panel derecho inferior
		JPanel panelDerInf = new JPanel();
		panelDerInf.setBackground(Color.WHITE);
		panelDerInf.setLayout(new GridLayout(3, 1, 0, 5));
		panelDer.add(panelDerInf, BorderLayout.SOUTH);

		JButton btnLogin = new JButton(resourceBundle.getString("Login"));
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("login_try" + tfPlate.getText() + ": " + tfEmail.getText());
				try {
					PremiumUser user = null;
					boolean found = false;
					List<PremiumUser> userList = PremiumUserRSH.getInstance().checkPremiumUsers();
					for (PremiumUser u : userList) {
						if (u.getEmail().equals(tfEmail.getText())) {
							System.out.println(u);
							user = u;
							found = true;
						}
					}

					if (!found) {
						JOptionPane.showMessageDialog(null, resourceBundle.getString("user_not_found"));
					}else {
						if (!user.getPlate().equals(tfPlate.getText())) {
							JOptionPane.showMessageDialog(null, resourceBundle.getString("unknown_plate"));
						}else {
							if (PremiumUserConnected.getConnectedUsers().isEmpty()) {
								PremiumUserConnected.getConnectedUsers().add(user);
								dispose();
								//to implement slots and parking view
							} else {
								for (PremiumUser u : PremiumUserConnected.getConnectedUsers()) {
									if (user.equals(u)) {
										JOptionPane.showMessageDialog(null, resourceBundle.getString("user_already_connected"));
										tfEmail.setText("");
										tfPlate.setText("");
									} else {
										PremiumUserConnected.getConnectedUsers().add(user);
										dispose();
										//to implement parking view;
									}
								}
							}
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		JButton btnRegistrarse = new JButton(resourceBundle.getString("register_button"));
		btnRegistrarse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(resourceBundle.getString("login_try") + tfPlate.getText() + ": " + tfEmail.getText());

			}
		});

		JLabel lb2 = new JLabel("");
		panelDerInf.add(btnLogin);
		panelDerInf.add(btnRegistrarse);
		panelDerInf.add(lb2);

		// Panel Central(Usuario y contraseï¿½a) solo los texfield y labels
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

		lbEmail = new JLabel(resourceBundle.getString("email"));
		lbPlate = new JLabel(resourceBundle.getString("plate"));

		tfEmail = new JTextField();
		tfPlate = new JTextField();

		panelCentralInf.add(lbEmail);
		panelCentralInf.add(tfEmail);
		panelCentralInf.add(lbPlate);
		panelCentralInf.add(tfPlate);
		lb = new JLabel("");
		panelCentralInf.add(lb);
	}

}
