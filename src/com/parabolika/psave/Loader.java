package com.parabolika.psave;

import java.lang.reflect.Field;
import java.util.Map;

import com.parabolika.psave.backing.BackedLoader;

public class Loader<T> {
	private BackedLoader backing = null;

	public Loader(BackedLoader backing) {
		this.backing = backing;
	}
	
	public void load(T instance, String key) {
		Map<String, String> fieldMap = backing.load(key);
		for(Field field : instance.getClass().getDeclaredFields()) {
			String value = fieldMap.get(field.getName());
			if(value != null) {
				try {
					field.setAccessible(true);
					setField(instance, field, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void setField(T instance, Field field, String value) {
		try {
			if(field.getType() == String.class) {
				field.set(instance, value);
			} else if(field.getType() == boolean.class) {
				field.setBoolean(instance, Boolean.parseBoolean(value));
			} else if(field.getType() == byte.class) {
				field.setByte(instance, Byte.parseByte(value));
			} else if(field.getType() == char.class) {
				field.setChar(instance, value.charAt(0)); // ??
			} else if(field.getType() == double.class) {
				field.setDouble(instance, Double.parseDouble(value));
			} else if(field.getType() == float.class) {
				field.setFloat(instance, Float.parseFloat(value));
			} else if(field.getType() == int.class) {
				field.setInt(instance, Integer.parseInt(value));
			} else if(field.getType() == long.class) {
				field.setLong(instance, Long.parseLong(value));
			} else if(field.getType() == short.class) {
				field.setShort(instance, Short.parseShort(value));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
