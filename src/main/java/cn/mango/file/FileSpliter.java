package cn.mango.file;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileSpliter {

    private static final Logger log = LoggerFactory.getLogger(FileSpliter.class);
    /**
     * 按行分割文件
     *
     * @param sourceFilePath      为源文件路径
     * @param targetDirectoryPath 文件分割后存放的目标目录
     * @param number              切割成多少个文件
     */

    public static List<String> splitFileByLine(String sourceFilePath, String targetDirectoryPath, int number) throws IOException {
        //源文件名
        String sourceFileName = sourceFilePath
                .substring(sourceFilePath.lastIndexOf(File.separator) + 1, sourceFilePath.lastIndexOf("."));
        //切割后的文件名
        String splitFileName = targetDirectoryPath + File.separator + sourceFileName + "-%s.txt";
        File targetDirectory = new File(targetDirectoryPath);
        if (!targetDirectory.exists()) {
            targetDirectory.mkdirs();
        }

        List<String> resultFileNames = Lists.newArrayList();
        //字符输出流
        PrintWriter pw = null;
        String tempLine;
        //本次行数累计 , 达到rows开辟新文件
        int lineNum = 0;
        //当前文件索引
        int splitFileIndex = 1;
        //每个文件行数
        long lines = Files.lines(Paths.get(sourceFilePath)).count();
        int rows = (int) Math.ceil(1.0 * lines / number);

        log.info("原文件行数:{}, 新文件行数:{}", lines, rows);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFilePath)))) {
            String parsedFileName = String.format(splitFileName, splitFileIndex);
            pw = new PrintWriter(parsedFileName);
            resultFileNames.add(parsedFileName);
            while ((tempLine = br.readLine()) != null) {
                //需要换新文件
                if (lineNum > 0 && lineNum % rows == 0) {
                    pw.flush();
                    pw.close();
                    parsedFileName = String.format(splitFileName, ++splitFileIndex);
                    pw = new PrintWriter(parsedFileName);
                    resultFileNames.add(parsedFileName);
                }
                pw.write(tempLine + "\n");
                lineNum++;
            }
            log.info("返回文件列表：{}", resultFileNames.toString());
            return resultFileNames;
        } catch (Exception e) {
            log.error("切割文件异常:{}", e);
        } finally {
            if (null != pw) {
                pw.flush();
                pw.close();
            }
        }
        return resultFileNames;
    }

    public static void main(String[] args) {
        String sourceFilePath = "C:\\Users\\Mangotea\\IdeaProjects\\mango-crawler-first\\src\\main\\resources\\sidList.txt";
        String targetDirectoryPath = "C:\\Users\\Mangotea\\IdeaProjects\\mango-crawler-first\\src\\main\\resources\\newList";
        List<String> resultFileNames = null;
        try {
            resultFileNames = splitFileByLine(sourceFilePath, targetDirectoryPath, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
