package com.boot.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.DecimalFormat;
import java.util.List;

/**
 * 类名中文描述: 文件、目录相关操作 工具类
 *
 * 基本操作功能:
 *
 * Module ID  : srplatform-1.1 
 *
 * Create Date：2012-2-20 下午12:29:28
 * 
 * CopyRight  :  Copyright(C) 2008-xxxx  珠海政采软件技术有限公司
 * 
 * @since 0.1
 * @version: 0.1
 * @author <a href="mailto:kehl@gpcsoft.com">kehl</a>
 *
 * Change History Log
 * --------------------------------------------------------------------------------------------------------------
 * Date	      | Version | Author       | Description			              
 * --------------------------------------------------------------------------------------------------------------
 * 2012-2-20 | 0.1     | kehualin      | CREATE THE JAVA FILE: GpcFileUtils.java.
 * --------------------------------------------------------------------------------------------------------------
 *
 * --------------------------------------------------------------------------------------------------------------
 *
 *
 */
public class FileUtils
{
	private final Logger logger = LoggerFactory.getLogger(FileUtils.class);
	/**
	 * 获取文件后缀名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}
	/**
	 * ##################################写文件###################################################
	 */

	/**
	 * Description:  把字符串 content写入到指定路径
	 *
	 * Create Date: 2012-2-29上午10:28:43
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param fileFullPath  是全路径，包括文件名在内
	 * @param content
	 * @return
	 */
	public static boolean writeFile(String fileFullPath, String content)
	{
		PrintWriter pw = null;
		boolean flag = true;
		try
		{
			pw = new PrintWriter(new BufferedWriter(new FileWriter(fileFullPath)));
			pw.write(content);
			pw.close();
		}
		catch (IOException e)
		{
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Description: 将字节码保存为文件
	 *
	 * Create Date: 2012-2-29上午10:29:08
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param fileFullPath  是全路径，包括文件名在内
	 * @param content
	 * @return
	 */
	public static boolean writeFile(String fileFullPath, byte[] content)
	{
		PrintWriter pw = null;
		boolean flag = true;
		try
		{
			pw = new PrintWriter(new BufferedWriter(new FileWriter(fileFullPath)));
			pw.write(new String(content));
			pw.close();
		}
		catch (IOException e)
		{
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Description: 
	 *
	 * Create Date: 2012-2-29上午10:29:35
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param fileFullPath  是全路径，包括文件名在内
	 * @param inputStream
	 * @return
	 */
	public static boolean writeFile(String fileFullPath, InputStream inputStream)
	{
		boolean flag = true;

		String content = readFile(inputStream);

		flag = writeFile(fileFullPath, content);

		return flag;
	}

	/**
	 * Description: 
	 *
	 * Create Date: 2012-3-31下午05:26:39
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param path
	 * @param content
	 * @since 0.6
	 */
	@Deprecated
	public static void create(String path, String content)
	{
		try
		{
			rename(path, path + ".bak");
			RandomAccessFile newfile = new RandomAccessFile(path, "rw");
			newfile.writeBytes(content);
			newfile.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	/**
	 * ##################################读文件###################################################
	 */

	/**
	 * Description: 根据传入字符串生成路径 
	 *
	 * Create Date: 2012-3-31下午05:16:30
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param fileFullPath  表示路径字符串  
	 * @return
	 */
	public static final File getDirectory(String fileFullPath)
	{
		File file = new File(fileFullPath);
		if (file.canRead() && file.isDirectory())
			return file;
		else
			return null;
	}

	/**
	 * 列出指定路径下的所有目录或文件
	 *
	 * @param path 指定路径
	 * @param isDirectory :true为返回目录名数组,false返回文件名数组
	 * @return 返回目录名或文件名组成的数组
	 */
	private static final String[] list(String path, boolean isDirectory)
	{
		File directory = getDirectory(path);
		if (directory == null)
			return null;
		int count = 0;
		File files[] = directory.listFiles();
		String temp[] = new String[files.length];
		for (int i = 0; i < files.length; i++)
			if (files[i].isDirectory() == isDirectory)
			{
				temp[count] = files[i].getName();
				count++;
			}

		return CollectionUtils.cloneSubarray(temp, 0, count);
	}

	/**
	 * 列出指定路径下的所有目录
	 *
	 * @param path 指定路径
	 * @return 返回目录名组成的数组
	 */
	public static final String[] listDirectorys(String path)
	{
		return list(path, true);
	}

	/**
	 * 列出指定路径下的所有文件
	 *
	 * @param path 指定路径
	 * @return 返回文件名组成的数组
	 */
	public static final String[] listFiles(String path)
	{
		return list(path, false);
	}

	/**
	 * 列出指定路径下的所有目录和文件
	 *
	 * @param path 指定路径
	 * @return 返回目录名和文件名组成的数组
	 */
	public static final String[] list(String path)
	{
		File file = getDirectory(path);
		if (file != null)
		{
			String list[] = file.list();
			return list;
		}
		else
		{
			return null;
		}
	}

	/**
	 * Description: 
	 *
	 * Create Date: 2012-2-29上午10:30:24
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param fileFullPath
	 * @return
	 */
	public static String readFile(String fileFullPath)
	{
		String content = "";
		try
		{
			InputStream inputStream = new FileInputStream(fileFullPath);
			content = readFile(inputStream);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return content;
	}

	/**
	 * Description: 
	 *
	 * Create Date: 2012-2-29上午10:30:42
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param inputStream
	 * @return
	 */
	public static String readFile(InputStream inputStream)
	{
		String content = "";
		String temp = "";
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(inputStream)));

			while ((temp = reader.readLine()) != null)
			{
				content += temp;
			}

			inputStream.close();
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return content;
	}
	
	/**
	 * Description: 注意:和{@link #readFile(InputStream)} 不一样。
	 *
	 * Create Date: 2012-4-10下午04:42:34
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static String read(InputStream inputStream) throws IOException
	{
		InputStreamReader ins = new InputStreamReader(inputStream,"UTF-8");
		BufferedReader br = new BufferedReader(ins);
		String text  ="";
		StringBuffer sb = new StringBuffer();
		while ((text = br.readLine()) != null) {
			sb.append(text+"\n");
		}
		br.close();
		return sb.toString();
	}

	/**
	 * Description: 读取文件，返回字节数组
	 *
	 * Create Date: 2012-2-29上午10:31:07
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param fileFullPath
	 * @return
	 */
	public static byte[] readFile2(String fileFullPath)
	{
		return readFile(fileFullPath).getBytes();
	}

	/**
	 * ##################################其他###################################################
	 */

	/**
	 * Description: 取得文件大小
	 *
	 * Create Date: 2012-2-20上午11:55:26
	 * Author     : Administrator
	 * Modify Date: 
	 * Modify By  : 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static long getFileSize(File f) throws Exception
	{
		long s = 0;
		if (f.exists())
		{
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s = fis.available();
		}
		else
		{
			f.createNewFile();
//			logger.error("文件不存在");
		}
		return s;
	}

	/**
	 * Description: 递归,取得文件夹大小
	 *
	 * Create Date: 2012-2-20上午11:55:42
	 * Author     : Administrator
	 * Modify Date: 
	 * Modify By  : 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static long getDirSize(File f) throws Exception
	{
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++)
		{
			if (flist[i].isDirectory())
			{
				size = size + getDirSize(flist[i]);
			}
			else
			{
				size = size + flist[i].length();
			}
		}
		return size;
	}

	/**
	 * Description: 转换文件大小
	 *
	 * Create Date: 2012-2-20上午11:56:19
	 * Author     : Administrator
	 * Modify Date: 
	 * Modify By  : 
	 * @param fileSize
	 * @return
	 */
	public static String FormatFileSize(long fileSize)
	{
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileSize < 1024)
		{
			fileSizeString = df.format((double) fileSize) + "B";
		}
		else if (fileSize < 1048576)
		{
			fileSizeString = df.format((double) fileSize / 1024) + "K";
		}
		else if (fileSize < 1073741824)
		{
			fileSizeString = df.format((double) fileSize / 1048576) + "M";
		}
		else
		{
			fileSizeString = df.format((double) fileSize / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 * Description: 递归求取目录文件个数
	 *
	 * Create Date: 2012-2-20下午12:27:36
	 * Author     : Administrator
	 * Modify Date: 
	 * Modify By  : 
	 * @param f
	 * @return
	 */
	public static long getFilesCounter(File f)
	{
		long size = 0;
		File flist[] = f.listFiles();
		size = flist.length;
		for (int i = 0; i < flist.length; i++)
		{
			if (flist[i].isDirectory())
			{
				size = size + getFilesCounter(flist[i]);
				size--;
			}
		}
		return size;

	}

	/**
	 * Description: 
	 *
	 * Create Date: 2012-3-31下午05:21:14
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param orgPathName
	 * @param tarPathName
	 * @return
	 */
	public static final boolean rename(String orgPathName, String tarPathName)
	{
		boolean result = false;
		try
		{
			File file = new File(orgPathName);
			file.renameTo(new File(tarPathName));
			result = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Description: 
	 *
	 * Create Date: 2012-3-31下午05:21:30
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param path
	 * @return
	 */
	public static final boolean delete(String path)
	{
		try
		{
			File file = new File(path);
			file.delete();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Description: 
	 *
	 * Create Date: 2012-3-31下午05:21:52
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param source
	 * @param target
	 * @return
	 */
	public static final boolean copy(String source, String target)
	{
		boolean flag = false;
		byte abyte0[] = new byte[4096];
		try
		{
			File mvSourceFile = new File(source);
			if (mvSourceFile.exists())
			{
				FileOutputStream fileoutputstream = new FileOutputStream(target);
				FileInputStream fileinputstream = new FileInputStream(source);
				int i;
				while ((i = fileinputstream.read(abyte0)) != -1)
					fileoutputstream.write(abyte0, 0, i);
				fileinputstream.close();
				fileoutputstream.close();
			}
			mvSourceFile = null;
			flag = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Description: 
	 *
	 * Create Date: 2012-3-31下午05:22:06
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param path
	 * @return
	 */
	public static boolean mkdirs(String path)
	{
		try
		{
			File dir = new File(path);
			dir.mkdirs();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Description: 递归遍历子目录，与getListFiles函数以前使用
	 *
	 * Create Date: 2012-3-31下午05:23:32
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param f
	 * @param fileList
	 * @param suffix 
	 * @param isdepth
	 * @return
	 */
	public static List<String> listFile(File f, List<String> fileList, String suffix, boolean isdepth)
	{
		// 是目录，同时需要遍历子目录
		if (f.isDirectory() && isdepth == true)
		{
			File[] t = f.listFiles();
			for (int i = 0; i < t.length; i++)
			{
				listFile(t[i], fileList, suffix, isdepth);
			}
		}
		else
		{
			String filePath = f.getAbsolutePath();

			if (suffix != null)
			{
				int begIndex = filePath.lastIndexOf(".");// 最后一个.(即后缀名前面的.)的索引
				String tempsuffix = "";

				if (begIndex != -1)// 防止是文件但却没有后缀名结束的文件
				{
					tempsuffix = filePath.substring(begIndex + 1, filePath.length());
				}

				if (tempsuffix.equals(suffix))
				{
					fileList.add(filePath);
				}
			}
			else
			{
				// 后缀名为null则为所有文件
				fileList.add(filePath);
			}
		}
		return fileList;
	}

	/**
	 * Description: 
	 *
	 * Create Date: 2012-3-31下午05:24:14
	 * Author     : kevin
	 * Modify Date: 
	 * Modify By  : 
	 * @param path
	 * @param fileList
	 * @param suffix   后缀名
	 * @param isdepth  是否遍历子目录
	 */
	public static void getListFiles(String path, List<String> fileList, String suffix, boolean isdepth)
	{
		File file = new File(path);
		listFile(file, fileList, suffix, isdepth);
	}

	/**
	 * 判断文件或目录 是否存在
	 * @param fileName 文件全路径 或 目录
	 * @return
	 */
	public static boolean exists(String fileName)
	{
		File objFile = new File(fileName);
		if (objFile.exists())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**  
	 *  删除文件夹里面的所有文件  
	 *  @param  path  String  文件夹路径  如  c:/Myfile
	 */
	public static void delAllFile(String path)
	{
		File file = new File(path);
		if (!file.exists())
		{
			return;
		}
		if (!file.isDirectory())
		{
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++)
		{
			if (path.endsWith(File.separator))
			{
				temp = new File(path + tempList[i]);
			}
			else
			{
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile())
			{
				temp.delete();
			}
			if (temp.isDirectory())
			{
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
			}
		}
	}
	/**  
	 *  删除文件夹  
	 *  @return
	 */
	public static void delFolder(String folderPath)
	{
		delAllFile(folderPath); // 删除完里面所有内容
		String filePath = folderPath;
		filePath = filePath.toString();
		java.io.File myFilePath = new java.io.File(filePath);
		myFilePath.delete(); // 删除空文件夹
	}
	/**  
	   *  复制整个文件夹内容  
	   *  @param  oldPath  String  原文件路径  如：c:/Myfile  
	   *  @param  newPath  String  复制后路径  如：f:/Myfile/file 
	   *  @return  boolean  
	   */
	public static void copyFolder(String oldPath, String newPath) throws IOException
	{
		(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
		File a = new File(oldPath);
		String[] file = a.list();
		File temp = null;
		for (int i = 0; i < file.length; i++)
		{
			if (oldPath.endsWith(File.separator))
			{
				temp = new File(oldPath + file[i]);
			}
			else
			{
				temp = new File(oldPath + File.separator + file[i]);
			}

			if (temp.isFile())
			{
				FileInputStream input = new FileInputStream(temp);
				FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
				byte[] b = new byte[1024 * 5];
				int len;
				while ((len = input.read(b)) != -1)
				{
					output.write(b, 0, len);
				}
				output.flush();
				output.close();
				input.close();
			}
			if (temp.isDirectory())
			{// 如果是子文件夹
				copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
			}
		}
	}

	/**
	 * Description: 
	 *
	 * Create Date: 2012-2-20下午12:42:37
	 * Author     : Administrator
	 * Modify Date: 
	 * Modify By  : 
	 * @param args
	 */
	public static void main(String args[])
	{
		//testFileSize();
		String path="D:\\111\\12233.txt";
		boolean b = exists(path);
		
		System.out.println(b);
	}
	
	
	
	private static void testFileSize()
	{
		long startTime = System.currentTimeMillis();
		try
		{
			long l = 0;
			String path = "E:\\tomcat-5.5.33\\gpcsoftlogs\\David Guetta - If We Ever.mp3";
			File ff = new File(path);
			if (ff.isDirectory())
			{ // 如果路径是文件夹的时候
				System.out.println("文件个数 : " + getFilesCounter(ff));
				System.out.println("目录");
				l = getDirSize(ff);
				System.out.println(path + "目录的大小为：" + FormatFileSize(l));
			}
			else
			{
				System.out.println("文件个数1");
				System.out.println("文件");
				l = getFileSize(ff);
				System.out.println(path + "文件的大小为：" + FormatFileSize(l));
			}
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("总共花费时间为：" + (endTime - startTime) + "毫秒...");
	}

}
