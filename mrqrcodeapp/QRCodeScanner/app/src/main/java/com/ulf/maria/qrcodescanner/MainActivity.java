package com.ulf.maria.qrcodescanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonScan;
    private TextView textViewNama, textViewNomor, textViewJenis;
    private Button buttonAdd;
    private Button buttonTabel;

    //qr code scanner object
    private IntentIntegrator intentIntegrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize object
        buttonScan = (Button) findViewById(R.id.buttonScan);
        textViewNomor = (TextView) findViewById(R.id.textViewNomor);
        textViewNama = (TextView) findViewById(R.id.textViewNama);
        textViewJenis = (TextView) findViewById(R.id.textViewJenis);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonTabel = (Button) findViewById(R.id.buttonTabel);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonTabel.setOnClickListener(this);

        // attaching onclickListener
        buttonScan.setOnClickListener(this);
    }
    //Dibawah ini merupakan perintah untuk Menambahkan Pegawai (CREATE)
    private void addEmployee(){

        final String nomor = textViewNomor.getText().toString().trim();
        final String nama = textViewNama.getText().toString().trim();
        final String jenis = textViewJenis.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_NOMOR,nomor);
                params.put(konfigurasi.KEY_EMP_NAMA,nama);
                params.put(konfigurasi.KEY_EMP_JENIS,jenis);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }
    // Mendapatkan hasil scan


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Hasil tidak ditemukan", Toast.LENGTH_SHORT).show();
            } else {
                // jika qrcode berisi data
                try {
                    // converting the data json
                    JSONObject object = new JSONObject(result.getContents());
                    // atur nilai ke textviews
                    textViewNomor.setText(object.getString("nomor"));
                    textViewNama.setText(object.getString("nama"));
                    textViewJenis.setText(object.getString("jenis"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    // jika format encoded tidak sesuai maka hasil
                    // ditampilkan ke toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {
        // inisialisasi IntentIntegrator(scanQR)
        if (v == buttonScan) {
            intentIntegrator = new IntentIntegrator(this);
            intentIntegrator.initiateScan();
        }

        if (v == buttonAdd) {
            addEmployee();
        }
        if (v == buttonTabel) {
            startActivity(new Intent(this, MainActivityTabel.class));
        }
    }
}
