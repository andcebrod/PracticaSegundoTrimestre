package es.studium.PracticaSegundoTrimestre;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

public class ConFac implements WindowListener{
	JFrame ventanaConFac = new JFrame ("Añadir Factura");
	JLabel lblFecha = new JLabel ("Fecha Factura :");
	JLabel lblClientes = new JLabel ("Cliente:");
	JLabel lblReparaciones = new JLabel ("Reparación:");
	
	JTextField txtClientes = new JTextField(10);
	JTextField txtReparaciones = new JTextField(10);
	JTextField txtFecha = new JTextField(10);
	
	JDialog dlgExitoAddFac = new JDialog(ventanaConFac, "Factura creada");
	JLabel lblExito = new JLabel("Factura creada con éxito");
	
	JPanel pnlPanel = new JPanel();
	JPanel pnlPanel2 = new JPanel();
	JPanel pnlPanel3 = new JPanel();
	
	public ConFac() {
		ventanaConFac.setLayout(new GridLayout(3,1));
		ventanaConFac.setLocationRelativeTo(null);
		ventanaConFac.setSize(250,150);
		
		pnlPanel.setLayout(new FlowLayout());
		pnlPanel2.setLayout(new FlowLayout());
		pnlPanel3.setLayout(new FlowLayout());
		
		pnlPanel.add(lblFecha);
		pnlPanel.add(txtFecha);
		ventanaConFac.add(pnlPanel);
		
		pnlPanel2.add(lblClientes);
		pnlPanel2.add(txtClientes);
		ventanaConFac.add(pnlPanel2);
		
		pnlPanel3.add(lblReparaciones);
		pnlPanel3.add(txtReparaciones);
		ventanaConFac.add(pnlPanel3);
		
		ventanaConFac.addWindowListener(this);
		ventanaConFac.setVisible(true);
		
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
		if(ventanaConFac.isActive()) {
			ventanaConFac.setVisible(false);
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


