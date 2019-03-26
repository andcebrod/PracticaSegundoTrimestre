package es.studium.PracticaSegundoTrimestre;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class ConRep implements WindowListener{

	JFrame ventanaConRep = new JFrame ("Consulta de Reparación");
	JLabel lblAveriaRep = new JLabel ("Avería:");
	JLabel lblFechaEntradaRep = new JLabel ("Fecha de Entrada:");
	JLabel lblFechaSalidaRep = new JLabel ("Fecha de Salida:");
	JLabel lblReparadoRep = new JLabel ("Reparado:");


	JTextField txtAveriaRep = new JTextField(10);
	JTextField txtFechaEntradaRep = new JTextField(10);
	JTextField txtFechaSalidaRep = new JTextField(10);
	
	ButtonGroup chkReparadoRep = new ButtonGroup();
	JRadioButton chkSiRep = new JRadioButton("Sí", false);
	JRadioButton chkNoRep = new JRadioButton("No", true);
	
	Panel pnlPanel = new Panel();
	Panel pnlPanel2 = new Panel();
	Panel pnlPanel3 = new Panel();
	Panel pnlPanel4 = new Panel();
	
	
	public ConRep() 
	{
		ventanaConRep.setLayout(new GridLayout(4,2));
		ventanaConRep.setLocationRelativeTo(null);
		ventanaConRep.setSize(600,300);
		
		pnlPanel.setLayout(new FlowLayout());
		pnlPanel2.setLayout(new FlowLayout());
		pnlPanel3.setLayout(new FlowLayout());
		pnlPanel4.setLayout(new FlowLayout());
		
		pnlPanel.add(lblAveriaRep);
		pnlPanel.add(txtAveriaRep);
		ventanaConRep.add(pnlPanel);

		pnlPanel2.add(lblFechaEntradaRep);
		pnlPanel2.add(txtFechaEntradaRep);
		ventanaConRep.add(pnlPanel2);

		pnlPanel3.add(lblFechaSalidaRep);
		pnlPanel3.add(txtFechaSalidaRep);
		ventanaConRep.add(pnlPanel3);
		
		chkReparadoRep.add(chkSiRep);
		chkReparadoRep.add(chkNoRep);
		
		pnlPanel4.add(lblReparadoRep);
		pnlPanel4.add(chkSiRep);
		pnlPanel4.add(chkNoRep);
		ventanaConRep.add(pnlPanel4);
		
		
		ventanaConRep.addWindowListener(this);
		ventanaConRep.setVisible(true);
	}
	public static void main(String[] args) {}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		if(ventanaConRep.isActive()) {
			ventanaConRep.setVisible(false);
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
