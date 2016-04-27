package Mservlet;

import DB.UserDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * Created by ff on 16-4-23.
 */

public class UpLoadServlet extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
//获取流信息
        InputStream filesource = request.getInputStream();
        String tempFileName="./tempFile";
        File tempfile = new File(tempFileName);
        FileOutputStream outputStream = new FileOutputStream(tempfile);
        byte buff[]= new byte[1024];
        int n;
        while ((n=filesource.read(buff))!=-1){
            outputStream.write(buff,0,n);
        }
        //关闭输出流，输入流
        outputStream.close();
        filesource.close();

        //获取上传文件的起始位置
        RandomAccessFile randomfile = new RandomAccessFile(tempfile,"r");
        randomfile.readLine();
        String str = randomfile.readLine();
        int beginIndex = str.lastIndexOf("=")+2;
        int endIndex = str.lastIndexOf("\"");
        HttpSession session=request.getSession();
        String userid=(String)session.getAttribute("userid");
        String filename =userid +".png";
        System.out.println("filename--:"+filename);

        //重新定位倒文件指针的文件头
        randomfile.seek(0);
        long startPosition = 0;
        int i=0;
        //获取文件内容，开始位置
        while((n = randomfile.readByte())!=-1 && i<4){
            if(n=='\n'){
                startPosition=randomfile.getFilePointer();
                i++;
            }
        }
        startPosition=startPosition;
        //获取文件内容，结束位置
        randomfile.seek(randomfile.length());
        long endPosition = randomfile.getFilePointer();
        int j=1;
        while (endPosition >= 0 && j<=2){
            endPosition--;
            randomfile.seek(endPosition);
            if(randomfile.readByte() == '\n'){
                j++;
            }
        }
        endPosition=endPosition-1;

        //设置保存上传文件的路径
        String readPath = getServletContext().getRealPath("/")+"image";
        System.out.println("文件位置"+readPath);
        File fileupload = new File(readPath);
        if(!fileupload.exists()){
            fileupload.mkdir();
        }
        File saveFile = new File(readPath,filename);
        RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");
        //从临时文件中读取文件内容
        randomfile.seek(startPosition);
        while (startPosition<endPosition){
            randomAccessFile.write(randomfile.readByte());
            startPosition = randomfile.getFilePointer();
        }
        //关闭输入输出流，删除临时文件
        randomAccessFile.close();
        randomfile.close();
        tempfile.delete();


        //数据库中更改图片名,
        UserDao ud = new UserDao();
        ud.changpicture(filename,userid);

        request.setAttribute("result","上传成功");
        request.getRequestDispatcher("/Login-success.jsp").forward(request,response);



    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
