package co.edu.utp.gia.sms.importutil;

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
