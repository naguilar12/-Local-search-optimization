package graficadora;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 *
 * @author jorge
 */
public class Interfaz extends JFrame implements ActionListener{
	
	int xInicial;
	int yInicial;
	
	int iteracion = 1;
	int punto;
	int foInicial;

	String[][] restricciones;
	int x;
	int y;

	String[] funcionObjetivo;

	int distancia;

	//Dialogo distancia
	JDialog dialogoDistancia;
	public final static String ACEPTAR_DISTANCIA = "Aceptar distancia";

	JTextField distanciaTextField;
	JButton aceptarDistancia;

	ArrayList<int[]> caminoPuntos;


	public static PlanoR2 plano;

	int[][] tresPuntosUsuario = new int[3][2];
	int longitudPasoUsuario;

	boolean conPuntosUsuario;

	ArrayList<String[]> data = new ArrayList<>();

	public Interfaz() {
		setSize(new Dimension(700, 700));
		setDefaultCloseOperation(Interfaz.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo( null );
		setResizable(false);

		//Bordes al panel

		caminoPuntos = new ArrayList<>();

		funcionObjetivo = new String[3];

		int numRestricciones = Integer.parseInt(JOptionPane.showInputDialog(this, "Inserte el número de restricciones"));
		restricciones = new String[numRestricciones][4];

		plano = new PlanoR2(this);

		add( plano, BorderLayout.CENTER);

		setVisible( true );



		DialogoRegistrarRestriccion dialogoRestricciones = new DialogoRegistrarRestriccion(numRestricciones, this,plano);
		dialogoRestricciones.setLocationRelativeTo( this );
		dialogoRestricciones.setVisible(true);
	}

	public void dialogoCoordenadaInicial() {
		DialogoCoordenadasIniciales dialogoCoordenadaInicial = new DialogoCoordenadasIniciales(this);
		dialogoCoordenadaInicial.setLocationRelativeTo(this);
		dialogoCoordenadaInicial.setVisible(true);
	}

	public void dialogoFuncionObjetivo() {
		DialogoFuncionObjetivo dialogo = new DialogoFuncionObjetivo(this);
		dialogo.setLocationRelativeTo(this);
		dialogo.setVisible(true);
	}

	public String[][] getRestricciones(){
		return restricciones;
	}

	public void ubicarPuntoInicial() {
		if(!satisfaceRestricciones(x,y)) {
			JOptionPane.showMessageDialog(this, "La coordenada inicial indicado no satisface todas las restricciones.");
			dialogoCoordenadaInicial();
			return;
		}
		dialogoFuncionObjetivo();	
	}

	public void valoresFO(String valx, String valy, String objetivo) {
		funcionObjetivo[0] = valx;
		funcionObjetivo[1] = valy;
		funcionObjetivo[2] = objetivo;

		dialogoDistancia = new JDialog();
		dialogoDistancia.setSize(200, 100);

		dialogoDistancia.setLayout(new GridLayout(2, 2));
		dialogoDistancia.setLocationRelativeTo(this);


		distanciaTextField = new JTextField();
		aceptarDistancia = new JButton("Aceptar");
		aceptarDistancia.addActionListener(this);
		aceptarDistancia.setActionCommand(ACEPTAR_DISTANCIA);

		dialogoDistancia.add(new JLabel("Distancia"));
		dialogoDistancia.add(distanciaTextField);		
		dialogoDistancia.add(new JLabel(""));
		dialogoDistancia.add(aceptarDistancia);

		dialogoDistancia.setVisible(true);
	}

	public boolean satisfaceRestricciones(int x, int y) {
		boolean cumple = true;

		for (String[] restriccion : restricciones) {
			int ladoDerecho = Integer.parseInt(restriccion[3]);
			String signo = restriccion[2];
			int a = Integer.parseInt(restriccion[0]);
			int b = Integer.parseInt(restriccion[1]);
			cumple = cumple && ( (((a*x+b*y)>=ladoDerecho) && signo.equals(">="))
					|| (((a*x+b*y)<=ladoDerecho) && signo.equals("<=")) 
					|| (((a*x+b*y)==ladoDerecho) && signo.equals("=")));
		}

		return cumple;
	}

	public void empezarBusquedaLocal() {

		boolean enOptimoLocal = false; 
		int mejor = calcularFO(x, y);
		int[] mejoresCoord = new int[] {x,y};
		foInicial = calcularFO(xInicial, yInicial); 

		while(!enOptimoLocal) {
			punto = 0;

			data.add(new String[]{iteracion+"", "Inicial", x+"", y+"", "Si", calcularFO(x, y)+"", ""});

			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1 ; j++) {

					int nuevox = x+i*distancia;
					int nuevoy = y+j*distancia;

					String factible = satisfaceRestricciones(nuevox, nuevoy)? "Si":"No";

					if(satisfaceRestricciones(nuevox, nuevoy)
							&& ((mejor < calcularFO(nuevox, nuevoy) && funcionObjetivo[2].equals("max"))
									||(mejor > calcularFO(nuevox, nuevoy) && funcionObjetivo[2].equals("min")))) {

						mejoresCoord[0] = nuevox;
						mejoresCoord[1] = nuevoy;
						mejor = calcularFO(nuevox, nuevoy);

					}
					if(nuevox != x || nuevoy!= y)
						data.add(new String[]{iteracion+"", punto+"", nuevox+"", nuevoy+"", factible, calcularFO(nuevox, nuevoy)+"", (foInicial-calcularFO(x+i*distancia, y+j*distancia)) + ""});

					punto++;
				}
			}
			if(mejoresCoord[0] != x || mejoresCoord[1] != y) {
				x = mejoresCoord[0];
				y = mejoresCoord[1];
				caminoPuntos.add(new int[] {mejoresCoord[0], mejoresCoord[1]});
				System.out.println(mejoresCoord[0] + " " + mejoresCoord[1]);
			}
			else {
				enOptimoLocal = true;
			}
			iteracion++;
		}

