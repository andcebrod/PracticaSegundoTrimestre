package es.studium.PracticaSegundoTrimestre;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class ElRecList implements WindowListener, ActionListener,TextListener {
	
	JFrame ventanaElRecList = new JFrame ("Buscar recambio para eliminar");
	JLabel lblBuscarElRec = new JLabel ("Buscar descripción de recambio:");
	JTextField txtBuscarRec = new JTextField(10);
	List ListaRec = new List(10, false);
	JButton btnBuscar = new JButton("Buscar");
	JButton btnSeleccionar = new JButton("Seleccionar");
	
	JDialog dlgElRec = new JDialog(ventanaElRecList, "Eliminar recambio");
	JLabel lblEliminar = new JLabel("¿Está seguro de eliminar el recambio?");
	JButton btnEliminar = new JButton ("Eliminar");
	JButton btnCancelar = new JButton ("Cancelar");
	JDialog dlgEliminado = new JDialog(dlgElRec, "Recambio Eliminado");
	JLabel lblEliminado = new JLabel("Recambio Eliminado");
	
	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();
	
	public ElRecList() 
	{
		ventanaElRecList.setLayout(new GridLayout(3,1));
		ventanaElRecList.setLocationRelativeTo(null);
		ventanaElRecList.setSize(400,300);
		
		pnl1.add(lblBuscarElRec);
		pnl1.add(txtBuscarRec);
		pnl1.add(btnBuscar);
		pnl2.add(ListaRec);
		pnl3.add(btnSeleccionar);
		
		ventanaElRecList.add(pnl1);
		ventanaElRecList.add(pnl2);
		ventanaElRecList.add(pnl3);

		
		btnSeleccionar.addActionListener(this);
		

		dlgElRec.setLocationRelativeTo(null);
		dlgElRec.setLayout(new FlowLayout());
		dlgElRec.setSize(200,100);
		dlgElRec.add(lblEliminar);
		dlgElRec.add(btnEliminar);
		btnEliminar.addActionListener(this);
		dlgElRec.add(btnCancelar);
		dlgElRec.addWindowListener(this);
		dlgElRec.setVisible(false);
		
		dlgEliminado.setLocationRelativeTo(null);
		dlgEliminado.setLayout(new FlowLayout());
		dlgEliminado.setSize(200,100);
		dlgEliminado.add(lblEliminado);
		dlgEliminado.addWindowListener(this);
		dlgEliminado.setVisible(false);
		
		ventanaElRecList.addWindowListener(this);
		ventanaElRecList.setVisible(true);
	}
	
	public static void main(String[] args) {new ElRecList();}
	@Override
	public void textValueChanged(TextEvent arg0) {}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		
		if(btnSeleccionar.equals(ae.getSource())) {
			dlgElRec.setVisible(true);
		} else if(btnEliminar.equals(ae.getSource())) 
		{
			dlgEliminado.setVisible(true);
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		if(ventanaElRecList.isActive()) {
			ventanaElRecList.setVisible(false);
		}else {
			//System.exit(0);
		}
		if(dlgElRec.isActive()) {
			dlgElRec.setVisible(false);
		}
		if(dlgEliminado.isActive()) {
			dlgEliminado.setVisible(false);
			dlgElRec.setVisible(false);
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
