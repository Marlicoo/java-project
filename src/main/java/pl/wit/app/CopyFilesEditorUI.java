package pl.wit.app;

import java.io.File;
import javax.swing.*;

public class CopyFilesEditorUI extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	JFrame frame;  
	File file1;
	File file2;
	
	public CopyFilesEditorUI() {
		SwingUtilities.invokeLater(() -> createAndShowGui());
	}
	
	private static void createAndShowGui() {
        JFrame frame =  new DirectoryChooser();
        ButtonLayout mainPanel = new ButtonLayout(frame);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
