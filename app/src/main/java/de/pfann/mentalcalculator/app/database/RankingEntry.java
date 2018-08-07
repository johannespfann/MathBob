package de.pfann.mentalcalculator.app.database;

/**
 * Created by jopfann on 15.08.14.
 */
public class RankingEntry {

    private int entry_id;
    private String name;
    private int points;

    public RankingEntry(final String aName, final int aPoints){
        name = aName;
        points = aPoints;
    }

    public RankingEntry(final int aEntry_id, final String aName, final int aPoints){
        entry_id = aEntry_id;
        name = aName;
        points = aPoints;
    }

    public void setEntry_id(int entry_id) {
        this.entry_id = entry_id;
    }

    public int getEntry_id() {
        return entry_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return name + " : " + points;
    }
}
