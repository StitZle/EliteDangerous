package elitedangerous.handler;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import elitedangerous.functions.SystemLogger;


public class JournalHandler
{
	SystemLogger logger = new SystemLogger();

	public void journalListener()
	{

		// Get Filename
		try {
			BufferedReader reader = new BufferedReader( new FileReader( getLastJournalFile() ) );
			String line;

			while (true) {
				try {
					line = reader.readLine();
					if (line != null) {
						System.out.println(line);
						JSONObject jsonObj = new JSONObject(jsonString.toString());
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
		File folder = new File( "Path" );
		ArrayList<String> listOfFiles = new ArrayList<>( Arrays.asList( folder.list() ) );
		ArrayList<Integer> listOfTimestamps = new ArrayList<>(  );


		for( String file : listOfFiles ) {
			if( !file.toLowerCase().contains( "Journal" ) ) {
				listOfFiles.remove( file );
			}
			else {
				file.toLowerCase().replace( "Journal.", "" ).replace( ".01.log", "" );
				listOfTimestamps.add( Integer.parseInt(file) );
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

}
