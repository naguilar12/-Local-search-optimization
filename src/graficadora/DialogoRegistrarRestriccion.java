package graficadora;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DialogoRegistrarRestriccion extends JDialog implements ActionListener{

	public final static String ACEPTAR = "ACEPTAR";

	JTextField[][] textRestricciones;
	JButton submit;

	int numRestricciones;
	Interfaz interfaz;
	PlanoR2 plano;


	public DialogoRegistrarRestriccion(int numRestricciones, Interfaz interfaz, PlanoR2 plano) {
		this.plano = plano;
		this.interfaz = interfaz;
		this.numRestricciones = numRestricciones;
		setSize(700, 150);
		setLayout(new GridLayout(numRestricciones + 2, 5));
		add(new JLabel(""));
		add(new JLabel("Coeficiente X"));
		add(new JLabel("Coeficiente Y"));
		add(new JLabel("Signo(<= , >= , =)"));
		add(new JLabel("Lado derecho"));

		textRestricciones = new JTextField[numRestricciones][4];

		for (int i = 0; i < numRestricciones; i++) {
			textRestricciones[i][0] = new JTextField();
			textRestricciones[i][1] = new JTextField();
			textRestricciones[i][2] = new JTextField();
			textRestricciones[i][3] = new JTextField();

			add(new JLabel((i+1)+""));
			add(textRestricciones[i][0]);
			add(textRestricciones[i][1]);
			add(textRestricciones[i][2]);
			add(textRestricciones[i][3]);
		}

		submit = new JButton("Aceptar");
		submit.setActionCommand(ACEPTAR);
		submit.addActionListener(this);
		add(new JLabel(""));
		add(new JLabel(""));
		add(submit);
	}

	public void agregarRestricciones() {

		String[][] restricciones = interfaz.getRestricciones();

		for(int i = 0; i < numRestricciones; i++) {
			restricciones[i][0] = textRestricciones[i][0].getText();
			restricciones[i][1] = textRestricciones[i][1].getText();
			restricciones[i][2] = textRestricciones[i][2].getText();
			restricciones[i][3] = textRestricciones[i][3].getText();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(ACEPTAR)) {
			try {
				for(int i = 0; i < numRestricciones; i++) {
					Integer.parseInt(textRestricciones[i][0].getText());
					Integer.parseInt(textRestricciones[i][1].getText());
					Integer.parseInt(textRestricciones[i][3].getText());
					if(!textRestricciones[i][2].getText().equals("=") && !textRestricciones[i][2].getText().equals(">=") && !textRestricciones[i][2].getText().equals("<=") )
						throw new Exception();
				}
				
				agregarRestricciones();
				plano.repaint();
				setVisible(false);
				interfaz.dialogoCoordenadaInicial();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(interfaz, "Ingrese numeros validos y signos validos sin espacios");
			}


		}

	}

}
