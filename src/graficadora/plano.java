package graficadora;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
/**
 *
 * @author jorge
 */
public class plano extends JPanel{

	public plano() {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	@Override 
	public void paintComponent( Graphics g ) {
		super.paintComponent(g);

		g.setColor(Color.red);
		
		g.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
		g.drawLine(this.getWidth()/2, 0,this.getWidth()/2 , this.getHeight());
		System.out.println(this.getWidth());
		System.out.println(this.getHeight());

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
	
	public void dibujarRestriccion(int x1, int y1, int x2, int y2){
		getGraphics().drawLine(coord_x(x1), coord_y(y1), coord_x(x2), coord_y(y2));
	}
	
	public void dibujarPunto(int x, int y) {
		getGraphics().setColor(new Color(255, 0, 0));
		getGraphics().drawOval(coord_x(x) - 25, coord_y(y) - 25, 50, 50);
	}
	

	private int coord_x(double x)
	{
		double real_x = x+this.getWidth()/2;
		return (int)real_x;
	}
	private int coord_y(double y)
	{
		double real_y = -y+this.getHeight()/2;

		return (int) real_y;
	}
}