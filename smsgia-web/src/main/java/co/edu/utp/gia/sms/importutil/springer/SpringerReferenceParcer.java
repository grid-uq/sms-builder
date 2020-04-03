package co.edu.utp.gia.sms.importutil.springer;

import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.importutil.Fuente;
import co.edu.utp.gia.sms.importutil.ReferenceParser;

/**
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @version 1.0
 * @since 20/06/2019
 *
 */

// El archivo de la bases de datos SPRINGER está presentado con formato horizontlal y separados por comas.

public class SpringerReferenceParcer extends ReferenceParser {

	public SpringerReferenceParcer() {
		super(Fuente.SPRINGER);
	}

	protected void procesarTexto(Referencia reference, String texto) {

		String elementos[] = texto.split("\",\"");

		if (elementos != null && elementos.length >= 25) { //revisar porque 25

			for (int i = 0; i < elementos.length; i++) {
				if (elementos[i] != null && !elementos[i].isEmpty()) {
					
					String value = elementos[i].indexOf(",") == 0 ? elementos[i].substring(1) : elementos[i];
					if (!"".equals(value)) {
						TipoMetadato identifier = identifierOf(i);
						if (TipoMetadato.AUTOR.equals(identifier)) {
							addAutors(reference, value);
						} else if (TipoMetadato.KEYWORD.equals(identifier)) {
							addKeywords(reference, value);
						} else {
							reference.addElement(identifier, value);
						}
					}
				}
			}
		}

	}

	private void addAutors(Referencia reference, String value) {
		String autors[] = value.split("; ");
		for (String autor : autors) {
			reference.addElement(TipoMetadato.AUTOR, autor);
		}
	}

	private void addKeywords(Referencia reference, String value) {
		String keywords[] = value.split(";");
		for (String keyword : keywords) {
			reference.addElement(TipoMetadato.KEYWORD, keyword);
		}
	}


/*	
//	"Document Title",Authors,"Author Affiliations","Publication Title",Date Added To Xplore,"Publication_Year","Volume","Issue","Start Page","End Page","Abstract","ISSN",ISBNs,"DOI",Funding Information,PDF Link,"Author Keywords","IEEE Terms","INSPEC Controlled Terms","INSPEC Non-Controlled Terms","Mesh_Terms",Article Citation Count,"Reference Count","License","Online Date",Issue Date,"Meeting Date","Publisher",Document Identifier
//	"A Dashboard for Microservice Monitoring and Management","B. Mayer; R. Weinreich","NA; NA","2017 IEEE International Conference on Software Architecture Workshops (ICSAW)","","2017","","","66","69","We present an experimental dashboard for microservice monitoring and management. The dashboard can be adapted to different stakeholder needs and it supports the integration of different monitoring infrastructures for collecting microservice runtime data. Aside from runtime information, the dashboard also supports the integration of other information sources for providing static information about microservices and microservice development. We describe the main motivation for developing the dashboard, explain the basic concepts and present important usage scenarios and views currently supported in the dashboard.","","978-1-5090-4793-2978-1-5090-4794","10.1109/ICSAW.2017.44","","https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=7958458","Microservices;microservice monitoring;microservice management;microservice dashboard","Conferences;Software architecture","data acquisition;data integration;service-oriented architecture;system monitoring","microservice monitoring;experimental dashboard;microservice management;monitoring infrastructures;microservice runtime data collection;information sources;microservice development","","3","8","","","","","IEEE","IEEE Conferences"
	private TipoMetadato identifierOf(int index) {
		switch (index) {
		case 0:
			return TipoMetadato.TITLE;
		case 1:
			return TipoMetadato.AUTOR;
		case 3:
		case 27:	
			return TipoMetadato.PUBLISHER;
		case 5:
			return TipoMetadato.YEAR;
		case 10:
			return TipoMetadato.ABSTRACT;
		case 11:
		case 12:
			return TipoMetadato.ISBN;
		case 13:	
			return TipoMetadato.DOI;
		case 16:
			return TipoMetadato.KEYWORD;
		case 28:
			return TipoMetadato.TYPE;
		default:
			return TipoMetadato.NOT_SUPORT;
		}

	}
*/
	
