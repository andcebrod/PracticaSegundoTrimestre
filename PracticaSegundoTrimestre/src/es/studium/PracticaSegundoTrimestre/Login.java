package es.studium.PracticaSegundoTrimestre;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
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

public class Login implements WindowListener, ActionListener, TextListener {
	
	String driver = "com.mysql.jdbc.Driver";
	String url ="jdbc:mysql://localhost:3306/TallerJava?autoReconnect=true&useSSL=false";
	String login = "root";
	String password = "Studium2018;";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	JFrame ventanaLogin = new JFrame ("Iniciar Sesión");
	
	JLabel lblIniciar = new JLabel("Iniciar Sesión");

	JLabel lblCorreo = new JLabel("Correo Electrónico: ");
	JLabel lblPass = new JLabel("Contraseña:             ");
	JLabel lblCorreoOlvidado = new JLabel("Introduzca su Correo Electrónico: ");
	JLabel lblCorreoOlvidadoOK = new JLabel("Compruebe su correo electrónico");
	
	JPasswordField txtPass = new JPasswordField(20);
	JTextField txtCorreo = new JTextField(20);
	JTextField txtCorreoOlvidado = new JTextField(20);
	
	JButton btnIniciar = new JButton ("Iniciar Sesión");
	JButton btnLimpiar = new JButton ("Limpiar"); 
	JButton btnOlvidePass = new JButton ("Olvidé la contraseña");
	JButton btnOlvidePassSiguiente = new JButton ("Siguiente");
	
	JDialog dlgOlvidada = new JDialog(ventanaLogin, "Contraseña olvidada");
	JDialog dlgOlvidadaOK = new JDialog(ventanaLogin, "Contraseña olvidada ");
	
	JPanel pnlPanel = new JPanel();
	JPanel pnlPanel2 = new JPanel();
	JPanel pnlPanel3 = new JPanel();
	JPanel pnlPanel4 = new JPanel();
	JPanel pnlPanel5 = new JPanel();
	
	public Login() 
	{
		ventanaLogin.setLocationRelativeTo(null);
		ventanaLogin.setSize(400,200);
		ventanaLogin.setLayout(new GridLayout(5,1));
		
		dlgOlvidada.setLocationRelativeTo(null);
		dlgOlvidada.setSize(300, 150);
		dlgOlvidada.setLayout(new FlowLayout());
		dlgOlvidada.setVisible(false);
		
		dlgOlvidada.add(lblCorreoOlvidado);
		dlgOlvidada.add(txtCorreoOlvidado);
		dlgOlvidada.add(btnOlvidePassSiguiente);
		
		dlgOlvidadaOK.setLocationRelativeTo(null);
		dlgOlvidadaOK.setSize(300, 150);
		dlgOlvidadaOK.setLayout(new FlowLayout());
		dlgOlvidadaOK.setVisible(false);
		
		dlgOlvidadaOK.add(lblCorreoOlvidadoOK);
		
		pnlPanel.setLayout(new FlowLayout());
		pnlPanel2.setLayout(new FlowLayout());
		pnlPanel3.setLayout(new FlowLayout());
		pnlPanel4.setLayout(new FlowLayout());
		pnlPanel5.setLayout(new FlowLayout());
		
		pnlPanel.add(lblIniciar);
		ventanaLogin.add(pnlPanel);
		
		pnlPanel2.add(lblCorreo);
		pnlPanel2.add(txtCorreo);
		ventanaLogin.add(pnlPanel2);
		
		pnlPanel3.add(lblPass);
		pnlPanel3.add(txtPass);
		ventanaLogin.add(pnlPanel3);
		
		pnlPanel4.add(btnIniciar);
		pnlPanel4.add(btnLimpiar);
		ventanaLogin.add(pnlPanel4);
		
		pnlPanel5.add(btnOlvidePass);
		ventanaLogin.add(pnlPanel5);

		btnIniciar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		btnOlvidePass.addActionListener(this);
		btnOlvidePassSiguiente.addActionListener(this);
	
		ventanaLogin.addWindowListener(this);
		dlgOlvidada.addWindowListener(this);
		dlgOlvidadaOK.addWindowListener(this);
		ventanaLogin.setVisible(true);
	}

