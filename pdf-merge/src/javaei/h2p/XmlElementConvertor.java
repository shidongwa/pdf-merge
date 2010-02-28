package javaei.h2p;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.ProcessingInstruction;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XmlElementConvertor {
	
	
	public void saveH2p(DefaultMutableTreeNode rootNode,String filename){
		XmlNodeObject rootobj = (XmlNodeObject)(rootNode).getUserObject();
		saveToXml(rootobj, new File(filename));
	}
	
	
	public DefaultMutableTreeNode loadH2p(String filename){
		File h2pfile = new File(filename);
		
		DefaultMutableTreeNode rootNode = null;
		FileInputStream fi = null;
		try{
			fi = new FileInputStream(h2pfile);
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(fi);
			Element root = doc.getRootElement(); // 得到根元素
			rootNode = new DefaultMutableTreeNode();
			XmlNodeObject rootobj = new XmlNodeObject();
			rootobj.setH2pElement(root);
			rootNode.setUserObject(rootobj);
			
			createNodeByElement(rootNode,root);
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fi.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rootNode;
	}
	
	
	
	private void createNodeByElement(DefaultMutableTreeNode parentNode,Element parent){
		List<Element>  chld = parent.getChildren();
		XmlNodeObject parentuserObject = (XmlNodeObject)parentNode.getUserObject();
		if(chld != null){
			for(Element che:chld){
				String tagname = che.getName();
				if(tagname.equals("chapter")){
					XmlNodeObject userObject = new XmlNodeObject();
					userObject.setH2pElement(che);
					userObject.setParentObject2(parentuserObject);
					DefaultMutableTreeNode node= new DefaultMutableTreeNode(userObject);
					parentNode.add(node);
					createNodeByElement(node,che);
				}
			}
		}
	}
	
	private void saveToXml(XmlNodeObject rootObj,File file){
		FileOutputStream fo = null;
		try {
			
			Document document = new Document();
			ProcessingInstruction pi =   new ProcessingInstruction("xml-stylesheet","type=\"text/xsl\" href=\"http://www.javaei.com/content/h2p/h2p.xsl\"");   
			document.addContent(pi);
			document.setDocType(createDoctype());
			Element cloneroot = (Element)rootObj.getH2pElement().clone();

			document.setRootElement(cloneroot);
			
			Format format = Format.getPrettyFormat();
			format.setIndent("\t");
			format.setEncoding("UTF-8");
			XMLOutputter outp = new XMLOutputter(format);
			fo = new FileOutputStream(file);
			outp.output(document, fo);
		} catch (Exception e) {
			System.err.println(e + "error");
		} finally {
			try {
				fo.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private DocType createDoctype(){
		DocType dt = new DocType("book","-//JavaEI/JavaEI h2p Configuration DTD//CN","http://www.javaei.com/dtd/javaei-h2p.dtd");
		return dt;
	}
}
