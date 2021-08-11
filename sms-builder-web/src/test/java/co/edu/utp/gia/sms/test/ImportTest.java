package co.edu.utp.gia.sms.test;

import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoFuente;
import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParse;
import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParseFactory;
import co.edu.utp.gia.sms.importutil.TipoArchivo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

/// Pendiente por completar la anotación
class ImportTest {

	@Test
	void testParseSCIENCEDIRECT_MENDELEY() {
		testParse("test",TipoFuente.BASE_DATOS.toString(),TipoArchivo.RIS, "/sciecedirect_47_mendeleyris.ris", 47);
	}	


	void testParse(String fuente,String tipoFuente, TipoArchivo tipoArchivo, String nombreArchivo, int cantidadReferencias) {

		FileMultipleRegisterParse frmp = FileMultipleRegisterParseFactory.getInstance(tipoArchivo,fuente,tipoFuente);

		InputStream archivo = ImportTest.class.getResourceAsStream(nombreArchivo);
		List<Referencia> datos = frmp.parse(archivo);
		
		
		Assert.assertEquals(cantidadReferencias, datos.size());

		datos.stream().forEach((referencia) -> {
			Assert.assertTrue(referencia.getYear().length() < 5);
			Assert.assertTrue(referencia.getResumen() == null || referencia.getResumen().length()>5);
		});
		
		

	}

}
