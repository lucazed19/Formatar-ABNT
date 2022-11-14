package com.mycompany.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


public class App 
{
    public static boolean formatar(String filePath, String fileName , String fileDestination){
    	try (XWPFDocument textoFormatado = new XWPFDocument()){
    		fileName = fileName.replace(".docx", "");
			FileInputStream file = new FileInputStream(filePath);
			
			XWPFDocument docx = new XWPFDocument(file);
			
			List<XWPFParagraph> listaParagrafos = docx.getParagraphs();
			
			for(XWPFParagraph paragrafo : listaParagrafos) {
				String paragrafoText = paragrafo.getText();
				XWPFParagraph newParagraph = textoFormatado.createParagraph();
				newParagraph.setAlignment(ParagraphAlignment.BOTH);
				XWPFRun paragraphRun = newParagraph.createRun();
				paragraphRun.setText(paragrafoText);
				paragraphRun.setFontFamily("Times New Roman");
				paragraphRun.setFontSize(12);
				
			}
			
			
			textoFormatado.write(new FileOutputStream(new File(fileDestination+"\\"+fileName+"-formatado.docx")));
			
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
}
