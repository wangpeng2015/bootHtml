package com.boot.commons.utils;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClient {

    private static final int REQUEST_TIMEOUT = 600 * 1000;// 设置请求超时600秒钟

    private static final int SO_TIMEOUT = 600 * 1000; // 设置等待数据超时时间600秒钟

    private static BasicHttpParams httpParams = new BasicHttpParams();
    static {

        HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);

    }

    public static String doGet(String url, Map<String, Object> parametersMap) {
        return doGet(url, parametersMap, "GBK");
    }
    
    public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.connect();
			Map<String, List<String>> map = connection.getHeaderFields();
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

    public static String doGet(String url, Map<String, Object> parametersMap, String coding) {
        try {
            DefaultHttpClient client = new DefaultHttpClient(httpParams);
            client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            client.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, coding);
            client.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
            if (parametersMap != null) {
                StringBuffer parameters = new StringBuffer();
                int i = 0;
                for (String key : parametersMap.keySet()) {
                    if (i == 0) {
                        parameters.append("?");
                    } else {
                        parameters.append("&");
                    }
                    parameters.append(key);
                    parameters.append("=");
                    if (parametersMap.get(key) == null) {
                        continue;
                    }
                    parameters.append(encoding(parametersMap.get(key).toString(), coding));
                    i++;
                }
                url = url + parameters.toString();
            }

            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = client.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String returnStr = EntityUtils.toString(entity, coding);
                return returnStr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String doPost(String url, Map<String, Object> parametersMap) throws ClientProtocolException, IOException {
        DefaultHttpClient client = new DefaultHttpClient(httpParams);
        client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
        client.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "GBK");
        client.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);

        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> qparams = new ArrayList<NameValuePair>();
        if (parametersMap != null) {
            for (String key : parametersMap.keySet()) {
                qparams.add(new BasicNameValuePair(key, parametersMap.get(key).toString()));
            }
        }
        UrlEncodedFormEntity params = new UrlEncodedFormEntity(qparams, "GBK");
        httpPost.setEntity(params);
        HttpResponse response = client.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity entity = response.getEntity();
            String returnStr = EntityUtils.toString(entity, "GBK");
            return returnStr;
        }
        return null;
    }
    public static String doPostHp(String url, Map<String, Object> parametersMap) throws ClientProtocolException, IOException {
    	DefaultHttpClient client = new DefaultHttpClient(httpParams);
    	client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
    	client.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
    	client.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
    	
    	HttpPost httpPost = new HttpPost(url);
    	List<NameValuePair> qparams = new ArrayList<NameValuePair>();
    	if (parametersMap != null) {
    		for (String key : parametersMap.keySet()) {
    			qparams.add(new BasicNameValuePair(key, parametersMap.get(key).toString()));
    		}
    	}
    	UrlEncodedFormEntity params = new UrlEncodedFormEntity(qparams, "UTF-8");
    	httpPost.setEntity(params);
    	HttpResponse response = client.execute(httpPost);
    	if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
    		HttpEntity entity = response.getEntity();
    		String returnStr = EntityUtils.toString(entity, "GBK");
    		return returnStr;
    	}
    	return null;
    }

    public static InputStream get(String url, Map<String, Object> parametersMap) throws ClientProtocolException, IOException {
        DefaultHttpClient client = new DefaultHttpClient(httpParams);
        client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
        client.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "GBK");
        client.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
        if (parametersMap != null) {
            StringBuffer parameters = new StringBuffer();
            int i = 0;
            for (String key : parametersMap.keySet()) {
                if (i == 0) {
                    parameters.append("?");
                } else {
                    parameters.append("&");
                }
                parameters.append(key);
                parameters.append("=");
                parameters.append(encoding(parametersMap.get(key).toString(), "GBK"));
                i++;
            }
            url = url + parameters.toString();
        }

        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity entity = response.getEntity();
            return entity.getContent();
        }
        return null;
    }

    public static HttpEntity getEntity(String url, Map<String, Object> parametersMap) throws ClientProtocolException, IOException {
        DefaultHttpClient client = new DefaultHttpClient(httpParams);
        client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
        client.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "GBK");
        client.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
        if (parametersMap != null) {
            StringBuffer parameters = new StringBuffer();
            int i = 0;
            for (String key : parametersMap.keySet()) {
                if (i == 0) {
                    parameters.append("?");
                } else {
                    parameters.append("&");
                }
                parameters.append(key);
                parameters.append("=");
                parameters.append(encoding(parametersMap.get(key).toString(), "GBK"));
                i++;
            }
            url = url + parameters.toString();
        }

        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity entity = response.getEntity();
            return entity;
        }
        return null;
    }


    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();

    }

    private static String encoding(String content, String encoding) {
        if (content != null) {
            try {
                content = URLEncoder.encode(content, encoding);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /**
     * 图片转换成字节流
     * @param imgUrl
     * @return
     */
    public static InputStream readStreamFromImg(String imgUrl) {
        HttpURLConnection httpConnection = null;
        InputStream input = null;
        try {
            URL url = new URL(imgUrl);
            // 打开连接
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("User-Agent", "Internet Explorer");
            httpConnection.setRequestProperty("Connection", "Keep-Alive");
            httpConnection.setConnectTimeout(20 * 1000);
            input = httpConnection.getInputStream();
            return input;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (httpConnection != null) {
                try {
                    httpConnection.disconnect();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取图片的字节数组
     * @param imgUrl
     * @return
     */
    public static byte[] getImgStreamByte(String imgUrl) {
        HttpURLConnection httpConnection = null;
        InputStream input = null;
        ByteArrayOutputStream out = null;
        try {
            URL url = new URL(imgUrl);
            // 打开连接
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("User-Agent", "Internet Explorer");
            httpConnection.setRequestProperty("Connection", "Keep-Alive");
            httpConnection.setConnectTimeout(20 * 1000);
            input = httpConnection.getInputStream();
            out = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int length = -1;
            while ((length = input.read(b)) != -1) {
                out.write(b, 0, length);
            }
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (httpConnection != null) {
                try {
                    httpConnection.disconnect();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws ClientProtocolException, IOException {
        String res = HttpClient.doGet("http://192.168.105.122:8080/backstage/first_exview.jsp", null);
        System.out.println(res);
    }
    

   public static JSONObject httpPostWithJSON(String url, String json) throws Exception {
    	String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
    	//----1、
    	HttpPost request = new HttpPost(url);
    	// 绑定到请求 Entry
    	StringEntity se = new StringEntity(encoderJson);
    	request.setEntity(se);
    	request.setHeader("Content-Type", "application/json;charset=UTF-8");
    	// 发送请求
    	HttpResponse httpResponse = new DefaultHttpClient().execute(request);
    	// 得到应答的字符串，这也是一个 JSON 格式保存的数据
    	String retSrc = EntityUtils.toString(httpResponse.getEntity());
    	// 生成 JSON 对象
    	return JSONObject.fromObject(retSrc);
    }
	
}
