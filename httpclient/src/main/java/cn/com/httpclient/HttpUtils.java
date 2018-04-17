package cn.com.httpclient;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;


/**
 * HTTP工具类
 * 
 * @author vetech
 * @version [版本号, 2017-3-24]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class HttpUtils {

	public static String sendHttp(String url, Map<String,String> map) throws Exception{

		NameValuePair[] params = new NameValuePair[map.size()];
		int i = 0;
		for(Map.Entry<String,String> entry:map.entrySet()){
			params[i++] = new NameValuePair(entry.getKey(), entry.getValue());
		}
		return HttpUtils.sendHttpClient(url, params);
	}


	/**
	 * POST发送URL 以UTF-8编码默认发送
	 *
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 *             [参数说明]
	 *
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String sendHttpClient(String url, NameValuePair[] params) throws Exception {
		return HttpUtils.sendHttpClient(url, params, null);
	}

	/**
	 * POST发送URL
	 *
	 * @param url
	 * @param params
	 * @param encode
	 *            中文编码
	 * @return
	 * @throws Exception
	 *             [参数说明]
	 *
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String sendHttpClient(String url, NameValuePair[] params, String encode) throws Exception {
		if (StringUtils.isBlank(encode)) {
			encode = "UTF-8";
		}
		Http http = Http.getInstance();
		HttpClient client = http.getClient();
		PostMethod method = new PostMethod(url);
		// method.setRequestBody(params);
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encode);
		method.addParameters(params);
		int statusCode = 0;
		String res = "";
		try {
			statusCode = client.executeMethod(method);
			byte[] responseBody = method.getResponseBody();
			res = StringUtils.trimToEmpty(new String(responseBody, encode));
		} catch (UnknownHostException e){
			//域名解析异常
			throw e;
		} catch (Exception e) {
			throw e;
		} finally {
			method.releaseConnection();
			client.getHttpConnectionManager().closeIdleConnections(0);
		}
		if (statusCode == HttpStatus.SC_OK) {
			return res;
		} else {
			throw new Exception("HTTP接收数据异常，状态码：" + statusCode + "错误内容:" + res);
		}
	}

	public static String sendHttp(String url,String json)throws Exception{
		byte[] params = json.getBytes("UTF-8");
		Map<String, String> header = new HashMap<>();
		header.put("Content-Type", "application/json");
		header.put("Accept", "application/json");
		byte[] resultByte = new byte[0];
		resultByte = HttpClientUtil.doPostStream(url, params, header, 30 * 1000, 30 * 1000);
		return new String(resultByte, "UTF-8");
	}
}

