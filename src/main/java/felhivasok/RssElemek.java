package felhivasok;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RssElemek {
    String link;
    String title;
    String description;
    List<String> category;
    String pubdate;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY. MMMM dd.");

    public RssElemek(String link, String title, String description, List<String> category) {
        this.link = link;
        this.title = title;
        this.description = description;
        this.category = category;
//        this.pubdate = LocalDate.now().format(formatter); //A Rome parser nem tudja kiolvasni a pubdate-et, ezert a parse datumat adtam hozza
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

    public List<String> getCategory() {
        return category;
    }

    public String getPubdate() {
        return pubdate;
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

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    @Override
    public String toString() {
        return "A pályázat címe: " + title + "\nLeírás: " + description + "\nLink: " +
                link + "\nPályázati kategóriák: " + category + "\nLetöltés dátuma: " + pubdate + "\n";
    }

}
