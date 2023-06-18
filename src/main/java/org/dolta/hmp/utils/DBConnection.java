package org.dolta.hmp.utils;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class DBConnection {


    public static void SetUp(){

        MongoClient mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase("data");

        MongoCollection<Document> collection = database.getCollection("userdata");

        String username = System.getProperty("user.name");
        Document document = new Document("name", username);

        collection.insertOne(document);
        mongoClient.close();
    }
}
