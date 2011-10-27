package net.decitrig.galaxy;

import static org.junit.Assert.*;

import org.junit.Test;

public class GalaxyTreeTest {

  @Test
  public void testAddIncrementsSize() {
    GalaxyTree tree = new GalaxyTree();
    assertEquals(0, tree.size());
    assertTrue(tree.isEmpty());
    tree.add(new Galaxy(1, 2.0, 2.0));
    assertEquals(1, tree.size());
    assertFalse(tree.isEmpty());

    tree.add(new Galaxy(2, 3.0, 3.0));
    assertEquals(2, tree.size());
    assertFalse(tree.isEmpty());
  }

}
