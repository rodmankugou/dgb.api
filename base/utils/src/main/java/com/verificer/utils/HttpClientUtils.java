package com.verificer.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * HttpClient工具类
 */
public class HttpClientUtils {

	// utf-8字符编码
	private static final String CHARSET_UTF_8 = "utf-8";

	// HTTP内容类型。
	private static final String CONTENT_TYPE_TEXT_HTML = "text/xml";

	// HTTP内容类型。相当于form表单的形式，提交数据
	public static final String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded";

	// HTTP内容类型。相当于form表单的形式，提交数据
	private static final String CONTENT_TYPE_JSON_URL = "application/json;charset=utf-8";

	public static enum HTTP_TYPE {GET, POST}

	// 连接管理器
	private static PoolingHttpClientConnectionManager pool;

	// 请求配置
	private static RequestConfig requestConfig;

	static {

		try {
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					builder.build());
			// 配置同时支持 HTTP 和 HTPPS
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register(
					"http", PlainConnectionSocketFactory.getSocketFactory()).register(
					"https", sslsf).build();
			// 初始化连接管理器
			pool = new PoolingHttpClientConnectionManager(
					socketFactoryRegistry);
			// 将最大连接数增加到200，实际项目最好从配置文件中读取这个值
			pool.setMaxTotal(200);
			// 设置最大路由
			pool.setDefaultMaxPerRoute(2);
			// 根据默认超时限制初始化requestConfig
			int socketTimeout = 10000;
			int connectTimeout = 10000;
			int connectionRequestTimeout = 10000;
			requestConfig = RequestConfig.custom().setConnectionRequestTimeout(
					connectionRequestTimeout).setSocketTimeout(socketTimeout).setConnectTimeout(
					connectTimeout).build();

			//System.out.println("初始化HttpClientTest~~~结束");
		} catch (NoSuchAlgorithmException e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		} catch (KeyStoreException e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		} catch (KeyManagementException e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}

