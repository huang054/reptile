package text01;

import java.util.HashSet;
import java.util.Set;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;

public class HtmlParserTool {

	
	public static Set<String> extracLinks(String url,LinkFilter filter){
		Set<String> links = new HashSet<String>();
		try {
			Parser parser = new Parser(url);
			parser.setEncoding("gb2312");
			NodeFilter fraeFilter = new NodeFilter() {
				@Override
				public boolean accept(Node node) {
					if(node.getText().startsWith("frame src=")){
						return true;
					}
					return false;
				}
			};
			
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return links;
	}
	public static void main(String[] args){
		
	}
}
