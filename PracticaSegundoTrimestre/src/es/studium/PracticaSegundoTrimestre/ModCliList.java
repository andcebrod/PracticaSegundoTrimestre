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

import javax.swing.*;


public class ModCliList implements WindowListener, ActionListener{
	JFrame ventanaModCliList = new JFrame ("Buscar cliente para modificar");
	JLabel lblClientes = new JLabel("Selecciona cliente");
	Choice clientes = new Choice();
	JButton btnSeleccionar = new JButton("Seleccionar");

	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();

	String user = "";

	public ModCliList(String usuario) {
		ventanaModCliList.setLayout(new GridLayout(3,1));
		ventanaModCliList.setLocationRelativeTo(null);
		ventanaModCliList.setSize(400,300);
		
		user = usuario;

		ResultSet selectClientes = ejecutarSelect("SELECT * FROM clientes",conectar("TallerJava","usuarioTaller","Studium2018;"));
		try {
			while(selectClientes.next())
			{
				String cli=Integer.toString(selectClientes.getInt("idCliente"));
				cli = cli + ".-"+" "+selectClientes.getString("nombreCliente");
				clientes.add(cli);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		desconectar(conectar("TallerJava","usuarioTaller","Studium2018;"));

		pnl1.add(lblClientes);
		pnl2.add(clientes);
		pnl3.add(btnSeleccionar);
		ventanaModCliList.add(pnl1);
		ventanaModCliList.add(pnl2);
		ventanaModCliList.add(pnl3);
		btnSeleccionar.addActionListener(this);
		ventanaModCliList.addWindowListener(this);
		ventanaModCliList.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(btnSeleccionar.equals(ae.getSource())) {

			String[] arrayClientes = clientes.getSelectedItem().toString().split(".-");
			int idCliente = Integer.parseInt(arrayClientes[0]);
			new ModCli(idCliente, user);
			ventanaModCliList.setVisible(false);
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		ventanaModCliList.setVisible(false);
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
