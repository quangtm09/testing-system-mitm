package controller.CourseTemplate;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CourseTemplate;
import model.Subject;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import business.CourseTemplateBO;
import business.impl.CourseTemplateBOimpl;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class CourseTemplatePDF extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> arg0, Document arg1,
			PdfWriter arg2, HttpServletRequest arg3, HttpServletResponse arg4)
			throws Exception {
		Map<String, CourseTemplate> ListCt = (Map<String, CourseTemplate>) arg0
				.get("Data");

		Paragraph header = new Paragraph("List Course Template");
		header.getFont().setSize(30);
		header.setAlignment(20);
		arg1.add(header);

		Paragraph header11 = new Paragraph("GVHD: To Chau");
		header.getFont().setSize(17);
		arg1.add(header11);

		Paragraph header1 = new Paragraph("Nhom 7:");
		header.getFont().setSize(17);
		arg1.add(header1);

		Paragraph header2 = new Paragraph("Nguyen Tan Loc - Thu Trang");
		header.getFont().setSize(15);
		arg1.add(header2);
		String strStartDate = null;
		String strEndDate = null;
		Table table = new Table(7);

		// format
		table.setPadding(5);
		table.setBorderColor(new Color(234, 34, 56));
		table.setWidth(110);
		table.addCell("No.");
		table.addCell("Id");
		table.addCell("Name");
		table.addCell("Start Day");
		table.addCell("End Day");
		table.addCell("Description");
		table.addCell("Subject");
		for (Map.Entry<String, CourseTemplate> entry : ListCt.entrySet()) {
			table.addCell(entry.getKey());
			CourseTemplate ct = (CourseTemplate) entry.getValue();
			table.addCell(ct.getId() + "");
			table.addCell(ct.getName());

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			strStartDate = sdf.format(ct.getStartDay().getTime());

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			strEndDate = sdf1.format(ct.getEndDay().getTime());
			table.addCell(strStartDate);
			table.addCell(strEndDate);
			Iterator<Subject> subjects = ct.getSubject().iterator();
			table.addCell(ct.getDescription());
			String str = " ";
			while (subjects.hasNext()) {
				Subject subject = subjects.next();
				str += subject.getSubjectName() + " ,";
			}
			table.addCell(str);
		}

		arg1.add(table);

	}

}
