package co.edu.utp.gia.sms.negocio;

import java.util.List;

import javax.ejb.Stateless;

import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.TipoMetadato;
import co.edu.utp.gia.sms.query.Queries;

@Stateless
public class MetadatoEJB extends AbstractEJB<Metadato, Integer>{

	public MetadatoEJB() {
		super(Metadato.class);
	}
	
	public List<Metadato> obtenerListMetadatoByTipo(Integer idReferencia, TipoMetadato tipoMetadato) {
		return entityManager.createNamedQuery(Queries.METADATO_GET_ALL, Metadato.class).setParameter("id", idReferencia)
				.setParameter("tipo", tipoMetadato).getResultList();
	}

	public String obtenerStringMetadatoByTipo(Integer idReferencia, TipoMetadato tipoMetadato) {
		List<Metadato> listMetadatos = obtenerListMetadatoByTipo(idReferencia, tipoMetadato);
		String valor = "";
		String separador = "";
		for (Metadato metadato : listMetadatos) {
			valor = valor + separador + metadato.getValue();
			separador = " ; ";
		}
		return valor;
	}

	public List<Metadato> obtenerMetadatos(Integer idReferencia) {
		List<Metadato> listaMetadatos = entityManager.createNamedQuery(Queries.REFERENCIA_METADATO_GET_ALL, Metadato.class)
				.setParameter("id", idReferencia).getResultList();
			return listaMetadatos;
	}	

}
