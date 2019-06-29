package com.zzm.demo.controllet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UploadaController {


    @RequestMapping(value = "/uploadimage",method = RequestMethod.POST)
    public Map upload(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Map<String,String> map = new HashMap<>();
        try {
            MultipartHttpServletRequest request1 = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = request1.getFileMap();
            if (fileMap != null && fileMap.size() >0) {
                if (fileMap.containsKey("picture")) {
                    MultipartFile file = fileMap.get("picture");
                    String fileName = file.getOriginalFilename();
                    String[] split = fileName.split("\\.");
                    String newFileName = "";
                    if (split != null ) {
                        String extendName = split[split.length-1];
                        newFileName = new Date().getTime()+""+extendName;
//                        ServletContext servletContext = request.getSession().getServletContext();
//                        String uploadPath = servletContext.getRealPath("/")+"ipload\\";
//                        File file1  = new File(uploadPath,newFileName);
//                        file.transferTo(file1);
                        File dir = new File("IMAGE");
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        File file1 = new File(dir.getAbsolutePath()+"/1.jpg");
                        file.transferTo(file1);

                    }

//                    session.setAttribute("newFileNmae",newFileName);
                }

            }
            map.put("msg","上传成功");
            map.put("data",null);
            return map;
        }catch (Exception e) {
            e.printStackTrace();
            map.put("msg","上传失败");
            map.put("data",null);
            return map;
        }
    }
}
