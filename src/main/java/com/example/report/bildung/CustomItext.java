package com.example.report.bildung;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.SneakyThrows;

import java.io.FileOutputStream;

public class CustomItext {


    public static Font fontTitle;
    public static Font fontSubject;
    public static Font fontText;
    public static Font fontTextBold;
    private String fileName;
    private Document document;

    @SneakyThrows
    public CustomItext(String fileName) {
        instanceFont();
    }
    @SneakyThrows
    private void instanceFont() {
        BaseFont myFont = BaseFont.createFont("B-NAZANIN.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        fontTitle = new Font(myFont, 15, Font.BOLD);
        fontSubject = new Font(myFont, 14, Font.BOLD);
        fontText = new Font(myFont, 12, Font.NORMAL);
        fontTextBold = new Font(myFont, 12, Font.BOLD);
    }
    @SneakyThrows
    public void headerAndFooter(PdfWriter writer) {
        HeaderFooterPageEvent event = new HeaderFooterPageEvent();
        writer.setPageEvent(event);
    }

    @SneakyThrows
    public PdfPTable newTitle(String title) {
        Paragraph element = new Paragraph(title, fontTitle);
        return newPersianParagraph(element);
    }

    @SneakyThrows
    public Paragraph newLine() {
        return new Paragraph("\n");
    }

    @SneakyThrows
    public PdfPTable newPersianParagraph(String text) {
        Paragraph p = new Paragraph();
//        p.setSpacingBefore(5);
//        p.setSpacingAfter(5);
//        p.setAlignment(Paragraph.ALIGN_RIGHT);
        p.setFont(fontText);
        p.add(text);
        return getPdfPTable(p);
    }

    @SneakyThrows
    public PdfPTable newPersianParagraph(Paragraph paragraph) {
        return getPdfPTable(paragraph);
    }

    @SneakyThrows
    public PdfPTable newPersianParagraph(String boldWord, String text) {
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

    private Chunk correctBold(String word) {
        Chunk chunk = new Chunk(word.concat(" "), fontTextBold);
        return chunk;
    }

    private Chunk correctText(String words) {
        Chunk chunk = new Chunk(words, fontText);
        return chunk;
    }

    private PdfPTable getPdfPTable(Paragraph paragraph) {
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
