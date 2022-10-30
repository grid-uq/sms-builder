package co.edu.utp.gia.sms.exportutil;

import co.edu.utp.gia.sms.dtos.ReferenciaDTO;
import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.TipoMetadato;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
/**
 * Clase utilitaria que permite exportar referencias a formato RIS.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
public class ReferenceToRIS {

    private static final String RIS_TITLE = "T1";
    private static final String RIS_TITLE2 = "TI";
    private static final String RIS_ABSTRACT = "AB";
    private static final String RIS_KEWYWORDS = "KW";
    private static final String RIS_ISBN = "SN";
    private static final String RIS_PUBLISHER = "JF";
    private static final String RIS_DOI = "DO";
    private static final String RIS_AUTHOR = "A1";
    private static final String RIS_YEAR = "Y1";
    private static final String RIS_TYPE = "TY";
    private static final String RIS_NOTE = "N1";
    private static final String RIS_NOT_SUPPORT = "U1";
    private static final String RIS_END_REFERNCE = "ER";
    private PrintStream ps;

    public ReferenceToRIS() {

    }

    public ReferenceToRIS(OutputStream flujoSalida) {
		ps = new PrintStream(flujoSalida);
    }

    private static String identificarClave(TipoMetadato identifier) {

        switch (identifier) {
            case TITLE:
                return RIS_TITLE;
            case ABSTRACT:
                return RIS_ABSTRACT;
            case KEYWORD:
                return RIS_KEWYWORDS;
            case ISBN:
                return RIS_ISBN;
            case PUBLISHER:
                return RIS_PUBLISHER;
            case DOI:
                return RIS_DOI;
            case AUTOR:
                return RIS_AUTHOR;
            case YEAR:
                return RIS_YEAR;
            case TYPE:
                return RIS_TYPE;
            case NOTA:
                return RIS_NOTE;

            case NOT_SUPORT:
            default:
                return RIS_NOT_SUPPORT;
        }
    }

    public void procesarReferencias(List<ReferenciaDTO> referencias) {

        for (ReferenciaDTO referencia : referencias) {
            procesarReferencia(referencia);
        }
    }

    public void procesarReferencia(ReferenciaDTO referencia) {

        StringBuilder sb = new StringBuilder();

        cargarNotas(sb, referencia);
        cargarTitulo(referencia);
        cargarAbstract(referencia);

        for (Metadato metadato : referencia.getMetadatos()) {

            String clave = identificarClave(metadato.getIdentifier());
            String valor = metadato.getValue();


            if (clave.equals(RIS_NOTE)) {
                sb.append(valor);
            } else if (!clave.equals(RIS_NOT_SUPPORT)) {
                if (clave.equals(RIS_TITLE)) {
                    escribirFlujo(RIS_TITLE2, valor);
                }
                escribirFlujo(clave, valor);
            }
        }
        escribirFlujo(RIS_NOTE, sb.toString());
        escribirFlujo(RIS_END_REFERNCE, "");

    }

    private void cargarAbstract(ReferenciaDTO referencia) {
        escribirFlujo(RIS_ABSTRACT, referencia.getResumen());
    }

    private void cargarTitulo(ReferenciaDTO referencia) {
        escribirFlujo(RIS_TITLE, referencia.getNombre());
    }

    private void cargarNotas(StringBuilder sb, ReferenciaDTO referencia) {
        sb.append(referencia.getNota());
    }

    private void escribirFlujo(String clave, String valor) {
        ps.println(clave + "  - " + valor);
    }
}
