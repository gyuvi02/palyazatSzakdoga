package palyazatok;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

//csak a teszteleskor adok hozza teljes palyazatot
public class Palyazat {
    private String palyazatCim;
    private String aktualisFazis; //ez lehet elkezdett, beadott, lezart, elfogadott, elutasitott
    private String leiras;
    private String felhivasKod;
    private String beadasEve;
    private String KplusF;
    private Double onero;
    private Double tervezettOsszkoltseg;
    private Double igenyeltTamogatas;
    private String megjegyzes;
    private PalyazatiResztvevok resztvevok = new PalyazatiResztvevok();
    private String DEazonosito;
    private String szerzodesSzam;
    private LocalDate kezdet;
    private LocalDate veg;

    public Palyazat(String palyazatCim, String aktualisFazis, String leiras, String felhivasKod, String beadasEve,
                    String kplusF, Double onero, Double tervezettOsszkoltseg, Double igenyeltTamogatas,
                    String megjegyzes, PalyazatiResztvevok resztvevok, String DEazonosito, String szerzodesSzam,
                    LocalDate kezdet, LocalDate veg) {
        this.palyazatCim = palyazatCim;
        this.aktualisFazis = aktualisFazis;
        this.leiras = leiras;
        this.felhivasKod = felhivasKod;
        this.beadasEve = beadasEve;
        this.KplusF = kplusF;
        this.onero = onero;
        this.tervezettOsszkoltseg = tervezettOsszkoltseg;
        this.igenyeltTamogatas = igenyeltTamogatas;
        this.megjegyzes = megjegyzes;
        this.resztvevok = resztvevok;
        this.DEazonosito = DEazonosito;
        this.szerzodesSzam = szerzodesSzam;
        this.kezdet = kezdet;
        this.veg = veg;
    }
    //Alapbol ezzel hozzuk letre, a tobbi mezot utolag adjuk hozza
    public Palyazat(String palyazatCim, String aktualisFazis, String szakmaiVezeto, Double onero, Double tervezettOsszkoltseg,
                    Double igenyeltTamogatas) {
        this.palyazatCim = palyazatCim;
        this.aktualisFazis = aktualisFazis;
        this.kezdet = LocalDate.now();
        this.veg = LocalDate.now();
        this.resztvevok = new PalyazatiResztvevok(szakmaiVezeto, "", "", new ArrayList<String>()); //ezt a NullPointerException elkerulese miatt adjuk hozza
        this.onero = onero;
        this.tervezettOsszkoltseg = tervezettOsszkoltseg;
        this.igenyeltTamogatas = igenyeltTamogatas;

    }

    public Palyazat() {
    }

    static MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    static MongoCollection<Palyazat> palyazatokColl = palyazatDB.getCollection("Palyazatok", Palyazat.class);


    public boolean PalyazatFeltolto() {
        if (palyazatEllenorzo(this.getPalyazatCim()) != 0 ) {//csak a cimet ellenorzom, nem lehet 2 egyforma cimu palyazat
            System.out.println("Mar van ilyen palyazat");
            return false;
        } else {
            palyazatokColl.insertOne(this);
            return true;
        }
    }

    public Palyazat PalyazatLetolto(String palyazatCim) {
        return palyazatokColl.find((eq("palyazatCim", palyazatCim))).first(); //Ha ureset kuld vissza, akkor nem leteik a palyazat
    }


    public  void PalyazatTorlo(String palyazatcim) {
        System.out.println(palyazatokColl.deleteOne(eq("palyazatCim", palyazatcim)));
    }

    public void PalyazatFrissito() {
        palyazatokColl.replaceOne(eq("palyazatCim", this.getPalyazatCim()), this);
    }

    public static ArrayList<String> PalyazatokListaja() {
        return palyazatokColl.find().map(Palyazat::getPalyazatCim).into(new ArrayList<>());
    }

