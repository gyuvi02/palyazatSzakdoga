package palyazatkezelo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;

public class Main
{
    public static void main( String[] args )
    {
     String connectioString = System.getProperty("mongodb.uri");

        try (MongoClient mongoClient = MongoClients.create(connectioString)){
            MongoDatabase palyazatDB = mongoClient.getDatabase("PalyazatDB");


            MongoCollection<Document> oktatok = palyazatDB.getCollection("Oktatok");
            JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();
        }


    }
}
