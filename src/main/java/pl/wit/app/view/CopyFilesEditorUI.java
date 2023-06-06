package pl.wit.app.view;

import java.awt.FlowLayout;
import java.io.File;
import javax.swing.*;

public class CopyFilesEditorUI extends JFrame  {
	
	public CopyFilesEditorUI() {
		super("Kopiowanie plików JPG");
		
		JPanel buttonPanel = new ButtonLayout();
 		add(buttonPanel);
 
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		pack();
 		setVisible(true);
	}
}
