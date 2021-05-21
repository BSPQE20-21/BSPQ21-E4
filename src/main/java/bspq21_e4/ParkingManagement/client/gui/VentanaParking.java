
package bspq21_e4.ParkingManagement.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
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
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bspq21_e4.ParkingManagement.server.data.GuestUser;
import bspq21_e4.ParkingManagement.server.data.Parking;
import bspq21_e4.ParkingManagement.server.data.PremiumUser;
import bspq21_e4.ParkingManagement.server.data.Slot;
import bspq21_e4.ParkingManagement.server.data.SlotAvailability;
import bspq21_e4.ParkingManagement.server.data.User;
import bspq21_e4.ParkingManagement.server.rsh.GuestUserRSH;
import bspq21_e4.ParkingManagement.server.rsh.ParkingRSH;
import bspq21_e4.ParkingManagement.server.rsh.PremiumUserRSH;
import bspq21_e4.ParkingManagement.server.rsh.SlotRSH;
import bspq21_e4.ParkingManagement.server.rsh.UserRSH;
import jakarta.ws.rs.core.GenericType;

/**
 * @class VentanaParking Window which shows the available slots in order for the
 *        user to select where to park
 * @author BSPQ21-E4
 */
public class VentanaParking extends JFrame {
	private static final long serialVersionUID = -464873001356522418L;
	private Slot selectedSlot;
	private JPanel contentPanel;
	private JButton selectSlot;
	private JList<Slot> slotL;
	private DefaultListModel slotDL;
	private JMenuBar menu;
	private JMenu menuUsuarios;
	private JMenuItem menuItem;
	private List<Slot> slotAL = SlotRSH.getInstance().checkSlots();
	private static ResourceBundle resourceBundle;

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	/**
	 * Constructor of the window just receives the user logged
	 * 
	 * @see bspq21_e4.ParkingManagement.client.gui.VentanaParking
	 * @param User
	 */
	public VentanaParking(PremiumUser u) {
		setResizable(false);
		resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
		initialize(u);
	}

	public void initialize(final PremiumUser u) {

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
		int counter = 0;
		List<Slot> slotList = SlotRSH.getInstance().checkSlots();
		for (int i = 0; i < slotList.size(); i++) {
			if (slotList.get(i).getSl().toString() == "GREEN") {
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
				// System.out.println(resourceBundle.getString("select_try"));
				System.out.println(selectedSlot.toString());
				selectedSlot.setSl(SlotAvailability.YELLOW);

				u.setSlotPk(selectedSlot.getPk());
				SlotRSH.getInstance().modifySlot(selectedSlot);
				PremiumUserRSH.getInstance().modifyPremiumUser(u);

//						ParkingRSH.getInstance().modifyParking(selectedSlot.getIdParking())

			}
		});

		panelDer.add(selectSlot);
		panelCent.add(slotL);

		JButton btnPay = new JButton(getResourceBundle().getString("retireAndPay"));

		btnPay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				PaymentWindow pw = new PaymentWindow(u);
				pw.setVisible(true);

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

				for (PremiumUser user : listaComprobacion) {
					if (user.getPlate().equals(u.getPlate())) {
						JOptionPane.showMessageDialog(null, getResourceBundle().getString("errorDelUser"));
					} else {
						dispose();
						AuthWindow v = new AuthWindow();
						v.setVisible(true);
						break;
					}
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

		JMenuItem menuItem5 = new JMenuItem(getResourceBundle().getString("bookingHistory"));

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

	public VentanaParking(GuestUser u) {
		setResizable(false);
		resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
		initializeGU(u);
	}

	public void initializeGU(final GuestUser u) {

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
		int counter = 0;
		List<Slot> slotList = SlotRSH.getInstance().checkSlots();
		for (int i = 0; i < slotList.size(); i++) {
			if (slotList.get(i).getSl().toString() == "GREEN") {
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
				// System.out.println(resourceBundle.getString("select_try"));
				System.out.println(selectedSlot.toString());
				selectedSlot.setSl(SlotAvailability.YELLOW);

				u.setSlotPk(selectedSlot.getPk());
				SlotRSH.getInstance().modifySlot(selectedSlot);
				GuestUserRSH.getInstance().modifyGuestUser(u);

//							ParkingRSH.getInstance().modifyParking(selectedSlot.getIdParking())

			}
		});

		panelDer.add(selectSlot);
		panelCent.add(slotL);

		JButton btnPay = new JButton(getResourceBundle().getString("retireAndPay"));

		btnPay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				PaymentWindow pw = new PaymentWindow(u);
				pw.setVisible(true);

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

				for (GuestUser user : listaComprobacion) {
					if (user.getPlate().equals(u.getPlate())) {
						JOptionPane.showMessageDialog(null, getResourceBundle().getString("errorDelUser"));
					} else {
						dispose();
						AuthWindow v = new AuthWindow();
						v.setVisible(true);
						break;
					}
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

		JMenuItem menuItem5 = new JMenuItem(getResourceBundle().getString("bookingHistory"));

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