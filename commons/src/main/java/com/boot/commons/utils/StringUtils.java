package com.boot.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * 类名中文描述: 字符串工具类 ，继承了  {@link org.springframework.util.StringUtils}
 * 				在方法上扩展了 {@link org.apache.commons.lang.StringUtils} 上几个常用的方法
 * 				尽量使用本工具类中提供的方法。
 *
 * 基本操作功能:
 *
 * Create Date：2012-3-30 下午03:20:24
 * 
 * @since 0.1
 * @version: 0.1
 * @author liaody</a>
 *
 * </pre>
 */
public class StringUtils extends org.springframework.util.StringUtils
{
	private final static Logger logger = LoggerFactory.getLogger(StringUtils.class);

	private static String PREFIX = "\\u";
	private static final int fillchar = '=';
	private static final String cvt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789+/";

	// ************第一部分****************取得随机数****************************************
	/**
	 * Description: 产生指定位数的纯数字的随机数字符串
	 *
	 * Create Date: 2012-3-30下午05:30:57
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param length
	 * @return
	 */
	public static String getRandomNumberString(int length)
	{
		Random random = new Random();
		StringBuffer number = new StringBuffer();

		for (int i = 0; i < length; i++)
		{
			number.append(random.nextInt(10));
		}
		return number.toString();
	}

	/**
	 * Description: 产生指定位数的 随机数字符串(数字、字符混合)
	 * Create Date: 2011-11-26下午12:20:09
	 * Author     : Administrator
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length)
	{
		if (length < 1)
		{
			return "";
		}
		char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		char[] randBuffer = new char[length];

		Random randGen = new Random();
		for (int i = 0; i < randBuffer.length; i++)
		{
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	/**
	 * Description: 生成随机字符串，含有时间戳信息
	 * Create Date: 2011-11-26下午12:20:54
	 * Author     : Administrator
	 * @param length  要大于14
	 * @return
	 */
	public static String getRandomTimestampString(int length)
	{
		if (length < 14)
			return "";
		String defaultTimeStampPattern = "yyyyMMddHHmmss"; // 14位
		String s1 = DateUtil.getCurrDate(defaultTimeStampPattern);
		String s2 = getRandomString(length - 14);
		return s1 + s2;
	}

	// ************第二部分****************字母大小写转换****************************************
	/**
	 * Description: 对字符串的首字母转换。
	 * Create Date: 2011-11-26下午12:22:14
	 * Author     : Administrator
	 * @param str
	 * @param capitalize  true:首字母大写 ,false首字母小写。
	 * @return
	 */
	public static String changeFirstCharacterCase(String str, boolean capitalize)
	{
		if (!hasText(str))
		{
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length());
		if (capitalize)
		{
			sb.append(Character.toUpperCase(str.charAt(0)));
		}
		else
		{
			sb.append(Character.toLowerCase(str.charAt(0)));
		}
		sb.append(str.substring(1));
		return sb.toString();
	}

	/**
	 * Description: 将字符串的首字母转换为大写字母。
	 *
	 * Create Date: 2012-3-30下午05:26:10
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param str
	 * @return
	 */
	public static String toUpperFirstChar(String str)
	{
		return changeFirstCharacterCase(str, true);
	}

	/**
	 * Description: 将字符串的首字母转换为小写字母。
	 *
	 * Create Date: 2012-3-30下午05:26:24
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param str
	 * @return
	 */
	public static String toLowerFirstChar(String str)
	{
		return changeFirstCharacterCase(str, false);
	}

	/**
	 * Description: 单词之间用"_"连接 (单词的命名格式应该遵循驼峰式命名.如：ZhongHua-->zhong_hua)
	 * 
	 * Create Date: 2011-11-26下午12:24:20
	 * Author     : Administrator
	 * @param str
	 * @param capitalize
	 * @return
	 */
	public static String addUnderlineOfWord(String str)
	{
		str = changeFirstCharacterCase(str, false);
		String lines[] = new String[str.length()];
		String result = "";
		for (int i = 0; i < str.length(); i++)
		{
			if (Character.isUpperCase(str.charAt(i)))
			{
				lines[i] = "_";
			}
			else
			{
				lines[i] = "";
			}
		}
		for (int i = 0; i < str.length(); i++)
		{
			result += lines[i] + String.valueOf(str.charAt(i));
		}
		return result.toLowerCase();
	}

