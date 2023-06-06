package pl.wit.app;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;



public class JpgFileCopierTest {

    private static final String SOURCE_DIR = "Projekt_source_test";
    private static final String DESTINATION_DIR = "Projekt_destination_test";

    @Before
    public void setUp() {
        // Utworzenie folderów testowych
        new File(SOURCE_DIR).mkdir();
        new File(DESTINATION_DIR).mkdir();
    }

    @Test
    public void testJpgFileCopier() throws IOException {
    	
    	
    	// Test utworzenia obiektu
    	JpgFileCopier test = new JpgFileCopier();
    	assertNotNull(test);
    	
        // Utworzenie testowych plików źródłowych
        File sourceFile1 = new File(SOURCE_DIR, "image1.jpg");
        sourceFile1.createNewFile();
        File sourceFile2 = new File(SOURCE_DIR, "image2.jpg");
        sourceFile2.createNewFile();

        // Wywołanie metody do przetestowania
        JpgFileCopier.copyJpgFiles(SOURCE_DIR, DESTINATION_DIR);

        // Sprawdzenie czy pliki zostały skopiowane
        File destinationFile1 = new File(DESTINATION_DIR, "image1.jpg");
        assertTrue(destinationFile1.exists());
        File destinationFile2 = new File(DESTINATION_DIR, "image2.jpg");
        assertTrue(destinationFile2.exists());

        // Sprawdzenie czy zawartość plików jest identyczna
        byte[] sourceBytes1 = Files.readAllBytes(sourceFile1.toPath());
        byte[] destinationBytes1 = Files.readAllBytes(destinationFile1.toPath());
        assertArrayEquals(sourceBytes1, destinationBytes1);

        byte[] sourceBytes2 = Files.readAllBytes(sourceFile2.toPath());
        byte[] destinationBytes2 = Files.readAllBytes(destinationFile2.toPath());
        assertArrayEquals(sourceBytes2, destinationBytes2);
    }
}