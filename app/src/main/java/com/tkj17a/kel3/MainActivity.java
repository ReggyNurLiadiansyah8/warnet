package com.tkj17a.kel3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button btnhitung ,btnexit;
TextView txtnama,txtjam,txttampil;
RadioGroup rg;
String nama ,jumlah,jm,hasil,spinn;
Double disc,disc2,hasil3;
Integer jumlah2,harga,hasil2;
//Spinner spinr;
 private Spinner spin;
 private String [] Kategori= {
   " Kecepatan :",
         "Super",
         "Middle",
         "Low",
 };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtnama = findViewById(R.id.txtnama);
        txtjam = findViewById(R.id.txtjam);
        rg = findViewById(R.id.rg);
        spin = findViewById(R.id.spin);
        btnexit = findViewById(R.id.btnexit);
        btnexit.setOnClickListener(this);
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
        btnhitung = findViewById(R.id.btnhitung);
        btnhitung.setOnClickListener(this);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Kategori);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> AdapterView, View view, int i, long l) {
                hasil = adapter.getItem(i);
                spinn = spin.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
    }
            @Override
            public void onClick(View v) {
        if (txtnama.getText().toString().length()==0 || hasil == "Kecepatan" || txtjam.getText().toString().length()==0) {
            Toast.makeText(getApplicationContext(), "Data harus lengkap !!", Toast.LENGTH_SHORT).show();
        } else {
            nama = txtnama.getText().toString();
            jumlah = txtjam.getText().toString();
            jumlah2 = Integer.parseInt(jumlah);
            int member = rg.getCheckedRadioButtonId();
            switch (member) {
                case R.id.rb1:
                    jm = "Member";
                    break;
                case R.id.rb2:
                    jm = "Non Member";
                    break;
            }
            if (jumlah2 >= 10 && hasil == "Super" && jm == "Non Member") {
                disc = 0.1;
            } else if (jumlah2 >= 8 && hasil == "Middle" && jm == "Non Member") {
                disc = 0.07;
            } else if (jumlah2 >= 5 && hasil == "Low" && jm == "Non Member") {
                disc = 0.05;
            } else {
                disc = 0.0;
            }

            if (hasil == "Super" && jm == "Non Member") {
                harga = 10000;
                hasil2 = harga * jumlah2;
                disc2 = harga * jumlah2 * disc;
                hasil3 = hasil2 - disc2;
            } else if (hasil == "Midle" && jm == "Non Member") {
                harga = 7500;
                hasil2 = harga * jumlah2;
                disc2 = harga * jumlah2 * disc;
                hasil3 = hasil2 - disc2;
            } else {
                harga = 5000;
                hasil2 = harga * jumlah2;
                disc2 = hasil2 * disc;
                hasil3 = hasil2 - disc2;
            }
        }
pesan();
    }

    public void pesan () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage("Nama pelanggan :" + nama +"\n" + "Status Member :" +jm + "\n" +"Kategori Kecepatan :" +spinn+ "\n" +"harga perjam :" +harga +"\n"
                        +"jumlah jam :" +jumlah +" jam"+"\n"+"potongan :" +disc2 +"\n"+"Harga yang harus di bayar :"+"Rp."+hasil3)
                .setNegativeButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
