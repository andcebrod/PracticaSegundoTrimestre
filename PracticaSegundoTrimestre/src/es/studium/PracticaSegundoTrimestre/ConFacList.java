package es.studium.PracticaSegundoTrimestre;


import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Font;
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


public class ConFacList extends JFrame implements WindowListener, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Choice facturas = new Choice();
	JLabel lblConsultaFac = new JLabel("Seleccione Factura a consultar:");
	JButton btnSeleccionar = new JButton("Seleccionar");
	JPanel pnl2 = new JPanel();
	int idReparacion;
	Font negrita = new Font("Arial", Font.BOLD, 14);
	public ConFacList() 
	{
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.setSize(500,150);
		this.setTitle("Consulta de Facturas");
		this.add(lblConsultaFac);
		this.add(facturas);
		pnl2.add(btnSeleccionar);
		this.add(pnl2, BorderLayout.SOUTH);
		btnSeleccionar.addActionListener(this);
		
		
		ResultSet selectFacturas = ejecutarSelect("SELECT * FROM facturas, clientes where idClienteFK = idCliente;", conectar("TallerJava","usuarioTaller","Studium2018;"));
		
		try {

			while (selectFacturas.next())
			{
				String fac=Integer.toString(selectFacturas.getInt("idFactura"));
				fac = fac + "-"+ 
				" "+selectFacturas.getString("DATE_FORMAT(fechaFactura, '%d/%m%a'")+
				", Cliente: "+selectFacturas.getString("nombreCliente")+
				", Reparación:"+selectFacturas.getInt("idReparacionFK");
				facturas.add(fac);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		desconectar(conectar("TallerJava","usuarioTaller" ,"Studium2018;"));
		
		
		this.addWindowListener(this);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(btnSeleccionar.equals(ae.getSource())) {

			String[] array= facturas.getSelectedItem().toString().split("-");
			int idFactura = Integer.parseInt(array[0]);
			String[] array2= facturas.getSelectedItem().toString().split(", Reparación:");
			int idReparacion = Integer.parseInt(array2[1]);
			new ConLineaRepRec(idReparacion, idFactura);
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


