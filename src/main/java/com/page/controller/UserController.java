package com.page.controller;

import com.page.controller.domain.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/testString")
    public String testString(Model model){
        System.out.println("执行了/user/testString请求！");
        User user  = new User();
        user.setAge(20);
        user.setUsername("张三");
        user.setPassword("123");
        model.addAttribute("key1",user);
        System.out.println(user);
        return "yes";
    }

    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("执行了/user/testVoid请求！");
//        User user  = new User();
//        user.setAge(20);
//        user.setUsername("张三");
//        user.setPassword("123");
//        model.addAttribute("key1",user);
       // request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request,response);

      //  response.sendRedirect(request.getContextPath()+"/response.jsp");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset='UTF-8'");
        response.getWriter().println("你好");
    }

    @RequestMapping("/testAjax")
    public @ResponseBody User testAjax(@RequestBody User user) {
       //request.setCharacterEncoding("UTF-8");
       // response.setCharacterEncoding("UTF-8");
      //  response.setContentType("text/html;charset='UTF-8'");
        System.out.println("执行了/user/testAjax请求！");
        //客户端发送ajax的请求，传的是ajaxz字符串，后端把数据封装在User里
        System.out.println(user);
        user.setUsername("Page");
        user.setAge(50);
        return user;
    }

    /*
    使用传统方式上传文件
     */

    @RequestMapping("/testUpload")
    public String  testUpload(HttpServletRequest request) throws Exception {
        System.out.println("执行了/user/testUpload请求！");
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断该路径是否存在
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        //解析request对象，获取上传文件项
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

        List<FileItem> fileItems = servletFileUpload.parseRequest(request);
        for(FileItem file1:fileItems){
             //进行判断，当前item对象是否是上传文件项
            if(file1.isFormField()){
                //普通的表单项
            }else {
                //上传文件项
                //获取上传文件的名称
                String fileName = file1.getName();
                //把文件设置成唯一值UUID
                String uuid = UUID.randomUUID().toString().replace("-", "");
                fileName = uuid+"_"+fileName;

                //完成文件上传
                file1.write(new File(path,fileName));
                //删除临时文件,大于10kb产生缓存文件
                file1.delete();

            }

        }


        return "success";
    }
  /*
  使用SpringMVC中的方法上传文件
   */
    @RequestMapping("/testUploadSpringMVC")
    public String testUploadSpringMVC(HttpServletRequest request, MultipartFile upload){
        System.out.println("SpringMVC上传文件！——执行了/user/testUploadSpringMVC请求！");
        String path = request.getSession().getServletContext().getRealPath("/upload/");
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        //获取文件的名称
        String filename = upload.getOriginalFilename();
        //把文件名称设置成唯一值
        UUID uuid = UUID.randomUUID();
        String replace = uuid.toString().replace("_", "");
        filename = replace+"_"+filename;
        //完成文件上传
        try {
            upload.transferTo(new File(path,filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";
    }
    /*
       跨服务器上传
     */

    @RequestMapping("/testUploadAcrossServers")
    public String testUploadAcrossServers(HttpServletRequest request,MultipartFile upload) throws Exception {
        System.out.println("跨服务器上传文件！——执行了/user/testUploadAcrossServers请求！");

        //获取文件的原始名称
        String filename = upload.getOriginalFilename();
        //把文件名称设置成唯一值
        UUID uuid = UUID.randomUUID();
        String replace = uuid.toString().replace("_", "");
        filename = replace+"_"+filename;
        //服务器地址
        String path = "http://localhost:8088/uploads/";
        //创建客户端对象
        Client client = Client.create();
        //连接图片服务器;
        WebResource webResource = client.resource(path + filename);
        //完成跨服务器上传文件
        webResource.put(upload.getBytes());


        return "success";
    }

    @RequestMapping("/testInterceptor")
    public String  testInterceptor() throws Exception {
        System.out.println("执行了 testInterceptor请求！");

        return "success";
    }

}
