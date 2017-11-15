package com.continental.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandlingLambda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			//list all
			Files.list(Paths.get("."))
			.forEach(System.out::println);
			//list only files
			Files.list(Paths.get("."))
	        .filter(Files::isRegularFile)
	        .forEach(System.out::println);
			//list files and subdirectories
			Files.newDirectoryStream(Paths.get("."))
	        .forEach(System.out::println);
			
			//list files with certain extension
			Files.newDirectoryStream(Paths.get("."),
			        path -> path.toString().endsWith(".java"))
			        .forEach(System.out::println);
			//
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
