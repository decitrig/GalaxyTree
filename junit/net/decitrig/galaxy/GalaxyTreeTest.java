package net.decitrig.galaxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class GalaxyTreeTest {

  private static final Galaxy GALAXY2 = new Galaxy(1, 3.0, 3.0);
  private static final Galaxy GALAXY1 = new Galaxy(1, 2.0, 2.0);

  @Test
  public void testTreeCreation() {
  	Galaxy a = new Galaxy(1, 0, 0);
  	Galaxy b = new Galaxy(2, 1, 2);
  	Galaxy c = new Galaxy(1, 0, 3);
    GalaxyTree tree = new GalaxyTree(Lists.newArrayList(a, b, c));
    System.out.println(tree.debugString());
  }
}
