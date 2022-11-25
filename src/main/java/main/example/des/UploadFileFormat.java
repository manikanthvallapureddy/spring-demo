package main.example.des;

import org.bouncycastle.crypto.RuntimeCryptoException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;  
import org.apache.pdfbox.pdmodel.PDPage;  

public class UploadFileFormat {

	public void fileFormat(String extension, MultipartFile multipartFile) {
		if (!(extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("pdf"))) {
			throw new RuntimeCryptoException("file is not supported in the system");

		}
	}
		public void generatePDFile() throws IOException {
			PDDocument pdfdoc= new PDDocument();  
			pdfdoc.addPage(new PDPage());  
			//path where the PDF file will be store 
			pdfdoc.save("");
		}

	}


