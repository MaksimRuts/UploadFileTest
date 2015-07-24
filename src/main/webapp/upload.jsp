<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>file upload controller</title>
</head>
<body>
    <form name="uploadForm" method="post" action="upload" enctype="multipart/form-data" >
        <h3>File upload controller form</h3>
        FIle:<input type="file" name="file" id="file"><br/>
        Destination:<input type="text" name="destination"><br/>
        <input type="submit" value="Upload" name="upload" id="upload">
    </form>
</body>
</html>