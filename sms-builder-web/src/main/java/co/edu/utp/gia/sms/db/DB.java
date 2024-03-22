package co.edu.utp.gia.sms.db;


import org.eclipse.serializer.reflect.ClassLoaderProvider;
import org.eclipse.store.storage.embedded.configuration.types.EmbeddedStorageConfiguration;
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager;

public class DB {
    public static EmbeddedStorageManager storageManager;
    public final static DataRoot root = new DataRoot();

    static {
        final var foundation = EmbeddedStorageConfiguration.load().createEmbeddedStorageFoundation();
        foundation.onConnectionFoundation(connectionFoundation ->
                connectionFoundation.setClassLoaderProvider(ClassLoaderProvider.New(Thread.currentThread()
                        .getContextClassLoader())));
        storageManager = foundation.createEmbeddedStorageManager(root).start();
    }
}
