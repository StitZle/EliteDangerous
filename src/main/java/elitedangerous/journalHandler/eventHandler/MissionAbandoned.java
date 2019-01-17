package elitedangerous.journalHandler.eventHandler;

import static elitedangerous.utils.Constants.DB_TABLE_MISSIONS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import elitedangerous.functions.SystemLogger;
import elitedangerous.handler.DatabaseHandler;


public class MissionAbandoned
{
	DatabaseHandler databaseHandler = new DatabaseHandler();
	SystemLogger logger = new SystemLogger();

	public void	missionAbondoned ( JSONObject jsonObject ) {

		//Delete Mission where MissionID = MissionID out of Database
		try
		{
			String missionID = jsonObject.get( "MissionID" ).toString();

			String sql = "DELETE FROM " + DB_TABLE_MISSIONS + " WHERE MissionID = ?";

			try (Connection conn = databaseHandler.connect();
					PreparedStatement preparedStatement = conn.prepareStatement( sql )){
				System.out.println(missionID);
				preparedStatement.setString( 1, missionID );
				preparedStatement.executeUpdate();
			}catch( SQLException e ){
				logger.error( "SQL ERROR: " + e );
			}
		}catch( JSONException e ){logger.error( "JSON " + e );}


	}
}
