package graficadora;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DialogoFuncionObjetivo extends JDialog implements ActionListener{
	
	public final static String ACEPTAR = "ACEPTAR";

	
	Interfaz interfaz;
	
	JTextField valorx;
	JTextField valory;
	JTextField objetivo;
	
	
	JButton aceptar;
	
	public DialogoFuncionObjetivo (Interfaz interfaz) {
		this.interfaz = interfaz; 
		setLayout(new GridLayout(4, 2));
		setSize(250, 200);
		
		setTitle("Valores funcion objetivo");
		
		valorx = new JTextField();
		valory = new JTextField();
		objetivo = new JTextField();
		aceptar = new JButton("Aceptar");
		aceptar.setActionCommand(ACEPTAR);
		aceptar.addActionListener(this);
		
		add(new JLabel("Coeficiente x"));
		add(valorx);
		add(new JLabel("Coeficiente y"));
		add(valory);
		add(new JLabel("max/min"));
		add(objetivo);
		add(new JLabel(""));
		add(aceptar);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals(ACEPTAR)) {
			interfaz.valoresFO(valorx.getText(), valorx.getText(), objetivo.getText());
			setVisible(false);
		}
	}

}