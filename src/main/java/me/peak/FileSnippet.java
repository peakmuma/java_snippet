package me.peak;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileSnippet {
	public static void main(String[] args) throws IOException {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("2021-12-20", new BigDecimal(0));
		jsonObject.put("2021-12-21", 0.004);
		jsonObject.put("2021-12-22", new BigDecimal(5));
		System.out.println(jsonObject.toJSONString());

	}

	static void compareFileContent(String sourceFile, String targetFile) throws IOException {
		List<String> sourceContent = readLines(sourceFile);
		List<String> targetContent = readLines(targetFile);
		for (String bean : sourceContent) {
			if (targetContent.contains(bean) && !bean.startsWith("org.spring")) {
				System.out.println(bean);
			}
		}
	}

	static String parseTable(String sql) {
		if (sql == null) {
			return null;
		}
		int start = sql.lastIndexOf("from");
		if (start == -1) {
			return null;
		}
		start += 5;
		int end = sql.lastIndexOf("where");
		if (end == -1) {
			end = sql.length();
		}
		end--;
		return sql.substring(start, end);
	}


	static void buildCSV(List<List> data, List<String> header) throws IOException {
		File file = new File("/data/test.csv");
		file.createNewFile();
		BufferedWriter csvWritter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)) );
		csvWritter.write(String.join(",", header));
		csvWritter.newLine();
		for (List row : data) {
			csvWritter.write(String.join(",", row));
			csvWritter.newLine();
		}
		csvWritter.flush();
		csvWritter.close();
	}

	static void compareFile(String sourcePath, String targetPath) {
		File sourceFile = new File(sourcePath);
		Set<String> sourceFiles = new HashSet<>();
		iterateAllFileName(sourceFile, sourceFiles);
		File targetFile = new File(targetPath);
		Set<String> targetFiles = new HashSet<>();
		iterateAllFileName(targetFile, targetFiles);
		for (String fileName : sourceFiles) {
			if (fileName.endsWith("java") && targetFiles.contains(fileName))	{
				System.out.println(fileName);
			}
		}

	}

	static void lookLog(String fileName) throws IOException {
		FileReader reader = new FileReader(fileName);
		BufferedReader reader1 = new BufferedReader(reader);
		String line = null;
		while ((line=reader1.readLine()) != null) {
			if (line.contains("queryStuListForExport")) {
				System.out.println(line);
			}
		}
	}

	static List<String> readLines(String path) throws IOException {
		FileReader reader = new FileReader(path);
		BufferedReader bufferedReader = new BufferedReader(reader);
		List<String> res = new ArrayList<>();
		String line;
		while ((line = bufferedReader.readLine())!= null) {
			res.add(line);
		}
		return res;
	}

	static void iterateAllFileName(File file, Set<String> res) {
		if (file.isDirectory()) {
			File[] children = file.listFiles();
			if (children == null) {
				return;
			}
			for (File child : children) {
				iterateAllFileName(child, res);
			}
		} else {
			res.add(file.getName());
		}

	}

}
