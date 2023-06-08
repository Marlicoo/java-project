package pl.wit.app.model;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * Klasa testowa sprawdzająca stany folderów.
 * 
 * @author Michał Lemański
 *
 */

public class DirectoryModelTest {
	
	private DirectoryModel directoryModel;
    private static final String SOURCE_DIR = "source_test";
    private static final String DESTINATION_DIR = "destination_test";
    private static final String TESTS_DIR = "source_other_test";
    private static final String TESTD_DIR = "destination_other_test";
    
    @Before
    public void setUp() {
    	// Utworzenie folderów testowych oraz sprawdzenie utworzenia obiektu DirectoryModel
        new File(SOURCE_DIR).mkdir();
        new File(DESTINATION_DIR).mkdir();
        new File(TESTS_DIR).mkdir();
        new File(TESTD_DIR).mkdir();
    	directoryModel = new DirectoryModel(SOURCE_DIR, DESTINATION_DIR);
    	assertNotNull(directoryModel);
    }
    
    @Test
    public void testDirectoryModel() throws IOException {
    	
    	// Przetestowanie czy ścieżka zródłowa przyjęła oczekiwną wartość
        File sourceDir = directoryModel.getSourceDir();
        assertEquals(SOURCE_DIR, sourceDir.getPath());
        
        // Przetestowanie czy ścieżka docelowa przyjęła oczekiwną wartość
        File destinationDir = directoryModel.getDestinationDir();
        assertEquals(DESTINATION_DIR, destinationDir.getPath());
    
        // Przetestowanie ustawienia innej ścieżki zródłowej
        directoryModel.setSourceDir(TESTS_DIR);
    	assertEquals(TESTS_DIR, directoryModel.getSourceDir().toString());
    	
    	// Przetestowanie ustawienia innej ścieżki docelowej
    	directoryModel.setDestinationDir(TESTD_DIR);
    	assertEquals(TESTD_DIR, directoryModel.getDestinationDir().toString());
        
    	// Utworzenie testowych plików źródłowych
        File sourceFile1 = new File(SOURCE_DIR, "image1.jpg");
        sourceFile1.createNewFile();
        File sourceFile2 = new File(SOURCE_DIR, "image2.jpg");
        sourceFile2.createNewFile();
    	
        // Utworzenie listy z plikami o rozszerzeniu *.jpg
        File[] imageFilesList = directoryModel.getImageFilesList();
     
        // Przetestowanie czy pliki .jpg istnieją w folderach
        assertNotNull(imageFilesList);

        
    }
    
    /**
     * Test wyjątku w przypadku kiedy folder zródłowy nie istnieje
     */
    @Test
    public void SetSourceDirThrowTest() {
    	try {
            directoryModel.setSourceDir("source_test\\fakeDir");    
        } catch (IllegalArgumentException e) {
            assertEquals("Folder źródłowy nie istnieje", e.getMessage());
        }
    }		
    
    /**
     * Test wyjątku w przypadku kiedy folder docelowy nie istnieje
     */
    
    @Test
    public void SetDestinationDirThrowTest() {
    	try {    		
            directoryModel.setDestinationDir("destination_test\\fakeDir");
        } catch (IllegalArgumentException e) {
            assertEquals("Folder docelowy nie istnieje", e.getMessage());
        }
    }		
        	
    /**
     * Test wyjątku w przypadku kiedy folder zródłowy nie został wybrany
     */
    @Test
    public void SourceDirEmptyTest() {
        try {
            directoryModel = new DirectoryModel("", DESTINATION_DIR);
        } catch (IllegalArgumentException e) {
            assertEquals("Nie wybrano folderu źrodłowego", e.getMessage());
        }
    }

    /**
     * Test wyjątku w przypadku kiedy folder docelowy nie został wybrany
     */
    @Test
    public void DestinationDirEmptyTest() {
        try {
            directoryModel = new DirectoryModel(SOURCE_DIR, "");
        } catch (IllegalArgumentException e) {
            assertEquals("Nie wybrano folderu docelowego", e.getMessage());
        }
    }

    /**
     * Test wyjątku w przypadku kiedy wskazany folder zródłowy nie istnieje
     */
    @Test
    public void SourceDirNotExistTest() {
    	final String fakeDir = "Fake_dir_test";
        try {
            directoryModel = new DirectoryModel(fakeDir, DESTINATION_DIR);
        } catch (IllegalArgumentException e) {
            assertEquals("Folder źródłowy nie istnieje", e.getMessage());
        }
    }

    /**
     * Test wyjątku w przypadku kiedy wskazany folder docelowy nie istnieje
     */
    @Test
    public void DestinationDirNotExistTest() {
    	final String fakeDir = "Fake_dir_test";
        try {
            directoryModel = new DirectoryModel(SOURCE_DIR,fakeDir);
        } catch (IllegalArgumentException e) {
            assertEquals("Folder docelowy nie istnieje", e.getMessage());
        }
    }
    
    /**
     * Usunięcie folderów i plików na których były wykonywane testy
     */
    
    @AfterClass
    public static void clean() {
    	File deleteSource = new File(SOURCE_DIR);
    	File deleteDestination = new File(DESTINATION_DIR);
    	File deleteSourceTest = new File(TESTS_DIR);
    	File deleteDestinationTest = new File(TESTD_DIR);
    	File deleteJpg1 = new File("source_test\\image1.jpg");
    	File deleteJpg2 = new File("source_test\\image2.jpg");
    	
    	deleteJpg1.delete();
    	deleteJpg2.delete();
    	deleteSource.delete();
    	deleteDestination.delete();
    	deleteSourceTest.delete();
    	deleteDestinationTest.delete();
    }
   

}





    
