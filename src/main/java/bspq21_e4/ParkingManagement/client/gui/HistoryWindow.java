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
 * @class HistoryWindow
 * Window which allows to see the record of the parking
 * @author BSPQ21-E4
 */
public class HistoryWindow extends JFrame{
	
	private JPanel panelContenidos;
	private JMenuBar menu;
	private JMenu menuUsuarios;
	private JMenuItem menuItem;
	private static ResourceBundle resourceBundle;
	
	public ResourceBundle getResourceBundle(){
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
		
		// Panel izquierdo --> superior
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.WHITE);
		panelSuperior.setLayout(new BorderLayout(0,0));
		panelContenidos.add(panelSuperior, BorderLayout.NORTH);
		menu = new JMenuBar();
		menuUsuarios = new JMenu(u.getPlate());
		menu.add(menuUsuarios);
		menuItem = new JMenuItem(getResourceBundle().getString("signOut"));
		
		/**
	     * This action listener is related with the sign out button
	     * If the button is clicked, the HistoryWindow will be closed and the AuthWindow opened
	     * @see bspq21_e4.ParkingManagement.client.gui.AuthWindow
	     */
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
		
		/**
	     * This action listener is related with the delete user button
	     * If the button is clicked, the selected user will be deleted from the database
	     */
		menuItem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
//				if(u instanceof PremiumUser) {
//					UserRSH.getInstance().deleteUser(u);
//					PremiumUserRSH.getInstance().deletePremiumUser((PremiumUser) u);
//					List<PremiumUser> listaComprobacion = PremiumUserRSH.getInstance().checkPremiumUsers();
//					
//					for (PremiumUser user : listaComprobacion) {
//						if(user.getPlate().equals(u.getPlate())) {
//							JOptionPane.showMessageDialog(null, getResourceBundle().getString("errorDelUser"));
//						}else {
//							dispose();
//							AuthWindow v = new AuthWindow();
//							v.setVisible(true);
//						}
//					}
//
//					
//				}else if(u instanceof GuestUser) {
//					GuestUserRSH.getInstance().deleteGuestUser((GuestUser) u);
//				}

				
			}
		});
		menuUsuarios.add(menuItem2);
		
		JMenuItem menuItem3 = new JMenuItem(getResourceBundle().getString("modify"));
		
		/**
	     * This action listener is related with the modify user button
	     * If the button is clicked, the selected user will be modified in the database
	     */
		menuItem3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		menuUsuarios.add(menuItem3);
		
		JMenuItem menuItem4 = new JMenuItem(getResourceBundle().getString("exit"));
		
		/**
	     * This action listener is related with the exit button
	     * If the button is clicked, the application will be closed
	     */
		menuItem4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);				
			}
		});
		
		menuUsuarios.add(menuItem4);
		
		JMenuItem menuItem5 = new JMenuItem(getResourceBundle().getString("bookingHistory"));
		
		/**
	     * This action listener is related with the booking history button
	     * If the button is clicked, the history window will be opened
	     * @see bspq21_e4.ParkingManagement.client.gui.HistoryWindow
	     */
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
