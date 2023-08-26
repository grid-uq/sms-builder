package co.edu.utp.gia.sms.query.estadistica;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.dtos.DatoDTO;
import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.query.Queries;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener el número de referencias por tipo de fuente en una revision <br />
 */
public class EstadisticaReferenciaByTipoFuente extends Queries {

    /**
     * Consulta que permite obtener el número de referencias por tipo de fuente en una revision
     *
     * @return TypedQuery<DatoDTO> que representa la consulta
     */
    public static Stream<DatoDTO> createQuery() {
        return createQuery(DB.root.revision().getPasoSeleccionado()::getReferencias);
    }

    /**
     * Consulta que permite obtener el número de referencias por tipo de fuente en una revision
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @return Stream<DatoDTO> que representa el resultado de la consulta
     */
    public static Stream<DatoDTO> createQuery(Provider<Collection<Referencia>> dataProvider) {
        Predicate<Metadato> filtro = metadato -> metadato.getIdentifier().equals(TipoMetadato.TIPO_FUENTE);
        return dataProvider.get().stream()
                .flatMap(referencia -> referencia.getMetadatos().stream().filter(filtro))
                .collect(Collectors.groupingBy(Metadato::getValue,Collectors.counting()))
                .entrySet().stream()
                .map( entry->new DatoDTO(entry.getKey(), entry.getValue()));
    }
}
