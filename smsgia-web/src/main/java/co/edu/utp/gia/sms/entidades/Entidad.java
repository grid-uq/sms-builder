package co.edu.utp.gia.sms.entidades;

import java.io.Serializable;

public interface Entidad<TipoId> extends Serializable {
	public TipoId getId();
}
