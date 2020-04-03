package co.edu.utp.gia.sms.entidades;

import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({

	@NamedQuery(name = "", query = "select p from Pregunta p where p.revision.id = :id")

})
public class Querys {

}
