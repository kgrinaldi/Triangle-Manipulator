package JMoreTriangles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.Timer;

public class TrianglePanel extends JPanel {

	private TriangleFrame gf;
	
	private Vector<Point> Triangles = new Vector<Point>();
	private Vector<Point> Centers = new Vector<Point>();
	
	private double TriangleVertices[][] = {{0,0,0},{0,0,0},{0,0,0}}; 
	
	private static ActionListener Draw;
	private static ActionListener Select;
	private static ActionListener Reset;
	
	private int intRadius;
	private int intSideA;
	private int intSideB;
	private int intSideC;
	private int intTempCenterX;
	private int intTempCenterY;

	
	
	private int intFunction = 0;
	

	public TrianglePanel (TriangleFrame _gf)
	{
		super();
		
		gf = _gf;

		this.setBackground(Color.gray);

		this.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
			
				if(intFunction == 0){ //draw
					
					if(Triangles.size() < 4) //it can only draw one triangle
					{
						if(Triangles.size() == 0)
						{
							TriangleVertices[0][0] = arg0.getX();
							TriangleVertices[0][1] = arg0.getY();
							Triangles.add(new Point(arg0.getX(), arg0.getY()));
							repaint();
						}
						
						else if(Triangles.size() == 1)
						{
							TriangleVertices[1][0] = arg0.getX();
							TriangleVertices[1][1] = arg0.getY();
							Triangles.add(new Point(arg0.getX(),arg0.getY()));
							repaint();
						}
						
						else if(Triangles.size() == 2)
						{
							TriangleVertices[2][0] = arg0.getX();
							TriangleVertices[2][1] = arg0.getY();
							Triangles.add(new Point(arg0.getX(),arg0.getY()));
							
							int a = (int)Triangles.get(0).getX(); //moved center up here so that it doesnt undergo changes each time repaint is called
							int b = (int)Triangles.get(1).getX();
							int c = (int)Triangles.get(2).getX();
							
							int d = (int)Triangles.get(0).getY();
							int e = (int)Triangles.get(1).getY();
							int f = (int)Triangles.get(2).getY();
							
							Center(a,b,c,d,e,f);//find center here
							
							repaint();
						}
							
						
					}

				
				}
				
				if(intFunction == 1){ //select
					
					
				
					
				
				}
				
				
								
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			
		}
		);
		
		Draw =  new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				intFunction = 0;
			}
		};
		
		Select =  new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				intFunction = 1;
			}
		};
		
		Reset = new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				for(int i = 0; i < TriangleVertices.length; i++)
				{
					for(int j = 0; j < TriangleVertices[0].length; j++)
					{
						TriangleVertices[i][j] = 0;
					}
				}
				Triangles.clear();
				Centers.clear();
				repaint();
			}
		};
	
	
	}
	
	
	public Dimension getPreferredSize() 
	{
		return new Dimension(50, 50);
	}
	
	
	public static ActionListener Draw()
	{
		return Draw;
	}
	
	public static ActionListener Select()
	{
		return Select;
	}
	
	public static ActionListener Reset()
	{
		return Reset;
	}
	
	public int CrossProduct(int a, int b, int c, int d) // a,c = x //b,d = y
	{
		return a * d - b * c;
		
	}
	
	public int DotProduct (int a, int b, int c, int d)
	{
		return a * b + c * d;
	}
	
	
	public void Center(int a, int b, int c, int d, int e, int f) //finds center of triangle with a-c as x values and d-f as y values
	{
		Centers.clear();
		
		intTempCenterX = (a + b + c)/3;
		intTempCenterY = (d + e + f)/3; 
		
		Centers.add(new Point(intTempCenterX, intTempCenterY));

		System.out.println(intSideA + " " + intSideB + " " + intSideC + " " + intTempCenterX + " " + intTempCenterY);
				
	}
	
	public void Radius() 
	{
		
		intRadius = (int)(.5 * Math.sqrt(((intSideB + intSideC - intSideA)*(intSideC + intSideA - intSideB)*(intSideA + intSideB - intSideC))/(intSideA + intSideB + intSideC)));
		
		System.out.println(intRadius);
	}
	
	
	

	public void TriangleTranslate(int a, int b) //adjust according to x and y
	{
			for(int i = 0; i < TriangleVertices.length; i++)
			{
				double dblTempA = TriangleVertices[i][0];
				double dblTempB = TriangleVertices[i][1];
				double dblTempC = TriangleVertices[i][2];
				
				double point[][] = {{dblTempA},{dblTempB},{dblTempC},{1}};
				double translate[][] = {{1, 0, 0, a}, {0, 1, 0, b}, {0, 0, 1, 1}, {0, 0, 0, 1}};
					
				point = MatrixMultiplication(translate,point);

				dblTempA = point[0][0];
				dblTempB = point[1][0];
				dblTempC = point[2][0];
				
				Triangles.remove(i);
				Triangles.add(i, new Point ((int)dblTempA, (int)dblTempB) );
				
				TriangleVertices[i][0] = dblTempA;
				TriangleVertices[i][1] = dblTempB;
				TriangleVertices[i][2] = dblTempC;
				
				int dblTempCenterA = (int)Triangles.get(0).getX(); 
				int dblTempCenterB = (int)Triangles.get(1).getX();
				int dblTempCenterC = (int)Triangles.get(2).getX();
				
				int dblTempCenterD = (int)Triangles.get(0).getY();
				int dblTempCenterE = (int)Triangles.get(1).getY();
				int dblTempCenterF = (int)Triangles.get(2).getY();
				
				Center(dblTempCenterA,dblTempCenterB,dblTempCenterC,dblTempCenterD,dblTempCenterE,dblTempCenterF);//find center here
				
				
			}
			
			
			
			
		
		
		System.out.println("New Values: " + Triangles.get(0).x + " " + Triangles.get(0).y);
		
		
		repaint();
	}
	
	public void TriangleScale(double a)
	{
		for(int i = 0; i < Triangles.size(); i++)
		{
			for(int j = 0; j < Centers.size(); j++)
			{
			
				double dblTempA = TriangleVertices[i][0]; //point
				double dblTempB = TriangleVertices[i][1];
				double dblTempC = TriangleVertices[i][2];
				
				double dblTempD = Centers.get(j).getX(); //center
				double dblTempE = Centers.get(j).getY();
					
				double transA[][] = {{1, 0, 0, -dblTempD}, {0, 1, 0, -dblTempE}, {0, 0, 1, 1}, {0, 0, 0, 1}};
				
				double scale[][] = {{a, 0, 0, 0}, {0, a, 0, 0}, {0, 0, a, 0}, {0, 0, 0, 1}};
				
				double transB[][] = {{1, 0, 0, dblTempD}, {0, 1, 0, dblTempE}, {0, 0, 1, 1}, {0, 0, 0, 1}};
				
				double point[][] = {{dblTempA},{dblTempB},{dblTempC},{1}};
				
				point = MatrixMultiplication(transA,point);
				point = MatrixMultiplication(scale,point);
				point = MatrixMultiplication(transB,point);

				
				dblTempA = point[0][0];
				dblTempB = point[1][0];
				dblTempC = point[2][0];
				
				Triangles.remove(i);
				Triangles.add(i, new Point ((int)dblTempA, (int)dblTempB) );
				
				TriangleVertices[i][0] = dblTempA;
				TriangleVertices[i][1] = dblTempB;
				TriangleVertices[i][2] = dblTempC;
				
				int dblTempCenterA = (int)Triangles.get(0).getX(); 
				int dblTempCenterB = (int)Triangles.get(1).getX();
				int dblTempCenterC = (int)Triangles.get(2).getX();
				
				int dblTempCenterD = (int)Triangles.get(0).getY();
				int dblTempCenterE = (int)Triangles.get(1).getY();
				int dblTempCenterF = (int)Triangles.get(2).getY();
				
				Center(dblTempCenterA,dblTempCenterB,dblTempCenterC,dblTempCenterD,dblTempCenterE,dblTempCenterF);//find center here
				
			}
		}
		
		System.out.println("New Values: " + Triangles.get(0).x + " " + Triangles.get(0).y);
		
		
		repaint();
	
	}
	
	public void TriangleRotate(double a, int b) //a = angle || b = case
	{
		
		double theta = a * (Math.PI / 180);
		

		
		
		switch (b)
		{
		case 0: //rotate X
		{

			for(int i = 0; i < Triangles.size(); i++)
			{
				//for(int j = 0; j < Centers.size(); j++)
				//{
					
					double dblTempA = TriangleVertices[i][0]; //point
					double dblTempB = TriangleVertices[i][1];
					double dblTempC = TriangleVertices[i][2];
					
					double dblTempD = Centers.get(0).getX(); //center
					double dblTempE = Centers.get(0).getY();
						
					double transA[][] = {{1, 0, 0, -dblTempD}, {0, 1, 0, -dblTempE}, {0, 0, 1, 1}, {0, 0, 0, 1}};
					
					double rotateX[][] = {{1, 0, 0, 0}, {0, Math.cos(theta), -Math.sin(theta), 0}, {0, Math.sin(theta), Math.cos(theta), 0}, {0, 0, 0, 1}};
					
					double transB[][] = {{1, 0, 0, dblTempD}, {0, 1, 0, dblTempE}, {0, 0, 1, 1}, {0, 0, 0, 1}};
					
					double point[][] = {{dblTempA},{dblTempB},{dblTempC},{1}};
					
					point = MatrixMultiplication(transA, point);
					point = MatrixMultiplication(rotateX, point);
					point = MatrixMultiplication(transB, point);

					
					dblTempA = point[0][0];
					dblTempB = point[1][0];
					dblTempC = point[2][0];
					
					Triangles.remove(i);
					Triangles.add(i, new Point ((int)dblTempA, (int)dblTempB) );
					
					TriangleVertices[i][0] = dblTempA;
					TriangleVertices[i][1] = dblTempB;
					TriangleVertices[i][2] = dblTempC;
					
				//}
			//}
			
			
			System.out.println("Result: ");
			for (int e = 0; e < Triangles.size(); e++)
			{
				System.out.println("New: " + Triangles.get(e).getX() + " " + Triangles.get(e).getY() + " ");
				
			}
			
			}
			
			break;
		}	
		case 1: //rotate Y
		{	
		

			for(int i = 0; i < Triangles.size(); i++)
			{
				for(int j = 0; j < Centers.size(); j++)
				{

					double dblTempA = TriangleVertices[i][0]; //point
					double dblTempB = TriangleVertices[i][1];
					double dblTempC = TriangleVertices[i][2];
					
					double dblTempD = Centers.get(j).getX(); //center
					double dblTempE = Centers.get(j).getY();
						
					double transA[][] = {{1, 0, 0, -dblTempD}, {0, 1, 0, -dblTempE}, {0, 0, 1, 1}, {0, 0, 0, 1}};
					
					double rotateY[][] = {{Math.cos(theta), 0, Math.sin(theta), 0}, {0, 1, 0, 0}, {-Math.sin(theta), 0, Math.cos(theta), 0}, {0, 0, 0, 1}};
					
					double transB[][] = {{1, 0, 0, dblTempD}, {0, 1, 0, dblTempE}, {0, 0, 1, 1}, {0, 0, 0, 1}};
					
					double point[][] = {{dblTempA},{dblTempB},{dblTempC},{1}};
					
					point = MatrixMultiplication(transA, point);
					point = MatrixMultiplication(rotateY, point);
					point = MatrixMultiplication(transB, point);

					
					dblTempA = point[0][0];
					dblTempB = point[1][0];
					dblTempC = point[2][0];
					
					Triangles.remove(i);
					Triangles.add(i, new Point ((int)dblTempA, (int)dblTempB) );
					
					TriangleVertices[i][0] = dblTempA;
					TriangleVertices[i][1] = dblTempB;
					TriangleVertices[i][2] = dblTempC;
					
					
				}
			}
			
			
			System.out.println("Result: ");
			for (int e = 0; e < Triangles.size(); e++)
			{
				System.out.println(Triangles.get(e).getX() + " " + Triangles.get(e).getY() + " ");
				
			}
			
			
			break;
		}	
		case 2: //rotate Z
		{
			for(int i = 0; i < Triangles.size(); i++)
			{
				for(int j = 0; j < Centers.size(); j++)
				{
					double dblTempA = TriangleVertices[i][0]; //point
					double dblTempB = TriangleVertices[i][1];
					double dblTempC = TriangleVertices[i][2];
					
					double dblTempD = Centers.get(j).getX(); //center
					double dblTempE = Centers.get(j).getY();
						
					double transA[][] = {{1, 0, 0, -dblTempD}, {0, 1, 0, -dblTempE}, {0, 0, 1, 1}, {0, 0, 0, 1}};
					
					double rotateZ[][] = {{Math.cos(theta), -Math.sin(theta), 0, 0}, {Math.sin(theta), Math.cos(theta), 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
					
					double transB[][] = {{1, 0, 0, dblTempD}, {0, 1, 0, dblTempE}, {0, 0, 1, 1}, {0, 0, 0, 1}};
					
					double point[][] = {{dblTempA},{dblTempB},{dblTempC},{1}};
					
					point = MatrixMultiplication(transA, point);
					point = MatrixMultiplication(rotateZ, point);
					point = MatrixMultiplication(transB, point);

					
					dblTempA = point[0][0];
					dblTempB = point[1][0];
					dblTempC = point[2][0];
					
					Triangles.remove(i);
					Triangles.add(i, new Point ((int)dblTempA, (int)dblTempB) );
					
					TriangleVertices[i][0] = dblTempA;
					TriangleVertices[i][1] = dblTempB;
					TriangleVertices[i][2] = dblTempC;
					
				}
			}
			
			
			System.out.println("Result: ");
			for (int e = 0; e < Triangles.size(); e++)
			{
				System.out.println(Triangles.get(e).getX() + " " + Triangles.get(e).getY() + " ");
				
			}
			
		}
		
		
		System.out.println("Result: ");
		for (int e = 0; e < Triangles.size(); e++)
		{
			System.out.println(Triangles.get(e).getX() + " " + Triangles.get(e).getY() + " ");
			
		}
			
			
			break;
		}
		
		
		
		
		for (int c = 0; c < Triangles.size(); c++)
		{
			System.out.print(Triangles.get(c).getX() + " ");
			System.out.print(Triangles.get(c).getY() + " ");
		}
		System.out.println(" ");
		
		int dblTempCenterA = (int)Triangles.get(0).getX(); 
		int dblTempCenterB = (int)Triangles.get(1).getX();
		int dblTempCenterC = (int)Triangles.get(2).getX();
		
		int dblTempCenterD = (int)Triangles.get(0).getY();
		int dblTempCenterE = (int)Triangles.get(1).getY();
		int dblTempCenterF = (int)Triangles.get(2).getY();
		
		Center(dblTempCenterA,dblTempCenterB,dblTempCenterC,dblTempCenterD,dblTempCenterE,dblTempCenterF);//find center here
		
		
		
		repaint();
	}
		
	
	
	public double[][] MatrixMultiplication(double a[][], double b[][])
	{
		int rowsA = a.length;
		int colsA = a[0].length;
		int rowsB = b.length;
		int colsB = b[0].length;

		double c[][] = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
		
		for (int i = 0; i < rowsA; ++i) 
		{
			for (int j = 0; j < colsB; ++j) 
			{
				
				for (int k = 0; k < rowsB; ++k) 
				{
					c[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		
		return c;
	}
	
	
	public void paint(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;

		int width = this.getWidth();
		int height = this.getHeight();
		
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, width, height);
		
		g2d.setColor(Color.green);
		

		
		for(int i = 0; i < Triangles.size(); i++)
		{
			if((i % 3) == 0){
				
				g2d.fillOval((int)Triangles.get(i).getX(), (int)Triangles.get(i).getY(), 2, 2);
				
			}
				
			else if((i % 3) == 1){
					
				g2d.fillOval((int)Triangles.get(i).getX(), (int)Triangles.get(i).getY(), 2, 2);
			}
				
				
			else if((i % 3) == 2){
				
				//insert a b c variables here to make this mess easier to see
				int a = (int)Triangles.get(i - 2).getX();
				int b = (int)Triangles.get(i - 1).getX();
				int c = (int)Triangles.get(i).getX();
				
				int d = (int)Triangles.get(i - 2).getY();
				int e = (int)Triangles.get(i - 1).getY();
				int f = (int)Triangles.get(i).getY();
				
				g2d.drawLine(a, d, b, e);
				g2d.drawLine(b, e, c, f);
				g2d.drawLine(c, f, a, d);
				
				System.out.println(a + " " + b + " " + c + " " + d + " " + e + " " + f);
				
				for(int j = 0; j < Centers.size(); j++)
				{
					g2d.drawOval((int)Centers.get(j).getX(), (int)Centers.get(j).getY(), 2, 2);
				}
				
				
			}
		}
		
		
		
		
						
			
		
		
	}
	

}
