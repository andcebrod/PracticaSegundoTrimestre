package es.studium.PracticaSegundoTrimestre;

import java.awt.Choice;
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

import javax.swing.*;

public class ModFac extends JFrame implements WindowListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel lblFactura = new JLabel ("Factura a modificar");
	JLabel lblFecha = new JLabel ("Fecha Factura:");
	JTextField txtFecha = new JTextField(10);
	
	JLabel lblCliente = new JLabel ("Cliente:");
	JLabel lblReparacion = new JLabel ("Reparación:");
	
	Choice clientes = new Choice();
	Choice reparaciones = new Choice();
	
	JButton btnModificar = new JButton ("Modificar");
	JButton btnLimpiar = new JButton ("Limpiar");
	
	JPanel pnlPanel1 = new JPanel();
	JPanel pnlPanel2 = new JPanel();
	JPanel pnlPanel3 = new JPanel();
	JPanel pnlPanel4 = new JPanel();
	JPanel pnlPanel5 = new JPanel();
	JPanel pnlPanel6 = new JPanel();
	JPanel pnlPanel7 = new JPanel();

	int idFac = 0;
	
	public ModFac(int id) 
	{
		idFac = id;
		this.setLayout(new GridLayout(7,1));
		this.setLocationRelativeTo(null);
		this.setSize(500,300);
		ResultSet selectClientes = ejecutarSelect("SELECT * FROM clientes",conectar("TallerJava","root","Studium2018;"));
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
		desconectar(conectar("TallerJava","root","Studium2018;"));
		
		ResultSet selectReparaciones = ejecutarSelect("SELECT * FROM reparaciones",conectar("TallerJava","root","Studium2018;"));
		try {
			while(selectReparaciones.next())
			{
				String rep=Integer.toString(selectReparaciones.getInt("idReparacion"));
				rep = rep + ".-"+ selectReparaciones.getString("Averia");
				reparaciones.add(rep);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		desconectar(conectar("TallerJava","root","Studium2018;"));
		
		ResultSet consultaFactura = ejecutarSelect("SELECT * FROM facturas where idFactura="+id+";",conectar("TallerJava","root","Studium2018;"));
		try {
			consultaFactura.next();
			txtFecha.selectAll();
			txtFecha.setText(consultaFactura.getString("fechaFactura"));
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		desconectar(conectar("TallerJava","root","Studium2018;"));
		
		pnlPanel1.add(lblFactura);
		pnlPanel2.add(lblFecha);
		pnlPanel2.add(txtFecha);
		pnlPanel3.add(lblCliente);
		pnlPanel4.add(clientes);
		pnlPanel5.add(lblReparacion);
		pnlPanel6.add(reparaciones);
		pnlPanel7.add(btnModificar);
		pnlPanel7.add(btnLimpiar);
		
		this.add(pnlPanel1);
		this.add(pnlPanel2);
		this.add(pnlPanel3);
		this.add(pnlPanel4);
		this.add(pnlPanel5);
		this.add(pnlPanel6);
		this.add(pnlPanel7);
		
		this.addWindowListener(this);
		btnModificar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (btnModificar.equals(ae.getSource())) 
		{
			if(txtFecha.getText().equals("")) {
				JOptionPane.showMessageDialog(null,"Error, Fecha vacía","Fecha vacía", JOptionPane.ERROR_MESSAGE);

			} else {
				String[] arrayReparaciones= reparaciones.getSelectedItem().toString().split(".-");
				int idReparacion = Integer.parseInt(arrayReparaciones[0]);
				
				String[] arrayClientes = clientes.getSelectedItem().toString().split(".-");
				int idCliente = Integer.parseInt(arrayClientes[0]);
				
				ejecutarIDA("UPDATE facturas SET fechaFactura = '"+txtFecha.getText()+"', idClienteFK="+idCliente+", idReparacionFK="+idReparacion+" WHERE idFactura ="+idFac+";",conectar("TallerJava","root","Studium2018;"));
				desconectar(conectar("TallerJava","root","Studium2018;"));
			}
		} 
		else if (btnLimpiar.equals(ae.getSource())) 
		{
			txtFecha.selectAll();
			txtFecha.setText("");
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

	public void ejecutarIDA(String sentencia, Connection c) 
	{
		try
		{
			Statement statement = c.createStatement();
			statement.executeUpdate(sentencia);
			JOptionPane.showMessageDialog(null,"Factura modificada","Factura modificada con éxito", JOptionPane.INFORMATION_MESSAGE);

		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}

	}

}
