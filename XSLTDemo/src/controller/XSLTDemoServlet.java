package controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

/**
 * Servlet implementation class XSLTDemoServlet
 */
@WebServlet("/XSLTDemoServlet")
public class XSLTDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void processAction(final HttpServletRequest request, final HttpServletResponse response){
		final File xml = new File("C:\\Users\\TAQ1HC\\workspace\\XSLTDemo\\WebContent\\xml\\animal_without_xsl.xml");
		final File xsd = new File("C:\\Users\\TAQ1HC\\workspace\\XSLTDemo\\WebContent\\xml\\animal.xsd");
		final File xsl = new File("C:\\Users\\TAQ1HC\\workspace\\XSLTDemo\\WebContent\\xml\\animal.xsl");

		final File outputHTMLFile = new File("C:\\Users\\TAQ1HC\\workspace\\XSLTDemo\\WebContent\\xml\\animal_output.html");

		if(outputHTMLFile.exists()){
			outputHTMLFile.delete();
		}

		response.setContentType("text/html");

		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
		} catch (final IOException e) {
			System.out.println("Cannot get writer!");
		}

		if(validateXMLAgainstXSD(xml, xsd)){
			final TransformerFactory factory = TransformerFactory.newInstance();
			final Source xslSource = new StreamSource(xsl);
			Transformer transformer = null;

			try {
				transformer = factory.newTransformer(xslSource);
			} catch (final TransformerConfigurationException e) {
				printWriter.print("Cannot initialize transformer");
			}

			final Source xmlSource = new StreamSource(xml);

			try {
				transformer.transform(xmlSource, new StreamResult(outputHTMLFile));

				// Read html file content
				int len;
				final char[] chr = new char[4096];
				final StringBuffer buffer = new StringBuffer();
				final FileReader reader = new FileReader(outputHTMLFile);
				try {
					while ((len = reader.read(chr)) > 0) {
						buffer.append(chr, 0, len);
					}
				} finally {
					reader.close();
				}

				// Print html file content to response

				printWriter.print(buffer.toString());

			} catch (final Exception e) {
				printWriter.print("Cannot transforming XML file");
			}
		} else {
			printWriter.print("Invalid XML file!");
		}

		printWriter.close();
	}

	/**
	 * Validate XML against XSD file
	 * 
	 * @param xml
	 * @param xsd
	 * @return
	 */
	private boolean validateXMLAgainstXSD(final File xml, final File xsd){
		try{

			final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			final Schema schema = factory.newSchema(new StreamSource(xsd));

			final Validator validator  = schema.newValidator();
			validator.validate(new StreamSource(xml));
			return true;
		}catch(final Exception exe){
			exe.printStackTrace();
			return false;
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XSLTDemoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

}
