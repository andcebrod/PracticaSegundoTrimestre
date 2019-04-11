package es.studium.PracticaSegundoTrimestre;

import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ElCliList implements WindowListener, ActionListener, TextListener, ItemListener{

	JFrame ventanaElCliList = new JFrame ("Buscar cliente para eliminar");
	JLabel lblBuscarCli = new JLabel ("Buscar cliente para eliminar:");
	Choice ListaCli = new Choice();

	TextField idCliente = new TextField(20);
	TextField nombreCliente = new TextField(20);
	int idClienteBorrar;

	JDialog dlgElCli = new JDialog(ventanaElCliList, "Eliminar Cliente");
	JLabel lblEliminar = new JLabel("¿Está seguro de eliminar al cliente?");
	JButton btnEliminar = new JButton ("Eliminar");
	JButton btnCancelar = new JButton ("Cancelar");
	JDialog dlgEliminado = new JDialog(dlgElCli, "Cliente Eliminado");
	JLabel lblEliminado = new JLabel("Cliente Eliminado");
	Label mCliente = new Label("");
	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();

	String s="";
	int idEmpleadoBorrar;
	int cerrar = 0;

	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TallerJava?autoReconnect=true&useSSL=false";
	String login = "root";
	String password = "Studium2018;";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs=null;

	public ElCliList() {
		ventanaElCliList.setLayout(new GridLayout(2,1));
		ventanaElCliList.setLocationRelativeTo(null);
		ventanaElCliList.setSize(400,300);

		pnl1.setLayout(new FlowLayout());
		pnl2.setLayout(new FlowLayout());

		pnl1.add(lblBuscarCli);
		pnl2.add(ListaCli);

		ListaCli.addItemListener(this);
		ListaCli.add("Seleccionar cliente a eliminar");


		ventanaElCliList.add(pnl1);
		ventanaElCliList.add(pnl2);


		try
		{
			Class.forName(driver);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Se ha producido un error al cargar el Driver");
		}
		//Establecer la conexión con la base de datos
		try
		{
			connection = DriverManager.getConnection(url, login, password);
		}
		catch(SQLException e)
		{
			System.out.println("Se produjo un error al conectar a la Base de Datos");
		}
		//Preparar el statement
		try
		{
			statement =connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs= statement.executeQuery("SELECT * FROM clientes");
			while(rs.next())
			{
				s=Integer.toString(rs.getInt("idCliente"));
				s = s + "-"+ rs.getString("nombreCliente");
				ListaCli.add(s);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:"+e.toString());
		}

		ventanaElCliList.addWindowListener(this);
		ventanaElCliList.setVisible(true);

		dlgElCli.setLocationRelativeTo(null);
		dlgElCli.setLayout(new FlowLayout());
		dlgElCli.setSize(230,100);
		dlgElCli.add(lblEliminar);
		dlgElCli.add(btnEliminar);
		btnEliminar.addActionListener(this);
		dlgElCli.add(btnCancelar);
		btnCancelar.addActionListener(this);
		dlgElCli.addWindowListener(this);
		dlgElCli.setVisible(false);

		dlgEliminado.setLocationRelativeTo(null);
		dlgEliminado.setLayout(new FlowLayout());
		dlgEliminado.setSize(230,100);
		dlgEliminado.add(lblEliminado);
		dlgEliminado.addWindowListener(this);
		dlgEliminado.setVisible(false);

	}
	@Override
	public void textValueChanged(TextEvent arg0) {}

	public void itemStateChanged(ItemEvent ie) {
		// TODO Auto-generated method stub
		// Mostraremos dialogo con los datos cargados
		String[] array = ie.getItem().toString().split("-");
		idClienteBorrar = Integer.parseInt(array[0]);
		cerrar = 1;
		dlgElCli.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{

		// Hemos pulsado borrar
		if(btnEliminar.equals(ae.getSource()))
		{
			try
			{
				statement.executeUpdate("DELETE FROM clientes WHERE idCliente="+idClienteBorrar);
				cerrar = 1;
				dlgEliminado.setVisible(true);
			}
			catch(SQLException se)
			{
				System.out.println("Error en la sentencia SQL"+se.toString());
			}
		}
		else if (btnCancelar.equals(ae.getSource()))
		{
			cerrar = 0;
			dlgElCli.setVisible(false);
		}
	}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		switch(cerrar)
		{
		// Cerrar Programa Principal
		case 0:
			//Cerrar los elementos de la base de datos
			try
			{
				statement.close();
				connection.close();
			}
			catch(SQLException e)
			{
				System.out.println("Error al cerrar "+e.toString());
			}
			System.exit(0);
			break;
			// Cerrar d
		case 1:
			cerrar = 0;
			dlgEliminado.setVisible(false);
			dlgElCli.setVisible(false);
			break;
			// Cerrar dialogo
		case 2:
			cerrar = 0;
			dlgElCli.setVisible(false);
			break;
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
