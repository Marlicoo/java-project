package pl.wit.app.view;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;


/**
 * Klasa testowa sprawdzająca stany przycisków oraz ich wyborów.
 * 
 * @author Michał Lemański
 *
 */

public class DirectoryChooserPanelTest {
	
			
	private static final String CHOICE_LABEL = "Choose directory: ";
	private static final String BUTTON_OPEN = "Open";

	
	@Test
	public void testDirectoryChooserPanel() {
		
		DirectoryChooserPanel panel = new DirectoryChooserPanel(CHOICE_LABEL, BUTTON_OPEN);
		
		// Przetestowanie czy CHOICE_LABEL jest przypisany do pola textFieldLabel
        assertEquals(CHOICE_LABEL, panel.getTextFieldLabel());
        
        // Przetestowanie czy BUTTON_OPEN jest przypisany do pola buttonLabel
        assertEquals(BUTTON_OPEN, panel.getButtonLabel());       
        
        // Przetestowanie czy fileChooser jest ustawiony na tryb wyboru "tylko katalogów"
        assertEquals(JFileChooser.DIRECTORIES_ONLY, panel.getFileChooser().getFileSelectionMode());
        
        // Przetestowanie konstruktora FlowLayout z oczekiwanymi parametrami
        FlowLayout layout = (FlowLayout) panel.getLayout();
        assertEquals(FlowLayout.CENTER, layout.getAlignment());
        assertEquals(5, layout.getHgap());
        assertEquals(5, layout.getVgap());
        
        // Sprawdź, czy po utworzeniu GUI label oraz button zostały utworzone z oczekiwanym tekstem
        assertEquals(CHOICE_LABEL, panel.getLabel().getText());
        assertEquals(BUTTON_OPEN, panel.getButtonLabel().toString());
        // Sprawdz, czy po utworzeniu GUI pole ścieżki zostało utworzone o oczekiwanym rozmiarze
        assertEquals(30, panel.getTextField().getColumns());
	}	
	
		
	@Test 
	public void ButtonActionModeOpenTest() throws IOException {
		
		DirectoryChooserPanel panel = new DirectoryChooserPanel(CHOICE_LABEL, null);
		
		// Test działania ustawienia trybu odczytu
		panel.setMode(1);
		JFileChooser fileChooser = new JFileChooser();
		
		
		
        // Utworzenie pliku tymczasowego
        File tempFile = null;
        tempFile = File.createTempFile("test", ".txt");
        fileChooser.setSelectedFile(tempFile);

        // Symulacja kliknięcia przycisku
        panel.buttonActionPerformed(new ActionEvent(panel.button, ActionEvent.ACTION_PERFORMED, ""));
            
        // Przetestowanie czy showOpenDialog jest wywoływane na fileChooser
        assertEquals(JFileChooser.APPROVE_OPTION, fileChooser.showOpenDialog(panel));

        // Przetestowanie czy textField jest ustawiany na absolutną ścieżkę wybranego pliku
        assertEquals(panel.textField.getText() , panel.getSelectedFilePath());

	        if (tempFile != null) {
	        tempFile.delete();
            
        }
	}
	
	@Test 
	public void ButtonActionModeSaveTest() throws IOException {
		
		DirectoryChooserPanel panel = new DirectoryChooserPanel(CHOICE_LABEL, null);
		
		// Test działania ustawienia trybu zapisu
		panel.setMode(2);
		JFileChooser fileChooser = new JFileChooser();
        
		// Utworzenie pliku tymczasowego
        File tempFile = null;
        tempFile = File.createTempFile("test", ".txt");
        fileChooser.setSelectedFile(tempFile);
    	
        
        // Symulacja kliknięcia przycisku
        panel.buttonActionPerformed(new ActionEvent(panel.button, ActionEvent.ACTION_PERFORMED, ""));

        // Przetestowanie czy showSaveDialog jest wywoływane na fileChooser
        assertEquals(JFileChooser.APPROVE_OPTION, fileChooser.showSaveDialog(panel));

        // Przetestowanie czy textField jest ustawiany na absolutną ścieżkę wybranego pliku
        assertEquals(panel.textField.getText() , panel.getSelectedFilePath());


	            if (tempFile != null) {
	                tempFile.delete();
	            }
        }
	
	}



