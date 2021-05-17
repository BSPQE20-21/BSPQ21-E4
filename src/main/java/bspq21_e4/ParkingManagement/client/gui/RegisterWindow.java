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
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


/**
 * @class RegisterWindow
 * Window which allows registering premium users
 * @author BSPQ21-E4
 */
public class RegisterWindow extends JFrame {

	public JTextField tfEmail;
	public JTextField tfPlate;
	private JLabel lbEmail;
	private JLabel lbPlate;
	private JPanel panelContenidos;
	private JLabel lb;
	
	private static ResourceBundle resourceBundle; 

	/**
	 * Creating the application.
	 */
	public RegisterWindow() {

		setResizable(false);
		initialize();

	}
	
	public ResourceBundle getResourceBundle(){
		return resourceBundle; 
	}

	/**
	 * Initializing the contents of the frame.
	 */
	public void initialize() {
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
		
		/**
	     * This action listener is related with the return button
	     * When this button is clicked this window will be closed and the authorization or logging window will be displayed again
	     * @see bspq21_e4.ParkingManagement.client.gui.AuthWindow
	     */
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AuthWindow frame = new AuthWindow();
				frame.setVisible(true);
				dispose();
			}
		});

		JButton btnRegister = new JButton(getResourceBundle().getString("register"));
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setBackground(new Color(72, 61, 139));
		
		/**
	     * This action listener is related with the register button
	     * When this button is clicked a new Premium user will be created and stored in the DB
	     * @see bspq21_e4.ParkingManagement.server.rsh.PremiumUserRSH
	     */
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
		

		JButton btnClose = new JButton(getResourceBundle().getString("close"));
		btnClose.setForeground(Color.WHITE);
		btnClose.setBackground(new Color(72, 61, 139));
		
		/**
	     * This action listener is related with the close button
	     * When this button is clicked the window will be closed and the program ends
	     */
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});



		lbEmail = new JLabel(getResourceBundle().getString("email"));
		lbPlate = new JLabel(getResourceBundle().getString("plate"));

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
