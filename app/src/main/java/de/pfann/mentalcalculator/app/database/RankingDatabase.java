package de.pfann.mentalcalculator.app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by jopfann on 15.08.14.
 */
public class RankingDatabase extends SQLiteOpenHelper implements BaseColumns {
    public static final String TABLE_NAME = "rankingDb";
    public static final int DATABASE_VERSION = 1;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ATTRIBUTES = "PRIMARY KEY AUTOINCREMENT";

    public static final String DATATYP_INTEGER = "INTEGER";
    public static final String DATATYP_TEXT = "TEXT";
    public static final String COMMA_SEP = ",";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_POINTS = "points";

    private String LOGTAG;
    private String deleteTableString = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public RankingDatabase(final Context aContext, final String APP_NAME){
        super(aContext, TABLE_NAME, null, DATABASE_VERSION);
        LOGTAG = APP_NAME + " - " + "DB";
    }

    @Override
    public void onCreate(final SQLiteDatabase aSqLiteDatabase) {
        Log.i(LOGTAG,"onCreate Database: " + TABLE_NAME + "with vision: " + DATABASE_VERSION);
        String createTableString = "CREATE TABLE " + TABLE_NAME + " ( " +
                COLUMN_ID + " " + DATATYP_INTEGER + " " + COLUMN_ATTRIBUTES + " " + COMMA_SEP +
                COLUMN_NAME + " " + DATATYP_TEXT + " " + COMMA_SEP +
                COLUMN_POINTS + " " + DATATYP_INTEGER + ")";
        Log.i(LOGTAG, "createTableString");
        aSqLiteDatabase.execSQL(createTableString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase aSqLiteDatabase, int i, int i2) {
        aSqLiteDatabase.execSQL(deleteTableString);
        onCreate(aSqLiteDatabase);
    }
}
