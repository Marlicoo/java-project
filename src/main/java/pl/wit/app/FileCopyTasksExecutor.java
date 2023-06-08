package pl.wit.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

import pl.wit.app.utility.ThreadPoolData;

/**
 * The <code>FileCopyTasks</code> is used to process copy files in separate
 * threads Number of threads is used from the config file and read from
 * <code>ThreadPoolData</code>
 * 
 * @author dawid.glogowski
 *
 */
public class FileCopyTasksExecutor {
	private final Collection<FileCopyTask> tasks = new ArrayList<FileCopyTask>();

	/**
	 * 
	 * @param task Add <code>FileCopyTask</code> task
	 */
	public void add(final FileCopyTask task) {
		tasks.add(task);
	}

	/**
	 * Method to process each task
	 * 
	 * @throws Throwable
	 */
	public void process() throws Throwable {
		final ExecutorService executorService = Executors.newFixedThreadPool(ThreadPoolData.getMaxThreadPool());
		List<Future<Boolean>> futureList = new ArrayList<>();

		ReentrantLock lock = new ReentrantLock();

		for (final FileCopyTask task : tasks) {
			task.addLock(lock);
			Future<Boolean> objFut = executorService.submit(task);
			futureList.add(objFut);
		}

		for (Future<Boolean> future : futureList) {
			future.get();
		}

		executorService.shutdown();
	}
}
