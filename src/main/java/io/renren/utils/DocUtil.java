package io.renren.utils;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.headerfooter.RtfHeaderFooter;

public class DocUtil {
	private Document document;
    private BaseFont bfChinese;
    public BaseFont getBfChinese() {
        return bfChinese;
    }

    public void setBfChinese(BaseFont bfChinese) {
        this.bfChinese = bfChinese;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public DocUtil(){
        this.document = new Document(PageSize.A4);
    }
    /**
     * @param filePath 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
     * @throws DocumentException
     * @throws IOException
     */
    public void openDocument(String filePath) throws DocumentException,
    IOException {
    	// 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
    	RtfWriter2 writer = RtfWriter2.getInstance(this.document, new FileOutputStream(filePath));
        this.document.open();
        // 添加页眉 
        Image headerImage = Image.getInstance("F:\\logo.png");
        headerImage.scaleAbsolute(110, 23); // 自定义图片大小
        //headerImage.scalePercent(20);//依照比例缩放
        RtfHeaderFooter header = new RtfHeaderFooter(headerImage);
        header.setBorder(Rectangle.BOTTOM);
        header.setAlignment(Rectangle.ALIGN_CENTER);
        header.setBorderColor(Color.BLACK);  
        this.document.setHeader(header);
		 
		// 设置中文字体
        /*this.bfChinese = BaseFont.createFont("STSongStd-Light",
                "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);*/
    }

    /**
     * @param titleStr 标题
     * @param fontsize 字体大小
     * @param fontStyle 字体样式
     * @param elementAlign 对齐方式
     * @throws DocumentException
     */
    public void insertTitle(String titleStr,int fontsize,int fontStyle,int elementAlign) throws DocumentException{
        Font titleFont = new Font(this.bfChinese, fontsize, fontStyle);
        Paragraph title = new Paragraph(titleStr);
        // 设置标题格式对齐方式
        title.setAlignment(elementAlign);
        title.setFont(titleFont);

        this.document.add(title);
    }

    /**
     * @param contextStr 内容
     * @param fontsize 字体大小
     * @param fontStyle 字体样式
     * @param elementAlign 对齐方式
     * @throws DocumentException 
     */
    public void insertContext(String contextStr,int fontsize,int fontStyle,int elementAlign) throws DocumentException{
        // 正文字体风格
        Font contextFont = new Font(bfChinese, fontsize, fontStyle);
        Paragraph context = new Paragraph(contextStr);
        //设置行距
        context.setLeading(10f);
        // 正文格式左对齐
        context.setAlignment(elementAlign);
        context.setFont(contextFont);
        // 离上一段落（标题）空的行数
        context.setSpacingBefore(5);
        // 设置第一行空的列数
        context.setFirstLineIndent(20);
        document.add(context);
    }
    /*
     * 测试清单
     * */
    public  void insertRiskTable() throws DocumentException{
        Table aTable = new Table(6,3);
        int width[] = { 10, 40, 17, 13, 10, 10 };
        aTable.setWidths(width);// 设置每列所占比例
        aTable.setWidth(100); // 占页面宽度 90%
        aTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
        aTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
        aTable.setAutoFillEmptyCells(true); // 自动填满
        aTable.setBorderWidth(0); // 边框宽度
        aTable.setBorderColor(new Color(0, 125, 255)); // 边框颜色
        aTable.setPadding(2);// 衬距，看效果就知道什么意思了
        aTable.setSpacing(3);// 即单元格之间的间距
        aTable.setBorder(2);// 边框

        Font fontChinese = new Font(bfChinese, 10, Font.BOLD);
        Cell cell = new Cell(new Phrase("\n测试代码\n", fontChinese));
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(new Color(0, 0, 0));
        cell.setBackgroundColor(new Color(153, 204, 255));
        aTable.addCell(cell);

        Cell cell1 = new Cell(new Phrase("测试名称", fontChinese));
        cell1.setVerticalAlignment(Element.ALIGN_CENTER);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setBorderColor(new Color(0, 0, 0));
        cell1.setBackgroundColor(new Color(153, 204, 255));
        aTable.addCell(cell1);

        Cell cell2 = new Cell(new Phrase("测试发生可能性", fontChinese));
        cell2.setVerticalAlignment(Element.ALIGN_CENTER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setBorderColor(new Color(0, 0, 0));
        cell2.setBackgroundColor(new Color(255, 255, 0));
        aTable.addCell(cell2);

        Cell cell3 = new Cell(new Phrase("测试损失度", fontChinese));
        cell3.setVerticalAlignment(Element.ALIGN_CENTER);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setBorderColor(new Color(0, 0, 0));
        cell3.setBackgroundColor(new Color(255, 255, 0));
        aTable.addCell(cell3);

        Cell cell4 = new Cell(new Phrase("测试水平", fontChinese));
        cell4.setVerticalAlignment(Element.ALIGN_CENTER);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setBorderColor(new Color(0, 0, 0));
        cell4.setBackgroundColor(new Color(255, 255, 0));
        aTable.addCell(cell4);

        Cell cell5 = new Cell(new Phrase("测试等级", fontChinese));
        cell5.setVerticalAlignment(Element.ALIGN_CENTER);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell5.setBorderColor(new Color(0, 0, 0));
        cell5.setBackgroundColor(new Color(255, 255, 0));
        aTable.addCell(cell5);

        for(int i=0;i<12;i++){
            aTable.addCell(new Cell(i+""));
        }       
        document.add(aTable);
        document.add(new Paragraph("\n"));  
    }
    /*
     * 现状评估
     * */
    public void insertRiskEvaluationTable() throws DocumentException{
        Table aTable = new Table(4,7);
        int width1[] = { 5, 20, 8, 25};
        aTable.setWidths(width1);// 设置每列所占比例
        aTable.setWidth(100); // 占页面宽度 90%
        aTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
        aTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
        aTable.setAutoFillEmptyCells(true); // 自动填满
        aTable.setBorderWidth(0); // 边框宽度
        aTable.setBorderColor(new Color(0, 125, 255)); // 边框颜色

        Font fontChinese = new Font(bfChinese, 10, Font.BOLD);
        Cell cell11 = new Cell(new Phrase("教学环节", fontChinese));
        cell11.setVerticalAlignment(Element.ALIGN_CENTER);
        cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell11.setColspan(2);
        cell11.setBorderColor(new Color(0, 0, 0));
        cell11.setBackgroundColor(new Color(153, 204, 255));
        aTable.addCell(cell11);

        Cell cell12 = new Cell(new Phrase("时间（分钟）", fontChinese));
        cell12.setVerticalAlignment(Element.ALIGN_CENTER);
        cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell12.setRowspan(1);
        cell12.setBorderColor(new Color(0, 0, 0));
        cell12.setBackgroundColor(new Color(153, 204, 255));
        aTable.addCell(cell12);

        Cell cell13 = new Cell(new Phrase("教学活动", fontChinese));
        cell13.setVerticalAlignment(Element.ALIGN_CENTER);
        cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell13.setRowspan(1);
        cell13.setBorderColor(new Color(0, 0, 0));
        cell13.setBackgroundColor(new Color(153, 204, 255));
        aTable.addCell(cell13);
        
        
        Font fontChinesecontent = new Font(bfChinese, 10, Font.BOLD);
        Cell cell21 = new Cell(new Phrase("课程引入、学习目标分析", fontChinesecontent));
        cell21.setVerticalAlignment(Element.ALIGN_CENTER);
        cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell21.setColspan(2);
        cell21.setBorderColor(new Color(0, 0, 0));
        aTable.addCell(cell21);

        Cell cell22 = new Cell(new Phrase("5", fontChinesecontent));
        cell22.setVerticalAlignment(Element.ALIGN_CENTER);
        cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell22.setRowspan(1);
        cell22.setBorderColor(new Color(0, 0, 0));
        aTable.addCell(cell22);

        Cell cell23 = new Cell(new Phrase("教师设问，播放课件，学生讨论", fontChinesecontent));
        cell23.setVerticalAlignment(Element.ALIGN_CENTER);
        cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell23.setRowspan(1);
        cell23.setBorderColor(new Color(0, 0, 0));
        aTable.addCell(cell23);

        Cell cell31 = new Cell(new Phrase("新课\n讲解\n技能\n训练", fontChinesecontent));
        cell31.setVerticalAlignment(Element.ALIGN_CENTER);
        cell31.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell31.setRowspan(2);
        cell31.setBorderColor(new Color(0, 0, 0));
        aTable.addCell(cell31);

        Cell cell32 = new Cell(new Phrase("？？？", fontChinesecontent));
        cell32.setVerticalAlignment(Element.ALIGN_CENTER);
        cell32.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell32.setRowspan(1);
        cell32.setBorderColor(new Color(0, 0, 0));
        aTable.addCell(cell32);

        Cell cell33 = new Cell(new Phrase("9", fontChinesecontent));
        cell33.setVerticalAlignment(Element.ALIGN_CENTER);
        cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell33.setRowspan(1);
        cell33.setBorderColor(new Color(0, 0, 0));
        aTable.addCell(cell33);
        
        Cell cell34 = new Cell(new Phrase("教师讲解，学生讨论", fontChinesecontent));
        cell34.setVerticalAlignment(Element.ALIGN_CENTER);
        cell34.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell34.setRowspan(1);
        cell34.setBorderColor(new Color(0, 0, 0));
        aTable.addCell(cell34);
        
        Cell cell41 = new Cell(new Phrase("？？？", fontChinesecontent));
        cell41.setVerticalAlignment(Element.ALIGN_CENTER);
        cell41.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell41.setRowspan(1);
        cell41.setBorderColor(new Color(0, 0, 0));
        aTable.addCell(cell41);
        
        Cell cell42 = new Cell(new Phrase("15", fontChinesecontent));
        cell42.setVerticalAlignment(Element.ALIGN_CENTER);
        cell42.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell42.setRowspan(1);
        cell42.setBorderColor(new Color(0, 0, 0));
        aTable.addCell(cell42);
        
        Cell cell43 = new Cell(new Phrase("？？？", fontChinesecontent));
        cell43.setVerticalAlignment(Element.ALIGN_CENTER);
        cell43.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell43.setRowspan(1);
        cell43.setBorderColor(new Color(0, 0, 0));
        aTable.addCell(cell43);
        
        Cell cell51 = new Cell(new Phrase("课程小结", fontChinesecontent));
        cell51.setVerticalAlignment(Element.ALIGN_CENTER);
        cell51.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell51.setColspan(2);
        cell51.setBorderColor(new Color(0, 0, 0));
        aTable.addCell(cell51);
        
        Cell cell52 = new Cell(new Phrase("5", fontChinesecontent));
        cell52.setVerticalAlignment(Element.ALIGN_CENTER);
        cell52.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell52.setRowspan(1);
        cell52.setBorderColor(new Color(0, 0, 0));
        aTable.addCell(cell52);
        
        Cell cell53 = new Cell(new Phrase("学生讨论归纳，教师点评", fontChinesecontent));
        cell53.setVerticalAlignment(Element.ALIGN_CENTER);
        cell53.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell53.setRowspan(1);
        cell53.setBorderColor(new Color(0, 0, 0));
        aTable.addCell(cell53);
        
        Cell cell61 = new Cell(new Phrase("过程评价", fontChinesecontent));
        cell61.setVerticalAlignment(Element.ALIGN_CENTER);
        cell61.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell61.setColspan(2);
        cell61.setBorderColor(new Color(0, 0, 0));
        aTable.addCell(cell61);
        
        Cell cell62 = new Cell(new Phrase("3", fontChinesecontent));
        cell62.setVerticalAlignment(Element.ALIGN_CENTER);
        cell62.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell62.setRowspan(1);
        cell62.setBorderColor(new Color(0, 0, 0));
        aTable.addCell(cell62);
        
        Cell cell63 = new Cell(new Phrase("学生自评和互评，教师评价在巡视中完成课堂表现部分，其余在课后补充完整", fontChinesecontent));
        cell63.setVerticalAlignment(Element.ALIGN_CENTER);
        cell63.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell63.setRowspan(1);
        cell63.setBorderColor(new Color(0, 0, 0));
        aTable.addCell(cell63);
        
        Cell cell71 = new Cell(new Phrase("作业布置", fontChinesecontent));
        cell71.setVerticalAlignment(Element.ALIGN_CENTER);
        cell71.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell71.setColspan(2);
        cell71.setBorderColor(new Color(0, 0, 0));
        aTable.addCell(cell71);
        
        Cell cell72 = new Cell(new Phrase("3", fontChinesecontent));
        cell72.setVerticalAlignment(Element.ALIGN_CENTER);
        cell72.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell72.setRowspan(1);
        cell72.setBorderColor(new Color(0, 0, 0));
        aTable.addCell(cell72);
        
        Cell cell73 = new Cell(new Phrase("教师布置课后作业（功能改进），或下发任务单。学生按要求完成", fontChinesecontent));
        cell73.setVerticalAlignment(Element.ALIGN_CENTER);
        cell73.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell73.setRowspan(1);
        cell73.setBorderColor(new Color(0, 0, 0));
        aTable.addCell(cell73);
        
        document.add(aTable);
        document.add(new Paragraph("\n"));
    }
    
    /**
     * 课时生成清单
     * @throws BadElementException 
     */
    public void insertCourseInfo() throws DocumentException {
    	Table tab = new Table(9,30); // 9表示几列，30表示几行
    	int width[] = { 4, 6, 6, 17, 14, 14, 17, 10, 7 }; // 每列所占的宽度
    	
    	tab.setWidths(width);// 设置每列所占比例
        tab.setWidth(100); // 占页面宽度 95%
        tab.setAlignment(Element.ALIGN_CENTER);// 居中显示
        tab.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
        tab.setAutoFillEmptyCells(true); // 自动填满
        tab.setBorderWidth(0); // 边框宽度
        tab.setBorderColor(new Color(0, 125, 255)); // 边框颜色
        
        String[] titles = {" 学号","日期","星期","起止时间","班  级","授课地点","授课内容/章节标题","导师签名"," 课时折算 (节)"};
        Font fontChinese = new Font(bfChinese, 9, Font.BOLD);
        Cell cell = null;
        for(String t : titles) {
            cell = new Cell(new Phrase(t, fontChinese));
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setBorderColor(new Color(0, 0, 0));
            cell.setBackgroundColor(new Color(153, 204, 255));
            tab.addCell(cell);
        }
        
//        for(int i=0;i<60;i++){
//            tab.addCell(new Cell(i+""));
//        }

        document.add(tab);
        document.add(new Paragraph("\n"));

        
        /*Font fontChinesecontent = new Font(bfChinese, 10, Font.BOLD);
        Cell cell21 = new Cell(new Phrase("课程引入、学习目标分析", fontChinesecontent));
        cell21.setVerticalAlignment(Element.ALIGN_CENTER);
        cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell21.setColspan(2);
        cell21.setBorderColor(new Color(0, 0, 0));
        tab.addCell(cell21);

        Cell cell22 = new Cell(new Phrase("5", fontChinesecontent));
        cell22.setVerticalAlignment(Element.ALIGN_CENTER);
        cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell22.setRowspan(1);
        cell22.setBorderColor(new Color(0, 0, 0));
        tab.addCell(cell22);

        Cell cell23 = new Cell(new Phrase("教师设问，播放课件，学生讨论", fontChinesecontent));
        cell23.setVerticalAlignment(Element.ALIGN_CENTER);
        cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell23.setRowspan(1);
        cell23.setBorderColor(new Color(0, 0, 0));
        tab.addCell(cell23);*/
        
    }

    /*
     * 测试控制清单
     * */
    public  void insertRiskControlTable() throws DocumentException{
        Table aTable = new Table(11,3);
        int width[] = { 5, 13, 5, 9, 9, 13, 9, 9, 9, 9, 9 };
        aTable.setWidths(width);// 设置每列所占比例
        aTable.setWidth(100); // 占页面宽度 90%
        aTable.setAlignment(Element.ALIGN_CENTER);// 居中显示
        aTable.setAlignment(Element.ALIGN_MIDDLE);// 纵向居中显示
        aTable.setAutoFillEmptyCells(true); // 自动填满
        aTable.setBorderWidth(0); // 边框宽度
        aTable.setBorderColor(new Color(0, 125, 255)); // 边框颜色

        Font fontChinese = new Font(bfChinese, 10, Font.BOLD);
        Cell cell = new Cell(new Phrase("\n测试代码\n", fontChinese));
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(new Color(0, 0, 0));
        cell.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell);

        Cell cell1 = new Cell(new Phrase("测试名称", fontChinese));
        cell1.setVerticalAlignment(Element.ALIGN_CENTER);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setBorderColor(new Color(0, 0, 0));
        cell1.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell1);

        Cell cell2 = new Cell(new Phrase("行为代码", fontChinese));
        cell2.setVerticalAlignment(Element.ALIGN_CENTER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setBorderColor(new Color(0, 0, 0));
        cell2.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell2);

        Cell cell3 = new Cell(new Phrase("引发测试的行为", fontChinese));
        cell3.setVerticalAlignment(Element.ALIGN_CENTER);
        cell3.setBorderColor(new Color(0, 0, 0));
        cell3.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell3);

