package net.decitrig.galaxy;

import org.apache.commons.math.geometry.Vector3D;

public class Particle {
	private Vector3D position;
	private Vector3D velocity;
	private Vector3D netForce = Vector3D.ZERO;
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

	public static Vector3D geometricCenter(Iterable<Particle> particles) {
		double n = 0;
		Vector3D sumVector = Vector3D.ZERO;
		for (Particle p : particles) {
			sumVector = sumVector.add(p.position);
			n += 1.0;
		}
		return new Vector3D(1/n, sumVector);
	}

	public Particle(double mass, Vector3D position) {
		this(mass, position, Vector3D.ZERO);
	}

	public Particle(double mass, Vector3D position, Vector3D velocity) {
		this.position = position;
		this.velocity = velocity;
		this.mass = mass;
	}

	public Vector3D position() {
  	return position;
  }

	public Vector3D velocity() {
  	return velocity;
  }

	public Vector3D netForce() {
		return netForce;
	}

	public double mass() {
		return mass;
	}

	public void applyForce(Vector3D force) {
		netForce = netForce.add(force);
	}

	public void update(double timeDelta) {
		Vector3D acceleration = new Vector3D(1/mass, netForce);
		velocity = velocity.add(timeDelta, acceleration);
		position = position.add(timeDelta, velocity);
	}
}
