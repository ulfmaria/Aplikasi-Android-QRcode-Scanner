

    <?php
    $server = "localhost";
    $username = "root";
    $password = "";
    $database = "qrcodescan";

    mysql_connect($server, $username, $password) or die("<h1>Koneksi Mysql Error : 

    </h1>" . mysql_error());
    mysql_select_db($database) or die("<h1>Koneksi Kedatabase Error : </h1>" .

    mysql_error());

    @$operasi = $_GET['operasi'];
    switch ($operasi) {
        case "view":

            $query_tampil_pakaian = mysql_query("SELECT * FROM tb_data")

     or die(mysql_error());
            $data_array = array();
            while ($data = mysql_fetch_assoc($query_tampil_pakaian)) {
                $data_array[] = $data;
            }
            echo json_encode($data_array);
            break;
        case "insert":
            /* Source code untuk Insert data */
            @$nomor = $_GET['nomor'];
            @$nama = $_GET['nama'];
            @$jenis = $_GET['jenis'];
            $query_insert_data = mysql_query("INSERT INTO tb_data 
                (nomor, nama, jenis) VALUES('$nomor', '$nama', '$jenis')");
            if ($query_insert_data) {
                echo "Data Berhasil Disimpan";
            } else {
                echo "Error Insert Pakaian " . mysql_error();
            }
            break;
        case "get_pakaian_by_id":
           
            @$id = $_GET['id'];
            $query_tampil_pakaian = mysql_query("SELECT * FROM tb_data 
                WHERE id='$id'"
                ) or die(mysql_error());
            $data_array = array();
            $data_array = mysql_fetch_assoc($query_tampil_pakaian);
            echo "[" . json_encode($data_array) . "]";

            break;
        case "update":
            /* Source code untuk Update Biodata */
        @$nomor = $_GET['nomor'];
        @$nama = $_GET['nama'];
        @$jenis = $_GET['jenis'];

            @$id = $_GET['id'];
            $query_update_pakaian = mysql_query("UPDATE tb_data SET nomor='$nomor', 
                nama='$nama' , jenis='$jenis' WHERE id='$id'");
            if ($query_update_pakaian) {
                echo "Update Data Berhasil";
            } else {
                echo mysql_error();
            }
            break;
        case "delete":
            /* Source code untuk Delete Biodata */
            @$id = $_GET['id'];
            $query_delete_pakaian = mysql_query("DELETE FROM tb_data WHERE

     id='$id'");
            if ($query_delete_pakaian) {
                echo "Delete Data Berhasil";
            } else {
                echo mysql_error();
            }

            break;

        default:
            break;
    }
    ?>