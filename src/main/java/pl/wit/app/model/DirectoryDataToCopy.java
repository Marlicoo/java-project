package pl.wit.app.model;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Class <code>DirectoryModel</code> is model that contains required data for file copier service
 * Based on the sourceDir param all files to copy are set in imageFilesList param. 
 * Default files filter now is .jpg and could be extended with any format as an extra param in the constructor
 * 
 * @author marlena.kuc
 *
 */
public class DirectoryDataToCopy {

	private File sourceDir;
	private File destinationDir;
	private File[] imageFilesList;

	/**
	 * 
	 * @param sourceDir from which files will be copied
	 * @param destinationDir to which files will be copied
	 */
	public DirectoryDataToCopy(String sourceDir, String destinationDir) {

		if (sourceDir.isEmpty() || destinationDir.isEmpty()) {
			String folder = sourceDir.isEmpty() ? "źrodłowego" : "docelowego";

			throw new IllegalArgumentException("Nie wybrano folderu " + folder);
		}

		this.sourceDir = new File(sourceDir);
		this.destinationDir = new File(destinationDir);

		if (!this.sourceDir.exists() || !this.sourceDir.exists()) {
			String folder = !this.sourceDir.exists() ? "źródłowy" : "docelowy";
			throw new IllegalArgumentException("Folder " + folder + " nie istnieje");
		}

		setImageFilesList();
	}

	/**
	 * 
	 * @return source directory 
	 */
	public File getSourceDir() {
		return sourceDir;
	}

	/**
	 * 
	 * @return destination directory
	 */
	public File getDestinationDir() {
		return destinationDir;
	}

	/**
	 * 
	 * @return files to copy filtered by jpg filter
	 */
	public File[] getImageFilesList() {
		return imageFilesList;
	}

	/**
	 * Sets image files from source directory
	 */
	private void setImageFilesList() {
		FilenameFilter jpgFilefilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				return lowercaseName.endsWith(".jpg") ? true : false;
			}
		};

		imageFilesList = sourceDir.listFiles(jpgFilefilter);
	}
}
