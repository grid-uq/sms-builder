package co.edu.utp.gia.sms.test;

import co.edu.utp.gia.sms.entidades.*;
import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParseFactory;
import co.edu.utp.gia.sms.importutil.ReferenceParse;
import co.edu.utp.gia.sms.importutil.TipoArchivo;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/// Pendiente por completar la anotación
class ImportTest {

	@Test
	void testParseSCIENCEDIRECT_MENDELEY() {
		testParse(new Fuente("ACM",TipoFuente.BASE_DATOS), TipoArchivo.RIS, "/sciecedirect_47_mendeleyris.ris", 47);
		testParse(new Fuente("ACM",TipoFuente.BASE_DATOS),TipoArchivo.BIBTEX, "/main64.bib", 64);
		testParse(new Fuente("ACM",TipoFuente.BASE_DATOS),TipoArchivo.BIBTEX, "/main2.bib", 2);
	}


	void testParse(Fuente fuente,  TipoArchivo tipoArchivo, String nombreArchivo, int cantidadReferencias) {

		ReferenceParse frmp = FileMultipleRegisterParseFactory.getInstance(tipoArchivo,fuente);

		InputStream archivo = ImportTest.class.getResourceAsStream(nombreArchivo);
		List<Referencia> datos = frmp.parse(archivo);
		
		
		assertEquals(cantidadReferencias, datos.size());

		datos.stream().forEach((referencia) -> {
			assertTrue(referencia.getYear().length() < 5);
			assertTrue(referencia.getResumen() == null || referencia.getResumen().length()>5);
			assertTrue(referencia.getNombre() != null && !referencia.getNombre().isEmpty());
			assertEquals(fuente,referencia.getFuente());
			System.out.println(referencia.getTipo());
			System.out.println(referencia.getYear());
		});

	}

}
