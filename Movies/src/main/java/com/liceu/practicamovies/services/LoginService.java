package com.liceu.practicamovies.services;

import com.google.gson.Gson;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {
    @Value("${clientid}")
    String clientid;

    @Value("${redirecturi}")
    String redirecturi;

    @Value("${secret}")
    String secret;

    public URL getGoogleRedirectURL() throws Exception {

        URIBuilder builder = new URIBuilder("https://accounts.google.com/o/oauth2/v2/auth");
        builder.addParameter("scope","https://www.googleapis.com/auth/userinfo.email");
        builder.addParameter("access_type","offline");
        builder.addParameter("prompt","select_account");
        builder.addParameter("state","abcde");
        builder.addParameter("response_type","code");
        builder.addParameter("client_id",clientid);
        builder.addParameter("redirect_uri",redirecturi);
        return builder.build().toURL();
    }

    public String getAccessToken(String code) throws Exception {
        URL url = new URL("https://oauth2.googleapis.com/token");
        Map<String,String> parameters = new HashMap<>();
        parameters.put("client_id",clientid);
        parameters.put("client_secret",secret);
        parameters.put("grant_type","authorization_code");
        parameters.put("code",code);
        parameters.put("redirect_uri",redirecturi);
        String content = doPost(url,parameters);
        Map<String,Object> map = new Gson().fromJson(content,HashMap.class);
        return map.get("access_token").toString();

    }

    private String doPost(URL url, Map<String, String> parameters) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url.toString());
        List<NameValuePair> nvps = new ArrayList<>();
        for(String s : parameters.keySet()){
            nvps.add(new BasicNameValuePair(s,parameters.get(s)));
        }
        post.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            //System.out.println("Token: "+EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        }
        throw new RuntimeException("Error in response");
    }

    public Map<String, String> getUserDetails(String token) throws Exception{
        URIBuilder builder = new URIBuilder("https://www.googleapis.com/oauth2/v3/userinfo");
        builder.addParameter("access_token",token);
        builder.addParameter("alt","json");
        String resp = doGet(builder.build().toURL());
        Map<String,String> map = new Gson().fromJson(resp,HashMap.class);
        return map;
    }

    private String doGet(URL url) throws Exception{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url.toString());
        CloseableHttpResponse resp = client.execute(get);
        System.out.println(resp.getStatusLine());
        if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            return EntityUtils.toString(resp.getEntity());
        }
        throw new RuntimeException("Error in response");
    }
}
