package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Entidad;
import co.edu.utp.gia.sms.exceptions.ExceptionMessage;
import co.edu.utp.gia.sms.exceptions.LogicException;
import co.edu.utp.gia.sms.exceptions.TecnicalException;
import co.edu.utp.gia.sms.util.ApplicationGeneralProducer;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.ws.rs.core.Response;
import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.serializer.concurrency.XThreads;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

/**
 * Clase abstracta que define los elementos de logica generales asociados al
 * CRUD de una entidad
 */
public abstract class AbstractGenericService<E extends Entidad<TipoId>, TipoId> implements Serializable {

    protected final Provider<Collection<E>> dataProvider;
    /**
     * Instancia que permite obtener los mensajes de las excepciones generadas.
     */
//    @Inject
    @Getter
    protected ExceptionMessage exceptionMessage;

    protected static <T> Collection<T> defaultDataProvider() {
        return Collections.emptyList();
    }

    public AbstractGenericService() {
        this(AbstractGenericService::defaultDataProvider);
//        dataProvider = AbstractGenericService::defaultDataProvider;
    }

    public AbstractGenericService(Provider<Collection<E>> dataProvider) {
        this.dataProvider = dataProvider;
//        exceptionMessage = ApplicationGeneralProducer.getInstance().getExceptionMessage();
        exceptionMessage = ConfigFactory.create(ExceptionMessage.class);
    }

    public E save(E entidad) {
       return save(dataProvider,entidad);
    }

    /**
     * Verificaciones a realizar antes de que la entidad sea almacenada
     * @param entidad Entidad a almacenar
     */
    protected void validateBeforeSave(E entidad){
        var stored = find(dataProvider,entidad.getId());
        if (stored.isPresent()) {
            throw new LogicException(exceptionMessage.getRegistroExistente(), Response.Status.CONFLICT);
        }
    }

    /**
     * Método que permite registrar una entidad en el sistema
     * @param dataProvider Colección de elementos en la que se almacenará el nuevo elemento
     * @param entidad Elemento a ser almacenado
     * @return El elemento almacenado
     */
    protected E save(Provider<Collection<E>> dataProvider,E entidad) {
        // TODO ejemplo de sincronización para el almacenamiento de datos
        XThreads.executeSynchronized(() -> {
            try {
                validateBeforeSave(entidad);
                dataProvider.get().add(entidad);
                DB.storageManager.store(dataProvider.get());

            } catch (Throwable t) {
                throw new TecnicalException(t);
            }
        });
        return entidad;
    }

    public void update(E entidad) {
        update(dataProvider,entidad);
    }

    protected void update(Provider<Collection<E>> dataProvider,E entidad) {
        try {
            requireDataProvider(dataProvider);
            validateBeforeUpdate(entidad);
            var entidadStored = findOrThrow(dataProvider,entidad.getId());
            if( entidadStored != entidad) {
                BeanUtils.copyProperties(entidadStored, entidad);
            }
            DB.storageManager.store(entidadStored);
        } catch (Throwable t) {
            throw new TecnicalException(t);
        }
    }

    /**
     * Verificaciones a realizar antes de que la entidad sea actualizada
     * @param entidad Entidad a actualizar
     */
    protected void validateBeforeUpdate(E entidad){

    }



    public void delete(TipoId id) {
        delete(dataProvider,findOrThrow(id));
    }

    public void delete(E entidad) {
        delete(dataProvider,entidad);
    }

    protected void delete(Provider<Collection<E>> dataProvider,E entidad) {
        try {
            requireDataProvider(dataProvider);
            validateBeforeDelete(entidad);
            dataProvider.get().remove(entidad);
            DB.storageManager.store(dataProvider.get());
        } catch (Throwable t) {
            throw new TecnicalException(t);
        }
    }

    /**
     * Verificaciones a realizar antes de que la entidad sea borrada
     * @param entidad Entidad a borrar
     */
    protected void validateBeforeDelete(E entidad){

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
                () -> new LogicException(exceptionMessage.getRegistroNoEncontrado(), Response.Status.NOT_FOUND));
    }

    public Collection<E> get() {
        return get(dataProvider);
    }

    public int count(){
        return dataProvider.get().size();
    }

    protected Collection<E> get(Provider<Collection<E>> dataProvider) {
        requireDataProvider(dataProvider);
        return dataProvider.get();
    }

    private void requireDataProvider(Provider<Collection<E>> dataProvider){
        Objects.requireNonNull(dataProvider,"El dataprovider es null");
        if( dataProvider.equals( AbstractGenericService.defaultDataProvider() )  ) {
            throw new UnsupportedOperationException("Requiere especificar la fuente de datos");
        }
    }
}
