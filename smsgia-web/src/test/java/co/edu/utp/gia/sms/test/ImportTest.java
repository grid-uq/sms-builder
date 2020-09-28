package co.edu.utp.gia.sms.test;

import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParse;
import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParseFactory;
import co.edu.utp.gia.sms.importutil.Fuente;

/// Pendiente por completar la anotación
class ImportTest {

	@Test
	void testParseACM() {
		testParse(Fuente.ACM, "/acm_3.enw", 3);
	}

	@Test
	void testParseIeee() {
		testParse(Fuente.IEEE, "/ieee_6.csv", 6);
	}

	@Test
	void testParseSCIENCEDIRECT() {
		testParse(Fuente.SCIENCEDIRECT, "/sd_47.ris", 47);
	}
	
	@Test
	void testParseScopus() {
		testParse(Fuente.SCOPUS, "/scopus_30.ris", 30);
	}



	@Test
	void testParseSpringer() {

		testParse(Fuente.SPRINGER, "/springer_179.csv", 179);
	}

	@Test
	void testParseWOS() {

		testParse(Fuente.WOS, "/wos_138.ciw", 138);
	}
	

	@Test
	void testParseACM_MENDELEY() {
		testParse(Fuente.ACM, "/acm_3_mendeleyris.ris", 3);
	}
	
	@Test
	void testParseIEEE_MENDELEY() {
		testParse(Fuente.IEEE, "/ieee_6_mendeleyris.ris", 6);
	}

	@Test
	void testParseSCIENCEDIRECT_MENDELEY() {
		testParse(Fuente.SCIENCEDIRECT, "/sciecedirect_47_mendeleyris.ris", 47);
	}	
	
	@Test
	void testParseINCLUSION_DIRECTA() {
		testParse(Fuente.SCIENCEDIRECT, "/inclusionDirecta_9_rismendeley.ris", 9);
	}	
	
//	@Test
//	void testFuente() {
//		Fuente [] fuente = Fuente.values();
//		for (Fuente f : fuente) {
//			System.out.println(f);
//		}
//	}

	void testParse(Fuente fuente, String nombreArchivo, int cantidadReferencias) {

		FileMultipleRegisterParse frmp = FileMultipleRegisterParseFactory.getInstance(fuente);

		InputStream archivo = ImportTest.class.getResourceAsStream(nombreArchivo);
		List<Referencia> datos = frmp.parse(archivo);
		
		
		Assert.assertEquals(cantidadReferencias, datos.size());

		datos.stream().forEach((referencia) -> {
			Assert.assertTrue(referencia.getYear().length() < 5);
			Assert.assertTrue(referencia.getResumen() == null || referencia.getResumen().length()>5);
		});
		
		

	}

}
