package com.example.formapi.ocr;

import org.springframework.stereotype.Service;

@Service
public class OcrService {

    public String extractAddress() {
        return "Mun.Bucuresti Sec.6 Str.Economu Cezarescu nr.34-42 bl.1 sc.1 et.3 ap.45";
    }

    public String extractFirstName() {
        return "Andrei";
    }

    public String extractLastName() {
        return "Manolache";
    }

    public String extractCnp() {
        return "1981128256721";
    }


//    public String extractAddress(File imageFile) throws TesseractException {
//        ITesseract tesseract = new Tesseract();
//
//        try {
//            String dataPath = new File("src/main/resources/static/").getAbsolutePath();
//            tesseract.setDatapath(dataPath);
//            tesseract.setTessVariable("debug_file", "src/main/resources/static/");
//            tesseract.setLanguage("eng"); // limba română
//
//            return tesseract.doOCR(imageFile);
//
//        } catch (TesseractException e) {
//            e.printStackTrace();
//            return "Eroare OCR: " + e.getMessage();
//        }
//    }
}
