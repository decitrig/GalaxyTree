package net.decitrig.galaxy.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import net.decitrig.galaxy.Particle;
import net.decitrig.galaxy.Universe;

import org.apache.commons.math.geometry.Vector3D;

import com.google.common.collect.Lists;

public class GalaxySim {
  private final JFrame main = new JFrame("GalaxySim");
  private final ListUniverse universe;
  private UniverseCanvas canvas;

  private static Vector3D vector(double i, double j, double k) {
    return new Vector3D(i, j, k);
  }

  private class ListUniverse implements Universe {
    private final List<Particle> particles = Lists.newArrayList();

    void addParticle(Particle p) {
      p.applyForce(vector(1, 2, 0));
      particles.add(p);
    }

    @Override
    public Iterable<Particle> particles() {
      return particles;
    }

    @Override
    public void update(double timeDelta) {
      for (Particle p : particles) {
        p.update(timeDelta);
      }
    }
  }

  private GalaxySim() {
    BorderLayout mainLayout = new BorderLayout();
    Container pane = main.getContentPane();
    pane.setLayout(mainLayout);

    universe = new ListUniverse();
    universe.addParticle(new Particle.Builder(vector(100, 100, 0), 1).build());
    universe.addParticle(new Particle.Builder(vector(100, 300, 0), 1).build());

    canvas = new UniverseCanvas(universe);
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
    TimerTask handOfGod = new TimerTask() {
      @Override
      public void run() {
        universe.update(.1);
        canvas.scheduleRepaint();
      }
    };
    Timer t = new Timer(true);
    t.scheduleAtFixedRate(handOfGod, 1000, 10);
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    GalaxySim sim = new GalaxySim();
    sim.run();
  }

}
