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

public class ModRecList implements WindowListener, ActionListener, TextListener{
	
	JFrame ventanaModRecList = new JFrame ("Buscar recambio para modificar");
	JLabel lblBuscarRec = new JLabel ("Buscar descripción de recambio:");
	JTextField txtBuscarRec = new JTextField(10);
	
	String recambios[] = {"Bujía", "Rueda", "Retrovisor","Paragolpes","Pastilla de Freno"};
	JList<String> ListaRec = new JList<String>(recambios);
	
	JButton btnBuscar = new JButton("Buscar");
	JButton btnSeleccionar = new JButton("Seleccionar");
	
	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();
	

	public ModRecList() {
		ventanaModRecList.setLayout(new GridLayout(3,1));
		ventanaModRecList.setLocationRelativeTo(null);
		ventanaModRecList.setSize(400,300);
		
		pnl1.add(lblBuscarRec);
		pnl1.add(txtBuscarRec);
		pnl1.add(btnBuscar);
		pnl2.add(ListaRec);
		pnl3.add(btnSeleccionar);
		btnSeleccionar.addActionListener(this);
		
		ventanaModRecList.add(pnl1);
		ventanaModRecList.add(pnl2);
		ventanaModRecList.add(pnl3);
		
		ventanaModRecList.addWindowListener(this);
		ventanaModRecList.setVisible(true);
	}
	public static void main(String[] args) {}
	@Override
	public void textValueChanged(TextEvent arg0) {}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		
		if(btnSeleccionar.equals(ae.getSource())) {
			new ModRec();
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		if(ventanaModRecList.isActive()) {
			ventanaModRecList.setVisible(false);
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
