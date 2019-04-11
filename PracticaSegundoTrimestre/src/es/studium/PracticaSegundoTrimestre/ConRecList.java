package es.studium.PracticaSegundoTrimestre;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConRecList implements WindowListener, ActionListener, TextListener {
	
	JFrame ventanaConRecList = new JFrame ("Buscar recambio");
	JLabel lblBuscarRec = new JLabel ("Buscar descripción del recambio:");
	JTextField txtBuscarRec = new JTextField(10);
	
	String recambios[] = {"Bujía", "Rueda", "Retrovisor","Paragolpes","Pastilla de Freno"};
	JList<String> ListaRec = new JList<String>(recambios);
	
	JButton btnBuscar = new JButton("Buscar");
	JButton btnSeleccionar = new JButton("Seleccionar");
	
	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();
	
	public ConRecList() 
	{
		ventanaConRecList.setLayout(new GridLayout(3,1));
		ventanaConRecList.setLocationRelativeTo(null);
		ventanaConRecList.setSize(400,300);
		
		pnl1.add(lblBuscarRec);
		pnl1.add(txtBuscarRec);
		pnl1.add(btnBuscar);
		pnl2.add(ListaRec);
		pnl3.add(btnSeleccionar);
		btnSeleccionar.addActionListener(this);
		
		ventanaConRecList.add(pnl1);
		ventanaConRecList.add(pnl2);
		ventanaConRecList.add(pnl3);
		
		ventanaConRecList.addWindowListener(this);
		ventanaConRecList.setVisible(true);
		
	}
	
public static void main(String[] args) {
		
		new ConRecList();
	}
	@Override
	public void textValueChanged(TextEvent arg0) {}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		
		if(btnSeleccionar.equals(ae.getSource())) {
			new ConRec();
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		if(ventanaConRecList.isActive()) {
			ventanaConRecList.setVisible(false);
		}else {
			//System.exit(0);
		}
		
	}
		
	
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		
		
	}

}
