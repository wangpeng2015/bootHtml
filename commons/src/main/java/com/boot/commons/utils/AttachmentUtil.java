package com.boot.commons.utils;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

public class AttachmentUtil {
	
    /**
     * Description :  上传附件
     * Create Date: 2012-4-4下午02:41:41 by liaody
     * @param   fileInputName
     * @return  Attachment 附件对象
     * @Exception   
     */
    public static Attachment uploadFile(HttpServletRequest request, String fileInputName) throws Exception
    {
    	Assert.notNull(fileInputName);
    	if(!(request instanceof MultipartHttpServletRequest))
    		throw new IllegalArgumentException("附件不能为空！");
    	
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

    	CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile(fileInputName);		//取上传文件的临时文件
		if (file == null) {
    		throw new IllegalArgumentException("文件路径找不到！");
		}
		
		//获取可以上传的最大值，并判断文件是否大于最大值
		String maxSizeStr = request.getParameter("maxSize");
		String attId = request.getParameter("attId");
		
		String maxSizeUnit = Constant.FILE_MAX_SIZE_UITE_KB;
		if(StringUtils.hasText(maxSizeStr)) {
			maxSizeStr = getDefaultMaxSize();//默认最大
			maxSizeUnit = Constant.FILE_MAX_SIZE_UITE_M;
		} else {
			long maxSize = Long.valueOf(maxSizeStr);
			if(Constant.FILE_MAX_SIZE_UITE_M.equalsIgnoreCase(maxSizeUnit)) {
				if(file.getSize() > (maxSize*1024*1024))//单位M
					throw new IllegalArgumentException("最大只能上传"+maxSize+"MB的文件");
			} else {
				if(file.getSize() > (maxSize*1024))//单位K
					throw new IllegalArgumentException("最大只能上传"+maxSize+"KB的文件");
			}
		}
		
		//获得文件的显示名称，如果没有则使用上传时的文件名
		String viewName = request.getParameter("attachViewName");
		String filename = viewName==null||viewName.equals("")?file.getOriginalFilename():viewName;
		
		//获取文件后缀".xxx"
		 String postfix="";
		 if(!filename.equals(file.getOriginalFilename())){
             int lastDot = file.getOriginalFilename().lastIndexOf(".");
             if(lastDot != -1){
                 postfix = file.getOriginalFilename().substring(lastDot);
             }
		 }
		if (file.getSize() > 0) {
			try {
				String pathKey = StringUtils.hasText(request.getParameter("pathKey"))?request.getParameter("pathKey"):"defaultUrl";
				
				String post_fix = "";
	            int lastDot = file.getOriginalFilename().lastIndexOf(".");
	            if(lastDot != -1){
	            	post_fix = file.getOriginalFilename().substring(lastDot+1);
	            }
	            
	            String fileName = request.getParameter("fileName");//自定义的显示名称
				String saveName = StringUtils.hasText(fileName)?fileName:((StringUtils.hasText(post_fix)?post_fix.toUpperCase():"FJ") + "-" +
						DateUtil.getCurrDate(DateUtil.FORMAT_FOUR) + "-" + 
						StringUtils.getRandomNumberString(3) + "."+post_fix);//自动生成一个保存的文件名称
				
				String uploadPath="";
				if("defaultUrl".equals(pathKey)){
					uploadPath = getAttachmentUrl(pathKey);//取到上传的路径
				}else
					uploadPath = getAttachmentUrl(pathKey) + pathKey + "/";
				File distFile = new File((uploadPath  + "/" + saveName).replace("/", File.separator)); //存到磁盘
				//获取图片点击的跳转URL
				String attUrl = StringUtils.hasText(request.getParameter("attUrl"))?request.getParameter("attUrl"):"";
				
				// 判断磁盘是否存在它的父目录，如果没有父目录则先创建目录
				if (!distFile.getParentFile().exists()) {
					distFile.getParentFile().mkdirs(); 
				}
				
				//判断是否是图片文件
	            FileExt ext = FileExt.valueOf(post_fix.toLowerCase());
	            boolean isImage = ext.isImage();
	            
				//如果上传的是图片
				if(file.getContentType()!=null && file.getContentType().contains("image/") || isImage){
					// 获得临时文件的路径
					File tempFile = null;
					tempFile = ((DiskFileItem)file.getFileItem()).getStoreLocation();//得到文件服务器端的临时文件路径
					if(!tempFile.exists()){
						// 如果找不到临时文件，则手动创建一个临时文件
						saveFileByInputStream(file.getInputStream(), uploadPath, saveName+".tmp");
						tempFile  = new File(uploadPath+saveName+".tmp");
					}
					
					try{
						//保存文件图片（源文件）
						saveFileByInputStream(file.getInputStream(), uploadPath, saveName);
						
						//生成中等图标路径
						File mDistFile = new File((uploadPath  + "middle/" +  saveName).replace("/", File.separator));
						if (!mDistFile.getParentFile().exists()) {
							mDistFile.getParentFile().mkdirs(); 
						}
						//生成中等图标
						ImageScale.resizeFix(tempFile, mDistFile, Integer.parseInt(Constant.FILE_MIDDLE_WIDTH_SIZE), 
								Integer.parseInt(Constant.FILE_MIDDLE_HEIGHT_SIZE));
						
						//生成中等图标路径
						File sDistFile = new File((uploadPath  + "small/" +  saveName).replace("/", File.separator));
						if (!sDistFile.getParentFile().exists()) {
							sDistFile.getParentFile().mkdirs(); 
						}
						//生成小图标
						ImageScale.resizeFix(tempFile, sDistFile, Integer.parseInt(Constant.FILE_SMALL_WIDTH_SIZE), 
								Integer.parseInt(Constant.FILE_SMALL_HEIGHT_SIZE));
					}catch(Exception ce) {
						ce.printStackTrace();
						
						throw new IllegalArgumentException("图片读取不了！");
					}
					// 删除临时文件
					if(tempFile.exists()){
						tempFile.delete();
					}
				} else {
					//上传时非图片文件
					saveFileByInputStream(file.getInputStream(), uploadPath, saveName);
				}
				
				Attachment attachment = new Attachment();
				if(null != attUrl && !attUrl.equals("")){
					attachment.setAttUrl(attUrl);
				}
				if(StringUtils.hasText(request.getParameter("attachRelaId")))
					attachment.setRelationId(java.util.UUID.randomUUID().toString().replace('-', '_'));
				else{
					attachment.setRelationId(request.getParameter("attachRelaId"));
				}
				
				attachment.setFileSize(Long.valueOf(file.getSize()));
				attachment.setFileType(file.getContentType());
				attachment.setPath(pathKey);
				attachment.setViewName(filename + postfix);
				attachment.setFileName(saveName);
				return attachment;
			} catch (IOException e) {
	    		throw new IllegalArgumentException("附件上传失败！");
			}
		} else {
    		throw new IllegalArgumentException("附件不能为空！");
		}
    }
    
