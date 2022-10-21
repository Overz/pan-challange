package com.example.challange;

import static com.example.challange.Constants.getPath;

import java.io.IOException;
import java.nio.file.Files;

public class File {

	public static byte[] readFile(String path) throws IOException {
		return Files.readAllBytes(getPath.apply(path));
	}
}