        Cell cell4 = new Cell(new Phrase("测试控制态度", fontChinese));
        cell4.setVerticalAlignment(Element.ALIGN_CENTER);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setBorderColor(new Color(0, 0, 0));
        cell4.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell4);

        Cell cell5 = new Cell(new Phrase("控制措施", fontChinese));
        cell5.setVerticalAlignment(Element.ALIGN_CENTER);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell5.setBorderColor(new Color(0, 0, 0));
        cell5.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell5);

        Cell cell6 = new Cell(new Phrase("措施类型", fontChinese));
        cell6.setVerticalAlignment(Element.ALIGN_CENTER);
        cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell6.setBorderColor(new Color(0, 0, 0));
        cell6.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell6);

        Cell cell7 = new Cell(new Phrase("完成标志", fontChinese));
        cell7.setVerticalAlignment(Element.ALIGN_CENTER);
        cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell7.setBorderColor(new Color(0, 0, 0));
        cell7.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell7);

        Cell cell8 = new Cell(new Phrase("控制措施完成时间", fontChinese));
        cell8.setVerticalAlignment(Element.ALIGN_CENTER);
        cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell8.setBorderColor(new Color(0, 0, 0));
        cell8.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell8);

        Cell cell9 = new Cell(new Phrase("控制措施牵头部门", fontChinese));
        cell9.setVerticalAlignment(Element.ALIGN_CENTER);
        cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell9.setBorderColor(new Color(0, 0, 0));
        cell9.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell9);

        Cell cell10 = new Cell(new Phrase("控制措施配合部门", fontChinese));
        cell10.setVerticalAlignment(Element.ALIGN_CENTER);
        cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell10.setBorderColor(new Color(0, 0, 0));
        cell10.setBackgroundColor(new Color(204, 153, 255));
        aTable.addCell(cell10);

        for(int i=0;i<22;i++){
            aTable.addCell(new Cell(i+""));
        }

        document.add(aTable);
        document.add(new Paragraph("\n"));

    }

    /**
     * @param imgUrl 图片路径
     * @param imageAlign 显示位置
     * @param height 显示高度
     * @param weight 显示宽度
     * @param percent 显示比例
     * @param heightPercent 显示高度比例
     * @param weightPercent 显示宽度比例
     * @param rotation 显示图片旋转角度
     * @throws MalformedURLException
     * @throws IOException
     * @throws DocumentException
     */
    public void insertImg(String imgUrl,int imageAlign,int height,int weight,int percent,int heightPercent,int weightPercent,int rotation) throws MalformedURLException, IOException, DocumentException{
//       添加图片
        Image img = Image.getInstance(imgUrl);
        if(img==null)
            return;
        img.setAbsolutePosition(0, 0);
        img.setAlignment(imageAlign);
        img.scaleAbsolute(height, weight);
        img.scalePercent(percent);
        img.scalePercent(heightPercent, weightPercent);
        img.setRotation(rotation);

        document.add(img);
    }

    public void closeDocument() throws DocumentException{
        this.document.close();
    }

    public static void main(String[] args) throws DocumentException, IOException {
    	DocUtil wt = new DocUtil();
        wt.openDocument("f:\\201705251843.doc");
        wt.insertTitle("\t附件3", 10, Font.BOLD, Element.ALIGN_LEFT);
        wt.insertTitle("项目实训教学课时登记表", 14, Font.BOLD, Element.ALIGN_CENTER);
        wt.insertTitle("日期：201__年__月份\t", 10, Font.BOLD, Element.ALIGN_RIGHT);
        /*wt.insertRiskEvaluationTable();*/
        wt.insertCourseInfo();
        wt.closeDocument();
    }
}
