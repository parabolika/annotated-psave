package com.parabolika.psave;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import com.parabolika.psave.backing.BackedDumper;

public class Saver<T> {
	private BackedDumper backing = null;
	
	public Saver(BackedDumper backing) {
		this.backing = backing;
	}
	
	public void save(T instance) {
		List<Field> fields = getSaveableFields(instance);
		for(Field field : fields) {
			Saveable saveable = (Saveable) field.getAnnotation(Saveable.class);
			if(saveable.value() == true) {
				backing.dump(instance, field, fields.toArray(new Field[0]));
				return;
			}
		}
	}
	
	private List<Field> getSaveableFields(T instance) {
		List<Field> fields = new LinkedList<Field>();
		for(Field field : instance.getClass().getDeclaredFields()) {
			if(field.isAnnotationPresent(Saveable.class)) {
				field.setAccessible(true);
				fields.add(field);
			}
		}
		return fields;
	}
}
