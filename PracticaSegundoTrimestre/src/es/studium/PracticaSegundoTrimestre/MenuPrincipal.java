package es.studium.PracticaSegundoTrimestre;

import java.awt.CardLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

public class MenuPrincipal implements WindowListener, ActionListener{

	String user = new String("");

	JFrame ventana = new JFrame ("Taller de Recambios");
	List Lista = new List();
	JPanel pnlLista = new JPanel();
	JPanel pnlCard = new JPanel();
	JPanel pnlClientes = new JPanel();
	JPanel pnlRecambios = new JPanel();
	JPanel pnlReparaciones = new JPanel();
	JPanel pnlFacturas = new JPanel();

	JMenuBar barraMenu = new JMenuBar();
	JMenu menuOtros = new JMenu("Opciones");
	JMenu menuAyuda = new JMenu("Ayuda");
	JMenuItem mniOtrosAyuda = new JMenuItem("Ayuda");
	JMenuItem mniOtrosSalir = new JMenuItem("Cerrar Sesión");

	final static String Clientes = "Clientes";
	final static String Recambios = "Recambios";
	final static String Reparaciones = "Reparaciones";
	final static String Facturas = "Facturas";

	JButton btnAddCli = new JButton("Añadir Clientes");
	JButton btnModCli = new JButton("Modificar Clientes");
	JButton btnElCli = new JButton("Eliminar Clientes");
	JButton btnConCli = new JButton("Consultar Clientes");

	JButton btnAddRec = new JButton("Añadir Recambios");
	JButton btnModRec = new JButton("Modificar Recambios");
	JButton btnElRec = new JButton("Eliminar Recambios");
	JButton btnConRec = new JButton("Consultar Recambios");

	JButton btnAddRep = new JButton("Añadir Reparaciones");
	JButton btnModRep = new JButton("Modificar Reparaciones");
	JButton btnElRep = new JButton("Eliminar Reparaciones");
	JButton btnConRep = new JButton("Consultar Reparaciones");

	JButton btnAddFac = new JButton("Añadir Facturas");
	JButton btnConFac = new JButton("Consultar Facturas");
	private final JPanel pnlImg = new JPanel();
	private final JLabel label = new JLabel("");

