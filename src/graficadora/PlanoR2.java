package graficadora;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
/**
 *
 * @author jorge
 */
public class PlanoR2 extends JPanel{

	Interfaz interfaz;
	CartesianPlane c;

	public PlanoR2(Interfaz interfaz) {
		setSize(700, 700);
		this.interfaz = interfaz;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public void update( Graphics2D pGraphics )
	{
		pGraphics.setStroke( new BasicStroke( 1 ) );
		pGraphics.setColor( Color.WHITE );
		pGraphics.fillRect( 0, 0, getWidth( ), getHeight( ) );

		pGraphics.setColor(Color.red);

		//		pGraphics.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
		//		pGraphics.drawLine(this.getWidth()/2, 0,this.getWidth()/2 , this.getHeight());
		//		
		c = new CartesianPlane();
		c.paintComponent(pGraphics);

		for ( String[] restriccion : interfaz.restricciones) {
			if(restriccion[0]!=null) {
				Color color;
				if(restriccion[2].equals(">="))
					color = Color.BLUE;
				else if(restriccion[2].equals("<="))
					color = Color.GREEN;
				else 
					color = Color.MAGENTA;
				 
				int x1 = -500;
				int x2 = 500;
				if (Integer.parseInt(restriccion[1])!= 0) {
					int y1 = (Integer.parseInt(restriccion[3]) - Integer.parseInt(restriccion[0]) *(-500))/ Integer.parseInt(restriccion[1]);
					int y2 = (Integer.parseInt(restriccion[3]) - Integer.parseInt(restriccion[0]) *(500))/ Integer.parseInt(restriccion[1]);
					dibujarRestriccion(pGraphics,x1, y1 , x2, y2, color); 
				}
				else {
					int x = Integer.parseInt(restriccion[3])/Integer.parseInt(restriccion[0]);
					dibujarRestriccion(pGraphics,x, -9999 , x, 9999, color);
				}
			}
		}

		for (int[] punto : interfaz.caminoPuntos) {
			if(interfaz.caminoPuntos.indexOf(punto) == 0)
				dibujarPunto(pGraphics, punto[0], punto[1], Color.GREEN);
			else if(interfaz.caminoPuntos.indexOf(punto) == interfaz.caminoPuntos.size()-1)
				dibujarPunto(pGraphics, punto[0], punto[1], Color.BLUE);
			else
				dibujarPunto(pGraphics, punto[0], punto[1], Color.GRAY);
		}
	}

	public void paintComponent( Graphics pGraphics )
	{
		super.paintComponent( pGraphics );
		update( ( Graphics2D )pGraphics );
	}

	public void paintSQRFunc(double x2mult,double x1mult,double cons, double x1,double x2)
	{
		for(double i=x1;i<x2;i++)
		{
			double y = ((double)Math.pow(i,2)*x2mult)+i*x1mult+cons;
			double xp = i+1;
			double yp = ((double)Math.pow(xp,2)*x2mult)+xp*x1mult+cons;
			getGraphics().drawLine((int)coord_x(i), (int)coord_y(y), (int)coord_x(xp), (int)coord_y(yp));

		}
	}

	public void dibujarRestriccion(Graphics2D pGraphics2d,int x1, int y1, int x2, int y2, Color color){
		pGraphics2d.setColor(color);
		pGraphics2d.drawLine(coord_x(x1), coord_y(y1), coord_x(x2), coord_y(y2));
	}

	public void dibujarPunto(Graphics2D pGraphics2d, int x, int y, Color color) {
		pGraphics2d.setColor(color);
		pGraphics2d.fillOval(coord_x(x) - 5, coord_y(y) - 5, 10, 10);
	}


	private int coord_x(double x)
	{
		int xLength = (CartesianPlane.X_AXIS_SECOND_X_COORD - CartesianPlane.X_AXIS_FIRST_X_COORD)
				/ (CartesianPlane.XMAXNUM - CartesianPlane.XMINNUM) ;

		double real_x = x*xLength + CartesianPlane.Y_AXIS_X_COORD;
		return (int)real_x;
	}
	private int coord_y(double y)
	{
		int yLength = (CartesianPlane.Y_AXIS_SECOND_Y_COORD - CartesianPlane.Y_AXIS_FIRST_Y_COORD)
				/ (CartesianPlane.YMAXNUM - CartesianPlane.YMINNUM);
		double real_y = -y*yLength + CartesianPlane.X_AXIS_Y_COORD;

		return (int) real_y;
	}
}