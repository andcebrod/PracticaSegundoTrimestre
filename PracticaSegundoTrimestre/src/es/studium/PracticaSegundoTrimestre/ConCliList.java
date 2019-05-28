package es.studium.PracticaSegundoTrimestre;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;


public class ConCliList extends JFrame implements WindowListener, ActionListener, TextListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DefaultTableModel modelo = new DefaultTableModel();
	JTable tablaClientes= new JTable(modelo);
	JButton btnAceptar = new JButton("Aceptar");
	JButton btnPDF = new JButton("Exportar a PDF");
	JPanel pnl2 = new JPanel();
	ResultSet rs;

	Document documento = new Document();

	public ConCliList() {
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setSize(600,200);
		this.setTitle("Consulta de Clientes");

		this.add(new JScrollPane(tablaClientes),BorderLayout.CENTER);
		pnl2.add(btnAceptar);
		pnl2.add(btnPDF);
		this.add(pnl2, BorderLayout.SOUTH);
		btnAceptar.addActionListener(this);
		btnPDF.addActionListener(this);

		modelo.addColumn("Nº Cliente");
		modelo.addColumn("Nombre");
		modelo.addColumn("Dirección");
		modelo.addColumn("Teléfono");

		rs = ejecutarSelect("SELECT * FROM clientes", conectar("TallerJava","root","Studium2018;"));
		try {

			while (rs.next())
			{
				Object [] fila = new Object[4];

				for (int i=0;i<4;i++) {
					fila[i] = rs.getObject(i+1);
				}
				modelo.addRow(fila); 
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		desconectar(conectar("practicamvc","root" ,"Studium2018;"));
		tablaClientes.setEnabled(false);


		this.addWindowListener(this);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new ConCliList();
	}

	@Override
	public void textValueChanged(TextEvent arg0) {}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{

		if(btnAceptar.equals(ae.getSource())) {
			this.setVisible(false);
		} else if (btnPDF.equals(ae.getSource())) 
		{	
			FileOutputStream ficheroPdf;
			try {
				ficheroPdf = new FileOutputStream("clientes.pdf");
				PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
				documento.open();
				PdfPTable tabla = new PdfPTable(4);
				
				Paragraph idCliente = new Paragraph("Nº Cliente");
				idCliente.getFont().setStyle(Font.BOLD);
				idCliente.getFont().setSize(15);
				tabla.addCell(idCliente);

				Paragraph nombreCliente = new Paragraph("Nombre");
				nombreCliente.getFont().setStyle(Font.BOLD);
				nombreCliente.getFont().setSize(15);
				tabla.addCell(nombreCliente);

				Paragraph direccionCliente = new Paragraph("Dirección");
				direccionCliente.getFont().setStyle(Font.BOLD);
				direccionCliente.getFont().setSize(15);
				tabla.addCell(direccionCliente);

				Paragraph telefono = new Paragraph("Teléfono");
				telefono.getFont().setStyle(Font.BOLD);
				telefono.getFont().setSize(15);
				tabla.addCell(telefono);

				tabla.addCell("Teléfono");
				ResultSet Co = ejecutarSelect("SELECT * FROM clientes;", conectar("TallerJava","root" ,"Studium2018;"));
				try {
					while (Co.next())
					{
						for (int i=0;i<4;i++) {
							if(i==0) {
								tabla.addCell(Co.getString("idCliente")); 
							} else if(i==1) {
								tabla.addCell(Co.getString("nombreCliente"));
							} else if(i==2) {
								tabla.addCell(Co.getString("direccionCliente"));
							} else if(i==3) {
								tabla.addCell(Co.getString("telefonoCliente"));
							}
						}
					}
					documento.add(tabla);
					documento.close();
					JOptionPane.showMessageDialog(null,"Documento pdf creado correctamente.","Documento creado", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
	public void windowDeactivated(WindowEvent arg0) {


	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {


	}
	@Override
	public void windowIconified(WindowEvent arg0) {


	}
	@Override
	public void windowOpened(WindowEvent arg0) {

	}
	public Connection conectar(String baseDatos, String usuario, String clave)
	{
		String driver = "com.mysql.jdbc.Driver";
		String url ="jdbc:mysql://localhost:3306/"+baseDatos+"?autoReconnect=true&useSSL=false";
		String login = usuario;
		String password = clave;
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
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


