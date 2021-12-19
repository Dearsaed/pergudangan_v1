package com.example.pergudangan_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.view.*;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    DatabaseManager dm;
    private EditText eID,eNama, eStok, eVendor, eTglMasuk;
    private Button bInsert;
//    TableLayout tabel4data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dm = new DatabaseManager(this);
        eID = (EditText)findViewById(R.id.inputID);
        eNama = (EditText)findViewById(R.id.inputNama);
        eStok = (EditText)findViewById(R.id.inputStok);
        eVendor = (EditText)findViewById(R.id.inputVendor);
        eTglMasuk = (EditText)findViewById(R.id.inputTglMasuk);
        bInsert = (Button)findViewById(R.id.btnInsert);

        bInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanTable();
            }
        });
//        updateTable();


    }

    protected void simpanTable() {
        try {
            dm.addRow(Integer.parseInt(eID.getText().toString()),eNama.getText().toString(),eStok.getText().toString(),eVendor.getText().toString(),eTglMasuk.getText().toString());
//            Toast.makeText(getBaseContext(), eNm.getText().toString() + ", berhasil disimpan",Toast.LENGTH_SHORT).show();
//            updateTable();
            kosongkanField();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "gagal simpan, " +
                    e.toString(),Toast.LENGTH_LONG).show();
        }
    }//Akhir void simpanTable()

    protected void kosongkanField(){
        eID.setText("");
        eNama.setText("");
        eStok.setText("");
        eVendor.setText("");
        eTglMasuk.setText("");
    }

//    protected void updateTable() {
//        while (tabel4data.getChildCount() > 1) {
//            tabel4data.removeViewAt(1);
//        }
//        ArrayList<ArrayList<Object>> data = dm.ambilSemuaBaris();
//        for (int posisi = 0; posisi < data.size(); posisi++) {
//            TableRow tabelBaris = new TableRow(this);
//            ArrayList<Object> baris = data.get(posisi);
//            TextView idTxt = new TextView(this);
//            idTxt.setTextSize(18);
//            idTxt.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
//            idTxt.setText(" "+baris.get(0).toString()+".");
//            tabelBaris.addView(idTxt);
//            TextView namaTxt = new TextView(this);
//            namaTxt.setTextSize(18);
//            namaTxt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//            namaTxt.setText(baris.get(1).toString());
//            tabelBaris.addView(namaTxt);
//            TextView TelpTxt = new TextView(this);
//            TelpTxt.setTextSize(18);
//            TelpTxt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//            TelpTxt.setText(baris.get(2).toString());
//            tabelBaris.addView(TelpTxt);
//        }
//    } //Akhir void updateTable
//


}