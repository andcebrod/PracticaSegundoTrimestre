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

public class AddRec implements WindowListener, ActionListener, TextListener{

	JFrame ventanaAddRec = new JFrame ("Añadir Recambio");
	JLabel lblDescripcionRec = new JLabel ("Descripción:");
	JLabel lblUnidadesRec = new JLabel ("Unidades:");
	JLabel lblPrecioRec = new JLabel ("Precio:");
	
	JTextField txtDescripcionRec = new JTextField(10);
	JTextField txtUnidadesRec = new JTextField(10);
	JTextField txtPrecioRec = new JTextField(10);
	

	JButton btnCrear = new JButton("Crear Recambio");
	JButton btnLimpiar = new JButton("Limpiar");
	
	JDialog dlgExitoAddRec = new JDialog(ventanaAddRec, "Recambio creado");
	JLabel lblExito = new JLabel("Recambio creado con éxito");
	
	JPanel pnlPanel = new JPanel();
	JPanel pnlPanel2 = new JPanel();
	JPanel pnlPanel3 = new JPanel();
	JPanel pnlPanel4 = new JPanel();

	public AddRec() {
		ventanaAddRec.setLayout(new GridLayout(4,2));
		ventanaAddRec.setLocationRelativeTo(null);
		ventanaAddRec.setSize(400,300);
		
		pnlPanel.setLayout(new FlowLayout());
		pnlPanel2.setLayout(new FlowLayout());
		pnlPanel3.setLayout(new FlowLayout());
		pnlPanel4.setLayout(new FlowLayout());
		
		
		pnlPanel.add(lblDescripcionRec);
		pnlPanel.add(txtDescripcionRec);
		ventanaAddRec.add(pnlPanel);
		
		pnlPanel2.add(lblUnidadesRec);
		pnlPanel2.add(txtUnidadesRec);
		ventanaAddRec.add(pnlPanel2);
		
		pnlPanel3.add(lblPrecioRec);
		pnlPanel3.add(txtPrecioRec);
		ventanaAddRec.add(pnlPanel3);
		
		pnlPanel4.add(btnCrear);
		btnCrear.addActionListener(this);
		pnlPanel4.add(btnLimpiar);
		btnLimpiar.addActionListener(this);
		ventanaAddRec.add(pnlPanel4);
		ventanaAddRec.addWindowListener(this);
		ventanaAddRec.setVisible(true);
		
		dlgExitoAddRec.setLocationRelativeTo(null);
		dlgExitoAddRec.setSize(190,90);
		dlgExitoAddRec.add(lblExito);
		dlgExitoAddRec.addWindowListener(this);
		dlgExitoAddRec.setVisible(false);
		dlgExitoAddRec.setLayout(new FlowLayout());
	}
	
	

	public static void main(String[] args) {
		new AddRec();
	}

	@Override
	public void textValueChanged(TextEvent e) {}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		
		if (btnCrear.equals(ae.getSource())) {
			dlgExitoAddRec.setVisible(true);
		} else if (btnLimpiar.equals(ae.getSource())) {
			txtDescripcionRec.selectAll();
			txtDescripcionRec.setText("");
			txtUnidadesRec.selectAll();
			txtUnidadesRec.setText("");
			txtPrecioRec.selectAll();
			txtPrecioRec.setText("");
			
		}
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowClosing(WindowEvent arg0) {
		
		if(ventanaAddRec.isActive()) {
			ventanaAddRec.setVisible(false);
		}else {
			//System.exit(0);
		}
		if(dlgExitoAddRec.isActive()) {
			dlgExitoAddRec.setVisible(false);
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
