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

public class ConFacList implements WindowListener, ActionListener, TextListener{
	
	JFrame ventanaConFacList = new JFrame ("Buscar factura");
	JLabel lblBuscarFac = new JLabel ("Buscar id de factura:");
	JTextField txtBuscarFac = new JTextField(10);
	List ListaFac = new List(10, false);
	JButton btnBuscar = new JButton("Buscar");
	JButton btnSeleccionar = new JButton("Seleccionar");

	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();
	
	public ConFacList() 
	{
		ventanaConFacList.setLayout(new GridLayout(3,1));
		ventanaConFacList.setLocationRelativeTo(null);
		ventanaConFacList.setSize(400,300);
		
		pnl1.add(lblBuscarFac);
		pnl1.add(txtBuscarFac);
		pnl1.add(btnBuscar);
		pnl2.add(ListaFac);
		pnl3.add(btnSeleccionar);
		
		ventanaConFacList.add(pnl1);
		ventanaConFacList.add(pnl2);
		ventanaConFacList.add(pnl3);
		
		btnSeleccionar.addActionListener(this);
		
		ventanaConFacList.addWindowListener(this);
		ventanaConFacList.setVisible(true);
	}

	@Override
	public void textValueChanged(TextEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(btnSeleccionar.equals(ae.getSource())) {
			new ConFac();
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
		if(ventanaConFacList.isActive()) {
			ventanaConFacList.setVisible(false);
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


