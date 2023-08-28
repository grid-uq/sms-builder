package co.edu.utp.gia.sms.entidades;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @version 1.0
 * @since 13/06/2019
 */
@RequiredArgsConstructor
public class Termino implements Entidad<String> {
    /**
     * Variable que representa el atributo id de la clase
     */
    @Getter @Setter
    private String id = UUID.randomUUID().toString();
    /**
     * Variable que representa el atributo texto de la clase
     */
    @Getter @Setter
    @NonNull
    private String descripcion;

    /**
     * Lista de sinonimos del termino
     */
    @Getter @Setter
    private List<String> sinonimos;

    public Termino(){
        sinonimos = new ArrayList<>();
    }

    public void adicionarSinonimo(String sinonimo) {
        getSinonimos().add(sinonimo);
    }

    public void removerSinonimo(String sinonimo) {
        getSinonimos().remove(sinonimo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Termino termino = (Termino) o;

        return Objects.equals(id, termino.id);
    }

    @Override
    public int hashCode() {
        return 1361059416;
    }
}
