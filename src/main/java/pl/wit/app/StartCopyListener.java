package pl.wit.app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.wit.app.view.CopyFilesPanel;

/**
 * The <code>StartCopyListener</code> implements <code>ActionListener</code>
 * and it's trigger on start button being pressed,
 * Class triggers <code>FileCopierService</code>
 * In case of any error it sets message in a view label
 * 
 * @author marlena.kuc
 *
 */
public class StartCopyListener implements ActionListener {

	private final CopyFilesPanel view;

	/**
	 * 
	 * @param view CopyFilesPanel
	 */
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
