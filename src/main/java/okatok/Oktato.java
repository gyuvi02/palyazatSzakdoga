package okatok;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.ArrayList;

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

//    public Oktato(ArrayList<String> palyazatiTema) {
//        this.palyazatiTema = palyazatiTema;
//    }

    public Oktato() {

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
