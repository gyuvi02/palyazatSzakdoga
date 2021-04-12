package okatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.gyula.okatok.Oktato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.gyula.palyazatkezelo.MongoAccess;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OktatoTest {
    private Oktato oktato;

    @BeforeEach
    public void setup() {
        oktato = new Oktato();
    }

    @org.junit.jupiter.api.BeforeAll
    static void setUp() throws InterruptedException {
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
