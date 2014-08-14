package multidocument_interface_drawing_application;

/** Declaration of class DrawFrame. 
 @author Jorge Aguado
 @version  Project 2 
 */

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * DrawFrame.java
 */
public class DrawFrame extends JFrame implements ActionListener, MenuListener {
	private JDesktopPane desktop;
	private DrawInternalFrame frame;
	private JMenu wMenu = new JMenu("Windows");;

	public DrawFrame() {
		// Make the big window be indented 50 pixels from each edge
		// of the screen.
		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(inset, inset, screenSize.width - (inset * 2),
				screenSize.height - (inset * 2));

		// Set up the GUI.
		this.desktop = new JDesktopPane(); // a specialized layered pane
		this.createFrame(); // create first "window"
		this.setContentPane(this.desktop);
		this.setJMenuBar(this.createMenuBar());
		this.setTitle("Multidocument Interface Drawing Application");

		// Make dragging a little faster but perhaps uglier.
		this.desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	}

	// React to menu selections.
	@Override
	public void actionPerformed(ActionEvent e) {

		if (this.containsDigit(e.getActionCommand())) {
			for (JInternalFrame ji : this.desktop.getAllFrames()) {
				if ((ji.getTitle()).equals(e.getActionCommand())) {
					ji.moveToFront();
					break;
				}
			}
		} else if ("new".equals(e.getActionCommand())) { // new
			this.createFrame();
		} else if ("quit".equals(e.getActionCommand())) {
			this.quit();
		}
	}

	private boolean containsDigit(String s) {
		boolean containsDigit = false;
		if (s != null) {
			for (char c : s.toCharArray()) {
				if (containsDigit = Character.isDigit(c)) {
					break;
				}
			}
		}

		return containsDigit;
	}

	// Create a new internal frame.
	private void createFrame() {
		this.frame = new DrawInternalFrame();
		this.frame.setVisible(true); // necessary as of 1.3
		this.frame.addPanels();
		this.desktop.add(this.frame);
		try {
			this.frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
		}

	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		// Set up the lone menu.
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		// Set up the first menu item.
		JMenuItem menuItem = new JMenuItem("New window");
		menuItem.setActionCommand("new");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		// menuItem.setEnabled(false);
		// Set up the second menu item.
		menuItem = new JMenuItem("Quit");
		menuItem.setActionCommand("quit");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuBar.add(this.wMenu);
		this.wMenu.addMenuListener(this);

		return menuBar;
	}

	@Override
	public void menuCanceled(MenuEvent me) {

	}

	@Override
	public void menuDeselected(MenuEvent me) {
	}

	@Override
	public void menuSelected(MenuEvent me) {

		this.wMenu.removeAll();// remove previous opened window jmenuitems
		for (JInternalFrame ji : this.desktop.getAllFrames()) {

			JMenuItem menuItem = new JMenuItem(ji.getTitle());
			menuItem.setActionCommand(ji.getTitle());
			menuItem.addActionListener(this);
			if (ji.isIcon() == true) {
				menuItem.setEnabled(false);
			}
			this.wMenu.add(menuItem);
		}

		this.wMenu.revalidate();
		this.wMenu.repaint();
		this.wMenu.doClick();
	}

	// Quit the application.
	private void quit() {
		System.exit(0);
	}

}
