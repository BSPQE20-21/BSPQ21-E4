package bspq21_e4.ParkingManagement.client.gui;

import java.awt.BorderLayout;

public class AuthWindow extends JFrame {
	private static final long serialVersionUID = -464873001356522418L;

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
		

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
		

			@Override
			public void actionPerformed(ActionEvent e) {
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
						JOptionPane.showMessageDialog(null, "User not found");
					}else {
						if (!user.getPlate().equals(tfPlate.getText())) {
							JOptionPane.showMessageDialog(null, "Unknown plate");
						}else {
							if (PremiumUserConnected.getConnectedUsers().isEmpty()) {
								PremiumUserConnected.getConnectedUsers().add(user);
								dispose();
								//to implement slots and parking view
							} else {
								for (PremiumUser u : PremiumUserConnected.getConnectedUsers()) {
									if (user.equals(u)) {
										JOptionPane.showMessageDialog(null, "This user is already connected");
										tfEmail.setText("");
										tfPlate.setText("");
									} else {
										PremiumUserConnected.getConnectedUsers().add(user);
										dispose();
										new VentanaParking().setVisible(true);
										
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
		JButton btnRegistrarse = new JButton("Register");
		btnRegistrarse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
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

		lbEmail = new JLabel("Email");
		lbPlate = new JLabel("Plate");

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
