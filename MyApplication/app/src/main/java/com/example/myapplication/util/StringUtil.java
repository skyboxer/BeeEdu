package com.example.myapplication.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static String addZeroForString(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength){
                str = "0" + str;// 左补0
                strLen = str.length();
            }
        }
        return str;
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || input.trim().length() == 0 || input.equals("null"))
            return true;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotEmpty(String input) {
        return !isEmpty(input);
    }

    /**
     * @param s    原始字符串
     * @param tag  分隔符
     * @param each 按几位分割
     * @return
     */
    public static String divide(String s, String tag, int each) {
        StringBuilder buf = new StringBuilder();
        int length = s.length();
        int start = 0;
        int end = length / each == 0 ? length : each;
        while (start < end) {
            buf.append(s.substring(start, end));
            buf.append(tag);
            start += each;
            if (end + each <= length) {
                end += each;
            } else {
                end = length;
            }
        }
        if (buf.toString().contains(tag)) {
            buf.deleteCharAt(buf.toString().lastIndexOf(tag));
        }
        return buf.toString();
    }

    /**
     * 将list数组转换为String，使用tag连接
     *
     * @param list
     * @param tag
     * @return
     */
    public static String convert(List<String> list, String tag) {
        StringBuilder buffer = new StringBuilder();
        for (String s : list) {
            buffer.append(s);
            buffer.append(tag);
        }
        if (buffer.toString().contains(tag)) {
            buffer.deleteCharAt(buffer.lastIndexOf(tag));
        }
        return buffer.toString();
    }

    /**
     * is mobile phone
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {

        String telRegex = "^[1-9]\\d{10}$";

        return mobiles.matches(telRegex);
    }

    /**
     * 验证码验证（6位数）
     *
     * @param code
     * @return
     */
    public static boolean isSixValidateCode(String code) {
        String telRegex = "\\d{6}";
        return code.matches(telRegex);
    }

    /**
     * 验证码验证（4位数）
     *
     * @param code
     * @return
     */
    public static boolean isFourValidateCode(String code) {
        String telRegex = "\\d{4}";
        return code.matches(telRegex);
    }

    /**
     * 校验密码（6-12位数字和字母的组合）
     *
     * @param code
     * @return
     */
    public static boolean isPassWord(String code) {
        String telRegex = "[0-9A-Za-z]{6,12}";
        boolean isMix = code.matches(telRegex);
        boolean isResult = false;
        if (isMix) {
            Pattern isLetter = Pattern.compile("[A-Za-z]");
            if (isLetter.matcher(code).find()) {
                Pattern isNumber = Pattern.compile("[0-9]");
                isResult = isNumber.matcher(code).find();
            }

        }
        return isResult;
    }


    /**
     * 验证券号
     *
     * @param code
     * @return
     */
    public static boolean isCouponCode(String code) {
        String telRegex = "\\d{8,20}";
        return code.matches(telRegex);
    }

    /**
     * 身份码验证
     *
     * @param code
     * @return
     */
    public static boolean isCustomerCode(String code) {
        String telRegex = "\\d{6,20}";
        return code.matches(telRegex);
    }

    public static boolean isBeforeToday(String birthdy) {
        long longtime1 = new Date().getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = format.format(longtime1);
        int resultDate = currentDate.compareTo(birthdy);
        return resultDate > 0;
    }

    public static boolean isTableNo(String tableNo) {
        String telRegex = "^[a-zA-Z0-9]+$";
        return tableNo.matches(telRegex);
    }

    public static boolean isUserName(String userName) {
        String userRegex = "^[a-zA-Z\\u4E00-\\u9FA5]{1,10}";
        return userName.matches(userRegex);
    }

    /**
     * 判断是否为url
     **/
    public static boolean isUrl(String str) {
        // 转换为小写
        str = str.toLowerCase();
        String[] regex = {"http://", "https://"};
        boolean isUrl = false;
        for (String s : regex) {
            if (str.contains(s)) {
                isUrl = true;
            }
        }
        return isUrl;
    }

    /**
     * 该方法主要使用正则表达式来判断字符串中是否包含字母
     *
     * @param cardNum 待检验的原始卡号
     * @return 返回是否包含
     * @author fenggaopan 2015年7月21日 上午9:49:40
     */
    public static boolean judgeContainsStr(String cardNum) {
        String regex = ".*[a-zA-Z]+.*";
        Matcher m = Pattern.compile(regex).matcher(cardNum);
        return m.matches();
    }


    public static String isEmptyShow(String str) {
        if(StringUtil.isEmpty(str))
            return "";
        else
            return str;
    }


    public static String objectRetention(Object d) {
        return String.format("%.2f", d);
    }

    public static String objectRetention1(Object d) {
        return String.format("%.1f", d);
    }

    /**
     * 字符串转换为16进制字符串
     **/
    public static String stringToHexString(String s) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = s.getBytes();
        int bit;
        for (byte b : bs) {
            bit = (b & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = b & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString();
    }

    /**
     * @param src 16进制字符串
     * @return 字节数组
     * @throws
     * @Title:hexString2String
     * @Description:16进制字符串转字符串
     */
    public static String hexString2String(String src) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < src.length() / 2; i++) {
            temp.append((char) Integer.valueOf(src.substring(i * 2, i * 2 + 2),
                    16).byteValue());
        }
        return temp.toString();
    }

    public static String getDistance(double distance) {

        if (distance < 1000) {


            return objectRetention1(distance) + "m";
        }

        return objectRetention1(distance / 1000) + "km";


    }

    public static double roundDouble(double dou, int i) {
        double d = 0;
        try {
            double factor = Math.pow(10, i);
            d = Math.floor(dou * factor + 0.5) / factor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }


}