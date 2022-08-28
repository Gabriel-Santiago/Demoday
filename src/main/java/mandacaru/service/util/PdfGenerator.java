package mandacaru.service.util;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import mandacaru.model.Imovel;

public class PdfGenerator {

	public byte[] criarPdf(Imovel imovel) throws IOException {
			
			 String caminhoRelatorio = "D:\\teste\\Imovel.pdf";
			
			 Document document = new Document();
	         try {
	
	             PdfWriter.getInstance(document, new FileOutputStream(caminhoRelatorio));
	             document.open();
	
	             // adicionando um parágrafo no documento
	             document.add(new Paragraph("id:"+ imovel.getId()));
	             document.add(new Paragraph("Titulo:"+ imovel.getTitulo()));
	             document.add(new Paragraph("Endereço:"+ imovel.getEndereco()));
	             document.add(new Paragraph("Metros Quadrados do terreno:"+ imovel.getMetros_quadrados_de_terreno()));
	             document.add(new Paragraph("Qunatidade de Quartos:"+ imovel.getQuantidade_de_quartos()));
	             document.add(new Paragraph("Quantidade de Banheiros:"+ imovel.getQuantidade_de_banheiros()));
	             document.add(new Paragraph("Quantidade de Vagas na Garagem:"+ imovel.getQuantidade_de_vagas_de_garagem()));
	             document.add(new Paragraph("Preço:"+ imovel.getPreco()));
	         }
	         catch(DocumentException de) {
	             System.err.println(de.getMessage());
	         }
	         catch(IOException ioe) {
	             System.err.println(ioe.getMessage());
	         }
	         document.close();
	        
	        Path pdfPath = Paths.get("caminhoRelatorio");
	        byte[] pdf = Files.readAllBytes(pdfPath);
	        
			return pdf;
	     	}

		 }
	 
	

