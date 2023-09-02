package co.edu.utp.gia.sms.db;

import co.edu.utp.gia.sms.entidades.*;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


public record DataRoot(Revision revision) {
	public DataRoot(){
        this(new Revision("",""));
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

    public <T> Provider<Collection<T>> getProvider(Class<T> tClass){
        if (tClass.equals(Revision.class)) {
            return  ()-> (Collection<T>) Collections.singletonList(revision());
        }
        if (tClass.equals(AtributoCalidad.class)) {
            return  ()-> (Collection<T>) revision().getAtributosCalidad();
        }
        if (tClass.equals(CadenaBusqueda.class)) {
            return  ()-> (Collection<T>) revision().getCadenasBusqueda();
        }
        if (tClass.equals(Fuente.class)) {
            return  ()-> (Collection<T>) revision().getFuentes();
        }
        if (tClass.equals(Objetivo.class)) {
            return  ()-> (Collection<T>) revision().getObjetivos();
        }
        if (tClass.equals(Pregunta.class)) {
            return  ()-> (Collection<T>) revision().getPreguntas();
        }
        if (tClass.equals(PasoProceso.class)) {
            return  ()-> (Collection<T>) revision().getPasosProceso();
        }
        if (tClass.equals(Paso.class)) {
            return  ()-> (Collection<T>) revision().getPasos();
        }
        if (tClass.equals(Recurso.class)) {
            return  ()-> (Collection<T>) revision().getRecursos();
        }
        if (tClass.equals(Referencia.class)) {
            return  ()-> (Collection<T>) revision().getReferencias();
        }
        if (tClass.equals(Rol.class)) {
            return  ()-> (Collection<T>) revision().getRoles();
        }
        if (tClass.equals(Termino.class)) {
            return  ()-> (Collection<T>) revision().getTerminos();
        }
        if (tClass.equals(Topico.class)) {
            return  ()-> (Collection<T>) revision().getTopicos();
        }
        if (tClass.equals(Usuario.class)) {
            return  ()-> (Collection<T>) revision().getUsuarios();
        }
        return Collections::emptyList;
    }
}
