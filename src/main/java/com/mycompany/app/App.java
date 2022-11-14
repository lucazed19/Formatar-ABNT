package com.mycompany.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


public class App 
{
    public static boolean formatar(String filePath, String fileName , String fileDestination){
    	try (XWPFDocument textoFormatado = new XWPFDocument()){
			FileInputStream file = new FileInputStream(filePath);
			fileName = fileName.replace(".docx", "");
			
//			XWPFDocument docx = new XWPFDocument(file);
			
//			List<XWPFParagraph> listaParagrafos = docx.getParagraphs();
//			
//			String teste = null;
//			for(XWPFParagraph paragrafo : listaParagrafos) {
//				System.out.println(paragrafo.getText());
//				teste += paragrafo.getText();
//			}
			
//			XWPFParagraph Teste = textoFormatado.createParagraph();
//			XWPFRun testeRun = Teste.createRun();
//			testeRun.setText(teste);
			
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
