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

public class ModRep implements WindowListener, ActionListener, TextListener{

	JFrame ventanaModRep = new JFrame ("Modificar reparación:");
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

	JButton btnCrear = new JButton("Modificar Reparación");
	JButton btnLimpiar = new JButton("Limpiar");

	JDialog dlgExitoModRep = new JDialog(ventanaModRep, "Reparación modificada");
	JLabel lblExito = new JLabel("Reparación modificada con éxito");
	
	JPanel pnlPanel = new JPanel();
	JPanel pnlPanel2 = new JPanel();
	JPanel pnlPanel3 = new JPanel();
	JPanel pnlPanel4 = new JPanel();
	JPanel pnlPanel5 = new JPanel();

	public ModRep() 
	{
		ventanaModRep.setLayout(new GridLayout(5,2));
		ventanaModRep.setLocationRelativeTo(null);
		ventanaModRep.setSize(400,300);
		
		pnlPanel.setLayout(new FlowLayout());
		pnlPanel2.setLayout(new FlowLayout());
		pnlPanel3.setLayout(new FlowLayout());
		pnlPanel4.setLayout(new FlowLayout());
		pnlPanel5.setLayout(new FlowLayout());


		pnlPanel.add(lblAveriaRep);
		pnlPanel.add(txtAveriaRep);
		ventanaModRep.add(pnlPanel);
		

		pnlPanel2.add(lblFechaEntradaRep);
		pnlPanel2.add(txtFechaEntradaRep);
		ventanaModRep.add(pnlPanel2);

		pnlPanel3.add(lblFechaSalidaRep);
		pnlPanel3.add(txtFechaSalidaRep);
		ventanaModRep.add(pnlPanel3);

		pnlPanel4.add(lblReparadoRep);
		chkReparadoRep.add(chkSiRep);
		chkReparadoRep.add(chkNoRep);
		pnlPanel4.add(chkSiRep);
		pnlPanel4.add(chkNoRep);
		ventanaModRep.add(pnlPanel4);
		

		pnlPanel5.add(btnCrear);
		btnCrear.addActionListener(this);
		pnlPanel5.add(btnLimpiar);
		btnLimpiar.addActionListener(this);
		ventanaModRep.add(pnlPanel5);
		
		ventanaModRep.addWindowListener(this);
		ventanaModRep.setVisible(true);

		dlgExitoModRep.setLocationRelativeTo(null);
		dlgExitoModRep.setSize(190,90);
		dlgExitoModRep.add(lblExito);
		dlgExitoModRep.addWindowListener(this);
		dlgExitoModRep.setVisible(false);
		dlgExitoModRep.setLayout(new FlowLayout());
	}

	@Override
	public void textValueChanged(TextEvent e) {}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if (btnCrear.equals(ae.getSource())) {
			dlgExitoModRep.setVisible(true);
		} else if (btnLimpiar.equals(ae.getSource())) {
			txtAveriaRep.selectAll();
			txtAveriaRep.setText("");
			txtFechaEntradaRep.selectAll();
			txtFechaEntradaRep.setText("");
			txtFechaSalidaRep.selectAll();
			txtFechaSalidaRep.setText("");
		}	
	}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) {
		if(ventanaModRep.isActive()) {
			ventanaModRep.setVisible(false);
		}else {
			//System.exit(0);
		}
		if(dlgExitoModRep.isActive()) {
			dlgExitoModRep.setVisible(false);
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
