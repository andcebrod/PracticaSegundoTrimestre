package es.studium.PracticaSegundoTrimestre;

import java.awt.Choice;
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

public class ModFacList extends JFrame implements WindowListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblFacturas = new JLabel("Selecciona factura");
	Choice facturas = new Choice();
	JButton btnSeleccionar = new JButton("Seleccionar");

	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();

	
	
	public ModFacList ()
	{
		this.setTitle("Buscar factura para modificar");
		this.setLayout(new GridLayout(3,1));
		this.setLocationRelativeTo(null);
		this.setSize(400,300);

		ResultSet selectFacturas = ejecutarSelect("SELECT * FROM facturas",conectar("TallerJava","root","Studium2018;"));
		try {
			while(selectFacturas.next())
			{
				String fac=Integer.toString(selectFacturas.getInt("idFactura"));
				fac = fac + "-"+ selectFacturas.getString("fechaFactura")+", Cliente: "+Integer.toString(selectFacturas.getInt("idClienteFK"))+", Reparación:"+Integer.toString(selectFacturas.getInt("idReparacionFK"));
				facturas.add(fac);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		desconectar(conectar("TallerJava","root","Studium2018;"));

		pnl1.add(lblFacturas);
		pnl2.add(facturas);
		pnl3.add(btnSeleccionar);
		this.add(pnl1);
		this.add(pnl2);
		this.add(pnl3);
		btnSeleccionar.addActionListener(this);
		this.addWindowListener(this);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(btnSeleccionar.equals(ae.getSource())) {

			String[] array= facturas.getSelectedItem().toString().split("-");
			int idFactura = Integer.parseInt(array[0]);
			new ModFac(idFactura);
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
	public void windowClosing(WindowEvent e) 
	{
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
