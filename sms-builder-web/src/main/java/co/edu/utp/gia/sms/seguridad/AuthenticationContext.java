package co.edu.utp.gia.sms.seguridad;

import co.edu.utp.gia.sms.entidades.Usuario;

public interface AuthenticationContext {
    Usuario getCurrentUser();
}