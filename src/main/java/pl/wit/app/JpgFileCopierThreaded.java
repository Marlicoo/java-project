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
        File sourceFolder = new File(sourceDir);
        File destinationFolder = new File(destinationDir);

        // Sprawdzenie istnienia folderu docelowego i utworzenie go, jeśli nie istnieje
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }

        // Przeszukiwanie folderu źródłowego i uruchomienie wątków do kopiowania plików JPG
        File[] files = sourceFolder.listFiles();
        if (files != null) {
            ExecutorService executor = Executors.newFixedThreadPool(threadCount);
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".jpg")) {
                    executor.execute(() -> {
                        try {
                            // Wywołanie metody copyJpgFiles z klasy JpgFileCopier dla każdego pliku
                            JpgFileCopier.copyJpgFiles(file.getAbsolutePath(), destinationFolder.getAbsolutePath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);
        }
    }
}
