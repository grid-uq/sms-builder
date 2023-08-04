package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Entidad;
import co.edu.utp.gia.sms.exceptions.ExceptionMessage;
import co.edu.utp.gia.sms.exceptions.LogicException;
import co.edu.utp.gia.sms.exceptions.TecnicalException;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import lombok.Getter;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * Clase abstracta que define los elementos de logica generales asociados al
 * CRUD de una entidad
 */
public abstract class AbstractGenericService<E extends Entidad<TipoId>, TipoId> implements Serializable {

    protected final Provider<Collection<E>> dataProvider;
    /**
     * Instancia que perite obtener los mensajes de las excepciones generadas.
     */
    @Inject
    @Getter
    protected ExceptionMessage exceptionMessage;

    public AbstractGenericService() {
        dataProvider = Collections::emptyList;
    }

    public AbstractGenericService(Provider<Collection<E>> dataProvider) {
        this.dataProvider = dataProvider;
    }

    public E save(E entidad) {
        try {
            var stored = find(entidad.getId());
            if (stored.isPresent()) {
                throw new LogicException(exceptionMessage.getRegistroExistente());
            }
            dataProvider.get().add(entidad);
            return entidad;
        } catch (Throwable t) {
            throw new TecnicalException(t);
        }
    }

    public void update(E entidad) {
        try {
            findOrThrow(entidad.getId()).updateFrom(entidad);
        } catch (Throwable t) {
            throw new TecnicalException(t);
        }
    }

    public void delete(E entidad) {
        try {
            delete(entidad.getId());
        } catch (Throwable t) {
            throw new TecnicalException(t);
        }
    }

    public void delete(TipoId id) {
        try {
            dataProvider.get().remove(findOrThrow(id));
        } catch (Throwable t) {
            throw new TecnicalException(t);
        }
    }

    public Optional<E> find(TipoId id) {
        try {
            return dataProvider.get().stream().filter((e) -> e.getId().equals(id)).findFirst();
        } catch (Throwable t) {
            throw new TecnicalException(t);
        }
    }

    public E findOrThrow(TipoId id) {
        return find(id).orElseThrow(
                () -> new LogicException(exceptionMessage.getRegistroNoEncontrado()));
    }

    public Collection<E> get() {
        return dataProvider.get();
    }

}
