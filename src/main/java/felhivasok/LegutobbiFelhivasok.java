package felhivasok;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class LegutobbiFelhivasok {
    private ArrayList<String> legutobbi;
    private LocalDateTime most;

    public LegutobbiFelhivasok(ArrayList<String> legutobbi) {
        this.legutobbi = legutobbi;
        this.most = LocalDateTime.now();
    }

    public LegutobbiFelhivasok() {
    }

    public ArrayList<String> getLegutobbi() {
        return legutobbi;
    }

    public void setLegutobbi(ArrayList<String> legutobbi) {
        this.legutobbi = legutobbi;
    }

    public LocalDateTime getMost() {
        return most;
    }

    public void setMost(LocalDateTime most) {
        this.most = most;
    }
}


