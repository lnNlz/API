package com;

import com.javaEngine.JavaEngine;

public final class Test {
	public static void main(String[] args) {
		// Initialize Java engine
		if(!JavaEngine.init()) System.out.println("Unable to initialize Java Engine!");
		
		// Java Engine object
		JavaEngine engine = JavaEngine.get();
		
		// Create window
		// 1000 pixel width, 700 pixel height
		engine.display(1000, 700, "Testing program");
		
		// Start the engine
		engine.start();
	}
}