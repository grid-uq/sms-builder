package co.edu.utp.gia.sms.importutil;

import co.edu.utp.gia.sms.entidades.Referencia;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class FileMultipleRegisterParse {

    private final ReferenceParser referenceParser;

    /**
     * Metodo que permite inicializar los elementos de la clase
     * FileMultipleRegisterParse
     *
     * @param referenceParser Interprete propio de la fuente
     */
    public FileMultipleRegisterParse(ReferenceParser referenceParser) {
        this.referenceParser = referenceParser;
    }

    protected ReferenceParser getReferenceParser() {
        return referenceParser;
    }

    public List<Referencia> parse(InputStream input) {
        List<Referencia> references = new ArrayList<>();
        try (Scanner lector = new Scanner(input)) {

            String linea = "";
            while (lector.hasNextLine() && "".equals(linea.trim())) {
                linea = lector.nextLine();
            }

            while (lector.hasNextLine()) {
                linea = lector.nextLine();
                Referencia reference = referenceParser.parse(linea);
                if (reference != null) {
                    references.add(reference);
                }
            }
        }

        return references;
    }

}
