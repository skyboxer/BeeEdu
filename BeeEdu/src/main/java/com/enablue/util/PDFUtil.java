package com.enablue.util;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.graphics.PdfMargins;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

/***
 * PDF工具类
 * 王成
 */
public class PDFUtil {
    /**
     * 获取PDF文件总页码数
     * @param PDFfile
     * @return
     * @throws IOException
     */
    public static int getNumberOfPages(File PDFfile) throws IOException {
        return  PDDocument.load(PDFfile).getNumberOfPages();
    }

    /**
     * PDF转换Word
     * @param file
     * @throws IOException
     */
    public static void PdfToWord(File file) throws IOException {
        //获取文件前缀名
        String fileName = file.getName();
        String prefix = fileName.substring(0, fileName.lastIndexOf("."));
        System.out.println("prefix = " + prefix);

        PdfDocument pdf = new PdfDocument();
        //读取pdf文件
        pdf.loadFromFile(String.valueOf(file));
        //获取pdf文件总页数
        int pages = getNumberOfPages(file);
        //如果页数少于十直接转换word
        if (pages < 11){
            //保存为Word格式
            pdf.saveToFile(prefix+".docx", FileFormat.DOCX);
        }else {
            //pdf切割
            int count=0;
            for(int i = 0;i < pages;){
                PdfDocument tempPdf = new PdfDocument();
                PdfPageBase page;
                //
                for(int j = i;j<i+10;j++) {
                    page = tempPdf.getPages().add(pdf.getPages().get(j).getSize(), new PdfMargins(0));
                    pdf.getPages().get(j).createTemplate().draw(page, new Point2D.Float(0,0));
                }
                //保存为word文档
                tempPdf.saveToFile(prefix+" - "+count+".docx",FileFormat.DOCX);
                count++;
                i+=10;
                //如果剩余页数不足十页
                if (i+10>=pages){
                    for(int j = i;j<pages;j++) {
                        page = tempPdf.getPages().add(pdf.getPages().get(j).getSize(), new PdfMargins(0));
                        pdf.getPages().get(j).createTemplate().draw(page, new Point2D.Float(0,0));
                    }
                    //保存为word文档
                    tempPdf.saveToFile(prefix+" - "+count+".docx",FileFormat.DOCX);
                    return ;
                }
            }
        }

    }
}
