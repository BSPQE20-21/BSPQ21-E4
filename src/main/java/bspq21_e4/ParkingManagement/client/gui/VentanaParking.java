
package bspq21_e4.ParkingManagement.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bspq21_e4.ParkingManagement.server.data.GuestUser;
import bspq21_e4.ParkingManagement.server.data.Parking;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.Slot;
import bspq21_e4.ParkingManagement.server.data.SlotAvailability;
import bspq21_e4.ParkingManagement.server.rsh.GuestUserRSH;
import bspq21_e4.ParkingManagement.server.rsh.ParkingRSH;
import bspq21_e4.ParkingManagement.server.rsh.PremiumUserRSH;
import bspq21_e4.ParkingManagement.server.rsh.SlotRSH;
import jakarta.ws.rs.core.GenericType;

/**
 * @class VentanaParking Window which shows the available slots in order for the
 *        user to select where to park
 * @author BSPQ21-E4
 */
public class VentanaParking extends JFrame {
	private static final long serialVersionUID = -464873001356522418L;

	public static SimpleDateFormat sdfResult = new SimpleDateFormat("HH:mm", Locale.US);

	private Slot selectedSlot;
	private JPanel contentPanel;
	private JMenuBar menu;
	private JMenu menuUsuarios;
	private JMenuItem menuItem;
	private static ResourceBundle resourceBundle;
	private JLabel lbInfo;
	private JPanel panelInfo;

	final Logger logger = LoggerFactory.getLogger(VentanaParking.class);
	
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	/**
	 * Constructor of the window just receives the premium user logged
	 * @see bspq21_e4.ParkingManagement.client.gui.VentanaParking
	 * @see bspq21_e4.ParkingManagement.client.data.PremiumUser;
	 * @param PremiumUser, String
	 */
	public VentanaParking(PremiumUser u, String p) {
		setResizable(false);
		resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
		initialize(u, p);
	}

	/**
	 * Initializing the window for a premium user
	 * @see bspq21_e4.ParkingManagement.client.gui.VentanaParking
	 * @see bspq21_e4.ParkingManagement.client.data.PremiumUser;
	 * @param PremiumUser, String
	 */
	public void initialize(final PremiumUser u, final String p) {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 250);
		this.setMinimumSize(new Dimension(750, 500));

		contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		setContentPane(contentPanel);
		contentPanel.setLayout(new BorderLayout(15, 15));

		JPanel panelDer = new JPanel();
		panelDer.setBackground(Color.WHITE);
		panelDer.setLayout(new GridLayout(1, 2));
		contentPanel.add(panelDer, BorderLayout.SOUTH);

		JPanel panelCent = new JPanel();
		panelCent.setBackground(Color.WHITE);
		panelCent.setLayout(new BorderLayout(0, 0));
		contentPanel.add(panelCent, BorderLayout.CENTER);

		final JList<Slot> slotL = new JList<>();
		DefaultListModel<Slot> slotDL = new DefaultListModel<>();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(slotL);
		slotL.setLayoutOrientation(JList.VERTICAL);

		int counter = 0;
		List<Slot> slotList = SlotRSH.getInstance().checkSlots();
		List<Parking> parkingList = ParkingRSH.getInstance().checkParkings();
		
		Parking parkingElegido = new Parking();
		
		for (Parking parking : parkingList) {

			if (parking.getNombre().equals(p)) {
				parkingElegido = parking;
				

			}
		}

		for (int i = 0; i < slotList.size(); i++) {
//			System.out.println(slotList.get(i).getIdParking());
//			System.out.println(parkingElegido.getId());
			
			if (slotList.get(i).getIdParking() == parkingElegido.getId()) {
				System.out.println("");
				System.out.println(slotList.get(i).toString());
				slotDL.add(counter, slotList.get(i));
				counter++;

			}

		}

