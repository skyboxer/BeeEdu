package com.enablue.test;
 
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
 
 
/**
 * 类 MsWordExtractor用来提取Microsoft Word 里面的文字和图片
 * 注意提取图片后，可以把图片放在由用户指定的路径下面
 *
 * @author Zhou Xiaolong
 * @email shaolongchou@126.com
 */
 
public class DocUtil {
 
    private HWPFDocument doc = null;
 
    private Range range = null;
 
    private static List<Picture> pictsList = null;
 
    // 用来标记是否存在图片
    boolean hasPic = false;
 
    /**
     * 构造器，注意到所传入的参数必须是微软word文档的名字
     *
     * @param msDocName
     * @throws IOException
     * @throws FileNotFoundException
     */
    public DocUtil(String msDocName) {
        try {
            doc = new HWPFDocument(new FileInputStream(msDocName));
            range = doc.getRange();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    /**
     * 默认构造器，为私有函数
     */
    private DocUtil() {
 
    }
 
    /**
     * 从word文档中获取所有文字
     *
     * @return
     */
    public String getAllText() {
        int numP = range.numParagraphs();
        StringBuffer ret = new StringBuffer();
        for (int i = 0; i < numP; ++i) {
            //从每一段落中获取文字
            Paragraph p = range.getParagraph(i);
            ret.append(p.text());
        }
        return ret.toString();
    }
 
    /**
     * 从word里面提取图片
     *
     * @return
     */
    private boolean extractPictures() {
        pictsList = new ArrayList();
        // 得到文档的数据流
        byte[] dataStream = doc.getDataStream();
        int numChar = range.numCharacterRuns();
 
        PicturesTable pTable = new PicturesTable(doc, dataStream, new byte[1024]);
        for (int j = 0; j < numChar; ++j) {
            CharacterRun cRun = range.getCharacterRun(j);
            // 是否有图片
            boolean has = pTable.hasPicture(cRun);
            if (has) {
                Picture picture = pTable.extractPicture(cRun, true);
                // 大于300bites的图片我们才弄下来，消除word中莫名的小图片的影响
                if (picture.getSize() > 300) {
                    pictsList.add(picture);
                    hasPic = true;
                }
            }
        }
        return hasPic;
    }
 
    /**
     * word文档里有几张图片，使用这个函数之前，
     * 必须先使用函数 extractPictures()
     *
     * @return
     */
    public int numPictures() {
        if (!hasPic)
            return 0;
        return pictsList.size();
    }
 
    /**
     * 把提取的图片保存到用户指定的位置
     *
     * @param picNames， 图片要保存的路径,最好完整地写上图片类型
     * @return
     */
    private boolean writePictures(String[] picNames, String savePath) {
        int size = pictsList.size();
        if (size == 0)
            return false;
 
        for (int i = 0; i < size; ++i) {
            Picture p = pictsList.get(i);
            try {
                p.writeImageContent(new FileOutputStream(savePath + "/" + picNames[i]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
 
    // maven太好用了
    // 读取srcFile源word文件docx文字
    // 读取srcFile源word文件docx中的image图片并且存放在文件夹imageFile中
    private static String readDocxImage(String srcFile, String imageFile) {
        String path = srcFile;
        File file = new File(path);
        try {
            // 用XWPFWordExtractor来获取文字
            FileInputStream fis = new FileInputStream(file);
            XWPFDocument document = new XWPFDocument(fis);
            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(document);
            String text = xwpfWordExtractor.getText();
 
            System.out.println(text);
            // 用XWPFDocument的getAllPictures来获取所有的图片
            List<XWPFPictureData> picList = document.getAllPictures();
            for (XWPFPictureData pic : picList) {
                byte[] bytev = pic.getData();
                // 大于300bites的图片我们才弄下来，消除word中莫名的小图片的影响
                if (bytev.length > 300) {
                    FileOutputStream fos = new FileOutputStream(imageFile + "/" + pic.getFileName());
                    fos.write(bytev);
                }
            }
            fis.close();
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    private static void readDocImage(String file, String savePath) {
        DocUtil extr = new DocUtil(file);
        String str = extr.getAllText().trim();
        extr.extractPictures();
        int num = extr.numPictures();
        String names[] = new String[num];
        for (int i = 0; i < num; ++i) {
            String imageType = pictsList.get(i).getMimeType().split("/")[1];
            names[i] = "image" + i + "." + imageType;
        }
        System.out.println(str);
        extr.writePictures(names, savePath);
    }
 
    public static void readWordImage(String file, String savePath) {
        if (null == savePath) {
            savePath = "";
        }
        if (file.endsWith(".doc")) {
            readDocImage(file, savePath);
        } else if (file.endsWith(".docx")) {
            readDocxImage(file, savePath);
        } else {
            return;
        }
    }
 
    public static void main(String[] args) {
        readWordImage("C:\\Users\\Administrator\\Desktop\\高二数学下学期期末复习题5(www.diyifanwen.com).doc", "C:\\Users\\Administrator\\Desktop\\images");
    }
 
}