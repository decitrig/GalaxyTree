package net.decitrig.galaxy;

import org.apache.commons.math.geometry.Vector3D;

public class Particle {
	private Vector3D position;
	private Vector3D acceleration;
	private Vector3D velocity;
	private final double mass;

	public static Vector3D barycenter(Iterable<Particle> particles) {
		Vector3D barycenter = Vector3D.ZERO;
		double totalMass = 0.0;
		for (Particle p : particles) {
			barycenter = barycenter.add(p.mass(), p.position());
			totalMass += p.mass();
		}
		return new Vector3D(1/totalMass, barycenter);
	}

	public static class Builder {
		private final Vector3D position;
		private Vector3D acceleration;
		private Vector3D velocity;
		private final double mass;

		public Builder(Vector3D position, double mass) {
			this.position = position;
			this.mass = mass;
		}

		public Builder withVelocity(Vector3D velocity) {
			this.velocity = velocity;
			return this;
		}

		public Builder withAcceleration(Vector3D acceleration) {
			this.acceleration = acceleration;
			return this;
		}

		public Particle build() {
			return new Particle(this);
		}
	}

	private Particle(Builder builder) {
		this.position = builder.position;
		this.velocity = builder.velocity;
		this.acceleration = builder.acceleration;
		this.mass = builder.mass;
	}

	public Vector3D position() {
  	return position;
  }

	public Vector3D acceleration() {
  	return acceleration;
  }

	public Vector3D velocity() {
  	return velocity;
  }

	public double mass() {
		return mass;
	}

	public void applyForce(Vector3D force) {
		acceleration = acceleration.add(1/mass, force);
	}

	public void update(double timeDelta) {
		velocity = velocity.add(timeDelta, acceleration);
		position = position.add(timeDelta, velocity);
	}
}
