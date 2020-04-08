package co.edu.utp.gia.sms.configuration;

import javax.annotation.sql.DataSourceDefinition;

@DataSourceDefinition(
		  name = "java:app/smsgia-web/smsDS",
		  className = "org.h2.jdbcx.JdbcDataSource",
		  initialPoolSize = 10,
		  minPoolSize = 10,
		  maxPoolSize = 200,
//		  url = "jdbc:h2:mem:test"
		  url = "jdbc:h2:file:./sms"
		)
//https://blog.payara.fish/an-intro-to-connection-pools-in-payara-server-5
public class DataSourceConfiguration {

}
