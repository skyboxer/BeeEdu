package com.enablue.test;

import com.enablue.googleutil.GoogleApi;
import com.enablue.util.PDFUtil;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;

import java.io.File;

public class test {

    public static void main(String[] args) throws Exception {
        PDFUtil.PdfToWord(new File("[V版]魔鬼逻辑学_凡禹_江西美术_2017.9.pdf"));
    }
}
