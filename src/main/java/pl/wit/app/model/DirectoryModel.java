package pl.wit.app.model;

import java.io.File;
import java.io.FilenameFilter;

public class DirectoryModel {

	private File sourceDir;
	private File destinationDir;
	private File[] imageFilesList;

	public DirectoryModel(String sourceDir, String destinationDir) {

		if (sourceDir.isEmpty() || destinationDir.isEmpty()) {
			String folder = sourceDir.isEmpty() ? "źrodłowego" : "docelowego";

			throw new IllegalArgumentException("Nie wybrano folderu " + folder);
		}

		this.sourceDir = new File(sourceDir);
		this.destinationDir = new File(destinationDir);

		if (!this.sourceDir.exists() || !this.sourceDir.exists()) {
			String folder = this.sourceDir.exists() ? "źródłowy" : "docelowy";
			throw new IllegalArgumentException("Folder " + folder + " nie istnieje");
		}

		setImageFilesList();
	}

	public File getSourceDir() {
		return sourceDir;
	}

	public void setSourceDir(String sourceDirPath) {

		File sourceDi = new File(sourceDirPath);
		if (!sourceDir.exists()) {
			throw new IllegalArgumentException();
		}

		this.sourceDir = sourceDi;

		setImageFilesList();

	}

	public File getDestinationDir() {
		return destinationDir;
	}

	public void setDestinationDir(String destinationDirPath) {
		File destinationD = new File(destinationDirPath);

		if (!destinationD.exists()) {
			throw new IllegalArgumentException();
		}
		destinationDir = destinationD;
	}

	public File[] getImageFilesList() {
		return imageFilesList;
	}

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
