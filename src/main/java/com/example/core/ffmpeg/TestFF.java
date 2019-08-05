package com.example.core.ffmpeg;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author wangwei
 * @Date 2019/8/2 14:08
 * -描述-
 */
public class TestFF {
    /**
     * 获取视频总时间
     * @param video_path    视频路径
     * @param ffmpeg_path   ffmpeg路径
     * @return
     */
    public static String getVideoTime(String video_path, String ffmpeg_path) {
        List<String> commands = new java.util.ArrayList<String>();
        commands.add(ffmpeg_path);
        commands.add("-i");
        commands.add(video_path);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            final Process p = builder.start();

            //从输入流中读取视频信息
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            //从视频信息中解析时长
            String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
            String regexVideo ="Video: (.*?), (.*?), (.*?)[,\\s]";
            Pattern pattern1 = Pattern.compile(regexDuration);
            Pattern pattern2 = Pattern.compile(regexVideo);
            Matcher m = pattern1.matcher(sb.toString());
            Matcher v = pattern2.matcher(sb.toString());
            if (m.find()) {
                String time = m.group(1);
                System.out.println(video_path+",视频时长："+time+", 开始时间："+m.group(2)+",比特率："+m.group(3)+"kb/s");
                String[] times = time.split(":");
                String need = times[2];
                double sec= Double.parseDouble(need) * 1000;
                System.out.println("视频的时长：" + (int)sec + " 毫秒");
            }

            if(v.find()){
                System.out.println("编码格式 ===" + v.group(1));
                System.out.println("视频格式 ===" + v.group(2));
                System.out.println(" 分辨率  ===" + v.group(3));
                String[] wh = v.group(3).split("x");
                System.out.println("视频的宽：" + wh[0]);
                System.out.println("视频的高：" + wh[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String need = getVideoTime("D:\\software\\ffmpeg\\bin\\解说-坐.webm","D:\\software\\ffmpeg\\bin\\ffmpeg.exe");
    }
}
