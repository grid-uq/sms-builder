package co.edu.utp.gia.sms.configuration;

import javax.annotation.sql.DataSourceDefinition;
import javax.annotation.sql.DataSourceDefinitions;
/**
 * Clase encargada de realizar la configuración del datasource en la aplicación
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@DataSourceDefinitions({
	
	@DataSourceDefinition(
			name = "java:app/sms-builder-web/smsDS",
			className = "org.h2.jdbcx.JdbcDataSource",
			initialPoolSize = 10,
			minPoolSize = 10,
			maxPoolSize = 200,
//			url = "jdbc:h2:mem:test"
			url = "jdbc:h2:file:./sms"
			),
//	@DataSourceDefinition(
//	name = "java:app/sms-builder-web/mysql",
////	className = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
////	className = "com.mysql.jdbc.Driver",
//	className = "com.mysql.cj.jdbc.MysqlDataSource",
//	url = "jdbc:mysql://localhost:3306/sms",
////	serverName="192.168.211.254",
//	serverName="localhost",
//	databaseName = "sms",
//	portNumber=3306,
//	user = "sms",
//	password = "sms-12345"
//		)
	
	
	
	@DataSourceDefinition(
			name = "java:app/sms-builder-web/mysql", //
			className = "com.mysql.cj.jdbc.MysqlDataSource", //
//			className = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
			initialPoolSize = 2,
			minPoolSize = 2,
			maxPoolSize = 10,
			serverName = "localhost", //
			portNumber = 3306, //
			user = "sms", //
			password = "sms-12345", //
			databaseName = "sms", //
			properties = { //
			"useSSL=false", "allowPublicKeyRetrieval=True","useInformationSchema=true", "nullCatalogMeansCurrent=true", "nullNamePatternMatchesAll=false" //
			})
	

//	@DataSourceDefinition(name = "java:global/MyApp/myDS",
//	className = "org.apache.derby.jdbc.ClientDataSource",
//	portNumber = 1527,
//	serverName = "localhost",
//	databaseName = "testDB21",
//	user = "lance",
//	password = "secret",
//	properties = {"createDatabase=create"}),
//
//	@DataSourceDefinition(name = "java:global/MyApp/myDS2",
//	className = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource",
//	portNumber = 3306,
//	serverName = "localhost",
//	databaseName = "dogDB",
//	user = "luckyDog",
//	password = "shhh",
//	properties = {"pedantic=true"})
	})

//https://blog.payara.fish/an-intro-to-connection-pools-in-payara-server-5
public class DataSourceConfiguration {

}
