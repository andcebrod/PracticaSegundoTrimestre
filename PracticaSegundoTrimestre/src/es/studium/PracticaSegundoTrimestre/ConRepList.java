package es.studium.PracticaSegundoTrimestre;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
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

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ConRepList extends JFrame implements WindowListener, ActionListener, TextListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel modelo = new DefaultTableModel();
	JTable tablaReparaciones= new JTable(modelo);
	JButton btnAceptar = new JButton("Aceptar");
	JPanel pnl2 = new JPanel();
	ResultSet rs;


	public ConRepList() 
	{
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setSize(600,200);
		this.setTitle("Consulta de Reparaciones");

		this.add(new JScrollPane(tablaReparaciones),BorderLayout.CENTER);
		pnl2.add(btnAceptar);
		this.add(pnl2, BorderLayout.SOUTH);
		btnAceptar.addActionListener(this);

		modelo.addColumn("Nº Reparacion");
		modelo.addColumn("Avería");
		modelo.addColumn("Fecha Entrada");
		modelo.addColumn("Fecha de Salida");
		modelo.addColumn("Reparado");

		rs = ejecutarSelect("SELECT * FROM reparaciones", conectar("TallerJava","root","Studium2018;"));
		try {

			while (rs.next())
			{
				Object [] fila = new Object[5];

				for (int i=0;i<5;i++)
					fila[i] = rs.getObject(i+1);
				modelo.addRow(fila); 
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		desconectar(conectar("practicamvc","root" ,"Studium2018;"));
		tablaReparaciones.setEnabled(false);


		this.addWindowListener(this);
		this.setVisible(true);
	}

	@Override
	public void textValueChanged(TextEvent arg0) {}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(btnAceptar.equals(ae.getSource())) {
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
		Statement statement = null;
		ResultSet rs = null;
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
