package okatok;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.conversions.Bson;
import palyazatkezelo.MongoAccess;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class Oktato {

    @BsonProperty(value = "nev")    //az elnevezesi szokasok ne keveredjenek
    String nev;
    @BsonProperty(value = "tanszek")
    String tanszek;
    @BsonProperty(value = "kutatasiTema")
    ArrayList<String> kutatasiTema;
    @BsonProperty(value = "email")
    String email;
    @BsonProperty(value = "honlap")
    String honlap;
    @BsonProperty(value = "palyazatiTema")
    ArrayList<String> palyazatiTema;

    public Oktato(String nev, String tanszek, ArrayList<String> kutatasiTema, String email,
                  String honlap, ArrayList<String> palyazatiTema) {
        this.nev = nev;
        this.tanszek = tanszek;
        this.kutatasiTema = kutatasiTema;
        this.email = email;
        this.honlap = honlap;
        this.palyazatiTema = palyazatiTema;
    }

    public Oktato() {
    }

    MongoDatabase palyazatDB = MongoAccess.getConnection().getDatabase("PalyazatDB");
    MongoCollection<Oktato> oktatokColl = palyazatDB.getCollection("Oktatok", Oktato.class);

    public void oktatoFeltolto() {
        if (oktatoEmailEllenorzes(this.email)) {
            oktatokColl.insertOne(this);
        }else
            System.out.println("Ezzel az email cimmel mar regisztráltal oktatót");
    }

    private boolean oktatoEmailEllenorzes(String email) { //itt csak ezt ellenorzom az uj oktato hazzaadasanal, a GUI lehetoseget ad a reszletesebb ellenorzesre
        FindIterable<Oktato> iterable = oktatokColl.find();
        for (Oktato oktato : iterable) {
            if (oktato.email.equals(email)) {
                return false;
            }
        }
        return true;
    }

//    A vegso valtozatban nem itt vegzem el az ellenorzest kulon-kulon, es egy Oktato objectet adok at
//    Mivel keves az oktato, nem kizart, hogy legordulo menuvel meg tudom oldani a kivalasztast
    public Oktato oktatoLetolto(String oktato) {
        Oktato keresettOktato = oktatokColl.find(eq("nev", oktato)).first();
        if (keresettOktato == null){
            System.out.println("Nincs ilyen oktató");
            return null;
        }
        else
            return keresettOktato;
    }

    public void oktatoTorol(String torlendoOktato) {
        Bson filter = eq("nev", torlendoOktato);
        if (oktatokColl.find(filter).first() != null){
            System.out.println(oktatokColl.deleteOne(filter));
        }
        else System.out.println("Nincs ilyen oktató");
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
                "Név: " + nev + "\n" +
                        "Tanszék: " + tanszek + " Tanszék\n" +
                        "Kutatasi téma: " + kutatasiTema + "\n" +
                        "Email cím: " + email + "\n" +
                        "Honlap: " + honlap + "\n" +
                        "Pályázati témák: " + palyazatiTema;
    }

}
