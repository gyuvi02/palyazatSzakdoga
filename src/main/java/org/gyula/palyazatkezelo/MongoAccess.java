package org.gyula.palyazatkezelo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoConfigurationException;
import com.mongodb.MongoTimeoutException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.gyula.InternetEllenorzo;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoAccess {
    private static MongoAccess mongoAccess;

    private MongoAccess() {
    }

    public static MongoClient getConnection() throws InterruptedException {
        ConnectionString connectionString = null;
        InternetEllenorzo.ellenoriz();

        try {
//        connectionString = new ConnectionString(System.getProperty("mongodb.uri"));
//        connectionString = new ConnectionString("mongodb://localhost:27017");
            connectionString = new ConnectionString("mongodb+srv://SzaboGyula:Gyulus99@gygykpalyazat-kljmo.mongodb.net/test?retryWrites=true&w=majority");
        } catch (MongoConfigurationException | MongoTimeoutException e) {
            System.out.println("Problema a kapcsolattal");
        }

        if (mongoAccess == null) {
                mongoAccess = new MongoAccess();
        }
        try {
            CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
            CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                    pojoCodecRegistry);
            MongoClientSettings clientSettings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .codecRegistry(codecRegistry)
                    .build();


            return MongoClients.create(clientSettings);

        } catch (Exception e) {
            System.out.println(e.getCause());
            return null;
        }
    }

    public static void closeDatabase() throws InterruptedException {
        MongoAccess.getConnection().close();
//        mongoClient.close();
    }
}


