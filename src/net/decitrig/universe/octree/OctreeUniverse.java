package net.decitrig.universe.octree;

import net.decitrig.galaxy.Particle;
import net.decitrig.galaxy.Universe;

public class OctreeUniverse implements Universe {
  private Node root = EmptyNode.get();

  @Override
  public Iterable<Particle> particles() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void update(double timeDelta) {
    throw new UnsupportedOperationException();
  }
}
