package kr.or.ddit.servlet04;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *   Multipart request 처리 방법
 *    MultipartConfig를 통해서 업로드 정책 설정. -> Part API 활성화
 *    *** 일반 문자데이터는 parameterMap으로 처리 가능해짐.
 *    
 */

@WebServlet(value = "/formDataProcess_Part", loadOnStartup = 1) // SingleValueAnnotation -> MultiValueAnnotation (이름을 정해줘야 한다.)
@MultipartConfig(location = "d:/temp", maxFileSize = 1024*1024*10, maxRequestSize = 1024*1024*10*5, fileSizeThreshold = 1024*10) 
// 여기서 파일을 업로드할 때 청크들을 어떤식으로 모을 것인지를 구체적으로 정한다. 
// maxFileSize = 업로드할 개별 파일의 사이즈를 제한한다.
// maxRequestSize = 파일 총합의 사이즈를 제한한다. (multipart 이니까)
// fileSizeThreshold = location(임시 저장소)을 안쓰고 메모리에서만 다룰 파일의 크기

public class FormDataProcessPartServlet extends HttpServlet {
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   
      req.setCharacterEncoding("utf-8");
      Part filePart = req.getPart("filePart");
      File saveFolder = new File("D:\\contents");
      String fileName = filePart.getSubmittedFileName(); // getName()은 Part의 이름 = 태그의 이름
      File saveFile = new File(saveFolder, fileName);
      try(
            InputStream is = filePart.getInputStream();
            FileOutputStream fos = new FileOutputStream(saveFile);
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
         ){
            int tmp = -1;
            while((tmp=bis.read()) != -1) {
               bos.write(tmp);
            }
         }      
      
   }
   
}