	public static void main(String[] args) 
	{
		new Login();
	}
	@Override
	public void textValueChanged(TextEvent te) {}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(btnIniciar.equals(ae.getSource())) {
			
			if(txtCorreo.getText().equals("administrador@studium.es")) 
			{
			try
			{
				Class.forName(driver);
				String sentencia = "SELECT * FROM usuarios where correoUsuario ='"+txtCorreo.getText()+"' AND claveUsuario = MD5('"+txtPass.getText()+"');";
				connection = DriverManager.getConnection(url, login,password);
				statement =connection.createStatement();
				rs=statement.executeQuery(sentencia);
				if(rs.next()) 
				{
					JOptionPane.showMessageDialog(null,"Login Correcto","Login correcto", JOptionPane.INFORMATION_MESSAGE);
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
						outPut.print("["+dia+"/"+mes+"/"+anyo+"]["+hora+":"+minutos+"] "+"[administrador@studium.es]");
						outPut.close();
						bw.close();
						fw.close();
					} catch(IOException ioe) {
						System.out.print("Error");
					}
					ventanaLogin.setVisible(false);
					new MenuPrincipal();
					
				} else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña erroneos","Error al iniciar sesión", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch (ClassNotFoundException cnfe)
			{
				JOptionPane.showMessageDialog(null, "Error",cnfe.getMessage(), JOptionPane.ERROR_MESSAGE);

			}
			catch (SQLException sqle)
			{
				JOptionPane.showMessageDialog(null, "Error",sqle.getMessage(), JOptionPane.ERROR_MESSAGE);
			}
			finally
			{
				try
				{
					if(connection!=null)
					{
						connection.close();
					}
				}
				catch (SQLException e)
				{
					JOptionPane.showMessageDialog(null, "Error",e.getMessage(), JOptionPane.ERROR_MESSAGE);
				}
			}
			}
			
				else {
					
					try
					{
						Class.forName(driver);
						@SuppressWarnings("deprecation")
						String sentencia = "SELECT * FROM usuarios where correoUsuario ='"+txtCorreo.getText()+"' AND claveUsuario = MD5('"+txtPass.getText()+"');";
						connection = DriverManager.getConnection(url, login,password);
						statement =connection.createStatement();
						rs=statement.executeQuery(sentencia);
						if(rs.next()) 
						{
							JOptionPane.showMessageDialog(null,"Login Correcto","Login correcto", JOptionPane.INFORMATION_MESSAGE);
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
								outPut.print("["+dia+"/"+mes+"/"+anyo+"]["+hora+":"+minutos+"] "+"["+txtCorreo.getText()+"]");
								outPut.close();
								bw.close();
								fw.close();
							} catch(IOException ioe) {
								System.out.print("Error");
							}
							ventanaLogin.setVisible(false);
							new MenuPrincipalUsuario();
							
						} else {
							JOptionPane.showMessageDialog(null, "Usuario o contraseña erroneos","Error al iniciar sesión", JOptionPane.ERROR_MESSAGE);
						}
					}
					catch (ClassNotFoundException cnfe)
					{
						JOptionPane.showMessageDialog(null, "Error",cnfe.getMessage(), JOptionPane.ERROR_MESSAGE);

					}
					catch (SQLException sqle)
					{
						JOptionPane.showMessageDialog(null, "Error",sqle.getMessage(), JOptionPane.ERROR_MESSAGE);
					}
					finally
					{
						try
						{
							if(connection!=null)
							{
								connection.close();
							}
						}
						catch (SQLException e)
						{
							JOptionPane.showMessageDialog(null, "Error",e.getMessage(), JOptionPane.ERROR_MESSAGE);
						}
					}
					
					
					
				}
					/* 
				
				Calendar horaFecha = Calendar.getInstance();
				int hora,minutos,dia,mes,anyo;
				hora = horaFecha.get(Calendar.HOUR_OF_DAY);
				minutos = horaFecha.get(Calendar.MINUTE);
				dia = horaFecha.get(Calendar.DAY_OF_MONTH);
				mes = horaFecha.get(Calendar.MONTH)+1;
				anyo = horaFecha.get(Calendar.YEAR);
				ventanaLogin.setVisible(false);
				try {
					FileWriter fw = new FileWriter("movimientos.log", true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter outPut = new PrintWriter(bw);
					outPut.print("["+dia+"/"+mes+"/"+anyo+"]["+hora+":"+minutos+"] "+"[usuario@studium.es]");
					outPut.close();
					bw.close();
					fw.close();
				} catch(IOException ioe) {
					System.out.print("Error");
				}
				new MenuPrincipalUsuario();
			} else {
				JOptionPane.showMessageDialog(null, "Compruebe que el correo o la contraseña sea correcta.");
			}
			*/
		} else if (btnLimpiar.equals(ae.getSource())) {
			txtCorreo.selectAll();
			txtCorreo.setText("");
			txtPass.selectAll();
			txtPass.setText("");
			
		} else if (btnOlvidePass.equals(ae.getSource())) {
			dlgOlvidada.setVisible(true);
		}
		if(btnOlvidePassSiguiente.equals(ae.getSource())) {
			dlgOlvidadaOK.setVisible(true);
		}
	}
	@Override
	public void windowActivated(WindowEvent we) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		if(ventanaLogin.isActive()) {
			ventanaLogin.setVisible(false);
		}else {
			//System.exit(0);
		}
		if(dlgOlvidada.isActive()) {
			dlgOlvidada.setVisible(false);
		}
		if(dlgOlvidadaOK.isActive()) {
			dlgOlvidada.setVisible(false);
			dlgOlvidadaOK.setVisible(false);
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
