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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class ConLineaRepRec extends JFrame implements WindowListener 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblTotal = new JLabel("Total");

	JTextField txtTotal = new JTextField(6);

	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();
	int FactSelec;
	JTextArea txtRecambiosFac = new JTextArea(6,35);

	double Total = 0;
	String recambios;
	double subTotal=0;
	double precio = 0;
	int idReparacion;
	double total = 0;
	String title;
	private final JPanel pnl3 = new JPanel();
	private final JButton btnAceptar = new JButton("Aceptar");
	private final JButton btnImprimirFactura = new JButton("Imprimir Factura");

	public ConLineaRepRec(int idRep,int idFac) 
	{
		recambios="";
		idReparacion = idRep;
		FactSelec = idFac;
		title = "Factura nº: "+Integer.toString(FactSelec);
		this.setLocationRelativeTo(null);
		this.setSize(500, 332);
		this.setTitle(title);
		//Rellenando el TextArea

		ResultSet rsRec = ejecutarSelect("SELECT * FROM incluyen,recambios where idRecambio = idRecambioFK and  idReparacionfk ="+idReparacion+";",conectar("TallerJava","root","Studium2018;"));
		try {
			while (rsRec.next()) {
				recambios =recambios+" "+"- "+rsRec.getString("descripcionRecambio")+", Precio: "+rsRec.getDouble("precioRecambio")+", Cantidad: "+rsRec.getInt("cantidad")+", Subtotal: "+(rsRec.getDouble("precioRecambio")*rsRec.getInt("cantidad"))+"\n";
				total = total+(rsRec.getDouble("precioRecambio")*rsRec.getInt("cantidad"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{484, 0};
		gridBagLayout.rowHeights = new int[]{180, 67, 44, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		pnl1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Desglose"));
		txtRecambiosFac.setText(recambios);
		
				txtRecambiosFac.setEditable(false);
				pnl1.add(txtRecambiosFac);
				GridBagConstraints gbc_pnl1 = new GridBagConstraints();
				gbc_pnl1.fill = GridBagConstraints.BOTH;
				gbc_pnl1.insets = new Insets(0, 0, 5, 0);
				gbc_pnl1.gridx = 0;
				gbc_pnl1.gridy = 0;
				getContentPane().add(pnl1, gbc_pnl1);
		txtTotal.setEditable(false);
		pnl2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Precio Total"));
		pnl2.add(lblTotal);
		txtTotal.setText(Double.toString(total));
		pnl2.add(txtTotal);
		GridBagConstraints gbc_pnl2 = new GridBagConstraints();
		gbc_pnl2.insets = new Insets(0, 0, 5, 0);
		gbc_pnl2.fill = GridBagConstraints.BOTH;
		gbc_pnl2.gridx = 0;
		gbc_pnl2.gridy = 1;
		getContentPane().add(pnl2, gbc_pnl2);
		
		GridBagConstraints gbc_pnl3 = new GridBagConstraints();
		gbc_pnl3.fill = GridBagConstraints.BOTH;
		gbc_pnl3.gridx = 0;
		gbc_pnl3.gridy = 2;
		getContentPane().add(pnl3, gbc_pnl3);
		
		pnl3.add(btnAceptar);
		
		pnl3.add(btnImprimirFactura);
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
