package controller.CourseTemplate;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class PdfRevenueReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map model, Document document,
			PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, String> revenueData = (Map<String, String>) model
				.get("revenueData");

		Paragraph header = new Paragraph("List Course Template");
		header.getFont().setSize(20);
		document.add(header);

		Table table = new Table(7);
		table.addCell("id");
		table.addCell("name");
		table.addCell("startDay");
		table.addCell("startEnd");
		table.addCell("description");
		table.addCell("SubjectError");
		table.addCell("subject");

		for (int i = 0; i < 100; i++) {
			table.addCell(i + " ");
			table.addCell("--" + i);
		}

		document.add(table);
	}
}