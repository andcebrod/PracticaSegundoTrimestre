package es.studium.PracticaSegundoTrimestre;


import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LineaReparacionRecs extends JFrame 

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
	JLabel Subtotal = new JLabel("Subtotal");
	JLabel lblTotal = new JLabel("Total");
	
	DefaultTableModel modelo = new DefaultTableModel();
	JTable tablaRecambios= new JTable(modelo);
	
	JTextArea txtArticulos = new JTextArea(20,20);
	
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
	
	public LineaReparacionRecs(int FacturaSeleccionada) 
	{
		FactSelec = FacturaSeleccionada;
		String idFacturaFK = Integer.toString(FactSelec);
		lblFactura.setText(idFacturaFK);
		this.setLayout(new GridLayout(5,1));
		this.setSize(500, 400);
		pnl1.add(lblTituloFactura);
		pnl1.add(lblFactura);
		this.add(pnl1);
		pnl2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Detalles"));
		pnl2.add(lblRecambio);
		txtTotal.setEditable(false);
		txtArticulos.setEditable(false);
		recambios.add("Elige un articulo...");
		
		//Selec de recambios para choice
		
		pnl2.add(recambios);
		pnl2.add(lblCantidad);
		pnl2.add(txtCantidad);
		pnl2.add(btnAgregar);
		
		
		pnl3.add(tablaRecambios);
		
		
		pnl4.add(lblTotal);
		pnl4.add(txtTotal);
		pnl5.add(btnAceptar);
		pnl5.add(btnCancelar);
		this.add(pnl2);
		this.add(pnl3);
		this.add(pnl4);
		this.add(pnl5);
		this.setVisible(true);
		
	}
	
}
