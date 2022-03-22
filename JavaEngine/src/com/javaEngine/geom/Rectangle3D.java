package com.javaEngine.geom;

import com.javaEngine.math.Vec3F;

public final class Rectangle3D extends Mesh3D {
	public Rectangle3D(final Vec3F scale) {
		super(new Triangle3D[] {
				// Triangles
				
				// Front
				new Triangle3D(
							new Vec3F(0.0F, 0.0F, 0.0F),
							new Vec3F(0.0F, scale.getY(), 0.0F),
							new Vec3F(scale.getX(), scale.getY(), 0.0F)
						),
				
				new Triangle3D(
						new Vec3F(0.0F, 0.0F, 0.0F),
						new Vec3F(scale.getX(), scale.getY(), 0.0F),
						new Vec3F(scale.getX(), 0.0F, 0.0F)
					),
			
				// Right
				new Triangle3D(
						new Vec3F(scale.getX(), 0.0F, 0.0F),
						new Vec3F(scale.getX(), scale.getY(), 0.0F),
						new Vec3F(scale.getX(), scale.getY(), scale.getZ())
					),
				
				new Triangle3D(
						new Vec3F(scale.getX(), 0.0F, 0.0F),
						new Vec3F(scale.getX(), scale.getY(), scale.getZ()),
						new Vec3F(scale.getX(), 0.0F, scale.getZ())
					),
				
				// Back
				new Triangle3D(
						new Vec3F(scale.getX(), 0.0F, scale.getZ()),
						new Vec3F(scale.getX(), scale.getY(), scale.getZ()),
						new Vec3F(0.0F, scale.getY(), scale.getZ())
					),
			
				new Triangle3D(
						new Vec3F(scale.getX(), 0.0F, scale.getZ()),
						new Vec3F(0.0F, scale.getY(), scale.getZ()),
						new Vec3F(0.0F, 0.0F, scale.getZ())
					),
				
				// Left
				new Triangle3D(
						new Vec3F(0.0F, 0.0F, scale.getZ()),
						new Vec3F(0.0F, scale.getY(), scale.getZ()),
						new Vec3F(0.0F, scale.getY(), 0.0F)
					),
			
				new Triangle3D(
						new Vec3F(0.0F, 0.0F, scale.getZ()),
						new Vec3F(0.0F, scale.getY(), 0.0F),
						new Vec3F(0.0F, 0.0F, 0.0F)
					),
				
				// Top
				new Triangle3D(
						new Vec3F(0.0F, scale.getY(), 0.0F),
						new Vec3F(0.0F, scale.getY(), scale.getZ()),
						new Vec3F(scale.getX(), scale.getY(), scale.getZ())
					),
			
				new Triangle3D(
						new Vec3F(0.0F, scale.getY(), 0.0F),
						new Vec3F(scale.getX(), scale.getY(), scale.getZ()),
						new Vec3F(scale.getX(), scale.getY(), 0.0F)
					),
				
				// Bottom
				new Triangle3D(
						new Vec3F(scale.getX(), 0.0F, scale.getZ()),
						new Vec3F(0.0F, 0.0F, scale.getZ()),
						new Vec3F(0.0F, 0.0F, 0.0F)
					),
			
				new Triangle3D(
						new Vec3F(scale.getX(), 0.0F, scale.getZ()),
						new Vec3F(0.0F, 0.0f, 0.0F),
						new Vec3F(scale.getX(), 0.0F, 0.0F)
					)
		});
	}
}