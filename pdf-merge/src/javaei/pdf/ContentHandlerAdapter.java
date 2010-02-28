package javaei.pdf;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class ContentHandlerAdapter implements ContentHandler {
	
	// DTD中定义的元素名
	public static final String ATTR_NAME = "name";
	public static final String ATTR_ID = "id";
	public static final String ELEMENT_HREF = "href";
	public static final String ELEMENT_BOOK = "book";
	public static final String ELEMENT_CHAPTER = "chapter";
	public static final String DEST_FILE_NAME = "javaei-h2p.pdf";


	private String destdir;
	private String desttempdir;
	
	
	
	public String getDesttempdir() {
		return desttempdir;
	}

	public String getDestdir() {
		return destdir;
	}

	public void setDestdir(String destdir) {
		if(!destdir.endsWith("/")){
			this.destdir = destdir+"/";
		}
		desttempdir = this.destdir+"temp/";
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub

	}

	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}

	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		// TODO Auto-generated method stub

	}

	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub

	}

}