	/**
	 * Description: 
	 *
	 * Create Date: 2012-3-30下午05:32:31
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param str
	 * @param capitalize
	 * @return
	 */
	public static String changeFirstCharacterCaseAndUnderline(String str, boolean capitalize)
	{
		str = changeFirstCharacterCase(str, capitalize);
		for (int i = 0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			if (!Character.isLowerCase(c))
			{
				if (!capitalize)
				{
					c = Character.toLowerCase(c);
				}
				String begin = str.substring(0, i - 1);
				String end = str.substring(i + 1);
				str = begin + "_" + c + end;
			}
		}
		return str;
	}

	/**
	 * Description: 将字符串的首字母转换为小写字母并在其他大写字母前加"_"。
	 *
	 * Create Date: 2012-3-30下午05:30:01
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param str
	 * @return
	 */
	public static String toLowerFirstCharAndUnderline(String str)
	{
		return changeFirstCharacterCaseAndUnderline(str, false);
	}

	// ************第三部分****************汉字与汉语拼音转换****************************************

	/**
	 * Description: 把输入的字符串转换成汉语拼音全拼，如：北京--> BEIJING
	 *
	 * Create Date: 2012-3-30下午05:11:36
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param str  待转换的字符串
	 * @return     转换后的汉语拼音
	 */
//	public static String convertToPinYin(String str)
//	{
//		char[] charArray = str.toCharArray();
//		String[] t2 = new String[10];
//		String pinYin = "";
//		// 定义输出的格式
//		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
//		outputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
//		outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//		outputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
//
//		try
//		{
//			for (int i = 0; i < charArray.length; i++)
//			{
//				// 判断是否为汉字字符
//				if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+"))
//				{
//					t2 = PinyinHelper.toHanyuPinyinStringArray(charArray[i], outputFormat);
//					pinYin += t2[0];
//				}
//				else
//					pinYin += Character.toString(charArray[i]);
//			}
//		}
//		catch (BadHanyuPinyinOutputFormatCombination e1)
//		{
//			logger.error("转换汉语拼音出错", e1.getMessage());
//		}
//		return pinYin;
//	}

	/**
	 * Description: 把输入的字符串转换成汉语拼音的首字母组合,如：北京-->BJ
	 *
	 * Create Date: 2012-3-30下午05:15:33
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param str  待转换的字符串
	 * @return	        转换后的汉语拼音的首字母组合
	 */
//	public static String convertToPinYinFirst(String str)
//	{
//		char[] charArray = str.toCharArray();
//		String[] t2 = new String[10];
//		String pinYin = "";
//		// 定义输出的格式
//		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
//		outputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
//		outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//		outputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
//
//		try
//		{
//			for (int i = 0; i < charArray.length; i++)
//			{
//				// 判断是否为汉字字符
//				if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+"))
//				{
//					t2 = PinyinHelper.toHanyuPinyinStringArray(charArray[i], outputFormat);
//					pinYin += t2[0].substring(0, 1);
//				}
//				else
//					pinYin += Character.toString(charArray[i]);
//			}
//		}
//		catch (BadHanyuPinyinOutputFormatCombination e1)
//		{
//			e1.printStackTrace();
//		}
//		return pinYin;
//	}

	// ************第四部分****************编码解码****************************************

	/**
	 * Description: 将布尔值转换为字符串（转换规则：null or false 转换为"0" ;true 转换为  "1"）
	 * 
	 * Create Date: 2011-12-23上午10:41:35
	 * Author     : shengn
	 * Modify Date: 
	 * Modify By  : 
	 * @param b
	 * @return
	 */
	public static String transfromBooleanToString(Boolean b)
	{
		if (b == null)
			return "0";
		return b ? "1" : "0";
	}

	/**
	 * Description: 将data进行Base64编码
	 *
	 * Create Date: 2011-12-30下午4:37:26
	 * Author     : shengn
	 * Modify Date: 
	 * Modify By  : 
	 * @param data
	 * @return
	 */
	public static String encodeBase64(String data)
	{
		return encodeBase64(data.getBytes());
	}

