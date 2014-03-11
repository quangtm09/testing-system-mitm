package controller.SubjectManagement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Material;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

import org.apache.poi.POITextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.awt.RenderingHints;

public class PDFDocumentViewer extends AbstractPdfView {
	String fileContent, filePath, materialName, materialType;
	Material material;

	// In order to use this class, you must download poi-3.8.jar and iText.jar or later then add to this project
	// Link: http://www.apache.org/dyn/closer.cgi/poi/release/bin/poi-bin-3.8-20120326.zip
	// Link: http://sourceforge.net/projects/itext/files/iText/
	// By Tran Minh Quang
	@Override
	protected void buildPdfDocument(Map<String, Object> mapObject,
			Document document, PdfWriter pdfWriter, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		// Read data from ViewMaterial Controller
		Map<String, Material> fileData = (Map<String, Material>) mapObject
				.get("fileData");
		material = new Material();
		for (Map.Entry<String, Material> materialEntry : fileData.entrySet()) {
			filePath = materialEntry.getKey();
			material = materialEntry.getValue();
		}

		// Get material name and type from sent data of controller
		materialName = material.getMaterialTitle();
		materialType = material.getMaterialType();
		
		String getType = materialName.substring(materialName.length() - 4,
				materialName.length()).toLowerCase();

		// Add name of file to header of PDF file, and give warning
		addNameAndNote(document);

		// Extract content of PPT file to PDF
		if (getType.equals(".ppt")) {
			extractPPTFile(document, filePath);
		}
		
		// Extract content of PPTX file to PDF
		if(getType.equals("pptx")){
			extractPPTXFile(document, filePath);
		}

		// Extract DOC, DOCX to PDF, but go wrong with RTF file
		if (getType.equals(".doc")
				|| getType
						.equals("docx")) {
			document.add(new Paragraph(extractMSWordFile(filePath)));
		}

		// Extract TXT file
		if (getType.equals(".txt")) {
			document.add(new Paragraph(extractTEXTFile(filePath)));
		}

	}

	// Get content of DOCX file
	public String extractDOCXText(String filePath) throws Exception {
		InputStream in = new FileInputStream(filePath);
		XWPFDocument doc = new XWPFDocument(in);
		in.close();
		XWPFWordExtractor ex = new XWPFWordExtractor(doc);
		String fileContent = ex.getText();
		return fileContent;
	}

	// Get content of text file
	public String extractTEXTFile(String filePath) throws IOException {
		InputStreamReader inputSR = new InputStreamReader(new FileInputStream(
				filePath));
		BufferedReader buffreader = new BufferedReader(inputSR);
		String fileContent = buffreader.readLine();
		inputSR.close();
		return fileContent;
	}

	// Get content of DOC file
	public String[] extractDOCFile(String filePath)
			throws FileNotFoundException, IOException {
		POIFSFileSystem fs = null;
		FileInputStream fileIS = new FileInputStream(filePath);
		fs = new POIFSFileSystem(fileIS);

		HWPFDocument doc = new HWPFDocument(fs);
		WordExtractor we = new WordExtractor(doc);
		
		Range range = doc.getRange();
		String[] paragraphs = we.getParagraphText();
		for (int i = 0; i < paragraphs.length; i++) {
			paragraphs[i] = paragraphs[i].replaceAll("\\cM?\r?\n", "");
		}
		fileIS.close();
		return paragraphs;
	}

	// Get content of DOC or DOCX (Better and more generic than 2 methods above)
	public String extractMSWordFile(String filePath)
			throws InvalidFormatException, IOException, OpenXML4JException,
			XmlException {
		InputStream in = new FileInputStream(filePath);
		POITextExtractor poitext = ExtractorFactory.createExtractor(in);
		String fileContent = poitext.getText();
		in.close();
		return fileContent;
	}

