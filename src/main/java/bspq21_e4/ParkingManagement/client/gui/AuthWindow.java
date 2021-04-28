package bspq21_e4.ParkingManagement.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bspq21_e4.ParkingManagement.client.main.ClientSide;


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
				System.out.println("# client wants to loggin: " + tfPlate.getText() +  ": " + tfEmail.getText());
				
//				if(client.logginUser(tfEmail.getText(), tfPlate.getText()) == true) {
//					JOptionPane.showMessageDialog(null, "ususario logeado!!");
//					
//				}else {
//					JOptionPane.showMessageDialog(null, "Error en  el login, por favor prueba de nuevo");
//				}
				
//					if (u != null) {
//						VentanaBusquedaReservaController controllerReserva = new VentanaBusquedaReservaController(controller.serviceLocator);
//						VentanaBusquedaReserva vr = new VentanaBusquedaReserva(controllerReserva, u);
//						vr.setVisible(true);
//						dispose();
//					}else {
//						JOptionPane.showMessageDialog(null, "Error en  el login, por favor prueba de nuevo");
//					}
			}
		});
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("# client wants to loggin: " + tfPlate.getText() +  ": " + tfEmail.getText());
				
//				if(client.registerUser(tfEmail.getText(), tfPlate.getText())== true) {
//					JOptionPane.showMessageDialog(null, "ususario registrado!!");
//				}else {
//					JOptionPane.showMessageDialog(null, "Error en el registro, por favor intentelo de nuevo");
//				}
//				VentanaRegController controllerRegistro = new VentanaRegController(controller.serviceLocator);
//				VentanaReg vr = new VentanaReg(controllerRegistro);
//				vr.setVisible(true);
//				dispose();

			}
		});

		JLabel lb2 = new JLabel("");
		panelDerInf.add(btnLogin);	
		panelDerInf.add(btnRegistrarse);
		panelDerInf.add(lb2);

		//Panel Central(Usuario y contraseï¿½a) solo los texfield y labels
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setLayout(new BorderLayout(0, 0));
		panelContenidos.add(panelCentral, BorderLayout.CENTER);
		
		//Panel superior
		JPanel panelCentralSup = new JPanel();
		panelCentralSup.setBackground(Color.WHITE);
		panelCentralSup.setLayout(new GridLayout(1,1));
		panelCentral.add(panelCentralSup, BorderLayout.NORTH);


		
		// Panel central inferior
		JPanel panelCentralInf = new JPanel();
		panelCentralInf.setBackground(Color.WHITE);
		panelCentralInf.setLayout(new GridLayout(3, 2, 5, 5));
		panelCentral.add(panelCentralInf, BorderLayout.SOUTH);

		lbEmail = new JLabel("Email: ");
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
