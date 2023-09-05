package co.edu.utp.gia.sms.entidades.util;

import co.edu.utp.gia.sms.entidades.Metadato;
import co.edu.utp.gia.sms.entidades.TipoMetadato;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MetadatoUtil {
    INSTANCE;
    public Stream<Metadato> getStream(Collection<Metadato> metadatos, TipoMetadato tipoMetadato){
        return metadatos.stream().filter(metadato -> metadato.getIdentifier().equals(tipoMetadato));
    }

    public List<Metadato> get(Collection<Metadato> metadatos,TipoMetadato tipoMetadato){
        return getStream(metadatos,tipoMetadato).toList();
    }

    public Stream<String> getValuesStream(Collection<Metadato> metadatos,TipoMetadato tipoMetadato){
        return getStream(metadatos,tipoMetadato).map(Metadato::getValue);
    }

    public String getValuesAsString(Collection<Metadato> metadatos,TipoMetadato tipoMetadato){
        return getValuesAsString(metadatos,tipoMetadato,";");
    }

    public String getValuesAsString(Collection<Metadato> metadatos,TipoMetadato tipoMetadato,String separator){
        return getValuesStream(metadatos,tipoMetadato).collect(Collectors.joining(separator));
    }

    public List<String> getAutores(Collection<Metadato> metadatos){
        return getValuesStream(metadatos,TipoMetadato.AUTOR).toList();
    }

    public String getAutoresAsString(Collection<Metadato> metadatos){
        return getValuesAsString(metadatos,TipoMetadato.AUTOR);
    }

    public List<String> getKeyWords(Collection<Metadato> metadatos){
        return getValuesStream(metadatos,TipoMetadato.KEYWORD).toList();
    }

    public String getKeyWordsAsString(Collection<Metadato> metadatos){
        return getValuesAsString(metadatos,TipoMetadato.KEYWORD);
    }

    public List<String>  getAbstract(Collection<Metadato> metadatos){
        return getValuesStream(metadatos,TipoMetadato.ABSTRACT).toList();
    }

    public String getAbstractAsString(Collection<Metadato> metadatos){
        return getValuesAsString(metadatos,TipoMetadato.ABSTRACT);
    }

}
