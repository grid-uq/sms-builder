package co.edu.utp.gia.sms.entidades;

import java.util.UUID;
/**
 * Clase que representa la entidad Proceso, la cual permite modelar en el
 * sistema un Paso del SMS
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
public record Paso (String id,String nombre,Recurso recurso,boolean forFilter) implements Entidad<String>{
    public Paso(String nombre, Recurso recurso, boolean forFilter) {
        this(UUID.randomUUID().toString(), nombre, recurso, forFilter);
    }

    @Override
    public String getId() {
        return id;
    }
}
