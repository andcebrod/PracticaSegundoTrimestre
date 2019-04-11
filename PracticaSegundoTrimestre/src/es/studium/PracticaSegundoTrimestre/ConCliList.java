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
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ConCliList implements WindowListener, ActionListener, TextListener{
	JFrame ventanaConCliList = new JFrame ("Buscar cliente");
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
	

	public ConCliList() {
		ventanaConCliList.setLayout(new GridLayout(3,1));
		ventanaConCliList.setLocationRelativeTo(null);
		ventanaConCliList.setSize(400,300);
		
		pnl1.add(lblBuscarCli);
		pnl1.add(txtBuscarCli);
		pnl1.add(btnBuscar);
		pnl2.add(ListaCli);
		pnl3.add(btnSeleccionar);
		
		ventanaConCliList.add(pnl1);
		ventanaConCliList.add(pnl2);
		ventanaConCliList.add(pnl3);
		
		btnSeleccionar.addActionListener(this);
		
		ventanaConCliList.addWindowListener(this);
		ventanaConCliList.setVisible(true);
	}
	public static void main(String[] args) {
		
	}
	@Override
	public void textValueChanged(TextEvent arg0) {}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		
		if(btnSeleccionar.equals(ae.getSource())) {
			new ConCli();
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		if(ventanaConCliList.isActive()) {
			ventanaConCliList.setVisible(false);
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
