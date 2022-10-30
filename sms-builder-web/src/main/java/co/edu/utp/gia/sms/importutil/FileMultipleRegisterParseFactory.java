package co.edu.utp.gia.sms.importutil;

import co.edu.utp.gia.sms.importutil.bibtex.BibtexFileMultipleRegisterParse;
import co.edu.utp.gia.sms.importutil.ris.RisFileMultipleRegisterParse;
/**
 * Clase utilitaria encargada de construir las instancias de los procesadores de archivos de referencias.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */

public class FileMultipleRegisterParseFactory {
	private FileMultipleRegisterParseFactory() {
	}

	public static ReferenceParse getInstance(TipoArchivo tipoArchivo, String fuente, String tipoFuente) {
		switch (tipoArchivo) {
			case RIS:
				return new RisFileMultipleRegisterParse(fuente,tipoFuente);
			case BIBTEX:
			default:
				return new BibtexFileMultipleRegisterParse(fuente,tipoFuente);
		}
	}

}