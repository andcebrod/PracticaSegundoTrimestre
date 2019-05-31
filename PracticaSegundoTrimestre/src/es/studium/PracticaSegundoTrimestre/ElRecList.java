package es.studium.PracticaSegundoTrimestre;

import java.awt.*;
import java.awt.event.*;
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

public class ElRecList extends JFrame implements WindowListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Choice ListaRec = new Choice();

	JButton btnBorrar = new JButton("Eliminar recambio");
	int idRecambioBorrar;

	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();

	int idRecBorrar;
	ResultSet con;
	String user="";

	public ElRecList(String usuario) 
	{
		user = usuario;
		this.setTitle("Eliminar recambios");
		this.setLayout(new GridLayout(2,1));
		this.setLocationRelativeTo(null);
		this.setSize(400,200);
		ListaRec.add("Seleccionar recambio a eliminar");
		con = ejecutarSelect("SELECT * FROM recambios", conectar("TallerJava", "root", "Studium2018;"));
		try {
			while(con.next())
			{
				String recambios=Integer.toString(con.getInt("idRecambio"));
				recambios = recambios+".-"+con.getString("descripcionRecambio");
				ListaRec.add(recambios);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		desconectar(conectar("TallerJava","root" ,"Studium2018;"));

		pnl1.add(ListaRec);
		pnl2.add(btnBorrar);

		this.add(pnl1);
		this.add(pnl2);
		this.addWindowListener(this);
		btnBorrar.addActionListener(this);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		// TODO Auto-generated method stub
		if(btnBorrar.equals(ae.getSource())) {
			int seleccion = JOptionPane.showOptionDialog( null,"¿Desea eliminar recambio?","Eliminar recambio",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[] { "Eliminar", "Cancelar"},"Cancelar");
			if (seleccion == 0){
				try {
					String[] array= ListaRec.getSelectedItem().toString().split(".-");
					idRecBorrar = Integer.parseInt(array[0]);
				} catch (NumberFormatException Nf) {
					JOptionPane.showMessageDialog(null,"Introduzca recambio válido","Error de recambio", JOptionPane.ERROR_MESSAGE);
				}
				String sentencia = "DELETE FROM recambios where idRecambio ="+idRecBorrar+";";
				ejecutarIDA(sentencia, conectar("TallerJava", "root", "Studium2018;"));
				JOptionPane.showMessageDialog(null,"El recambio "+idRecBorrar+" ha sido eliminado","Recambio eliminado", JOptionPane.INFORMATION_MESSAGE);
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
				this.setVisible(false);
			} else if(seleccion == 1) {

			}
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
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}

	}
	
}
