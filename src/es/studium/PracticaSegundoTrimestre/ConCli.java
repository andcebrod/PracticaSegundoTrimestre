package es.studium.PracticaSegundoTrimestre;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConCli implements WindowListener{

	JFrame ventanaConCli = new JFrame ("Consulta de Cliente");
	JLabel lblNombreCli = new JLabel ("Nombre:");
	JLabel lblApellidosCli = new JLabel ("Apellidos:");
	JLabel lblDireccionCli = new JLabel ("Dirección:");
	JLabel lblTelefonoCli = new JLabel ("Teléfono:");

	JTextField txtNombreCli = new JTextField(15);
	JTextField txtApellidosCli = new JTextField(15);
	JTextField txtDireccionCli = new JTextField(15);
	JTextField txtTelefonoCli = new JTextField(15);
	
	JPanel pnlPanel = new JPanel();
	JPanel pnlPanel2 = new JPanel();
	JPanel pnlPanel3 = new JPanel();
	JPanel pnlPanel4 = new JPanel();

	public ConCli() {
		ventanaConCli.setLayout(new GridLayout(4,2));
		ventanaConCli.setLocationRelativeTo(null);
		ventanaConCli.setSize(400,200);
		
		pnlPanel.setLayout(new FlowLayout());
		pnlPanel2.setLayout(new FlowLayout());
		pnlPanel3.setLayout(new FlowLayout());
		pnlPanel4.setLayout(new FlowLayout());

		pnlPanel.add(lblNombreCli);
		pnlPanel.add(txtNombreCli);
		ventanaConCli.add(pnlPanel);

		pnlPanel2.add(lblApellidosCli);
		pnlPanel2.add(txtApellidosCli);
		ventanaConCli.add(pnlPanel2);

		pnlPanel3.add(lblDireccionCli);
		pnlPanel3.add(txtDireccionCli);
		ventanaConCli.add(pnlPanel3);

		pnlPanel4.add(lblTelefonoCli);
		pnlPanel4.add(txtTelefonoCli);
		ventanaConCli.add(pnlPanel4);


		ventanaConCli.addWindowListener(this);
		ventanaConCli.setVisible(true);

	}

	public static void main(String[] args) {
		new ConCli();
	}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) {
		if(ventanaConCli.isActive()) {
			ventanaConCli.setVisible(false);
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
