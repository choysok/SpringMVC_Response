<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2020/4/22
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-1.10.2.min.js"></script>
    <script>
        $(function () {
            $("#btn").on("click",function () {
                  $.ajax({
                      //编写Json格式，设置属性和值。dataType(期望服务器返回的数据类型)success（服务器成功返回）
                      //url,contentType,data,type都是向服务器发送的请求
                      url:"/user/testAjax",
                      contentType:"application/json;charset=UTF-8",
                      type:"post",
                      dataType:"json",
                      data:{"username":"dyson","password":"123","age":30},
                      success:function (data) {//data是服务器端响应的的数据
                          alert(data);
                          alert(data.username);
                          alert(data.password);
                          alert(data.age);
                      }
                  }).done(function( msg ) {
                      alert( "Data Saved: " + msg );
                  });
            });
        });
    </script>

</head>
<body>
<h1>这是首页</h1>
<a href="/testVoid" >testVoid测试</a> <br>
<a href="/user/testString" >testString测试</a><br>

<input id="btn" type="button" value="发送ajax请求">
<br>
传统方式文件上传
<form action="/user/testUpload" method="post" enctype="multipart/form-data" >
   选择文件： <input type="file" name="upload"><br>
    <input type="submit" value="上传">
</form>


SpringMVC文件上传
<form action="/user/testUploadSpringMVC" method="post" enctype="multipart/form-data" >
    选择文件： <input type="file" name="upload"><br>
    <input type="submit" value="上传">
</form>
<br>

Upload files across servers文件上传
<form action="/user/testUploadAcrossServers" method="post" enctype="multipart/form-data" >
    选择文件： <input type="file" name="upload"><br>
    <input type="submit" value="上传">
</form>
<br>
<br>
<a href="/testExceptionInterceptor">testExceptionInterceptor测试异常拦截器</a>
<br>
<br>
<a href="/user/testInterceptor">testInterceptor测试拦截器</a>


</body>
</html>
