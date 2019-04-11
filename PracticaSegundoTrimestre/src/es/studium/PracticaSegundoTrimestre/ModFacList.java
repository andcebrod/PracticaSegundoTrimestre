package es.studium.PracticaSegundoTrimestre;

import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class ModFacList implements WindowListener, ActionListener, TextListener {
	
	JFrame ventanaModFacList = new JFrame ("Buscar factura para modificar");
	JLabel lblBuscarCli = new JLabel ("Buscar id de Factura:");
	JTextField txtBuscarCli = new JTextField(3);
	List ListaFac = new List(10, false);
	JButton btnBuscar = new JButton("Buscar");
	JButton btnSeleccionar = new JButton("Seleccionar");
	
	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();
	
	
	public ModFacList ()
	{
		ventanaModFacList.setLayout(new GridLayout(3,1));
		ventanaModFacList.setLocationRelativeTo(null);
		ventanaModFacList.setSize(400,300);
		
		pnl1.add(lblBuscarCli);
		pnl1.add(txtBuscarCli);
		pnl1.add(btnBuscar);
		pnl2.add(ListaFac);
		pnl3.add(btnSeleccionar);
		
		ventanaModFacList.add(pnl1);
		ventanaModFacList.add(pnl2);
		ventanaModFacList.add(pnl3);
		
		btnSeleccionar.addActionListener(this);
		
		ventanaModFacList.addWindowListener(this);
		ventanaModFacList.setVisible(true);
	}

	@Override
	public void textValueChanged(TextEvent te) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(btnSeleccionar.equals(ae.getSource())) {
			new ModFac();
		}
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if(ventanaModFacList.isActive()) {
			ventanaModFacList.setVisible(false);
		}else {
			//System.exit(0);
		}
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
