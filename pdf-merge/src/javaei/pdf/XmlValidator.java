package javaei.pdf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XmlValidator {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		XmlValidator c = new XmlValidator();
		c.validateWithDTD("2009-06-25-href.h2p.xml");
	}
	
	public void validateWithDTD(String xmlFileName) throws Exception {
		StringBuffer sb = new StringBuffer();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        dbf.setValidating(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        DTDErrorHandler errorHandler = new DTDErrorHandler();
		db.setErrorHandler(errorHandler);

        db.parse(new File(xmlFileName));
        boolean legal = errorHandler.isLegal();
        if(!legal){
        	List<SAXParseException> errList = errorHandler.getErrList();
        	for(SAXParseException e : errList){
        		String errmsg = e.getSystemId() + " parse error at "
				                        + "line " + e.getLineNumber() + ",column "
				                        + e.getColumnNumber() + "," + e.getMessage();
				System.err.println(errmsg);
        		sb.append(errmsg+"\n");
        	}
        }else{
        	System.out.println("xml文档校验通过");
        }
        String errMsg = sb.toString();
        if(errMsg.length() > 0){
        	throw new Exception(errMsg);
        }

    }

	
	
	
	private class DTDErrorHandler implements ErrorHandler {
		
		private boolean legal = true;
		private List<SAXParseException> errList = new ArrayList<SAXParseException>();
		
		public List<SAXParseException> getErrList(){
			return errList;
		}

        public void error(SAXParseException arg0) throws SAXException {
        	legal = false;
        	errList.add(arg0);
        }

        public void fatalError(SAXParseException arg0) throws SAXException {
        	legal = false;
        	errList.add(arg0);
        }

        public void warning(SAXParseException arg0) throws SAXException {
        	legal = false;
        	errList.add(arg0);
        	
        }

		public boolean isLegal() {
			return legal;
		}

    }

}
