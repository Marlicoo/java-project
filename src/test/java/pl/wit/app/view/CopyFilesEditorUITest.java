package pl.wit.app.view;

import static org.junit.Assert.*;

import java.awt.FlowLayout;
import java.io.File;
import javax.swing.*;

import org.junit.Test;

public class CopyFilesEditorUITest {

	@Test
    public void testCopyFilesEditorUI() {
		
		// Utworzenie nowego obiektu naszego okienka
		CopyFilesEditorUI ui = new CopyFilesEditorUI();
        
        // Sprawdzenie czy powstał obiekt okienka
        assertNotNull(ui);
        
        // Przetestowanie czy tytuł okienka jest prawidłowy
        assertEquals("Kopiowanie plików JPG", ui.getTitle());
        
        // Test przypisania domyślnej wartości zamknięcia okienka  
        assertEquals(JFrame.EXIT_ON_CLOSE, ui.getDefaultCloseOperation());
        
        // Test widoczności okienka
        assertTrue(ui.isVisible());
    }
}