    public ArrayList<String> osszesPalyazat() {
        ArrayList<String> palyazatLista = new ArrayList<>();
        FindIterable<Palyazat> iterPalyazat = palyazatokColl.find();
        for (Palyazat palyazat : iterPalyazat) {
            palyazatLista.add(palyazat.getPalyazatCim());
        }
        return palyazatLista;
    }

    public ArrayList<String> menedzserKereso(String menedzser) {
        ArrayList<String> palyazatLista = new ArrayList<>();
            FindIterable<Palyazat> iterPalyazat = palyazatokColl.find();
        for (Palyazat palyazat : iterPalyazat) {
            palyazatLista.add(palyazat.getPalyazatCim());
        }
        return palyazatLista;
    }

    public void resztvevoFrissito(String palyazatCim, ArrayList<String> ujLista) {
        Bson filter = eq("palyazatCim", palyazatCim);
        Bson ujElem = set("resztvevok.resztvevoEmberek", ujLista);
        palyazatokColl.updateOne(filter, ujElem);
    }



    public int palyazatEllenorzo(String cim) {
        return palyazatokColl.find(eq("palyazatCim", cim)).into(new ArrayList<>()).size(); //ha 0, akkor nincs ilyen email
    }


    public String getPalyazatCim() {
        return palyazatCim;
    }

