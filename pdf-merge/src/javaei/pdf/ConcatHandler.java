package javaei.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.lowagie.text.pdf.PdfCopyFields;
import com.lowagie.text.pdf.PdfReader;

public class ConcatHandler extends ContentHandlerAdapter {
    private Stack<HashMap> outlineStack = new Stack<HashMap>();
    private Stack<NameHref> hrefstack = new Stack<NameHref>();
    private PdfCopyFields copy = null;
    private int index = 1;
    
    private List outlines = new ArrayList();
    private NewPdfCreator pdfCreator = new NewPdfCreator();
    private String bookname;
    private Logger logger = null;
    
    public void startElement(String namespaceURI, String localName,
                             String qName, Attributes atts) throws SAXException {
    	
        String name = atts.getValue(ATTR_NAME);
        String id = atts.getValue(ATTR_ID);
        PdfReader reader = null;
        HashMap outline = null;
        
        try{
        	//构造reader和outline
        	if(ELEMENT_BOOK.equals(localName)){
        		pdfCreator.createPage(name, getDesttempdir()+name+".pdf");
        		reader = new PdfReader(getDesttempdir()+name+".pdf");

        		copy.addDocument(reader);
        		outline = createOutline(name,index);

        	}else if(ELEMENT_CHAPTER.equals(localName)){
        		logInfo("处理节点："+name);
        		if(!hrefstack.empty()){
        			NameHref nh = hrefstack.peek();
        			if(!nh.hashref){
        				pdfCreator.createPage(nh.name, getDesttempdir()+nh.name+".pdf");
        				reader = new PdfReader(getDesttempdir()+nh.name+".pdf");
        				nh.hashref=true;
        				copy.addDocument(reader);
        				outline = createOutline(nh.name,index);
        			}
        		}
        		hrefstack.push(new NameHref(name,false));
        		
        	}else if(ELEMENT_HREF.equals(localName)){
        		NameHref nh = hrefstack.peek();
        		nh.hashref=true;

        		File tempfile = new File(getDesttempdir()+id+".pdf");
	    		if(!tempfile.exists()){
	    			pdfCreator.createPage(nh.name, getDesttempdir()+id+".pdf");
	    		}
	    		reader = new PdfReader(getDesttempdir()+id+".pdf");
	    		copy.addDocument(reader);
	    		outline = createOutline(nh.name,index);
        	}
        	
        	if(reader !=null){
        		//构造outline的层次关系
        		if(localName.equals(ELEMENT_BOOK)){
        			outlineStack.push(outline);
        			bookname = name;
        			
        		}else if(localName.equals(ELEMENT_CHAPTER)||localName.equals(ELEMENT_HREF)){
        			HashMap parent = outlineStack.peek();
        			List<HashMap> kids = (List<HashMap>)parent.get("Kids");
        			if(kids == null){
        				kids = new ArrayList<HashMap>();
        				parent.put("Kids", kids);
        			}
        			kids.add(outline);
        			outlineStack.push(outline);
        		}
        		
        		index = index + reader.getNumberOfPages();
        	}
			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    private void logInfo(String msg){
    	logger.log(msg);
    	System.out.println(msg);
    }
    
    private HashMap createOutline(String title,int pageindex){
		HashMap outline = new HashMap();
		outline.put("Action", "GoTo");
		outline.put("Title", title);
		outline.put("Page", pageindex+" FitH null");
		return outline;

    }

    public void endElement(String namespaceURI, String localName, String qName) throws
        SAXException {
    	if(localName.equals(ELEMENT_CHAPTER)){
    		outlineStack.pop();
    		if(!hrefstack.empty()){
    			hrefstack.pop();
    		}
    	}else if(localName.equals(ELEMENT_BOOK)){
    		HashMap rootline = outlineStack.pop();
    		outlines.add(rootline);
    	}
    }
    

    public void startDocument() throws SAXException {
 		 
		
		try {
			copy = new PdfCopyFields(new FileOutputStream(getDestdir()+DEST_FILE_NAME));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		logger = new Logger(getDestdir()+"\\msg.txt");
		logInfo("\n开始合并......\n");
    }

    public void endDocument() throws SAXException {
    	
    	copy.setOutlines(outlines);
		copy.close();
		logger.close();
		deleteTempfile(getDesttempdir());
		
		File destfile = new File(getDestdir()+DEST_FILE_NAME);
		File renameto = new File(getDestdir()+bookname+".pdf");
		destfile.renameTo(renameto);
		
    }
    
    
    private void deleteTempfile(String tempdir){
    	File temp = new File(tempdir);
    	File[] tempfiles = temp.listFiles();
    	for(File f : tempfiles){
    		f.delete();
    	}
    	temp.delete();
    }
    
    private class NameHref{
    	String name;
    	boolean hashref;
    	NameHref(String n,boolean h){
    		name=n;
    		hashref=h;
    	}
    }


}
