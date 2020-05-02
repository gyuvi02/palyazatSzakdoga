package felhivasok;

import java.io.IOException;
import java.util.ArrayList;

public class FelhivasRogzito {

    FelhivasParser felhivas = new FelhivasParser();

    public void rssReszek(ArrayList<RssElemek> felhivasok) throws IOException {
        for (RssElemek elemek : felhivasok) {
            felhivas.felhivasKeszito(elemek);
        }
    }

}
