package com.javaEngine;

import javax.swing.JFrame;

/**
 * Main class a.k.a. foundation of the engine,
 * this holds several useful methods
 * 
 * @since 1.0
 * @version 1.0
 */
public final class JavaEngine implements Runnable {
	private static JavaEngine engine;
	
	private JFrame frame;
	private Thread thread;
	
	// Run variables
	private double TPS = 60.0; // Ticks per second
	private int FPS = 0; // Frames per second
	private int TICKS = 0; // Current Ticks
	
	
	/**
	 * <b>YOU CANNOT INITIALIZE {@code this class} </b>
	 * , to get the instance of {@code this class},
	 * call {@link #get()}
	 */
	private JavaEngine() {}
	
	// ************************************************************
	// Local methods
	// ************************************************************
	
	/**
	 * Starts the thread and shows the window,
	 * this also initializes other things
	 * @return
	 * {@code true} if the initialization is successful; {@code false} otherwise
	 */
	public boolean start() {
		// Initialized thread
		if(thread != null) return false;
		
		// Thread starting
		thread = new Thread(this);
		thread.start();
		
		// Show window
		if(frame != null)
			frame.setVisible(true);
		
		return true;
	}
	
	/**
	 * 
	 * Stops the program by killing the thread and disposing the window,
	 * this also handles other things such as automatic closing
	 * 
	 * @param closeProgramIfSuccessful
	 * - Close the program if process is successful
	 * 
	 * @return
	 * {@code true} if the process is successful; {@code false} otherwise
	 * 
	 * @throws InterruptedException
	 */
	public boolean stop(final boolean closeProgramIfSuccessful) throws InterruptedException {
		// Uninitialized thread
		if(thread == null) return false;
		
		// Thread stopping
		thread.join();
		thread = null;
		
		// Close window
		if(frame != null) {
			frame.dispose();
			frame = null;
		}
		
		// Close the program
		if(closeProgramIfSuccessful)
			System.exit(1);
		
		return true;
	}
	
	@Override
	public void run() {
		// Manually called
		if(thread == null) return;
		
		long last_engineTime = System.nanoTime();
		double engine_deltaTime = 0.0;
		long engineTimer = System.currentTimeMillis();
		long elapsedTime = 0;
		while(isRunning()) {
			final double nano = 1E+9 / TPS;
			final long current_engineTime = System.nanoTime();
			
			// Calculating delta time
			engine_deltaTime += (current_engineTime - last_engineTime) / nano;
			last_engineTime = current_engineTime; // reset old time
			
			// Update
			while(engine_deltaTime >= 0) {
				TICKS++; // increment
				engine_deltaTime--; // decrement
			}
			
			// Frame
			FPS++;
			
			// Timer
			if(System.currentTimeMillis() - engineTimer > 1_000)  {
				// Reset timer
				engineTimer += 1_000;
				
				// Reset
				FPS = 0;
				TICKS = 0;
			}
		}
	}
	
	/**
	 * Checks whether thread has been initialized and running
	 * @return
	 * {@code true} if thread has been initialized; {@code false} otherwise
	 */
	public boolean isRunning() {
		return thread != null || thread.isAlive();
	}
	
	/**
	 * @return
	 * {@code true} if thread is alive; {@code false} otherwise
	 * 
	 * @deprecated
	 * use {@link #isRunning()} instead
	 */
	@Deprecated
	public boolean isAlive() {
		return thread.isAlive();
	}
	
	/**
	 * Sets the window({@code JFrame}) to an initiated {@code JFrame}
	 * 
	 * @param frame
	 * - {@code JFrame} value
	 * 
	 * @return
	 * {@code true}
	 */
	public boolean display(final JFrame frame) {
		frame.setVisible(false);
		
		this.frame = frame;
		return true;
	}
	
	/**
	 * Sets the size and title of window
	 * 
	 * @param width
	 * - Window Width
	 * 
	 * @param height
	 * - Window height
	 * 
	 * @param title
	 * - Title of the window
	 * 
	 * @return
	 * {@link #display(JFrame)}
	 */
	public boolean display(final int width, final int height, final String title) {
		final JFrame frame = new JFrame(title);
		frame.setSize(width, height);
		
		// Exit window on close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Place the window on the middle
		frame.setLocationRelativeTo(null);
		
		return display(frame);
	}
	
	// ************************************************************
	// Static methods
	// ************************************************************
	
	/**
	 * This returns the {@code engine instance},
	 * 
	 * <br>
	 * Remember to call {@link #init()} before using this method or else, it will return null
	 * @return
	 */
	public static JavaEngine get() {
		return engine;
	}
	
	/**
	 * Initializes some stuffs to run the engine successfully
	 * 
	 * @return
	 * {@code true} if the initialization is successful; {@code false} otherwise
	 */
	public static boolean init() {
		// Engine has already been initialized
		if(engine != null) return false;
		
		engine = new JavaEngine();
		return true;
	}
 }