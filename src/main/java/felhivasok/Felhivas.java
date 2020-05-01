package felhivasok;

import okatok.Oktato;

import java.util.Date;
import java.util.List;

public class Felhivas {
    String felhivasCim;
    String felhivasKiiro;
    String targymutato;
    String kiPalyazhat;
    Date beadasiHatarido;
    String felhivasLink;
    String reszletesLeiras;
    List<String> kategoriak;
    List<Oktato> lehetsegesResztvevok;

    public Felhivas(String felhivasCim, String felhivasKiiro, String targymutato, String kiPalyazhat,
                    Date beadasiHatarido, String felhivasLink, String reszletesLeiras,
                    List<String> kategoriak, List<Oktato> lehetsegesResztvevok) {
        this.felhivasCim = felhivasCim;
        this.felhivasKiiro = felhivasKiiro;
        this.targymutato = targymutato;
        this.kiPalyazhat = kiPalyazhat;
        this.beadasiHatarido = beadasiHatarido;
        this.felhivasLink = felhivasLink;
        this.reszletesLeiras = reszletesLeiras;
        this.kategoriak = kategoriak;
        this.lehetsegesResztvevok = lehetsegesResztvevok;
    }
}
