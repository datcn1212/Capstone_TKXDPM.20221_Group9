package util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

/**
 * @author hdb
 *
 */
@Slf4j
public class HTTPBinder {
	/**
	 * client use for connecting to URL
	 */
	private final OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10000, TimeUnit.MILLISECONDS)
			.readTimeout(10000, TimeUnit.MILLISECONDS).retryOnConnectionFailure(true).build();

	/**
	 * patch a message
	 * @param url
	 * @param body
	 * @return
	 */
	public String patch(String url, String body) {
		try {
			RequestBody requestBody = RequestBody
					.create(MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_VALUE), body);
			Request request = new Request.Builder().url(url).patch(requestBody).build();
			Response response = client.newCall(request).execute();
			assert response.body() != null;
			JSONObject returnJson = new JSONObject(response.body().string());
			return returnJson.getString("errorCode");
		} catch (Exception e) {
			System.out.println("Exception at utils.HTTPConnector.sendPatch");
		}
		return null;
	}

	/**
	 * post a message
	 * @param url
	 * @param body
	 * @return
	 */
	public String post(String url, String body) {
		try {
			RequestBody requestBody = RequestBody
					.create(MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_VALUE), body);
			Request request = new Request.Builder().url(url).post(requestBody).build();
			Response response = client.newCall(request).execute();
			assert response.body() != null;
			JSONObject returnJson = new JSONObject(response.body().string());
			return returnJson.getString("id");
		} catch (Exception e) {
			System.out.println("Exception at HTTPConnector.post");
		}
		return null;
	}
}
