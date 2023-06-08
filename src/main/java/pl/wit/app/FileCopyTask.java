package pl.wit.app;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.Callable;

/**
 * The <code>FileCopyTask</code> process copy of the files from source to
 * destination directory Class copy files with rules: file is copied to
 * destination dir in subdirectory named as file date create metadata, file name
 * is next number
 * 
 * @author dawid.glogowski
 *
 */
public class FileCopyTask implements Callable<Boolean> {

	private File sourceFile;
	private File destinationDir;

	/**
	 * 
	 * @param file File to copy
	 * @param dir  Destination directory
	 */
	public FileCopyTask(File file, File dir) {
		sourceFile = file;
		destinationDir = dir;
	}

	@Override
	public Boolean call() throws Exception {

		String creationTime = (String) Files.getAttribute(sourceFile.toPath(), "creationTime").toString();

		File[] existigFiles = new File(destinationDir.toPath() + File.separator + creationTime).listFiles();
		Integer fileOrder = null != existigFiles ? existigFiles.length + 1 : 1;

		File finalDir = new File(destinationDir.toPath() + File.separator + creationTime, fileOrder.toString()+ ".jpg");

		finalDir.mkdirs();

		Files.copy(sourceFile.toPath(), finalDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
		
		return true;
	}
}
