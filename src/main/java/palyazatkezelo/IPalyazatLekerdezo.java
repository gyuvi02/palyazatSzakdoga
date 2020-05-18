package palyazatkezelo;

public interface IPalyazatLekerdezo {

    void rendezettLekerdezes(String rendezesAlapja);

    void kulcsszavasKeresesLeirasban(String kulcsszo);

    void szemelyKereses(String nev, String kategoria);

    void projektKezdoevKereses(int kezdoEv);

    void kPluszFe(boolean kPluszF);

    void igenyeltTamogtasKereses(double min, double max);

    void fazisKereses(String fazis);

    void felhivasKodkereses(String kod);










    

}
