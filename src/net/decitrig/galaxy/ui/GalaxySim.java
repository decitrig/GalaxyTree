package net.decitrig.galaxy.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import net.decitrig.galaxy.Particle;
import net.decitrig.galaxy.Universe;

import org.apache.commons.math.geometry.Vector3D;

import com.google.common.collect.Lists;

public class GalaxySim {
  private final JFrame main = new JFrame("GalaxySim");

  private static Vector3D vector(double i, double j, double k) {
    return new Vector3D(i, j, k);
  }

  private class ListUniverse implements Universe {
    private final List<Particle> particles = Lists.newArrayList();

    void addParticle(Particle p) {
      particles.add(p);
    }

    @Override
    public Iterable<Particle> particles() {
      return particles;
    }

  }

  private GalaxySim() {
    BorderLayout mainLayout = new BorderLayout();
    Container pane = main.getContentPane();
    pane.setLayout(mainLayout);

    ListUniverse u = new ListUniverse();
    u.addParticle(new Particle.Builder(vector(100, 100, 0), 1).build());
    u.addParticle(new Particle.Builder(vector(100, 300, 0), 1).build());

    UniverseCanvas canvas = new UniverseCanvas(u);
    pane.add(canvas.getComponent(), BorderLayout.CENTER);
    main.setSize(800, 800);
  }

  void run() {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
      }
    });
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    GalaxySim sim = new GalaxySim();
    sim.run();
  }

}
