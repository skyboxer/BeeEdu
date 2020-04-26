package com.enablue.common;

import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  .doc .docx 文档读取工具类
 *
 */
public class PoiUtil {

	/**
	 * 读取word文件内容
	 *
	 * @param path
	 * @return buffer
	 */
	public String readWord(String path) {
		String buffer = "";
		FileInputStream is=null;
		try {
			if (path.endsWith(".doc")||path.endsWith(".docx")) {
				is = new FileInputStream(path);
				WordExtractor ex = new WordExtractor(is);
				buffer = ex.getText();
				is.close();
			} else {
				System.out.println("此文件不是word文件！");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer;
	}

	/**
	 * 把读取的文本分割成试题集合
	 * @param buffer
	 * @return
	 */
	public  List<String> plateFormat(String buffer){
		System.out.println("buffer = " + buffer);
		String[] plate = buffer.split("[\\u4e00-\\u9fa5]\\、 ");
		ArrayList<String> arrayList = new ArrayList<>();
		for (int i = 1; i < plate.length ; i++) {
			int index = plate[i].indexOf("\r\n");
			arrayList.add(plate[i].substring(0,index));

		}

		return arrayList;
	}
}
//		TemplatePool templatePool;
//		String[] split = buffer.split("[1-99]\\、 ");
//		List<TemplatePool> list=new ArrayList<>();
//		for (int i=1;i<split.length;i++) {
//			templatePool=new TemplatePool();
//			templatePool.setAnswerId(-1);
//			templatePool.setSubjectId(-1);
//			templatePool.setTypeId(-1);
//			templatePool.setDifficultyGrade(-1);
//			templatePool.setGmtCreate(new Date());
//			templatePool.setGmtModified(new Date());
//			if (i==1){
//				String[] strings = split[i].split("[\\u4e00-\\u9fa5]\\、");
//				templatePool.setTemplateContent(strings[0]);
//				list.add(templatePool);
//				System.out.println("strings = " + strings[0]);
//				continue;
//			}
//			System.out.println("split = " + split[i]);
//			templatePool.setTemplateContent(split[i]);
//			list.add(templatePool);
//		}
//		return list;