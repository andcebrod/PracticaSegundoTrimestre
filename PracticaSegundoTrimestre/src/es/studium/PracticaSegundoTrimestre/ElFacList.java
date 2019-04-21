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

public class ElFacList implements WindowListener, ActionListener, TextListener{
	
	JFrame ventanaElFacList = new JFrame ("Buscar factura para eliminar");
	JLabel lblBuscarFac = new JLabel ("Buscar id de factura:");
	JTextField txtBuscarFac = new JTextField(10);
	List ListaFac = new List(10, false);
	JButton btnBuscar = new JButton("Buscar");
	JButton btnSeleccionar = new JButton("Seleccionar");
	JDialog dlgElFac = new JDialog(ventanaElFacList, "Eliminar Factura");
	JLabel lblEliminar = new JLabel("¿Está seguro de eliminar la factura?");
	JButton btnEliminar = new JButton ("Eliminar");
	JButton btnCancelar = new JButton ("Cancelar");
	JDialog dlgEliminado = new JDialog(dlgElFac, "Factura Eliminada");
	JLabel lblEliminado = new JLabel("Factura Eliminada");
	
	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();
	
	public ElFacList () 
	{
		ventanaElFacList.setLayout(new GridLayout(3,1));
		ventanaElFacList.setLocationRelativeTo(null);
		ventanaElFacList.setSize(400,300);
		
		pnl1.add(lblBuscarFac);
		pnl1.add(txtBuscarFac);
		pnl1.add(btnBuscar);
		pnl2.add(ListaFac);
		pnl3.add(btnSeleccionar);
		btnSeleccionar.addActionListener(this);
		
		ventanaElFacList.add(pnl1);
		ventanaElFacList.add(pnl2);
		ventanaElFacList.add(pnl3);
		
		ventanaElFacList.addWindowListener(this);
		ventanaElFacList.setVisible(true);
		
		dlgElFac.setLocationRelativeTo(null);
		dlgElFac.setLayout(new FlowLayout());
		dlgElFac.setSize(220,100);
		dlgElFac.add(lblEliminar);
		dlgElFac.add(btnEliminar);
		btnEliminar.addActionListener(this);
		dlgElFac.add(btnCancelar);
		dlgElFac.addWindowListener(this);
		dlgElFac.setVisible(false);
		
		dlgEliminado.setLocationRelativeTo(null);
		dlgEliminado.setLayout(new FlowLayout());
		dlgEliminado.setSize(220,100);
		dlgEliminado.add(lblEliminado);
		dlgEliminado.addWindowListener(this);
		dlgEliminado.setVisible(false);
		
	}

	@Override
	public void textValueChanged(TextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		
		if(btnSeleccionar.equals(ae.getSource())) 
		{
			dlgElFac.setVisible(true);
		} else if(btnEliminar.equals(ae.getSource())) 
		{
			dlgEliminado.setVisible(true);
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		if(ventanaElFacList.isActive()) {
			ventanaElFacList.setVisible(false);
		}else {
			//System.exit(0);
		}
		if(dlgElFac.isActive()) {
			dlgElFac.setVisible(false);
		}
		if(dlgEliminado.isActive()) {
			dlgEliminado.setVisible(false);
			dlgElFac.setVisible(false);
			
		}
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
