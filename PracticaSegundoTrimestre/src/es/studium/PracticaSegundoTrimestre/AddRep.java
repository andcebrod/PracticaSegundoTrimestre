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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.*;

public class AddRep extends JFrame implements WindowListener, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String user;
	
	JLabel lblAveriaRep = new JLabel ("Avería:");
	JLabel lblFechaEntradaRep = new JLabel ("Fecha de Entrada:");
	JLabel lblFechaSalidaRep = new JLabel ("Fecha de Salida:");
	JLabel lblReparadoRep = new JLabel ("Reparado:");


	JTextField txtAveriaRep = new JTextField(10);
	JTextField txtFechaEntradaRep = new JTextField(10);
	JTextField txtFechaSalidaRep = new JTextField(10);
	ButtonGroup  chkReparadoRep = new ButtonGroup ();
	JRadioButton  chkSiRep = new JRadioButton ("Sí", false);
	JRadioButton  chkNoRep = new JRadioButton ("No", true);

	JButton btnCrear = new JButton("Crear Reparación");
	JButton btnLimpiar = new JButton("Limpiar");
	
	Calendar horaFecha = Calendar.getInstance();

	JPanel pnlPanel = new JPanel();
	JPanel pnlPanel2 = new JPanel();
	JPanel pnlPanel3 = new JPanel();
	JPanel pnlPanel4 = new JPanel();
	JPanel pnlPanel5 = new JPanel();
	
	int dia,mes,anyo;

	public AddRep(String usuario) 
	{
		user = usuario;
		this.setTitle("Añadir Reparación");
		this.setLayout(new GridLayout(5,2));
		this.setLocationRelativeTo(null);
		this.setSize(400,300);

		pnlPanel.setLayout(new FlowLayout());
		pnlPanel2.setLayout(new FlowLayout());
		pnlPanel3.setLayout(new FlowLayout());
		pnlPanel4.setLayout(new FlowLayout());
		pnlPanel5.setLayout(new FlowLayout());

		pnlPanel.add(lblAveriaRep);
		pnlPanel.add(txtAveriaRep);
		this.add(pnlPanel);
		
		dia = horaFecha.get(Calendar.DAY_OF_MONTH);
		mes = horaFecha.get(Calendar.MONTH)+1;
		anyo = horaFecha.get(Calendar.YEAR);

		pnlPanel2.add(lblFechaEntradaRep);
		pnlPanel2.add(txtFechaEntradaRep);
		txtFechaEntradaRep.setText(anyo+"-"+mes+"-"+dia);
		this.add(pnlPanel2);

		pnlPanel3.add(lblFechaSalidaRep);
		pnlPanel3.add(txtFechaSalidaRep);
		this.add(pnlPanel3);

		pnlPanel4.add(lblReparadoRep);
		chkReparadoRep.add(chkSiRep);
		chkReparadoRep.add(chkNoRep);
		pnlPanel4.add(chkSiRep);
		pnlPanel4.add(chkNoRep);
		this.add(pnlPanel4);

		pnlPanel5.add(btnCrear);
		btnCrear.addActionListener(this);
		pnlPanel5.add(btnLimpiar);
		btnLimpiar.addActionListener(this);
		this.add(pnlPanel5);

		this.addWindowListener(this);
		this.setVisible(true);		
	}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{

		if (btnCrear.equals(ae.getSource())) 
		{
			if(chkSiRep.isSelected()) 
			{
				String sentencia1 = "INSERT INTO reparaciones VALUES (null, '"+txtAveriaRep.getText()+"', '"+txtFechaEntradaRep.getText()+"','"+txtFechaSalidaRep.getText()+"', '1');";
				ejecutarIDA(sentencia1, conectar("TallerJava","usuarioTaller","Studium2018;"));
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
					outPut.print("["+dia+"/"+mes+"/"+anyo+"]["+hora+":"+minutos+"] "+"["+user+"]"+"["+sentencia1+"]"+"\n");
					outPut.close();
					bw.close();
					fw.close();
				} catch(IOException ioe) {
					System.out.print("Error");
				}
				
			} else if(chkNoRep.isSelected()) 
			{
				String sentencia2 = "INSERT INTO reparaciones VALUES (null, '"+txtAveriaRep.getText()+"', '"+txtFechaEntradaRep.getText()+"','"+txtFechaSalidaRep.getText()+"', '0');";
				ejecutarIDA(sentencia2, conectar("TallerJava","usuarioTaller","Studium2018;"));
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
					outPut.print("["+dia+"/"+mes+"/"+anyo+"]["+hora+":"+minutos+"] "+"["+user+"]"+"["+sentencia2+"]"+"\n");
					outPut.close();
					bw.close();
					fw.close();
				} catch(IOException ioe) {
					System.out.print("Error");
				}
			}

		} else if (btnLimpiar.equals(ae.getSource())) {
			txtAveriaRep.selectAll();
			txtAveriaRep.setText("");
			txtFechaEntradaRep.selectAll();
			txtFechaEntradaRep.setText("");
			txtFechaSalidaRep.selectAll();
			txtFechaSalidaRep.setText("");

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
			JOptionPane.showMessageDialog(null,cnfe.getMessage(),"Error 1", JOptionPane.ERROR_MESSAGE);
		}
		catch (SQLException sqle)
		{
			JOptionPane.showMessageDialog(null,sqle.getMessage(),"Error 2", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error 3", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void ejecutarIDA(String sentencia, Connection c) 
	{
		try
		{
			Statement statement = c.createStatement();
			statement.executeUpdate(sentencia);
			JOptionPane.showMessageDialog(null,"Reparación añadida","Reparación añadida con éxito", JOptionPane.INFORMATION_MESSAGE);

		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error 4", JOptionPane.ERROR_MESSAGE);
		}

	}
}
