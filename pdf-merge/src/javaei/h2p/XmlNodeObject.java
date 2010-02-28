package javaei.h2p;

import javaei.H2pXmlUtil;

import org.jdom.CDATA;
import org.jdom.Element;

public class XmlNodeObject {
	
	private Element h2pElement;
	
	private XmlNodeObject parentObject;
	
	
//	设置父节点对象,添加节点成为自节点时
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
	
//	创建节点信息
	public XmlNodeObject(String title,String href){
		h2pElement = new Element("chapter");
		h2pElement.setAttribute("name", title);
		
		if(href!= null && href.length() > 0){
			Element hrefElement = createHrefElement(href);
			h2pElement.addContent(hrefElement);
		}
	}
	
	public XmlNodeObject(){}
	
	//构造根节点
	public XmlNodeObject(String title){
		h2pElement = new Element("book");
		h2pElement.setAttribute("name", title);
	}
	
	//修改节点信息
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
	
	//从父节点删除时
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
