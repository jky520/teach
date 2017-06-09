package io.renren.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class FileUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
	public static List<String> readTxtFile(String fileName) {
		List<String> list = new ArrayList<String>();
		try {
			String encode = "utf-8";
			File file = new File(fileName);
			String cellContent = null;
			if(file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encode);// 编码设置
				BufferedReader br = new BufferedReader(read);
				String lineText = null;
				int n = 0;
				while((lineText = br.readLine()) != null) {
					cellContent = lineText.trim().replace("Example of ", "");
					list.add(cellContent);
				}
				read.close();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static void readwriteWord(String filePath, List<Map<String,Object>> lists, HttpServletResponse response) throws IOException {
		System.out.println("============");
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		//读取word模板
		FileInputStream in = null;
		int[] fontSize = {10,8,8,8,8,8,8,8};// 设置每一个单元格的字体大小
		try {
			in = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		XWPFDocument xdoc = null;
		try {
			xdoc = new XWPFDocument(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//replaceInPara(xdoc,params);
		//替换表格里面的变量  
	    replaceInTable(xdoc, lists,fontSize);
	    String fileName = sdf.format(new Date())+".docx";
		try {
			//os = new FileOutputStream("f:\\"+fileName);  
			xdoc.write(os);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
	    response.reset();
	    response.setContentType("application/vnd.ms-excel;charset=utf-8");
	    response.setHeader("Content-Disposition", "attachment;filename="
	          + new String((fileName).getBytes(), "iso-8859-1"));
	    ServletOutputStream out = response.getOutputStream();
	    BufferedInputStream bis = null;
	    BufferedOutputStream bos = null;
	 
	    try {
	    	bis = new BufferedInputStream(is);
	        bos = new BufferedOutputStream(out);
	        byte[] buff = new byte[2048];
	        int bytesRead;
	        // Simple read/write loop.
	        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	          bos.write(buff, 0, bytesRead);
	        }
	      } catch (Exception e) {
	        // TODO: handle exception
	        e.printStackTrace();
	      } finally {
	        if (bis != null)
	          bis.close();
	        if (bos != null)
	          bos.close();
	    }
	    close(os);  
	    close(in);  
	}
	
	
	/*public static void readwriteWord(String filePath, Map<String,Object> map) {
		//读取word模板
		FileInputStream in = null;
		int[] fontSize = {10,8,8,8,8,8,8,8};// 设置每一个单元格的字体大小
		try {
			in = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		XWPFDocument xdoc = null;
		try {
			xdoc = new XWPFDocument(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//replaceInPara(xdoc,params);
		//替换表格里面的变量  
	    replaceInTable(xdoc, map,fontSize);
	    OutputStream os = null;
		try {
			os = new FileOutputStream("f:\\write.docx");  
			xdoc.write(os);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	    close(os);  
	    close(in);  
		
	}*/
	
	/** 
    * 替换段落里面的变量 
    * @param doc 要替换的文档 
    * @param params 参数 
    */  
	private static void replaceInPara(XWPFDocument doc, Map<String, Object> params) {  
		Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();  
		XWPFParagraph para;  
		while (iterator.hasNext()) {  
			para = iterator.next();  
			replaceInPara(para, params);  
		}  
	} 
	
	
	/** 
    * 替换段落里面的变量 
    * @param para 要替换的段落 
    * @param params 参数 
    */  
	private static void replaceInPara(XWPFParagraph para, Map<String, Object> params) {  
		List<XWPFRun> runs;  
		runs = para.getRuns();  
		for (int i=0; i<runs.size(); i++) {  
			XWPFRun run = runs.get(i);  
			Object key = params.keySet().toArray()[0];
			Object vlue = params.get(key);
			String runText = vlue.toString();  
			System.out.println(runText);
			//直接调用XWPFRun的setText()方法设置文本时，在底层会重新创建一个XWPFRun，把文本附加在当前文本后面，  
			//所以我们不能直接设值，需要先删除当前run,然后再自己手动插入一个新的run。  
			para.removeRun(i);  
			para.insertNewRun(i).setText(runText);  
    	}  
	}  
	
	/** 
    * 替换表格里面的变量 
    * @param doc 要替换的文档 
    * @param params 参数 
    */  
    private static void replaceInTable(XWPFDocument doc, List<Map<String,Object>> lists,int[] fontSize) {  
    	Iterator<XWPFTable> iterator = doc.getTablesIterator();  
    	XWPFTable table;  
    	List<XWPFTableRow> rows;  
    	List<XWPFTableCell> cells;  
    	while (iterator.hasNext()) {  
    		table = iterator.next();  
    		rows = table.getRows(); 
    		XWPFTableRow row = null;
    		for (int i=1; i<rows.size(); i++) {  
    			row = rows.get(i);
                cells = row.getTableCells();  
                XWPFTableCell cell = null;
                if(i<lists.size()) {
                	 Map<String,Object> params = lists.get(i-1);
                     for (int k = 0,j=1; k < params.keySet().size(); k++,j++) { 
                     	cell = cells.get(j);
                     	Object key = params.keySet().toArray()[k];
                         Object value = params.get(key);
                         
                         // 关键的一步，删掉原有的段落
                         cell.removeParagraph(0);// 感觉注释有些问题,这个好像只是删除单元格原有内容,而不是删掉整个段落
                                                                                                                                                                                                                                                                                                                                        
                         XWPFParagraph newPara = new XWPFParagraph(cell.getCTTc().addNewP(), cell);                                                                                                                               
                         XWPFRun run=newPara.createRun();                       
                        
                         /**内容居中显示**/
                         newPara.setAlignment(ParagraphAlignment.CENTER);
                        
                         //run.getCTR().addNewRPr().addNewColor().setVal("FF0000");/**FF0000红色*/    
                         run.setFontSize(fontSize[k]);
                         run.setBold(true);
                         run.setFontFamily("宋体");
                         //run.setUnderline(UnderlinePatterns.THICK);
                         run.setText(value.toString());       
                     }
                }
             }  
    	}  
    }  
	
	/** 
    * 正则匹配字符串 
    * @param str 
    * @return 
    */  
	private static Matcher matcher(String str) {  
		Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);  
		Matcher matcher = pattern.matcher(str);  
		return matcher;  
	}  
    
	/** 
    * 关闭输入流 
    * @param is 
    */  
	private static void close(InputStream is) {  
		if (is != null) {  
			try {  
				is.close();  
			} catch (IOException e) {  
				e.printStackTrace();  
			}  
		}  
	}  
    
	/** 
    * 关闭输出流 
    * @param os 
    */  
	private static void close(OutputStream os) {  
		if (os != null) {  
			try {  
				os.close();  
			} catch (IOException e) {  
				e.printStackTrace();  
			}  
		}  
	}  


	public static void main(String[] args) throws IOException {
		//String url = "F:\\工会大数据比赛\\test.txt";
		/*String url = "C:\\Users\\Administrator\\Desktop\\test.txt";
		List<String> rs = readTxtFile(url);
		for (String s : rs) {
			System.out.println(s);
		}*/
		Map<String,Object> params = new LinkedHashMap<String,Object>();
		params.put("date", "5");  
	    params.put("week", "一");  
	    params.put("sedate", "08时50分-11时50分");  
	    params.put("class", "J1703");  
	    params.put("adress", "软开一部");  
	    params.put("content", "前端基础");  
	    params.put("teacher", "姜坤银");  
	    params.put("ks", "4");  
		String url = "f:\\课时登记表.docx";
		
		//readwriteWord(url,params);
	}
}
