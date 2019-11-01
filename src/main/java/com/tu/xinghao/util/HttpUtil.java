package com.tu.xinghao.util;

import com.google.common.collect.Lists;
import com.tu.xinghao.constants.CommonConstant;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author: lixinghao
 * @date: 2019-10-17 00:44
 * @Description:
 */
public class HttpUtil {
    private static Logger logger = Logger.getLogger(HttpUtil.class);

    private static final String HTTP_TYPE = "http";

    public static InputStream doGet(String url) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return responseEntity.getContent();
            }
        } catch (IOException e) {
            logger.error("获取CloseableHttpResponse失败", e);
            return null;
        } finally {
            closeConnection(httpClient, response);
        }
        return null;
    }

    public static HttpEntity doPost(String url, Map<String, String> params) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse response = null;

        RequestConfig config = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(10000).build();
        post.setConfig(config);
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Host", CommonConstant.HOST);
        post.addHeader("Connection", "keep-alive");
        post.addHeader("Accept", CommonConstant.ACCEPT);

        List<NameValuePair> mapParam = buildPostMapParam(params);
        try {
            post.setEntity(new UrlEncodedFormEntity(mapParam, "UTF-8"));
            response = client.execute(post);

            if (Objects.isNull(response)) {
                logger.error("执行post方法，获得response失败");
                return null;
            }
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return response.getEntity();
            }
        } catch (IOException e) {
            logger.error("获取CloseableHttpResponse失败", e);
            return null;
        } finally {
            closeConnection(client, response);
        }

        return null;
    }

    /**
     * 将网页返回的内容封装为字符串
     *
     * @param in
     * @return
     */
    public static String outHtml(InputStream in) {
        if (Objects.isNull(in)) {
            logger.error("输入流为空");
            return null;
        }
        String result = "";

        try {
            StringBuilder stringBuilder = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            bufferedReader.close();

            result = new String(stringBuilder.toString().getBytes());
        } catch (IOException e) {
            logger.error("读取数据流数据错误", e);
        }

        return result;
    }

    /**
     * 关闭httpclient和httpresponse
     *
     * @param httpClient
     * @param response
     */
    private static void closeConnection(CloseableHttpClient httpClient, CloseableHttpResponse response) {
        if (Objects.isNull(httpClient)) {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Objects.isNull(response)) {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<NameValuePair> buildPostMapParam(Map<String, String> params) {
        List<NameValuePair> result = Lists.newArrayList();

        if (Objects.isNull(params)) {
            return result;
        }
        for (String key : params.keySet()) {
            if (Objects.isNull(key)) {
                continue;
            }
            result.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
        }
        logger.info("构造post参数为:" + result.toString());
        return result;
    }
}