		plano.repaint();

		new DialogoOptimizacionRecargada(this);

	}
	
	public void buscarConPuntosUsuario() {
		
		int mejor = calcularFO(caminoPuntos.get(caminoPuntos.size()-1)[0], caminoPuntos.get(caminoPuntos.size()-1)[1]);
		int xMejor = -1;
		int yMejor = -1;
		
		for (int i = 0; i < tresPuntosUsuario.length; i++) {
			int nuevox = x + longitudPasoUsuario*tresPuntosUsuario[i][0];
			int nuevoy = y + longitudPasoUsuario*tresPuntosUsuario[i][1];
			
			if(satisfaceRestricciones(nuevox, nuevoy)
					&& ((mejor < calcularFO(nuevox, nuevoy) && funcionObjetivo[2].equals("max"))
							||(mejor > calcularFO(nuevox, nuevoy) && funcionObjetivo[2].equals("min")))) {
				mejor = calcularFO(nuevox, nuevoy);
				xMejor = nuevox;
				yMejor = nuevoy;
			}
			
			String factible = satisfaceRestricciones(nuevox, nuevoy)?"Si":"No";
			data.add(new String[]{iteracion+"", (punto++)+"", nuevox+"", nuevoy+"", factible, calcularFO(nuevox, nuevoy)+"", (foInicial-calcularFO(nuevox, nuevoy)) + ""});
		}
		
		if(xMejor == -1 && yMejor == -1) {
			TableDialog t = new TableDialog(data);
			t.setLocationRelativeTo(this);
			t.setVisible(true);
		}
		
		else {
			x = xMejor;
			y = yMejor;
			caminoPuntos.add(new int[] {x, y});
			empezarBusquedaLocal();
		}
		
		
	}

	public int calcularFO(int x, int y) {
		return Integer.parseInt(funcionObjetivo[0])*x+Integer.parseInt(funcionObjetivo[1])*y;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals(ACEPTAR_DISTANCIA)) {
			distancia = Integer.parseInt(distanciaTextField.getText());
			dialogoDistancia.setVisible(false);
			caminoPuntos.add(new int[] {x,y});
			xInicial = x;
			yInicial = y;
			empezarBusquedaLocal();
		}
	}

	public static void main(String[] args) {

		//Creando la ventana y el plano de dibujo
		Interfaz frame = new Interfaz();
	}

}