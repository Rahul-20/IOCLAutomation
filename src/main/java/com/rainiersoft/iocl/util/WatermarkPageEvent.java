package com.rainiersoft.iocl.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class WatermarkPageEvent extends PdfPageEventHelper 
{
	Font FONT = new Font(Font.FontFamily.HELVETICA,45,Font.BOLD,BaseColor.LIGHT_GRAY);

	PdfTemplate total;

	private Font normal, normalSmall;

	/*public WatermarkPageEvent ()
	{
		try{
			this.normal = new Font(Font.FontFamily.HELVETICA, 52, Font.BOLD, new GrayColor(0.70f));
			this.normalSmall = new Font(Font.FontFamily.HELVETICA, 52, Font.BOLD, new GrayColor(0.70f));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Creates the PdfTemplate that will hold the total number of pages.
	 * @see com.itextpdf.text.pdf.PdfPageEventHelper#onOpenDocument(
	 *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
	 */
	/*public void onOpenDocument(PdfWriter writer, Document document) {
		total = writer.getDirectContent().createTemplate(30, 12);
	}*/

	/**
	 * Fills out the total number of pages before the document is closed.
	 * @see com.itextpdf.text.pdf.PdfPageEventHelper#onCloseDocument(
	 *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
	 */
	/*public void onCloseDocument(PdfWriter writer, Document document) {
		ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
				new Phrase(String.valueOf(writer.getPageNumber() - 1), normal),
				2, 2, 0);
	}*/

	@Override
	public void onEndPage(PdfWriter writer, Document document)
	{
		PdfContentByte contentbytes = writer.getDirectContent();
		String mainHeader=IOCLConstants.PdfCommonHeader;
		
		ColumnText.showTextAligned(contentbytes,Element.ALIGN_CENTER,new Phrase(mainHeader,new Font(Font.FontFamily.HELVETICA,10,Font.BOLD)),(document.right()-document.left())/2+document.leftMargin(),document.top()+10,0);
		
		/*//800 parameter is height
		if(writer.getPageNumber() == 1)
		{
			ColumnText.showTextAligned(contentbytes,Element.ALIGN_CENTER,new Phrase(reportName,new Font(Font.FontFamily.HELVETICA,10,Font.BOLD)),297.5f,800,0);
		}*/
		
		//ColumnText.showTextAligned(writer.getDirectContentUnder(),Element.ALIGN_CENTER, new Phrase("IOCL BALASORE",FONT),297.5f,421,writer.getPageNumber() % 2 == 1 ? 45 : -45);
		
		//Last Parameter for angle(25)
		ColumnText.showTextAligned(writer.getDirectContentUnder(),Element.ALIGN_CENTER, new Phrase("IOCL BALASORE",FONT),297.5f,421,25);
	}
}