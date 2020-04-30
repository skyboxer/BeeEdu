package com.enablue.common;

import com.enablue.dto.TemplateDTO;
import com.enablue.pojo.SubjectPool;
import com.enablue.pojo.TypePool;
import com.spire.doc.Document;

import java.util.ArrayList;
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
	public List<TemplateDTO> templateFormat(String text, SubjectPool subject, TypePool type){
		TemplateDTO templateDTO;

		String[] split = text.split("[1-99]\\)\\、");
		List<TemplateDTO> list=new ArrayList<>();
		for (int i=0;i<split.length;i++) {
			if (!"".equals(split[i])) {
				System.out.println("split = " + split[i]);
				templateDTO = new TemplateDTO();
				//显示在页面上的内容
				templateDTO.setSubject(subject.getSubjectName());
				templateDTO.setType(type.getPlateName());
				//要携带的数据
				templateDTO.setSubjectId(subject.getSubjectId());
				templateDTO.setTypeId(type.getPlateId());
				templateDTO.setDifficultyGrade(0);
				templateDTO.setTemplateContent(split[i]);
				list.add(templateDTO);
			}
		}
		return list;
	}
}
