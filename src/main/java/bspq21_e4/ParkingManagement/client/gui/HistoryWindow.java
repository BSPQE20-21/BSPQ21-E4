package bspq21_e4.ParkingManagement.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bspq21_e4.ParkingManagement.server.data.GuestUser;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.User;
import bspq21_e4.ParkingManagement.server.rsh.GuestUserRSH;
import bspq21_e4.ParkingManagement.server.rsh.PremiumUserRSH;
import bspq21_e4.ParkingManagement.server.rsh.UserRSH;

/**
 * @class HistoryWindow Window which allows to see the record of the parking
 * @author BSPQ21-E4
 */
public class HistoryWindow extends JFrame {

	private JPanel panelContenidos;
	private JMenuBar menu;
	private JMenu menuUsuarios;
	private JMenuItem menuItem;
	private static ResourceBundle resourceBundle;

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	/**
	 * Creating the application.
	 */
	public HistoryWindow(PremiumUser u) {
		resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
		initialize(u);
	}

	/**
	 * Initializing the contents of the frame.
	 */
	public void initialize(final PremiumUser u) {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 250);
		this.setMinimumSize(new Dimension(750, 500));

		panelContenidos = new JPanel();
		panelContenidos.setBackground(Color.WHITE);
		setContentPane(panelContenidos);
		panelContenidos.setLayout(new BorderLayout(15, 15));

//Panel Iferior(botones)

		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(Color.WHITE);
		panelInferior.setLayout(new BorderLayout());
		panelContenidos.add(panelInferior, BorderLayout.SOUTH);

		JButton btnVolver = new JButton(getResourceBundle().getString("Return"));

		/**
		 * This action listener is related with the return button If the button is
		 * clicked, the window will be closed
		 */
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});

	}
	
	
	public HistoryWindow(final GuestUser u) {
		resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
		initializeGU(u);
	}
	
	/**
	 * Initializing the contents of the frame.
	 */
	public void initializeGU(final GuestUser u) {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 250);
		this.setMinimumSize(new Dimension(750, 500));
		
		panelContenidos = new JPanel();
		panelContenidos.setBackground(Color.WHITE);
		setContentPane(panelContenidos);
		panelContenidos.setLayout(new BorderLayout(15,15));
		
		
//Panel Iferior(botones)
		
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(Color.WHITE);
		panelInferior.setLayout(new BorderLayout());
		panelContenidos.add(panelInferior, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton(getResourceBundle().getString("Return"));
		
		
		/**
	     * This action listener is related with the return button
	     * If the button is clicked, the window will be closed
	     */
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		

	}
}

