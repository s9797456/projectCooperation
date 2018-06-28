package com.credit.util;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;


import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

public class WordToJPG {

	
	//将World格式的文件转换为PDF格式的文件
    public static String wordToPDF(String docfile, String toFile, int type) {
    	String filePath = null; //PDF文件路径
        ActiveXComponent app = new ActiveXComponent("Word.Application"); // 启动word    
        try {
            //设置word程序非可视化运行
            app.setProperty("Visible", new Variant(false));
            //打开word文件
            Dispatch docs = app.getProperty("Documents").toDispatch();
            //打开word文件，注意这里第三个参数要设为false，这个参数表示是否以只读方式打开，因为我们要保存原文件，所以以可写方式打开 
            Dispatch doc = Dispatch.invoke(docs,"Open",Dispatch.Method,new Object[] { docfile, new Variant(false),new Variant(true) }, new int[1]).toDispatch();
            //new Variant(type)，这里面的type的决定另存为什么类型的文件 17代表为PDF类型  
            //作为PDF格式保存文件
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[]{toFile, new Variant(type)}, new int[1]);
            Variant f = new Variant(false);
            //关闭doc文件
            Dispatch.call(doc, "Close", f);
            System.out.println("World文件转换PDF文件成功");
            filePath = toFile + ".pdf";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //退出word程序
			app.invoke("Quit", new Variant[] {});
        }
        return filePath;
    }
    
    //将PDF格式的文件转换为JPG格式的文件
    public static void pdfToJPG(String inputFile, String outFile) throws IOException {
        // load a pdf from a byte buffer
        File file = new File(inputFile);
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        FileChannel channel = raf.getChannel();
        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        PDFFile pdffile = new PDFFile(buf);
        //输出文件的页数
        System.out.println("页数： " + pdffile.getNumPages());
        for (int i = 1; i <= pdffile.getNumPages(); i++) {
            //以图片的形式来描绘首页
            PDFPage page = pdffile.getPage(i);
            Rectangle rect = new Rectangle(0, 0, (int) page.getBBox().getWidth(), (int) page.getBBox().getHeight());
            //生成图片
            //clip rect,null for the ImageObserver,fill background with white,block until drawing is done
			Image img = page.getImage(rect.width, rect.height,rect, null,true,true);
            BufferedImage tag = new BufferedImage(rect.width, rect.height,BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height,null);
            FileOutputStream out = new FileOutputStream(outFile + i + ".jpg"); // 输出到文件流
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag); // JPEG编码
            //String path = ServletActionContext.getServletContext().getRealPath(File.separator+"UploadFiles"+File.separator+"ReportTemplate");
            //WatermarkUtil.markImageByIcon(path+"/dzyz.png", outFile + i + ".jpg", outFile + i + ".jpg" , null);  
            //关闭输出流
            out.close();
            System.out.println("PDF文件转换JPG文件成功");
        }
        channel.close();
        raf.close();
        unmap(buf);
    }
	
    /**
     * 清空缓冲
     * @param buffer
     */
    public static void unmap(final Object buffer) {
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                try {
                    Method getCleanerMethod = buffer.getClass().getMethod("cleaner", new Class[0]);
                    getCleanerMethod.setAccessible(true);
                    sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod.invoke(buffer, new Object[0]);
                    cleaner.clean();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("清空缓冲成功");
                return null;
            }
        });
    }
	
	public static void main(String[] args) {
		 //源文件全路径  
        String docfile = "D:\\1.doc";
        //获取文件全名  带后缀
        String filename = null;
        //文件名  不带后缀
        String name = null;
        File file = new File(docfile);
        //获取文件名 带后缀
        filename = file.getName();
        //获取文件名  不带后缀
        name = filename.substring(0, filename.indexOf("."));
        //        System.out.println(name+"name");
        //用于存放图片的目录
        String outFile = "d://picture//" + name + "//";
        //如果目录不存在，就创建新的目录
        if (!new File(outFile).isDirectory()) {
            new File(outFile).mkdirs();
            System.out.println("新建上传临时文件夹");
        }
        //存放PDF的路径和PDF的文件名
        String toFile = "d:\\" + name;
        //实例化对象WordToJPG
        //WordToJPG wj = new WordToJPG();
        //将world文件转换为PDF文件   并返回PDF文件的全路径   17 表示文件格式为PDF  
        String filePath = WordToJPG.wordToPDF(docfile, toFile, 17);
        System.out.println(filePath + "===========================");
        try {
            //将PDF文件转换为JPG文件
            WordToJPG.pdfToJPG(filePath, outFile);
            //删除pdf文件
            //new FileManager().deleteFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
