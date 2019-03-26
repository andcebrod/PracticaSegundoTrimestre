package es.studium.PracticaSegundoTrimestre;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModCli implements WindowListener, ActionListener, TextListener{
	JFrame ventanaModCli = new JFrame ("Modificar cliente:");
	JLabel lblNombreCli = new JLabel ("Nombre:");
	JLabel lblApellidosCli = new JLabel ("Apellidos:");
	JLabel lblDireccionCli = new JLabel ("Direcci�n:");
	JLabel lblTelefonoCli = new JLabel ("Tel�fono:");
	
	JTextField txtNombreCli = new JTextField(15);
	JTextField txtApellidosCli = new JTextField(15);
	JTextField txtDireccionCli = new JTextField(15);
	JTextField txtTelefonoCli = new JTextField(15);

	JButton btnCrear = new JButton("Modificar Cliente");
	JButton btnLimpiar = new JButton("Limpiar");
	
	JDialog dlgExitoModCli = new JDialog(ventanaModCli, "Cliente modificado");
	JLabel lblExito = new JLabel("Cliente modificado con �xito");
	
	JPanel pnlPanel = new JPanel();
	JPanel pnlPanel2 = new JPanel();
	JPanel pnlPanel3 = new JPanel();
	JPanel pnlPanel4 = new JPanel();
	JPanel pnlPanel5 = new JPanel();
	
	public ModCli() 
	{
		ventanaModCli.setLayout(new GridLayout(5,2));
		ventanaModCli.setLocationRelativeTo(null);
		ventanaModCli.setSize(400,300);
		
		pnlPanel.setLayout(new FlowLayout());
		pnlPanel2.setLayout(new FlowLayout());
		pnlPanel3.setLayout(new FlowLayout());
		pnlPanel4.setLayout(new FlowLayout());
		pnlPanel5.setLayout(new FlowLayout());
		
		pnlPanel.add(lblNombreCli);
		pnlPanel.add(txtNombreCli);
		ventanaModCli.add(pnlPanel);
		
		pnlPanel2.add(lblApellidosCli);
		pnlPanel2.add(txtApellidosCli);
		ventanaModCli.add(pnlPanel2);
		
		pnlPanel3.add(lblDireccionCli);
		pnlPanel3.add(txtDireccionCli);
		ventanaModCli.add(pnlPanel3);
		
		pnlPanel4.add(lblTelefonoCli);
		pnlPanel4.add(txtTelefonoCli);
		ventanaModCli.add(pnlPanel4);
		
		pnlPanel5.add(btnCrear);
		btnCrear.addActionListener(this);
		pnlPanel5.add(btnLimpiar);
		btnLimpiar.addActionListener(this);
		ventanaModCli.add(pnlPanel5);
		
		ventanaModCli.addWindowListener(this);
		ventanaModCli.setVisible(true);
		
		dlgExitoModCli.setLocationRelativeTo(null);
		dlgExitoModCli.setSize(190,90);
		dlgExitoModCli.add(lblExito);
		dlgExitoModCli.addWindowListener(this);
		dlgExitoModCli.setVisible(false);
		dlgExitoModCli.setLayout(new FlowLayout());
	}
	public static void main(String[] args) {
		
	}
	@Override
	public void textValueChanged(TextEvent e) {}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		
		if (btnCrear.equals(ae.getSource())) {
			dlgExitoModCli.setVisible(true);
		} else if (btnLimpiar.equals(ae.getSource())) {
			txtNombreCli.selectAll();
			txtNombreCli.setText("");
			txtApellidosCli.selectAll();
			txtApellidosCli.setText("");
			txtDireccionCli.selectAll();
			txtDireccionCli.setText("");
			txtTelefonoCli.selectAll();
			txtTelefonoCli.setText("");
		}
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowClosing(WindowEvent arg0) {
		
		if(ventanaModCli.isActive()) {
			ventanaModCli.setVisible(false);
		}else {
			//System.exit(0);
		}
		if(dlgExitoModCli.isActive()) {
			dlgExitoModCli.setVisible(false);
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


