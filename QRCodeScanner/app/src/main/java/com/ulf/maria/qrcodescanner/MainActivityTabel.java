package com.ulf.maria.qrcodescanner;

/**
 * Created by user on 3/11/2018.
 */

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityTabel extends Activity implements OnClickListener {
    Pakaian pakaian = new Pakaian();
    TableLayout tabelPakaian;

    Button buttonTambahPakaian;
    ArrayList<Button> buttonEdit = new ArrayList<Button>();
    ArrayList<Button> buttonDelete = new ArrayList<Button>();

    JSONArray arrayPakaian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabellayout);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        //pengenalan variabel
        tabelPakaian = (TableLayout) findViewById(R.id.tablePakaian);
        buttonTambahPakaian = (Button) findViewById(R.id.buttonTambahPakaian);
        buttonTambahPakaian.setOnClickListener(this);

        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.CYAN);

        TextView viewHeaderId = new TextView(this);
        TextView viewHeaderNomor = new TextView(this);
        TextView viewHeaderNama = new TextView(this);
        TextView viewHeaderJenis = new TextView(this);

        viewHeaderId.setText("ID");
        viewHeaderNomor.setText("Nomor");
        viewHeaderNama.setText("Nama");
        viewHeaderJenis.setText("Jenis");

        viewHeaderId.setPadding(5, 1, 5, 1);
        viewHeaderNomor.setPadding(5, 1, 5, 1);
        viewHeaderNama.setPadding(5, 1, 5, 1);
        viewHeaderJenis.setPadding(5, 1, 5, 1);


        barisTabel.addView(viewHeaderId);
        barisTabel.addView(viewHeaderNomor);
        barisTabel.addView(viewHeaderNama);
        barisTabel.addView(viewHeaderJenis);

        tabelPakaian.addView(barisTabel, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));

        try {

            arrayPakaian = new JSONArray(pakaian.tampilPakaian());

            for (int i = 0; i < arrayPakaian.length(); i++) {
                JSONObject jsonChildNode = arrayPakaian.getJSONObject(i);
                String nomor = jsonChildNode.optString("nomor");
                String nama = jsonChildNode.optString("nama");
                String jenis = jsonChildNode.optString("jenis");

                String id = jsonChildNode.optString("id");

                System.out.println("Nomor :" + nomor);
                System.out.println("Nama :" + nama);
                System.out.println("Jenis :" + jenis);

                System.out.println("ID :" + id);

                barisTabel = new TableRow(this);

                if (i % 2 == 0) {
                    barisTabel.setBackgroundColor(Color.LTGRAY);
                }

                TextView viewId = new TextView(this);
                viewId.setText(id);
                viewId.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewId);

                TextView viewNomor = new TextView(this);
                viewNomor.setText(nomor);
                viewNomor.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewNomor);

                TextView viewNama = new TextView(this);
                viewNama.setText(nama);
                viewNama.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewNama);

                TextView viewJenis = new TextView(this);
                viewJenis.setText(jenis);
                viewJenis.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewJenis);

                buttonEdit.add(i, new Button(this));
                buttonEdit.get(i).setId(Integer.parseInt(id));
                buttonEdit.get(i).setTag("Edit");
                buttonEdit.get(i).setText("Edit");
                buttonEdit.get(i).setOnClickListener(this);
                barisTabel.addView(buttonEdit.get(i));

                buttonDelete.add(i, new Button(this));
                buttonDelete.get(i).setId(Integer.parseInt(id));
                buttonDelete.get(i).setTag("Delete");
                buttonDelete.get(i).setText("Delete");
                buttonDelete.get(i).setOnClickListener(this);
                barisTabel.addView(buttonDelete.get(i));

                tabelPakaian.addView(barisTabel, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {

        if (view.getId() == R.id.buttonTambahPakaian) {
            // Toast.makeText(MainActivity.this, "Button Tambah Data",
            // Toast.LENGTH_SHORT).show();

            tambahPakaian();

        } else {
           /*
            * Melakukan pengecekan pada data array, agar sesuai dengan index
            * masing-masing button
            */
            for (int i = 0; i < buttonEdit.size(); i++) {

            /* jika yang diklik adalah button edit */
                if (view.getId() == buttonEdit.get(i).getId() && view.getTag().toString().trim().equals("Edit")) {
                    // Toast.makeText(MainActivity.this, "Edit : " +
                    // buttonEdit.get(i).getId(), Toast.LENGTH_SHORT).show();
                    int id = buttonEdit.get(i).getId();
                    getDataByID(id);

                } /* jika yang diklik adalah button delete */
                else if (view.getId() == buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")) {
                    // Toast.makeText(MainActivity.this, "Delete : " +
                    // buttonDelete.get(i).getId(), Toast.LENGTH_SHORT).show();
                    int id = buttonDelete.get(i).getId();
                    deletePakaian(id);

                }
            }
        }
    }

    public void deletePakaian(int id) {
        pakaian.deletePakaian(id);

          /* restart acrtivity */
        finish();
        startActivity(getIntent());

    }

    public void getDataByID(int id) {

        String nomorEdit = null, namaEdit = null, jenisEdit = null;
        JSONArray arrayPersonal;

        try {

            arrayPersonal = new JSONArray(pakaian.getPakaianById(id));

            for (int i = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
                nomorEdit = jsonChildNode.optString("nomor");
                namaEdit = jsonChildNode.optString("nama");
                jenisEdit = jsonChildNode.optString("jenis");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        // buat id tersembunyi di alertbuilder
        final TextView viewId = new TextView(this);
        viewId.setText(String.valueOf(id));
        viewId.setTextColor(Color.TRANSPARENT);
        layoutInput.addView(viewId);

        final EditText editNomor = new EditText(this);
        editNomor.setText(nomorEdit);
        layoutInput.addView(editNomor);

        final EditText editNama = new EditText(this);
        editNama.setText(namaEdit);
        layoutInput.addView(editNama);

        final EditText editJenis = new EditText(this);
        editJenis.setText(jenisEdit);
        layoutInput.addView(editJenis);

        AlertDialog.Builder builderEditPakaian = new AlertDialog.Builder(this);
        builderEditPakaian.setIcon(R.drawable.batagrams);
        builderEditPakaian.setTitle("Update Data");
        builderEditPakaian.setView(layoutInput);
        builderEditPakaian.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nomor = editNomor.getText().toString();
                String nama = editNama.getText().toString();
                String jenis = editJenis.getText().toString();

                System.out.println("Nomor : " + nomor + " Nama : " + nama+ " Jenis : " + jenis);

                String laporan = pakaian.updatePakaian(viewId.getText().toString(), editNomor.getText().toString(),
                        editNama.getText().toString(),editJenis.getText().toString());

                Toast.makeText(MainActivityTabel.this, laporan, Toast.LENGTH_SHORT).show();

            /* restart acrtivity */
                finish();
                startActivity(getIntent());
            }

        });

        builderEditPakaian.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderEditPakaian.show();

    }

    public void tambahPakaian() {
          /* layout akan ditampilkan pada AlertDialog */
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final EditText editNomor = new EditText(this);
        editNomor.setHint("Nomor");
        layoutInput.addView(editNomor);

        final EditText editNama = new EditText(this);
        editNama.setHint("Nama");
        layoutInput.addView(editNama);

        final EditText editJenis = new EditText(this);
        editJenis.setHint("Jenis");
        layoutInput.addView(editJenis);

        AlertDialog.Builder builderInsertPakaian = new AlertDialog.Builder(this);
        builderInsertPakaian.setIcon(R.drawable.batagrams);
        builderInsertPakaian.setTitle("Insert Data");
        builderInsertPakaian.setView(layoutInput);
        builderInsertPakaian.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nomor = editNomor.getText().toString();
                String nama = editNama.getText().toString();
                String jenis = editJenis.getText().toString();

                System.out.println("Nomor : " + nomor + " Nama : " + nama+ " Jenis : " + jenis);

                String laporan = pakaian.inserPakaian(nomor, nama, jenis);

                Toast.makeText(MainActivityTabel.this, laporan, Toast.LENGTH_SHORT).show();

            /* restart acrtivity */
                finish();
                startActivity(getIntent());
            }

        });

        builderInsertPakaian.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderInsertPakaian.show();
    }
}
