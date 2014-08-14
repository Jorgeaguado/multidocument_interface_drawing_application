package multidocument_interface_drawing_application;

/** Declaration of class ShapePanel. 
 @author Jorge Aguado 
 */
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ShapePanel extends JPanel {

	/**
	 * Method CheckBoxHandler, it doesnt change anything just it handles the
	 * event
	 */
	private class CheckBoxHandler implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
		}
	}

	private class ShapeHandler implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				// currentShape = shapeJComboBox.getSelectedIndex();
			}
		}
	}

	private String[] shapeNames = { "Line", "Rectangule", "Oval" };

	private JComboBox<String> shapeJComboBox;

	// private int currentShape;
	private JCheckBox filled;

	/**
	 * Constructor, it adds a JComboBox and a JCheckBox to the panel
	 */
	ShapePanel() {

		this.filled = new JCheckBox("Filled", false);
		CheckBoxHandler boxHandler = new CheckBoxHandler();
		this.filled.addItemListener(boxHandler);

		this.shapeJComboBox = new JComboBox<String>(this.shapeNames);
		this.shapeJComboBox.setSelectedIndex(0);
		ShapeHandler shape = new ShapeHandler();
		this.shapeJComboBox.addItemListener(shape);

		this.add(this.shapeJComboBox);
		this.add(this.filled);
		this.setVisible(true);
	}

	/**
	 * Method getFilled
	 * 
	 * @return it returns a JCheckBox
	 */
	public JCheckBox getFilled() {
		return this.filled;
	}

	/**
	 * Method getShape
	 * 
	 * @return it returns a JComboBox
	 */
	public JComboBox<String> getShape() {
		return this.shapeJComboBox;
	}
}
