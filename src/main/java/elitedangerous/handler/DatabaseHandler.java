package elitedangerous.handler;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;

import elitedangerous.functions.SystemLogger;
import static elitedangerous.utils.Constants.*;

public class DatabaseHandler
{
	SystemLogger logger = new SystemLogger();

	public void generateNewDatabase()
	{
		System.out.println(DB_URL+DB_NAME);
		//GenerateDatabase and Tabels.
		try (Connection conn = DriverManager.getConnection( DB_URL + DB_NAME ))
		{
			logger.info( "Connection: " + conn );
			if (conn != null)
			{
				DatabaseMetaData meta = conn.getMetaData();
				logger.info( "New Database created."  );
				logger.info( "The database driver name is: " + meta.getDriverName() );
				logger.info( "Database driver version: " + meta.getDriverVersion() );
				logger.info( "Database Path: " + DB_URL+DB_NAME );
				System.out.println("Hallo");

			}
		}
		catch (SQLException e)
		{
			logger.error( "SQLExecption: " + e);
		}
	}
}
