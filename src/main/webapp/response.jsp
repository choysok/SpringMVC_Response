<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2020/4/22
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

     <script src="js/jquery-3.4.1.min.js"></script>
    <script>
      $(function () {
         $("#btn").on("click",function () {
                $.ajax({
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

                });
         });
      });
   </script>
</head>
<body>
<h1>Redirect成功</h1>
<a href="/user/testString" >testString测试</a>
<br>
<br>
<button id="btn">发送ajax请求</button>


</body>
</html>
