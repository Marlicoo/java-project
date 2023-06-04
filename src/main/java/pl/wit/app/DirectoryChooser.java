package pl.wit.app;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

public class DirectoryChooser extends JFrame implements ActionListener {
    static JLabel l;
    File sourceDir;
    File destinationDir;

    JFileChooser chooser;
    String choosertitle;

    public DirectoryChooser() {
        super("Kopiowanie plików JPG");

        JButton startButton = new JButton("Start");
        startButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(startButton);
        getContentPane().add(panel);
    }

    public void actionPerformed(ActionEvent evt) {
        String com = evt.getActionCommand();

        if (com.equals("Start")) {
            if (sourceDir == null || destinationDir == null) {
                l.setText("Proszę wybrać folder źródłowy i folder docelowy");
                return;
            }

            try {
                // Wywołanie metody copyJpgFiles z klasy JpgFileCopier
                JpgFileCopier.copyJpgFiles(sourceDir.getAbsolutePath(), destinationDir.getAbsolutePath());
                l.setText("Kopiowanie zakończone!");
            } catch (IOException e) {
                e.printStackTrace();
                l.setText("Wystąpił błąd podczas kopiowania plików");
            }
        } else {
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int r = j.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                if (sourceDir == null) {
                    // Wybór folderu źródłowego
                    sourceDir = j.getSelectedFile();
                    l.setText("Wybrano folder źródłowy: " + sourceDir.getAbsolutePath());
                } else {
                    // Wybór folderu docelowego
                    destinationDir = j.getSelectedFile();
                    l.setText("Wybrano folder docelowy: " + destinationDir.getAbsolutePath());
                }
            } else {
                l.setText("Operacja anulowana przez użytkownika");
            }
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
}
