package com.example.report.bildung;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.SneakyThrows;

import java.io.FileOutputStream;

public class ReportV3 {
    @SneakyThrows
    public static void main(String[] args) {
        CustomItext customItext =new CustomItext("text-pdf.pdf");

        String sample = "ﻣﻬﻢ ﻧﻴﺴﺖ ﭼﻘﺪﺭ ﻣﻰ ﻣﺎﻧﻰ، ﻳﮏ ﺭﻭﺯ، ﻳﮏ ﻣﺎﻩ یا ﻳﮏ ﺳﺎﻝ. ﻣﻬﻢ ﮐﻴﻔﻴﺖ ﻣﺎﻧﺪﻥ ﺍﺳﺖ. ﺑﻌﻀﻰ ﻫﺎ ﺩﺭ ﻳﮏ ﺭﻭﺯ" +
                "ﺗﻤﺎﻡ ﺩﻧﻴﺎ ﺭﺍ ﺑﻪ ﺗﻮ ﻫﺪﻳﻪ ﻣﻰ ﺩﻫﻨﺪ. ﮔﺎﻫﻰ ﺑﻌﻀﻰ ﻫﺎ، ﻳﮏ ﻋﻤﺮ ﮐﻨﺎﺭﺕ ﻫﺴﺘﻨﺪ ﺍﻣﺎ ﺟﺰ ﺩﺭﺩ، ﻫﻴﭻ ﭼﻴﺰ ﺑﺮﺍﻳﺖ" +
                "ندارند، ﻭ ﺗﺎ ﺗﻪ ﺭﻭﺣﺖ ﺭﺍ ﻣﻰ ﺧﺮﺍﺷﻨد! ﺑﻌﻀﻰ ﻫﺎ ﻧﺎﺏ ﻫﺴﺘﻨﺪ! ﻭ ﺑﻪ ﺗﻮ ﻟﺤﻈﻪ ﻫﺎﻯ ﻧﺎﺏ ﺗﺮﻯ ﻫﺪﻳﻪ ﻣﻰ ﺩﻫﻨﺪ!" +
                "ﺍﻳﻦ ﺑﻌﻀﻰ ﻫﺎ ﻣﻬﻢ ﻧﻴﺴﺖ ﭼﻘﺪﺭ ﺑﻤﺎﻧﻨﺪ، ﻫﺮ ﭼﻘﺪﺭ ﻫﻢ ﮐﻪ ﺯﻭﺩ ﺑﺮﻭﻧﺪ ﻳﺎﺩﺷﺎﻥ و ﺣﺲ ﺧﻮﺏ ﺑﻮﺩﻧﺸﺎﻥ ﺗﺎ ﻫﻤﻴﺸﻪ ﻫﺴﺖ…";


        Document document = new Document(PageSize.A4, 36, 36, 90, 36);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("samplev2.pdf"));
        customItext.headerAndFooter(writer);
        document.open();
        document.add(customItext.newTitle("ﭼﻘﺪﺭ ﻫﻢ ﮐﻪ ﺯﻭﺩ ﺍﺳﺖ"));
        document.add(customItext.newLine());
        document.add(customItext.newPersianParagraph(sample));
        document.add(customItext.newPersianParagraph("ماده ۱۶ ) ","سبد باید حداکثر ﻧﺎﺏ بود را ببماید " ));
        document.add(customItext.newPersianParagraph(sample));
        document.add(customItext.newPersianParagraph(sample));
        document.add(customItext.newPersianParagraph(sample));

        document.close();

    }
}
