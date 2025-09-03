package co.edu.utp.gia.sms.beans;

import co.edu.utp.gia.sms.entidades.Pregunta;
import co.edu.utp.gia.sms.entidades.Topico;
import co.edu.utp.gia.sms.negocio.AbstractGenericService;
import co.edu.utp.gia.sms.negocio.TopicoService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Clase controladora de interfaz web que se encarga de la gestión de tópicos.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 13/06/2019
 */
@Named
@ViewScoped
public class RegistroTopicoBean extends GenericBeanNew<Topico,String> {
    @Inject
    private TopicoService topicoService;
    @Getter @Setter
    private List<Pregunta> listaPreguntas;

    @Getter
    private Collection<String> tags;


    @Override
    public void inicializar() {
        tags = topicoService.getTags().stream().distinct().toList();

        setRecords(topicoService.get().stream()
                .sorted(Comparator.comparing(topico -> topico.getPregunta().getCodigo())).toList());
        listaPreguntas = new ArrayList<>();
    }

    @Override
    protected Topico newRecord() {
        return new Topico();
    }

    @Override
    protected AbstractGenericService<Topico, String> getServices() {
        return topicoService;
    }

    @Override
    public void registrar() {
        super.registrar();
        setRecords(topicoService.get().stream()
                .sorted(Comparator.comparing(topico -> topico.getPregunta().getCodigo())).toList());
    }

    @Override
    public void eliminar(Topico record) {
        super.eliminar(record);
        setRecords(topicoService.get().stream()
                .sorted(Comparator.comparing(topico -> topico.getPregunta().getCodigo())).toList());
    }

    /**
     * Método que permite validar que no se ingresen dos tópico con la misma descripción bajo una misma pregunta.
     * @param facesContext Contexto de faces.
     * @param component Componente que se está validando
     * @param object Valor que se pretende asignar al componente.
     */
    public void validate(FacesContext facesContext, UIComponent component, java.lang.Object object){
        validateUnique(facesContext, component, object, record -> record.getPregunta() == this.getRecord().getPregunta() && record.getDescripcion().equals(object.toString()) );
    }
}
