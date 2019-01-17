package elitedangerous;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import elitedangerous.handler.DatabaseHandler;
import elitedangerous.journalHandler.JournalHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import elitedangerous.functions.SystemLogger;
import static elitedangerous.utils.Constants.*;

public class Main extends Application {

	@Override
    public void start(Stage dashboardStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource( "/fxml/settings.fxml" ));

        dashboardStage.setTitle(PROGRAMM_NAME);
        dashboardStage.setScene(new Scene(root, 1280, 720));
        dashboardStage.getIcons().add( new Image( getClass().getResource( "/images/spaceship.png" ).toExternalForm() ) );
        dashboardStage.show();
		FXMLLoader.load(getClass().getResource("/fxml/settingsButton.fxml"));
    }



    public static void main(String[] args)
    {
		launch(args);
        Main main = new Main();
        main.init();
    }

    public void init ()
    {
        // Init
		SystemLogger logger = new SystemLogger();
		try
		{
			logger.newFile();
		}
		catch (FileNotFoundException | UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}


		DatabaseHandler databaseHandler = new DatabaseHandler();
		databaseHandler.generateNewDatabase();
		databaseHandler.generateTables();


		logger.info( "Loading JournalLog..." );
		JournalHandler journalHandler = new JournalHandler();
		journalHandler.journalListener();

		System.exit( 404 );




    }
}
