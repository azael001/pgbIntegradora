import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) {
//        ProcessBuilder pb1= new ProcessBuilder("cmd","/c" ,"start msconfig"); Aquí se hace directamente
        //        try {
//            pb1.start();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        String relativePath= "./src/msconfig.bat";
        try {
            ProcessBuilder pb = new ProcessBuilder(relativePath);
            Process pj = new ProcessBuilder("java", "-jar", "aquivalarutadeljar").start();
            pb.start();
            OutputStream osr = pj.getOutputStream();
            InputStreamReader isr = new InputStreamReader(pj.getInputStream());
            BufferedReader br = new BufferedReader(isr);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}