package es.studium.PracticaSegundoTrimestre;

import java.awt.BorderLayout;
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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ConRecList implements WindowListener, ActionListener, TextListener {
	
	JFrame ventanaConRecList = new JFrame ("Consulta de Recambios");
	DefaultTableModel modelo = new DefaultTableModel();
	JTable tablaRecambios= new JTable(modelo);
	JButton btnAceptar = new JButton("Aceptar");
	JPanel pnl1 = new JPanel();
	ResultSet rs;
	
	public ConRecList() 
	{
		ventanaConRecList.setLayout(new BorderLayout());
		ventanaConRecList.setLocationRelativeTo(null);
		ventanaConRecList.setSize(600,200);
		ventanaConRecList.add(new JScrollPane(tablaRecambios),BorderLayout.CENTER);
		
		modelo.addColumn("N� Recambio");
		modelo.addColumn("Descripci�n");
		modelo.addColumn("Unidades");
		modelo.addColumn("Precio");
		
		
		rs = ejecutarSelect("SELECT * FROM recambios", conectar("TallerJava","root","Studium2018;"));
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
		tablaRecambios.setEnabled(false);
		pnl1.add(btnAceptar);
		btnAceptar.addActionListener(this);
		ventanaConRecList.add(pnl1, BorderLayout.SOUTH);
		ventanaConRecList.addWindowListener(this);
		ventanaConRecList.setVisible(true);
	}
	
	@Override
	public void textValueChanged(TextEvent arg0) {}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		
		if(btnAceptar.equals(ae.getSource())) {
			ventanaConRecList.setVisible(false);
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		ventanaConRecList.setVisible(false);
		
	}
		
	
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		
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
