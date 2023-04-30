package co.edu.utp.gia.sms.importutil.taylorfrancis;

import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.importutil.Fuente;
import co.edu.utp.gia.sms.importutil.ReferenceParser;

import java.io.StringReader;
import java.util.Scanner;

public class TaylorFrancisReferenceParcer extends ReferenceParser {

    public TaylorFrancisReferenceParcer() {
        super(Fuente.TAYLOR_AND_FRANCIS);
    }

    private static final String TITULO = "TI";
    private static final String KEYWORD = "KW";
    private static final String YEAR = "PY";
    private static final String ABSTRACT = "AB";
    private static final String AUTOR = "AU";
    private static final String DOI = "DO";
    private static final String ISBN = "SN";
    private static final String NOMBRE_PUBLICACION = "PP"; // PB DB
    private static final String TIPO_PUBLICACION = "TY";

    @Override
    protected void procesarTexto(Referencia referencia, String texto) {
        try (Scanner lector = new Scanner(new StringReader(texto))) {
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                procesarLinea(referencia, linea);
            }
        }
    }

    /**
     * Procesa una linea del formato .RIS exportado desde la BD SCOPUS
     *
     * @param reference Referencia que se esta procesando
     * @param nextLine  Linea a ser procesada
     */
    private void procesarLinea(Referencia reference, String nextLine) {
        if (nextLine != null && !nextLine.isEmpty()) {
            String key = nextLine.substring(0, 2).toUpperCase();
            String value = nextLine.substring(5).trim().toUpperCase();
            reference.addElement(identifierOf(key), value);
        }
    }

    private TipoMetadato identifierOf(String key) {
        switch (key) {
            case TITULO:
                return TipoMetadato.TITLE;
            case AUTOR:
                return TipoMetadato.AUTOR;
            case NOMBRE_PUBLICACION:
                return TipoMetadato.PUBLISHER;
            case YEAR:
                return TipoMetadato.YEAR;
            case ABSTRACT:
                return TipoMetadato.ABSTRACT;
            case ISBN:
                return TipoMetadato.ISBN;
            case DOI:
                return TipoMetadato.DOI;
            case KEYWORD:
                return TipoMetadato.KEYWORD;
            case TIPO_PUBLICACION:
                return TipoMetadato.TYPE;
            default:
                return TipoMetadato.NOT_SUPORT;
        }
    }
}
