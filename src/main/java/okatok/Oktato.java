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
}
