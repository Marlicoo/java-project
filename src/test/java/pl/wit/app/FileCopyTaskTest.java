package pl.wit.app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Klasa testowa kopiująca plik do folderu docelowego.
 * 
 * @author Michał Lemański
 *
 */
public class FileCopyTaskTest {

	private FileCopyTask copyTaskUnderTest;
	private String fileDateCreate;
	private File sourceFile;
	public TemporaryFolder destinationDir = new TemporaryFolder();
	public TemporaryFolder sourceDir = new TemporaryFolder();

	@Before
	public void setUp() throws IOException {
		destinationDir.create();
		sourceDir.create();

		sourceFile = sourceDir.newFile("image1.jpg");
		fileDateCreate = (String) Files.getAttribute(sourceFile.toPath(), "creationTime").toString();

		copyTaskUnderTest = new FileCopyTask(sourceFile, destinationDir.getRoot());
	}

	@Test
	public void testCopyFileToGivenDestinationDir() throws Exception {
		Boolean result = copyTaskUnderTest.call();
		assertTrue(result);

		File destinationFile = new File(destinationDir.getRoot() + File.separator + fileDateCreate, "1.jpg");

		assertTrue(destinationFile.exists());
		assertFileCopiedWithSuccess(sourceFile, destinationFile);
	}

	@Test
	public void testCopyFileWithNextNumberNameIfOtherFileExists() throws Exception {
		givenFileExistsInDestinationFolder("1.jpg");

		// Wywołanie metody do przetestowania
		Boolean result = copyTaskUnderTest.call();
		assertTrue(result);

		File destinationFile = new File(destinationDir.getRoot() + File.separator + fileDateCreate, "2.jpg");

		assertTrue(destinationFile.exists());
		assertFileCopiedWithSuccess(sourceFile, destinationFile);
	}

	private void givenFileExistsInDestinationFolder(String fileName) throws IOException {
		destinationDir.newFolder(fileDateCreate);
		destinationDir.newFile(fileDateCreate + File.separator + fileName);
	}

	private void assertFileCopiedWithSuccess(File sourceFile, File destinationFile) throws IOException {
		byte[] sourceBytes1 = Files.readAllBytes(sourceFile.toPath());
		byte[] destinationBytes1 = Files.readAllBytes(destinationFile.toPath());
		assertArrayEquals(sourceBytes1, destinationBytes1);
	}
}