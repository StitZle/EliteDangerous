package elitedangerous.journalHandler.eventHandler;

import org.json.JSONObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import static elitedangerous.utils.Constants.*;

import elitedangerous.functions.SystemLogger;
import elitedangerous.handler.DatabaseHandler;


public class MissionAccepted
{
	DatabaseHandler databaseHandler = new DatabaseHandler();
	SystemLogger logger = new SystemLogger();

	public void missionAccepted ( JSONObject jsonObject )
	{

		//Get MissionID
		String missionID = jsonObject.get( "MissionID" ).toString();
		//Enter MissionID  and json in DB

		String missionIDSql = "INSERT INTO " + DB_TABLE_MISSIONS + "(MissionID) VALUES (?)";

		try (Connection conn = databaseHandler.connect();
		PreparedStatement preparedStatement = conn.prepareStatement( missionIDSql )){
			preparedStatement.setString( 1, missionID );
			preparedStatement.execute();
		}catch( SQLException e	 ){
			logger.error( "Cloud not enter MissionID: " + missionID + " in Database" );
		}


		for( int i = 0; i < jsonObject.names().length(); i++ )
		{
			String key = jsonObject.names().getString( i );
			String value = jsonObject.get( jsonObject.names().getString( i ) ).toString();

			if (key != "MissionID") {
				//Enter Data into DB
				String sql = "UPDATE " + DB_TABLE_MISSIONS + " SET " + key + " = ? WHERE MissionID = " + missionID;

				try	(Connection conn = databaseHandler.connect();
				PreparedStatement preparedStatement = conn.prepareStatement( sql )) {
					preparedStatement.setString( 1, value );
					preparedStatement.execute();
				}catch( SQLException e ) {
					logger.error( "SQLExecptiion: " + e );
				}
			}

		}
	}
}
