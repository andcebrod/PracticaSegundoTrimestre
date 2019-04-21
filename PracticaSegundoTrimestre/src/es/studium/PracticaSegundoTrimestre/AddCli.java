package es.studium.PracticaSegundoTrimestre;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddCli  implements WindowListener, ActionListener, TextListener
{
	
	String driver = "com.mysql.jdbc.Driver";
	String url ="jdbc:mysql://localhost:3306/TallerJava?autoReconnect=true&useSSL=false";
	String login = "root";
	String password = "Studium2018;";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	
	JFrame ventanaAddCli = new JFrame ("Añadir Cliente");
	JLabel lblNombreCli = new JLabel ("Nombre:");
	JLabel lblDireccionCli = new JLabel ("Dirección:");
	JLabel lblTelefonoCli = new JLabel ("Teléfono:");

	JTextField txtNombreCli = new JTextField(15);
	JTextField txtDireccionCli = new JTextField(15);
	JTextField txtTelefonoCli = new JTextField(15);

	JButton btnCrear = new JButton("Crear Cliente");
	JButton btnLimpiar = new JButton("Limpiar");

	JPanel pnlPanel = new JPanel();
	JPanel pnlPanel3 = new JPanel();
	JPanel pnlPanel4 = new JPanel();
	JPanel pnlPanel5 = new JPanel();
	
	public AddCli() {
		ventanaAddCli.setLayout(new GridLayout(4,1));
		ventanaAddCli.setLocationRelativeTo(null);
		ventanaAddCli.setSize(400,300);

		pnlPanel.setLayout(new FlowLayout());
		pnlPanel3.setLayout(new FlowLayout());
		pnlPanel4.setLayout(new FlowLayout());
		pnlPanel5.setLayout(new FlowLayout());

		pnlPanel.add(lblNombreCli);
		pnlPanel.add(txtNombreCli);
		ventanaAddCli.add(pnlPanel);

		pnlPanel3.add(lblDireccionCli);
		pnlPanel3.add(txtDireccionCli);
		ventanaAddCli.add(pnlPanel3);

		pnlPanel4.add(lblTelefonoCli);
		pnlPanel4.add(txtTelefonoCli);
		ventanaAddCli.add(pnlPanel4);

		pnlPanel5.add(btnCrear);
		btnCrear.addActionListener(this);
		pnlPanel5.add(btnLimpiar);
		btnLimpiar.addActionListener(this);
		ventanaAddCli.add(pnlPanel5);


		ventanaAddCli.addWindowListener(this);
		ventanaAddCli.setVisible(true);
	}

	@Override
	public void textValueChanged(TextEvent e) {}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{

		if (btnCrear.equals(ae.getSource())) {
			
			txtNombreCli.selectAll();
			String Nombre = txtNombreCli.getText();
			txtDireccionCli.selectAll();
			String Direccion = txtDireccionCli.getText();
			txtTelefonoCli.selectAll();
			String Telefono = txtTelefonoCli.getText();
			
			try
			{
				Class.forName(driver);
				String sentencia = "INSERT INTO clientes VALUES (null,'"+Nombre+"', '"+Direccion+"', '"+Telefono+"');";
				connection = DriverManager.getConnection(url, login,password);
				statement =connection.createStatement();
				statement.executeUpdate(sentencia);
				JOptionPane.showMessageDialog(null,"Cliente creado","Cliente Creado", JOptionPane.INFORMATION_MESSAGE);
			}
			catch (ClassNotFoundException cnfe)
			{
				JOptionPane.showMessageDialog(null,"Error",cnfe.getMessage(), JOptionPane.ERROR_MESSAGE);
			}
			catch (SQLException sqle)
			{
				JOptionPane.showMessageDialog(null,"Error",sqle.getMessage(), JOptionPane.ERROR_MESSAGE);
			}
			finally
			{
				try
				{
					if(connection!=null)
					{
						connection.close();
					}
				}
				catch (SQLException e)
				{
					JOptionPane.showMessageDialog(null,"Error",e.getMessage(), JOptionPane.ERROR_MESSAGE);
				}
			}
			
			

		} else if (btnLimpiar.equals(ae.getSource())) {
			txtNombreCli.selectAll();
			txtNombreCli.setText("");
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

		if(ventanaAddCli.isActive()) {
			ventanaAddCli.setVisible(false);
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
