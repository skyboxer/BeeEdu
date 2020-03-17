package com.enablue.util;

import com.alibaba.fastjson.JSONObject;
import com.enablue.common.RecursiveEquation;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.*;
import java.util.*;

public class DocUtil {
    /*public static void main(String[] args) {
        String [] nameArray1 = new String[]{"${oneone}","${onetwo}",
                "${onethree}","${onefour}"};
        String [] nameArray2 = new String[]{"${twoone}","${twotwo}",
                "${twothree}","${twofour}","${twofive}","${twosix}"};
        List<JSONObject> list = new ArrayList<>();
        JSONObject jsonObject = null;
        jsonObject = new JSONObject();
        jsonObject.put("name","${title}");
        jsonObject.put("value","三年级算法测试");
        list.add(jsonObject);
        //创建竖式运算对象
        RecursiveEquation recursiveEquation = new RecursiveEquation();
        //生成竖式运算
        list.addAll(recursiveEquation.generativeExpression(nameArray1));

        Algorithm algorithm = new Algorithm();
        List<JSONObject> list2 = algorithm.recursiveComputation(nameArray2);

        String path = "src/main/webapp/TemplateDoc/title.doc";
        try {
            File file = new File(path);
            System.out.println(file.getCanonicalPath());
            String filePath = file.getCanonicalPath();
            InputStream is = new FileInputStream(filePath);
            OutputStream outputStream = null;
            HWPFDocument doc = null;
            doc= new HWPFDocument(is);
            Range range = doc.getRange();
            for(JSONObject jsonObject1 : list){
                range.replaceText(jsonObject1.getString("name"),jsonObject1.getString("value"));
            }
            for (JSONObject jsonObject2 :list2){
                range.replaceText(jsonObject2.getString("name"),jsonObject2.getString("value"));
            }
            //  /home/cnxjk/下载/new.doc
            outputStream = new FileOutputStream(new File("D:\\BeeEdu\\SmartQuestionBank\\src\\main\\webapp\\OutputTemplate\\new.doc"));
            doc.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static void searchAndReplace(String srcPath, String destPath,
                                        Map<String, String> map) {
        try {
            XWPFDocument document = new XWPFDocument(POIXMLDocument
                    .openPackage(srcPath));
            Iterator it = document.getTablesIterator();
            while (it.hasNext()) {
                XWPFTable table = (XWPFTable) it.next();
                int rcount = table.getNumberOfRows();
                for (int i = 0; i < rcount; i++) {
                    XWPFTableRow row = table.getRow(i);
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        for (Map.Entry<String, String> e : map.entrySet()) {
                            String key = e.getKey();
                            String text = cell.getText();
                            String value = e.getValue();
                            System.out.println(text);
                            System.out.println(key);
                            System.out.println(value);
                            if (cell.getText().equals(e.getKey())) {
                                cell.removeParagraph(0);
                                cell.setText(e.getValue());
                            }
                        }
                    }
                }
            }
            FileOutputStream outStream = null;
            outStream = new FileOutputStream(destPath);
            document.write(outStream);
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            String[] s=new String[20];
            FileInputStream in=new FileInputStream("/home/cnxjk/workspace/BeeEdu/SmartQuestionBank/src/main/webapp/TemplateDoc/title.doc");
            POIFSFileSystem pfs=new POIFSFileSystem(in);
            HWPFDocument hwpf=new HWPFDocument(pfs);
            Range range =hwpf.getRange();
            TableIterator it=new TableIterator(range);
            int index=0;
            while(it.hasNext()){
                Table tb=(Table)it.next();
                for(int i=0;i<tb.numRows();i++){
                    //System.out.println("Numrows :"+tb.numRows());
                    TableRow tr=tb.getRow(i);
                    for(int j=0;j<tr.numCells();j++){
                        //System.out.println("numCells :"+tr.numCells());
//						System.out.println("j   :"+j);
                        TableCell td=tr.getCell(j);
                        for(int k=0;k<td.numParagraphs();k++){
                            //System.out.println("numParagraphs :"+td.numParagraphs());
                            Paragraph para=td.getParagraph(k);
                            s[index]=para.text().trim();
                            index++;
                        }
                    }
                }
            }
//			System.out.println(s.toString());
            for(int i=0;i<s.length;i++){
                System.out.println(s[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}