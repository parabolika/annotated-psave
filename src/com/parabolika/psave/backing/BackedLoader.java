package com.parabolika.psave.backing;

import java.util.Map;

public interface BackedLoader {
	Map<String, String> load(String key);
}
