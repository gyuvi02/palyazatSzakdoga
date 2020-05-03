package okatok;

import java.util.List;

public class Oktato {

    String oktatoNev;
    String Tanszek;
    List<String> kutatasiTema;
    String email;
    String honlap;

    public Oktato(String oktatoNev, String tanszek, List<String> kutatasiTema, String email, String honlap) {
        this.oktatoNev = oktatoNev;
        Tanszek = tanszek;
        this.kutatasiTema = kutatasiTema;
        this.email = email;
        this.honlap = honlap;
    }

    public Oktato() {
    }

    public String getOktatoNev() {
        return oktatoNev;
    }

    public void setOktatoNev(String oktatoNev) {
        this.oktatoNev = oktatoNev;
    }

    public String getTanszek() {
        return Tanszek;
    }

    public void setTanszek(String tanszek) {
        Tanszek = tanszek;
    }

    public List<String> getKutatasiTema() {
        return kutatasiTema;
    }

    public void setKutatasiTema(List<String> kutatasiTema) {
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
}
