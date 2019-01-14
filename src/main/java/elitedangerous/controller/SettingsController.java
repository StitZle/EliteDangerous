package elitedangerous.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class SettingsController
{
	public Button buttonToggleFullscreen;

	public void toggleFullscreen( ActionEvent actionEvent )
	{

		buttonToggleFullscreen.setOnAction( new EventHandler<ActionEvent>()
		{
			@Override
			public void handle( ActionEvent event )
			{
				Stage stage = (Stage) buttonToggleFullscreen.getScene().getWindow();
				if (stage.isFullScreen()) {stage.setFullScreen( false );}
				else stage.setFullScreen( true );
			}
		} );

	}
}