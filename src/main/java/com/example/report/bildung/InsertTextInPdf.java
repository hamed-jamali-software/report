package com.example.report.bildung;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.languages.ArabicLigaturizer;
import com.itextpdf.text.pdf.languages.LanguageProcessor;
import lombok.SneakyThrows;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class InsertTextInPdf {
    public static final String FONT = "B-NAZANIN.TTF";
    public static Font fontTitle;
    public static Font fontSubject;
    public static Font fontText;
    public static Font fontTextBold;

    private static Document document;

    @SneakyThrows
    public InsertTextInPdf() {
        BaseFont bfComic = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
          fontTitle = new Font(bfComic, 16, Font.BOLD);
          fontSubject = new Font(bfComic, 14, Font.BOLD);
          fontText = new Font(bfComic, 12, Font.NORMAL);
          fontTextBold = new Font(bfComic, 12, Font.BOLD);

          pdfFile();
    }


    @SneakyThrows
    public Document pdfFile() {
        document = new Document(PageSize.A4, 36, 36, 90, 36);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("sample.pdf"));
        writer.setRunDirection(PdfWriter.RUN_DIRECTION_LTR);
        writer.setViewerPreferences(PdfWriter.DirectionR2L);
        FontFactory.getFontImp().defaultEncoding = BaseFont.WINANSI;


        // add header and footer
        HeaderFooterPageEvent event = new HeaderFooterPageEvent();
        writer.setPageEvent(event);
        document.open();

        String sample = "ﻣﻬﻢ ﻧﻴﺴﺖ ﭼﻘﺪﺭ ﻣﻰ ﻣﺎﻧﻰ، ﻳﮏ ﺭﻭﺯ، ﻳﮏ ﻣﺎﻩ یا ﻳﮏ ﺳﺎﻝ. ﻣﻬﻢ ﮐﻴﻔﻴﺖ ﻣﺎﻧﺪﻥ ﺍﺳﺖ. ﺑﻌﻀﻰ ﻫﺎ ﺩﺭ ﻳﮏ ﺭﻭﺯ" +
                "ﺗﻤﺎﻡ ﺩﻧﻴﺎ ﺭﺍ ﺑﻪ ﺗﻮ ﻫﺪﻳﻪ ﻣﻰ ﺩﻫﻨﺪ. ﮔﺎﻫﻰ ﺑﻌﻀﻰ ﻫﺎ، ﻳﮏ ﻋﻤﺮ ﮐﻨﺎﺭﺕ ﻫﺴﺘﻨﺪ ﺍﻣﺎ ﺟﺰ ﺩﺭﺩ، ﻫﻴﭻ ﭼﻴﺰ ﺑﺮﺍﻳﺖ" +
                "ندارند، ﻭ ﺗﺎ ﺗﻪ ﺭﻭﺣﺖ ﺭﺍ ﻣﻰ ﺧﺮﺍﺷﻨد! ﺑﻌﻀﻰ ﻫﺎ ﻧﺎﺏ ﻫﺴﺘﻨﺪ! ﻭ ﺑﻪ ﺗﻮ ﻟﺤﻈﻪ ﻫﺎﻯ ﻧﺎﺏ ﺗﺮﻯ ﻫﺪﻳﻪ ﻣﻰ ﺩﻫﻨﺪ!" +
                "ﺍﻳﻦ ﺑﻌﻀﻰ ﻫﺎ ﻣﻬﻢ ﻧﻴﺴﺖ ﭼﻘﺪﺭ ﺑﻤﺎﻧﻨﺪ، ﻫﺮ ﭼﻘﺪﺭ ﻫﻢ ﮐﻪ ﺯﻭﺩ ﺑﺮﻭﻧﺪ ﻳﺎﺩﺷﺎﻥ و ﺣﺲ ﺧﻮﺏ ﺑﻮﺩﻧﺸﺎﻥ ﺗﺎ ﻫﻤﻴﺸﻪ ﻫﺴﺖ…";

        List<Paragraph> paragraphs = persianParagraph(sample);


        document.newPage();
        Paragraph pg2 = new Paragraph("Television is one of the many wonders of modern science and technology. It was invented in England by the Scottish scientist J.N. Baird in 1928 and the British Broadcasting Corporation was the first to broadcast television images in 1929. Previously the radio helped us hear things from far and near");
        document.add(pg2);
        document.add(new Paragraph("This is Page two"));
        document.close();
        return document;
    }


    private static List<Paragraph> persianParagraph(String text ) throws IOException {

        List<String> parseLines = parseLines(text,document.getPageSize().getWidth()  , fontText, "fa");

        LanguageProcessor al = new ArabicLigaturizer();
//        Collections.reverse(parseLines);
        List<Paragraph> list = new ArrayList<Paragraph>();

        parseLines.forEach(s1 -> {
            Phrase element = new Phrase(al.process(s1 + " "), fontText);
            Paragraph preface = new Paragraph(element);
            preface.setAlignment(Element.ALIGN_RIGHT);
            list.add(preface);
            try {
                document.add(preface);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
        });
        return list;
    }

    private static List<String> parseLines(String text, float width, Font font, String languageTag) throws IOException {
        List<String> lines = new ArrayList<String>();
        int lastSpace = -1;
        while (text.length() > 0) {
            int spaceIndex = text.indexOf(' ', lastSpace + 1);
            if (spaceIndex < 0)
                spaceIndex = text.length();
            String subString = text.substring(0, spaceIndex);
            float widthPoint = font.getCalculatedBaseFont(false).getWidthPoint(subString, font.getCalculatedSize()+1.7F);
            float size = widthPoint;
            if (size > width) {
                if (lastSpace < 0) {
                    lastSpace = spaceIndex;
                }
                subString = text.substring(0, lastSpace);
                lines.add(subString);
                text = text.substring(lastSpace).trim();
                lastSpace = -1;
            } else if (spaceIndex == text.length()) {
                lines.add(text);
                text = "";
            } else {
                lastSpace = spaceIndex;
            }
        }
//        if (languageTag.equals("fa"))
//            Collections.reverse(lines);
        return lines;
    }

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("PDF Created!");
        InsertTextInPdf pdf = new InsertTextInPdf();
        pdf.pdfFile();
    }
}
