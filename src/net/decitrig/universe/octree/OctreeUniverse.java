package net.decitrig.universe.octree;

import java.util.List;

import net.decitrig.galaxy.Particle;
import net.decitrig.galaxy.Universe;

/** A universe implemented using an octree and the Barnes-Hut algorithm. */
public class OctreeUniverse implements Universe {
  private Node root = EmptyNode.get();
  private List<Particle> particles;

  public OctreeUniverse(List<Particle> particles) {
    this.particles = particles;
  }

  @Override
  public Iterable<Particle> particles() {
    return particles;
  }

  @Override
  public void update(double timeDelta) {
    throw new UnsupportedOperationException();
  }
}
