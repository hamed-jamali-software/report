package com.example.report.bildung;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.*;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.languages.ArabicLigaturizer;
import com.itextpdf.text.pdf.languages.LanguageProcessor;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReportV2 {


    public static Font fontTitle;
    public static Font fontSubject;
    public static Font fontText;
    public static Font fontTextBold;

    public static void main(String[] args) {
        createPdf();
    }

    @SneakyThrows
    private static void createPdf() {
        Document doc = new Document(PageSize.A4, 36, 36, 90, 36);
        BaseFont myFont = BaseFont.createFont("B-NAZANIN.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        fontTitle = new Font(myFont, 15, Font.BOLD);
        fontSubject = new Font(myFont, 14, Font.BOLD);
        fontText = new Font(myFont, 12, Font.NORMAL);
        fontTextBold = new Font(myFont, 12, Font.BOLD);

        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("samplev2.pdf"));
        // add header and footer
        HeaderFooterPageEvent event = new HeaderFooterPageEvent();
        writer.setPageEvent(event);
        doc.open();
        String sample = "ﻣﻬﻢ ﻧﻴﺴﺖ ﭼﻘﺪﺭ ﻣﻰ ﻣﺎﻧﻰ، ﻳﮏ ﺭﻭﺯ، ﻳﮏ ﻣﺎﻩ یا ﻳﮏ ﺳﺎﻝ. ﻣﻬﻢ ﮐﻴﻔﻴﺖ ﻣﺎﻧﺪﻥ ﺍﺳﺖ. ﺑﻌﻀﻰ ﻫﺎ ﺩﺭ ﻳﮏ ﺭﻭﺯ" +
                "ﺗﻤﺎﻡ ﺩﻧﻴﺎ ﺭﺍ ﺑﻪ ﺗﻮ ﻫﺪﻳﻪ ﻣﻰ ﺩﻫﻨﺪ. ﮔﺎﻫﻰ ﺑﻌﻀﻰ ﻫﺎ، ﻳﮏ ﻋﻤﺮ ﮐﻨﺎﺭﺕ ﻫﺴﺘﻨﺪ ﺍﻣﺎ ﺟﺰ ﺩﺭﺩ، ﻫﻴﭻ ﭼﻴﺰ ﺑﺮﺍﻳﺖ" +
                "ندارند، ﻭ ﺗﺎ ﺗﻪ ﺭﻭﺣﺖ ﺭﺍ ﻣﻰ ﺧﺮﺍﺷﻨد! ﺑﻌﻀﻰ ﻫﺎ ﻧﺎﺏ ﻫﺴﺘﻨﺪ! ﻭ ﺑﻪ ﺗﻮ ﻟﺤﻈﻪ ﻫﺎﻯ ﻧﺎﺏ ﺗﺮﻯ ﻫﺪﻳﻪ ﻣﻰ ﺩﻫﻨﺪ!" +
                "ﺍﻳﻦ ﺑﻌﻀﻰ ﻫﺎ ﻣﻬﻢ ﻧﻴﺴﺖ ﭼﻘﺪﺭ ﺑﻤﺎﻧﻨﺪ، ﻫﺮ ﭼﻘﺪﺭ ﻫﻢ ﮐﻪ ﺯﻭﺩ ﺑﺮﻭﻧﺪ ﻳﺎﺩﺷﺎﻥ و ﺣﺲ ﺧﻮﺏ ﺑﻮﺩﻧﺸﺎﻥ ﺗﺎ ﻫﻤﻴﺸﻪ ﻫﺴﺖ…";

        Paragraph title = new Paragraph(" ﭼﻘﺪﺭ ﻫﻢ ﮐﻪ ﺯﻭﺩ ﺍﺳﺖ", fontTitle);
        PdfPTable element = newPersianParagraph(sample );
        PdfPTable element2 = newPersianParagraph("ماده ۱۶ ) ","سبد باید حداکثر ﻧﺎﺏ بود را ببماید " );
        PdfPTable element3 = newPersianParagraph(sample );
        PdfPTable element4 = newPersianParagraph(sample );



        doc.add(newPersianParagraph(title));
        doc.add(element);
        doc.add(element2);
        doc.add(newPersianParagraph(title));
        doc.add(newLine());
        doc.add(newLine());
        doc.add(newLine());



        doc.add(element3);
        doc.add(newPersianParagraph(title));

        doc.add(element4);
        doc.close();

    }
    @SneakyThrows
    private static Paragraph newLine() {
        return new Paragraph("\n");
    }
    @SneakyThrows
    private static PdfPTable newPersianParagraph(String text ) {
        Paragraph p = new Paragraph();
//        p.setSpacingBefore(5);
//        p.setSpacingAfter(5);
//        p.setAlignment(Paragraph.ALIGN_RIGHT);
        p.setFont(fontText);
        p.add(text);
        return getPdfPTable(p);
    }

    @SneakyThrows
    private static PdfPTable newPersianParagraph(Paragraph paragraph) {
        return getPdfPTable(paragraph);
    }

    @SneakyThrows
    private static PdfPTable newPersianParagraph(String boldWord,String text ) {
        Chunk BoldChunk = correctBold(boldWord);
        Chunk textChunk = correctText(text);
        Phrase phrase = new Phrase();
        phrase.add(BoldChunk);
        phrase.add(textChunk);
        Paragraph paragraph = new Paragraph();
        paragraph.add(phrase);
        paragraph.add(text);
        PdfPTable pdfPTable = newPersianParagraph(paragraph);
        return pdfPTable;
    }

    private static Chunk correctBold(String word) {
        Chunk chunk = new Chunk(word.concat(" "), fontTextBold);
        return chunk;
    }

    private static Chunk correctText(String words) {
        Chunk chunk = new Chunk(words, fontText);
        return chunk;
    }

    private static PdfPTable getPdfPTable(Paragraph paragraph) {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(paragraph);
        cell.setBorder(0);
        cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        cell.setHorizontalAlignment(Paragraph.ALIGN_JUSTIFIED);
        table.addCell(cell);
        return table;
    }


}
