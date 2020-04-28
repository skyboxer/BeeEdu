package com.enablue.common;

import com.enablue.pojo.TemplatePool;
import com.spire.doc.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
		if (path.endsWith(".doc")||path.endsWith(".docx")) {
			Document document = new Document(path);
			buffer = document.getText();
		} else {
			System.out.println("此文件不是word文件！");
		}
		return buffer;
	}

	/**
	 * 把读取的文本格式化城类型和试题内容
	 * @param buffer
	 * @return
	 */
	public HashMap<String,Object> plateFormat(String buffer){
		String[] plate = buffer.split("[一二三四五六七八九十](\\、|\\.)");
		HashMap<String,Object> map = new HashMap<>();
		for (int i = 1; i < plate.length ; i++) {
			//截取出题目类型
			int index = plate[i].indexOf("\r\n");
			String substring = plate[i].substring(0, index);
			substring = substring.replaceAll("[^\u4E00-\u9FA5]", "");
			//截取类型下所有题目
			String context = plate[i].substring(index + 2);
			//封装到map
			map.put(substring,context);
		}
		return map;
	}
	/**
	 * 把读取的文本格式化成试题
	 * @param text
	 * @return
	 */
	public List<TemplatePool> templateFormat(String text,Integer subjectId,Integer typeId){
		TemplatePool templatePool;

		String[] split = text.split("[1-99](\\、|\\.) ");
		List<TemplatePool> list=new ArrayList<>();
		for (int i=0;i<split.length;i++) {
			if (!"".equals(split[i])) {
				System.out.println("split = " + split[i]);
				templatePool = new TemplatePool();
				templatePool.setAnswerId(-1);
				templatePool.setSubjectId(subjectId);
				templatePool.setTypeId(typeId);
				templatePool.setDifficultyGrade(0);
				templatePool.setGmtCreate(new Date());
				templatePool.setGetModified(new Date());
				templatePool.setTemplateContent(split[i]);
				list.add(templatePool);
			}
		}
		return list;
	}
}
