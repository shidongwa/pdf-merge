package javaei.pdf;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

import javaei.H2pXmlUtil;

import com.lowagie.text.Anchor;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.Markup;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class NewPdfCreator {
	private URL logourl = null;
	private  BaseFont bf;
	{
		try {
			bf = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		logourl = H2pXmlUtil.getClasspathFilePath("javaei/pdf/img/logo.bmp");
	}
	
	private void createTop(Document document)throws Exception{

		Image img1 = Image.getInstance(logourl);
		img1.scalePercent(50);
		Font font = new Font();
		font.setColor(new Color(32,96,128));
		font.setFamily(FontFactory.TIMES_ROMAN);
		font.setSize(8);
		font.setStyle(Markup.CSS_VALUE_UNDERLINE+Markup.CSS_VALUE_BOLD);

		Anchor anchor = new Anchor("http://www.javaei.com",font);
		anchor.setReference("http://www.javaei.com");

		PdfPTable outertable = new PdfPTable(1);
		outertable.setTotalWidth(img1.getWidth());
		PdfPCell cell = new PdfPCell(img1,true);
		cell.setBorder(0);
		outertable.addCell(cell);

		cell = new PdfPCell(anchor);
		cell.setBorder(0);
		outertable.addCell(cell);

		outertable.setTotalWidth(img1.getScaledWidth());
		outertable.setLockedWidth(true);
		outertable.setHorizontalAlignment(Element.ALIGN_LEFT);
		document.add(outertable);

		document.add(Chunk.NEWLINE);
		document.add(new Paragraph("\n\n\n"));

	}
	
	private void createContent(Document document,String content)throws Exception{
		Font font = new Font(bf);
		font.setColor(new Color(32,96,128));
		font.setFamily(FontFactory.TIMES_ROMAN);
		font.setSize(40);
		font.setStyle(Markup.CSS_VALUE_TEXTALIGNCENTER+Markup.CSS_VALUE_BOLD);

		Paragraph paragraph = new Paragraph(content,font);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph);

	}
	
	
	public  void createPage(String content,String chptpdfpath){
		Document document = new Document();
		try {
			
			PdfWriter.getInstance(document, new FileOutputStream(chptpdfpath));
			document.open();
			
			createTop(document);
			createContent(document,content);

		} catch (Exception e) {
			// handle exception
			e.printStackTrace();
		}
		document.close();

	}

	



}
