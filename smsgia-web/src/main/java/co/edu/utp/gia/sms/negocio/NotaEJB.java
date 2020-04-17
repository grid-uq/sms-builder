package co.edu.utp.gia.sms.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import co.edu.utp.gia.sms.entidades.Nota;
import co.edu.utp.gia.sms.entidades.Referencia;
import co.edu.utp.gia.sms.query.Queries;

@Stateless
public class NotaEJB extends AbstractEJB<Nota, Integer> {
	@Inject
	private ReferenciaEJB referenciaEJB;

	public NotaEJB() {
		super(Nota.class);
	}
	
	public Nota registrar(Integer etapa, String descripcion, Integer idReferencia) {
		Nota nota = null;

		Referencia referencia = referenciaEJB.obtener(idReferencia);
		if (referencia != null) {
			nota = new Nota(etapa, descripcion, referencia);
			entityManager.persist(nota);
		}
		return nota;
	}

	public List<Nota> obtenerNotas(Integer idReferencia) {
		return entityManager.createNamedQuery(Queries.NOTA_GET_ALL, Nota.class).setParameter("id", idReferencia)
				.getResultList();
	}

	public void actualizar(Integer id, String descripcion) {
		Nota nota = obtener(id);
		if (nota != null) {
			nota.setDescripcion(descripcion);
		}
	}

	public Nota obtener(Integer id, int filtro) {
		List<Nota> lista = entityManager.createNamedQuery(Queries.REFERENCIA_NOTA_ETAPA_GET_ALL, Nota.class).setParameter("id", id)
				.setParameter("filtro", filtro).getResultList();
		if (lista.size()>0) {
			return lista.get(0);
		}
		Nota nota = new Nota();
		nota.setEtapa(filtro);
		return nota;
	}
	
}
