package net.decitrig.universe.octree;


import java.util.Map;

import net.decitrig.galaxy.Particle;
import net.decitrig.universe.octree.Box.Octant;

import org.apache.commons.math.geometry.Vector3D;

import com.google.common.collect.Maps;

public class InternalNode implements Node {
  private final Map<Octant, Node> children = Maps.newEnumMap(Octant.class);

  public InternalNode() {
    for (Octant oct : Octant.values()) {
      children.put(oct, EmptyNode.get());
    }
  }

  @Override
  public Node insert(Box bounds, Particle particle) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Vector3D barycenter() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public double mass() {
    // TODO Auto-generated method stub
    return 0;
  }
}
