package javaei.h2p;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class H2pFileFilter extends FileFilter{

	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return ".h2p.xml";
	}

	public boolean accept(File pathname) {
		// TODO Auto-generated method stub
		if(pathname.isDirectory()||pathname.getName().endsWith(".h2p.xml")){
			return true;
		}
		return false;
	}

	
}