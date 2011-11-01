package net.decitrig.universe.octree;

import static org.junit.Assert.assertEquals;
import net.decitrig.TestUtil;
import net.decitrig.universe.octree.Box.Octant;

import org.junit.Test;

public class BoxTest {
  @Test
  public void testTopNortheastOctantBox() {
    Box b = new Box(TestUtil.vector(0, 0, 0), 4.0);
    assertEquals(4.0, b.getSize(), 1e-6);
    assertEquals(TestUtil.vector(0, 0, 0), b.getOrigin());
    Box top_ne = b.getOctantBox(Octant.TOP_NE);
    Box expected = new Box(TestUtil.vector(2.0, 2.0, 2.0), 2.0);
    assertEquals(expected, top_ne);
   }

  @Test
  public void testOddSizeOctantBox() {
    Box b = new Box(TestUtil.vector(0, 0, 0), 3.33);
    assertEquals(3.33, b.getSize(), 1e-3);
    Box top_ne = b.getOctantBox(Octant.TOP_NE);
    double size = 3.33 / 2;
    Box expected = new Box(TestUtil.vector(size, size, size), size);
    assertEquals(expected, top_ne);
  }

  @Test
  public void testFindOctant() {
    Box b = new Box(TestUtil.vector(0, 0, 0), 4.0);
    assertEquals(Octant.BOTTOM_SW, b.octantOf(TestUtil.vector(1, 1, 1)));
    assertEquals(Octant.BOTTOM_NW, b.octantOf(TestUtil.vector(1, 3, 1)));
    assertEquals(Octant.BOTTOM_SE, b.octantOf(TestUtil.vector(3, 1, 1)));
    assertEquals(Octant.BOTTOM_NE, b.octantOf(TestUtil.vector(3, 3, 1)));

    assertEquals(Octant.TOP_SW, b.octantOf(TestUtil.vector(1, 1, 3)));
    assertEquals(Octant.TOP_NW, b.octantOf(TestUtil.vector(1, 3, 3)));
    assertEquals(Octant.TOP_SE, b.octantOf(TestUtil.vector(3, 1, 3)));
    assertEquals(Octant.TOP_NE, b.octantOf(TestUtil.vector(3, 3, 3)));
  }

  @Test
  public void testOctantsClosedBelowOpenAbove() {
    Box b = new Box(TestUtil.vector(0, 0, 0), 4.0);
    assertEquals(Octant.TOP_NE, b.octantOf(TestUtil.vector(2, 2, 2)));
    assertEquals(Octant.TOP_NE, b.octantOf(TestUtil.vector(4, 4, 4)));
    assertEquals(Octant.BOTTOM_SW, b.octantOf(TestUtil.vector(0, 0, 0)));
  }
}
