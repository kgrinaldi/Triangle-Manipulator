package JMoreTriangles;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ControlPanel extends JPanel {

	private TriangleFrame gf;
	
	
	public ControlPanel (TriangleFrame _gf)
	{
		gf = _gf;
		
		
		setLayout(new GridLayout(20, 1, 2, 2));

		
		JButton drawButton = new JButton("Draw");
		JButton selectButton = new JButton("Select");
		JButton translateButton = new JButton("Translate");
		JButton scaleButton = new JButton("Scale");
		JButton rotateButtonX = new JButton("Rotate X");
		JButton rotateButtonY = new JButton("Rotate Y");
		JButton rotateButtonZ = new JButton("Rotate Z");
		JButton resetButton = new JButton("Reset");
		
		final JTextField TranslateX = new JTextField("0", 3);
		final JTextField TranslateY = new JTextField("0", 3);
		final JTextField Scale = new JTextField("0", 3);
		final JTextField RotateAngle = new JTextField("0", 3);
		
		
		drawButton.addActionListener(TrianglePanel.Draw()); 
		selectButton.addActionListener(TrianglePanel.Select());
		
		translateButton.addActionListener(new ActionListener() { //translate
			public void actionPerformed(ActionEvent arg0) {
				
				gf.getGraphicPanel().TriangleTranslate(Integer.parseInt(TranslateX.getText()), Integer.parseInt(TranslateY.getText()));
			
			}
		}
				);
		
		scaleButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//scale
			
				gf.getGraphicPanel().TriangleScale(Integer.parseInt(Scale.getText()));
			
			}
		}
			); 
		
		rotateButtonX.addActionListener(new ActionListener() {//rotateX
			public void actionPerformed(ActionEvent arg0) {
			
				double number = Double.parseDouble(RotateAngle.getText());
				gf.getGraphicPanel().TriangleRotate(number,0);
			
			}
		});
		
		rotateButtonY.addActionListener(new ActionListener() {//rotateY
			public void actionPerformed(ActionEvent arg0) {
			
				double number = Double.parseDouble(RotateAngle.getText());
				gf.getGraphicPanel().TriangleRotate(number,1);
			
			}
		});
		
		rotateButtonZ.addActionListener(new ActionListener() {//rotateZ
			public void actionPerformed(ActionEvent arg0) {
			
				gf.getGraphicPanel().TriangleRotate(Integer.parseInt(RotateAngle.getText()),2);
			
			}
		});
		
		resetButton.addActionListener(TrianglePanel.Reset());
		
		
		add(drawButton);
		add(selectButton);
		add(TranslateX);
		add(TranslateY);
		add(translateButton);
		add(Scale);
		add(scaleButton);
		add(RotateAngle);
		add(rotateButtonX);
		add(rotateButtonY);
		add(rotateButtonZ);
		add(resetButton);
		
	}
	
	public Dimension getPreferredSize() 
	{
		return new Dimension(100, 500);
	}

}
