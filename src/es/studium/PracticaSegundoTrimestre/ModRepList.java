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

public class ModRepList implements WindowListener, ActionListener, TextListener{
	JFrame ventanaModRepList = new JFrame ("Buscar reparación para modificar");
	JLabel lblBuscarCli = new JLabel ("Buscar avería :");
	JTextField txtBuscarRep = new JTextField(10);
	List ListaRep = new List(10, false);
	JButton btnBuscar = new JButton("Buscar");
	JButton btnSeleccionar = new JButton("Seleccionar");
	
	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();

	public ModRepList() {
		ventanaModRepList.setLayout(new GridLayout(3,1));
		ventanaModRepList.setLocationRelativeTo(null);
		ventanaModRepList.setSize(400,300);
		pnl1.setLayout(new FlowLayout());
		pnl2.setLayout(new FlowLayout());
		pnl3.setLayout(new FlowLayout());
		
		pnl1.add(lblBuscarCli);
		pnl1.add(txtBuscarRep);
		pnl1.add(btnBuscar);
		
		pnl2.add(ListaRep);
		
		pnl3.add(btnSeleccionar);
		btnSeleccionar.addActionListener(this);
		
		ventanaModRepList.add(pnl1);
		ventanaModRepList.add(pnl2);
		ventanaModRepList.add(pnl3);
		
		ventanaModRepList.addWindowListener(this);
		ventanaModRepList.setVisible(true);
	}
	@Override
	public void textValueChanged(TextEvent arg0) {}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(btnSeleccionar.equals(ae.getSource())) 
		{
			new ModRep();
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		if(ventanaModRepList.isActive()) {
			ventanaModRepList.setVisible(false);
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
