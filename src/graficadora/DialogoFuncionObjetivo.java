package graficadora;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DialogoFuncionObjetivo extends JDialog implements ActionListener{
	
	public final static String ACEPTAR = "ACEPTAR";

	
	Interfaz interfaz;
	
	JTextField valorx;
	JTextField valory;
	JTextField objetivo;
	JTextField exponentex;
	JTextField exponentey;

	
	
	JButton aceptar;
	
	public DialogoFuncionObjetivo (Interfaz interfaz) {
		this.interfaz = interfaz; 
		setLayout(new GridLayout(6, 2));
		setSize(250, 200);
		
		setTitle("Valores funcion objetivo");
		
		valorx = new JTextField();
		valory = new JTextField();
		objetivo = new JTextField();
		aceptar = new JButton("Aceptar");
		aceptar.setActionCommand(ACEPTAR);
		aceptar.addActionListener(this);
		exponentex = new JTextField();
		exponentey = new JTextField();
		
		add(new JLabel("Coeficiente x"));
		add(valorx);
		
		add(new JLabel("Exponente x"));
		add(exponentex);
		
		add(new JLabel("Coeficiente y"));
		add(valory);
		
		add(new JLabel("Exponente y"));
		add(exponentey);
		
		add(new JLabel("max/min"));
		add(objetivo);
		
		add(new JLabel(""));
		add(aceptar);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals(ACEPTAR)) {
			try {
				Integer.parseInt(valorx.getText());
				Integer.parseInt(valory.getText());
				if(!objetivo.getText().equals("max") && !objetivo.getText().equals("min"))
					throw new Exception();
				Integer.parseInt(exponentex.getText());
				Integer.parseInt(exponentey.getText());		
				
				interfaz.valoresFO(valorx.getText(), valory.getText(), objetivo.getText(), exponentex.getText(), exponentey.getText());
				setVisible(false);
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(interfaz, "Ingrese numeros validos y en el campo de objetivo escriba max o min sin espacios");
			}
			
		}
	}

}
