package multidocument_interface_drawing_application;

/** Declaration of class DrawProgram. 
 @author Jorge Aguado
 */
import javax.swing.JFrame;

public class DrawProgram {

	public static void main(String args[]) {
		// Create the frame.
		DrawFrame frame = new DrawFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Display the window.
		frame.setVisible(true);
	}
}
