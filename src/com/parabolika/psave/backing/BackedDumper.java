package com.parabolika.psave.backing;

import java.lang.reflect.Field;

public interface BackedDumper {
	boolean dump(Object instance, Field key, Field[] allFields);
}
