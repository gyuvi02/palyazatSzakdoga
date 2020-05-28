package okatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import palyazatkezelo.MongoAccess;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OktatoTest {
    private Oktato oktato;

    @BeforeEach
    public void setup() {
        oktato = new Oktato();
    }

    @org.junit.jupiter.api.BeforeAll
    static void setUp() {
        MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
        MongoCollection<Oktato> oktatokColl = palyazatDB.getCollection("Oktatok", Oktato.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"rosszemail@valami", "xxx", "", "ez is rossz"})
    void oktatoEmailEllenorzesFailed(String input) {
        assertEquals(oktato.oktatoEmailEllenorzes(input), 0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"rosszemail@valami", "xxx", "", "ez is rossz"})
    void oktatoTorolFailed(String input) {
        assertEquals(oktato.oktatoTorol(input), 0.0);
    }

}