package co.edu.utp.gia.sms.importutil;

import co.edu.utp.gia.sms.entidades.Referencia;

import java.io.InputStream;
import java.util.List;

public interface ReferenceParse {
    List<Referencia> parse(InputStream input);
}
