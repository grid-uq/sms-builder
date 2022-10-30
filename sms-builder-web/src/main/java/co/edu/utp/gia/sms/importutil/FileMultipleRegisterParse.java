package co.edu.utp.gia.sms.importutil;
/**
 * Clase utilitaria que define de forma general un procesar de archivos de referencias.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
public abstract class FileMultipleRegisterParse<T extends ReferenceParser> implements ReferenceParse {

    private final T referenceParser;

    /**
     * Metodo que permite inicializar los elementos de la clase
     * FileMultipleRegisterParse
     *
     * @param referenceParser Interprete propio de la fuente
     */
    public FileMultipleRegisterParse(T referenceParser) {
        this.referenceParser = referenceParser;
    }

    protected T getReferenceParser() {
        return referenceParser;
    }

    //public abstract List<Referencia> parse(InputStream input);

}
