package pl.wit.app;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class JpgFileCopierThreadedTest {

    private static final String SOURCE_DIR = "Projekt_source_Threaded_test";
    private static final String DESTINATION_DIR = "Projekt_destination_Threaded_test";

    @Before
    public void setUp() {
        // Tworzenie tymczasowych folderów
        new File(SOURCE_DIR).mkdir();
        new File(DESTINATION_DIR).mkdir();
    }

    @Test
    public void testJpgFileCopierThreaded() throws InterruptedException {
    	
    	// Test utworzenia obiektu
    	JpgFileCopierThreaded test = new JpgFileCopierThreaded();
    	assertNotNull(test);    	
    	
    	
        // Tworzenie tymczasowych plików źródłowych
        File sourceFile1 = new File(SOURCE_DIR, "image1.jpg");
        File sourceFile2 = new File(SOURCE_DIR, "image2.jpg");
        createFile(sourceFile1);
        createFile(sourceFile2);

        // Wywołanie metody do przetestowania
        try {
            JpgFileCopierThreaded.copyJpgFilesThreaded(SOURCE_DIR, DESTINATION_DIR, 2);
        } catch (InterruptedException e) {
            fail("InterruptedException occurred during file copying");
        }

        // Sprawdzenie czy pliki zostały skopiowane
        File destinationFile1 = new File(DESTINATION_DIR, "image1.jpg");
        File destinationFile2 = new File(DESTINATION_DIR, "image2.jpg");
        assertTrue(destinationFile1.exists());
        assertTrue(destinationFile2.exists());
    }

    private void createFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            fail("IOException occurred during file creation");
        }
    }
}
