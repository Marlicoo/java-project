package pl.wit.app;

import java.io.File;

import pl.wit.app.model.DirectoryDataToCopy;

/**
 * The <code>FileCopierService</code> is used to set copy files
 * tasks and process copy of <code>FileCopyTask</code>
 *  
 * @author dawid.glogowski
 *
 *
 */
public class FileCopierService {

	private DirectoryDataToCopy directoryData;

	/**
	 * @param dirModel
	 */
	public FileCopierService(DirectoryDataToCopy dirModel) {
		directoryData = dirModel;
	}

	/**
	 * @throws Throwable
	 */
	public void copy() throws Throwable {

		try {
			FileCopyTasksExecutor tasks = new FileCopyTasksExecutor();

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
