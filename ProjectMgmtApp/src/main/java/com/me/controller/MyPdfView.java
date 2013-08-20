package com.me.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.me.pojo.Project;
import com.me.pojo.Team;

public class MyPdfView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> map,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
			Date date = new Date();
			Locale locale = null;
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String formattedDate = dateFormat.format(date);
			Paragraph date1 = new Paragraph(formattedDate.toString());
			Project project = (Project) map.get("project");
			Paragraph p0 = new Paragraph("PROJECT OVERVIEW");
			
			
			Paragraph p1 = new Paragraph("Project Name : "+ project.getProjectName());
			Paragraph p2 = new Paragraph("Project ID  : "+ project.getProjectID());
			Paragraph p3 = new Paragraph("Stage : "+ project.getStage());
			Paragraph p4 = new Paragraph("Description : "+project.getDescription() );
			
			List<Team> pteams = (List<Team>) map.get("pteams"); 
			Paragraph p5 =  new Paragraph("");
			Paragraph p6 = new Paragraph("TEAMS ASSIGNED TO PROJECT");
			
			float j =20	; 
			
			document.addTitle("PROJECT OVERVIEW");
			document.add(date1);
			document.top(j);
			document.addCreationDate();
			document.add(p0);
			document.add(p1);
			document.add(p2);
			document.add(p3);
			document.add(p4);
			document.add(p5);
			document.add(p6);
	
			
			
			PdfPTable table = new PdfPTable(pteams.size());
			PdfPCell c1 =  new PdfPCell();
			for(int i = 0 ; i < pteams.size() ; i++){
				c1 = new PdfPCell(new Phrase(pteams.get(i).getTeamName()));
				c1.setVerticalAlignment(Element.ALIGN_LEFT);
				c1.setBorder(0);
				
				table.addCell(c1);
			}
			
			document.add(table);
			document.close();
	}

}
