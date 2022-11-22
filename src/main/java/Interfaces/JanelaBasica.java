package Interfaces;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.mycompany.app.App;

public class JanelaBasica extends JFrame {
	private static final long serialVersionUID = -880380318589595500L;
	
	Container contentPane = getContentPane();
	String filepath;
	String fileDestination = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
	String fileName;
	JLabel nomeArquivo = new JLabel();
	JButton formatar = new JButton("Formatar");
	
	public JanelaBasica() {
		super();
		setSize(800, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		contentPane.setLayout(new BorderLayout());
		
		JLabel titulo = new JLabel("Selecione o arquivo docx para formatar", JLabel.CENTER);
		titulo.setFont(new Font("Serif", Font.PLAIN, 24));
		contentPane.add(titulo, BorderLayout.PAGE_START);
		
		JButton selecionarDiretorio = new JButton("<HTML>Escolha o<br>diretório de<br>destino</HTML>");
		selecionarDiretorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarDiretorio();
				
			}
		});
		contentPane.add(selecionarDiretorio, BorderLayout.WEST);
		selecionarDiretorio.setPreferredSize(new Dimension(100,40));
		
		JButton selecionarArquivo = new JButton("Selecione Arquivo");
		selecionarArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				selecionarArquivo();
			}
		});
		selecionarArquivo.setPreferredSize(new Dimension(40,40));
		contentPane.add(selecionarArquivo, BorderLayout.PAGE_END);
		
		formatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (App.formatar(filepath, fileName, fileDestination)) {
					JOptionPane.showMessageDialog(null, "Texto Formatado");
				}else {
					JOptionPane.showMessageDialog(null,
						    "Erro",
						    "Inane error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void selecionarArquivo() {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File(FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath()));
		int i = fc.showOpenDialog(this);
		if(i==JFileChooser.APPROVE_OPTION){    
			filepath=fc.getSelectedFile().getPath();
			fileName=fc.getSelectedFile().getName();
	        if (filepath.contains(".docx")) {
	        	nomeArquivo.setText(fileName);
	        	nomeArquivo.setHorizontalAlignment(JLabel.CENTER);
	        	nomeArquivo.setFont(new Font("Serif", Font.PLAIN, 18));
	        	contentPane.add(nomeArquivo, BorderLayout.CENTER);
	        	contentPane.add(formatar, BorderLayout.EAST);
	        	setVisible(true);
	        	formatar.setVisible(true);
	        }else{
	        	nomeArquivo.setText("Arquivo inválido");
	        	nomeArquivo.setHorizontalAlignment(JLabel.CENTER);
	        	nomeArquivo.setFont(new Font("Serif", Font.PLAIN, 18));
	        	contentPane.add(nomeArquivo, BorderLayout.CENTER);
	        	setVisible(true);
	        	formatar.setVisible(false);
	        }
	    } 
	}
	
	private void selecionarDiretorio(){
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File(FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath()));
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int i = fc.showOpenDialog(this);
		if(i==JFileChooser.APPROVE_OPTION){
			fileDestination = fc.getSelectedFile().getAbsolutePath();
		}
	}
	
	
}