		slotL.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					selectedSlot = (slotL.getSelectedValue());
				}
			}

		});

		slotL.setModel(slotDL);

		JButton selectSlot = new JButton(getResourceBundle().getString("select"));

		selectSlot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedSlot.getSl().equals(SlotAvailability.RED) || selectedSlot.getSl().equals(SlotAvailability.YELLOW)) {
					JOptionPane.showMessageDialog(null, getResourceBundle().getString("validSlot"));
					logger.info(getResourceBundle().getString("validSlot"));
				}else {
	
					selectedSlot.setSl(SlotAvailability.YELLOW);
					SlotRSH.getInstance().modifySlot(selectedSlot);
					List<Parking> listaComprobacion = ParkingRSH.getInstance().checkParkings();
					Parking parkingModified = new Parking();
					for (Parking parking : listaComprobacion) {
	
						if (parking.getNombre().equals(p)) {
							parkingModified = parking;
	
						}
	
					}
					parkingModified.setId(selectedSlot.getIdParking());
					parkingModified.setAvailableSlots(parkingModified.getAvailableSlots() - 1);
					parkingModified.setOccupiedSlots(parkingModified.getOccupiedSlots() + 1);
					
					
					ParkingRSH.getInstance().modifyParking(parkingModified);
	
					u.setSlotPk(selectedSlot.getPk());
					PremiumUserRSH.getInstance().modifyPremiumUser(u);
					
					repaint();
					dispose();
					new VentanaParking(u,p).setVisible(true);
					
					
					

				}
			}
		});

		panelDer.add(selectSlot);
		panelCent.add(scrollPane);

		JButton btnPay = new JButton(getResourceBundle().getString("retireAndPay"));

		btnPay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				PaymentWindow pw = new PaymentWindow(u, p);
				pw.setVisible(true);
				dispose();

			}
		});
		panelDer.add(btnPay);
		this.setVisible(true);

		// Panel izquierdo --> superior
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.WHITE);
		panelSuperior.setLayout(new BorderLayout(0, 0));
		contentPanel.add(panelSuperior, BorderLayout.NORTH);
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

				PremiumUserRSH.getInstance().deletePremiumUser(u);

				List<PremiumUser> listaComprobacion = PremiumUserRSH.getInstance().checkPremiumUsers();

				if (listaComprobacion.size() == 0) {
					dispose();
					AuthWindow v = new AuthWindow();
					v.setVisible(true);
				} else {
					for (PremiumUser user : listaComprobacion) {
						if (user.getPlate().equals(u.getPlate())) {
							JOptionPane.showMessageDialog(null, getResourceBundle().getString("errorDelUser"));
							logger.info(getResourceBundle().getString("errorDelUser"));
						} else {
							dispose();
							AuthWindow v = new AuthWindow();
							v.setVisible(true);

							break;
						}
					}
				}

			}
		});
		menuUsuarios.add(menuItem2);


		JMenuItem menuItem4 = new JMenuItem(getResourceBundle().getString("exit"));

		menuItem4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		menuUsuarios.add(menuItem4);


		panelSuperior.add(menu);

		 panelInfo = new JPanel();
		panelInfo.setBackground(Color.white);
		panelInfo.setLayout(new GridLayout(1, 1));
		panelSuperior.add(panelInfo, BorderLayout.SOUTH);

		List<Parking> listInfo = ParkingRSH.getInstance().checkParkings();
		for (Parking parking : listInfo) {
			if (parking.getNombre().equals(p)) {
				String nombre = parking.getNombre();
				int total = parking.getnSlots();
				int ocupados = parking.getOccupiedSlots();
				int libres = parking.getAvailableSlots();
				 lbInfo = new JLabel(
						getResourceBundle().getString("name") + ": " + nombre + " " + getResourceBundle().getString("total") + " " + total + " " + getResourceBundle().getString("occupied") + " " + ocupados + " " + getResourceBundle().getString("free") + " " + libres);
				panelInfo.add(lbInfo);
			}
		}

	}

	/**
	 * Constructor of the window just receives the guest user logged
	 * @see bspq21_e4.ParkingManagement.client.gui.VentanaParking
	 * @see bspq21_e4.ParkingManagement.client.data.GuestUser;
	 * @param GuestUser, String
	 */
	public VentanaParking(GuestUser u, String p) {
		setResizable(false);
		resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
		initializeGU(u, p);
	}

	/**
	 * Initializing the window for a guest user
	 * @see bspq21_e4.ParkingManagement.client.gui.VentanaParking
	 * @see bspq21_e4.ParkingManagement.client.data.GuestUser;
	 * @param GuestUser, String
	 */
	public void initializeGU(final GuestUser u, final String p) {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 500, 250);
		this.setMinimumSize(new Dimension(750, 500));

		contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		setContentPane(contentPanel);
		contentPanel.setLayout(new BorderLayout(15, 15));

		JPanel panelDer = new JPanel();
		panelDer.setBackground(Color.WHITE);
		panelDer.setLayout(new GridLayout(1, 2));
		contentPanel.add(panelDer, BorderLayout.SOUTH);

		JPanel panelCent = new JPanel();
		panelCent.setBackground(Color.WHITE);
		panelCent.setLayout(new BorderLayout(0, 0));
		contentPanel.add(panelCent, BorderLayout.CENTER);

		final JList<Slot> slotL = new JList<>();
		DefaultListModel<Slot> slotDL = new DefaultListModel<>();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(slotL);
		slotL.setLayoutOrientation(JList.VERTICAL);

		int counter = 0;
		List<Slot> slotList = SlotRSH.getInstance().checkSlots();
		List<Parking> parkingList = ParkingRSH.getInstance().checkParkings();
		Parking parkingElegido = new Parking();

		for (Parking parking : parkingList) {

			if (parking.getNombre().equals(p)) {
				parkingElegido = parking;

			}
		}

		for (int i = 0; i < slotList.size(); i++) {

			if (slotList.get(i).getIdParking() == parkingElegido.getId()) {

				slotDL.add(counter, slotList.get(i));
				counter++;

			}

		}

		slotL.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					
					selectedSlot = (slotL.getSelectedValue());
					
				}
			}

		});
		
		
		slotL.setModel(slotDL);

		JButton selectSlot = new JButton(getResourceBundle().getString("select"));

		selectSlot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(selectedSlot.getSl().equals(SlotAvailability.RED) || selectedSlot.getSl().equals(SlotAvailability.YELLOW)) {
					JOptionPane.showMessageDialog(null, getResourceBundle().getString("validSlot"));
					logger.info(getResourceBundle().getString("validSlot"));
				}else {
					selectedSlot.setSl(SlotAvailability.RED);
					SlotRSH.getInstance().modifySlot(selectedSlot);
					List<Parking> listaComprobacion = ParkingRSH.getInstance().checkParkings();
					Parking parkingModified = new Parking();
					for (Parking parking : listaComprobacion) {

						if (parking.getNombre().equals(p)) {
							parkingModified = parking;

						}

					}
					parkingModified.setId(selectedSlot.getIdParking());
					parkingModified.setAvailableSlots(parkingModified.getAvailableSlots() - 1);
					parkingModified.setOccupiedSlots(parkingModified.getOccupiedSlots() + 1);
					
					
					

					ParkingRSH.getInstance().modifyParking(parkingModified);

					u.setSlotPk(selectedSlot.getPk());

					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
					LocalDateTime now = LocalDateTime.now();
					u.setEntranceDate(dtf.format(now));
					GuestUserRSH.getInstance().modifyGuestUser(u);
					
					repaint();
					dispose();
					new VentanaParking(u,p).setVisible(true);
					
				}
				

			}
		});

		panelDer.add(selectSlot);
		panelCent.add(scrollPane);

		JButton btnPay = new JButton(getResourceBundle().getString("retireAndPay"));

		btnPay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				PaymentWindow pw = new PaymentWindow(u, p);
				pw.setVisible(true);
				dispose();

			}
		});
		panelDer.add(btnPay);
		this.setVisible(true);

		// Panel izquierdo --> superior
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.WHITE);
		panelSuperior.setLayout(new BorderLayout(0, 0));
		contentPanel.add(panelSuperior, BorderLayout.NORTH);
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

				GuestUserRSH.getInstance().deleteGuestUser(u);

				List<GuestUser> listaComprobacion = GuestUserRSH.getInstance().checkGuestUsers();
				if (listaComprobacion.size() == 0) {
					dispose();
					AuthWindow v = new AuthWindow();
					v.setVisible(true);
				} else {
					for (GuestUser user : listaComprobacion) {
						if (user.getPlate().equals(u.getPlate())) {
							JOptionPane.showMessageDialog(null, getResourceBundle().getString("errorDelUser"));
							logger.info(getResourceBundle().getString("errorDelUser"));
						} else {
							dispose();
							AuthWindow v = new AuthWindow();
							v.setVisible(true);
							break;
						}
					}
				}

			}

		});
		menuUsuarios.add(menuItem2);


		JMenuItem menuItem4 = new JMenuItem(getResourceBundle().getString("exit"));

		menuItem4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		menuUsuarios.add(menuItem4);



		panelSuperior.add(menu);

		panelInfo = new JPanel();
		panelInfo.setBackground(Color.white);
		panelInfo.setLayout(new GridLayout(1, 1));
		panelSuperior.add(panelInfo, BorderLayout.SOUTH);

		List<Parking> listInfo = ParkingRSH.getInstance().checkParkings();
		for (Parking parking : listInfo) {
			if (parking.getNombre().equals(p)) {
				String nombre = parking.getNombre();
				int total = parking.getnSlots();
				int ocupados = parking.getOccupiedSlots();
				int libres = parking.getAvailableSlots();
				 lbInfo = new JLabel(
						getResourceBundle().getString("name") + ": " + nombre + " " + getResourceBundle().getString("total") + " " + total + " " + getResourceBundle().getString("occupied") + " " + ocupados + " " + getResourceBundle().getString("free") + " " + libres);
				panelInfo.add(lbInfo);
			}
		}

	}

}