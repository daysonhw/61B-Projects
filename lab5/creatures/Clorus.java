package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * Called when this creature moves.
     */

    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    @Override
    public void move() {
        energy -= 0.03;
    }

    /**
     * Called when this creature attacks C.
     *
     * @param c
     */
    @Override
    public void attack(Creature c) {
        energy += c.energy();

    }

    /**
     * Called when this creature chooses replicate.
     * Must return a creature of the same type.
     */
    @Override
    public Creature replicate() {
        energy = energy * 0.5;
        return new Clorus(energy);
    }

    /**
     * Called when this creature chooses stay.
     */
    @Override
    public void stay() {
        energy -= 0.01;
    }

    /**
     * Returns an action. The creature is provided information about its
     * immediate NEIGHBORS with which to make a decision.
     *
     * @param neighbors
     */
    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plip = new ArrayDeque<>();
        for (Direction key : neighbors.keySet()) {
            if (neighbors.get(key).name().equals("empty")) {
                emptyNeighbors.add(key);
            }
            if (neighbors.get(key).name().equals("plip")) {
                plip.add(key);
            }
        }
        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }
        // rule 2
        if (! plip.isEmpty()) {
            while(true){
            for (Direction d : plip) {
                if (Math.random() < 1.0 / plip.size()) {
                    return new Action(Action.ActionType.ATTACK, d);
                }
            }
            }
        }
        //rule 3
        if (energy >= 1) {
            while (true) {
                for (Direction d : emptyNeighbors) {
                    if (Math.random() < 1.0 / emptyNeighbors.size()) {
                        return new Action(Action.ActionType.REPLICATE, d);
                    }
                }
            }
        }
        while (true) {
            for (Direction d : emptyNeighbors) {
                if (Math.random() < 1.0 / emptyNeighbors.size()) {
                    return new Action(Action.ActionType.MOVE, d);
                }
            }
        }
    }
    /**
     * Required method that returns a color.
     */
    @Override
        public Color color() {
        return color(r, g, b);
    }
}
