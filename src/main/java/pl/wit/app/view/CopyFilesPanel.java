package pl.wit.app.view;

import java.awt.*;
import javax.swing.*;

import pl.wit.app.StartCopyListener;
import pl.wit.app.model.DirectoryModel;

public class CopyFilesPanel extends JPanel {
	public static final int MY_WIDTH = 120;
	public static final int MY_HEIGHT = 100;

	private DirectoryChooserPanel sourceDirPath, destinationDirPath;
	private JLabel textFieldLabel;
	private DirectoryModel model;

	public CopyFilesPanel() {
		sourceDirPath = setDirectoryChooser("Wybierz folder źródłowy");
		destinationDirPath = setDirectoryChooser("Wybierz folder docelowy");
		setButton("Start");
		textFieldLabel = new JLabel("");
		this.add(textFieldLabel);

		setLayout(new GridLayout(3, 2));
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension superSize = super.getPreferredSize();
		int width = Math.max(MY_WIDTH, superSize.width);
		int height = Math.max(MY_HEIGHT, superSize.height);

		return new Dimension(width, height);
	}

	private void setButton(String btnText) {
		JButton button = new JButton(btnText);
		button.addActionListener(new StartCopyListener(this));

		int mnemonic = (int) btnText.charAt(0);
		button.setMnemonic(mnemonic);
		this.add(button);
	}

	private DirectoryChooserPanel setDirectoryChooser(String labelText) {
		DirectoryChooserPanel chooser = new DirectoryChooserPanel(labelText, "Wybierz");
		chooser.setMode(DirectoryChooserPanel.MODE_SAVE);
		this.add(chooser);

		return chooser;
	}

	public void setModel() {
		this.model = new DirectoryModel(sourceDirPath.getSelectedFilePath(), destinationDirPath.getSelectedFilePath());

	}

	public DirectoryModel getModel() {
		return model;
	}

	public JLabel setLabelText(String labelText, boolean isVisible) {
		textFieldLabel.setText(labelText);
		textFieldLabel.setVisible(isVisible);

		return textFieldLabel;
	}

	public DirectoryChooserPanel getSourceDirPath() {
		return sourceDirPath;
	}

	public void setSourceDirPath(DirectoryChooserPanel sourceDirPath) {
		this.sourceDirPath = sourceDirPath;
	}

	public DirectoryChooserPanel getDestinationDirPath() {
		return destinationDirPath;
	}

	public void setDestinationDirPath(DirectoryChooserPanel destinationDirPath) {
		this.destinationDirPath = destinationDirPath;
	}
}
