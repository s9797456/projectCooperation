package com.credit.util.html2pdf.component;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.credit.bean.vo.html2pdf.Report;
import com.credit.util.html2pdf.component.builder.HeaderFooterBuilder;

/**
 * Created by fgm on 2017/4/22.
 * 页眉页脚定制工具
 */
public class PDFHeaderFooter  implements HeaderFooterBuilder {
	/**
	 * @param writer   PDF编写类
	 * @param document PDF文档对象
	 * @param data     业务数据
	 * @param font     字体设置
	 * @param template PDF模板
	 * @description PDF页脚设置类
	 */
	public void writeFooter(PdfWriter writer,Document document,Object data,Font font,PdfTemplate template) {
		if (data == null) {
			return;
		}
		int pageS = writer.getPageNumber();
		int currentPage = pageS - 1;
		if (currentPage <= 0) {
			return;
		}
		Phrase footer1 = new Phrase("©版权：江苏汇誉通数据科技有限公司", font);
		Phrase footer2 = new Phrase(currentPage + "/", font);

		PdfContentByte cb = writer.getDirectContent();
		ColumnText.showTextAligned(cb,Element.ALIGN_LEFT,footer1,(document.left() + 10),document.bottom() - 20,0);
		ColumnText.showTextAligned(cb,Element.ALIGN_RIGHT,footer2,(document.right() - 30),document.bottom() - 20, 0);
		//设置模板位置
		cb.addTemplate(template, document.right() - 30, document.bottom() - 20);

	}

	/**
	 * @param writer   PDF编写类
	 * @param document PDF文档对象
	 * @param data     业务数据
	 * @param font     字体设置
	 * @param template PDF模板
	 * @description PDF页头设置类
	 */
	public void writeHeader(PdfWriter writer,Document document,Object data,Font font,PdfTemplate template) {
		Report report=new Report();
		if(data.equals(report)){
			report=(Report) data;
			String name=report.getEntName()+"信用评价报告";
			ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_RIGHT,new Phrase(name, font),document.right(),document.top(), 0);
			ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER,new Phrase(new Chunk(new LineSeparator())),document.left(),document.top()-5, 0);
		}
	}


	/**
	 * @param writer   PDF编写类
	 * @param document PDF文档对象
	 * @param data     业务数据
	 * @description 页头、页眉设置的模板替换类
	 */
	public String getReplaceOfTemplate(PdfWriter writer, Document document, Object data) {
		int total = writer.getPageNumber() - 2;
		return total + "";
	}

}
