package elitedangerous.journalHandler;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.json.JSONException;
import org.json.JSONObject;

import elitedangerous.functions.SystemLogger;
import elitedangerous.journalHandler.eventHandler.MissionAbandoned;
import elitedangerous.journalHandler.eventHandler.MissionAccepted;


public class JournalHandler
{
	SystemLogger logger = new SystemLogger();

	public void journalListener()
	{
		try {
			BufferedReader reader = new BufferedReader( new FileReader( "./Journals/" + getLastJournalFile() ) );
			String line;

			while (true) {
				try {
					line = reader.readLine();
					if (line != null) {
						try {
							JSONObject jsonObject = new JSONObject( line );
							eventHandler( jsonObject );
						}catch( JSONException e ){
							logger.error( "Error in line: " + line + " . " + e );
						}
					}
				}catch ( IOException e ){
					logger.error( "Exception occurred trying to read Journal File" + e );
				}
			}
		} catch ( FileNotFoundException e ){
			logger.error( "Exception occurred trying to read Journal File" + e );
		}
	}


	public String getLastJournalFile ()
	{
		File folder = new File( "./Journals/" );
		ArrayList<String> listOfFiles = new ArrayList<>( Arrays.asList( folder.list() ) );
		ArrayList<Long> listOfTimestamps = new ArrayList<>(  );

		for( int i = 0; i < listOfFiles.size(); i++ ) {
			String file = listOfFiles.get( i );
			if( !file.toLowerCase().contains( "journal" ) || (file.toLowerCase().contains( "cache" )) ) {
				listOfFiles.remove( i );
			}
			else {
				file = file.replace( "Journal.", "" ).replace( ".01.log", "" );
				listOfTimestamps.add( Long.valueOf(file) );
			}
		}
		Collections.sort( listOfTimestamps, Collections.reverseOrder() );
		String newestJournalTimestamp = String.valueOf( listOfTimestamps.get( 0 ) );

		//search File in directory

		for( String file : listOfFiles) {
			if (file.contains( newestJournalTimestamp )){return file;}
		}
		return null;
	}

	public void eventHandler (JSONObject jsonObject)
	{
		MissionAccepted missionAccepted = new MissionAccepted();
		MissionAbandoned missionAbandoned = new MissionAbandoned();

		switch( jsonObject.getString( "event" ) ) {

			case "MissionAccepted" : missionAccepted.missionAccepted(jsonObject); break;
			case "MissionAbandoned" : missionAbandoned.missionAbondoned( jsonObject ); break;

			case "MissionCompleted" :
				System.out.println("MissionCompleted"); break;
		}

	}

}