	public MenuPrincipal(String usuario) {
		user = usuario;

		ventana.setSize(840,350);

		ventana.setJMenuBar(barraMenu);
		menuAyuda.add(mniOtrosAyuda);
		mniOtrosAyuda.addActionListener(this);
		// Añadimos un separador
		menuOtros.addSeparator();
		menuOtros.add(mniOtrosSalir);
		mniOtrosSalir.addActionListener(this);
		barraMenu.add(menuOtros);
		barraMenu.add(menuAyuda);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{120, 111, 580, 0};
		gridBagLayout.rowHeights = new int[]{71, 169, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		ventana.getContentPane().setLayout(gridBagLayout);
		pnlCard.setBorder(new TitledBorder(null, "Opciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCard.setLayout(new CardLayout() );
		pnlClientes.setBorder(null);

		pnlClientes.add(btnAddCli);
		pnlClientes.add(btnModCli);
		pnlClientes.add(btnElCli);
		pnlClientes.add(btnConCli);

		btnAddCli.addActionListener(this);
		btnModCli.addActionListener(this);
		btnElCli.addActionListener(this);
		btnConCli.addActionListener(this);

		pnlRecambios.add(btnAddRec);
		pnlRecambios.add(btnModRec);
		pnlRecambios.add(btnElRec);
		pnlRecambios.add(btnConRec);

		btnAddRec.addActionListener(this);
		btnModRec.addActionListener(this);
		btnElRec.addActionListener(this);
		btnConRec.addActionListener(this);

		pnlReparaciones.add(btnAddRep);
		pnlReparaciones.add(btnModRep);
		pnlReparaciones.add(btnElRep);
		pnlReparaciones.add(btnConRep);

		btnAddRep.addActionListener(this);
		btnModRep.addActionListener(this);
		btnElRep.addActionListener(this);
		btnConRep.addActionListener(this);

		pnlFacturas.add(btnAddFac);
		pnlFacturas.add(btnConFac);

		btnAddFac.addActionListener(this);
		btnConFac.addActionListener(this);
		GridBagConstraints gbc_pnlLista = new GridBagConstraints();
		gbc_pnlLista.gridheight = 2;
		gbc_pnlLista.anchor = GridBagConstraints.WEST;
		gbc_pnlLista.fill = GridBagConstraints.VERTICAL;
		gbc_pnlLista.insets = new Insets(0, 0, 5, 5);
		gbc_pnlLista.gridx = 0;
		gbc_pnlLista.gridy = 0;
		ventana.getContentPane().add(pnlLista, gbc_pnlLista);
		GridBagConstraints gbc_Lista = new GridBagConstraints();
		gbc_Lista.insets = new Insets(0, 0, 5, 5);
		gbc_Lista.gridx = 1;
		gbc_Lista.gridy = 0;
		ventana.getContentPane().add(Lista, gbc_Lista);

		Lista.add(Clientes);
		Lista.add(Recambios);
		Lista.add(Reparaciones);
		Lista.add(Facturas);
		Lista.addActionListener(this);

		pnlCard.add(Clientes , pnlClientes);
		pnlCard.add(Recambios , pnlRecambios);
		pnlCard.add(Reparaciones , pnlReparaciones);
		pnlCard.add(Facturas , pnlFacturas);
		GridBagConstraints gbc_pnlCard = new GridBagConstraints();
		gbc_pnlCard.insets = new Insets(0, 0, 5, 0);
		gbc_pnlCard.anchor = GridBagConstraints.WEST;
		gbc_pnlCard.fill = GridBagConstraints.VERTICAL;
		gbc_pnlCard.gridx = 2;
		gbc_pnlCard.gridy = 0;
		ventana.getContentPane().add(pnlCard, gbc_pnlCard);

		GridBagConstraints gbc_pnlImg = new GridBagConstraints();
		gbc_pnlImg.insets = new Insets(0, 0, 5, 0);
		gbc_pnlImg.fill = GridBagConstraints.BOTH;
		gbc_pnlImg.gridx = 2;
		gbc_pnlImg.gridy = 1;
		ventana.getContentPane().add(pnlImg, gbc_pnlImg);
		label.setIcon(new ImageIcon("imagenes/logo.png"));

		pnlImg.add(label);
		ventana.addWindowListener(this);
		ventana.setVisible(true);
	}
	public static void main(String[] args) {
		new MenuPrincipal("Admin");
	}
	@Override
	public void actionPerformed(ActionEvent ae) {

		//OPCIONES LISTA
		if(Clientes.equals(Lista.getSelectedItem())) 
		{
			pnlCard.add(Clientes , pnlClientes);
			pnlCard.add(Recambios , pnlRecambios);
			pnlCard.add(Reparaciones , pnlReparaciones);
			pnlCard.add(Facturas , pnlFacturas);
		} else if(Recambios.equals(Lista.getSelectedItem())) 
		{
			pnlCard.add(Recambios , pnlRecambios);
			pnlCard.add(Clientes , pnlClientes);
			pnlCard.add(Reparaciones , pnlReparaciones);
			pnlCard.add(Facturas , pnlFacturas);
		} else if(Reparaciones.equals(Lista.getSelectedItem())) 
		{
			pnlCard.add(Reparaciones , pnlReparaciones);
			pnlCard.add(Clientes , pnlClientes);
			pnlCard.add(Recambios , pnlRecambios);
			pnlCard.add(Facturas , pnlFacturas);
		} else if(Facturas.equals(Lista.getSelectedItem())) 
		{
			pnlCard.add(Facturas , pnlFacturas);
			pnlCard.add(Reparaciones , pnlReparaciones);
			pnlCard.add(Clientes , pnlClientes);
			pnlCard.add(Recambios , pnlRecambios);
		} 

		//BOTONES DE CLIENTES
		if(btnAddCli.equals(ae.getSource())) {
			new AddCli(user);
		}
		else if(btnModCli.equals(ae.getSource())) {
			new ModCliList(user);
		}
		else if(btnElCli.equals(ae.getSource())) {
			new ElCliList(user);
		}
		else if (btnConCli.equals(ae.getSource())) {
			new ConCliList();
			Calendar horaFecha = Calendar.getInstance();
			int hora,minutos,dia,mes,anyo;
			hora = horaFecha.get(Calendar.HOUR_OF_DAY);
			minutos = horaFecha.get(Calendar.MINUTE);
			dia = horaFecha.get(Calendar.DAY_OF_MONTH);
			mes = horaFecha.get(Calendar.MONTH)+1;
			anyo = horaFecha.get(Calendar.YEAR);
			try {
				FileWriter fw = new FileWriter("movimientos.log", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter outPut = new PrintWriter(bw);
				outPut.print("["+dia+"/"+mes+"/"+anyo+"]["+hora+":"+minutos+"] "+"["+user+"]"+"["+"SELECT * FROM CLIENTES"+"]"+"\n");
				outPut.close();
				bw.close();
				fw.close();
			} catch(IOException ioe) {
				System.out.print("Error");
			}
		}
		//BOTONES DE RECAMBIOS
		if(btnAddRec.equals(ae.getSource())) {
			new AddRec(user);
		}  else if(btnModRec.equals(ae.getSource())) {
			new ModRecList(user);
		}
		else if(btnElRec.equals(ae.getSource())) {
			new ElRecList(user);
		}
		else if (btnConRec.equals(ae.getSource())) {
			new ConRecList();
			Calendar horaFecha = Calendar.getInstance();
			int hora,minutos,dia,mes,anyo;
			hora = horaFecha.get(Calendar.HOUR_OF_DAY);
			minutos = horaFecha.get(Calendar.MINUTE);
			dia = horaFecha.get(Calendar.DAY_OF_MONTH);
			mes = horaFecha.get(Calendar.MONTH)+1;
			anyo = horaFecha.get(Calendar.YEAR);
			try {
				FileWriter fw = new FileWriter("movimientos.log", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter outPut = new PrintWriter(bw);
				outPut.print("["+dia+"/"+mes+"/"+anyo+"]["+hora+":"+minutos+"] "+"["+user+"]"+"["+"SELECT * FROM RECAMBIOS"+"]"+"\n");
				outPut.close();
				bw.close();
				fw.close();
			} catch(IOException ioe) {
				System.out.print("Error");
			}
		}
		//BOTONES DE REPARACIONES
		if(btnAddRep.equals(ae.getSource())) {
			new AddRep(user);
		} else if(btnModRep.equals(ae.getSource())) {
			new ModRepList(user);
		} else if(btnElRep.equals(ae.getSource())) {
			new ElRepList(user);
		}else if (btnConRep.equals(ae.getSource())) {
			new ConRepList();
			Calendar horaFecha = Calendar.getInstance();
			int hora,minutos,dia,mes,anyo;
			hora = horaFecha.get(Calendar.HOUR_OF_DAY);
			minutos = horaFecha.get(Calendar.MINUTE);
			dia = horaFecha.get(Calendar.DAY_OF_MONTH);
			mes = horaFecha.get(Calendar.MONTH)+1;
			anyo = horaFecha.get(Calendar.YEAR);
			try {
				FileWriter fw = new FileWriter("movimientos.log", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter outPut = new PrintWriter(bw);
				outPut.print("["+dia+"/"+mes+"/"+anyo+"]["+hora+":"+minutos+"] "+"["+user+"]"+"["+"SELECT * FROM REPARACIONES"+"]"+"\n");
				outPut.close();
				bw.close();
				fw.close();
			} catch(IOException ioe) {
				System.out.print("Error");
			}
		}
		//BOTONES DE REPARACIONES
		if(btnAddFac.equals(ae.getSource())) {
			new AddFac(user);
		}else if (btnConFac.equals(ae.getSource())) {
			new ConFacList();
			Calendar horaFecha = Calendar.getInstance();
			int hora,minutos,dia,mes,anyo;
			hora = horaFecha.get(Calendar.HOUR_OF_DAY);
			minutos = horaFecha.get(Calendar.MINUTE);
			dia = horaFecha.get(Calendar.DAY_OF_MONTH);
			mes = horaFecha.get(Calendar.MONTH)+1;
			anyo = horaFecha.get(Calendar.YEAR);
			try {
				FileWriter fw = new FileWriter("movimientos.log", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter outPut = new PrintWriter(bw);
				outPut.print("["+dia+"/"+mes+"/"+anyo+"]["+hora+":"+minutos+"] "+"["+user+"]"+"["+"SELECT * FROM FACTURAS"+"]"+"\n");
				outPut.close();
				bw.close();
				fw.close();
			} catch(IOException ioe) {
				System.out.print("Error");
			}
		}

		if(mniOtrosSalir.equals(ae.getSource())) {
			ventana.setVisible(false);
			new Login();
		}
		if(mniOtrosAyuda.equals(ae.getSource())) {
			new Ayuda();
		} else if(mniOtrosSalir.equals(ae.getSource())) {
			ventana.setVisible(false);
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		System.exit(0);
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {}
	@Override
	public void windowDeiconified(WindowEvent arg0) {}
	@Override
	public void windowIconified(WindowEvent arg0) {}
	@Override
	public void windowOpened(WindowEvent arg0) {}

}
