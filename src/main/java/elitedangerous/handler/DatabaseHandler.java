package elitedangerous.handler;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.sql.Statement;

import elitedangerous.functions.SystemLogger;
import static elitedangerous.utils.Constants.*;

public class DatabaseHandler
{
	SystemLogger logger = new SystemLogger();

	public Connection connect() {
		Connection conn = null;
		try
		{
			conn = DriverManager.getConnection( DB_URL + DB_NAME );
		}catch (SQLException e){
			logger.error( "SQLExecption:" + e );
		}
		return conn;
	}


	public void generateNewDatabase() {
		try (Connection conn = connect()) {
			logger.info( "Connection: " + conn );
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				logger.info( "The database driver name is: " + meta.getDriverName() );
				logger.info( "Database driver version: " + meta.getDriverVersion() );
				logger.info( "Database Path: " + DB_URL+DB_NAME );

			}
		}
		catch (SQLException e) {
			logger.error( "SQLExecption: " + e);
		}
	}

	public void generateTables () {

		String missionTable = "CREATE TABLE IF NOT EXISTS " + DB_TABLE_MISSIONS + "(\n"
				+ "event text,\n"
				+ "MissionID text PRIMARY KEY, \n"
				+ "Name text,\n"
				+ "LocalisedName text,\n"
				+ "Faction text,\n"

				+ "Influence text,\n"
				+ "Reputation text,\n"

				+ "Commodity text,\n"
				+ "Commodity_Localised text,\n"
				+ "Count text,\n"

				+ "Target text,\n"
				+ "TargetType text,\n"
				+ "TargetFaction text,\n"
				+ "KillCount text,\n"


				+ "DestinationSystem text,\n"
				+ "DestinationStation text,\n"

				+ "PassengerCount text,\n"
				+ "PassengerVIPs text,\n"
				+ "PassengerWanted text,\n"
				+ "PassengerType text,\n"

				+ "Reward text,\n"

				+ "Wing text,\n"

				+ "timestamp text,\n"
				+ "Expiry text,\n"
				+ "JsonString text\n"
				+ ");";

		try	(Connection conn = connect();
				Statement stmt = conn.createStatement()) {

			stmt.execute( missionTable );
			logger.info( "Table name: " + DB_TABLE_MISSIONS  );

			}catch( SQLException e ){
			logger.error( "SQL ERROR in createTable: " + e );
		}
	}
}
