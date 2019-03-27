package es.studium.PracticaSegundoTrimestre;


import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
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

import javax.swing.*;

public class ElRecList implements WindowListener, ActionListener,TextListener, ItemListener {

	JFrame ventanaElRecList = new JFrame ("Buscar recambio para eliminar");
	JLabel lblBuscarElRec = new JLabel ("Buscar recambio:");
	Choice ListaRec = new Choice();

	JDialog dlgElRec = new JDialog(ventanaElRecList, "Eliminar recambio");
	JLabel lblEliminar = new JLabel("¿Está seguro de eliminar el recambio?");
	JButton btnEliminar = new JButton ("Eliminar");
	JButton btnCancelar = new JButton ("Cancelar");
	JDialog dlgEliminado = new JDialog(dlgElRec, "Recambio Eliminado");
	JLabel lblEliminado = new JLabel("Recambio Eliminado");

	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();

	String s="";
	int idRecambioBorrar;
	int cerrar = 0;

	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TallerJava?autoReconnect=true&useSSL=false";
	String login = "root";
	String password = "Studium2018;";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs=null;

	public ElRecList() 
	{
		ventanaElRecList.setLayout(new GridLayout(3,1));
		ventanaElRecList.setLocationRelativeTo(null);
		ventanaElRecList.setSize(400,300);

		pnl1.add(lblBuscarElRec);
		pnl2.add(ListaRec);
		ListaRec.addItemListener(this);

		ventanaElRecList.add(pnl1);
		ventanaElRecList.add(pnl2);

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
			rs= statement.executeQuery("SELECT * FROM recambios");
			while(rs.next())
			{
				s=Integer.toString(rs.getInt("idRecambio"));
				s = s + "-"+ rs.getString("descripcionRecambio");
				ListaRec.add(s);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error en la sentencia SQL:"+e.toString());
		}

		dlgElRec.setLocationRelativeTo(null);
		dlgElRec.setLayout(new FlowLayout());
		dlgElRec.setSize(250,100);
		dlgElRec.add(lblEliminar);
		dlgElRec.add(btnEliminar);
		btnEliminar.addActionListener(this);
		dlgElRec.add(btnCancelar);
		btnCancelar.addActionListener(this);
		dlgElRec.addWindowListener(this);
		dlgElRec.setVisible(false);

		dlgEliminado.setLocationRelativeTo(null);
		dlgEliminado.setLayout(new FlowLayout());
		dlgEliminado.setSize(200,100);
		dlgEliminado.add(lblEliminado);
		dlgEliminado.addWindowListener(this);
		dlgEliminado.setVisible(false);

		ventanaElRecList.addWindowListener(this);
		ventanaElRecList.setVisible(true);
	}

	public static void main(String[] args) {new ElRecList();}
	public void itemStateChanged(ItemEvent ie) 
	{
		// TODO Auto-generated method stub
		// Mostraremos dialogo con los datos cargados
		String[] array = ie.getItem().toString().split("-");
		idRecambioBorrar = Integer.parseInt(array[0]);
		cerrar = 1;
		dlgElRec.setVisible(true);
	}
	@Override
	public void textValueChanged(TextEvent arg0) {}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		// Hemos pulsado borrar
		if(btnEliminar.equals(ae.getSource()))
		{
			try
			{
				statement.executeUpdate("DELETE FROM recambios WHERE idRecambio="+idRecambioBorrar);
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
			dlgElRec.setVisible(false);
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
			dlgElRec.setVisible(false);
			break;
			// Cerrar dialogo
		case 2:
			cerrar = 0;
			dlgElRec.setVisible(false);
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
