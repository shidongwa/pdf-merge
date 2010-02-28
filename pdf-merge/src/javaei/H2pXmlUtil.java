package javaei;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;

import javaei.h2p.H2pFileFilter;
import javaei.h2p.PKGenerateTool;
import javaei.pdf.NewPdfCreator;

import javax.swing.JFileChooser;

public class H2pXmlUtil {
	private static H2pFileFilter filter = new H2pFileFilter();
	private static PKGenerateTool pktool = new PKGenerateTool();
	
	
	public static boolean strIsNull(String str){
		boolean isNull = str == null || str.length() == 0;
		isNull =isNull || str.replaceAll(" ", "").length() == 0 ;
		return isNull;
	}
	
	public static void locateCenter(Component comp){
		int w = comp.getWidth();
		int h = comp.getHeight();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		double dw = d.getWidth();
		double dh = d.getHeight();
		int x = (int)(dw - w)/2;
		int y = (int)(dh - h)/2;
		comp.setLocation(x, y);
	}
	
	
	public static String generatorId(){
		return pktool.generatorPk(8);
	}
	
	public static File getSelectedFile(Component parent){
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(parent);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();
			return selectedFile;
		}
		return null;

	}
	
	public static File getSaveFile(Component parent){
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(parent);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();
			if(!selectedFile.getName().endsWith(".h2p.xml")){
				selectedFile = new File(selectedFile.getAbsolutePath()+".h2p.xml");
			}
			return selectedFile;
		}
		return null;

	}
	
	public static URL getClasspathFilePath(String cpfile){
		URL fileurl = null;
		fileurl = NewPdfCreator.class.getResource(cpfile);
		if(fileurl == null){
			fileurl = NewPdfCreator.class.getClassLoader().getResource(cpfile);
		}
		if(fileurl == null){
			fileurl = Thread.currentThread().getContextClassLoader().getResource(cpfile);
		}

		return fileurl;
	}

}
