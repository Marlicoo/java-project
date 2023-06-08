package pl.wit.app.view;

import static org.junit.Assert.*;


import javax.swing.*;

import org.junit.Test;
/**
 * Test klasy tworzącej główne okienko kopiowania.
 * 
 * @author Michał Lemański
 *
 */
public class CopyFilesEditorFrameTest {

	@Test
    public void testCopyFilesEditorUI() {

		// Utworzenie nowego obiektu naszego okienka
		CopyFilesEditorFrame ui = new CopyFilesEditorFrame();

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