package aktualis_palyazatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import palyazatkezelo.MongoAccess;

import java.time.format.DateTimeFormatter;

public class AktualisPalyazat {
    String palyazatCim;
    String leiras;
    String felhivasKod;
    Boolean KplusF;
    Double onero;
    Double tervezettOsszkoltseg;
    Double igenyeltTamogatas;
    String megjegyzes;
    AktualisResztvevok resztvevok;

    public AktualisPalyazat(String palyazatCim, String leiras, String felhivasKod, Boolean kplusF,
                            Double onero, Double tervezettOsszkoltseg, Double igenyeltTamogatas,
                            String megjegyzes, AktualisResztvevok resztvevok) {
        this.palyazatCim = palyazatCim;
        this.leiras = leiras;
        this.felhivasKod = felhivasKod;
        KplusF = kplusF;
        this.onero = onero;
        this.tervezettOsszkoltseg = tervezettOsszkoltseg;
        this.igenyeltTamogatas = igenyeltTamogatas;
        this.megjegyzes = megjegyzes;
        this.resztvevok = resztvevok;
    }

    public AktualisPalyazat() {
    }

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<AktualisPalyazat> aktualisPalyazatokColl = palyazatDB.getCollection("AktualisPalyazatok", AktualisPalyazat.class);

    public void aktualisPalyazatFeltolto() {
        aktualisPalyazatokColl.insertOne(this);
    }
    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy. MMMM dd. ");
    @Override
    public String toString() {
        return "Pályázat címe: " + palyazatCim + "\n" +
                "Leírás: " + leiras + "\n" +
                "Felhíváskód: " + felhivasKod + "\n" +
                "K+F: " + KplusF + "\n" +
                "Önerő: " + onero + "\n" +
                "Tervezett összköltség: " + tervezettOsszkoltseg + "\n" +
                "Igényelt támogatás: " + igenyeltTamogatas + "\n" +
                "Megjegyzés: " + megjegyzes + "\n" +
                "Szakmai vezető: " + resztvevok.szakmaiVezeto + "\n"+
                "Projektmenedzser: " + resztvevok.projektmenedzser + "\n" +
                "A pályázat kezelője: " + resztvevok.kezelo + "\n" +
                "Résztvevő kutatók: " + resztvevok.resztvevoEmberek.toString() + "\n";
    }


}
