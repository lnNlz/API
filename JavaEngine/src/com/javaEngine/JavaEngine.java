package com.javaEngine;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * Main class a.k.a. foundation of the engine,
 * this holds several useful methods
 * 
 * @since 1.0
 * @version 1.0
 */
public final class JavaEngine extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private static JavaEngine engine;
	
	private JFrame frame;
	private Thread thread;
	
	// Run variables
	private static double TPS = 60.0; // Ticks per second
	private static int FPS = 0; // Frames per second
	private static int TICKS = 0; // Current Ticks
	
	// Objects
	private final ArrayList<Obj> objects;
	
	// Others
	private Graphics2D graphics2D;
	
	/**
	 * <b>YOU CANNOT INITIALIZE {@code this class} </b>
	 * , to get the instance of {@code this class},
	 * call {@link #get()}
	 */
	private JavaEngine() {
		objects = new ArrayList<Obj> ();
	}
	
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
		
		// Show window
		if(frame != null) {
			frame.add(this);
			frame.setVisible(true);
		}
		
		// Thread starting
		thread = new Thread(this);
		thread.start();
		
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
		
		// Close window
		if(frame != null) {
			frame.dispose();
			frame = null;
		}
		
		// Thread stopping
		thread.join();
		thread = null;
		
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
				// Update objects
				for(final Obj obj : objects) obj.onUpdate(elapsedTime);
				
				TICKS++; // increment
				engine_deltaTime--; // decrement
			}
			
			
			// Clean this part
			final BufferStrategy bufferStrategy = getBufferStrategy();
			if(bufferStrategy == null) {
				// TODO: allow custom buffer_int
				createBufferStrategy(3);
				
				continue;
			}
			
			graphics2D = (Graphics2D)bufferStrategy.getDrawGraphics();
			
			// TODO: Allow optional clearing
			graphics2D.clearRect(0, 0, getWidth(), getHeight());
			
			// Render objects
			for(final Obj obj : objects) obj.onRender(graphics2D);
			
			graphics2D.dispose();
			bufferStrategy.show();
			
			// Frame
			FPS++;
			
			// Timer
			if(System.currentTimeMillis() - engineTimer > 1_000)  {
				// Reset timer
				engineTimer += 1_000;
				// Increment elapsed time
				elapsedTime++;
				
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
	
	/**
	 * Adds the {@code Object} to the list where it will be handled
	 * 
	 * @param obj
	 * - Object to add : {@code Obj}
	 * 
	 * @return
	 * {@code true} if the object has been added {@code false} otherwise
	 */
	public boolean add(final Obj obj) {
		// Null object
		if(obj == null) return false;
		
		if(objects.add(obj)) {
			obj.onStart();
			return true;
		}
		
		// Failed to add the object
		return false;
	}
	
	/**
	 * Removes the {@code Object} from the list
	 * 
	 * @param obj
	 * - Object to remove : {@code Obj}
	 * 
	 * @return
	 * {@code true} if the object has been removed; {@code false} otherwise
	 */
	public boolean remove(final Obj obj) {
		// Null object
		if(obj == null) return false;
		
		if(objects.remove(obj)) {
			obj.onStart();
			return true;
		}
		
		// Failed to remove the object
		return false;
	}
	
	/**
	 * Removes the {@code Object} from the index from the list
	 * 
	 * @param index
	 * - Index where the object is located : {@code Obj}
	 * 
	 * @return
	 * {@code true} if the object has been removed; {@code false} otherwise
	 * 
	 * @see #remove(Obj)
	 */
	public boolean remove(final int index) {
		// Invalid index (out of bounds)
		if(index < 0 || index >= objects.size()) return false;
		
		return remove(objects.get(index));
	}
	
	/**
	 * @return
	 * {@code size} of the list
	 */
	public int objSize() {
		return objects.size();
	}
	
	/**
	 * Returns the {@code object} from the index specified
	 * 
	 * @param index
	 * - Index of the object
	 * 
	 * @return
	 * {@code Object} if index is valid; {@code null} otherwise
	 */
	public Obj get(final int index) {
		// Invalid index
		if(index < 0 || index >= objSize()) return null;
		
		return objects.get(index);
	}
	
	/**
	 * Returns whether the list contains the object specified
	 * 
	 * @param obj
	 * - Object to find
	 * 
	 * @return
	 * {@code true} if the object is in the list; {@code false} otherwise
	 */
	public boolean contains(final Obj obj) {
		return objects.contains(obj);
	}
	
	// Renders
	
	/**
	 * Clears the screen to draw more {@code stuffs}
	 */
	public void clearScreen() {
		if(graphics2D != null)
			graphics2D.clearRect(0, 0, frame.getWidth(), frame.getHeight());
	}
	
	// ************************************************************
	// Getters and Setters
	// ************************************************************
	
	/**
	 * @return
	 * {@code Engine} frames per second
	 */
	public int getFPS() {
		return FPS;
	}
	
	/**
	 * @return
	 * {@code Engine} ticks per second
	 */
	public double getTPS() {
		return TPS;
	}
	
	/**
	 * Sets ticks per second value to the value specified
	 * 
	 * @param newTPSValue
	 * - New value for TPS
	 */
	public void setTPS(final double newTPSValue) {
		TPS = newTPSValue;
	}
	
	/**
	 * @return
	 * {@code Engine} current ticks
	 */
	public int getTicks() {
		return TICKS;
	}
	
	@Override
	public int getWidth() {
		return frame.getWidth();
	}
	
	@Override
	public int getHeight() {
		return frame.getHeight();
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