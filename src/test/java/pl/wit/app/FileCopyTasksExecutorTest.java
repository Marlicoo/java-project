package pl.wit.app;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileCopyTasksExecutorTest {

	private FileCopyTasksExecutor copyTasksExecutorUnderTests;
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
		
		copyTasksExecutorUnderTests = new FileCopyTasksExecutor();
		copyTasksExecutorUnderTests.add(new FileCopyTask(sourceFile, destinationDir.getRoot()));
		copyTasksExecutorUnderTests.add(new FileCopyTask(sourceFile, destinationDir.getRoot()));
    }

    @Test
    public void testShouldProcessCopyTaskWithTheSameSubDirectoryTarget() throws Throwable {
    	copyTasksExecutorUnderTests.process();
    	
		File destinationFile1 = new File(destinationDir.getRoot() 
				+ File.separator + fileDateCreate, "1.jpg");
		File destinationFile2 = new File(destinationDir.getRoot()
				+ File.separator + fileDateCreate, "2.jpg");

		assertTrue(destinationFile1.exists());
		assertTrue(destinationFile2.exists());

    }
}