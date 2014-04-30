package text01;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;


public class DownloadOnePage {

	public void downloadPage(String url) throws HttpException, IOException, InterruptedException{
		HttpClient httpclient = new HttpClient();
		GetMethod getmethod = new GetMethod(url);
		int stautsCode = httpclient.executeMethod(getmethod);
		if(stautsCode==HttpStatus.SC_OK){
			byte[] by = getmethod.getResponseBody();
			String filename = getUrl(url);
			OutputStream out = new FileOutputStream("C:/Users/辉/Desktop/reptile/"+filename);
			out.write(by);
			if(out!=null){
				out.close();
				System.out.println("流已关闭，程序结束");
			}
		}
	}
	public String getUrl(String url){
		return url.substring(7).replaceAll("/", "_")+".html";	
	}
	public static void main(String[] args) throws HttpException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		Scanner scanner  = new Scanner(System.in);
		System.out.println("请输入网址,需加上http");
		String url = scanner.next();
		DownloadOnePage t01 = new DownloadOnePage();
		t01.downloadPage(url);
	}
}
