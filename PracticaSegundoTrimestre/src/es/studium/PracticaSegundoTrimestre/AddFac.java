package es.studium.PracticaSegundoTrimestre;

import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.*;

public class AddFac extends JFrame implements WindowListener, ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String user;

	JLabel lblFecha = new JLabel ("Fecha Factura :");
	JLabel lblClientes = new JLabel ("Cliente:");
	JLabel lblReparaciones = new JLabel ("Reparación:");

	Choice clientes = new Choice();
	Choice reparaciones = new Choice();
	JTextField txtFecha = new JTextField(10);

	JButton btnCrear = new JButton("Crear Factura");
	JButton btnLimpiar = new JButton("Limpiar");


	JPanel pnlPanel = new JPanel();
	JPanel pnlPanel2 = new JPanel();
	JPanel pnlPanel3 = new JPanel();
	JPanel pnlPanel4 = new JPanel();
	
	Calendar horaFecha = Calendar.getInstance();
	int hora,minutos,dia,mes,anyo;

	public AddFac(String usuario) 
	{
		user = usuario;
		this.setTitle("Añadir Factura");
		this.setLayout(new GridLayout(5,2));
		this.setLocationRelativeTo(null);
		this.setSize(400,300);

		hora = horaFecha.get(Calendar.HOUR_OF_DAY);
		minutos = horaFecha.get(Calendar.MINUTE);
		dia = horaFecha.get(Calendar.DAY_OF_MONTH);
		mes = horaFecha.get(Calendar.MONTH)+1;
		anyo = horaFecha.get(Calendar.YEAR);
		
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

		ResultSet selectReparaciones = ejecutarSelect("SELECT * FROM reparaciones",conectar("TallerJava","usuarioTaller","Studium2018;"));
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
		desconectar(conectar("TallerJava","usuarioTaller","Studium2018;"));


		pnlPanel.setLayout(new FlowLayout());
		pnlPanel2.setLayout(new FlowLayout());
		pnlPanel3.setLayout(new FlowLayout());
		pnlPanel4.setLayout(new FlowLayout());

		pnlPanel.add(lblFecha);
		txtFecha.setText(dia+"/"+mes+"/"+anyo);
		pnlPanel.add(txtFecha);
		this.add(pnlPanel);

		pnlPanel2.add(lblClientes);
		pnlPanel2.add(clientes);
		this.add(pnlPanel2);

		pnlPanel3.add(lblReparaciones);
		pnlPanel3.add(reparaciones);
		this.add(pnlPanel3);

		pnlPanel4.add(btnCrear);
		btnCrear.addActionListener(this);
		pnlPanel4.add(btnLimpiar);
		btnLimpiar.addActionListener(this);
		this.add(pnlPanel4);

		this.addWindowListener(this);
		this.setVisible(true);

	}
	@Override
	public void actionPerformed(ActionEvent ae) {

		if (btnCrear.equals(ae.getSource())) 
		{
			String[] arrayClientes = clientes.getSelectedItem().toString().split(".-");
			int idCliente = Integer.parseInt(arrayClientes[0]);
			String[] arrayReparaciones= reparaciones.getSelectedItem().toString().split(".-");
			int idReparacion = Integer.parseInt(arrayReparaciones[0]);
			
			String Fecha = txtFecha.getText();
			String[] arrayFecha = Fecha.split("/");
			Fecha = arrayFecha[2]+"-"+arrayFecha[1]+"-"+arrayFecha[0];
			
			String sentencia = "INSERT INTO facturas VALUES (null,'"+Fecha+"',"+idCliente+","+idReparacion+");";
			
			ejecutarIDA(sentencia,conectar("TallerJava","usuarioTaller","Studium2018;"));
			ResultSet rsCodFac = ejecutarSelect("select * from facturas order by 1 desc;", conectar("TallerJava","usuarioTaller","Studium2018;"));
			try {
				rsCodFac.next();
				new LineaRepRec(idReparacion, rsCodFac.getInt("idFactura"));
				this.setVisible(false);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
			}
			desconectar(conectar("TallerJava","usuarioTaller","Studium2018;"));
			
			
			
			Calendar horaFecha = Calendar.getInstance();
			int hora,minutos,dia,mes,anyo;
			hora = horaFecha.get(Calendar.HOUR_OF_DAY);
			minutos = horaFecha.get(Calendar.MINUTE);
			dia = horaFecha.get(Calendar.DAY_OF_MONTH);
			mes = horaFecha.get(Calendar.MONTH)+1;
			anyo = horaFecha.get(Calendar.YEAR);
			try {
				FileWriter fw = new FileWriter("movimientos.log", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter outPut = new PrintWriter(bw);
				outPut.print("["+dia+"/"+mes+"/"+anyo+"]["+hora+":"+minutos+"] "+"["+user+"]"+"["+sentencia+"]"+"\n");
				outPut.close();
				bw.close();
				fw.close();
			} catch(IOException ioe) {
				System.out.print("Error");
			}
		
		}
		
		else if (btnLimpiar.equals(ae.getSource())) 
		{
			txtFecha.selectAll();
			txtFecha.setText("");
		}

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		this.setVisible(false);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
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

	public void ejecutarIDA(String sentencia, Connection c) 
	{
		try
		{
			Statement statement = c.createStatement();
			statement.executeUpdate(sentencia);
			JOptionPane.showMessageDialog(null,"Factura creada","Factura creada con éxito", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);

		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}

	}


}


