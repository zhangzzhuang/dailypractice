package com.example.sortagorithm.io;

import java.io.*;
import java.util.Objects;

/**
 * @author zhangzhuang
 * @since 2021-04-08
 */
public class ListFileDemo {

	//递归读取文件
	public static void listFile(String path) {
		if (path == null || path.isEmpty()) {
			return;
		}

		File[] files = new File(path).listFiles();
		if (files == null) {
			return;
		}
		for (File file : files) {
			if (file.isFile()) {
				System.out.println(file.getName());
			} else if (file.isDirectory()) {
				System.out.println("Directory:" + file.getName());
				listFile(file.getPath());
			} else {
				System.out.println("Error");
			}
		}

	}


	public static void write(File file) throws IOException {
		OutputStream os = new FileOutputStream(file);
		String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
		os.write(string.getBytes());
		os.close();
	}

	public static void writeBuffer(File file) throws IOException {
		BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream(file));
		String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
		bs.write(string.getBytes());
		bs.close();
	}

	public static String read(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		byte[] bytes = new byte[2048];
		StringBuilder sb = new StringBuilder();
		int length = 0;
		while ((length = is.read(bytes)) != -1) {
			sb.append(new String(bytes, 0, length));
		}
		is.close();
		return sb.toString();
	}

	public static String readBuffer(File file) throws IOException {

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		byte[] bytes = new byte[2048];
		StringBuilder sb = new StringBuilder();
		int length = 0;
		while ((length = bis.read(bytes)) != -1) {
			sb.append(new String(bytes, 0, length));
		}
		bis.close();
		return sb.toString();
	}

	public static void writer(File file) throws IOException {
		FileWriter fw = new FileWriter(file);
		String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
		fw.write(string);
		fw.close();
	}

	public static void writerBuffer(File file) throws IOException {
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
		bw.write(string);
		bw.flush();
		bw.close();
		fw.close();
	}

	public static String reader(File file) throws IOException {
		FileReader fr = new FileReader(file);
		char[] chars = new char[2048];
		StringBuilder sb = new StringBuilder();
		int count;
		while ((count = fr.read(chars)) != -1) {
			sb.append(new String(chars, 0, count));
		}
		fr.close();
		return sb.toString();
	}

	public static String readerBuffer(File file) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		fr.close();
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		/*
			String path = "/Users/basi/Downloads/abc.txt";
			File file = new File(path);
			write(file);
		*/

		/*
			String path = "/Users/basi/Downloads/abc.txt";
			File file = new File(path);
			System.err.println(read(file));
		 */

		/*
			String path = "/Users/basi/Downloads/abc.txt";
			File file = new File(path);
			writeBuffer(file);
		 */

		/*
			String path = "/Users/basi/Downloads/abc.txt";
			File file = new File(path);
			System.err.println(readBuffer(file));
		 */

		/*
			String path = "/Users/basi/Downloads/abc.txt";
			File file = new File(path);
			writer(file);
		 */

		/*
			String path = "/Users/basi/Downloads/abc.txt";
			File file = new File(path);
			writerBuffer(file);
		 */

		/*
			String path = "/Users/basi/Downloads/abc.txt";
			File file = new File(path);
			System.err.println(reader(file));
		 */

		/*
			String path = "/Users/basi/Downloads/abc.txt";
			File file = new File(path);
			System.err.println(readerBuffer(file));
		 */

		/*
			String path = "/Users/basi/Downloads/abc.txt";
			System.out.println(transReadByBuf(path));
		 */

		/*
			String path = "/Users/basi/Downloads/abc.txt";
			transWriteByBuf(path);
		*/

		File file = new File("/Users/basi/Desktop/oom/");
		if (!file.exists()){
			file.mkdirs();
		}

		File tmp = new File(file+File.separator+"jmapHeap.txt");
		String absolutePath = file.getAbsolutePath();
		String path = file.getPath();
		String name = file.getName();

		if (!tmp.exists()){
			tmp.createNewFile();
		}
		System.out.println(absolutePath);
		System.out.println(path);
		System.out.println(name);
		System.out.println(tmp.getAbsolutePath());
		System.out.println(tmp.getPath());
		System.out.println(tmp.getName());

	}

	public static String transReadByBuf(String path) throws IOException {

		InputStream is = new FileInputStream(new File(path));
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		isr.close();
		isr.close();
		return sb.toString();
	}

	public static void transWriteByBuf(String path) throws IOException {
		OutputStream os = new FileOutputStream(new File(path));
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
		String string = "松下问童子，言师采药去。只在此山中，云深不知处。";
		bw.write(string);
		bw.flush();
		bw.close();
	}


}
