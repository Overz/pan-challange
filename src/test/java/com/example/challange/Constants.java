package com.example.challange;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

public class Constants {

	public static final Path RESOURCES_PATH = Paths.get("src", "test", "resources");
	public static Function<String, Path> getPath = path ->
		Paths.get(RESOURCES_PATH.toAbsolutePath().toString(), path);
}
