import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.json.simple.JSONObject;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;

import javax.imageio.ImageIO;

import java.awt.image.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ProjectWindow {
	private static Text SearchBox;
	private static Boolean toggleDetails;
	private static Image img;
	private static Character[] arr;
	private static Character character;
	private static String allCharacters;
	
	private static Image convert(String link) {
		try {
			URL url = new URL(link);
			InputStream is = url.openStream();
			Image image = new Image(Display.getCurrent(), is);
			Image scaled = new Image(Display.getDefault(), 150, 150);
			GC gc = new GC(scaled);
			gc.setAntialias(SWT.ON);
			gc.setInterpolation(SWT.HIGH);
			gc.drawImage(image,  0,  0,
			image.getBounds().width, image.getBounds().height, 0, 0, 150, 150);
			gc.dispose();
			image.dispose();
			return scaled;
		}
		catch(IOException e) {
			e.printStackTrace();
			return null;
			}
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		APIConnection a = new APIConnection(); 
		Storage s = new Storage(); 
		Parser p = new Parser(s); 
		
		RequestByEvent event = new RequestByEvent(a, p, s); 
		RequestByName name = new RequestByName(a, p, s); 
		
		toggleDetails = false;
		
		Display display = Display.getDefault();
		Shell shlMarvelSuperheroFinder = new Shell();
		shlMarvelSuperheroFinder.setSize(800, 600);
		shlMarvelSuperheroFinder.setText("Marvel Superhero Finder");
		
		Text lblDetails = new Text(shlMarvelSuperheroFinder, SWT.BORDER | SWT.READ_ONLY | SWT.V_SCROLL | SWT.H_SCROLL);
		lblDetails.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDetails.setBounds(10, 10, 760, 500);
		lblDetails.setText("Detailed Information");
		lblDetails.setVisible(false);
		
		Text lblInfo = new Text(shlMarvelSuperheroFinder, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		lblInfo.setBounds(23, 60, 550, 400);
		final GridData data = new GridData(SWT.HORIZONTAL, SWT.TOP, true, false, 1, 1);
		lblInfo.setLayoutData(data);
		lblInfo.setText("Information will be displayed here.");
		
		Label lblImage = new Label(shlMarvelSuperheroFinder, SWT.NONE);
		lblImage.setBounds(583, 150, 180, 180);
		
		SearchBox = new Text(shlMarvelSuperheroFinder, SWT.BORDER);
		SearchBox.setBounds(20, 20, 250, 30);
		
		Button btnName = new Button(shlMarvelSuperheroFinder, SWT.NONE);
		btnName.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		btnName.setBounds(350, 20, 90, 30);
		btnName.setText("Name");
		btnName.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				//ignore
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				character = name.get(SearchBox.getText())[0];
				
				
				img = convert(character.getThumbnail());
				
				lblInfo.setText(character.toString());
				lblDetails.setText(character.getResponse());
				lblImage.setImage(img);
			}
		});
		
		Label lblSearchBy = new Label(shlMarvelSuperheroFinder, SWT.NONE);
		lblSearchBy.setBounds(274, 25, 70, 20);
		lblSearchBy.setText("Search by:");
		
		Button btnEvent = new Button(shlMarvelSuperheroFinder, SWT.NONE);
		btnEvent.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		btnEvent.setBounds(450, 20, 90, 30);
		btnEvent.setText("Event");
		btnEvent.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				//ignore
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				arr = event.get(SearchBox.getText());
				
				img = convert(arr[0].getThumbnail());
				
				JSONObject tmp = event.connect(); 
				
				lblInfo.setText(Storage.printArray(arr));
				lblDetails.setText(tmp.toString());
				lblImage.setImage(img);
			}
		});
		
		Button btnShowDetailedInfo = new Button(shlMarvelSuperheroFinder, SWT.NONE);
		btnShowDetailedInfo.setBounds(10, 513, 150, 30);
		btnShowDetailedInfo.setText("Show Detailed Info");
		btnShowDetailedInfo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				//ignore
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (toggleDetails == false) {
					lblDetails.setVisible(true);
					btnShowDetailedInfo.setText("Hide Detailed Info");
					toggleDetails = true;
				}
				else {
					lblDetails.setVisible(false);
					btnShowDetailedInfo.setText("Show Detailed Info");
					toggleDetails = false;
				}
			}
		});

		shlMarvelSuperheroFinder.open();
		shlMarvelSuperheroFinder.layout();
		while (!shlMarvelSuperheroFinder.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			} 
		} 
	}
}