	/*
	0 - "Document Title",
	1 - Authors,
	2 - "Author Affiliations",
	3 - "Publication Title",
	4 - Date Added To Xplore,
	5 - "Publication Year",
	6 - "Volume",
	7 - "Issue",
	8 - "Start Page",
	9 - "End Page",
	10- "Abstract",
	11- "ISSN",
	12- ISBNs,
	13- "DOI",
	14-Funding Information,
	15-PDF Link,
	16-"Author Keywords",
	17-"IEEE Terms",
	18-"INSPEC Controlled Terms",
	19-"INSPEC Non-Controlled Terms",
	20-"Mesh_Terms",
	21-Article Citation Count,
	22-Patent Citation Count,
	23-"Reference Count",
	24-"License",
	25-"Online Date",
	26-Issue Date,
	27-"Meeting Date",
	28-"Publisher",
	29-Document Identifier	

Ejemplo: 

"Document Title",Authors,"Author Affiliations","Publication Title",Date Added To Xplore,"Publication Year","Volume","Issue","Start Page","End Page","Abstract","ISSN",ISBNs,"DOI",Funding Information,PDF Link,"Author Keywords","IEEE Terms","INSPEC Controlled Terms","INSPEC Non-Controlled Terms","Mesh_Terms",Article Citation Count,Patent Citation Count,"Reference Count","License","Online Date",Issue Date,"Meeting Date","Publisher",Document Identifier
"A Unified Framework for Software Defined Sensing, Transmission and Computing","J. Sun; F. Liu; M. Ahmed; Y. Li; H. Zeng","School of Electronics and Information Engineering, Beihang University, Beijing, China; School of Electronics and Information Engineering, Beihang University, Beijing, China; Department of Computer Science and Technology, Qingdao University, Qingdao, China; Department of Electronic Engineering, Tsinghua University, Beijing, China; School of Electronics and Information Engineering, Beihang University, Beijing, China","IEEE Access","","2019","7","","48923","48934","The in-cessation trend of railways ramification calls for railway sensing on an urgent basis. Railway sense is required to keep suspecting potential danger in a large scope and provide a safe transportation environment. The fundamental infrastructure to realize railway sensing comprises of space and terrestrial integrated network (STIN) nodes, such as high-speed railway, trackside equipment, unmanned aerial vehicle, airship, and remote sensing satellite. This architecture needs to support diverse applications flexibly and ensure efficient infrastructure management. Inspired by the philosophy of software-defined network, which attempts to give more flexibility to networks, we propose a software-defined sensing and integrated architecture for such a network. We decouple the railway sensing application from the physical infrastructure. Besides, we designed centralized controllers to manage physical facilities and supply APIs of data processing, including acquisition, transmission, computation, and storage. Various applications can share common infrastructure with such properties, and each of these applications can customize its data acquisition, transmission, and computing by requesting APIs of controllers. This paper includes discussions of the design details of the proposed architecture and benefits. We also proposed some open problems and the potential solutions to solve them, which we hope to provide railway sensing research in a new direction.","2169-3536","","10.1109/ACCESS.2019.2908823","National Natural Science Foundation of China; National Key Research and Development Program of China; Program for New Century Excellent Talents in University; Fundamental Research Funds for the Central Universities; ","https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=8680613","SDN;railway sensing;space and terrestrial integrated network;architecture design","Rail transportation;Computer architecture;Monitoring;Temperature sensors;Remote sensing;Satellites","data acquisition;railways;software defined networking","software defined sensing;railways ramification calls;high-speed railway;remote sensing satellite;software-defined network;software-defined sensing;railway sensing application;railway sensing research;infrastructure management;space and terrestrial integrated network;STIN nodes","","","","35","","","","","IEEE","IEEE Journals"


*/
	
	private TipoMetadato identifierOf(int index) {
		switch (index) {
		case 0:
			return TipoMetadato.TITLE;
		case 1:
			return TipoMetadato.AUTOR;
		case 3:
		case 28:	
			return TipoMetadato.PUBLISHER;
		case 5:
			return TipoMetadato.YEAR;
		case 10:
			return TipoMetadato.ABSTRACT;
		case 11:
		case 12:
			return TipoMetadato.ISBN;
		case 13:	
			return TipoMetadato.DOI;
		case 16:
			return TipoMetadato.KEYWORD;
		case 29:
			return TipoMetadato.TYPE;
		default:
			return TipoMetadato.NOT_SUPORT;
		}

	}
	
	
	
}
