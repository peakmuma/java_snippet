package me.peak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class CmdRunner {
//	static String command = "dir";
	static String command = "E:\\software\\ffmpeg-3.4.2-win64-static\\bin\\ffmpeg -y -f gdigrab -framerate 30 -offset_x 630 -offset_y 350 -video_size 800x450 -show_region 1 -i desktop  -f dshow -i audio=\"virtual-audio-capturer\" -t 9 test.mp4";
	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec("cmd.exe /c " + command);
			BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
			BufferedReader stderrReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), Charset.forName("GBK")));
			String line;
			System.out.println("OUTPUT");
			while ((line = stdoutReader.readLine()) != null) {
//				System.out.println(line);
			}
			System.out.println("ERROR");
			while ((line = stderrReader.readLine()) != null) {
				System.out.println(line);
			}
			int exitVal = process.waitFor();
			System.out.println("process exit value is " + exitVal);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
