package com.boot.commons.utils;

public interface Constant {
	
	//203.130.41.104:8060, localhost:8080，182.92.189.127:8080
	
	/****************分页对象 使用常量*****************/
	public static final int DEFAULT_PAGE_SIZE = 10;
	public static final int DEFAULT_PAGE_SIZE2 = 10;//默认每页的记录数 ，可设置。
	public static final int DEFAULT_PAGE_NO = 1;//当前页码，默认从1开始
	public static final int TOTAL_ROW_COUNT = 0;//总记录数
	public static final int TOTAL_PAGE_COUNT = 0;//总的页数
	public static final int START_OF_ROWNO = 0;//从第几条开始查询
	
	 public static final String DOWN_LOAD_PATH = "";
	 public static final String UPDATE_LOAD_PATH = "";
	 
	 public static final Integer APP_CODE = 1;//Android
	 public static final String APP_PATH = "/appFile/";//app文件上传路径
	 
	 public static final String RANDOM_KEY = "1zqb5WAD7FNf4EFD";
	 public static final String KEY = "1zqa2WSX3def4VRF";
	 
	 public static final String ERROR_AUTH_FAILE="您没有访问权限";
	 
	 public static final String FILE_UPLOAD_ROOT_PATH="/var/www/resources/static/xishi/";
//	 public static final String FILE_UPLOAD_ROOT_PATH="D://attachment1/";
	 public final static String FILE_UPLOAD_MAX_SIZE = "10";
	 public final static String FILE_MAX_SIZE_UITE_KB = "KB";
	 public final static String FILE_MAX_SIZE_UITE_M = "M";
	 public final static String FILE_MIDDLE_WIDTH_SIZE = "200";
	 public final static String FILE_MIDDLE_HEIGHT_SIZE = "200";
	 public final static String FILE_SMALL_WIDTH_SIZE = "100";
	 public final static String FILE_SMALL_HEIGHT_SIZE = "100";

    /* redis key */
    /* APP cache_key start */
	public static final String CACHE_GUIZI_USER_DETAIL = "guizi:diaochan:user:detail:";
	public static final String CACHE_GUIZI_USER = "guizi:diaochan:user:";
	public static final String GUIZI_USER_COOKIE_NAME = "cookie_guizi_user";
	public static final String GUIZI_USER_COOKIE_VALUE = "cookie_guizi_user_";
	public static final String REQUEST_USER = "guizi_user";
	public static final String DIAOCHAN_USER_AUTH_MENU = "guizi:diaochan:user:auth:menu:";
	public static final String DIAOCHAN_ORDER_COMPANY = "guizi:diaochan:order:company:";
	
	
	public static final String DIAOCHAN_USER_AUTH_LOCK = "guizi:diaochan:user:lock:";
    /* APP cache_key end */
	
	/*存放图片链接的服务器地址*/
	public static final String FILESERVER_URL="http://203.130.41.105/xishi/product/";
}
