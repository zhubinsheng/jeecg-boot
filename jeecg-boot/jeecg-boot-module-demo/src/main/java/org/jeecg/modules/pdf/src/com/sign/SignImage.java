package org.jeecg.modules.pdf.src.com.sign;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;


public class SignImage {

    public static String filePath = "D:\\Users\\zhubinsheng\\Documents\\GitHub\\jeecg-boot\\jeecg-boot\\jeecg-boot-module-demo\\src\\main\\java\\org\\jeecg\\modules\\pdf\\file";


    /**
     * @param doctorName
     *            String 医生名字
     * @param hospitalName
     *            String 医生名称
     * @param date
     *            String 签名日期
     *            图片高度
     * @param jpgname
     *            String jpg图片名
     * @return
     */
    public static boolean createSignTextImg(
            String doctorName, //
            String hospitalName, //
            String date,
            String jpgname) {
        int width = 255;
        int height = 100;
        FileOutputStream out = null;
        //背景色
        Color bgcolor = Color.WHITE;
        //字色
        Color fontcolor = Color.RED;
        Font doctorNameFont = new Font(null, Font.BOLD, 20);
        Font othorTextFont = new Font(null, Font.BOLD, 18);
        try { // 宽度 高度
            BufferedImage bimage = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bimage.createGraphics();
            g.setColor(bgcolor); // 背景色
            g.fillRect(0, 0, width, height); // 画一个矩形
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON); // 去除锯齿(当设置的字体过大的时候,会出现锯齿)

            g.setColor(Color.RED);
            g.fillRect(0, 0, 8, height);
            g.fillRect(0, 0, width, 8);
            g.fillRect(0, height - 8, width, height);
            g.fillRect(width - 8, 0, width, height);

            g.setColor(fontcolor); // 字的颜色
            g.setFont(doctorNameFont); // 字体字形字号
            FontMetrics fm = FontDesignMetrics.getMetrics(doctorNameFont);
            int font1_Hight = fm.getHeight();
            int strWidth = fm.stringWidth(doctorName);
            int y = 35;
            int x = (width - strWidth) / 2;
            g.drawString(doctorName, x, y); // 在指定坐标除添加文字

            g.setFont(othorTextFont); // 字体字形字号

            fm = FontDesignMetrics.getMetrics(othorTextFont);
            int font2_Hight = fm.getHeight();
            strWidth = fm.stringWidth(hospitalName);
            x = (width - strWidth) / 2;
            g.drawString(hospitalName, x, y + font1_Hight); // 在指定坐标除添加文字

            strWidth = fm.stringWidth(date);
            x = (width - strWidth) / 2;
            g.drawString(date, x, y + font1_Hight + font2_Hight); // 在指定坐标除添加文字

            g.dispose();
//            out = new FileOutputStream(jpgname); // 指定输出文件

            //将bufferedImage对象输出到磁盘上
            ImageIO.write(bimage,"jpg",new File(filePath+jpgname));


//            out.flush();
            return true;
        } catch (Exception e) {
            return false;
        }finally{
//            if(out!=null){
//                try {
//                    out.close();
//                } catch (IOException e) {
//                }
//            }
        }
    }
    public static void main(String[] args) {
        createSignTextImg("朱斌生", "在线签名", "2021.04.20",   "\\signImage.jpg");
    }
}