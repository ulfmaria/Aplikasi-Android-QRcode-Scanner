

    <?php
    	include('conn.php');
     
    	$nomor=$_POST['nomor'];
    	$nama=$_POST['nama'];
    	$jenis=$_POST['jenis'];
    	
     
    	mysqli_query($conn,"insert into tb_data (nomor, nama, jenis) values ('$nomor','$nama', '$jenis')");
    	header('location:index.php');
     
    ?>