package com.javaEngine.geom;

import com.javaEngine.math.Vec3F;

public final class Cube extends Mesh3D {
	public Cube(final Vec3F rotation, final float size) {
		super(new Triangle3D[] {
				// Triangles
				
				// Front
				new Triangle3D(
							new Vec3F(0.0F, 0.0F, 0.0F),
							new Vec3F(0.0F, size, 0.0F),
							new Vec3F(size, size, 0.0F)
						),
				
				new Triangle3D(
						new Vec3F(0.0F, 0.0F, 0.0F),
						new Vec3F(size, size, 0.0F),
						new Vec3F(size, 0.0F, 0.0F)
					),
			
				// Right
				new Triangle3D(
						new Vec3F(size, 0.0F, 0.0F),
						new Vec3F(size, size, 0.0F),
						new Vec3F(size, size, size)
					),
				
				new Triangle3D(
						new Vec3F(size, 0.0F, 0.0F),
						new Vec3F(size, size, size),
						new Vec3F(size, 0.0F, size)
					),
				
				// Back
				new Triangle3D(
						new Vec3F(size, 0.0F, size),
						new Vec3F(size, size, size),
						new Vec3F(0.0F, size, size)
					),
			
				new Triangle3D(
						new Vec3F(size, 0.0F, size),
						new Vec3F(0.0F, size, size),
						new Vec3F(0.0F, 0.0F, size)
					),
				
				// Left
				new Triangle3D(
						new Vec3F(0.0F, 0.0F, size),
						new Vec3F(0.0F, size, size),
						new Vec3F(0.0F, size, 0.0F)
					),
			
				new Triangle3D(
						new Vec3F(0.0F, 0.0F, size),
						new Vec3F(0.0F, size, 0.0F),
						new Vec3F(0.0F, 0.0F, 0.0F)
					),
				
				// Top
				new Triangle3D(
						new Vec3F(0.0F, size, 0.0F),
						new Vec3F(0.0F, size, size),
						new Vec3F(size, size, size)
					),
			
				new Triangle3D(
						new Vec3F(0.0F, size, 0.0F),
						new Vec3F(size, size, size),
						new Vec3F(size, size, 0.0F)
					),
				
				// Bottom
				new Triangle3D(
						new Vec3F(size, 0.0F, size),
						new Vec3F(0.0F, 0.0F, size),
						new Vec3F(0.0F, 0.0F, 0.0F)
					),
			
				new Triangle3D(
						new Vec3F(size, 0.0F, size),
						new Vec3F(0.0F, 0.0f, 0.0F),
						new Vec3F(size, 0.0F, 0.0F)
					)
		}, rotation);
		
		// Invalid size
		if (size == 0.0F)
			throw new IllegalArgumentException("Invalid size!");
		
		// Equal size
		scale.set(new Vec3F(size == 0.0F ? 0.1F : size));
	}
	
	public Cube(final float size) {
		this(new Vec3F(0.0F), size);
	}
}