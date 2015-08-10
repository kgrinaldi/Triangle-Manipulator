package JMoreTriangles;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TriangleFrame extends JFrame {

	private TrianglePanel gp;
	private ControlPanel cp;
	
	TrianglePanel getGraphicPanel()
	{
		return gp;
	}
	
	public TriangleFrame (int height, int width)
	{

		setTitle("BasicShapeDrawing");
		
		setSize(width, height);
		
		setLocationRelativeTo(null);
		
		
		URL imgURL = getClass().getResource("Logo.png");//got alternate code from the internet
		BufferedImage bi = null;		
		//File inputfile = new File("./Logo.png");
		try {
			bi = ImageIO.read(imgURL);
		} catch (IOException e) {
			//TODO Auto-generated catch block
		 	e.printStackTrace();
			System.out.println("error reading logo, exiting");
			System.exit(0);
		}
		setIconImage(bi);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout(5, 5));

		gp = new TrianglePanel(this);
		this.add(gp, BorderLayout.CENTER);
		
		cp = new ControlPanel(this);
		this.add(cp, BorderLayout.EAST);
		
		setVisible(true);
		
	}
	
	
	public static void main (String[] args)
	{
		System.out.println("starting");
		
		TriangleFrame gf = new TriangleFrame(512, 768);
	}
}
