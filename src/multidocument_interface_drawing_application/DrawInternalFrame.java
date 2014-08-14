package multidocument_interface_drawing_application;

/** Declaration of class DrawInternalFrame. 
 @author Jorge Aguado
 */

import java.awt.BorderLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;

public class DrawInternalFrame extends JInternalFrame {

	static private int openFrameCount = 0;
	static final int xOffset = 30, yOffset = 30;
	private JComboBox<String> shapeJComboBox;
	private JCheckBox getFilled;

	public DrawInternalFrame() {
		super("Frame #" + (++openFrameCount), true, // resizable
				true, // closable
				true, // maximizable
				true);// iconifiable

		// ...Create the GUI and put it in the window...

		// ...Then set the window size or call pack...
		this.setSize(300, 300);

		// Set the window's location.
		this.setLocation(xOffset * openFrameCount, yOffset * openFrameCount);

	}

	public void addPanels() {

		this.setLayout(new BorderLayout());
		ShapePanel panel = new ShapePanel();
		this.shapeJComboBox = panel.getShape();
		this.getFilled = panel.getFilled();

		MyColorChooser4 colorchooser = new MyColorChooser4();
		JFormattedTextField textGreen = colorchooser.getTextFieldGreenValue();
		JFormattedTextField textRed = colorchooser.getTextFieldRedValue();
		JFormattedTextField textBlue = colorchooser.getTextFieldBlueValue();

		DrawPanel3 panel3 = new DrawPanel3(this.shapeJComboBox, this.getFilled,
				textRed, textGreen, textBlue);

		this.add(panel, BorderLayout.NORTH);
		this.add(panel3, BorderLayout.CENTER);
		this.add(colorchooser, BorderLayout.SOUTH);

	}
}
