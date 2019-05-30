package es.studium.PracticaSegundoTrimestre;


import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;


public class ConLineaRepRec extends JFrame implements WindowListener 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblTituloFactura = new JLabel("Factura nº ");
	JLabel lblFactura = new JLabel("");
	JLabel lblTotal = new JLabel("Total");

	JTextField txtTotal = new JTextField(6);

	JPanel pnl1 = new JPanel();
	JPanel pnl3 = new JPanel();
	JPanel pnl4 = new JPanel();
	int FactSelec;
	JTextArea txtRecambiosFac = new JTextArea(6,35);

	double Total = 0;
	String recambios;
	double subTotal=0;
	double precio = 0;
	int idReparacion;
	double total = 0;

	public ConLineaRepRec(int idRep,int idFac) 
	{
		recambios="";
		idReparacion = idRep;
		FactSelec = idFac;
		lblFactura.setText(Integer.toString(FactSelec));
		getContentPane().setLayout(new GridLayout(3,1));
		this.setLocationRelativeTo(null);
		this.setSize(500, 400);
		pnl1.add(lblTituloFactura);
		pnl1.add(lblFactura);
		this.add(pnl1);
		txtTotal.setEditable(false);
		pnl3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Desglose"));
		//Rellenando el TextArea

		ResultSet rsRec = ejecutarSelect("SELECT * FROM incluyen,recambios where idRecambio = idRecambioFK and  idReparacionfk ="+idReparacion+";",conectar("TallerJava","root","Studium2018;"));
		try {
			while (rsRec.next()) {
				recambios = recambios+" "+rsRec.getString("descripcionRecambio")+", Precio: "+rsRec.getDouble("precioRecambio")+", Cantidad: "+rsRec.getInt("cantidad")+", Subtotal: "+(rsRec.getDouble("precioRecambio")*rsRec.getInt("cantidad"))+"\n";
				total = total+(rsRec.getDouble("precioRecambio")*rsRec.getInt("cantidad"));
			}
			txtRecambiosFac.setText(recambios);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}

		txtRecambiosFac.setEditable(false);
		pnl3.add(txtRecambiosFac);
		getContentPane().add(pnl3);
		pnl4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Precio Total"));
		pnl4.add(lblTotal);
		txtTotal.setText(Double.toString(total));
		pnl4.add(txtTotal);
		getContentPane().add(pnl4);
		this.setVisible(true);

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
		// TODO Auto-generated method stub
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
