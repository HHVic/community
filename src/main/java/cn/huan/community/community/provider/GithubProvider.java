package cn.huan.community.community.provider;

import cn.huan.community.community.dto.AccessToken;
import cn.huan.community.community.dto.GithubUser;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class GithubProvider {
    public String getAccessToken(AccessToken accessToken) {
        log.info("获取accessToken");
        MediaType JsonType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = OKHttpClientBuilder.buildOKHttpClient().build();

        log.info("获取OkHttpClient客户端");
        String json = JSON.toJSONString(accessToken);
        String url = "https://github.com/login/oauth/access_token";
        log.info("获取accessToken请求参数:{}",json);
        RequestBody body = RequestBody.create(json,JsonType);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            log.info("获取accessTokenq返回结果:{}",string);
            System.out.println(string);
            String token = string.split("&")[0].split("=")[1];
            response.close();
            return token;
        } catch (IOException e) {
            log.error("获取accessToken失败:{}",e.getMessage());
            e.printStackTrace();
        }finally {

        }
        return null;
        /*CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost("https://github.com/login/oauth/access_token");
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(2000) //服务器响应超时时间
                    .setConnectTimeout(2000) //连接服务器超时时间
                    .build();
            httpPost.setConfig(requestConfig);
            String json = JSON.toJSONString(accessToken);
            StringEntity entity = new StringEntity(json, "utf-8");//也可以直接使用JSONObject
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", "application/json;charset=utf8");
            // 由客户端执行(发送)请求
            response = httpClient.execute(httpPost);
            System.out.println("响应状态为:" + response.getStatusLine());
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
               // System.out.println("响应内容长度为:" + responseEntity.getContentLength());
               // System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
                String token = EntityUtils.toString(responseEntity).split("&")[0].split("=")[1];
                return token;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;*/
    }

    public GithubUser getUser(String accessToken) {
        log.info("通过accessToken:{}获取用户信息",accessToken);
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();

        OkHttpClient client = OKHttpClientBuilder.buildOKHttpClient().build();
        if(client == null){
            log.error("连接获取失败");
        }
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            log.info("通过accessToken:{}获取的用户:{}",accessToken,string);
            //System.out.println(string);
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        /*//使用Httpclient
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 定义请求的参数
        URI uri = null;
        try {
            uri = new URIBuilder("https://api.github.com/user")
                    .setParameter("access_token", accessToken)
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(uri);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(2000) //服务器响应超时时间
                .setConnectTimeout(2000) //连接服务器超时时间
                .build();
        httpGet.setConfig(requestConfig);
        //response 对象
        CloseableHttpResponse response = null;
        try {
            // 执行http get请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    GithubUser githubUser = JSON.parseObject(EntityUtils.toString(responseEntity), GithubUser.class);
                    return githubUser;
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;*/
    }

    /*public OkHttpClient getUnsafeOkHttpClient() {

        try {
            log.info("getUnsafeOkHttpClient 执行");
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final javax.net.ssl.SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);

            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            OkHttpClient build = builder.build();
            log.info("获取OkHttpClient");
            return build;
        } catch (Exception e) {
            log.info("获取失败:{}",e.getMessage());
            throw new RuntimeException(e);
        }

    }*/
}
