package palyazatkezelo;

import java.util.ArrayList;

public interface IPalyazatLekerdezo {

    ArrayList<Palyazat> rendezettLekerdezes(String rendezesAlapja);

    void kulcsszavasKeresesLeirasban(String kulcsszo);

    void szemelyKereses(String nev, String kategoria);

    void projektKezdoevKereses(int kezdoEv);

    void kPluszFe(boolean kPluszF);

    void igenyeltTamogtasKereses(double min, double max);

    void fazisKereses(String fazis);

    void felhivasKodkereses(String kod);

    ArrayList<Palyazat> idorend(ArrayList<Palyazat> palyazat); //ehhez letre kell hozni a Palyazat abstract osztalyt










    

}
