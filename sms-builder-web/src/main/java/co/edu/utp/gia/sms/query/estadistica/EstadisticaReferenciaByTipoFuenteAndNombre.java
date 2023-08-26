package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.*;
import co.edu.utp.gia.sms.query.Queries;
import co.edu.utp.gia.sms.query.fuente.FuenteGetByTipoFuente;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener el número de referencias por cada fuente de un determinado tipo de fuente en una revision <br />
 */
public class EstadisticaReferenciaByTipoFuenteAndNombre extends Queries {
    /**
     * Consulta que permite obtener el número de referencias por cada fuente de un determinado tipo de fuente en una revision
     *
     * @param tipoFuente    Tipo de fuente de las referencias a tener en cuenta
     *
     * @return TypedQuery<DatoDTO> que representa la consulta
     */
    public static Stream<DatoDTO> createQuery(TipoFuente tipoFuente) {
        return createQuery(DB.root.revision().getPasoSeleccionado()::getReferencias,tipoFuente);
    }

    /**
     * Consulta que permite obtener el número de referencias por cada fuente de un determinado tipo de fuente en una revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param tipoFuente    Tipo de fuente de las referencias a tener en cuenta
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<DatoDTO> createQuery(Provider<Collection<Referencia>> dataProvider, TipoFuente tipoFuente) {
        var fuentes = FuenteGetByTipoFuente.createQuery(tipoFuente)
                .map(Fuente::getNombre)
                .toList();
        Predicate<Metadato> filtro = metadato -> metadato.getIdentifier().equals(TipoMetadato.TIPO_FUENTE)
                && fuentes.contains(metadato.getValue());
        return dataProvider.get().stream()
                .flatMap(referencia -> referencia.getMetadatos().stream().filter(filtro))
                .collect(Collectors.groupingBy(Metadato::getValue,Collectors.counting()))
                .entrySet().stream()
                .map( entry->new DatoDTO(entry.getKey(), entry.getValue()));
    }
}