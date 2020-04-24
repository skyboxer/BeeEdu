package com.enablue.common;

import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.FileInputStream;
import java.io.IOException;

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
}