package net.decitrig.universe.octree;


import net.decitrig.galaxy.Particle;

import org.apache.commons.math.geometry.Vector3D;

class EmptyNode implements Node {
  private static final EmptyNode instance = new EmptyNode();

  static Node get() {
    return instance;
  }

  @Override
  public Node insert(Box bounds, Particle particle) {
    return new ParticleNode(particle);
  }

  @Override
  public Vector3D barycenter() {
    return Vector3D.ZERO;
  }

  @Override
  public double mass() {
    return 0.0;
  }
}
