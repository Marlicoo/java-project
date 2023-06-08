package pl.wit.app.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

public class DirectoryChooserPanel extends JPanel {
	private String textFieldLabel;
	private String buttonLabel;
	private JLabel label;
	protected JTextField textField;
	protected JButton button;
	private JFileChooser fileChooser;
	private int mode;
	public static final int MODE_OPEN = 1;
	public static final int MODE_SAVE = 2;

	public DirectoryChooserPanel(String textFieldLabel, String buttonLabel) {
		this.textFieldLabel = textFieldLabel;
		this.buttonLabel = buttonLabel;
		fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// creates the GUI
		label = new JLabel(textFieldLabel);
		textField = new JTextField(30);
		button = new JButton(buttonLabel);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				buttonActionPerformed(evt);
			}
		});

		add(label);
		add(textField);
		add(button);
	}

	public String getTextFieldLabel() {
		return textFieldLabel;
	}

	public String getButtonLabel() {
		return buttonLabel;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}


	public String getSelectedFilePath() {
		return textField.getText();
	}
	
	public JLabel getLabel() {
		return label;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}
	
	protected void buttonActionPerformed(ActionEvent evt) {
		if (mode == MODE_OPEN) {
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
			}
		} else if (mode == MODE_SAVE) {
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
			}
		}
	}
}
	