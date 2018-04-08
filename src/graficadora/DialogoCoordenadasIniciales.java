package graficadora;

import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DialogoCoordenadasIniciales extends JDialog implements ActionListener{
	
	public final static String ACEPTAR = "ACEPTAR";
	
	Interfaz interfaz;
	
	JTextField x0Text;
	JTextField y0Text;
	
	JButton aceptar;
	
	
	public DialogoCoordenadasIniciales(Interfaz interfaz) {
		setTitle("Punto Inicial");
		this.interfaz = interfaz; 
		setLayout(new GridLayout(3, 2));
		setSize(200, 150);
		
		x0Text = new JTextField();
		y0Text = new JTextField();
		
		add(new JLabel("X0"));
		add(x0Text);
		add(new JLabel("Y0"));
		add(y0Text);
		
		aceptar = new JButton("Aceptar");
		aceptar.setActionCommand(ACEPTAR);
		aceptar.addActionListener(this);
		add(new JLabel(""));
		add(aceptar);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(ACEPTAR)) {
			interfaz.x = Integer.parseInt(x0Text.getText());
			interfaz.y = Integer.parseInt(y0Text.getText());
			interfaz.ubicarPuntoInicial();
			setVisible(false);
		}
	}
}
