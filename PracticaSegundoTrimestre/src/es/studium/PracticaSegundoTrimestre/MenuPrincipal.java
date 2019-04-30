package es.studium.PracticaSegundoTrimestre;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

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
	JButton btnModFac = new JButton("Modifcar Facturas");
	JButton btnElFac = new JButton("Eliminar Facturas");
	JButton btnConFac = new JButton("Consultar Facturas");



	public MenuPrincipal(String usuario) {
		user = usuario;
		ventana.setLocationRelativeTo(null);
		ventana.setSize(830,300);
		ventana.setLayout(new BorderLayout());

		ventana.setJMenuBar(barraMenu);
		menuOtros.add(mniOtrosAyuda);
		mniOtrosAyuda.addActionListener(this);
		// Añadimos un separador
		menuOtros.addSeparator();
		menuOtros.add(mniOtrosSalir);
		mniOtrosSalir.addActionListener(this);
		barraMenu.add(menuOtros);

		Lista.add(Clientes);
		Lista.add(Recambios);
		Lista.add(Reparaciones);
		Lista.add(Facturas);
		pnlLista.add(Lista);
		ventana.add("West", pnlLista);
		pnlCard.setLayout(new CardLayout() );

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
		pnlFacturas.add(btnModFac);
		pnlFacturas.add(btnElFac);
		pnlFacturas.add(btnConFac);

		btnAddFac.addActionListener(this);
		btnModFac.addActionListener(this);
		btnElFac.addActionListener(this);
		btnConFac.addActionListener(this);

		pnlCard.add(Clientes , pnlClientes);
		pnlCard.add(Recambios , pnlRecambios);
		pnlCard.add(Reparaciones , pnlReparaciones);
		pnlCard.add(Facturas , pnlFacturas);
		ventana.add("East",pnlCard);
		Lista.addActionListener(this);
		ventana.addWindowListener(this);
		ventana.setVisible(true);
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
			new ModCliList();
		}
		else if(btnElCli.equals(ae.getSource())) {
			new ElCliList(user);
		}
		else if (btnConCli.equals(ae.getSource())) {
			new ConCliList();
		}
		//BOTONES DE RECAMBIOS
		if(btnAddRec.equals(ae.getSource())) {
			new AddRec(user);
		}  else if(btnModRec.equals(ae.getSource())) {
			new ModRecList();
		}
		else if(btnElRec.equals(ae.getSource())) {
			new ElRecList(user);
		}
		else if (btnConRec.equals(ae.getSource())) {
			new ConRecList();
		}
		//BOTONES DE REPARACIONES
		if(btnAddRep.equals(ae.getSource())) {
			new AddRep(user);
		} else if(btnModRep.equals(ae.getSource())) {
			new ModRepList();
		} else if(btnElRep.equals(ae.getSource())) {
			new ElRepList(user);
		}else if (btnConRep.equals(ae.getSource())) {
			new ConRepList();
		}
		//BOTONES DE REPARACIONES
		if(btnAddFac.equals(ae.getSource())) {
			new AddFac(user);
		} else if(btnModFac.equals(ae.getSource())) {
			new ModFacList();
		} else if(btnElFac.equals(ae.getSource())) {
			new ElFacList(user);
		}else if (btnConFac.equals(ae.getSource())) {
			new ConFacList();
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
	public void windowClosing(WindowEvent arg0) {
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
