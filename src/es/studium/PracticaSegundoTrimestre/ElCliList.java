package es.studium.PracticaSegundoTrimestre;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ElCliList implements WindowListener, ActionListener, TextListener{

	JFrame ventanaElCliList = new JFrame ("Buscar cliente para eliminar");
	JLabel lblBuscarCli = new JLabel ("Buscar apellidos de cliente:");
	JTextField txtBuscarCli = new JTextField(10);
	
	String clientes[] = { "Cristian", "Julian", "Manuel","Pedro","Joaquin", "Julian", "Julian", "Julian", "Julian",};
	JList<String> ListaCli = new JList<String>(clientes);
	
	JButton btnBuscar = new JButton("Buscar");
	JButton btnSeleccionar = new JButton("Seleccionar");
	JDialog dlgElCli = new JDialog(ventanaElCliList, "Eliminar Cliente");
	JLabel lblEliminar = new JLabel("¿Está seguro de eliminar al cliente?");
	JButton btnEliminar = new JButton ("Eliminar");
	JButton btnCancelar = new JButton ("Cancelar");
	JDialog dlgEliminado = new JDialog(dlgElCli, "Cliente Eliminado");
	JLabel lblEliminado = new JLabel("Cliente Eliminado");
	
	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();
	
	public ElCliList() {
		ventanaElCliList.setLayout(new GridLayout(3,1));
		ventanaElCliList.setLocationRelativeTo(null);
		ventanaElCliList.setSize(400,300);
		
		pnl1.add(lblBuscarCli);
		pnl1.add(txtBuscarCli);
		pnl1.add(btnBuscar);
		pnl2.add(ListaCli);
		pnl3.add(btnSeleccionar);
		btnSeleccionar.addActionListener(this);
		
		ventanaElCliList.add(pnl1);
		ventanaElCliList.add(pnl2);
		ventanaElCliList.add(pnl3);
		
		ventanaElCliList.addWindowListener(this);
		ventanaElCliList.setVisible(true);
		
		dlgElCli.setLocationRelativeTo(null);
		dlgElCli.setLayout(new FlowLayout());
		dlgElCli.setSize(230,100);
		dlgElCli.add(lblEliminar);
		dlgElCli.add(btnEliminar);
		btnEliminar.addActionListener(this);
		dlgElCli.add(btnCancelar);
		dlgElCli.addWindowListener(this);
		dlgElCli.setVisible(false);
		
		dlgEliminado.setLocationRelativeTo(null);
		dlgEliminado.setLayout(new FlowLayout());
		dlgEliminado.setSize(230,100);
		dlgEliminado.add(lblEliminado);
		dlgEliminado.addWindowListener(this);
		dlgEliminado.setVisible(false);
		
	}
	public static void main(String[] args) {
		
		
	}
	@Override
	public void textValueChanged(TextEvent arg0) {}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		
		if(btnSeleccionar.equals(ae.getSource())) 
		{
			dlgElCli.setVisible(true);
		} else if(btnEliminar.equals(ae.getSource())) 
		{
			dlgEliminado.setVisible(true);
		} else if (btnCancelar.equals(ae.getSource())) {
			ventanaElCliList.setVisible(false);
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		if(ventanaElCliList.isActive()) {
			ventanaElCliList.setVisible(false);
		}else {
			//System.exit(0);
		}
		if(dlgElCli.isActive()) {
			dlgElCli.setVisible(false);
		}
		if(dlgEliminado.isActive()) {
			dlgEliminado.setVisible(false);
			dlgElCli.setVisible(false);
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