	/**
	 * Description: 将data进行Base64编码
	 *
	 * Create Date: 2011-12-30下午4:38:35
	 * Author     : shengn
	 * Modify Date: 
	 * Modify By  : 
	 * @param data
	 * @return
	 */
	public static String encodeBase64(byte[] data)
	{
		int c;
		int len = data.length;
		StringBuffer ret = new StringBuffer(((len / 3) + 1) * 4);
		for (int i = 0; i < len; ++i)
		{
			c = (data[i] >> 2) & 0x3f;
			ret.append(cvt.charAt(c));
			c = (data[i] << 4) & 0x3f;
			if (++i < len)
				c |= (data[i] >> 4) & 0x0f;

			ret.append(cvt.charAt(c));
			if (i < len)
			{
				c = (data[i] << 2) & 0x3f;
				if (++i < len)
					c |= (data[i] >> 6) & 0x03;

				ret.append(cvt.charAt(c));
			}
			else
			{
				++i;
				ret.append((char) fillchar);
			}

			if (i < len)
			{
				c = data[i] & 0x3f;
				ret.append(cvt.charAt(c));
			}
			else
			{
				ret.append((char) fillchar);
			}
		}
		return ret.toString();
	}

	/**
	 * Description: 将data进行十六进制编码
	 *
	 * Create Date: 2011-12-30下午4:38:56
	 * Author     : shengn
	 * Modify Date: 
	 * Modify By  : 
	 * @param bytes
	 * @return
	 */
	public static final String encodeHex(byte[] bytes)
	{
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		int i;

		for (i = 0; i < bytes.length; i++)
		{
			if (((int) bytes[i] & 0xff) < 0x10)
			{
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}

	/**
	 * Description: 解码十六进制的字符串
	 *
	 * Create Date: 2011-12-30下午4:39:36
	 * Author     : shengn
	 * Modify Date: 
	 * Modify By  : 
	 * @param hex
	 * @return
	 */
	public static final byte[] decodeHex(String hex)
	{
		char[] chars = hex.toCharArray();
		byte[] bytes = new byte[chars.length / 2];
		int byteCount = 0;
		for (int i = 0; i < chars.length; i += 2)
		{
			byte newByte = 0x00;
			newByte |= hexCharToByte(chars[i]);
			newByte <<= 4;
			newByte |= hexCharToByte(chars[i + 1]);
			bytes[byteCount] = newByte;
			byteCount++;
		}
		return bytes;
	}

	/**
	 * Description:返回字符对应的十六进制字节码 
	 *
	 * Create Date: 2011-12-30下午4:40:23
	 * Author     : shengn
	 * Modify Date: 
	 * Modify By  : 
	 * @param ch
	 * @return
	 */
	private static final byte hexCharToByte(char ch)
	{
		switch (ch)
		{
		case '0':
			return 0x00;
		case '1':
			return 0x01;
		case '2':
			return 0x02;
		case '3':
			return 0x03;
		case '4':
			return 0x04;
		case '5':
			return 0x05;
		case '6':
			return 0x06;
		case '7':
			return 0x07;
		case '8':
			return 0x08;
		case '9':
			return 0x09;
		case 'a':
			return 0x0A;
		case 'b':
			return 0x0B;
		case 'c':
			return 0x0C;
		case 'd':
			return 0x0D;
		case 'e':
			return 0x0E;
		case 'f':
			return 0x0F;
		}
		return 0x00;
	}

	/**
	 * Description: 解码Base64字符串
	 *
	 * Create Date: 2011-12-30下午4:41:21
	 * Author     : shengn
	 * Modify Date: 
	 * Modify By  : 
	 * @param data
	 * @return
	 */
	public static String decodeBase64(String data)
	{
		return decodeBase64(data.getBytes());
	}

	/**
	 * Description: 解码Base64字节数据
	 *
	 * Create Date: 2011-12-30下午4:41:52
	 * Author     : shengn
	 * Modify Date: 
	 * Modify By  : 
	 * @param data
	 * @return
	 */
	public static String decodeBase64(byte[] data)
	{
		int c, c1;
		int len = data.length;
		StringBuffer ret = new StringBuffer((len * 3) / 4);
		for (int i = 0; i < len; ++i)
		{
			c = cvt.indexOf(data[i]);
			++i;
			c1 = cvt.indexOf(data[i]);
			c = ((c << 2) | ((c1 >> 4) & 0x3));
			ret.append((char) c);
			if (++i < len)
			{
				c = data[i];
				if (fillchar == c)
					break;

				c = cvt.indexOf((char) c);
				c1 = ((c1 << 4) & 0xf0) | ((c >> 2) & 0xf);
				ret.append((char) c1);
			}

			if (++i < len)
			{
				c1 = data[i];
				if (fillchar == c1)
					break;

				c1 = cvt.indexOf((char) c1);
				c = ((c << 6) & 0xc0) | c1;
				ret.append((char) c);
			}
		}
		return ret.toString();
	}

	/**
	 * Description :字符串转换为ASCII码  
	 * Create Date: 2010-12-6上午10:31:47 by yucy  Modified Date: 2010-12-6上午10:31:47 by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	public static String stringToASCII(String s)
	{// 字符串转换为ASCII码
		String result = "";
		for (int i = 0; i < s.length(); i++)
		{
			int chr1 = (char) s.charAt(i);
			if (chr1 >= 19968 && chr1 <= 171941)
			{// 汉字范围 \u4e00-\u9fa5 (中文)
				result += "\\u" + Integer.toHexString(chr1);
			}
			else
			{
				result += s.charAt(i);
			}
		}
		return result;
	}
	/** 
	 * Description :  ascii2转中文
	 * Create Date: 2010-11-18下午09:13:28 by liangxj  Modified Date: 2010-11-18下午09:13:28 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static String asciiToNative(String str)
	{
		if (str == null)
		{
			str = "";
		}
		StringBuilder sb = new StringBuilder();
		int begin = 0;
		int index = str.indexOf(PREFIX);
		while (index != -1)
		{

			sb.append(str.substring(begin, index));

			sb.append(asciiToChar(str.substring(index, index + 6)));

			begin = index + 6;

			index = str.indexOf(PREFIX, begin);
		}

		sb.append(str.substring(begin));
		return sb.toString();
	}

	/** 
	 * Description :  
	 * Create Date: 2010-11-18下午09:13:48 by liangxj  Modified Date: 2010-11-18下午09:13:48 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private static char asciiToChar(String str)
	{
		if (str.length() != 6)
		{
			throw new IllegalArgumentException("Ascii string of a native character must be 6 character.");
		}

		if (!PREFIX.equals(str.substring(0, 2)))
		{
			throw new IllegalArgumentException("Ascii string of a native character must start with \" \\u\".");
		}
		String tmp = str.substring(2, 4);
		int code = Integer.parseInt(tmp, 16) << 8;
		tmp = str.substring(4, 6);
		code += Integer.parseInt(tmp, 16);
		return (char) code;
	}

	// ************第五部分***********字符串内容判断、操作*********************************************
	/**
	 * Description: 判断字符串是否包含内容
	 * Create Date: 2011-11-26下午12:15:29
	 * Author     : Administrator
	 * @param str
	 * @return
	 */
	public static boolean hasText(String str)
	{
		if (org.apache.commons.lang.StringUtils.isEmpty(str))
			return false;
		str = str.replaceAll(" ", "");
		if ("null".equalsIgnoreCase(str.trim()))
			return false;
		return org.apache.commons.lang.StringUtils.isNotBlank(str);
	}

	public static boolean isBlank(String source)
	{
		return !hasText(source);
	}

	/**
	 * Description: 获取str中separator 以后的字符串
	 * Create Date: 2011-11-26下午12:22:02
	 * Author     : Administrator
	 * @param str
	 * @param tag
	 * @return
	 */
	public static String substringAfter(String str, String separator)
	{
		return org.apache.commons.lang.StringUtils.substringAfter(str, separator);
	}

	/**
	 * Description: 
	 *
	 * Create Date: 2012-3-31上午09:48:25
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param str
	 * @param separator
	 * @return
	 */
	public static String substringBefore(String str, String separator)
	{
		return org.apache.commons.lang.StringUtils.substringBefore(str, separator);
	}

	/**
	 * Description: 把字符串转换成以引号包含的字符串,如： 北京,上海-->'北京','上海'
	 *
	 * Create Date: 2012-3-30下午05:19:33
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param obj   字符串，以逗号分割
	 * @return		携带单引号，以逗号分割
	 */
	public static String convertToQuoteString(String obj)
	{
		if (null != obj)
		{
			String[] s = obj.split(",");
			StringBuffer sb = new StringBuffer();
			for (String string : s)
			{
				sb.append("'").append(string).append("',");
			}
			return sb.substring(0, sb.lastIndexOf(","));
		}
		return null;
	}

	// ************第五部分********集合转换成字符串************************************************

	/**
	 * Description: 把对象数组转换成      以指定字符       分隔每个字符的字符串
	 *
	 * Create Date: 2011-12-30下午3:41:16
	 * Author     : shengn
	 * Modify Date: 
	 * Modify By  : 
	 * @param objs 待转换的对象数组
	 * @return
	 */
	public static String convertArrayToString(Object[] objs, char separator)
	{
		return org.apache.commons.lang.StringUtils.join(objs, separator);
	}

	/**
	 * Description: 把对象数组转换成用逗号分隔每个字符的字符串
	 *
	 * Create Date: 2012-3-30下午05:44:16
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param array
	 * @return
	 */
	public static String convertArrayToString(String[] array)
	{
		return convertArrayToString(array, ',');
	}

	// ************第6部分********************************************************

	public static Boolean string2Boolean(String param)
	{
		Assert.notNull(param, "参数不能为空.");
		if ("1".equalsIgnoreCase(param.trim()) || "true".equalsIgnoreCase(param.trim().toLowerCase()))
		{
			return true;
		}
		return false;
	}

	// ************第7部分******************其它**************************************
	/**
	 * Description: 返回指定长度的字符串，位数不足指定长度时，前面补零
	 *
	 * Create Date: 2012-3-30下午05:22:32
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param sourceCode
	 * @param length
	 * @return
	 */
	public static String constructCode(int sourceCode, int length)
	{
		String targetCode = String.valueOf(sourceCode);
		int oldLength = targetCode.length();

		for (int i = length; i > oldLength; i--)
		{
			targetCode = "0" + targetCode;
		}

		return targetCode;
	}

	/**
	 * Description: 验证是否是     非负整数 
	 *
	 * Create Date: 2012-3-31下午05:39:16
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param strNumber
	 * @return
	 */
	public static boolean isNumeric(String strNumber)
	{
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(strNumber);
		if (!isNum.matches())
		{
			return false;
		}
		return true;
	}

	/**
	 * Description:  匹配请求路径
	 *
	 * Create Date: 2012-3-30下午05:45:40
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param pattern
	 * @param str
	 * @return
	 */
	public static boolean matchStrings(String pattern, String str)
	{
		char[] patArr = pattern.toCharArray();
		char[] strArr = str.toCharArray();
		int patIdxStart = 0;
		int patIdxEnd = patArr.length - 1;
		int strIdxStart = 0;
		int strIdxEnd = strArr.length - 1;
		char ch;

		boolean containsStar = false;
		for (int i = 0; i < patArr.length; i++)
		{
			if (patArr[i] == '*')
			{
				containsStar = true;
				break;
			}
		}

		if (!containsStar)
		{
			// No '*'s, so we make a shortcut
			if (patIdxEnd != strIdxEnd)
			{
				return false; // Pattern and string do not have the same size
			}
			for (int i = 0; i <= patIdxEnd; i++)
			{
				ch = patArr[i];
				if (ch != '?')
				{
					if (ch != strArr[i])
					{
						return false;// Character mismatch
					}
				}
			}
			return true; // String matches against pattern
		}

		if (patIdxEnd == 0)
		{
			return true; // Pattern contains only '*', which matches anything
		}

		// Process characters before first star
		while ((ch = patArr[patIdxStart]) != '*' && strIdxStart <= strIdxEnd)
		{
			if (ch != '?')
			{
				if (ch != strArr[strIdxStart])
				{
					return false;// Character mismatch
				}
			}
			patIdxStart++;
			strIdxStart++;
		}
		if (strIdxStart > strIdxEnd)
		{
			// All characters in the string are used. Check if only '*'s are
			// left in the pattern. If so, we succeeded. Otherwise failure.
			for (int i = patIdxStart; i <= patIdxEnd; i++)
			{
				if (patArr[i] != '*')
				{
					return false;
				}
			}
			return true;
		}

		// Process characters after last star
		while ((ch = patArr[patIdxEnd]) != '*' && strIdxStart <= strIdxEnd)
		{
			if (ch != '?')
			{
				if (ch != strArr[strIdxEnd])
				{
					return false;// Character mismatch
				}
			}
			patIdxEnd--;
			strIdxEnd--;
		}
		if (strIdxStart > strIdxEnd)
		{
			// All characters in the string are used. Check if only '*'s are
			// left in the pattern. If so, we succeeded. Otherwise failure.
			for (int i = patIdxStart; i <= patIdxEnd; i++)
			{
				if (patArr[i] != '*')
				{
					return false;
				}
			}
			return true;
		}

		// process pattern between stars. padIdxStart and patIdxEnd point
		// always to a '*'.
		while (patIdxStart != patIdxEnd && strIdxStart <= strIdxEnd)
		{
			int patIdxTmp = -1;
			for (int i = patIdxStart + 1; i <= patIdxEnd; i++)
			{
				if (patArr[i] == '*')
				{
					patIdxTmp = i;
					break;
				}
			}
			if (patIdxTmp == patIdxStart + 1)
			{
				// Two stars next to each other, skip the first one.
				patIdxStart++;
				continue;
			}
			// Find the pattern between padIdxStart & padIdxTmp in str between
			// strIdxStart & strIdxEnd
			int patLength = (patIdxTmp - patIdxStart - 1);
			int strLength = (strIdxEnd - strIdxStart + 1);
			int foundIdx = -1;
			strLoop: for (int i = 0; i <= strLength - patLength; i++)
			{
				for (int j = 0; j < patLength; j++)
				{
					ch = patArr[patIdxStart + j + 1];
					if (ch != '?')
					{
						if (ch != strArr[strIdxStart + i + j])
						{
							continue strLoop;
						}
					}
				}

				foundIdx = strIdxStart + i;
				break;
			}

			if (foundIdx == -1)
			{
				return false;
			}

			patIdxStart = patIdxTmp;
			strIdxStart = foundIdx + patLength;
		}

		// All characters in the string are used. Check if only '*'s are left
		// in the pattern. If so, we succeeded. Otherwise failure.
		for (int i = patIdxStart; i <= patIdxEnd; i++)
		{
			if (patArr[i] != '*')
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * Description: 
	 *
	 * Create Date: 2012-4-1下午05:59:58
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param name
	 * @return
	 */
	public static boolean isValidatedName(String name)
	{
		// 关键字列表
		String[] keyWords = { "abstract", "default", "if", "private", "this", "boolean", "do", "implements", "protected", "throw", "break", "double", "import", "public", "throws", "byte", "else",
				"instanceof", "return", "transient", "case", "extends", "int", "short", "try", "catch", "final", "interface", "static", "void", "char", "finally", "long", "strictfp", "volatile",
				"class", "float", "native", "super", "while", "const", "for", "new", "switch", "continue", "goto", "package", "synchronized", "string", "null" };

		List<String> list = Arrays.asList(keyWords);
		if (list.contains(name.toLowerCase()))
		{
			return true;
		}
		return false;
	}

	public static String getSimpleEntityName(String name)
	{
		// TODO
		return name;
	}

	/**
	 * Description: 
	 *
	 * Create Date: 2012-3-30下午05:46:38
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param args
	 */
	public static void main(String[] args)
	{
		// String s1 = "aa";
		// String s2 = constructCode(1234, 6);
		// String[] arr = new String[] { "11", "22", "33" };
		// System.out.println(isNumeric("54543543"));
	}
}