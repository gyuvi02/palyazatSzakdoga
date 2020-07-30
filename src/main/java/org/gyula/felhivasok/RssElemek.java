package org.gyula.felhivasok;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RssElemek {
    String link;
    String title;
    String description;
    ArrayList<String> category;
//    String pubdate;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY. MMMM dd.");

    public RssElemek(String link, String title, String description, ArrayList<String> category) {
        this.link = link;
        this.title = title;
        this.description = description;
        this.category = category;
//        this.pubdate = LocalDate.now().format(formatter); //A Rome parser nem tudja kiolvasni a pubdate-et, ezert a parse datumat adtam hozza
    }

    public RssElemek() {
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "A pályázat címe: " + title + "\nLeírás: " + description + "\nLink: " +
                link + "\nPályázati kategóriák: " + category + "\n\n";
    }

}
