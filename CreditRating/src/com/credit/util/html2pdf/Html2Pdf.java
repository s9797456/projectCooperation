package com.credit.util.html2pdf;

import java.util.ArrayList;
import java.util.List;

import com.credit.bean.enterprise.Executives;
import com.credit.bean.enterprise.Shareholder;
import com.credit.bean.vo.html2pdf.Report;
import com.credit.util.html2pdf.component.chart.Line;
import com.credit.util.model.IndexRateVo;
import com.google.common.collect.Lists;

public class Html2Pdf {

	
	public static void main(String[] args) {
		Report report = new Report();
		report.setEntName("江苏森茗生态景观研究院有限公司");
		report.setEncoding("4890-3201-000-0001-1709");
		report.setGradeTime("2017年09月20日");
		report.setValueTime("2018年09月20日");
		report.setQrcode("asd");
		List<IndexRateVo> indexRates = new ArrayList<IndexRateVo>();
		IndexRateVo index1 = new IndexRateVo();
		IndexRateVo index2 = new IndexRateVo();
		IndexRateVo index3 = new IndexRateVo();
		index1.setIndexName("财务综合能力");
		index1.setRateValue("96%");
		index1.setDescription("经营环境欠佳");
		index2.setIndexName("经营管理能力");
		index2.setDescription("企业曝光度欠佳");
		index2.setRateValue("85.5%");
		index3.setIndexName("实际控制人特征");
		index3.setRateValue("70%");
		index3.setDescription("行业受政策限制");
		indexRates.add(index1);
		indexRates.add(index2);
		indexRates.add(index3);
		report.setIndexRates(indexRates);
		report.setFinalLevel("AA");
		report.setScoreSummary("信用极好，信用能力很强，几乎无风险。在授信时充分值得信赖。具有优秀的企业品格，企业发展能力极强，债务偿还风险极低，社会责任心强，可以在其财务能力能够承担的最高限授信。");
		report.setEntType("建筑行业");
		report.setAddress("南京市溧水区永阳镇中大街68号115室");
		report.setZipCode("215000");
		report.setLegalPerson("吴克羣");
		report.setRegiCapital("5000万美元");
		report.setSetupDate("2014年01月07日");
		report.setUscc("91320117088138732G");
		report.setRegisOrg("南京市溧水区市场监督管理局");
		report.setWebsite("www.baidu.com");
		report.setTel("025-86529100");
		report.setFax("025-86529100");
		report.setScale("10~99人");
		report.setBusinessScope("景观工程、园林绿化工程、室内外装饰工程设计、施工；工程监理；模型设计服务；金属模型制作；建筑设计；图文设计；企业项目策划服务；展览展示服务；林业调查规划设计；规划咨询；编制项目建议书及可行性研究报告；林业、园林、景观生态科技产品研发；农业及林业有害生物防治服务；森林防火服务；农业及林业技术推广服务；其他农业服务；其他林业服务；会务服务；计算机及网络培训；");
		Shareholder s1 = new Shareholder();
		Shareholder s2 = new Shareholder();
		Shareholder s3 = new Shareholder();
		s1.setName("刘颀");
		s1.setRealcapi("10");
		s1.setStockpercent("1");
		s1.setType("自然人股东");
		s2.setName("刘轶");
		s2.setStockpercent("1");
		s2.setRealcapi("10");
		s2.setType("自然人股东");
		s3.setName("吴斌");
		s3.setRealcapi("10");
		s3.setStockpercent("98");
		s3.setType("自然人股东");
		List<Shareholder> shareholders = new ArrayList<Shareholder>();
		shareholders.add(s1);
		shareholders.add(s2);
		shareholders.add(s3);
		report.setShareHolders(shareholders);
		Executives e1 = new Executives();
		Executives e2 = new Executives();
		Executives e3 = new Executives();
		e1.setName("李刚");
		e1.setGender("男");
		e1.setIDCard("65465465465463121325");
		e1.setJob("总经理");
		e1.setEducation("大专");
		e2.setName("李玉刚");
		e2.setIDCard("65465465465463123425");
		e2.setJob("监事");
		e2.setGender("女");
		e2.setEducation("本科");
		e3.setName("李刘刚");
		e3.setIDCard("65465465465463125525");
		e3.setGender("男");
		e3.setJob("茶水科科长");
		e3.setEducation("小学毕业");
		List<Executives> executives = new ArrayList<Executives>();
		executives.add(e1);
		executives.add(e2);
		executives.add(e3);
		report.setExecutives(executives);
		boolean flag = Html2PDFUtil.createPDF(report, "c://test//new.pdf", "C:/test/", "report.ftl");
		if(flag){
			System.out.println("***************");
			System.out.println("c://test//new.pdf");
			System.out.println("***************");
		}else{
			System.out.println("pdf生成失败");
		}

		/*TemplateBO templateBO=new TemplateBO();
	        templateBO.setTemplateName("Hello iText! Hello freemarker! Hello jFreeChart!");
	        templateBO.setFreeMarkerUrl("http://www.zheng-hang.com/chm/freemarker2_3_24/ref_directive_if.html");
	        templateBO.setITEXTUrl("http://developers.itextpdf.com/examples-itext5");
	        templateBO.setJFreeChartUrl("http://www.yiibai.com/jfreechart/jfreechart_referenced_apis.html");
	        templateBO.setImageUrl("c:/test/images/1.jpg");
	        List<String> scores=new ArrayList<String>();
	        scores.add("90");
	        scores.add("95");
	        scores.add("98");
	        templateBO.setScores(scores);
	        List<Line> lineList=getTemperatureLineList();
	        DefaultLineChart lineChart=new DefaultLineChart();
	        lineChart.setHeight(500);
	        lineChart.setWidth(300);
	        String picUrl=lineChart.draw(lineList,0);
	        templateBO.setPicUrl(picUrl);
	        String path= html2Pdf.createPDF(templateBO,"hello.pdf");
	        System.out.println("***************");
	        System.out.println(path);
	        System.out.println("***************");*/
	}
	
	

    public static List<Line> getTemperatureLineList() {
        List<Line> list= Lists.newArrayList();
        for(int i=1;i<=7;i++){
            Line line=new Line();
            float random=Math.round(Math.random()*10);
            line.setxValue("星期"+i);
            line.setyValue(20+random);
            line.setGroupName("下周");
            list.add(line);
        }
        for(int i=1;i<=7;i++){
            Line line=new Line();
            float random=Math.round(Math.random()*10);
            line.setxValue("星期"+i);
            line.setyValue(20+random);
            line.setGroupName("这周");
            list.add(line);
        }
        return list;
    }

/*    public  String createPDF(Object data, String fileName){
        //pdf保存路径
        try {
            //设置自定义PDF页眉页脚工具类
            PDFHeaderFooter headerFooter=new PDFHeaderFooter();
            PDFKit kit=new PDFKit();
            kit.setHeaderFooterBuilder(headerFooter);
            //设置输出路径
            kit.setSaveFilePath("c://test//new.pdf");
            String saveFilePath=kit.exportToFile(fileName,data);
            return  saveFilePath;
        } catch (Exception e) {
            //log.error("PDF生成失败{}", ExceptionUtils.getFullStackTrace(e));
            return null;
        }

    }*/
}
