package es.studium.PracticaSegundoTrimestre;

import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ModRecList extends JFrame implements WindowListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblRecambios = new JLabel("Selecciona recambio");
	Choice recambios = new Choice();
	JButton btnSeleccionar = new JButton("Seleccionar");

	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();
	
	String user = "";

	public ModRecList(String usuario) 
	{
		user = usuario;
		this.setTitle("Buscar recambio para modificar");
		this.setLayout(new GridLayout(3,1));
		this.setLocationRelativeTo(null);
		this.setSize(400,300);

		ResultSet selectRecambios = ejecutarSelect("SELECT * FROM recambios",conectar("TallerJava","usuarioTaller","Studium2018;"));
		try {
			while(selectRecambios.next())
			{
				String rec=Integer.toString(selectRecambios.getInt("idRecambio"));
				rec = rec + "-"+ selectRecambios.getString("descripcionRecambio");
				recambios.add(rec);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		desconectar(conectar("TallerJava","usuarioTaller","Studium2018;"));

		pnl1.add(lblRecambios);
		pnl2.add(recambios);
		pnl3.add(btnSeleccionar);
		this.add(pnl1);
		this.add(pnl2);
		this.add(pnl3);
		btnSeleccionar.addActionListener(this);
		this.addWindowListener(this);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent ae) 
	{
		if(btnSeleccionar.equals(ae.getSource())) {

			String[] array= recambios.getSelectedItem().toString().split("-");
			int idRecambio = Integer.parseInt(array[0]);
			new ModRec(idRecambio, user);
			this.setVisible(false);
		}

	}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		this.setVisible(false);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}
	@Override
	public void windowDeiconified(WindowEvent arg0) {}
	@Override
	public void windowIconified(WindowEvent arg0) {}
	@Override
	public void windowOpened(WindowEvent arg0) {}

	public Connection conectar(String baseDatos, String usuario, String clave)
	{
		String driver = "com.mysql.jdbc.Driver";
		String url ="jdbc:mysql://localhost:3306/"+baseDatos+"?autoReconnect=true&useSSL=false";
		String login = usuario;
		String password = clave;
		Connection connection = null;

		try
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(url, login,password);
		}
		catch (ClassNotFoundException cnfe)
		{
			JOptionPane.showMessageDialog(null,cnfe.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (SQLException sqle)
		{
			JOptionPane.showMessageDialog(null,sqle.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		return connection;
	}

	public void desconectar(Connection c) 
	{
		try
		{
			if(c!=null)
			{
				c.close();
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public ResultSet ejecutarSelect(String sentencia, Connection c) 
	{

		try
		{
			Statement statement = c.createStatement();
			ResultSet rs= statement.executeQuery(sentencia);
			return rs;
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

	}

}
