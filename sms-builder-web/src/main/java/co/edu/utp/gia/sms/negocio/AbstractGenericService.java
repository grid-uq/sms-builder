package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Entidad;
import co.edu.utp.gia.sms.exceptions.ExceptionMessage;
import co.edu.utp.gia.sms.exceptions.LogicException;
import co.edu.utp.gia.sms.exceptions.TecnicalException;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import lombok.Getter;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Clase abstracta que define los elementos de logica generales asociados al
 * CRUD de una entidad
 */
public abstract class AbstractGenericService<E extends Entidad<TipoId>, TipoId> implements Serializable {

    protected final static Provider<? extends Collection> DEFAULT_DATA_PROVIDER = Collections::emptyList;
    protected final Provider<Collection<E>> dataProvider;
    /**
     * Instancia que perite obtener los mensajes de las excepciones generadas.
     */
    @Inject
    @Getter
    protected ExceptionMessage exceptionMessage;

    protected static final <T> Collection<T> defaultDataProvider() {
        final Collection<T> emptyList = Collections.EMPTY_LIST;
        return emptyList;
    }

    public AbstractGenericService() {
        dataProvider = AbstractGenericService::defaultDataProvider;
    }

    public AbstractGenericService(Provider<Collection<E>> dataProvider) {
        this.dataProvider = dataProvider;
    }

    public E save(E entidad) {
       return save(dataProvider,entidad);
    }

    protected E save(Provider<Collection<E>> dataProvider,E entidad) {
        try {
            var stored = find(dataProvider,entidad.getId());
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
        update(dataProvider,entidad);
    }

    protected void update(Provider<Collection<E>> dataProvider,E entidad) {
        try {
            requireDataProvider(dataProvider);
            BeanUtils.copyProperties(findOrThrow(dataProvider,entidad.getId()),entidad);
        } catch (Throwable t) {
            throw new TecnicalException(t);
        }
    }

    public void delete(E entidad) {
        delete(dataProvider,entidad.getId());
    }

    public void delete(TipoId id) {
        delete(dataProvider,id);
    }

    protected void delete(Provider<Collection<E>> dataProvider,TipoId id) {
        try {
            requireDataProvider(dataProvider);
            dataProvider.get().remove(findOrThrow(id));
        } catch (Throwable t) {
            throw new TecnicalException(t);
        }
    }

    public Optional<E> find(TipoId id) {
        return find(dataProvider,id);
    }

    protected Optional<E> find(Provider<Collection<E>> dataProvider,TipoId id) {
        try {
            requireDataProvider(dataProvider);
            return dataProvider.get().stream().filter((e) -> e.getId().equals(id)).findFirst();
        } catch (Throwable t) {
            throw new TecnicalException(t);
        }
    }


    public E findOrThrow(TipoId id) {
        return findOrThrow(dataProvider,id);
    }

    protected E findOrThrow(Provider<Collection<E>> dataProvider,TipoId id) {
        return find(dataProvider,id).orElseThrow(
                () -> new LogicException(exceptionMessage.getRegistroNoEncontrado()));
    }

    public Collection<E> get() {
        return get(dataProvider);
    }

    protected Collection<E> get(Provider<Collection<E>> dataProvider) {
        requireDataProvider(dataProvider);
        return dataProvider.get();
    }

    private void requireDataProvider(Provider<Collection<E>> dataProvider){
        if( dataProvider.get().equals( AbstractGenericService.defaultDataProvider() )  ) {
            throw new UnsupportedOperationException("Requiere especificar la fuente de datos");
        }
    }
}
