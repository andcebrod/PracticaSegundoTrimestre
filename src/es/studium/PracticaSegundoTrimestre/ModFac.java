package es.studium.PracticaSegundoTrimestre;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

public class ModFac implements WindowListener, ActionListener, TextListener{
	
	JFrame ventanaModFac = new JFrame ("Modificar Factura:");
	JLabel lblFecha = new JLabel ("Fecha Factura :");
	JLabel lblClientes = new JLabel ("Cliente:");
	JLabel lblReparaciones = new JLabel ("Reparación:");
	
	JComboBox ListaClientes = new JComboBox();
	JComboBox ListaReparaciones = new JComboBox();
	JTextField txtFecha = new JTextField(10);
	

	JButton btnModificar = new JButton("Modificar Factura");
	JButton btnLimpiar = new JButton("Limpiar");
	
	JDialog dlgExitoModFac = new JDialog(ventanaModFac, "Factura modificada");
	JLabel lblExito = new JLabel("Factura modificada con éxito");
	
	JPanel pnlPanel = new JPanel();
	JPanel pnlPanel2 = new JPanel();
	JPanel pnlPanel3 = new JPanel();
	JPanel pnlPanel4 = new JPanel();
	JPanel pnlPanel5 = new JPanel();
	
	public ModFac() 
	{
		ventanaModFac.setLayout(new GridLayout(5,2));
		ventanaModFac.setLocationRelativeTo(null);
		ventanaModFac.setSize(400,300);
		
		pnlPanel.setLayout(new FlowLayout());
		pnlPanel2.setLayout(new FlowLayout());
		pnlPanel3.setLayout(new FlowLayout());
		pnlPanel4.setLayout(new FlowLayout());
		

		pnlPanel.add(lblFecha);
		pnlPanel.add(txtFecha);
		ventanaModFac.add(pnlPanel);
		
		pnlPanel2.add(lblClientes);
		pnlPanel2.add(ListaClientes);
		ventanaModFac.add(pnlPanel2);
		
		pnlPanel3.add(lblReparaciones);
		pnlPanel3.add(ListaReparaciones);
		ventanaModFac.add(pnlPanel3);
		
		pnlPanel4.add(btnModificar);
		btnModificar.addActionListener(this);
		pnlPanel4.add(btnLimpiar);
		btnLimpiar.addActionListener(this);
		ventanaModFac.add(pnlPanel4);
		
		ventanaModFac.addWindowListener(this);
		ventanaModFac.setVisible(true);
		
		dlgExitoModFac.setLocationRelativeTo(null);
		dlgExitoModFac.setSize(190,90);
		dlgExitoModFac.add(lblExito);
		dlgExitoModFac.addWindowListener(this);
		dlgExitoModFac.setVisible(false);
		dlgExitoModFac.setLayout(new FlowLayout());
	}

	@Override
	public void textValueChanged(TextEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (btnModificar.equals(ae.getSource())) {
			dlgExitoModFac.setVisible(true);
		} else if (btnLimpiar.equals(ae.getSource())) {
			txtFecha.selectAll();
			txtFecha.setText("");
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
		if(ventanaModFac.isActive()) {
			ventanaModFac.setVisible(false);
		}else {
			//System.exit(0);
		}
		if(dlgExitoModFac.isActive()) {
			dlgExitoModFac.setVisible(false);
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
