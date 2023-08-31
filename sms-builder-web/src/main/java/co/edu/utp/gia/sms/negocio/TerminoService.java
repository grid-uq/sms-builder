package co.edu.utp.gia.sms.negocio;

import co.edu.utp.gia.sms.db.DB;
import co.edu.utp.gia.sms.entidades.Termino;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Clase de negocio encargada de implementar las funciones correspondientes a la
 * gestion del {@link Termino}.
 *
 * @author Christian A. Candela <christiancandela@uniquindio.edu.co>
 * @author Luis E. Sepúlveda R <lesepulveda@uniquindio.edu.co>
 * @author Grupo de Investigacion en Redes Información y Distribución - GRID
 * @author Universidad del Quindío
 * @version 1.0
 * @since 12/11/2015
 */
@ApplicationScoped
public class TerminoService extends AbstractGenericService<Termino, String>{
	public TerminoService() {
		super(DB.root.getProvider(Termino.class));
	}
	/**
	 * Permite registrar una termino
	 * 
	 * @param descripcion Descripcion de la Termino
	 * @return La Termino registrada
	 */
	public Termino save(String descripcion) {
		Termino termino = new Termino(descripcion);
		return save(termino);
	}

	/**
	 * Permite actualizar una Termino
	 * 
	 * @param id          Id de la {@link Termino} a ser actualizada
	 * @param descripcion Descripcion de la Termino a actulizar
	 */
	public void update(String id, String descripcion) {
		find(id).ifPresent(termino->termino.setDescripcion(descripcion));
	}

	/**
	 * Permite adicionar un sinónimo a un Termino
	 *
	 * @param id          Id de la {@link Termino} al que se adicionará el sinónimo
	 * @param sinonimo Sinónimo a ser adicionado
	 */
    public void addSinonimo(String id, String sinonimo) {
		var termino = findOrThrow(id);
		termino.adicionarSinonimo( sinonimo );
		DB.storageManager.store(termino.getSinonimos());
    }

	/**
	 * Permite remover un sinónimo a un Termino
	 *
	 * @param id          Id de la {@link Termino} al que se adicionará el sinónimo
	 * @param sinonimo Sinónimo a ser removido
	 */
	public void removeSinonimo(String id, String sinonimo) {
		var termino = findOrThrow(id);
		termino.removerSinonimo( sinonimo );
		DB.storageManager.store(termino.getSinonimos());
	}
}
