package pl.wit.app;

import java.io.File;

import pl.wit.app.model.DirectoryModel;

/**
 * The <code>FileCopierService</code> is used to set copy files
 * tasks and process copy of <code>FileCopyTask<code>
 *  
 * @author dawid.glogowski
 *
 *
 */
public class FileCopierService {

	private DirectoryModel directoryData;

	/**
	 * @param dirModel
	 */
	public FileCopierService(DirectoryModel dirModel) {
		directoryData = dirModel;
	}

	/**
	 * @throws Throwable
	 */
	public void copy() throws Throwable {

		try {
			FileCopyTasks tasks = new FileCopyTasks();

			for (File file : directoryData.getImageFilesList()) {
				tasks.add(new FileCopyTask(file, directoryData.getDestinationDir()));
			}
			tasks.process();
		} catch (Throwable e) {
			System.err.println(e.getMessage());
			throw e;
		}

	}
}