		// 设置请求超时时间
		requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000)
				.setConnectionRequestTimeout(50000).build();
	}

	private static CloseableHttpClient getHttpClient() {

		CloseableHttpClient httpClient = HttpClients.custom()
				// 设置连接池管理
				.setConnectionManager(pool)
				// 设置请求配置
				.setDefaultRequestConfig(requestConfig)
				// 设置重试次数
				.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
				.build();

		return httpClient;
	}

	/**
	 * 发送 post请求
	 *
	 * @param httpUrl 地址
	 */
	public static String sendHttpPost(String httpUrl) {
		// 创建httpPost
		HttpPost httpPost = new HttpPost(httpUrl);
		return sendHttp(httpPost, HTTP_TYPE.POST);
	}

	/**
	 * 发送 get请求
	 *
	 * @param httpUrl 地址
	 */
	public static String sendHttpGet(String httpUrl) {
		// 创建get请求
		HttpGet httpGet = new HttpGet(httpUrl);
		return sendHttp(httpGet, HTTP_TYPE.GET);
	}


	/**
	 * 发送 post请求（带文件）
	 *
	 * @param httpUrl   地址
	 * @param maps      参数
	 * @param files 附件
	 */
	public static String sendHttpPost(String httpUrl, Map<String, String> maps, Map<String,List<File>> files) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
		if (maps != null) {
			for (String key : maps.keySet()) {
				meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
			}
		}
		if (files != null) {
			for(String key : files.keySet()){
				List<File> fileLists = files.get(key);
				if(fileLists != null){
					for (File file : fileLists) {
						FileBody fileBody = new FileBody(file);
						meBuilder.addPart(key, fileBody);
					}
				}
			}
		}
		HttpEntity reqEntity = meBuilder.build();
		httpPost.setEntity(reqEntity);
		return sendHttp(httpPost, HTTP_TYPE.POST);
	}



	/**
	 * 发送 post请求
	 *
	 * @param httpUrl 地址
	 * @param params  参数(格式:key1=value1&key2=value2)
	 */
	public static String sendHttpPostForm(String httpUrl, String params) {
		return postData(httpUrl, params, CONTENT_TYPE_FORM_URL);
	}

	/**
	 * 发送 post请求
	 *
	 * @param maps 参数
	 */
	public static String sendHttpPostMap(String httpUrl, Map<String, String> maps) {
		String parem = convertStringParamter(maps);
		return sendHttpPostForm(httpUrl, parem);
	}


	/**
	 * 发送 post请求 发送json数据
	 *
	 * @param httpUrl    地址
	 * @param paramsJson 参数(格式 json)
	 */
	public static String sendHttpPostJson(String httpUrl, String paramsJson) {
		return postData(httpUrl, paramsJson, CONTENT_TYPE_JSON_URL);
	}

	/**
	 * 发送 带Head的post JSON数据
	 *
	 * @param httpUrl    地址
	 * @param paramsJson 参数(格式 json)
	 */
	public static String sendHttpPostJson(String httpUrl, Map<String, String> headerMap, String paramsJson) {
		return postData(httpUrl, headerMap, paramsJson, CONTENT_TYPE_JSON_URL);
	}

	/**
	 * 发送 post请求 发送xml数据
	 *
	 * @param httpUrl   地址
	 * @param paramsXml 参数(格式 Xml)
	 */
	public static String sendHttpPostXml(String httpUrl, String paramsXml) {
		return postData(httpUrl, paramsXml, CONTENT_TYPE_TEXT_HTML);
	}

	/**
	 * 发送Post请求
	 *
	 * @param httpObject http对象
	 * @return 字符串
	 */
	private static String sendHttp(Object httpObject, HTTP_TYPE httpType) {

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		// 响应内容
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = getHttpClient();

			if (httpType == HTTP_TYPE.GET) {
				HttpGet httpGet = (HttpGet) httpObject;
				// 配置请求信息
				httpGet.setConfig(requestConfig);
				// 执行请求
				response = httpClient.execute(httpGet);
			} else {
				HttpPost httpPost = (HttpPost) httpObject;
				// 配置请求信息
				httpPost.setConfig(requestConfig);
				// 执行请求
				response = httpClient.execute(httpPost);
			}
			// 得到响应实例
			HttpEntity entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
			EntityUtils.consume(entity);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			try {
				// 释放资源
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				//e.printStackTrace();
				System.err.println(e.getMessage());
			}
		}

		return decodeUnicode(responseContent);
	}


	/**
	 * 发送Post请求
	 *
	 * @param httpObject http对象
	 * @return 字符串
	 */
	private static File sendHttp2(Object httpObject, HTTP_TYPE httpType) {

		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		// 响应内容
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = getHttpClient();

			if (httpType == HTTP_TYPE.GET) {
				HttpGet httpGet = (HttpGet) httpObject;
				// 配置请求信息
				httpGet.setConfig(requestConfig);
				// 执行请求
				response = httpClient.execute(httpGet);
			} else {
				HttpPost httpPost = (HttpPost) httpObject;
				// 配置请求信息
				httpPost.setConfig(requestConfig);
				// 执行请求
				response = httpClient.execute(httpPost);
			}
			// 得到响应实例
			HttpEntity entity = response.getEntity();
			InputStream stream = entity.getContent();

			File file = new File(UuidUtils.newUuid());
			SFileUtils.copyInputStreamToFile(stream,file.getPath());
			return file;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			try {
				// 释放资源
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				//e.printStackTrace();
				System.err.println(e.getMessage());
			}
		}

	}


	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
							case '0':
							case '1':
							case '2':
							case '3':
							case '4':
							case '5':
							case '6':
							case '7':
							case '8':
							case '9':
								value = (value << 4) + aChar - '0';
								break;
							case 'a':
							case 'b':
							case 'c':
							case 'd':
							case 'e':
							case 'f':
								value = (value << 4) + 10 + aChar - 'a';
								break;
							case 'A':
							case 'B':
							case 'C':
							case 'D':
							case 'E':
							case 'F':
								value = (value << 4) + 10 + aChar - 'A';
								break;
							default:
								throw new IllegalArgumentException(
										"Malformed   \\uxxxx   encoding.");
						}

					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	private static String postData(String httpUrl, Map<String, String> headerMap, String params, String contentType) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				httpPost.setHeader(entry.getKey(), entry.getValue());
			}
			if (params != null && params.trim().length() > 0) {
				StringEntity stringEntity = new StringEntity(params, "UTF-8");
				stringEntity.setContentType(contentType);
				httpPost.setEntity(stringEntity);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return sendHttp(httpPost, HTTP_TYPE.POST);
	}

	/**
	 * 发送数据
	 *
	 * @param httpUrl     地址
	 * @param params      参数
	 * @param contentType 类型
	 * @return 字符串
	 */
	private static String postData(String httpUrl, String params, String contentType) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			if (params != null && params.trim().length() > 0) {
				StringEntity stringEntity = new StringEntity(params, "UTF-8");
				stringEntity.setContentType(contentType);
				httpPost.setEntity(stringEntity);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return sendHttp(httpPost, HTTP_TYPE.POST);
	}

	/**
	 * 发送数据
	 *
	 * @param httpUrl     地址
	 * @param params      参数
	 * @param contentType 类型
	 * @return 字符串
	 */
	public static File postData2(String httpUrl, String params, String contentType) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		try {
			// 设置参数
			if (params != null && params.trim().length() > 0) {
				StringEntity stringEntity = new StringEntity(params, "UTF-8");
				stringEntity.setContentType(contentType);
				httpPost.setEntity(stringEntity);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new RuntimeException(e.getMessage(),e);
		}
		return sendHttp2(httpPost, HTTP_TYPE.POST);
	}

	/**
	 * 将map集合的键值对转化成：key1=value1&key2=value2 的形式
	 *
	 * @param parameterMap 需要转化的键值对集合
	 * @return 字符串
	 */
	private static String convertStringParamter(Map parameterMap) {
		StringBuffer parameterBuffer = new StringBuffer();
		if (parameterMap != null) {
			Iterator iterator = parameterMap.keySet().iterator();
			String key = null;
			String value = null;
			while (iterator.hasNext()) {
				key = (String) iterator.next();
				if (parameterMap.get(key) != null) {
					value = (String) parameterMap.get(key);
				} else {
					value = "";
				}
				parameterBuffer.append(key).append("=").append(value);
				if (iterator.hasNext()) {
					parameterBuffer.append("&");
				}
			}
		}
		return parameterBuffer.toString();
	}

	public static void main(String[] args) throws Exception {
		//String str = HttpClientUtils.sendHttpGet("http://api.map.baidu.com/location/ip?&ak=GEcf8Fp2A1XfbIbhGZ9ZlSQhzDLauEtY");
		//String str = HttpClientUtils.sendHttpGet("https://apis.map.qq.com/ws/location/v1/ip?ip=183.11.128.176&key=IMWBZ-MNCRK-53MJW-ALSTD-YNYEH-XXF2X");
//		String str = HttpClientUtils.sendHttpPost("http://127.0.0.1:5001/api/v0/cat?arg=QmagACXM7pukdukPSKvdNv6vscUMGFpVvdcmCZBZ76ZhJQ");
//		System.out.println(str);

		File file = postData2("http://127.0.0.1:5001/api/v0/cat?arg=QmYCYjSS9iH23jz1PSrXXUbEwj2AY5QBBjqkQ4tK9A1nVE","",CONTENT_TYPE_FORM_URL);
		System.out.println(file.getPath());


	}


}