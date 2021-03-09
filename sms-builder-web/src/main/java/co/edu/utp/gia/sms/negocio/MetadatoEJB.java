package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.query.Queries;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class MetadatoEJB extends AbstractEJB<Metadato, Integer> {

    public MetadatoEJB() {
        super(Metadato.class);
    }

    public List<Metadato> obtenerListMetadatoByTipo(Integer idReferencia, TipoMetadato tipoMetadato) {
        return entityManager.createNamedQuery(Queries.METADATO_GET_ALL, Metadato.class).setParameter("id", idReferencia)
                .setParameter("tipo", tipoMetadato).getResultList();
    }

    public String obtenerStringMetadatoByTipo(Integer idReferencia, TipoMetadato tipoMetadato) {
        List<Metadato> listMetadatos = obtenerListMetadatoByTipo(idReferencia, tipoMetadato);


        return listMetadatos.stream().map(Metadato::getValue).collect(Collectors.joining(" ; "));
    }

    public List<Metadato> obtenerMetadatos(Integer idReferencia) {
        return entityManager.createNamedQuery(Queries.REFERENCIA_METADATO_GET_ALL, Metadato.class)
                .setParameter("id", idReferencia).getResultList();
    }

}
