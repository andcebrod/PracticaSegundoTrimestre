package es.studium.PracticaSegundoTrimestre;

import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class LineaRepRec extends JFrame implements WindowListener, ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblTituloFactura = new JLabel("Factura nº ");
	JLabel lblFactura = new JLabel("");
	JLabel lblRecambio = new JLabel("Recambio:");
	JLabel lblCantidad = new JLabel("Cantidad: ");
	JLabel lblPrecio = new JLabel("Precio");
	JLabel lblTotal = new JLabel("Total");

	Choice recambios = new Choice();
	JTextField txtCantidad = new JTextField(3);
	JTextField txtTotal = new JTextField(6);
	JButton btnAgregar = new JButton("Agregar");
	JButton btnCancelar = new JButton("Cancelar");
	JButton btnAceptar = new JButton("Aceptar");

	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();
	JPanel pnl4 = new JPanel();
	JPanel pnl5 = new JPanel();
	int FactSelec;
	JTextArea txtRecambiosFac = new JTextArea(6,35);

	double total = 0;
	String recambioSeleccionado ="";
	double subTotal=0;
	double precio = 0;
	int idReparacion;

	public LineaRepRec(int idRep, int idFac) {
		idReparacion = idRep;
		FactSelec = idFac;
		lblFactura.setText(Integer.toString(FactSelec));
		this.setLayout(new GridLayout(5,1));
		this.setLocationRelativeTo(null);
		this.setSize(500, 400);
		pnl1.add(lblTituloFactura);
		pnl1.add(lblFactura);
		this.add(pnl1);
		pnl2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Detalles"));
		pnl2.add(lblRecambio);
		txtTotal.setEditable(false);
		recambios.add("Elige un articulo...");

		//Selec de recambios para choice

		ResultSet rsRec = ejecutarSelect("SELECT * FROM recambios;", conectar("TallerJava","usuarioTaller","Studium2018;")); 
		try {
			while(rsRec.next())
			{
				String rec = Integer.toString(rsRec.getInt("idRecambio"));
				rec = rec + "-"+ rsRec.getString("descripcionRecambio");
				recambios.add(rec);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		desconectar(conectar("TallerJava","usuarioTaller" ,"Studium2018;"));

		pnl2.add(recambios);
		pnl2.add(lblCantidad);
		pnl2.add(txtCantidad);
		pnl2.add(btnAgregar);
		this.add(pnl2);
		pnl3.add(txtRecambiosFac);
		this.add(pnl3);
		pnl4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Precio Total"));
		pnl4.add(lblTotal);
		pnl4.add(txtTotal);
		pnl5.add(btnAceptar);
		pnl5.add(btnCancelar);
		btnAgregar.addActionListener(this);
		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);

		this.add(pnl4);
		this.add(pnl5);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{

		//Botón Agregar
		if(btnAgregar.equals(ae.getSource())) 
		{
			try 
			{
				int Cantidad = Integer.parseInt(txtCantidad.getText());
				String[] arrayCod= recambios.getSelectedItem().toString().split("-");
				ResultSet recSelect = ejecutarSelect("SELECT precioRecambio from recambios where idRecambio ="+arrayCod[0]+";",conectar("TallerJava", "usuarioTaller", "Studium2018;"));
				try {
					recSelect.next();
					precio = recSelect.getDouble("precioRecambio");
					subTotal = precio*Cantidad;
					total = total + recSelect.getDouble("precioRecambio")*Cantidad;
					recambioSeleccionado = recambioSeleccionado+" "+recambios.getSelectedItem().toString()+", Precio: "+Double.toString(precio)+", Cantidad:"+Cantidad+", Subtotal:"+Double.toString(subTotal)+"\n";
					txtRecambiosFac.setText(recambioSeleccionado);
					txtTotal.setText(Double.toString(total));
					desconectar(conectar("TallerJava", "usuarioTaller", "Studium2018;"));
					ejecutarIDA("INSERT INTO incluyen values (null,"+idReparacion+","+arrayCod[0]+","+Cantidad+");", conectar("TallerJava", "usuarioTaller", "Studium2018;"));

				} catch (SQLException e){
					JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
				}
				desconectar(conectar("TallerJava","usuarioTaller" ,"Studium2018;"));
			} catch(NumberFormatException nf) {
				JOptionPane.showMessageDialog(null,"Introduzca cantidad o artículo válidos","Error", JOptionPane.ERROR_MESSAGE);
			}
		} 
		//Botón Aceptar
		if(btnAceptar.equals(ae.getSource())) 
		{
			JOptionPane.showMessageDialog(null,"Factura creada correctamente con un total de "+txtTotal.getText(),"Factura creada", JOptionPane.INFORMATION_MESSAGE);
		}
		//Botón Cancelar
		else if(btnCancelar.equals(ae.getSource())) 
		{
			int seleccion = JOptionPane.showOptionDialog( null,"¿Desea salir de la línea de factura?","Salir",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[] { "Salir", "Cancelar"},"Cancelar");
			if (seleccion == 0){
				this.setVisible(false);
			}
		}
	} 
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		int seleccion = JOptionPane.showOptionDialog( null,"¿Desea salir de la línea de factura?","Salir",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[] { "Salir", "Cancelar"},"Cancelar");
		if (seleccion == 0){
			this.setVisible(false);
		}
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
