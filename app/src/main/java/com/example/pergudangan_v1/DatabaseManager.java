package com.example.pergudangan_v1;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import java.util.ArrayList;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

public class DatabaseManager {

    private static final String ROW_ID = "_id";
    private static final String ROW_NAMA = "nama";
    private static final String ROW_STOK = "stok";
    private static final String ROW_VENDOR = "vendor";
    private static final String ROW_TGLMASUK = "tglMasuk";


    private static final String NAMA_DB = "database1";
    private static final String NAMA_TABEL = "tblbarang";
    private static final int DB_VERSION = 3;
    private static final String CREATE_TABLE = "create table IF NOT EXISTS "+NAMA_TABEL+
            " ("+ROW_ID+" integer, "+ROW_NAMA+" text,"+ROW_STOK+" text, "+ROW_VENDOR+" text, "+ROW_TGLMASUK+" text)";
    private final Context context;
    private DatabaseOpenHelper dbHelper;
    private SQLiteDatabase db;

    public DatabaseManager(Context ctx) {
        this.context = ctx;
        dbHelper = new DatabaseOpenHelper(context);
        setDb(dbHelper.getWritableDatabase());
    }
    public SQLiteDatabase getDb() {
        return db;
    }
    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    private static class DatabaseOpenHelper extends SQLiteOpenHelper {
        public DatabaseOpenHelper(Context context) {
            super(context, NAMA_DB, null, DB_VERSION);
// TODO Auto-generated constructor stub 39:
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub
            db.execSQL(CREATE_TABLE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
// TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS "+NAMA_DB);
            onCreate(db);
        }
    }
    public void close() {
        dbHelper.close();
    }

    //private String QueryUpdate;
    public void UpdateRecord (int iId, String sName, String sStok, String sVendor, String sTglMasuk)
    {
        ContentValues values = new ContentValues();
        values.put(ROW_ID, iId);
        values.put(ROW_NAMA, sName);
        values.put(ROW_STOK, sStok);
        values.put(ROW_VENDOR, sVendor);
        values.put(ROW_TGLMASUK, sTglMasuk);
        try {
            db.update(NAMA_TABEL, values, ROW_ID+"="+iId, null);
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString()); e.printStackTrace();
        }
    }

    //menghapus data
    public void DeleteRecord (int Iid)
    {
        ContentValues values = new ContentValues();
        values.put(ROW_ID, Iid);
        try {
            db.delete(NAMA_TABEL, ROW_ID+"="+Iid, null);
/*sSQLQuery = "DELETE FROM datadiri " +
"WHERE ROW_ID='" + iId + "';";
db.execSQL(sSQLQuery);*/
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString()); e.printStackTrace();
        }
    }

    //menambah data
    public void addRow(int iId, String sName, String sStok, String sVendor, String sTglMasuk) {
        ContentValues values = new ContentValues();

        values.put(ROW_ID, iId);
        values.put(ROW_NAMA, sName);
        values.put(ROW_STOK, sStok);
        values.put(ROW_VENDOR, sVendor);
        values.put(ROW_TGLMASUK, sTglMasuk);
        try {
            db.insert(NAMA_TABEL, null, values);
        } catch (Exception e) {
            Log.e("DB ERROR", e.toString()); e.printStackTrace();
        }
    }
    //menampung data hasil select, dan ditampung pada arraylist
    public ArrayList<ArrayList<Object>> ambilSemuaBaris() {
        ArrayList<ArrayList<Object>> dataArray = new ArrayList<ArrayList<Object>>();
        Cursor cur;
        try {
            cur = db.query(NAMA_TABEL,
                    new String[] { ROW_ID, ROW_NAMA, ROW_STOK, ROW_VENDOR, ROW_TGLMASUK }, null, null,
                    null, null, null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    ArrayList<Object> dataList = new ArrayList<Object>();
                    dataList.add(cur.getLong(0));
                    dataList.add(cur.getString(1));
                    dataList.add(cur.getString(2));
                    dataArray.add(dataList);
                } while (cur.moveToNext());
            }
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("Database ERROR", e.toString());
        }
        return dataArray;
    }

}
