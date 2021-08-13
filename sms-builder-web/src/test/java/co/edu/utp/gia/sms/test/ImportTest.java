package co.edu.utp.gia.sms.test;

import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoFuente;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParseFactory;
import co.edu.utp.gia.sms.importutil.ReferenceParse;
import co.edu.utp.gia.sms.importutil.TipoArchivo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

/// Pendiente por completar la anotación
class ImportTest {

	@Test
	void testParseSCIENCEDIRECT_MENDELEY() {
		//testParse("test",TipoFuente.BASE_DATOS.toString(),TipoArchivo.RIS, "/sciecedirect_47_mendeleyris.ris", 47);
		testParse("test",TipoFuente.BASE_DATOS.toString(),TipoArchivo.BIBTEX, "/main64.bib", 64);
	}	


	void testParse(String fuente,String tipoFuente, TipoArchivo tipoArchivo, String nombreArchivo, int cantidadReferencias) {

		ReferenceParse frmp = FileMultipleRegisterParseFactory.getInstance(tipoArchivo,fuente,tipoFuente);

		InputStream archivo = ImportTest.class.getResourceAsStream(nombreArchivo);
		List<Referencia> datos = frmp.parse(archivo);
		
		
		Assert.assertEquals(cantidadReferencias, datos.size());

		datos.stream().forEach((referencia) -> {
			Assert.assertTrue(referencia.getResumen() == null || referencia.getYear().length() < 5);
			Assert.assertTrue(referencia.getResumen() == null || referencia.getResumen().length()>5);
			System.out.println( referencia.getMetadatos().stream().filter(m->TipoMetadato.AUTOR.equals(m.getIdentifier())).count() );
			referencia.getMetadatos().stream()
					.filter(m->TipoMetadato.AUTOR.equals(m.getIdentifier()))
					.map(Metadato::getValue)
					.forEach(System.out::println);
		});

	}

}
