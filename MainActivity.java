package com.example.mid_202102276;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText kode,judul,pengarang,penerbit,isbn;
    String isikode,isijudul,isipengarang,isipenerbit,isiisbn;
    Button simpan;
    DBHelper db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kode = findViewById(R.id.kodebuku);
        judul = findViewById(R.id.judulbuku);
        pengarang = findViewById(R.id.pengarang);
        penerbit = findViewById(R.id.penerbit);
        isbn = findViewById(R.id.isbn);
        simpan = findViewById(R.id.btnsimpan);
        db = new DBHelper(this);


        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  isikode = kode.getText().toString();
                String isijudul = judul.getText().toString();
                String isipengarang = pengarang.getText().toString();
                String isipenerbit = penerbit.getText().toString();
                String isiisbn = isbn.getText().toString();

                if (TextUtils.isEmpty(isikode) || TextUtils.isEmpty(isijudul) || TextUtils.isEmpty(isipengarang)
                || TextUtils.isEmpty(isipenerbit) || TextUtils.isEmpty(isiisbn)){
                    Toast.makeText(MainActivity.this,"Semua Field Wajib diIsi", Toast.LENGTH_LONG).show();
                }else {
                    Boolean checkkode = db.checkkodebuku(isikode);
                    if (checkkode == false){
                        Boolean insert = db.insertData(isikode,isijudul,isipengarang,isipenerbit,isiisbn);
                        if (insert == true){
                            Toast.makeText(MainActivity.this, "Buku berhasil disimpan", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),BukuActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(MainActivity.this,"Buku gagal disimpan", Toast.LENGTH_SHORT).show();
                        }

                        }else {
                           Toast.makeText(MainActivity.this,"Kode Buku Sudah Ada", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });

    }
}