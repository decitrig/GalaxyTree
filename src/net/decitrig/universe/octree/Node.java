package net.decitrig.universe.octree;


import net.decitrig.galaxy.Particle;

import org.apache.commons.math.geometry.Vector3D;

interface Node {
  Node insert(Box bounds, Particle particle);
  Vector3D barycenter();
  double mass();
}
