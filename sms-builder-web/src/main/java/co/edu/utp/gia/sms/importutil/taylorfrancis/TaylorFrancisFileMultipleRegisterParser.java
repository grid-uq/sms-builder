package co.edu.utp.gia.sms.importutil.taylorfrancis;

import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParse;

public class TaylorFrancisFileMultipleRegisterParser extends FileMultipleRegisterParse {
    /**
     * Metodo que permite inicializar los elementos de la clase
     * FileMultipleRegisterParse
     *
     * @param referenceParser Interprete propio de la fuente
     */
    public TaylorFrancisFileMultipleRegisterParser() {
        super(new TaylorFrancisReferenceParcer());
    }
}
