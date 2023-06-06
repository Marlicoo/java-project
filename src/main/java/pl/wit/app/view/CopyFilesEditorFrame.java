package pl.wit.app.view;

import javax.swing.*;

public class CopyFilesEditorFrame extends JFrame  {

	private static final long serialVersionUID = 1L;

	public CopyFilesEditorFrame() {
		super("Kopiowanie plików JPG");
		
		JPanel copyFilesPanel = new CopyFilesPanel();
 		add(copyFilesPanel);
 
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		pack();
 		setVisible(true);
	}
}
