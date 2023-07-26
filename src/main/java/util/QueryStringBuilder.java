package util;

import java.util.HashMap;
import java.util.Map;

public class QueryStringBuilder {

	public static String build(Map<String, String> params) {

		String str = params.toString();

		str = str.replaceAll(", ", "&");

		return str.substring(1, str.length() - 1);
	}

}
