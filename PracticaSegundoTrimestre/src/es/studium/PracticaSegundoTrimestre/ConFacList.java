package es.studium.PracticaSegundoTrimestre;


import java.awt.BorderLayout;
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

public class ConFacList extends JFrame implements WindowListener, ActionListener, TextListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel modelo = new DefaultTableModel();
	JTable tablaFacturas= new JTable(modelo);
	JButton btnAceptar = new JButton("Aceptar");
	JPanel pnl2 = new JPanel();
	ResultSet rs;
	
	
	public ConFacList() 
	{
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setSize(600,200);
		this.setTitle("Consulta de Facturas");
		
		this.add(new JScrollPane(tablaFacturas),BorderLayout.CENTER);
		pnl2.add(btnAceptar);
		this.add(pnl2, BorderLayout.SOUTH);
		btnAceptar.addActionListener(this);
		
		modelo.addColumn("Nº Factura");
		modelo.addColumn("Fecha Factura");
		modelo.addColumn("Nº Cliente");
		modelo.addColumn("Nº Reparación");
		
		rs = ejecutarSelect("SELECT * FROM facturas", conectar("TallerJava","root","Studium2018;"));
		try {

			while (rs.next())
			{
			   Object [] fila = new Object[4];

			   for (int i=0;i<4;i++)
			      fila[i] = rs.getObject(i+1);
			   modelo.addRow(fila); 
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		desconectar(conectar("practicamvc","root" ,"Studium2018;"));
		tablaFacturas.setEnabled(false);
		
		
		this.addWindowListener(this);
		this.setVisible(true);
	}

	@Override
	public void textValueChanged(TextEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(btnAceptar.equals(ae.getSource())) {
			this.setVisible(false);
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
		this.setVisible(false);
		
		
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


