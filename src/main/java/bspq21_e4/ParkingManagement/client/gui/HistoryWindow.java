package bspq21_e4.ParkingManagement.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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

public class HistoryWindow extends JFrame{
	
	private JPanel panelContenidos;
	private JMenuBar menu;
	private JMenu menuUsuarios;
	private JMenuItem menuItem;
	private static ResourceBundle resourceBundle;
	
	public ResourceBundle getResourceBundle(){
		return resourceBundle; 
	}
	
	public HistoryWindow(User u) {
		initialize(u);
	}
	
	
	public void initialize(final User u) {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 250);
		this.setMinimumSize(new Dimension(250, 250));
		
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
			
				if(u instanceof PremiumUser) {
					UserRSH.getInstance().deleteUser(u);
					PremiumUserRSH.getInstance().deletePremiumUser((PremiumUser) u);
					List<PremiumUser> listaComprobacion = PremiumUserRSH.getInstance().checkPremiumUsers();
					
					for (PremiumUser user : listaComprobacion) {
						if(user.getPlate().equals(u.getPlate())) {
							JOptionPane.showMessageDialog(null, getResourceBundle().getString("errorDelUser"));
						}else {
							dispose();
							AuthWindow v = new AuthWindow();
							v.setVisible(true);
						}
					}

					
				}else if(u instanceof GuestUser) {
					GuestUserRSH.getInstance().deleteGuestUser((GuestUser) u);
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
		
		JMenuItem menuItem5 = new JMenuItem(getResourceBundle().getString("Booking history"));
		
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
