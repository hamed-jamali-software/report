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


    public static void main(String[] args) {
        createPdf();
    }

    @SneakyThrows
    private static void createPdf() {
        Document doc = new Document(PageSize.A4, 36, 36, 90, 36);
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("samplev2.pdf"));
        // add header and footer
        HeaderFooterPageEvent event = new HeaderFooterPageEvent();
        writer.setPageEvent(event);
        doc.open();
        String sample = "ﻣﻬﻢ ﻧﻴﺴﺖ ﭼﻘﺪﺭ ﻣﻰ ﻣﺎﻧﻰ، ﻳﮏ ﺭﻭﺯ، ﻳﮏ ﻣﺎﻩ یا ﻳﮏ ﺳﺎﻝ. ﻣﻬﻢ ﮐﻴﻔﻴﺖ ﻣﺎﻧﺪﻥ ﺍﺳﺖ. ﺑﻌﻀﻰ ﻫﺎ ﺩﺭ ﻳﮏ ﺭﻭﺯ" +
                "ﺗﻤﺎﻡ ﺩﻧﻴﺎ ﺭﺍ ﺑﻪ ﺗﻮ ﻫﺪﻳﻪ ﻣﻰ ﺩﻫﻨﺪ. ﮔﺎﻫﻰ ﺑﻌﻀﻰ ﻫﺎ، ﻳﮏ ﻋﻤﺮ ﮐﻨﺎﺭﺕ ﻫﺴﺘﻨﺪ ﺍﻣﺎ ﺟﺰ ﺩﺭﺩ، ﻫﻴﭻ ﭼﻴﺰ ﺑﺮﺍﻳﺖ" +
                "ندارند، ﻭ ﺗﺎ ﺗﻪ ﺭﻭﺣﺖ ﺭﺍ ﻣﻰ ﺧﺮﺍﺷﻨد! ﺑﻌﻀﻰ ﻫﺎ ﻧﺎﺏ ﻫﺴﺘﻨﺪ! ﻭ ﺑﻪ ﺗﻮ ﻟﺤﻈﻪ ﻫﺎﻯ ﻧﺎﺏ ﺗﺮﻯ ﻫﺪﻳﻪ ﻣﻰ ﺩﻫﻨﺪ!" +
                "ﺍﻳﻦ ﺑﻌﻀﻰ ﻫﺎ ﻣﻬﻢ ﻧﻴﺴﺖ ﭼﻘﺪﺭ ﺑﻤﺎﻧﻨﺪ، ﻫﺮ ﭼﻘﺪﺭ ﻫﻢ ﮐﻪ ﺯﻭﺩ ﺑﺮﻭﻧﺪ ﻳﺎﺩﺷﺎﻥ و ﺣﺲ ﺧﻮﺏ ﺑﻮﺩﻧﺸﺎﻥ ﺗﺎ ﻫﻤﻴﺸﻪ ﻫﺴﺖ…";
        PdfPTable element = newPersianParagraph(sample);
         doc.add(element);
        doc.close();

    }

    @SneakyThrows
    private static PdfPTable newPersianParagraph(String text) {
        Paragraph p = new Paragraph();
        BaseFont myFont = BaseFont.createFont("B-NAZANIN.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font paraFont = new Font(myFont, 12);
        p.setSpacingBefore(5);
        p.setSpacingAfter(5);
        p.setAlignment(Paragraph.ALIGN_RIGHT);
        p.setFont(paraFont);

        p.add(text);
        PdfPTable table = new PdfPTable(1);

        table.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(p);
        cell.setBorder(0);
        cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        cell.setHorizontalAlignment(Paragraph.ALIGN_JUSTIFIED);
        table.addCell(cell);
        return table;
    }


}
