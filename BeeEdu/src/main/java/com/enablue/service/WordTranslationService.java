package com.enablue.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cnxjk
 * word 文档翻译
 */
public interface WordTranslationService {

    /**
     * google翻译
     * @param from
     * @param to
     * @param text
     * @return 文件路径
     */
    public StringBuffer googleTreansl(String from, String to, String text);

    /**
     * 百度翻译
     * @param from
     * @param to
     * @param text
     * @param req
     * @return
     */
    public StringBuffer baiduTransl(String from, String to, String text,String engineType, HttpServletRequest req);

    /**
     * 讯飞翻译
     * @param from
     * @param to
     * @param text
     * @param req
     * @return 文件路径
     */
    public StringBuffer  xunfeiTransl(String from, String to, String text, String engineType,HttpServletRequest req);

}
