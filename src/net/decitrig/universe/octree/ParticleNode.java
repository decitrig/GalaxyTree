package net.decitrig.universe.octree;


import net.decitrig.galaxy.Particle;

import org.apache.commons.math.geometry.Vector3D;

public class ParticleNode implements Node {
  private final Particle particle;

  public ParticleNode(Particle particle) {
    this.particle = particle;
  }

  @Override
  public Node insert(Box bounds, Particle particle) {
    Node internal = new InternalNode();
    internal.insert(bounds, this.particle);
    internal.insert(bounds, particle);
    return internal;
  }

  @Override
  public Vector3D barycenter() {
    return particle.position();
  }

  @Override
  public double mass() {
    return particle.mass();
  }
}
