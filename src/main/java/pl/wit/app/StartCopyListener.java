package pl.wit.app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.wit.app.view.CopyFilesPanel;

public class StartCopyListener implements ActionListener {

	private final CopyFilesPanel view;

	public StartCopyListener(CopyFilesPanel view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			view.setLabelText("", false);
			view.setModel();

			FileCopierService copyService = new FileCopierService(view.getModel());
			copyService.copy();
			view.setLabelText("Skopiowano " + view.getModel().getImageFilesList().length + " plików", true);
		} catch (Throwable e) {
			view.setLabelText(e.getMessage(), true);
		}
	}

}
