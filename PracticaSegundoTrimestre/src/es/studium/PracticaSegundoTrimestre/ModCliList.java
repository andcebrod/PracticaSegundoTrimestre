package es.studium.PracticaSegundoTrimestre;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;


public class ModCliList implements WindowListener, ActionListener, TextListener{
	JFrame ventanaModCliList = new JFrame ("Buscar cliente para modificar");
	JLabel lblBuscarCli = new JLabel ("Buscar apellidos de cliente:");
	JTextField txtBuscarCli = new JTextField(10);
	String clientes[] = { "Cristian", "Julian", "Manuel","Pedro","Joaquin", "Julian", "Julian", "Julian", "Julian",};
	JList<String> ListaCli = new JList<String>(clientes);
	JScrollPane scrollLista= new JScrollPane();
	
	JButton btnBuscar = new JButton("Buscar");
	JButton btnSeleccionar = new JButton("Seleccionar");
	
	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();
	
	

	public ModCliList() {
		ventanaModCliList.setLayout(new GridLayout(3,1));
		ventanaModCliList.setLocationRelativeTo(null);
		ventanaModCliList.setSize(400,300);
		
		pnl1.add(lblBuscarCli);
		pnl1.add(txtBuscarCli);
		pnl1.add(btnBuscar);
		ventanaModCliList.add(pnl1);
		
		ListaCli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollLista.setViewportView(ListaCli);
		
		pnl2.add(scrollLista);
		pnl2.add(ListaCli);
		
		ventanaModCliList.add(pnl2);
		pnl3.add(btnSeleccionar);
		btnSeleccionar.addActionListener(this);
		ventanaModCliList.add(pnl3);
		ventanaModCliList.addWindowListener(this);
		ventanaModCliList.setVisible(true);
	}
	public static void main(String[] args) {new ModCliList();
	}
	@Override
	public void textValueChanged(TextEvent arg0) {}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(btnSeleccionar.equals(ae.getSource())) {
			new ModCli();
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		if(ventanaModCliList.isActive()) {
			ventanaModCliList.setVisible(false);
		}else {
			//System.exit(0);
		}
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
