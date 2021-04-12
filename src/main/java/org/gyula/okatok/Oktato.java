package org.gyula.okatok;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.conversions.Bson;
import org.gyula.palyazatkezelo.MongoAccess;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class Oktato {

    @BsonProperty(value = "nev")    //az elnevezesi szokasok ne keveredjenek
    private String nev;
    @BsonProperty(value = "tanszek")
    private String tanszek;
    @BsonProperty(value = "kutatasiTema")
    private ArrayList<String> kutatasiTema;
    @BsonProperty(value = "email")
    private String email;
    @BsonProperty(value = "honlap")
    private String honlap;
    @BsonProperty(value = "palyazatiTema")
    private ArrayList<String> palyazatiTema;

    public Oktato(String nev, String tanszek, ArrayList<String> kutatasiTema, String email,
                  String honlap, ArrayList<String> palyazatiTema) {
        this.nev = nev;
        this.tanszek = tanszek;
        this.kutatasiTema = new ArrayList<>();
        this.email = email;
        this.honlap = honlap;
        this.palyazatiTema = new ArrayList<>();
    }


    public Oktato() {
    }

    static MongoDatabase palyazatDB;

    static {
        try {
            palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static MongoCollection<Oktato> oktatokColl = palyazatDB.getCollection("Oktatok", Oktato.class);

    public boolean oktatoFeltolto() {
        if (oktatoEmailEllenorzes(this.getEmail()) != 0) {
            System.out.println("Ezzel az email cimmel mar regisztráltal oktatót");
            return false;
        }else
            oktatokColl.insertOne(this);
        return true;
    }

    public void oktatoFrissites() {
        Bson filter = eq("nev", this.getNev());
        Bson ujElem = (Bson) this;
        oktatokColl.findOneAndUpdate(filter, ujElem);
    }

    public int oktatoEmailEllenorzes(String email) { //itt csak ezt ellenorzom az uj oktato hazzaadasanal, a GUI lehetoseget ad a reszletesebb ellenorzesre
        return oktatokColl.find(eq("email", email)).into(new ArrayList<>()).size(); //ha 0, akkor nincs ilyen email
    }

//    A vegso valtozatban nem itt vegzem el az ellenorzest kulon-kulon, es egy Oktato objectet adok at
//    Mivel keves az oktato, nem kizart, hogy legordulo menuvel meg tudom oldani a kivalasztast
    public static Oktato oktatoLetolto(String oktato) {
        return oktatokColl.find(eq("nev", oktato)).first(); //ha null a visszakapott ertek, akkor nem letezik
    }

    public long oktatoTorol(String email) {
        return oktatokColl.deleteOne(eq("email", email)).getDeletedCount(); //ha a visszadott ertek 0, akkor nem tortent semmi
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getTanszek() {
        return tanszek;
    }

    public void setTanszek(String tanszek) {
        this.tanszek = tanszek;
    }

    public ArrayList<String> getKutatasiTema() {
        return kutatasiTema;
    }

    public void setKutatasiTema(ArrayList<String> kutatasiTema) {
        this.kutatasiTema = kutatasiTema;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHonlap() {
        return honlap;
    }

    public void setHonlap(String honlap) {
        this.honlap = honlap;
    }

    public ArrayList<String> getPalyazatiTema() {
        return palyazatiTema;
    }

    public void setPalyazatiTema(ArrayList<String> palyazatiTema) {
        this.palyazatiTema = palyazatiTema;
    }

    @Override
    public String toString() {
        return
                "\nNév: " + nev + "\n\n" +
                    "Tanszék: " + tanszek + " Tanszék\n\n" +
                    "Email cím: " + email + "\n\n" +
                    "Honlap: " + honlap + "\n\n" +
                    "Kutatási téma: " + String.join(", ", kutatasiTema) + "\n\n" +
                    "Pályázati témák: " + String.join(", ", palyazatiTema) + "\n";
    }
}
