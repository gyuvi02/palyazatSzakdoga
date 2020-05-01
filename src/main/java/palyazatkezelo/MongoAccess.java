package palyazatkezelo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoAccess {
    private static MongoClient mongoClient;
    private static MongoAccess mongoAccess;

    private MongoAccess() {
    }

    public static MongoClient getConnection() {
        String connectioString = System.getProperty("mongodb.uri");

        if (mongoAccess == null) {
            mongoAccess = new MongoAccess();
        }
        MongoClient mongoClient = MongoClients.create(connectioString);

        return mongoClient;
    }

    public static void closeDatabase() {
        mongoClient.close();
    }
}


