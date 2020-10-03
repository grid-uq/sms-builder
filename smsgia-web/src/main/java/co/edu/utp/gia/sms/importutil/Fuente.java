package co.edu.utp.gia.sms.importutil;

/**
 * @author Christian A. Candela
 * @author Luis Eduardo Sepúlveda
 * @author Grupo de Investigacion en Redes Informacion y Distribucion - GRID
 * @author Universidad del Quindío
 * @author Grupo de Investigacion en Inteligencia Artificial - GIA
 * @author Universidad Tecnológica de Pereira
 * @version 1.0
 * @since 20/06/2019
 *
 */
public enum Fuente {
	ACM(TipoFuente.BASE_DATOS), IEEE(TipoFuente.BASE_DATOS), SCIENCEDIRECT(TipoFuente.BASE_DATOS),
	SCOPUS(TipoFuente.BASE_DATOS), SPRINGER(TipoFuente.BASE_DATOS), WOS(TipoFuente.BASE_DATOS),
//	ACM_MENDELEY(TipoFuente.BASE_DATOS), IEEE_MENDELEY(TipoFuente.BASE_DATOS),
//	SCIENCEDIRECT_MENDELEY(TipoFuente.BASE_DATOS), SCOPUS_MENDELEY(TipoFuente.BASE_DATOS),
//	SPRINGER_MENDELEY(TipoFuente.BASE_DATOS), WOS_MENDELEY(TipoFuente.BASE_DATOS), 
	MANUAL(TipoFuente.INCLUSION_DIRECTA),
	SNOWBALL_BACKWARD(TipoFuente.BOLA_NIEVE),SNOWBALL_FORWARD(TipoFuente.BOLA_NIEVE), INCLUSION_DIRECTA(TipoFuente.INCLUSION_DIRECTA);

	private TipoFuente tipo;

	private Fuente(TipoFuente tipo) {
		this.tipo = tipo;
	}

	/**
	 * Metodo que permite obtener el valor del atributo tipo
	 * @return El valor del atributo tipo
	 */
	public TipoFuente getTipo() {
		return tipo;
	}

	
	
}
