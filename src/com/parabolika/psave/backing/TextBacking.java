package com.parabolika.psave.backing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class TextBacking implements BackedDumper, BackedLoader {
	private String baseDirectory = null;
	
	public TextBacking(String baseDirectory) {
		this.baseDirectory = baseDirectory;
	}
	
	@Override
	public boolean dump(Object instance, Field key, Field[] allFields) {
		try {
			File file = new File(baseDirectory + File.separator + key.get(instance).toString() + ".txt");
			FileWriter writer = new FileWriter(file);
			for(Field field : allFields) {
				writer.write(field.getName() + ":" + field.get(instance) + "\n");
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Map<String, String> load(String key) {
		HashMap<String, String> map = new HashMap<String, String>();
		File file = new File(baseDirectory + File.separator + key + ".txt");
		if(file.exists()) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				while((line = reader.readLine()) != null) {
					map.put(line.split(":", 2)[0], line.split(":", 2)[1]);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
