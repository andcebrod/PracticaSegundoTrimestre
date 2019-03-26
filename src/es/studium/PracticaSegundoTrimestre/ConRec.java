package es.studium.PracticaSegundoTrimestre;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConRec implements WindowListener{
	JFrame ventanaConRec = new JFrame ("Consulta de Cliente");
	JLabel lblDescripcionRec = new JLabel ("Descripción:");
	JLabel lblUnidadesRec = new JLabel ("Unidades:");
	JLabel lblPrecioRec = new JLabel ("Precio:");

	JTextField txtDescripcionRec = new JTextField(10);
	JTextField txtUnidadesRec = new JTextField(10);
	JTextField txtPrecioRec = new JTextField(10);
	
	JPanel pnlPanel = new JPanel();
	JPanel pnlPanel2 = new JPanel();
	JPanel pnlPanel3 = new JPanel();

	public ConRec() {
		
		ventanaConRec.setLayout(new GridLayout(3,2));
		ventanaConRec.setLocationRelativeTo(null);
		ventanaConRec.setSize(400,300);
		
		pnlPanel.setLayout(new FlowLayout());
		pnlPanel2.setLayout(new FlowLayout());
		pnlPanel3.setLayout(new FlowLayout());
		
		pnlPanel.add(lblDescripcionRec);
		pnlPanel.add(txtDescripcionRec);
		ventanaConRec.add(pnlPanel);

		pnlPanel2.add(lblUnidadesRec);
		pnlPanel2.add(txtUnidadesRec);
		ventanaConRec.add(pnlPanel2);

		pnlPanel3.add(lblPrecioRec);
		pnlPanel3.add(txtPrecioRec);
		ventanaConRec.add(pnlPanel3);
		
		ventanaConRec.addWindowListener(this);
		ventanaConRec.setVisible(true);

	}
	public static void main(String[] args) {}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		if(ventanaConRec.isActive()) {
			ventanaConRec.setVisible(false);
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
