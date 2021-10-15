package me.peak;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileSnippet {
	public static void main(String[] args) throws IOException {
//		lookLog("");

		String sourcePath = "/Users/gaolei/IdeaProjects/data-di-apollo/apollo-server/src/main/java/com/mobike/server/bi";
//		String sourcePath ="/Users/gaolei/IdeaProjects/data-di-apollo/server-authority/src/main/java/com/mobike/server/user";
//		String targetPath = "/Users/gaolei/IdeaProjects/data-di-apollo/self-service-bi/apollo-core/src/main/java/com/sankuai/bike/apollo";
		String targetPath = "/Users/gaolei/IdeaProjects/data-di-apollo/self-service-bi/apollo-query/src/main/java/com/mobike/data/bi";
		compareFile(sourcePath, targetPath);

//		String source = "/Users/gaolei/Documents/basebean";
//		String target = "/Users/gaolei/Documents/querybean";
//		compareFileContent(source, target);

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
