package es.studium.PracticaSegundoTrimestre;

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

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModRec implements  WindowListener, ActionListener{
	JFrame ventanaModRec = new JFrame ("Modificar recambio:");
	JLabel lblDescripcionRec = new JLabel ("Descripción:");
	JLabel lblUnidadesRec = new JLabel ("Unidades:");
	JLabel lblPrecioRec = new JLabel ("Precio:");
	
	JTextField txtDescripcionRec = new JTextField(10);
	JTextField txtUnidadesRec = new JTextField(10);
	JTextField txtPrecioRec = new JTextField(10);
	
	JButton btnModificar = new JButton("Modificar Recambio");
	JButton btnLimpiar = new JButton("Limpiar");
	
	
	JPanel pnlPanel = new JPanel();
	JPanel pnlPanel2 = new JPanel();
	JPanel pnlPanel3 = new JPanel();
	JPanel pnlPanel4 = new JPanel();
	int idRec = 0;
	String user = "";
	public ModRec(int id, String usuario) 
	{
		user = usuario;
		idRec = id;
		ResultSet rs = ejecutarSelect("SELECT * FROM recambios where idRecambio="+id+";",conectar("TallerJava","root","Studium2018;"));
		try {
			rs.next();
			txtDescripcionRec.selectAll();
			txtDescripcionRec.setText(rs.getString("descripcionRecambio"));
			txtUnidadesRec.selectAll();
			txtUnidadesRec.setText(rs.getString("unidadesRecambio"));
			txtPrecioRec.selectAll();
			txtPrecioRec.setText(Integer.toString(rs.getInt("precioRecambio")));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		desconectar(conectar("TallerJava","root","Studium2018;"));
		
		
		
		ventanaModRec.setLayout(new GridLayout(4,1));
		ventanaModRec.setLocationRelativeTo(null);
		ventanaModRec.setSize(400,300);
		
		pnlPanel.setLayout(new FlowLayout());
		pnlPanel2.setLayout(new FlowLayout());
		pnlPanel3.setLayout(new FlowLayout());
		pnlPanel4.setLayout(new FlowLayout());
		
		pnlPanel.add(lblDescripcionRec);
		pnlPanel.add(txtDescripcionRec);
		ventanaModRec.add(pnlPanel);
		
		pnlPanel2.add(lblUnidadesRec);
		pnlPanel2.add(txtUnidadesRec);
		ventanaModRec.add(pnlPanel2);
		
		pnlPanel3.add(lblPrecioRec);
		pnlPanel3.add(txtPrecioRec);
		ventanaModRec.add(pnlPanel3);
		
		pnlPanel4.add(btnModificar);
		btnModificar.addActionListener(this);
		pnlPanel4.add(btnLimpiar);
		btnLimpiar.addActionListener(this);
		ventanaModRec.add(pnlPanel4);
		
		ventanaModRec.addWindowListener(this);
		ventanaModRec.setVisible(true);

	}

	public static void main(String[] args) {
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		
		if (btnModificar.equals(ae.getSource())) {
			if(txtDescripcionRec.getText().equals("")) {
				JOptionPane.showMessageDialog(null,"Error, Nombre de recambio vacío","Nombre vacío", JOptionPane.ERROR_MESSAGE);

			} else {
				String sentencia = "UPDATE recambios SET descripcionRecambio = '"+txtDescripcionRec.getText()+"', unidadesRecambio = '"+txtUnidadesRec.getText()+"', precioRecambio ="+txtPrecioRec.getText()+" WHERE idRecambio ="+idRec+";";
				ejecutarIDA(sentencia,conectar("TallerJava","root","Studium2018;"));
				desconectar(conectar("TallerJava","root","Studium2018;"));
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
					outPut.print("["+dia+"/"+mes+"/"+anyo+"]["+hora+":"+minutos+"] "+"["+user+"]"+"["+sentencia+"]");
					outPut.close();
					bw.close();
					fw.close();
				} catch(IOException ioe) {
					System.out.print("Error");
				}
			
			}
			
			
		} else if (btnLimpiar.equals(ae.getSource())) {
			txtDescripcionRec.selectAll();
			txtDescripcionRec.setText("");
			txtUnidadesRec.selectAll();
			txtUnidadesRec.setText("");
			txtPrecioRec.selectAll();
			txtPrecioRec.setText("");
		}
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		ventanaModRec.setVisible(false);
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
			JOptionPane.showMessageDialog(null,"Recambio modificado","Recambio modificado con éxito", JOptionPane.INFORMATION_MESSAGE);

		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}

	}
	
}
