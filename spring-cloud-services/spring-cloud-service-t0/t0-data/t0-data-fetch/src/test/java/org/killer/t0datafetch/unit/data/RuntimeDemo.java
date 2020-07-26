package org.killer.t0datafetch.unit.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author killer
 * @date 2020/05/27 - 14:37
 */
public class RuntimeDemo {

    public static void main(String[] args) {
        // 为什么没有输出？？
        Runtime runtime = Runtime.getRuntime();
        try {
            Process exec = runtime.exec("java -version");
            // byte[] buffer = new byte[1];
            // if(exec.getInputStream().read(buffer) != -1) {
            //     System.out.println(new String(buffer));
            // }

            InputStream is = exec.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            String line;
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }


            // int i = exec.waitFor();
            //
            System.out.println(exec.exitValue());
            // is.close();
            // bufferedReader.close();
            // exec.destroy();

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("java");
            InputStream stdin = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(stdin);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            System.out.println("<OUTPUT>");
            while ((line = br.readLine()) != null)
                System.out.println(line);
            System.out.println("</OUTPUT>");
            int exitVal = proc.waitFor();
            System.out.println("Process exitValue: " + exitVal);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        System.out.println("test");
    }

}
