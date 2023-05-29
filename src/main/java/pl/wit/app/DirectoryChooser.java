package pl.wit.app;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

public class DirectoryChooser extends JFrame implements ActionListener {
	static JLabel l;
	File file;

	JFileChooser chooser;
	String choosertitle;

	public DirectoryChooser() {
		super("Kopiowanie plików JPG");
	}

	public void actionPerformed(ActionEvent evt) {
		String com = evt.getActionCommand();

		if (com.equals("Start")) {
			// to do
		}

		else {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			int r = j.showOpenDialog(null);

			if (r == JFileChooser.APPROVE_OPTION) {
				l.setText(j.getSelectedFile().getAbsolutePath());
			} else
				l.setText("the user cancelled the operation");
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(400, 400);
	}
}