package com.eleveneleven.seoulapt.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HouseDealsInsert {
	static int total = 0;
	static int totalcount = 0;

	public static void main(String[] args) throws IOException {
		String[] guguncodeArray = { "11110", "11140", "11170", "11200", "11215", "11230", "11260", "11290", "11305", "11320",
				"11350", "11380", "11410", "11440", "11470", "11500", "11530", "11545", "11560", "11590", "11620",
				"11650", "11680", "11710", "11740" };
		String[] yearMonth = {	"202105", "202104", "202103", "202102", "202101", "202012", "202011", "202010", "202009", "202008", "202007", "202006", 
								"202005", "202004", "202003", "202002", "202001", "201912", "201911", "201910", "201909", "201908", "201907", "201906"};
		try {
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < 1; j++) {
					insertHousedeal(guguncodeArray[i], yearMonth[j]);
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
      
		} finally {
			System.out.println("받아온 아파트 매물 수  : " + total);
			System.out.println("총 "+totalcount+"개 업데이트 완료");
			System.out.println("끝");
		}

	}

	public static void insertHousedeal(String gugunCode, String yearMonth) throws Exception {
		System.out.println(gugunCode+"구 "+yearMonth);
		Connection conn = null;
		PreparedStatement pstmt = null;

		StringBuilder urlBuilder = new StringBuilder("OpenAPIURL"); 
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "ServiceKey"); 
		urlBuilder.append("&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(gugunCode, "UTF-8")); /* 각 지역별 코드 */
		urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(yearMonth, "UTF-8")); /* 월 단위 신고자료 */
		URL url = new URL(urlBuilder.toString());

		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("GET");
		httpConn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + httpConn.getResponseCode());

		BufferedReader rd;
		if (httpConn.getResponseCode() >= 200 && httpConn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(httpConn.getErrorStream()));
		}

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		httpConn.disconnect();
	}
	
}
