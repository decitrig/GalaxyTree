package net.decitrig.galaxy;

import static org.junit.Assert.*;

import org.apache.commons.math.geometry.Vector3D;
import org.junit.Test;

import com.google.common.collect.Lists;

public class ParticleTest {

	@Test
	public void testSimpleBarycenter() {
		Particle p1 = new Particle(1.0, vector(1, 1, 1));
		Particle p2 = new Particle(1.0, vector(0, 0, 0));
		Vector3D center = Particle.geometricCenter(particles(p1, p2));
		Vector3D barycenter = Particle.barycenter(particles(p1, p2));
		Vector3D expected = vector(0.5, 0.5, 0.5);
		assertEquals(expected, center);
		assertEquals(expected, barycenter);
	}

	private static Vector3D vector(double i, double j, double k) {
		return new Vector3D(i, j, k);
	}

	private static Iterable<Particle> particles(Particle...particles) {
		return Lists.newArrayList(particles);
	}
}
