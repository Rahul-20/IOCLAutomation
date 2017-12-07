package com.rainiersoft.iocl.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class PDFUtilities 
{
	Properties appProps;

	PdfPTable table=null;

	public PDFUtilities(int numOfCol,Properties props)
	{
		this.appProps=props;
		table = new PdfPTable(numOfCol);
	}

	public PDFUtilities(int numOfCol)
	{
		table = new PdfPTable(numOfCol);
		table.setSpacingBefore(130.0f);
	}

	public void createPdfFile(String[] header,List<String[]> reportDetails,List<String[]> data,String tempFileName,String calculatedValue) throws URISyntaxException, IOException, DocumentException
	{
		Document document = new Document(PageSize.A4);
		PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(appProps.getProperty("TempReportFilePath")+tempFileName));
		document.open();
		writer.setPageEvent(new WatermarkPageEvent());

		this.addDetailsTableHeader(reportDetails,document);
		this.addTableHeader(table,header);
		this.addRows(table,data,calculatedValue);

		document.add(table);
		document.close();
	}

	private void addDetailsTableHeader(List<String[]> details,Document document) throws DocumentException 
	{
		PdfPTable detailsTable=new PdfPTable(2);
		detailsTable.setSpacingAfter(40.0f);
		for(String[] arr:details)
		{	
			for(String row:arr)
			{
				PdfPCell detailsCell = new PdfPCell();
				detailsCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				detailsCell.setBorderColor(BaseColor.BLACK);
				detailsCell.setBorder(Rectangle.NO_BORDER);
				detailsCell.setBorderWidth(1);
				detailsCell.setPhrase(new Phrase(5f,row,FontFactory.getFont(FontFactory.TIMES,8f)));
				detailsTable.addCell(detailsCell);
			}
		}
		document.add(detailsTable);
	}

	private void addTableHeader(PdfPTable table,String[] docHeader) 
	{
		for(String columnHeader:docHeader)
		{
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderColor(BaseColor.BLACK);
			header.setBorderWidth(1);
			header.setPaddingTop(8.0f);
			header.setPhrase(new Phrase(15f,columnHeader,FontFactory.getFont(FontFactory.TIMES_BOLD,8f)));
			table.addCell(header);
		}
	}

	private void addRows(PdfPTable table,List<String[]> data,String calculatedValue) 
	{
		for(String[] arr:data)
		{
			for(String row:arr)
			{			
				table.addCell(new Phrase(12f,row,FontFactory.getFont(FontFactory.TIMES,8f)));
			}
		}

		if(calculatedValue.length()>0)
		{
			PdfPCell cell=new PdfPCell(new Phrase(12f,"Total",FontFactory.getFont(FontFactory.TIMES,12f)));
			cell.setColspan(6);
			table.addCell(cell);
			table.addCell(new Phrase(12f,calculatedValue,FontFactory.getFont(FontFactory.TIMES,8f)));
		}
	}

	/*private void addCustomRows(PdfPTable table) throws URISyntaxException, BadElementException, IOException 
	{
		Path path = Paths.get(ClassLoader.getSystemResource("Java_logo.png").toURI());
		Image img = Image.getInstance(path.toAbsolutePath().toString());
		img.scalePercent(10);

		PdfPCell imageCell = new PdfPCell(img);
		table.addCell(imageCell);

		PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
		horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(horizontalAlignCell);

		PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
		verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		table.addCell(verticalAlignCell);
	}*/

	/*public static void manipulatePdf(String src,String dest) throws IOException, DocumentException 
	{
		PdfReader reader = new PdfReader(src);
		int n = reader.getNumberOfPages();
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(src));
		stamper.setRotateContents(false);
		// text watermark
		Font f = new Font(FontFamily.HELVETICA, 30);
		Phrase p = new Phrase("My watermark (text)", f);
		// image watermark
		Image img = Image.getInstance("C:\\Users\\erapami\\Desktop\\logo.png");
		img.setBackgroundColor(BaseColor.WHITE);
		float w = img.getScaledWidth();
		float h = img.getScaledHeight();
		// transparency
		PdfGState gs1 = new PdfGState();
		gs1.setFillOpacity(0.5f);
		// properties
		PdfContentByte over;
		Rectangle pagesize;
		float x, y;
		// loop over every page
		for (int i = 1; i <= n; i++) {
			pagesize = reader.getPageSize(i);
			x = (pagesize.getLeft() + pagesize.getRight()) / 2;
			y = (pagesize.getTop() + pagesize.getBottom()) / 2;
			over = stamper.getOverContent(i);
			over.saveState();
			over.setGState(gs1);
			over.addImage(img, w, 0, 0, h, x - (w / 2), y - (h / 2));
			over.restoreState();
		}
		stamper.close();
		reader.close();
	}*/
}