    /**
	 * Description: 获取上传文件的默认最大限制值
	 * Create Date: 2012-3-30下午02:50:49<br/>
	 * Modify Date: <br/>
	 * Modify By  : <br/>
	 * @return
	 */
	public static String getAttachmentUrl(String uploadPath) throws IOException{
		String realUploadPath = Constant.FILE_UPLOAD_ROOT_PATH;
		realUploadPath = realUploadPath.replace("/", File.separator);
		FileUtils.mkdirs(realUploadPath);
		return realUploadPath;	 
	}
	
	/**
	 * Description: 获取上传文件的默认最大限制值
	 * Create Date: 2012-3-30下午02:50:49<br/>
	 * Modify Date: <br/>
	 * Modify By  : <br/>
	 * @return
	 */
	public static String getDefaultMaxSize() {
		return Constant.FILE_UPLOAD_MAX_SIZE;
	}
	
	/**
	 * Description: 以流的形式保存文件
	 * Create Date: 2012-3-29下午04:59:26
	 * Author     : liaody
	 * Modify Date: 
	 * Modify By  : 
	 * @param 
	 */
	public static void saveFileByInputStream(InputStream stream, String path,String filename) throws IOException {
		FileOutputStream fs = new FileOutputStream(path + filename);
		byte[] buffer = new byte[1024 * 1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}
	
	/**
	 * Description: 附件的根路径
	 * Create Date: 2012-3-29下午04:59:26
	 * Author     : liaody
	 * Modify Date: 
	 * Modify By  : 
	 * @param 
	 */
	public static String getAttachmentRootPath(){
		return Constant.FILE_UPLOAD_ROOT_PATH;
	}
}