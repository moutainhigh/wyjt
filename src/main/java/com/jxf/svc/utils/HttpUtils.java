package com.jxf.svc.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpUtils {
	 
    /**
     * get
     *
     * @param host
     * @param path
     *
     * @param headers
     * @param querys
     * @return
     * @throws Exception
     */
    public static HttpResponse doGet(String url,
                                     Map<String, String> headers,
                                     Map<String, String> querys)
            throws Exception {
        HttpClient httpClient = wrapClient(url);
        HttpGet request = new HttpGet(buildUrl(url, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        return httpClient.execute(request);
    }
 
    /**
     * post form
     *
     * @param host
     * @param path
     *
     * @param headers
     * @param querys
     * @param bodys
     * @return
     * @throws Exception
     */
    public static String doPost(String url,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      Map<String, String> bodys)
            throws Exception {
        HttpClient httpClient = wrapClient(url);
        HttpPost request = new HttpPost(buildUrl(url, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        if (bodys != null) {
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
 
            for (String key : bodys.keySet()) {
                nameValuePairList.add(new BasicNameValuePair(key, bodys.get(key)));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");
            formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
            request.setEntity(formEntity);
        }
        HttpResponse response = httpClient.execute(request);
        if (response != null) {
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				return EntityUtils.toString(resEntity, "utf-8");
			}
		}
        return null;
    }
 
    /**
     * Post String
     *
     * @param url
     *
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static String doPost(String url,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      String body)
            throws Exception {
        HttpClient httpClient = wrapClient(url);
        HttpPost request = new HttpPost(buildUrl(url, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        if (StringUtils.isNotBlank(body)) {
            request.setEntity(new StringEntity(body, "utf-8"));
        }
        HttpResponse response = httpClient.execute(request);
        if (response != null) {
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				return EntityUtils.toString(resEntity, "utf-8");
			}
		}
        return null;
    }
    
    /**
     * Post String
     *
     * @param url
     *
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static String doPost(String url,String body)
            throws Exception {
        HttpClient httpClient = wrapClient(url);
        HttpPost request = new HttpPost(url);
        if (StringUtils.isNotBlank(body)) {
            request.setEntity(new StringEntity(body, "utf-8"));
        }
        HttpResponse response = httpClient.execute(request);
        if (response != null) {
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				return EntityUtils.toString(resEntity, "utf-8");
			}
		}
        return null;
    }
    /**
     * Post String
     *
     * @param url
     *
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static String doPostForWithdraw(String url,String body)
            throws Exception {
        HttpClient httpClient = wrapClient(url);
        HttpPost request = new HttpPost(url);
        if (StringUtils.isNotBlank(body)) {
        	StringEntity s = new StringEntity(body, "utf-8");
        	s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
            request.setEntity(s);
        }
        HttpResponse response = httpClient.execute(request);
        if (response != null) {
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				JSONObject json = getJson(response);
				return json.toJSONString();
			}
		}
        return null;
    }
    /**
     * Post stream
     *
     * @param host
     * @param path
     *
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String url,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      byte[] body)
            throws Exception {
        HttpClient httpClient = wrapClient(url);
        HttpPost request = new HttpPost(buildUrl(url, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }
        return httpClient.execute(request);
    }
 
    /**
     * Put String
     * @param host
     * @param path
     *
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPut(String url,
                                     Map<String, String> headers,
                                     Map<String, String> querys,
                                     String body)
            throws Exception {
        HttpClient httpClient = wrapClient(url);
        HttpPut request = new HttpPut(buildUrl(url, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        if (StringUtils.isNotBlank(body)) {
            request.setEntity(new StringEntity(body, "utf-8"));
        }
        return httpClient.execute(request);
    }
 
    /**
     * Put stream
     * @param host
     * @param path
     *
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPut(String url,
                                     Map<String, String> headers,
                                     Map<String, String> querys,
                                     byte[] body)
            throws Exception {
        HttpClient httpClient = wrapClient(url);
        HttpPut request = new HttpPut(buildUrl(url, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }
        return httpClient.execute(request);
    }
 
    /**
     * Delete
     *
     * @param host
     * @param path
     *
     * @param headers
     * @param querys
     * @return
     * @throws Exception
     */
    public static HttpResponse doDelete(String url, 
                                        Map<String, String> headers,
                                        Map<String, String> querys)
            throws Exception {
        HttpClient httpClient = wrapClient(url);
        HttpDelete request = new HttpDelete(buildUrl(url, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        return httpClient.execute(request);
    }
 
    /**
     * 构建请求的 url
     * @param host
     * @param path
     * @param querys
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String buildUrl(String url, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(url);
        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }
                if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (!StringUtils.isBlank(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (!StringUtils.isBlank(query.getValue())) {
                        sbQuery.append("=");
                        sbQuery.append(URLEncoder.encode(query.getValue(), "utf-8"));
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }
        return sbUrl.toString();
    }
 
    /**
     * 获取 HttpClient
     * @param host
     * @param path
     * @return
     */
    private static HttpClient wrapClient(String url) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        if (url != null && url.startsWith("https://")) {
            return sslClient();
        }
        return httpClient;
    }
 
    /**
     * 在调用SSL之前需要重写验证方法，取消检测SSL
     * 创建ConnectionManager，添加Connection配置信息
     * @return HttpClient 支持https
     */
    private static HttpClient sslClient() {
        try {
            // 在调用SSL之前需要重写验证方法，取消检测SSL
            X509TrustManager trustManager = new X509TrustManager() {
                @Override public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                @Override public void checkClientTrusted(X509Certificate[] xcs, String str) {}
                @Override public void checkServerTrusted(X509Certificate[] xcs, String str) {}
            };
            SSLContext ctx = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
            ctx.init(null, new TrustManager[] { trustManager }, null);
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
            // 创建Registry
            RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                    .setExpectContinueEnabled(Boolean.TRUE).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM,AuthSchemes.DIGEST))
                    .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https",socketFactory).build();
            // 创建ConnectionManager，添加Connection配置信息
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            CloseableHttpClient closeableHttpClient = HttpClients.custom().setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(requestConfig).build();
            return closeableHttpClient;
        } catch (KeyManagementException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
 
    /**
     * 将结果转换成JSONObject
     * @param httpResponse
     * @return
     * @throws IOException
     */
    public static JSONObject getJson(HttpResponse httpResponse) throws IOException {
        HttpEntity entity = httpResponse.getEntity();
        String resp = EntityUtils.toString(entity, "UTF-8");
        EntityUtils.consume(entity);
        return JSON.parseObject(resp);
    }
}
