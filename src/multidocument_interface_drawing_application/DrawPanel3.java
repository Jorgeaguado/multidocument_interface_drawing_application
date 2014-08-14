package multidocument_interface_drawing_application;

/** Declaration of class DrawPanel3. 
 @author Jorge Aguado
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

public class DrawPanel3 extends JPanel {
	/**
	 * Class MouseHandler used to track mouse events
	 */
	private class MouseHandler extends MouseAdapter implements
			MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent event) {

			DrawPanel3.this.currentShape.setX2Coord(event.getX());
			DrawPanel3.this.currentShape.setY2Coord(event.getY());
			DrawPanel3.this.repaint();
		}

		@Override
		public void mousePressed(MouseEvent event) {

			MyShape auxShape = null;
			DrawPanel3.this.setCurrentColor(new Color(Integer
					.parseInt(DrawPanel3.this.auxTextRed.getText()), Integer
					.parseInt(DrawPanel3.this.auxTextGreen.getText()), Integer
					.parseInt(DrawPanel3.this.auxTextBlue.getText())));

			if (DrawPanel3.this.shapeJComboBox.getSelectedIndex() == 0) {
				auxShape = new MyLine(event.getX(), event.getX(), event.getY(),
						event.getY(), DrawPanel3.this.currentColor);
			} else if (DrawPanel3.this.shapeJComboBox.getSelectedIndex() == 1) {
				auxShape = new MyRectangle(event.getX(), event.getX(),
						event.getY(), event.getY(),
						DrawPanel3.this.currentColor,
						DrawPanel3.this.auxGetFilled.isSelected());
			} else if (DrawPanel3.this.shapeJComboBox.getSelectedIndex() == 2) {
				auxShape = new MyOval(event.getX(), event.getX(), event.getY(),
						event.getY(), DrawPanel3.this.currentColor,
						DrawPanel3.this.auxGetFilled.isSelected());
			}

			DrawPanel3.this.setCurrentShape(auxShape);

		}

		@Override
		public void mouseReleased(MouseEvent event) {
			DrawPanel3.this.currentShape.setX2Coord(event.getX());
			DrawPanel3.this.currentShape.setY2Coord(event.getY());
			DrawPanel3.this.addShape(DrawPanel3.this.currentShape);
			DrawPanel3.this.setCurrentShape(null);
			DrawPanel3.this.repaint();
		}
	}

	private MyShape shapes[];// An array of type MyShape. Max 100 shapes
	private int shapeCount;// Numbers of shapes used in the array shapes[]
	private int shapeType;// 0- Line, 1- Rectangle, 2- Oval
	private MyShape currentShape;// The current shape the user is drawing
	private Color currentColor;// The current color drawing

	private boolean filledShape;// True - the shape is filled
	private JFormattedTextField auxTextGreen;
	private JFormattedTextField auxTextRed;

	private JFormattedTextField auxTextBlue;
	private JCheckBox auxGetFilled;

	private JComboBox<String> shapeJComboBox;

	/**
	 * Constructor, with initials values
	 * 
	 * @param shapeUsed
	 *            , JComboBox used in the class MyColorChooser4 for the shape
	 *            selected
	 * 
	 * @param getFilled
	 *            , JCheckBox used in the class MyColorChooser4 to fill the
	 *            shape
	 * 
	 * @param textRed
	 *            , JFormattedTextField with the value of the field red
	 * 
	 * @param textGreen
	 *            , JFormattedTextField with the value of the field green
	 * 
	 * @param textBlue
	 *            , JFormattedTextField with the value of the field blue
	 */
	public DrawPanel3(JComboBox<String> shapeUsed, JCheckBox getFilled,
			JFormattedTextField textRed, JFormattedTextField textGreen,
			JFormattedTextField textBlue) {
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.setBackground(new Color(0, 0, 0));// Set the default color
												// background
		this.shapes = new MyShape[100];
		this.shapeCount = 0;
		this.shapeType = 0;
		this.currentShape = null;
		this.filledShape = false;
		this.auxGetFilled = getFilled;
		this.auxTextGreen = textGreen;
		this.auxTextRed = textRed;
		this.auxTextBlue = textBlue;
		this.shapeJComboBox = shapeUsed;

		MouseHandler handler = new MouseHandler();
		this.addMouseListener(handler);
		this.addMouseMotionListener(handler);
	}

	/**
	 * Method addShape
	 * 
	 * @parameter shape, it is added to the private vector shapes
	 */
	private void addShape(MyShape shape) {
		if (this.shapeCount < 100) {
			this.shapes[this.getShapeCount()] = shape;
			this.shapeCount++;
		}
	}

	/**
	 * Method clearDrawing
	 */
	public void clearDrawing() {
		this.shapeCount = 0;

		this.repaint();
	}

	/**
	 * Method clearLastShape
	 */
	public void clearLastShape() {
		if (this.getShapeCount() > 0) {
			this.shapeCount--;
			this.repaint();
		}
	}

	/**
	 * Method currentShapePaint, draw the carruent shape
	 * 
	 * @parameter g, used to draw objects
	 */
	public void currentShapePaint(Graphics g) {
		if (this.getCurrentShape() != null) {
			this.getCurrentShape().draw(g);
		}
	}

	/**
	 * Method getCurrentColor
	 * 
	 * @return the value of the private variable currentColor
	 */
	public Color getCurrentColor(Color c) {
		return this.currentColor;
	}

	/**
	 * Method getCurrentShape,
	 * 
	 * @return the value of the private variable currentShape
	 */
	public MyShape getCurrentShape() {
		return this.currentShape;
	}

	/**
	 * Method getFilledShape
	 * 
	 * @parameter filled, Yes- The shape is filled
	 */
	public boolean getFilledShape(boolean filled) {
		return this.filledShape;
	}

	/**
	 * Method getShape
	 * 
	 * @return the shape of the position 'pos'
	 * 
	 * @exception NullPointerException
	 *                , when pos parameter is not between 0 and 100
	 * 
	 * @parameter pos, position of the private array where is the shape to be
	 *            returned
	 */
	public MyShape getShape(int pos) {
		try {
			if ((pos >= 0) && (pos < 100) && (pos < this.getShapeCount())) {
				return this.shapes[pos];
			} else {
				throw null;
			}
		} catch (NullPointerException NullPointerException) {
			System.err.printf("\nException: in method getShape %s\n",
					NullPointerException);
		}
		return null;
	}

	/**
	 * Method getShapeCount
	 * 
	 * @return an integer, the number of the shapes
	 */
	public int getShapeCount() {
		return this.shapeCount;
	}

	/**
	 * Method getShapeType
	 * 
	 * @return an integer, 0- Line 1- Rectangle 2- Oval
	 */
	public int getShapeType() {
		return this.shapeType;

	}

	/**
	 * Method paintComponent, draw every shape of the array and call to draw the
	 * current shape
	 * 
	 * @parameter g, used to draw objects
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < this.getShapeCount(); i++) {
			this.getShape(i).draw(g);
		}
		this.currentShapePaint(g);
	}

	/**
	 * Method setCurrentColor, set the color passed as a parameter
	 * 
	 * @parameter c, this is set in the private parameter currentColor
	 */
	public void setCurrentColor(Color c) {
		this.currentColor = c;
	}

	/**
	 * Method setCurrentShape, set as a current the shape passed as a parameter
	 * 
	 * @parameter shape, this is set in the private parameter currentShape
	 */
	public void setCurrentShape(MyShape shape) {
		this.currentShape = shape;
	}

	/**
	 * Method setFilledShape
	 * 
	 * @parameter filled True- the shape is filled
	 */
	public void setFilledShape(boolean filled) {
		this.filledShape = filled;
	}

	/**
	 * Method setShapeType, set the color passed as a parameter
	 * 
	 * @parameter shape, this is set in the private parameter currentShape
	 */
	public void setShapeType(int type) {
		this.shapeType = type;
	}
}
