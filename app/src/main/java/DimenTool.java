import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * des: hello
 * author: pz
 * date: 2018/4/16
 */
public class DimenTool {

    public static void gen() {
        //以此文件夹下的dimens.xml文件内容为初始值参照
        File file = new File("./app/src/main/res/values/dimens.xml");

        BufferedReader reader = null;
        StringBuilder sw240 = new StringBuilder();
        StringBuilder sw270 = new StringBuilder();
        StringBuilder sw360 = new StringBuilder();
        StringBuilder sw480 = new StringBuilder();
        StringBuilder sw540 = new StringBuilder();
        StringBuilder sw600 = new StringBuilder();



        try {

            System.out.println("生成不同分辨率：");

            reader = new BufferedReader(new FileReader(file));

            String tempString;

            int line = 1;

            // 一次读入一行，直到读入null为文件结束

            while ((tempString = reader.readLine()) != null) {


                if (tempString.contains("</dimen>")) {

                    //tempString = tempString.replaceAll(" ", "");

                    String start = tempString.substring(0, tempString.indexOf(">") + 1);

                    String end = tempString.substring(tempString.lastIndexOf("<") - 2);
                    //截取<dimen></dimen>标签内的内容，从>右括号开始，到左括号减2，取得配置的数字
                    Double num = Double.parseDouble
                            (tempString.substring(tempString.indexOf(">") + 1,
                                    tempString.indexOf("</dimen>") - 2));

                    //根据不同的尺寸，计算新的值，拼接新的字符串，并且结尾处换行。
                    sw240.append(start).append( num * 0.666).append(end).append("\r\n");
                    sw270.append(start).append(num * 0.75).append(end).append("\r\n");
                    sw360.append(start).append(num * 1).append(end).append("\r\n");
                    sw480.append(start).append(num * 1.333).append(end).append("\r\n");
                    sw540.append(start).append(num * 1.5).append(end).append("\r\n");
                    sw600.append(start).append(num * 1.666).append(end).append("\r\n");



                } else {
                    sw240.append(tempString).append("");
                    sw270.append(tempString).append("");
                    sw360.append(tempString).append("");
                    sw480.append(tempString).append("");
                    sw540.append(tempString).append("");
                    sw600.append(tempString).append("");
                }

                line++;

            }

            reader.close();
            System.out.println("<!--  sw240 -->");

            System.out.println(sw240);

            System.out.println("<!--  sw480 -->");

            System.out.println(sw480);

            System.out.println("<!--  sw600 -->");

            System.out.println(sw600);

            System.out.println("<!--  sw720 -->");


            System.out.println("<!--  sw800 -->");


            String sw240file = "./app/src/main/res/values-sw240dp/dimens.xml";
            String sw270file = "./app/src/main/res/values-sw270dp/dimens.xml";
            String sw360file = "./app/src/main/res/values-sw360dp/dimens.xml";
            String sw480file = "./app/src/main/res/values-sw480dp/dimens.xml";
            String sw540file = "./app/src/main/res/values-sw540dp/dimens.xml";
            String sw600file = "./app/src/main/res/values-sw600dp/dimens.xml";

            //将新的内容，写入到指定的文件中去
            writeFile(sw240file, sw240.toString());
            writeFile(sw270file, sw270.toString());
            writeFile(sw360file, sw360.toString());
            writeFile(sw480file, sw480.toString());
            writeFile(sw540file, sw540.toString());
            writeFile(sw600file, sw600.toString());



        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (reader != null) {

                try {

                    reader.close();

                } catch (IOException e1) {

                    e1.printStackTrace();

                }

            }

        }

    }


    /**
     * 写入方法
     *
     */

    public static void writeFile(String file, String text) {

        PrintWriter out = null;

        try {

            out = new PrintWriter(new BufferedWriter(new FileWriter(file)));

            out.println(text);

        } catch (IOException e) {

            e.printStackTrace();

        }



        out.close();

    }
    public static void main(String[] args) {

        gen();

    }

}
