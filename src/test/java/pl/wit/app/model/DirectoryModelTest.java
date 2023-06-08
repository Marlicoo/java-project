package pl.wit.app.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

/**
 * Klasa testowa sprawdzająca stany folderów.
 * 
 * @author Michał Lemański
 *
 */

public class DirectoryModelTest {

	public TemporaryFolder destinationDir = new TemporaryFolder();
	public TemporaryFolder sourceDir = new TemporaryFolder();

	@Before
	public void setUp() throws IOException {
		destinationDir.create();
		sourceDir.create();
	}

	@Test
	public void testDirectoryModelCreated() throws IOException {
		DirectoryDataToCopy directoryModel = new DirectoryDataToCopy(sourceDir.getRoot().getAbsolutePath(),
				destinationDir.getRoot().getAbsolutePath());

		File setSourceDir = directoryModel.getSourceDir();
		assertEquals(sourceDir.getRoot().getAbsolutePath(), setSourceDir.getPath());

		File setDestinationDir = directoryModel.getDestinationDir();
		assertEquals(destinationDir.getRoot().getAbsolutePath(), setDestinationDir.getPath());

		File[] imageFilesList = directoryModel.getImageFilesList();
		assertEquals(0, imageFilesList.length);
	}

	@Test
	public void testDirectoryModelCreatedAndJPGFilesWereFiltered() throws IOException {

		sourceDir.newFile("image1.jpg");
		sourceDir.newFile("text1.txt");

		DirectoryDataToCopy directoryModel = new DirectoryDataToCopy(sourceDir.getRoot().getAbsolutePath(),
				destinationDir.getRoot().getAbsolutePath());

		File setSourceDir = directoryModel.getSourceDir();
		assertEquals(sourceDir.getRoot().getAbsolutePath(), setSourceDir.getPath());

		File setDestinationDir = directoryModel.getDestinationDir();
		assertEquals(destinationDir.getRoot().getAbsolutePath(), setDestinationDir.getPath());

		File[] imageFilesList = directoryModel.getImageFilesList();
		assertEquals(1, imageFilesList.length);
	}

	/**
	 * Test wyjątku w przypadku kiedy folder zródłowy nie istnieje
	 */
	@Test
	public void testExceptionThrownIfSourceFolderDoesNotExist() {
		try {
			new DirectoryDataToCopy("source_test\\fakeDir",
					destinationDir.getRoot().getAbsolutePath());
		} catch (IllegalArgumentException e) {
			assertEquals("Folder źródłowy nie istnieje", e.getMessage());
		}
	}

	/**
	 * Test wyjątku w przypadku kiedy folder docelowy nie istnieje
	 */

	@Test
	public void testSetDestinationDirThrowTest() {
		try {
			new DirectoryDataToCopy(sourceDir.getRoot().getAbsolutePath(),
					"source_test\\fakeDir");
		} catch (IllegalArgumentException e) {
			assertEquals("Folder docelowy nie istnieje", e.getMessage());
		}
	}

	/**
	 * Test wyjątku w przypadku kiedy folder zródłowy nie został wybrany
	 */
	@Test
	public void testSourceDirEmptyTest() {
		try {
			new DirectoryDataToCopy("",
					destinationDir.getRoot().getAbsolutePath());
		} catch (IllegalArgumentException e) {
			assertEquals("Nie wybrano folderu źrodłowego", e.getMessage());
		}
	}

	/**
	 * Test wyjątku w przypadku kiedy folder docelowy nie został wybrany
	 */
	@Test
	public void testDestinationDirEmptyTest() {
		try {
			new DirectoryDataToCopy(sourceDir.getRoot().getAbsolutePath(), "");
		} catch (IllegalArgumentException e) {
			assertEquals("Nie wybrano folderu docelowego", e.getMessage());
		}
	}
}
