package run;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import sun.misc.URLClassPath;

public class Main {

		/**
		 * @param args
		 * 第一个参数是带main方法的类名
		 * 第二个参数是该类所依赖的jar包所在的文件夹
		 * 第三个参数及以后的参数将被所调用的类使用
		 * @throws MalformedURLException 
		 */
	public static void main(String[] args) throws Exception {
		if(args == null || args.length < 2){
			return;
		}
		String jardir = args[1];
		String classname = args[0];
		addJarToClasspath(jardir);
		Class mainclass = Class.forName(classname);
		Method mainmethod = mainclass.getMethod("main", new Class[]{String[].class});
		int callargslen = args.length - 2;
		String[] callargs = null;
		if(callargslen > 0){
			callargs = new String[callargslen];
			for(int i = 0;i < callargslen;i ++){
				callargs[i] = args[i + 2];
			}
		}
		mainmethod.invoke(null, new Object[]{callargs});

	}
	
		
		
		
		
		private static void addJarToClasspath(String jarDirPath){
			File libdir = new File(jarDirPath);
			File[] libs = libdir.listFiles();

			for(File lib : libs){
				setClasspath(lib.getAbsolutePath());
			}
		}
		
		private static void setClasspath(String fileordirname){
			File f = new File(fileordirname);
			URL addurl = null;
			URLClassLoader cl = (URLClassLoader)Thread.currentThread().getContextClassLoader();
			try {
				if(f.isFile()){
					addurl = new URL("file:/"+f.getAbsolutePath());
				}else{
					addurl = new URL("file:/"+f.getAbsolutePath()+"/");
				}
				Field uf = cl.getClass().getSuperclass().getDeclaredField("ucp");
				uf.setAccessible(true);
				URLClassPath ucp = (URLClassPath)uf.get(cl);
				ucp.addURL(addurl);
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}

}
