package com.lyd.itshequ.commponent;

import com.alibaba.fastjson.JSON;
import com.lyd.itshequ.bean.AccessTokenDTO;
import com.lyd.itshequ.bean.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName GithubProvider
 * @Description TODO
 * @Author Liuyunda
 * @Date 2020/2/21 18:39
 **/
@Component
public class GithubProvider {
	/**
	 * 使用okhttp根据传过来的accessTokenDTO对象，向https://github.com/login/oauth/access_token发送post请求获得token
	 *
	 * @param accessTokenDTO
	 * @return
	 */
	public String getAccessToken(AccessTokenDTO accessTokenDTO) {
		MediaType mediaType = MediaType.get("application/json; charset=utf-8");
		OkHttpClient client = new OkHttpClient();
		// JSON.toJSONString(accessTokenDTO);
		RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
		Request request = new Request.Builder().url("https://github.com/login/oauth/access_token").post(body).build();
		try (Response response = client.newCall(request).execute()) {
			String string = response.body().string();
			System.out.println(string);//access_token=c231d1e359bed82f6f98b2a2d86eb916478b56d1&scope=user&token_type=bearer
			String token = string.split("&")[0].split("=")[1];
			return token;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 使用okhttp根据传过来的accessToken向https://api.github.com/user发送请求获得user
	 *
	 * @param accessToken
	 * @return
	 */
	public GithubUser getUser(String accessToken) {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("https://api.github.com/user?access_token=" + accessToken)
				.build();
		try (
				Response response = client.newCall(request).execute()) {
			String string = response.body().string();
			return JSON.parseObject(string, GithubUser.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
