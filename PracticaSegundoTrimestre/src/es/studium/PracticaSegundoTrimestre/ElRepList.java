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

public class ElRepList implements WindowListener, ActionListener, TextListener{
	JFrame ventanaElRepList = new JFrame ("Buscar reparación para eliminar");
	JLabel lblBuscarRep = new JLabel ("Buscar avería :");
	JTextField txtBuscarRep = new JTextField(10);
	List ListaRep = new List(10, false);
	JButton btnBuscar = new JButton("Buscar");
	JButton btnSeleccionar = new JButton("Seleccionar");
	
	JDialog dlgElRep = new JDialog(ventanaElRepList, "Eliminar la reparación");
	JLabel lblEliminar = new JLabel("¿Está seguro de eliminar la reparación?");
	JButton btnEliminar = new JButton ("Eliminar");
	JButton btnCancelar = new JButton ("Cancelar");
	JDialog dlgEliminado = new JDialog(dlgElRep, "Reparación Eliminada");
	JLabel lblEliminado = new JLabel("Reparación Eliminada");
	
	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();
	
	public ElRepList() 
	{
		ventanaElRepList.setLayout(new GridLayout(3,1));
		ventanaElRepList.setLocationRelativeTo(null);
		ventanaElRepList.setSize(400,300);
		pnl1.setLayout(new FlowLayout());
		pnl2.setLayout(new FlowLayout());
		pnl3.setLayout(new FlowLayout());
		
		pnl1.add(lblBuscarRep);
		pnl1.add(txtBuscarRep);
		pnl1.add(btnBuscar);
		pnl2.add(ListaRep);
		pnl3.add(btnSeleccionar);
		btnSeleccionar.addActionListener(this);
		
		ventanaElRepList.add(pnl1);
		ventanaElRepList.add(pnl2);
		ventanaElRepList.add(pnl3);

		dlgElRep.setLocationRelativeTo(null);
		dlgElRep.setLayout(new FlowLayout());
		dlgElRep.setSize(250,120);
		dlgElRep.add(lblEliminar);
		dlgElRep.add(btnEliminar);
		btnEliminar.addActionListener(this);
		dlgElRep.add(btnCancelar);
		dlgElRep.addWindowListener(this);
		dlgElRep.setVisible(false);
		
		dlgEliminado.setLocationRelativeTo(null);
		dlgEliminado.setLayout(new FlowLayout());
		dlgEliminado.setSize(250,120);
		dlgEliminado.add(lblEliminado);
		dlgEliminado.addWindowListener(this);
		dlgEliminado.setVisible(false);
		
		
		ventanaElRepList.addWindowListener(this);
		ventanaElRepList.setVisible(true);
	}
	@Override
	public void textValueChanged(TextEvent arg0) {}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(btnSeleccionar.equals(ae.getSource())) 
		{
			dlgElRep.setVisible(true);
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
		if(ventanaElRepList.isActive()) {
			ventanaElRepList.setVisible(false);
		}else {
			//System.exit(0);
		} if(dlgElRep.isActive()) {
			dlgElRep.setVisible(false);
		}
		if(dlgEliminado.isActive()) {
			dlgEliminado.setVisible(false);
			dlgElRep.setVisible(false);
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
