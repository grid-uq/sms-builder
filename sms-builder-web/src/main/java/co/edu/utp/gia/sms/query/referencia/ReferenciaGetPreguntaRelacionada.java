package co.edu.utp.gia.sms.query.referencia;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.entidades.Topico;
import jakarta.inject.Provider;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Consulta que permite obtener el número de preguntas relacionadas con una referencia
 */
public class ReferenciaGetPreguntaRelacionada {
    /**
     * Consulta que permite obtener las preguntas relacionadas con una referencia
     *
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return Stream< Pregunta > que representa el flujo de datos de la consulta
     */
    public static Stream<Pregunta> createQuery(String id){
        return createQuery(DB.root.revision()::getReferencias,id);
    }

    /**
     * Consulta que permite obtener las preguntas relacionadas con una referencia
     *
     * @param dataProvider Proveedor de la colección de datos en la que se realizará la búsqueda
     * @param id Id de la {@link co.edu.utp.gia.sms.entidades.Referencia}
     * @return Stream< Pregunta > que representa el flujo de datos de la consulta
     */
    public static Stream<Pregunta> createQuery(Provider<Collection<Referencia>> dataProvider, String id){
        return dataProvider.get().stream()
                .filter( referencia -> referencia.getId().equals(id))
                .map(Referencia::getTopicos)
                .flatMap(List::stream)
                .map(Topico::getPregunta)
                .distinct();
    }

}
