package multidocument_interface_drawing_application;

/** Declaration of class MyColorChooser4. 
 @author Jorge Aguado
 */

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

public class MyColorChooser4 extends JPanel implements PropertyChangeListener,
		ChangeListener {

	private JSlider slidergreen;
	private JSlider sliderred;
	private JSlider sliderblue;
	private JLabel labelred;
	private JLabel labelgreen;
	private JLabel labelblue;
	private JFormattedTextField textGreen;
	private JFormattedTextField textRed;
	private JFormattedTextField textBlue;

	/**
	 * Constructor, initials values for red, blue and green are 0
	 */
	public MyColorChooser4() {

		this.setLayout(new GridLayout(3, 3));

		this.slidergreen = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 0);
		this.slidergreen.addChangeListener(this);

		this.sliderred = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 0);
		this.sliderred.addChangeListener(this);

		this.sliderblue = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 0);
		this.sliderblue.addChangeListener(this);

		this.labelred = new JLabel("Red   ");
		this.labelgreen = new JLabel("Green ");
		this.labelblue = new JLabel("Blue  ");

		java.text.NumberFormat numberFormat = java.text.NumberFormat
				.getIntegerInstance();
		NumberFormatter formatter = new NumberFormatter(numberFormat);
		formatter.setMinimum(new Integer(0));
		formatter.setMaximum(new Integer(255));

		this.textGreen = new JFormattedTextField(formatter);
		this.textGreen.setValue(new Integer(0));
		this.textGreen.setColumns(3); // get some space
		this.textGreen.addPropertyChangeListener("value", this);

		this.textRed = new JFormattedTextField(formatter);
		this.textRed.setValue(new Integer(0));
		this.textRed.setColumns(3); // get some space
		this.textRed.addPropertyChangeListener("value", this);

		this.textBlue = new JFormattedTextField(formatter);
		this.textBlue.setValue(new Integer(0));
		this.textBlue.setColumns(3); // get some space
		this.textBlue.addPropertyChangeListener("value", this);

		this.add(this.labelred);
		this.add(this.sliderred);
		this.add(this.textRed);

		this.add(this.labelgreen);
		this.add(this.slidergreen);
		this.add(this.textGreen);

		this.add(this.labelblue);
		this.add(this.sliderblue);
		this.add(this.textBlue);
	}

	/**
	 * Method getTextFieldGreenValue
	 * 
	 * @return JFormattedTextField for blue color
	 */
	public JFormattedTextField getTextFieldBlueValue() {
		return this.textBlue;
	}

	/**
	 * Method getTextFieldGreenValue
	 * 
	 * @return JFormattedTextField for green color
	 */
	public JFormattedTextField getTextFieldGreenValue() {
		return this.textGreen;

	}

	/**
	 * Method getTextFieldGreenValue
	 * 
	 * @return JFormattedTextField for red color
	 */
	public JFormattedTextField getTextFieldRedValue() {
		return this.textRed;
	}

	/**
	 * Method propertyChange, it registers the changes in the input fields
	 */
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		Object source = e.getSource();
		int value = 0;
		if (source == this.textRed) {
			value = (int) this.textRed.getValue();
			if ((value >= 0) && (value <= 255)) {
				this.sliderred.setValue(value);
			}
		} else if (source == this.textGreen) {
			value = (int) this.textGreen.getValue();
			if ((value >= 0) && (value <= 255)) {
				this.slidergreen.setValue(value);
			}
		} else if (source == this.textBlue) {
			value = (int) this.textBlue.getValue();
			if ((value >= 0) && (value <= 255)) {
				this.sliderblue.setValue(value);
			}
		}
	}

	/**
	 * Method stateChanged, it registers the changes in the sliders
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		int value = source.getValue();
		if (source == this.sliderred) {
			this.textRed.setValue(new Integer(value));
		} else if (source == this.slidergreen) {
			this.textGreen.setValue(new Integer(value));
		} else if (source == this.sliderblue) {
			this.textBlue.setValue(new Integer(value));
		}
	}

}
