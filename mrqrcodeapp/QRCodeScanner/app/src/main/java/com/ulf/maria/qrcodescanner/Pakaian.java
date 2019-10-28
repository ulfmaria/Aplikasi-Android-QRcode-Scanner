package com.ulf.maria.qrcodescanner;

/**
 * Created by user on 3/11/2018.
 */

public class Pakaian extends Koneksi {
    String URL = "http://192.168.43.81/webqrcodescan/server.php";
    String url = "";
    String response = "";

    public String tampilPakaian() {
        try {
            url = URL + "?operasi=view";
            System.out.println("URL Tampil Data: " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String inserPakaian(String nomor, String nama, String jenis) {
        try {
            url = URL + "?operasi=insert&nomor=" + nomor + "&nama=" + nama+ "&jenis=" + jenis;
            System.out.println("URL Insert Data : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String getPakaianById(int id) {
        try {
            url = URL + "?operasi=get_pakaian_by_id&id=" + id;
            System.out.println("URL Insert Data : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String updatePakaian(String id, String nomor, String nama, String jenis) {
        try {
            url = URL + "?operasi=update&id=" + id + "&nomor=" + nomor + "&nama=" + nama + "&jenis=" + jenis;
            System.out.println("URL Insert Data : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String deletePakaian(int id) {
        try {
            url = URL + "?operasi=delete&id=" + id;
            System.out.println("URL Insert Data : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

}