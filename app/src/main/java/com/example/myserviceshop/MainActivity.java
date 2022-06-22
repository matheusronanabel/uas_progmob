package com.example.myserviceshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText fieldNamaBarang, fieldJumlahStock, fieldHargaJual;
    Button buttonSubmit, buttonUpdate, buttonDelete, buttonView;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fieldNamaBarang = findViewById( R.id.fieldNamaBarang);
        fieldJumlahStock = findViewById(R.id.fieldJumlahStock);
        fieldHargaJual = findViewById(R.id.fieldHargaJual);

        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonView = findViewById(R.id.buttonView);

        DB=new DBHelper(this);

        buttonSubmit.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String NamaBarangTXT = fieldNamaBarang.getText().toString();
                String JumlahStockTXT = fieldJumlahStock.getText().toString();
                String HargaJualTXT = fieldHargaJual.getText().toString();

                Boolean checkinsertdata = DB.insertProductData(NamaBarangTXT,JumlahStockTXT,HargaJualTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "New Data has been inserted!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data Entry Failed! Please Try Again", Toast.LENGTH_SHORT).show();
            }
        });

        buttonUpdate.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String NamaBarangTXT = fieldNamaBarang.getText().toString();
                String JumlahStockTXT = fieldJumlahStock.getText().toString();
                String HargaJualTXT = fieldHargaJual.getText().toString();

                Boolean checkupdatedata = DB.updateProductData(NamaBarangTXT,JumlahStockTXT,HargaJualTXT);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "Update Data has been inserted!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data Entry Failed! Please Try Again", Toast.LENGTH_SHORT).show();
            }
        });

        buttonDelete.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String NamaBarangTXT = fieldNamaBarang.getText().toString();
                Boolean checkdeletedata = DB.deleteProductData(NamaBarangTXT);
                if(checkdeletedata==true)
                    Toast.makeText(MainActivity.this, "Delete Data has been inserted!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data Delete Failed! Please Try Again", Toast.LENGTH_SHORT).show();
            }
        });

        buttonView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Cursor res = DB.getdata();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this, "It's empty here.",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Nama Barang :" + res.getString(0)+"\n");
                    buffer.append("Jumlah Stock :" + res.getString(1)+"\n");
                    buffer.append("Harga Jual :" + res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Product Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}