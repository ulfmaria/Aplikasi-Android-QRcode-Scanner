

    <?php
    	include('conn.php');
     
    	$id=$_GET['id'];
     
    	$nomor=$_POST['nomor'];
    	$nama=$_POST['nama'];
    	$jenis=$_POST['jenis'];
     
    	mysqli_query($conn,"update tb_data set nomor='$nomor', nama='$nama', jenis='$jenis' where id='$id'");
    	header('location:index.php');
     
    ?>