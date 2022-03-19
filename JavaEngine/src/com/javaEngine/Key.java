package com.javaEngine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

public final class Key implements KeyListener {
	// Stack of key pressed
	private static final Stack<Integer> keyPressed = new Stack<Integer> ();
	
	// Key pressed
	private static final Stack<Integer> keyPressedOnce = new Stack<Integer> ();
	
	protected Key() {}
	
	@Override
	public void keyPressed(KeyEvent e) {
		final Integer keyCode = Integer.valueOf(e.getKeyCode());
		
		// Registers key code to the stack
		keyPressed.add(keyCode);
		
		// Registers key code to the stack
		if(keyPressed.contains(keyCode) && !keyPressedOnce.contains(keyCode))
			keyPressedOnce.add(keyCode);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Removes key code to the stack
		keyPressed.clear();
		
		// Removes key code pressed to the stack
		keyPressedOnce.clear();
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	// ***********************************************************
	// Static methods
	// ***********************************************************
	
	/**
	 * @param keyCode
	 * - {@code Key code} to check
	 * 
	 * @return
	 * {@code true} if the key code specified is {@code being pressed}; {@code false} otherwise
	 */
	public static boolean keyPressed(final int keyCode) {
		return keyPressed.contains(Integer.valueOf(keyCode));
	}
	
	/**
	 * @param keyChar
	 * - {@code Key char} to check
	 * 
	 * @return
	 * {@code true} if the key char specified is {@code being pressed}; {@code false} otherwise
	 */
	public static boolean charPressed(final char keyChar) {
		return keyPressed(keyChar);
	}
	
	/**
	 * @param keyCode
	 * - {@code Key code} to check
	 * 
	 * @return
	 * {@code true} if the key code specified has {@code been pressed}; {@code false} otherwise
	 */
	public static boolean pressed(final int keyCode) {
		return keyPressedOnce.contains(Integer.valueOf(keyCode));
	}
	
	/**
	 * @param keyChar
	 * - {@code Key char} to check
	 * 
	 * @return
	 * {@code true} if the key char specified has {@code been pressed}; {@code false} otherwise
	 */
	public static boolean pressedChar(final char keyChar) {
		return keyPressed(keyChar);
	}
}