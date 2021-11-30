package com.example.springboottest.provider;



import com.alibaba.fastjson.JSON;
import com.example.springboottest.dto.AccessTokenDto;
import com.example.springboottest.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDto accessTokenDto){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                .url("https://gitee.com/oauth/token?grant_type=authorization_code&code="+accessTokenDto.getCode()+
                        "&client_id="+accessTokenDto.getClient_id()+"&redirect_uri="+accessTokenDto.getRedirect_uri()+
                        "&client_secret="+accessTokenDto.getClient_secret())
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String str = response.body().string();
            //String token=str.split("&")[0].split("=")[1];
            String token1=str.split(":")[1];
            String token=token1.split("\"")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public GithubUser getUser(String accessToken){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url("https://gitee.com/api/v5/user?access_token="+accessToken)
                //.header("Authorization","token "+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string=response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return null;
    }
}
