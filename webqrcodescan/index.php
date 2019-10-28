<!DOCTYPE html>
<html>
<head>
    <title>Aplikasi Web QRCODE SCAN</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div style="height:50px;"></div>
    <div class="well" style="margin:auto; padding:auto; width:80%;">
    <span style="font-size:25px; color:blue"><center><strong>Aplikasi Web MR-QR CODE SCANNER BARANG</strong> <img width=60 height=60 src='iconmrqrcode.png' /></center></span> 
        <span class="pull-left"><a href="#addnew" data-toggle="modal" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Add New</a></span> 
        
        <div style="height:50px;"></div>
        <table class="table table-striped table-bordered table-hover">
            <thead>
                <th>NOMOR BARANG</th>
                <th>NAMA BARANG</th>
                <th>JENIS</th>
                <th>Action</th>
            </thead>
            <tbody>
            <?php
                include('conn.php');
                
                $query=mysqli_query($conn,"select * from `tb_data`");
                while($row=mysqli_fetch_array($query)){
                    ?>
                    <tr>
                        <td><?php echo $row['nomor']; ?></td>
                        <td><?php echo $row['nama']; ?></td>
                        <td><?php echo $row['jenis']; ?></td>
                  
                        <td>
                            <a href="#edit<?php echo $row['id']; ?>" data-toggle="modal" class="btn btn-warning"><span class="glyphicon glyphicon-edit"></span> Edit</a> || 
                            <a href="#del<?php echo $row['id']; ?>" data-toggle="modal" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Delete</a>
                            <?php include('button.php'); ?>
                        </td>
                    </tr>
                    <?php
                }
            
            ?>
            </tbody>
        </table>
    </div>
    <?php include('add_modal.php'); ?>
</div>
</body>
</html>