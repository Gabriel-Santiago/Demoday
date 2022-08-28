package mandacaru.service.util;

import java.io.ByteArrayOutputStream;
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
			
			 Document document = new Document();
			 ByteArrayOutputStream out = new ByteArrayOutputStream();
	         try {
	
	             PdfWriter.getInstance(document, out);
	             document.open();
	
	             // adicionando um parágrafo no documento
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
	         document.close();
	        
			return out.toByteArray();
	     	}

		 }
	 
	

