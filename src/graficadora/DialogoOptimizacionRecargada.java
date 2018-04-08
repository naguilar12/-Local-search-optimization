package graficadora;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DialogoOptimizacionRecargada extends JDialog implements ActionListener{
	
	public final static String ACEPTAR = "Aceptar";
	
	JTextField direccionx1 = new JTextField();
	JTextField direccionx2 = new JTextField();
	JTextField direccionx3 = new JTextField();
	
	JTextField direcciony1 = new JTextField();
	JTextField direcciony2 = new JTextField();
	JTextField direcciony3 = new JTextField();
	
	JTextField longitudPaso = new JTextField();
	
	JButton aceptar = new JButton(ACEPTAR);
	
	Interfaz interfaz;
	
	public DialogoOptimizacionRecargada(Interfaz interfaz) {
		this.interfaz = interfaz;
		setLayout(new GridLayout(5, 3));
		setSize(500, 250);
		aceptar.setActionCommand(ACEPTAR);
		aceptar.addActionListener(this);
		
		add(new JLabel("Punto Nuevo"));
		add(new JLabel("Direccion X"));
		add(new JLabel("Direccion Y"));
		
		add(new JLabel(1+""));
		add(direccionx1);
		add(direcciony1);
		
		add(new JLabel(2+""));
		add(direccionx2);
		add(direcciony2);
		
		add(new JLabel(3+""));
		add(direccionx3);
		add(direcciony3);
		
		add(new JLabel("Longitud de paso"));
		add(longitudPaso);
		add(aceptar);
		
		setLocationRelativeTo(interfaz);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(ACEPTAR)) {
			interfaz.tresPuntosUsuario[0][0] = Integer.parseInt(direccionx1.getText());
			interfaz.tresPuntosUsuario[0][1] = Integer.parseInt(direcciony1.getText());
			
			interfaz.tresPuntosUsuario[1][0] = Integer.parseInt(direccionx2.getText());
			interfaz.tresPuntosUsuario[1][1] = Integer.parseInt(direcciony2.getText());
			
			interfaz.tresPuntosUsuario[2][0] = Integer.parseInt(direccionx3.getText());
			interfaz.tresPuntosUsuario[2][1] = Integer.parseInt(direcciony3.getText());
			
			interfaz.longitudPasoUsuario = Integer.parseInt(longitudPaso.getText());
			interfaz.buscarConPuntosUsuario();
			setVisible(false);
		}
		
	}

}
