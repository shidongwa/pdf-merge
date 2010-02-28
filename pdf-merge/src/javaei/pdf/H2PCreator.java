package javaei.pdf;

import java.io.IOException;
import java.net.URLEncoder;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class H2PCreator {

    public static void main(String[] args) {
    	
     
        H2PCreator sax = new H2PCreator();
        if(args!=null && args.length == 2){
        	sax.createPdf(args[0], args[1]);
        }else{
        	System.out.println("参数不对:" + args.length);
        }

    }

    /**
     * 解析文档
     * @param fileName XML文件的名字
     */
    public void createPdf(String h2pfile,String destdir) {
    	System.out.println("开始合并pdf......");
    	
        try {
            XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");

			ConcatHandler concatHandler = new ConcatHandler();
			
			concatHandler.setDestdir(destdir);
            
            parser.setContentHandler(concatHandler);
            String filepath = URLEncoder.encode(h2pfile, "UTF-8");
			parser.parse(filepath);
            pause();
        }
        catch (IOException e) {
           e.printStackTrace();
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
    }
    
    private void pause(){
    	System.out.println("按Enter键退出:");
    	try {
			int read = System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
