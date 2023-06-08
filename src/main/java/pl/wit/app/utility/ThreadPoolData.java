package pl.wit.app.utility;

import java.io.FileReader;
import java.util.Properties;

/**
 * <code>ThreadPoolData<code> to read and pass set threads value in a config file
 * 
 * @author marlena.kuc
 *
 */
public class ThreadPoolData {

	/**
	 * 
	 * @return set max threads value
	 * @throws Throwable
	 */
	public static Integer getMaxThreadPool() throws Throwable {
		try (FileReader reader = new FileReader("config")) {
			Properties p = new Properties();
			p.load(reader);

			return Integer.valueOf(p.getProperty("app.threads"));

		} catch (Throwable e) {
			throw e;
		}
	}
}
