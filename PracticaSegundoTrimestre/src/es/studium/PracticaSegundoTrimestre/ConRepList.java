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

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ConRepList extends JFrame implements WindowListener, ActionListener, TextListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel modelo = new DefaultTableModel();
	JTable tablaReparaciones= new JTable(modelo);
	JButton btnAceptar = new JButton("Aceptar");
	JButton btnPDF = new JButton("Crear Pdf");
	JPanel pnl2 = new JPanel();
	ResultSet rs;
	int reparado;
	
	Document documento = new Document();

	public ConRepList() 
	{
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setSize(600,200);
		this.setTitle("Consulta de Reparaciones");

		this.add(new JScrollPane(tablaReparaciones),BorderLayout.CENTER);
		pnl2.add(btnAceptar);
		pnl2.add(btnPDF);
		this.add(pnl2, BorderLayout.SOUTH);
		btnAceptar.addActionListener(this);
		btnPDF.addActionListener(this);

		modelo.addColumn("Nº Reparacion");
		modelo.addColumn("Avería");
		modelo.addColumn("Fecha Entrada");
		modelo.addColumn("Fecha de Salida");
		modelo.addColumn("Reparado");

		rs = ejecutarSelect("SELECT * FROM reparaciones", conectar("TallerJava","root","Studium2018;"));
		try {

			while (rs.next())
			{
				Object [] fila = new Object[5];

				for (int i=0;i<5;i++) 
				
					if(i==4) 
					{
						reparado = Integer.parseInt(rs.getString("reparado"));
						if(reparado==1) {
							 fila[i] = "Reparado";
						} else {
							fila[i] = "No reparado";
						}
					} 
					else 
					{
						fila[i] = rs.getObject(i+1);
						
					}
					modelo.addRow(fila); 
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		desconectar(conectar("TallerJava","root" ,"Studium2018;"));
		tablaReparaciones.setEnabled(false);


		this.addWindowListener(this);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new ConRepList();
	}
	@Override
	public void textValueChanged(TextEvent arg0) {}
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(btnAceptar.equals(ae.getSource())) {
			this.setVisible(false);
		} else if (btnPDF.equals(ae.getSource())) {
			FileOutputStream ficheroPdf;
			try {
				ficheroPdf = new FileOutputStream("reparaciones.pdf");
				PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
				documento.open();
				PdfPTable tabla = new PdfPTable(5);
				
				Paragraph idReparacion = new Paragraph("Nº Reparación");
				idReparacion.getFont().setStyle(Font.BOLD);
				idReparacion.getFont().setSize(15);
				tabla.addCell(idReparacion);

				Paragraph averia = new Paragraph("Avería");
				averia.getFont().setStyle(Font.BOLD);
				averia.getFont().setSize(15);
				tabla.addCell(averia);

				Paragraph fechaEntrada = new Paragraph("Fecha de Entrada");
				fechaEntrada.getFont().setStyle(Font.BOLD);
				fechaEntrada.getFont().setSize(15);
				tabla.addCell(fechaEntrada);

				Paragraph fechaSalida = new Paragraph("Fecha de Salida");
				fechaSalida.getFont().setStyle(Font.BOLD);
				fechaSalida.getFont().setSize(15);
				tabla.addCell(fechaSalida);
				
				Paragraph reparado = new Paragraph("Reparado");
				reparado.getFont().setStyle(Font.BOLD);
				reparado.getFont().setSize(15);
				tabla.addCell(reparado);
				
				ResultSet Co = ejecutarSelect("SELECT * FROM reparaciones;", conectar("TallerJava","root" ,"Studium2018;"));
				try {
					while (Co.next())
					{
						for (int i=0;i<5;i++) {
							if(i==0) {
								tabla.addCell(Co.getString("idReparacion")); 
							} else if(i==1) {
								tabla.addCell(Co.getString("averia"));
							} else if(i==2) {
								tabla.addCell(Co.getString("fechaEntrada"));
							} else if(i==3) {
								tabla.addCell(Co.getString("fechaSalida"));
							}else if(i==4) {
								if(Co.getString("reparado").equals("0")) {
									tabla.addCell("No reparado");
								} else if (Co.getString("reparado").equals("1"))
								{
									tabla.addCell("Reparado");
								}
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

}
