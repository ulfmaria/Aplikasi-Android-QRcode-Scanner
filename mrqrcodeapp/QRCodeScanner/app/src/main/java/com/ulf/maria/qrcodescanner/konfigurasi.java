package com.ulf.maria.qrcodescanner;

/**
 * Created by user on 2/12/2018.
 */

public class konfigurasi {
    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_ADD="http://192.168.43.81/webqrcodescan/android/tambah.php";
    public static final String URL_GET_ALL = "http://192.168.43.81/webqrcodescan/android/tampilSemua.php";
    public static final String URL_GET_EMP = "http://192.168.43.81/webqrcodescan/android/tampilPgw.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.43.81/webqrcodescan/android/updatePgw.php";
    public static final String URL_DELETE_EMP = "http://192.168.43.81/webqrcodescan/android/hapusPgw.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NOMOR = "nomor";
    public static final String KEY_EMP_NAMA = "nama";
    public static final String KEY_EMP_JENIS = "jenis"; //desg itu variabel untuk posisi

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_JENIS = "jenis";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_ID = "emp_id";
}
