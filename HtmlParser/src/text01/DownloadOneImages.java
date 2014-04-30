package text01;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class DownloadOneImages {

	public void ParserImages(String url) throws ParserException{
		HttpClient hc = new HttpClient();
		Parser parser = new Parser();
		parser.setURL(url);
		parser.setEncoding("UTF-8");
		NodeFilter nodeFilter = new NodeFilter() {
			
			@Override
			public boolean accept(Node node) {
				if(node.getText().startsWith("img src=")){
					return true;
				}else{
					return false;
				}
			}
		};
		OrFilter orFilter = new OrFilter(new NodeClassFilter(LinkTag.class),nodeFilter);
		NodeList  nodeList = parser.extractAllNodesThatMatch(nodeFilter);
		for(int i = 0;i<nodeList.size();i++){
			Node node = nodeList.elementAt(i);
			if(node instanceof ImageTag){
				ImageTag imTag = (ImageTag) nodeList.elementAt(i);
				String imgUrl = imTag.getImageURL();
				GetMethod getMethod = new GetMethod(imgUrl);
				try {
					hc.executeMethod(getMethod);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					byte[] imgData = getMethod.getResponseBody();
					System.out.println("imgDataLeng:"+imgData.length);
					String imgName = imTag.getImageURL();
					imgName = getUrl(imgName);
					System.out.println("imgName:"+imgName);
					downloadImages(imgData,imgName);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public String getUrl(String url){
		return url.substring(7).replaceAll("/", "_");
	}
	public void downloadImages(byte[] imgData,String imgName){
		try {
			OutputStream ops = new FileOutputStream("C:/Users/DELL/Desktop/page/img/"+imgName);
			ops.write(imgData);
			ops.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new DownloadOneImages().ParserImages("http://news.ifeng.com/photoview/detail_2014_04/29/36077334_0.shtml#p=1");
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("³ÌÐò½áÊø£¡");
	}*/

}
