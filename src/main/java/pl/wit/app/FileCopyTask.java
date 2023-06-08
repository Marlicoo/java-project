package pl.wit.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pl.wit.app.model.DirectoryModel;

public class FileCopyTask implements Runnable {

	private File sourceFile;
	private File destinationDir;

	public FileCopyTask(File file, File dir) {
		sourceFile = file;
		destinationDir = dir;
	}

	@Override
	public void run() {

		try {
			copyFile();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	void copyFile() throws Throwable {
		try {
			String creationTime = (String) Files.getAttribute(sourceFile.toPath(), "creationTime").toString();
			System.out.println(creationTime);
			System.out.println(destinationDir.toPath());

			File[] existigFiles = new File(destinationDir.toPath() + File.separator + creationTime).listFiles();
			Integer fileOrder = existigFiles.length + 1;

			File finalDir = new File(destinationDir.toPath() + File.separator + creationTime, fileOrder.toString());

			finalDir.mkdirs();

			Files.copy(sourceFile.toPath(), finalDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
