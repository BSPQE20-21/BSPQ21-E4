package bspq21_e4.ParkingManagement.client.gui;

import bspq21_e4.ParkingManagement.client.gui.*;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.Slot;
import bspq21_e4.ParkingManagement.server.rsh.PremiumUserRSH;

import java.awt.Color;
import java.awt.EventQueue;
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

	private JPanel contentPane;
	private JTextField tfEmail;
	private JTextField tfPlate;

	public RegisterWindow() {

		setTitle("Sing up user");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(72, 61, 139));
		panel.setForeground(new Color(0, 128, 128));
		panel.setBounds(0, 21, 615, 43);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbemail = new JLabel("Email");
		lbemail.setBounds(268, 11, 77, 32);
		lbemail.setHorizontalAlignment(SwingConstants.CENTER);
		lbemail.setForeground(SystemColor.text);
		panel.add(lbemail);

		tfEmail = new JTextField();
		tfEmail.setBounds(285, 82, 96, 19);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);

		JLabel lbPlate = new JLabel("Plate:");
		lbPlate.setBounds(208, 85, 57, 13);
		contentPane.add(lbPlate);

		tfPlate = new JTextField();
		tfPlate.setColumns(10);
		tfPlate.setBounds(285, 113, 96, 19);
		contentPane.add(tfPlate);

		JButton btnVolver = new JButton("Return");
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setBackground(new Color(72, 61, 139));
		btnVolver.setBounds(10, 294, 80, 31);
		contentPane.add(btnVolver);
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
		btnRegister.setBounds(235, 230, 141, 36);
		contentPane.add(btnRegister);

		JButton btnClose = new JButton("Close");
		btnClose.setForeground(Color.WHITE);
		btnClose.setBackground(new Color(72, 61, 139));
		btnClose.setBounds(517, 294, 80, 31);
		contentPane.add(btnClose);
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

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
	}

}
