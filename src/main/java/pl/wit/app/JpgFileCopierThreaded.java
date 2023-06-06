package pl.wit.app;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JpgFileCopierThreaded {

    /**
     * Kopiuje pliki JPG z folderu źródłowego do folderu docelowego z wykorzystaniem wielu wątków.
     *
     * @param sourceDir      ścieżka folderu źródłowego
     * @param destinationDir ścieżka folderu docelowego
     * @param threadCount    liczba wątków do użycia
     * @throws InterruptedException jeśli wystąpi błąd podczas oczekiwania na zakończenie wątków
     */
	
    public static void copyJpgFilesThreaded(String sourceDir, String destinationDir, int threadCount) throws InterruptedException {
    	{
            ExecutorService executor = Executors.newFixedThreadPool(threadCount);
            executor.execute(() -> {
                try {
                    JpgFileCopier.copyJpgFiles(sourceDir, destinationDir);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);
        }
    }
}
