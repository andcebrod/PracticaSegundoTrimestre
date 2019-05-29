package es.studium.PracticaSegundoTrimestre;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ConRecList implements WindowListener, ActionListener, TextListener {
	
	JFrame ventanaConRecList = new JFrame ("Consulta de Recambios");
	DefaultTableModel modelo = new DefaultTableModel();
	JTable tablaRecambios= new JTable(modelo);
	JButton btnAceptar = new JButton("Aceptar");
	JButton btnPDF = new JButton("Crear Pdf");
	JPanel pnl1 = new JPanel();
	ResultSet rs;
	
	Document documento = new Document();
	
	public ConRecList() 
	{
		ventanaConRecList.setLayout(new BorderLayout());
		ventanaConRecList.setLocationRelativeTo(null);
		ventanaConRecList.setSize(600,200);
		ventanaConRecList.add(new JScrollPane(tablaRecambios),BorderLayout.CENTER);
		
		modelo.addColumn("Nº Recambio");
		modelo.addColumn("Descripción");
		modelo.addColumn("Unidades");
		modelo.addColumn("Precio");
		
		
		rs = ejecutarSelect("SELECT * FROM recambios", conectar("TallerJava","root","Studium2018;"));
		try {

			while (rs.next())
			{
			   Object [] fila = new Object[4];

			   for (int i=0;i<4;i++)
			      fila[i] = rs.getObject(i+1);
			   modelo.addRow(fila); 
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		desconectar(conectar("TallerJava","root" ,"Studium2018;"));
		tablaRecambios.setEnabled(false);
		pnl1.add(btnAceptar);
		pnl1.add(btnPDF);
		btnAceptar.addActionListener(this);
		btnPDF.addActionListener(this);
		ventanaConRecList.add(pnl1, BorderLayout.SOUTH);
		ventanaConRecList.addWindowListener(this);
		ventanaConRecList.setVisible(true);
	}
	
	@Override
	public void textValueChanged(TextEvent arg0) {}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		
		if(btnAceptar.equals(ae.getSource())) {
			ventanaConRecList.setVisible(false);
		} else if (btnPDF.equals(ae.getSource())) 
		{
			FileOutputStream ficheroPdf;
			try {
				ficheroPdf = new FileOutputStream("recambios.pdf");
				PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
				documento.open();
				PdfPTable tabla = new PdfPTable(4);
				
				Paragraph idRecambio = new Paragraph("Nº Recambio");
				idRecambio.getFont().setStyle(Font.BOLD);
				idRecambio.getFont().setSize(15);
				tabla.addCell(idRecambio);

				Paragraph descripcionRecambio = new Paragraph("Descripción del Recambio");
				descripcionRecambio.getFont().setStyle(Font.BOLD);
				descripcionRecambio.getFont().setSize(15);
				tabla.addCell(descripcionRecambio);

				Paragraph unidadesRecambio = new Paragraph("Unidades del Recambio");
				unidadesRecambio.getFont().setStyle(Font.BOLD);
				unidadesRecambio.getFont().setSize(15);
				tabla.addCell(unidadesRecambio);

				Paragraph precioRecambio = new Paragraph("Precio del Recambio");
				precioRecambio.getFont().setStyle(Font.BOLD);
				precioRecambio.getFont().setSize(15);
				tabla.addCell(precioRecambio);

				
				ResultSet Co = ejecutarSelect("SELECT * FROM recambios;", conectar("TallerJava","root" ,"Studium2018;"));
				try {
					while (Co.next())
					{
						for (int i=0;i<4;i++) {
							if(i==0) {
								tabla.addCell(Co.getString("idRecambio")); 
							} else if(i==1) {
								tabla.addCell(Co.getString("descripcionRecambio"));
							} else if(i==2) {
								tabla.addCell(Co.getString("unidadesRecambio"));
							} else if(i==3) {
								tabla.addCell(Co.getString("precioRecambio"));
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

			desconectar(conectar("TallerJava","root" ,"Studium2018;"));
		}
	}
	public static void main(String[] args) {
		new ConRecList();
	}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		ventanaConRecList.setVisible(false);
		
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
