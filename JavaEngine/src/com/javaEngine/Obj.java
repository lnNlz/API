package com.javaEngine;

import java.awt.Graphics2D;

/**
 * {@code Object} interface acts as a blueprint for Objects,
 * {@code Classes} that implements this could be added to {@code Java Engine}
 * where it will be handled till removed
 * <br>
 * <br>
 * This interface contains several methods like {@code onStart()}, {@code onUpdate()} and
 * {@code onRender()} where each one has its own purpose
 * 
 * @since 1.0
 * @version 1.0
 */
public interface Obj {
	/**
	 * Called after adding this {@code object} into the list ({@code JavaEngine.get().add()})
	 */
	public default void onStart() {}
	
	/**
	 * Called once per tick; to change ticking speed, call {@code JavaEngine.setTPS()}
	 * 
	 * @param elapsedTime
	 * - Elapsed time : {@code seconds}
	 */
	public void onUpdate(final long elapsedTime);
	
	/**
	 * Called after {@code onUpdate()} method, rendering stuffs will be handled in this method
	 */
	public default void onRender(final Graphics2D g) {}
	
	// Other override-able methods
	/**
	 * Called after removing this {@code object} from the list ({@code JavaEngine.get().re()})
	 */
	public default void onDestroy() {}
	public default void onPause() {}
	public default void onResume() {}
}