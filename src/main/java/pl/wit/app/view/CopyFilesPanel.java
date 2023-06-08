package pl.wit.app.view;

import java.awt.*;
import javax.swing.*;

import pl.wit.app.StartCopyListener;
import pl.wit.app.model.DirectoryDataToCopy;

/**
 * Class <code>CopyFilesPanel<code> is responsible for displaying two directory
 * chooser panels and button to start copy files process
 * 
 * @author marlena.kuc
 *
 */
public class CopyFilesPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int MY_WIDTH = 120;
	public static final int MY_HEIGHT = 100;

	private DirectoryChooserPanel sourceDirPath, destinationDirPath;
	private JLabel textFieldLabel;
	private DirectoryDataToCopy model;
	private JButton startButton;

	/**
	 * Constructor
	 */
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

	/**
	 * Sets the button
	 * 
	 * @param btnText button text param
	 */
	private void setButton(String btnText) {
		startButton = new JButton(btnText);
		startButton.addActionListener(new StartCopyListener(this));

		int mnemonic = (int) btnText.charAt(0);
		startButton.setMnemonic(mnemonic);
		this.add(startButton);
	}

	/**
	 * Sets the DirectoryChooserPanel directory chooser
	 *
	 * @param labelText the label text.
	 * @return DirectoryChooserPanel object
	 */
	private DirectoryChooserPanel setDirectoryChooser(String labelText) {
		DirectoryChooserPanel chooser = new DirectoryChooserPanel(labelText, "Wybierz");
		chooser.setMode(DirectoryChooserPanel.MODE_SAVE);
		this.add(chooser);

		return chooser;
	}

	/**
	 * Sets model data based on current values of source and destination directory
	 */
	public void setModel() {
		this.model = new DirectoryDataToCopy(sourceDirPath.getSelectedFilePath(), destinationDirPath.getSelectedFilePath());

	}

	/**
	 * 
	 * @return DirectoryModel
	 */
	public DirectoryDataToCopy getModel() {
		return model;
	}

	/**
	 * Sets current label values, used for exceptions and error messages 
	 * @param labelText
	 * @param isVisible 
	 * @return <code>JLabel<code> with set message
	 */
	public JLabel setLabelText(String labelText, boolean isVisible) {
		textFieldLabel.setText(labelText);
		textFieldLabel.setVisible(isVisible);

		return textFieldLabel;
	}

	public JButton getStartButton() {
		return startButton;
	}
}
