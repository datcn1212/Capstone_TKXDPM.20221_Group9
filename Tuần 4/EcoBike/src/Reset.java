import org.json.JSONObject;
import util.HTTPBinder;

public class Reset {
	public static void main(String[] args) {
		testReset();
//		testProcess();
		JSONObject jsonObject = new JSONObject(
				"{\"errorCode\":\"00\",\"cardCode\":\"kstn_group2_2021\",\"owner\":\"Group 2\",\"cvvCode\":\"494\",\"dateExpired\":\"1125\",\"balance\":1000000}");
		System.out.println(jsonObject.get("errorCode").equals("00"));
	}

	public static void testReset() {
		String url = "https://ecopark-system-api.herokuapp.com/api/card/reset-balance";
		HTTPBinder h = new HTTPBinder();
		System.out.println("{\"cardCode\":\"kstn_group2_2021\",\r\n" + "     \"owner\":\"Group 2\",\r\n"
				+ "     \"cvvCode\":\"494\",\r\n" + "     \"dateExpired\":\"1125\"}");
		String result = h.patch(url, "{\"cardCode\":\"kstn_group2_2021\",\r\n" + "     \"owner\":\"Group 2\",\r\n"
				+ "     \"cvvCode\":\"494\",\r\n" + "     \"dateExpired\":\"1125\"}");
		System.out.println(result);
	}

	public static void testProcess() {
		String url = "https://ecopark-system-api.herokuapp.com/api/card/processTransaction";
		HTTPBinder h = new HTTPBinder();
		System.out.println("{\"version\":\"1.0.1\",\r\n"
				+ "     \"transaction\":{\"cardCode\":\"kstn_group2_2021\",\r\n"
				+ "                    \"owner\":\"Group 2\",\r\n" + "                    \"cvvCode\":\"494\",\r\n"
				+ "                    \"dateExpired\":\"1125\",\r\n" + "                    \"command\":\"pay\",\r\n"
				+ "                    \"transactionContent\":\"Transaction for a rent\",\r\n"
				+ "                    \"amount\": \"capital\",\r\n"
				+ "                    \"createdAt\":\"2021-12-14 17:00:36\"},\r\n"
				+ "     \"appCode\":\"BGmofQLFzMY=\",\r\n" + "     \"hashCode\":\"57DD01CCEF0D7C72ADB0AB33749BE1A4\"}");
		String result = h.patch(url, "{\"version\":\"1.0.1\",\r\n"
				+ "     \"transaction\":{\"cardCode\":\"kstn_group2_2021\",\r\n"
				+ "                    \"owner\":\"Group 2\",\r\n" + "                    \"cvvCode\":\"494\",\r\n"
				+ "                    \"dateExpired\":\"1125\",\r\n" + "                    \"command\":\"pay\",\r\n"
				+ "                    \"transactionContent\":\"Transaction for a rent\",\r\n"
				+ "                    \"amount\": 400000,\r\n"
				+ "                    \"createdAt\":\"2021-12-14 17:00:36\"},\r\n"
				+ "     \"appCode\":\"BGmofQLFzMY=\",\r\n" + "     \"hashCode\":\"8DE5A4C4D874AB89551D1ABD7E34709F\"}");
		System.out.println(result);
	}

}
