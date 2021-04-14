package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.HugLifeUtils;
import huglife.Occupant;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class Clorus extends Creature {

    /** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    public Clorus() {
        this(1);
    }

    public Color color() {
        return color(r, g, b);
    }

    public void move() {
        energy -= 0.03;
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public void stay() {
        energy -= 0.01;
    }

    public Clorus replicate() {
        energy *= 0.5;
        return new Clorus(energy);
    }

    public huglife.Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plip = getNeighborsOfType(neighbors, "plip");
        if (empties.size() == 0) {
            return new huglife.Action(huglife.Action.ActionType.STAY);
        } else if (!plip.isEmpty()) {
            Direction moveDir = HugLifeUtils.randomEntry(plip);
            return new huglife.Action(Action.ActionType.ATTACK, moveDir);
        } else if (energy >= 1) {
            Direction moveDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, moveDir);
        } else {
            Direction moveDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.MOVE, moveDir);
        }
    }
}
