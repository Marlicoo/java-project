package pl.wit.app.view;

import javax.swing.*;

/**
 * Class <code>CopyFilesEditorFrame<code> is the main frame for copy file app
 * @author marlena.kuc
 *
 */
public class CopyFilesEditorFrame extends JFrame  {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor which generates <code>CopyFilesPanel<code>
	 * 
	 */
	public CopyFilesEditorFrame() {
		super("Kopiowanie plików JPG");
		
		JPanel copyFilesPanel = new CopyFilesPanel();
 		add(copyFilesPanel);
 
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		pack();
 		setVisible(true);
	}
}
