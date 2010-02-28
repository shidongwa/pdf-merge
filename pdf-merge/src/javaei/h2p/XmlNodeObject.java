package javaei.h2p;

import javaei.H2pXmlUtil;

import org.jdom.CDATA;
import org.jdom.Element;

public class XmlNodeObject {
	
	private Element h2pElement;
	
	private XmlNodeObject parentObject;
	
	
//	���ø��ڵ����,��ӽڵ��Ϊ�Խڵ�ʱ
	public void setParentObject(XmlNodeObject parentObject) {
		this.parentObject = parentObject;
		this.parentObject.getH2pElement().addContent(getH2pElement());
	}
	
	public void setParentObject2(XmlNodeObject parentObject) {
		this.parentObject = parentObject;
	}
	
	
	public Element getH2pElement(){
		return h2pElement;
	}
	
//	�����ڵ���Ϣ
	public XmlNodeObject(String title,String href){
		h2pElement = new Element("chapter");
		h2pElement.setAttribute("name", title);
		
		if(href!= null && href.length() > 0){
			Element hrefElement = createHrefElement(href);
			h2pElement.addContent(hrefElement);
		}
	}
	
	public XmlNodeObject(){}
	
	//������ڵ�
	public XmlNodeObject(String title){
		h2pElement = new Element("book");
		h2pElement.setAttribute("name", title);
	}
	
	//�޸Ľڵ���Ϣ
	public void setTitleAndHref(String title,String href){
		h2pElement.setAttribute("name", title);
		Element hrefElement = h2pElement.getChild("href");
		if(hrefElement == null ){
			if(!H2pXmlUtil.strIsNull(href)){
				hrefElement = createHrefElement(href);
				h2pElement.addContent(0, hrefElement);
			}
		}else{
			if(!H2pXmlUtil.strIsNull(href)){
				h2pElement.removeChild("href");
				hrefElement = createHrefElement(href);
				h2pElement.addContent(0, hrefElement);
				
			}else{
				h2pElement.removeChild("href");
			}
		}
	}
	
	
	public boolean isRoot(){
		return h2pElement.getName().equals("book");
	}
	
	//�Ӹ��ڵ�ɾ��ʱ
	public void deleteFromParent(){
		parentObject.getH2pElement().removeContent(h2pElement);
	}
	
	private Element createHrefElement(String href){
		Element hrefElement = new Element("href");
		String hrefid = H2pXmlUtil.generatorId();
		hrefElement.setAttribute("id", hrefid);
		hrefElement.addContent(new CDATA(href));
		return hrefElement;
	}


	public void setH2pElement(Element element) {
		h2pElement = element;
	}
	public String toString() {
		// TODO Auto-generated method stub
		return h2pElement.getAttributeValue("name");
	}
	
	public String getTitle(){
		return h2pElement.getAttributeValue("name");
	}
	public String getHref(){
		Element hrefElement = h2pElement.getChild("href");
		if(hrefElement!=null){
			return hrefElement.getText();
		}else{
			return "";
		}
	}
	
	
}
