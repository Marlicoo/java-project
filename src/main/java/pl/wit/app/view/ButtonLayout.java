package pl.wit.app.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import pl.wit.app.JpgFileCopier;
import pl.wit.app.JpgFileCopierThreaded;

public class ButtonLayout extends JPanel implements ActionListener {
	public static final int MY_WIDTH = 120;
	public static final int MY_HEIGHT = 100;

	private JButton start;
	private DirectoryChooser choose1, choose2;
	private JLabel textFieldLabel;

	public ButtonLayout() {
		choose1 = setDirectoryChooser("Wybierz folder źródłowy");
		choose2 = setDirectoryChooser("Wybierz folder docelowy");
		start = setButton("Start");
		textFieldLabel = setLabel("");		

		setLayout(new GridLayout(3, 2));
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension superSize = super.getPreferredSize();
		int width = Math.max(MY_WIDTH, superSize.width);
		int height = Math.max(MY_HEIGHT, superSize.height);

		return new Dimension(width, height);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == start) {
			String sourceDir = choose1.getSelectedFilePath();
			String destinationDir = choose2.getSelectedFilePath();
			if (!sourceDir.isEmpty() && !destinationDir.isEmpty()) {
				try {
					start.setOpaque(true);
					textFieldLabel.setText("");
					textFieldLabel.setVisible(true);

					JpgFileCopierThreaded.copyJpgFilesThreaded(
							choose1.getSelectedFilePath(), choose2.getSelectedFilePath(), 10);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if(sourceDir.isEmpty()) {
				textFieldLabel.setText("Nie wybrano folderu źródłowego");
				textFieldLabel.setVisible(true);
			} else {
				textFieldLabel.setText("Nie wybrano folderu docelowego");
				textFieldLabel.setVisible(true);
			}
		}
	}

	private JButton setButton(String btnText) {
		JButton button = new JButton(btnText);
		button.addActionListener((ActionListener) this);

		int mnemonic = (int) btnText.charAt(0);
		button.setMnemonic(mnemonic);
		this.add(button);

		return button;
	}

	private DirectoryChooser setDirectoryChooser(String labelText) {
		DirectoryChooser chooser = new DirectoryChooser(labelText, "Wybierz");
		chooser.setMode(DirectoryChooser.MODE_SAVE);
		this.add(chooser);

		return chooser;
	}
	
	private JLabel setLabel(String labelText) {
		textFieldLabel = new JLabel("");
		textFieldLabel.setVisible(false);
		
		this.add(textFieldLabel);

		return textFieldLabel;
	}
}
