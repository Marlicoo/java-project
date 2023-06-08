package pl.wit.app.view;

import static org.junit.Assert.*;

import java.awt.Component;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Klasa testowa sprawdzająca panel kopiowania folderów.
 * 
 * @author Michał Lemański
 *
 */

public class CopyFilesPanelTest {
	private CopyFilesPanel panel;

	@Before
	public void setUp() {
		panel = new CopyFilesPanel();

	}

	@Test
	public void testCopyFilesPanelInit() {
		List<String> componentNames = Arrays.asList(JButton.class.getSimpleName(),
				DirectoryChooserPanel.class.getSimpleName(), JLabel.class.getSimpleName());
		Component[] components = panel.getComponents();

		assertEquals(4, components.length);

		for (Component component : components) {
			assertTrue(componentNames.contains(component.getClass().getSimpleName()));
		}
	}

	@Test
	public void testSetLabelTextTest() {
		String labelText = "Test Label";
		boolean isVisible = true;

		JLabel label = panel.setLabelText(labelText, isVisible);

		assertEquals(labelText, label.getText());
		assertEquals(isVisible, label.isVisible());
	}

}