package pl.wit.app.view;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.*;

import org.junit.AfterClass;
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
    private static final String SOURCE_DIR = "source_test";
    private static final String DESTINATION_DIR = "destination_test";

    @Before
    public void setUp() {

        // Utworzenie folderów testowych
        new File(SOURCE_DIR).mkdir();
        new File(DESTINATION_DIR).mkdir();
        panel = new CopyFilesPanel();
        

    }

    @Test
    public void SetLabelTextTest() {
        String labelText = "Test Label";
        boolean isVisible = true;

        JLabel label = panel.setLabelText(labelText, isVisible);

        // Sprawdź, czy ustawiono poprawny tekst etykiety
        assertEquals(labelText, label.getText());

        // Sprawdź, czy ustawiono właściwą widoczność etykiety
        assertEquals(isVisible, label.isVisible());
    }


	@Test
	public void setModelTest(){
		
		
		// Utworzenie nowego modelu okienka 
		DirectoryChooserPanel panel2 = new DirectoryChooserPanel("Choose directory: ", "Open");
		// Ustawienie trybu odczytu
		panel2.setMode(1);
		// Symulacja kliknięcia przycisku
        panel2.buttonActionPerformed(new ActionEvent(panel2.button, ActionEvent.ACTION_PERFORMED, ""));
        // Przypisanie nowej ścieżki zródłowej
		panel.setSourceDirPath(panel2);
		
		// Utworzenie nowego modelu okienka 		
		DirectoryChooserPanel panel3 = new DirectoryChooserPanel("Choose directory: ", "Save");
		// Ustawienie trybu odczytu
		panel3.setMode(2);
		// Symulacja kliknięcia przycisku
		panel3.buttonActionPerformed(new ActionEvent(panel3.button, ActionEvent.ACTION_PERFORMED, ""));
		// Przypisanie nowej ścieżki docelowej
		panel.setDestinationDirPath(panel3);
        
		// Ustawienie ścieżek w modelu
		panel.setModel();
		
		// Przetestowanie czy ścieżki z wyboru zostały przejęte i ustawione w modelu
        assertEquals(panel2.getSelectedFilePath() , panel.getModel().getSourceDir().toString());
		assertEquals(panel3.getSelectedFilePath() , panel.getModel().getDestinationDir().toString());
		
	}
	@AfterClass
    public static void clean() {
		
		// Usunięcie folderów testowych - porządki
		
    	File deleteSource = new File(SOURCE_DIR);
    	File deleteDestination = new File(DESTINATION_DIR);

    	deleteSource.delete();
    	deleteDestination.delete();

    }
	
}

