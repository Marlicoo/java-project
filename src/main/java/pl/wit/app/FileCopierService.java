package pl.wit.app;

import java.io.File;

import pl.wit.app.model.DirectoryModel;

public class FileCopierService {

	private DirectoryModel directoryData;

	public FileCopierService(DirectoryModel dirModel) {
		directoryData = dirModel;
	}

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
