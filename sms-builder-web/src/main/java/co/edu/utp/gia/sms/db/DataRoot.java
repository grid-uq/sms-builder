package co.edu.utp.gia.sms.db;

import co.edu.utp.gia.sms.entidades.AtributoCalidad;
import co.edu.utp.gia.sms.entidades.CadenaBusqueda;
import co.edu.utp.gia.sms.entidades.Fuente;
import co.edu.utp.gia.sms.entidades.Revision;

import java.util.Collections;
import java.util.List;

public record DataRoot(Revision revision) {
	public DataRoot(){
        this(new Revision());
    }

    public <T> List<T> getRecords(Class<T> tClass){

        if (tClass.equals(Revision.class)) {
            return (List<T>) Collections.singletonList(revision());
        }
        if (tClass.equals(AtributoCalidad.class)) {
            return (List<T>) Collections.unmodifiableList(revision().getAtributosCalidad());
        }
        if (tClass.equals(CadenaBusqueda.class)) {
            return (List<T>) Collections.unmodifiableList(revision().getCadenasBusqueda());
        }
        if (tClass.equals(Fuente.class)) {
            return (List<T>) Collections.unmodifiableList(revision().getFuentes());
        }
        return Collections.emptyList();
    }
}
