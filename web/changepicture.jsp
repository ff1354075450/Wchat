<%--
  Created by IntelliJ IDEA.
  User: ff
  Date: 16-4-2
  Time: 上午11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传或者改变用户头像</title>
    <script type="text/javascript">
        function showPreview(){
            var str=obj.value;
            document.getElementById("previewImg").innerHTML=
                    "<img src='"+str+"'/>"
        }
    </script>
</head>
<body>
<div id="previewImg" width="500" height="500"><img src="image/preview.jpg"></div>
<form action="uploadServlet" method="post" enctype="multipart/form-data">
    请选择png图片:<input id="myfile" name="myfile" type="file" onchange="showPreview(this)">
    <input type="submit"value="提交">
</form>

</body>
</html>