    public void setPalyazatCim(String palyazatCim) {
        this.palyazatCim = palyazatCim;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public String getFelhivasKod() {
        return felhivasKod;
    }

    public void setFelhivasKod(String felhivasKod) {
        this.felhivasKod = felhivasKod;
    }

    public String getBeadasEve() {
        return beadasEve;
    }

    public void setBeadasEve(String beadasEve) {
        this.beadasEve = beadasEve;
    }

    public String getKplusF() {
        return KplusF;
    }

    public void setKplusF(String kplusF) {
        KplusF = kplusF;
    }

    public Double getOnero() {
        return onero;
    }

    public void setOnero(Double onero) {
        this.onero = onero;
    }

    public Double getTervezettOsszkoltseg() {
        return tervezettOsszkoltseg;
    }

    public void setTervezettOsszkoltseg(Double tervezettOsszkoltseg) {
        this.tervezettOsszkoltseg = tervezettOsszkoltseg;
    }

    public Double getIgenyeltTamogatas() {
        return igenyeltTamogatas;
    }

    public void setIgenyeltTamogatas(Double igenyeltTamogatas) {
        this.igenyeltTamogatas = igenyeltTamogatas;
    }

    public String getMegjegyzes() {
        return megjegyzes;
    }

    public void setMegjegyzes(String megjegyzes) {
        this.megjegyzes = megjegyzes;
    }

    public PalyazatiResztvevok getResztvevok() {
        return resztvevok;
    }

    public void setResztvevok(PalyazatiResztvevok resztvevok) {
        this.resztvevok = resztvevok;
    }

    public String getAktualisFazis() {
        return aktualisFazis;
    }

    public String getDEazonosito() {
        return DEazonosito;
    }

    public void setDEazonosito(String DEazonosito) {
        this.DEazonosito = DEazonosito;
    }

    public String getSzerzodesSzam() {
        return szerzodesSzam;
    }

    public void setSzerzodesSzam(String szerzodesSzam) {
        this.szerzodesSzam = szerzodesSzam;
    }

    public LocalDate getKezdet() {
        return kezdet;
    }

    public void setKezdet(LocalDate kezdet) {
        this.kezdet = kezdet;
    }

    public LocalDate getVeg() {
        return veg;
    }

    public void setVeg(LocalDate veg) {
        this.veg = veg;
    }

    static DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy. MMMM dd. ");
    public void setAktualisFazis(String aktualisFazis) {
        this.aktualisFazis = aktualisFazis;
    }

    public static String toStringHelyettPalyazat(Palyazat palyazat) {
        StringBuilder str = new StringBuilder();
        ArrayList<String> resztvevok = palyazat.getResztvevok().getResztvevoEmberek();
        str.append("Pályázat címe: ").append(palyazat.getPalyazatCim()).append("\n\n")
                .append("A pályázat állapota: ").append(palyazat.getAktualisFazis()).append("\n\n")
                .append("Leírás: ").append(palyazat.getLeiras() == null ? "-" : palyazat.getLeiras()).append("\n\n")
                .append("Felhíváskód: ").append(palyazat.getFelhivasKod() == null ? "-" : palyazat.getFelhivasKod()).append("\n\n")
                .append("K+F: ").append(palyazat.getKplusF() == null ? "-" : palyazat.getKplusF()).append("\n\n")
                .append("Önerő: ").append(palyazat.getOnero().toString()).append("\n\n")
                .append("Tervezett összköltség: ").append(palyazat.getTervezettOsszkoltseg().toString()).append("\n\n")
                .append("Igényelt támogatás: ").append(palyazat.getIgenyeltTamogatas().toString()).append("\n\n")
                .append("Megjegyzés: ").append(palyazat.getMegjegyzes() == null ? "-" : palyazat.getMegjegyzes()).append("\n\n")
                .append("Szerződésszám: ").append(palyazat.getSzerzodesSzam() == null ? "-" : palyazat.getSzerzodesSzam()).append("\n\n")
                .append("A pályázat kezdete: ").append(palyazat.getKezdet().format(formatters)).append("\n\n")
                .append("A pályázat vége: ").append(palyazat.getVeg().format(formatters)).append("\n\n")
                .append("Szakmai vezető: ").append(palyazat.getResztvevok().getSzakmaiVezeto()).append("\n\n")
                .append("Projektmenedzser: ").append(palyazat.getResztvevok().getProjektmenedzser() == null ? "-"
                : palyazat.getResztvevok().getProjektmenedzser()).append("\n\n")
                .append("A pályázat kezelője: ").append(palyazat.getResztvevok().getKezelo() == null ? "-"
                : palyazat.getResztvevok().getKezelo()).append("\n\n")
                .append("Résztvevő kutatók: ").append(resztvevok.isEmpty() ? "-" :
                String.join(", ", palyazat.getResztvevok().getResztvevoEmberek())).append("\n\n");
        return str.toString();
    }

//    @Override
//    public String toString() {
//        return "Pályázat címe: " + getPalyazatCim() + "\n\n" +
//                "A pályázat állapota: " + getAktualisFazis() + "\n\n" +
//                "Leírás: " + getLeiras() + "\n\n" +
//                "Felhíváskód: " + getFelhivasKod() + "\n\n" +
//                "K+F: " + getKplusF() + "\n\n" +
//                "Önerő: " + getOnero() + "\n" +
//                "Tervezett összköltség: " + getTervezettOsszkoltseg() + " Ft\n" +
//                "Igényelt támogatás: " + getIgenyeltTamogatas() + " Ft\n\n" +
//                "Megjegyzés: " + getMegjegyzes() + "\n\n" +
//                "Szerződésszám: " + getSzerzodesSzam() + "\n\n" +
//                "A pályázat kezdete: " + getKezdet().format(formatters) + "\n\n" + //a datumok is NullPointer Exception-t adnak
//                "A pályázat vége: " + getVeg().format(formatters) + "\n\n" +
//                "Szakmai vezető: " + getResztvevok().getSzakmaiVezeto() + "\n\n"+     //ha nincs megadva, akkor NullPointerException lesz
//                "Projektmenedzser: " + getResztvevok().getProjektmenedzser() + "\n\n" +
//                "A pályázat kezelője: " + getResztvevok().getKezelo() + "\n\n" +
//                "Résztvevő kutatók: " + String.join(", ", getResztvevok().getResztvevoEmberek())  + "\n"
//                 ;
//    }

}
