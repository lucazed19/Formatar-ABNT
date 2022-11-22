package com.mycompany.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;


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
				paragraphRun.setFontFamily("Arial");
				paragraphRun.setFontSize(12);
			}
			
			CTSectPr sectPr = textoFormatado.getDocument().getBody().addNewSectPr();
		    CTPageMar pageMar = sectPr.addNewPgMar();
		    pageMar.setLeft(BigInteger.valueOf(1700L));
		    pageMar.setTop(BigInteger.valueOf(1700L));
		    pageMar.setRight(BigInteger.valueOf(1135L));
		    pageMar.setTop(BigInteger.valueOf(1135L));
			
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
