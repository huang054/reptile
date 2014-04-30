package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexs {

	String urlRegexs = "[http:]*[https:]*//[a-zA-Z\\.]{3,}";
	
	public boolean checkingUrl(String url){
		Pattern pattern = Pattern.compile(urlRegexs);
        Matcher  matcher = pattern.matcher(url);
        boolean boo = matcher.matches();
        System.out.println(boo);
        return boo;
	}
}
