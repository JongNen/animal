package util;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

import data.address.AddressDocument;
import data.address.AddressResponseResult;

/*
 * 주소에 해당하는 상세값들을 얻어올때 사용할 API
 */
public class AddressAPI {

	public static AddressDocument getAddress(String query) {
		try {
			String target = "https://dapi.kakao.com/v2/local/search/address";
			System.out.println(query);
			System.out.println(URLEncoder.encode(query,"utf-8"));
			
			String queryString = "query=" + URLEncoder.encode(query,"utf-8");

			URI uri = new URI(target + "?" + queryString);

			// HttpClient 객체를 활용하는 방식
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(uri)
					.header("Authorization", "KakaoAK 3e63194799d61cfc1e3a9389f7121aa3").GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			Gson gson = new Gson();
			// System.out.println(response.body());
			AddressResponseResult result = gson.fromJson(response.body(), AddressResponseResult.class);
			return result.getDocuments()[0];
			
		} catch (Exception e) {
			e.printStackTrace();
			 return null;
		}
	}
}
