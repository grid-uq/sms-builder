package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.Referencia;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;
import java.util.Optional;
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
    private ReferenciaService referenciaService;

    public MetadatoServices() {
        super();
    }

    @Override
    public Metadato save(Metadato entidad) {
        return save(entidad.getReferencia()::getMetadatos,entidad);
    }

    @Override
    public void update(Metadato entidad) {
        super.update(entidad.getReferencia()::getMetadatos,entidad);
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

    @Override
    public Collection<Metadato> get() {
        return super.get();
    }


}
