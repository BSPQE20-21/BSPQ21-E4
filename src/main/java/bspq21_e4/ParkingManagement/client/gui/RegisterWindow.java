package bspq21_e4.ParkingManagement.client.gui;

import bspq21_e4.ParkingManagement.client.gui.*;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.Slot;
import bspq21_e4.ParkingManagement.server.rsh.PremiumUserRSH;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class RegisterWindow extends JFrame {

	public JTextField tfEmail;
	public JTextField tfPlate;
	private JLabel lbEmail;
	private JLabel lbPlate;
	private JPanel panelContenidos;
	private JLabel lb;

	public RegisterWindow() {

		setResizable(false);
		initialize();

	}

	public void initialize() {
		setTitle("Sing up user");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 250);
		this.setMinimumSize(new Dimension(250, 250));

		panelContenidos = new JPanel();
		panelContenidos.setBackground(Color.WHITE);
		setContentPane(panelContenidos);
		panelContenidos.setLayout(new BorderLayout(15, 15));

//Panel Central

		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setLayout(new GridLayout(2,2));
		panelContenidos.add(panelCentral, BorderLayout.CENTER);
		
		
//Panel Inferior
		
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(Color.white);
		panelInferior.setLayout(new GridLayout(1, 3));
		panelContenidos.add(panelInferior, BorderLayout.SOUTH);

		JButton btnVolver = new JButton("Return");
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setBackground(new Color(72, 61, 139));
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AuthWindow frame = new AuthWindow();
				frame.setVisible(true);
				dispose();
			}
		});

		JButton btnRegister = new JButton("Register");
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setBackground(new Color(72, 61, 139));
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String email = tfEmail.getText();
				String plate = tfPlate.getText();

				PremiumUser user = new PremiumUser();
				user.setEmail(email);
				user.setPlate(plate);

				PremiumUserRSH.getInstance().savePremiumUsers(user);

			}
		});
		

		JButton btnClose = new JButton("Close");
		btnClose.setForeground(Color.WHITE);
		btnClose.setBackground(new Color(72, 61, 139));
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});



		lbEmail = new JLabel("Email:");
		lbPlate = new JLabel("Plate:");

		tfEmail = new JTextField();
		tfPlate = new JTextField();

		panelCentral.add(lbEmail);
		panelCentral.add(tfEmail);

		panelCentral.add(lbPlate);
		panelCentral.add(tfPlate);
		
		panelInferior.add(btnClose);
		panelInferior.add(btnRegister);
		panelInferior.add(btnVolver);
	}

}
