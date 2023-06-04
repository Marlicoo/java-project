package pl.wit.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class JpgFileCopier {

    /**
     * Kopiuje pliki JPG z folderu źródłowego do folderu docelowego.
     *
     * @param sourceDir      ścieżka folderu źródłowego
     * @param destinationDir ścieżka folderu docelowego
     * @throws IOException jeśli wystąpi błąd podczas kopiowania plików
     */
    public static void copyJpgFiles(String sourceDir, String destinationDir) throws IOException {
        File sourceFolder = new File(sourceDir);
        File destinationFolder = new File(destinationDir);
        
		System.out.println("Rozpoczecie kopiowania");


        // Sprawdzenie istnienia folderu docelowego i utworzenie go, jeśli nie istnieje
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }

        // Przeszukiwanie folderu źródłowego i kopiowanie plików JPG
        File[] files = sourceFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".jpg")) {
                    String fileName = file.getName();
                    File destinationFile = new File(destinationFolder, fileName);
                    // Kopiowanie pliku do folderu docelowego
                    Files.copy(file.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }
}
