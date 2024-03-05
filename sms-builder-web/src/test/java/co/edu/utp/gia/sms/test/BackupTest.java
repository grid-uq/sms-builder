package co.edu.utp.gia.sms.test;

import co.edu.utp.gia.sms.entidades.*;
import co.edu.utp.gia.sms.importutil.FileMultipleRegisterParseFactory;
import co.edu.utp.gia.sms.importutil.ReferenceParse;
import co.edu.utp.gia.sms.importutil.TipoArchivo;
import co.edu.utp.gia.sms.negocio.AtributoCalidadService;
import co.edu.utp.gia.sms.negocio.FuenteService;
import co.edu.utp.gia.sms.util.json.JsonExporter;
import co.edu.utp.gia.sms.util.json.JsonImporter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BackupTest {
    static Revision revisionGenerated;
    static String fileName;
    @BeforeAll
    public static void prepareData() throws IOException {
        fileName = "backup.zip";
        revisionGenerated = poblar();
        restoreData();
    }

    @AfterAll
    public static void restoreData() throws IOException {
       // Files.deleteIfExists(Paths.get(fileName));
    }


    void exportarTest() throws IOException {
        JsonExporter.INSTANCE.toJsonFile(revisionGenerated,fileName);
    }

//    @Test
    void backupTest() throws IOException {
        exportarTest();
        var revision = JsonImporter.INSTANCE.importFromJson(fileName);

        assertEquals(revisionGenerated.getNombre(),revision.getNombre());
        assertTrue(revisionGenerated.getReferencias().containsAll(revision.getReferencias()));
        assertArrayEquals(revisionGenerated.getPreguntas().toArray(),revision.getPreguntas().toArray());
        assertArrayEquals(revisionGenerated.getObjetivos().toArray(),revision.getObjetivos().toArray());
        revisionGenerated.getPasosProceso().forEach(
                paso->{
                    var auxiliar = revision.getPasosProceso().stream().filter(paso::equals).findFirst();
                    assertTrue(auxiliar.isPresent());
                    assertArrayEquals(paso.getReferencias().toArray(),auxiliar.get().getReferencias().toArray());
                }
        );
    }
    static Revision poblar(){
        Revision revision = new Revision("Prueba","Revision de prueba");

        var at1 = new AtributoCalidad(AtributoCalidadService.SCI,true);
        var at2 = new AtributoCalidad(AtributoCalidadService.CVI,false);
        var at3 =new AtributoCalidad(AtributoCalidadService.IRRQ,false);
        revision.getAtributosCalidad().add( at1);
        revision.getAtributosCalidad().add( at2);
        revision.getAtributosCalidad().add( at3);
        var f1 = new Fuente(FuenteService.INCLUSION_DIRECTA,TipoFuente.INCLUSION_DIRECTA);
        revision.getFuentes().add(f1);
        revision.getFuentes().add(new Fuente(FuenteService.SNOWBALL_BACKWARD,TipoFuente.BOLA_NIEVE));
        revision.getFuentes().add(new Fuente(FuenteService.SNOWBALL_FORWARD,TipoFuente.BOLA_NIEVE));
        var r1 = new Recurso("recurso1 nombre","url Recurso 1",true);
        var r2 = new Recurso("recurso2 nombre","url Recurso 1",true);
        var r3 = new Recurso("recurso3 nombre","url Recurso 3",false);
        var r4 = new Recurso("recurso4 nombre","url Recurso 4",false);
        revision.getRecursos().add(r1);
        revision.getRecursos().add(r2);
        revision.getRecursos().add(r3);
        revision.getRecursos().add(r4);
        var rol1 = new Rol("rol 1");
        rol1.getRecursos().add(r1);
        rol1.getRecursos().add(r2);

        var rol2 = new Rol("rol 2 2");
        rol2.getRecursos().add(r3);
        rol2.getRecursos().add(r4);
        revision.getRoles().add( rol2 );

        var usuario = crearUsuario(rol1, rol2);
        revision.getUsuarios().add(usuario);


        var paso1 = new Paso("paso 1", r1,false);
        var paso2 = new Paso("paso 2", r2,false);
        var paso3 = new Paso("paso 3", r3,false);
        var paso4 = new Paso("paso 4", r4,true);
        revision.getPasos().add(paso1);
        revision.getPasos().add(paso2);
        revision.getPasos().add(paso3);
        revision.getPasos().add(paso4);

        var pp1 = new PasoProceso(paso1);
        var pp2 = new PasoProceso(paso2);
        var pp3 = new PasoProceso(paso3);
        var pp4 = new PasoProceso(paso4);
        revision.getPasosProceso().add(pp1);
        revision.getPasosProceso().add(pp2);
        revision.getPasosProceso().add(pp3);
        revision.getPasosProceso().add(pp4);

        var obj1 = new Objetivo("G1","Obj 1");
        var obj2 = new Objetivo("G2","Obj 2");
        revision.getObjetivos().add(obj1);
        revision.getObjetivos().add(obj2);

        var rq1 = new Pregunta("RQ1","Pregunta 1",List.of(obj1,obj2));
        var rq2 = new Pregunta("RQ2","Pregunta 2",List.of(obj1));
        var rq3 = new Pregunta("RQ3","Pregunta 3",List.of(obj2));
        obj1.getPreguntas().add(rq1);
        obj1.getPreguntas().add(rq2);
        obj2.getPreguntas().add(rq1);
        obj2.getPreguntas().add(rq3);
        revision.getPreguntas().add(rq1);
        revision.getPreguntas().add(rq2);
        revision.getPreguntas().add(rq3);

        var t1 = new Topico("T1 R1",rq1);
        var t2 = new Topico("T2 R1",rq1);
        rq1.getTopicos().add(t1);
        rq1.getTopicos().add(t2);
        revision.getTopicos().add(t1);
        revision.getTopicos().add(t2);

        var ter1 = new Termino("Termino 1");
        ter1.getSinonimos().add("sino 1");
        ter1.getSinonimos().add("sino 2");
        revision.getTerminos().add(ter1);

        var cri1 = new CriterioSeleccion("criterio 1","descr 1",false);
        revision.getCriteriosSeleccion().add(cri1);

        var cad1 = new CadenaBusqueda(f1,"consulta", Calendar.getInstance().getTime(),23,12);
        revision.getCadenasBusqueda().add(cad1);

        var referencias = testParse(new Fuente("ACM",TipoFuente.BASE_DATOS),TipoArchivo.RIS, "/acm_3_mendeleyris.ris", 3);
        referencias.forEach(revision.getReferencias()::add);
        referencias.forEach(pp1.getReferencias()::add);
        referencias.forEach(pp2.getReferencias()::add);
        referencias.forEach(pp3.getReferencias()::add);
        referencias.forEach(pp4.getReferencias()::add);

        referencias.forEach(referencia -> {
            referencia.getTopicos().add(t1);
            referencia.getEvaluacionCalidad().add( new EvaluacionCalidad(referencia,at1) );
            referencia.getEvaluacionCalidad().add( new EvaluacionCalidad(referencia,at2) );
            referencia.getEvaluacionCalidad().add( new EvaluacionCalidad(referencia,at3) );
        });

        return revision;
    }

    private static Usuario crearUsuario(Rol rol1, Rol rol2) {
        var usuario = new Usuario();
        usuario.setNombreUsuario("root");
        usuario.setClave("12345");
        usuario.setRoles(List.of(rol1, rol2));
        usuario.setNombre("root");
        usuario.setEmail("root@email.com");
        return usuario;
    }

    static List<Referencia> testParse(Fuente fuente, TipoArchivo tipoArchivo, String nombreArchivo, int cantidadReferencias) {

        ReferenceParse frmp = FileMultipleRegisterParseFactory.getInstance(tipoArchivo,fuente);

        InputStream archivo = ImportTest.class.getResourceAsStream(nombreArchivo);
        return frmp.parse(archivo);
    }
}
