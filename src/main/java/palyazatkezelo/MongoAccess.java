package palyazatkezelo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoAccess {
    private static MongoClient mongoClient;
    private static MongoAccess mongoAccess;

    private MongoAccess() {
    }

    public static MongoClient getConnection() {
//        String connectionString2 = System.getProperty("mongodb.uri");

        ConnectionString connectionString = new ConnectionString(System.getProperty("mongodb.uri"));

        if (mongoAccess == null) {
            mongoAccess = new MongoAccess();
        }
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                pojoCodecRegistry);

        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();

        MongoClient mongoClient = MongoClients.create(clientSettings);

        return mongoClient;
    }

    public static void closeDatabase() {
        MongoAccess.getConnection().close();
//        mongoClient.close();
    }
}