	public void extractPPTFile(Document document, String filePath)
			throws IOException, DocumentException {

//		FileInputStream is = new FileInputStream(filePath);
//		SlideShow ppt1 = new SlideShow(is); // get ppt file
//		/*
//		 * Resize ppt1 and get ONLY 2 slides to put in the first page of
//		 * document. (842 595) is the size of A4 page in document. (-60) to make
//		 * it fix with the page, (/2) to make the first page able to hold 2
//		 * slides.
//		 */
//		ppt1.setPageSize(new java.awt.Dimension((842 - 60) / 2, (595 - 60) / 2));
//
//		// save changes to new file
//		FileOutputStream fileOutputStream = new FileOutputStream(filePath
//				+ "new");
//		ppt1.write(fileOutputStream);
//		fileOutputStream.close();
//		is.close();
//
//		// get new file but only 2 slides
//		FileInputStream is2 = new FileInputStream(filePath + "new");
//		SlideShow ppt2 = new SlideShow(is2);// create ppt2 to set size of the 2
//											// first slides, we cannot
//											// resize the first page of
//											// document.
//
//		
//		is2.close();
//
//		// get size of ppt3 to set ORIGINAL size the rest pages of the document
//		Dimension pgsize = ppt3.getPageSize();
//		int pgx = pgsize.width; // slide width
//		int pgy = pgsize.height; // slide height
//		document.setPageSize(new Rectangle(pgx + 60, pgy + 60));
//
//		Slide[] slide2 = ppt2.getSlides();
//
//		// first 2 slides
//		for (int i = 0; i < 2; i++) {
//
//			BufferedImage img = new BufferedImage(pgsize.width, pgsize.height,
//					BufferedImage.TYPE_INT_RGB);
//			Graphics2D graphics = img.createGraphics();
//			// clear the drawing area
//			graphics.setPaint(Color.white);
//			graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width,
//					pgsize.height));
//			// render
//			slide2[i].draw(graphics);
//
//			// save the output
//			FileOutputStream out = new FileOutputStream("slide-" + (i + 1)
//					+ ".png");
//			javax.imageio.ImageIO.write(img, "png", out);
//			out.close();
//
//			// add image to page
//			com.lowagie.text.Image image = com.lowagie.text.Image
//					.getInstance("slide-" + (i + 1) + ".png");
//			document.add(image);
//
//			// delete image from directory
//			File f1 = new File("slide-" + (i + 1) + ".png");
//			f1.delete();
//		}
		
		FileInputStream is3 = new FileInputStream(filePath);
		SlideShow ppt3 = new SlideShow(is3); // get original slide
		is3.close();
		Slide[] slide3 = ppt3.getSlides();
		
		// get size of ppt3 to set ORIGINAL size the rest pages of the document
		Dimension pgsize = ppt3.getPageSize();
		int pgx = pgsize.width; // slide width
		int pgy = pgsize.height; // slide height
		document.setPageSize(new Rectangle(pgx + 60, pgy + 60));

		// Reference of insert image to PDF: http://www.geek-tutorials.com/java/itext/itext_image.php
		// Continue with normal size of the rest slides
		for (int i = 0; i < slide3.length; i++) {

			BufferedImage img = new BufferedImage(pgsize.width, pgsize.height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = img.createGraphics();
			// clear the drawing area
			graphics.setPaint(Color.white);
			graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width,
					pgsize.height));

			// render
			slide3[i].draw(graphics);

			// save the output
			FileOutputStream out2 = new FileOutputStream("slide-" + (i + 1)
					+ ".png");
			javax.imageio.ImageIO.write(img, "png", out2);
			out2.close();

			// add image to page
			com.lowagie.text.Image image2 = com.lowagie.text.Image
					.getInstance("slide-" + (i + 1) + ".png");
			document.add(image2);
			

			// delete created image
			File f2 = new File("slide-" + (i + 1) + ".png");
			f2.delete();
		}

//		// delete the created file
//		File newFile = new File(filePath + "new");
//		newFile.delete();

	}
	
	// Reference: https://svn.apache.org/repos/asf/poi/trunk/src/ooxml/java/org/apache/poi/xslf/util/PPTX2PNG.java
	public void extractPPTXFile(Document document, String filePath) throws InvalidFormatException, IOException, DocumentException{
	        
	        XMLSlideShow ppt = new XMLSlideShow(OPCPackage.open(filePath));

	        Dimension pgsize = ppt.getPageSize();
	        int width = pgsize.width ;
	        int height = pgsize.height;
	        document.setPageSize(new Rectangle(width + 60, height + 60));

	        XSLFSlide[] slide = ppt.getSlides();
	        for (int i = 0; i < slide.length; i++) {

	            String title = slide[i].getTitle();

	            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	            Graphics2D graphics = img.createGraphics();

	            // default rendering options
	            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	            graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	            graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

	            graphics.setColor(Color.white);
	            graphics.clearRect(0, 0, width, height);

	            // draw stuff
	            slide[i].draw(graphics);

	            // save the result
	            FileOutputStream out = new FileOutputStream("slide-" + (i + 1)
						+ ".png");
				javax.imageio.ImageIO.write(img, "png", out);
				out.close();
				
				// add image to page
				com.lowagie.text.Image image = com.lowagie.text.Image
						.getInstance("slide-" + (i + 1) + ".png");
				document.add(image);

				// delete created image
				File file = new File("slide-" + (i + 1) + ".png");
				file.delete();
	        }
	}

	public void addNameAndNote(Document document) throws DocumentException {
		// Add name of file into header of PDF File
		Paragraph header = new Paragraph(materialName);
		header.getFont().setSize(25);
		header.setAlignment(20);
		document.add(header);

		// Add note for warning
		Paragraph note = new Paragraph(
				"Note: This PDF file maybe not display all the content of this file, you should download it instead of viewing online");
		note.getFont().setSize(10);
		header.setAlignment(20);
		document.add(note);
	}

}
