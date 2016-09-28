package com.coral.practice.utils;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.text.PDFTextStripper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by qiuhai on 2016/8/30.
 */
public class PDFUtils {

    public static void pdfParser(String path){
        InputStream in = null;
        File pdfFile = new File(path);
        PDDocument document = null;
        try{
            in = new FileInputStream(pdfFile);
            document=PDDocument.load(in);

            int pageCount = document.getNumberOfPages();
            System.out.println("总页数:"+pageCount);

            PDPageTree pdPages = document.getPages();
            Iterator<PDPage> it = pdPages.iterator();
            while (it.hasNext()){
                PDPage pdPage = it.next();

            }
            /** 文档属性信息 **/
            PDDocumentInformation info = document.getDocumentInformation();
            System.out.println( "标题:" + info.getTitle() );
            System.out.println( "主题:" + info.getSubject() );
            System.out.println( "作者:" + info.getAuthor() );
            System.out.println( "关键字:" + info.getKeywords() );

            System.out.println( "应用程序:" + info.getCreator() );
            System.out.println( "pdf 制作程序:" + info.getProducer() );

            System.out.println( "作者:" + info.getTrapped() );

            System.out.println( "创建时间:" + info.getCreationDate().toString());
            System.out.println( "修改时间:" + info.getModificationDate().toString());
            //获取内容信息
            PDFTextStripper pts = new PDFTextStripper();
            String content = pts.getText( document );
            System.out.println( "内容:" + content );


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String [] args){
        String path="C:/Users/qiuhai/Desktop/191.pdf";
        PDFUtils.pdfParser(path);

    }

}
