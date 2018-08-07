package de.pfann.mentalcalculator.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jopfann on 15.08.14.
 */
public class RankingDataAccessor {

    private RankingDatabase rankingDatabase;
    private String LogTag;

    public RankingDataAccessor(final Context aContext, final String APP_NAME) {
        rankingDatabase = new RankingDatabase(aContext, APP_NAME);
        LogTag = APP_NAME + "RankingDataAccessor";
    }

    public void addRankingEntry(final RankingEntry rankingEntry) {
        Log.i(LogTag,"addRankingEntry: " + rankingEntry.getName() + " - " + rankingEntry.getPoints());
        SQLiteDatabase database = rankingDatabase.getWritableDatabase();
        database.insert(
                RankingDatabase.TABLE_NAME, null,
                getContentValues(rankingEntry));
        database.close();
    }


    private ContentValues getContentValues(RankingEntry rankingEntry) {
        ContentValues values = new ContentValues();
        values.put(RankingDatabase.COLUMN_NAME, rankingEntry.getName());
        values.put(RankingDatabase.COLUMN_POINTS, rankingEntry.getPoints());
        return values;
    }

    public List<RankingEntry> getAllRankingEntries() {
        List<RankingEntry> rankingEntries = new ArrayList<RankingEntry>();
        SQLiteDatabase database = rankingDatabase.getReadableDatabase();
        String[] projection = {
                RankingDatabase.COLUMN_ID,
                RankingDatabase.COLUMN_NAME,
                RankingDatabase.COLUMN_POINTS};

        String sortOrder =
                RankingDatabase.COLUMN_POINTS + " DESC";

        Cursor databaseCursor = database.query(
                RankingDatabase.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        if (databaseCursor.moveToFirst()) {
            do {
                RankingEntry  rankingEntry = new RankingEntry(databaseCursor.getInt(0),databaseCursor.getString(1),databaseCursor.getInt(2));
                rankingEntries.add(rankingEntry);
            } while (databaseCursor.moveToNext());
        }
        Log.i(LogTag,"getAllRankingEntries: " + rankingEntries.size() + "(Size)");
        return rankingEntries;
    }

    public void deleteAllEntries() {
        Log.i(LogTag,"Delete ranking");
        SQLiteDatabase database = rankingDatabase.getWritableDatabase();
        database.delete(RankingDatabase.TABLE_NAME,null,null);
        database.close();
    }

    public void deleteEntry(RankingEntry aRankingEntry){
        Log.i(LogTag,"Delete Entry:" + aRankingEntry.toString());
        SQLiteDatabase database = rankingDatabase.getWritableDatabase();
        database.delete(RankingDatabase.TABLE_NAME,RankingDatabase.COLUMN_ID+"= ?",new String[]{String.valueOf(aRankingEntry.getEntry_id())});
        database.close();
    }

    public void logoutAllEntries(){
        int rank = 1;
        for(RankingEntry entry : getAllRankingEntries()){
            Log.i(LogTag,rank + " : " + entry.toString());
            rank++;
        }
    }
}
