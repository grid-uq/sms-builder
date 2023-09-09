package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.query.metadato.MetadatoGetByTipoFuente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion del {@link Metadato}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@ApplicationScoped
public class MetadatoServices extends AbstractGenericService<Metadato, String> {
    @Inject
    private FuenteService fuenteService;
    public MetadatoServices() {
        super();
    }

    @Override
    public Metadato save(Metadato entidad) {
        save(entidad.getReferencia()::getMetadatos,entidad);
        entidad.getReferencia().refreshDataFromElement(entidad);
        DB.storageManager.store(entidad.getReferencia());
        return entidad;
    }

    @Override
    public void update(Metadato entidad) {
        super.update(entidad.getReferencia()::getMetadatos,entidad);
        entidad.getReferencia().refreshDataFromElement(entidad);
        if( entidad.getIdentifier() == TipoMetadato.FUENTE ){
           fuenteService.findByNombre(entidad.getValue().toString()).ifPresent(entidad.getReferencia()::setFuente);
           var metadatoTipoFuente = MetadatoGetByTipoFuente.createQuery(entidad.getReferencia()::getMetadatos,
                   TipoMetadato.TIPO_FUENTE).findFirst();
           if( metadatoTipoFuente.isPresent() ){
               metadatoTipoFuente.get().setValue( entidad.getReferencia().getFuente().getTipo().toString() );
               super.update(entidad.getReferencia()::getMetadatos,metadatoTipoFuente.get());
           }
        }
        DB.storageManager.store(entidad.getReferencia());
    }

    @Override
    public void delete(Metadato entidad) {
        super.delete(entidad.getReferencia()::getMetadatos,entidad);
    }


//    public void delete(String idReferencia,String idMetadato) {
//        var referencia = referenciaService.findOrThrow(idReferencia);
//        var metadato = findOrThrow(idMetadato);
//        delete(referencia::getMetadatos,metadato);
//    }

//    public Optional<Metadato> find(String idReferencia,String idMetadato) {
//        var referencia = referenciaService.findOrThrow(idReferencia);
//        return find(referencia,idMetadato);
//    }
//    public Optional<Metadato> find(Referencia referencia, String idMetado) {
//        return super.find(referencia::getMetadatos,idMetado);
//    }

//    public Metadato findOrThrow(String idReferencia,String idMetadato) {
//        var referencia = referenciaService.findOrThrow(idReferencia);
//        return findOrThrow(referencia,idMetadato);
//    }

//    public Metadato findOrThrow(Referencia referencia, String idMetado) {
//        return super.findOrThrow(referencia::getMetadatos,idMetado);
//    }
}
