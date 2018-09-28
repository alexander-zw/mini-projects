package hw4.puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.princeton.cs.algs4.StdOut;

/**
 * RubiksCubes are technically mutable, but are in practice not mutated. They are only mutated
 * right after creation, before being returned by the RubiksCube's methods.
 * 
 * @author AlexanderWu
 */
public class RubiksCube implements WorldState {

    /**
     * Always have top yellow, front blue, right red,
     * bottom white, back green, left orange.
     */

    /** **************************** Class/instance variables **************************** */

    /** all possible layers you can rotate */
    public static final Layer[] LAYERS = Layer.values();
    private static Random rand = new Random();

    /** front-left, front-right, back-right, back-left respectively (counterclockwise) */
    private final List<Corner> topC;

    /** front-left, front-right, back-right, back-left respectively (counterclockwise) */
    private final List<Corner> bottomC;

    /** front, right, back, left respectively (counterclockwise) */
    private final List<Edge> topE;

    /** front-left, front-right, back-right, back-left respectively (counterclockwise) */
    private final List<Edge> middleE;

    /** front, right, back, left respectively (counterclockwise) */
    private final List<Edge> bottomE;

    /** ************************* Core public constructors/methods ************************* */

    public RubiksCube() {
        topC = new ArrayList<>(4);
        bottomC = new ArrayList<>(4);

        topE = new ArrayList<>(4);
        middleE = new ArrayList<>(4);
        bottomE = new ArrayList<>(4);
    }

    public RubiksCube(RubiksCube other) {
        topC = new ArrayList<>(other.topC);
        bottomC = new ArrayList<>(other.bottomC);

        topE = new ArrayList<>(other.topE);
        middleE = new ArrayList<>(other.middleE);
        bottomE = new ArrayList<>(other.bottomE);
    }

    @Override
    public Iterable<WorldState> neighbors() {
        List<WorldState> neighbors = new ArrayList<>(12);

        neighbors.add(transform(Layer.UP, true));
        neighbors.add(transform(Layer.UP, false));
        neighbors.add(transform(Layer.DOWN, true));
        neighbors.add(transform(Layer.DOWN, false));
        neighbors.add(transform(Layer.FRONT, true));
        neighbors.add(transform(Layer.FRONT, false));
        neighbors.add(transform(Layer.BACK, true));
        neighbors.add(transform(Layer.BACK, false));
        neighbors.add(transform(Layer.RIGHT, true));
        neighbors.add(transform(Layer.RIGHT, false));
        neighbors.add(transform(Layer.LEFT, true));
        neighbors.add(transform(Layer.LEFT, false));

        return neighbors;
    }

    /**
     * Possible values for edges: 0 to 12, in practice rarely more than 8.
     * Possible values for corners: 0 to 6, in practice rarely more than 5.
     */
    @Override
    public int estimatedDistanceToGoal() {
        // this does not improve the algorithm speed but guarantees correctness
        return Math.max(edgeDist(), cornerDist());
        // slightly faster but may be incorrect
        // return edgeDist();
    }

    /** *************************** Private helpers *************************** */

    private int edgeDist() {

        int manhattan = 0;

        manhattan += dist(topE.get(0), Layer.UP, Layer.FRONT);
        manhattan += dist(topE.get(1), Layer.UP, Layer.RIGHT);
        manhattan += dist(topE.get(2), Layer.UP, Layer.BACK);
        manhattan += dist(topE.get(3), Layer.UP, Layer.LEFT);

        manhattan += dist(middleE.get(0), Layer.FRONT, Layer.LEFT);
        manhattan += dist(middleE.get(1), Layer.FRONT, Layer.RIGHT);
        manhattan += dist(middleE.get(2), Layer.BACK, Layer.RIGHT);
        manhattan += dist(middleE.get(3), Layer.BACK, Layer.LEFT);

        manhattan += dist(bottomE.get(0), Layer.DOWN, Layer.FRONT);
        manhattan += dist(bottomE.get(1), Layer.DOWN, Layer.RIGHT);
        manhattan += dist(bottomE.get(2), Layer.DOWN, Layer.BACK);
        manhattan += dist(bottomE.get(3), Layer.DOWN, Layer.LEFT);

        return (int) Math.ceil(manhattan / 4.0);
    }

    private int cornerDist() {

        int manhattan = 0;

        manhattan += dist(topC.get(0), Layer.UP, Layer.FRONT, Layer.LEFT);
        manhattan += dist(topC.get(1), Layer.UP, Layer.FRONT, Layer.RIGHT);
        manhattan += dist(topC.get(2), Layer.UP, Layer.BACK, Layer.RIGHT);
        manhattan += dist(topC.get(3), Layer.UP, Layer.BACK, Layer.LEFT);

        manhattan += dist(bottomC.get(0), Layer.DOWN, Layer.FRONT, Layer.LEFT);
        manhattan += dist(bottomC.get(1), Layer.DOWN, Layer.FRONT, Layer.RIGHT);
        manhattan += dist(bottomC.get(2), Layer.DOWN, Layer.BACK, Layer.RIGHT);
        manhattan += dist(bottomC.get(3), Layer.DOWN, Layer.BACK, Layer.LEFT);

        return (int) Math.ceil(manhattan / 4.0);
    }

    /** possible values 0 to 4 */
    private static int dist(Edge e, Layer l1, Layer l2) {

        Color correctF = correct(l1);
        Color correctS = correct(l2);

        if (e.first == correctF) {
            if (e.second == correctS) {
                return 0; // correct position
            }
            if (colorsOpposite(e.second, correctS)) {
                return 2; // rotate 180 to get to correct position
            }
            return 1; // rotate 90 to get to correct position
        }
        if (e.second == correctS) {
            if (colorsOpposite(e.first, correctF)) {
                return 2; // rotate 180 to get to correct position
            }
            return 1; // rotate 90 to get to correct position
        }

        if (e.first == correctS) {
            if (colorsOpposite(e.second, correctF)) {
                return 3; // in same layer, rotate 3 90's
            }
            return 2; // in same layer, rotate 2 90's
        }
        if (e.second == correctF) {
            if (colorsOpposite(e.first, correctS)) {
                return 3; // in same layer, rotate 3 90's
            }
            return 2; // in same layer, rotate 2 90's
        }

        if (colorsOpposite(e.first, correctF)) {
            if (colorsOpposite(e.second, correctS)) {
                return 4; // exact opposites position and color, rotate 2 180's
            }
            return 3; // rotate 1 90 and 1 180
        }

        if (colorsOpposite(e.first, correctS) && colorsOpposite(e.second, correctF)) {
            return 3; // exact opposite position but not color, rotate 3 90's
        }
        return 2; // rotate 2 90's

    }

    /** possible values 0 to 3 */
    private static int dist(Corner c, Layer l1, Layer l2, Layer l3) {

        Color correctF = correct(l1);
        Color correctS = correct(l2);
        Color correctT = correct(l3);

        if (c.first == correctF) { // on same layer and only need to rotate one direction
            if (c.second == correctS) {
                return 0; // correct position
            }
            if (colorsOpposite(c.second, correctS)) {
                return 2; // diagonal on layer, rotate 180
            }
            return 1; // adjacent, rotate 90
        }
        if (c.second == correctS) { // on same layer and only need to rotate one direction
            if (colorsOpposite(c.first, correctF)) {
                return 2; // diagonal on layer, rotate 180
            }
            return 1; // adjacent, rotate 90
        }
        if (c.third == correctT) { // on same layer and only need to rotate one direction
            if (colorsOpposite(c.first, correctF)) {
                return 2; // diagonal on layer, rotate 180
            }
            return 1; // adjacent, rotate 90
        }

        if (c.first == correctS) { // on same layer
            if (colorsOpposite(c.third, correctT)) {
                return 3; // adjacent, rotate 1 90 and 1 180
            }
            return 2; // either correct position or diagonal, rotate 2 90's either way
        }
        if (c.first == correctT) { // on same layer
            if (colorsOpposite(c.second, correctS)) {
                return 3; // adjacent, rotate 1 90 and 1 180
            }
            return 2; // either correct position or diagonal, rotate 2 90's either way
        }
        if (c.second == correctF) { // on same layer
            if (colorsOpposite(c.third, correctT)) {
                return 3; // adjacent, rotate 1 90 and 1 180
            }
            return 2; // either correct position or diagonal, rotate 2 90's either way
        }
        if (c.second == correctT) { // on same layer
            if (colorsOpposite(c.first, correctF)) {
                return 3; // adjacent, rotate 1 90 and 1 180
            }
            return 2; // either correct position or diagonal, rotate 2 90's either way
        }
        if (c.third == correctF) { // on same layer
            if (colorsOpposite(c.second, correctS)) {
                return 3; // adjacent, rotate 1 90 and 1 180
            }
            return 2; // either correct position or diagonal, rotate 2 90's either way
        }
        if (c.third == correctS) { // on same layer
            if (colorsOpposite(c.first, correctF)) {
                return 3; // adjacent, rotate 1 90 and 1 180
            }
            return 2; // either correct position or diagonal, rotate 2 90's either way
        }

        // positions are exact opposites (not same layer), orientation has only one possibility
        return 3; // either 3 90's or 1 90 and 1 180

    }

    private static boolean colorsOpposite(Color c1, Color c2) {
        return (c1 == Color.Y && c2 == Color.W) || (c1 == Color.W && c2 == Color.Y)
                || (c1 == Color.B && c2 == Color.G) || (c1 == Color.G && c2 == Color.B)
                || (c1 == Color.R && c2 == Color.O) || (c1 == Color.O && c2 == Color.R);
    }

    private static Color correct(Layer layer) {
        switch (layer) {
            case UP:
                return Color.Y;
            case DOWN:
                return Color.W;
            case FRONT:
                return Color.B;
            case BACK:
                return Color.G;
            case RIGHT:
                return Color.R;
            case LEFT:
                return Color.O;
        }
        return null;
    }

    private RubiksCube transform(Layer layer, boolean counterclockwise) {
        RubiksCube newCube = new RubiksCube(this);
        switch (layer) {
            case UP:
                if (counterclockwise) {
                    // insert last into first
                    newCube.topC.add(0, newCube.topC.remove(3));
                    newCube.topE.add(0, newCube.topE.remove(3));
                } else {
                    // add first to last
                    newCube.topC.add(newCube.topC.remove(0));
                    newCube.topE.add(newCube.topE.remove(0));
                }
                // flip sides to account for rotation
                for (int i = 0; i < 4; i++) {
                    newCube.topC.set(i, newCube.topC.get(i).flipSides());
                }
                return newCube;

            case DOWN:
                if (counterclockwise) {
                    // insert last into first
                    newCube.bottomC.add(0, newCube.bottomC.remove(3));
                    newCube.bottomE.add(0, newCube.bottomE.remove(3));
                } else {
                    // add first to last
                    newCube.bottomC.add(newCube.bottomC.remove(0));
                    newCube.bottomE.add(newCube.bottomE.remove(0));
                }
                // flip sides to account for rotation
                for (int i = 0; i < 4; i++) {
                    newCube.bottomC.set(i, newCube.bottomC.get(i).flipSides());
                }
                return newCube;

            case FRONT:
                if (counterclockwise) {
                    // corners
                    Corner temp = newCube.topC.get(0);
                    newCube.topC.set(0, newCube.topC.get(1).flipThird());
                    newCube.topC.set(1, newCube.bottomC.get(1).flipThird());
                    newCube.bottomC.set(1, newCube.bottomC.get(0).flipThird());
                    newCube.bottomC.set(0, temp.flipThird());

                    // edges
                    Edge temp2 = newCube.topE.get(0);
                    newCube.topE.set(0, newCube.middleE.get(1).flip());
                    newCube.middleE.set(1, newCube.bottomE.get(0).flip());
                    newCube.bottomE.set(0, newCube.middleE.get(0).flip());
                    newCube.middleE.set(0, temp2.flip());
                } else {
                    // corners
                    Corner temp = newCube.topC.get(0);
                    newCube.topC.set(0, newCube.bottomC.get(0).flipThird());
                    newCube.bottomC.set(0, newCube.bottomC.get(1).flipThird());
                    newCube.bottomC.set(1, newCube.topC.get(1).flipThird());
                    newCube.topC.set(1, temp.flipThird());

                    // edges
                    Edge temp2 = newCube.topE.get(0);
                    newCube.topE.set(0, newCube.middleE.get(0).flip());
                    newCube.middleE.set(0, newCube.bottomE.get(0).flip());
                    newCube.bottomE.set(0, newCube.middleE.get(1).flip());
                    newCube.middleE.set(1, temp2.flip());
                }
                return newCube;

            case BACK:
                if (counterclockwise) {
                    // corners
                    Corner temp = newCube.topC.get(2);
                    newCube.topC.set(2, newCube.bottomC.get(2).flipThird());
                    newCube.bottomC.set(2, newCube.bottomC.get(3).flipThird());
                    newCube.bottomC.set(3, newCube.topC.get(3).flipThird());
                    newCube.topC.set(3, temp.flipThird());

                    // edges
                    Edge temp2 = newCube.topE.get(2);
                    newCube.topE.set(2, newCube.middleE.get(2).flip());
                    newCube.middleE.set(2, newCube.bottomE.get(2).flip());
                    newCube.bottomE.set(2, newCube.middleE.get(3).flip());
                    newCube.middleE.set(3, temp2.flip());
                } else {
                    // corners
                    Corner temp = newCube.topC.get(2);
                    newCube.topC.set(2, newCube.topC.get(3).flipThird());
                    newCube.topC.set(3, newCube.bottomC.get(3).flipThird());
                    newCube.bottomC.set(3, newCube.bottomC.get(2).flipThird());
                    newCube.bottomC.set(2, temp.flipThird());

                    // edges
                    Edge temp2 = newCube.topE.get(2);
                    newCube.topE.set(2, newCube.middleE.get(3).flip());
                    newCube.middleE.set(3, newCube.bottomE.get(2).flip());
                    newCube.bottomE.set(2, newCube.middleE.get(2).flip());
                    newCube.middleE.set(2, temp2.flip());
                }
                return newCube;

            case RIGHT:
                if (counterclockwise) {
                    // corners
                    Corner temp = newCube.topC.get(1);
                    newCube.topC.set(1, newCube.topC.get(2).flipSecond());
                    newCube.topC.set(2, newCube.bottomC.get(2).flipSecond());
                    newCube.bottomC.set(2, newCube.bottomC.get(1).flipSecond());
                    newCube.bottomC.set(1, temp.flipSecond());

                    // edges
                    Edge temp2 = newCube.topE.get(1);
                    newCube.topE.set(1, newCube.middleE.get(2));
                    newCube.middleE.set(2, newCube.bottomE.get(1));
                    newCube.bottomE.set(1, newCube.middleE.get(1));
                    newCube.middleE.set(1, temp2);
                } else {
                    // corners
                    Corner temp = newCube.topC.get(1);
                    newCube.topC.set(1, newCube.bottomC.get(1).flipSecond());
                    newCube.bottomC.set(1, newCube.bottomC.get(2).flipSecond());
                    newCube.bottomC.set(2, newCube.topC.get(2).flipSecond());
                    newCube.topC.set(2, temp.flipSecond());

                    // edges
                    Edge temp2 = newCube.topE.get(1);
                    newCube.topE.set(1, newCube.middleE.get(1));
                    newCube.middleE.set(1, newCube.bottomE.get(1));
                    newCube.bottomE.set(1, newCube.middleE.get(2));
                    newCube.middleE.set(2, temp2);
                }
                return newCube;

            case LEFT:
                if (counterclockwise) {
                    // corners
                    Corner temp = newCube.topC.get(0);
                    newCube.topC.set(0, newCube.topC.get(3).flipSecond());
                    newCube.topC.set(3, newCube.bottomC.get(3).flipSecond());
                    newCube.bottomC.set(3, newCube.bottomC.get(0).flipSecond());
                    newCube.bottomC.set(0, temp.flipSecond());

                    // edges
                    Edge temp2 = newCube.topE.get(3);
                    newCube.topE.set(3, newCube.middleE.get(3));
                    newCube.middleE.set(3, newCube.bottomE.get(3));
                    newCube.bottomE.set(3, newCube.middleE.get(0));
                    newCube.middleE.set(0, temp2);
                } else {
                    // corners
                    Corner temp = newCube.topC.get(0);
                    newCube.topC.set(0, newCube.bottomC.get(0).flipSecond());
                    newCube.bottomC.set(0, newCube.bottomC.get(3).flipSecond());
                    newCube.bottomC.set(3, newCube.topC.get(3).flipSecond());
                    newCube.topC.set(3, temp.flipSecond());

                    // edges
                    Edge temp2 = newCube.topE.get(3);
                    newCube.topE.set(3, newCube.middleE.get(0));
                    newCube.middleE.set(0, newCube.bottomE.get(3));
                    newCube.bottomE.set(3, newCube.middleE.get(3));
                    newCube.middleE.set(3, temp2);
                }
                return newCube;
        }
        return null;
    }

    /** *************************** Public helpers *************************** */

    @Override
    public String toString() {

        String repr = "      ";

        // up face
        repr += topC.get(3).first + " ";
        repr += topE.get(2).first + " ";
        repr += topC.get(2).first + "\n      ";

        repr += topE.get(3).first + " ";
        repr += "Y ";
        repr += topE.get(1).first + "\n      ";

        repr += topC.get(0).first + " ";
        repr += topE.get(0).first + " ";
        repr += topC.get(1).first + "\n";

        // top row of left face, front face, right face, bottom face
        repr += topC.get(3).third + " ";
        repr += topE.get(3).second + " ";
        repr += topC.get(0).third + " ";

        repr += topC.get(0).second + " ";
        repr += topE.get(0).second + " ";
        repr += topC.get(1).second + " ";

        repr += topC.get(1).third + " ";
        repr += topE.get(1).second + " ";
        repr += topC.get(2).third + " ";

        repr += topC.get(2).second + " ";
        repr += topE.get(2).second + " ";
        repr += topC.get(3).second + "\n";

        // middle row of left face, front face, right face, bottom face
        repr += middleE.get(3).second + " ";
        repr += "O ";
        repr += middleE.get(0).second + " ";

        repr += middleE.get(0).first + " ";
        repr += "B ";
        repr += middleE.get(1).first + " ";

        repr += middleE.get(1).second + " ";
        repr += "R ";
        repr += middleE.get(2).second + " ";

        repr += middleE.get(2).first + " ";
        repr += "G ";
        repr += middleE.get(3).first + "\n";

        // last row of left face, front face, right face, bottom face
        repr += bottomC.get(3).third + " ";
        repr += bottomE.get(3).second + " ";
        repr += bottomC.get(0).third + " ";

        repr += bottomC.get(0).second + " ";
        repr += bottomE.get(0).second + " ";
        repr += bottomC.get(1).second + " ";

        repr += bottomC.get(1).third + " ";
        repr += bottomE.get(1).second + " ";
        repr += bottomC.get(2).third + " ";

        repr += bottomC.get(2).second + " ";
        repr += bottomE.get(2).second + " ";
        repr += bottomC.get(3).second + "\n      ";

        // down face
        repr += bottomC.get(0).first + " ";
        repr += bottomE.get(0).first + " ";
        repr += bottomC.get(1).first + "\n      ";

        repr += bottomE.get(3).first + " ";
        repr += "W ";
        repr += bottomE.get(1).first + "\n      ";

        repr += bottomC.get(3).first + " ";
        repr += bottomE.get(2).first + " ";
        repr += bottomC.get(2).first + "\n";

        return repr;
    }

    @Override
    public final boolean equals(Object o) {
        RubiksCube rc = (RubiksCube) o;
        return topC.equals(rc.topC) && bottomC.equals(rc.bottomC) && topE.equals(rc.topE)
                && middleE.equals(rc.middleE) && bottomE.equals(rc.bottomE);
    }

    public final int hashCode() {
        int hashCode = topC.hashCode() * 31;
        hashCode = (hashCode + bottomC.hashCode()) * 31;
        hashCode = (hashCode + topE.hashCode()) * 31;
        hashCode = (hashCode + middleE.hashCode()) * 31;
        hashCode = hashCode + bottomE.hashCode();
        return hashCode;
    }

    /** **************************** Private classes/enums **************************** */

    private static enum Color {
        Y, B, R, W, G, O
    }

    private static enum Layer {
        UP, DOWN, FRONT, BACK, LEFT, RIGHT
    }

    private static class Edge {
        /** The color facing up or down. If no such color, the color facing front or back. */
        private final Color first;
        /** The color facing left or right. If so such color, the color facing front or back. */
        private final Color second;

        public Edge(Color f, Color s) {
            first = f;
            second = s;
        }

        public Edge flip() {
            return new Edge(second, first);
        }

        @Override
        public String toString() {
            return "" + first + second;
        }

        @Override
        public boolean equals(Object o) {
            Edge e = (Edge) o;
            return first == e.first && second == e.second;
        }

        @Override
        public int hashCode() {
            return first.hashCode() + 31 * second.hashCode();
        }
    }

    private static class Corner {
        /** The color facing up or down. */
        private final Color first;
        /** The color facing front or back. */
        private final Color second;
        /** The color facing left or right. */
        private final Color third;

        public Corner(Color f, Color s, Color t) {
            first = f;
            second = s;
            third = t;
        }

        public Corner flipSides() {
            return new Corner(first, third, second);
        }

        public Corner flipSecond() {
            return new Corner(second, first, third);
        }

        public Corner flipThird() {
            return new Corner(third, second, first);
        }

        @Override
        public String toString() {
            return "" + first + second + third;
        }

        @Override
        public boolean equals(Object o) {
            Corner c = (Corner) o;
            return first == c.first && second == c.second && third == c.third;
        }

        @Override
        public int hashCode() {
            return first.hashCode() + 31 * (second.hashCode() + 31 * third.hashCode());
        }

    }

    /** **************************** Methods for debugging etc **************************** */

    private static RubiksCube complete() {
        RubiksCube rc = new RubiksCube();

        rc.topC.add(new Corner(Color.Y, Color.B, Color.O));
        rc.topC.add(new Corner(Color.Y, Color.B, Color.R));
        rc.topC.add(new Corner(Color.Y, Color.G, Color.R));
        rc.topC.add(new Corner(Color.Y, Color.G, Color.O));

        rc.bottomC.add(new Corner(Color.W, Color.B, Color.O));
        rc.bottomC.add(new Corner(Color.W, Color.B, Color.R));
        rc.bottomC.add(new Corner(Color.W, Color.G, Color.R));
        rc.bottomC.add(new Corner(Color.W, Color.G, Color.O));

        rc.topE.add(new Edge(Color.Y, Color.B));
        rc.topE.add(new Edge(Color.Y, Color.R));
        rc.topE.add(new Edge(Color.Y, Color.G));
        rc.topE.add(new Edge(Color.Y, Color.O));

        rc.middleE.add(new Edge(Color.B, Color.O));
        rc.middleE.add(new Edge(Color.B, Color.R));
        rc.middleE.add(new Edge(Color.G, Color.R));
        rc.middleE.add(new Edge(Color.G, Color.O));

        rc.bottomE.add(new Edge(Color.W, Color.B));
        rc.bottomE.add(new Edge(Color.W, Color.R));
        rc.bottomE.add(new Edge(Color.W, Color.G));
        rc.bottomE.add(new Edge(Color.W, Color.O));

        return rc;
    }

    public static RubiksCube exampleWith10Turns(boolean showAllDistances) {
        RubiksCube start = complete();

        start = start.transform(Layer.BACK, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.LEFT, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.FRONT, false);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.RIGHT, false);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.LEFT, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.BACK, false);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.DOWN, false);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.DOWN, false);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.UP, false);
        showDistance(showAllDistances, start);

        // comment out to make into 9 turns
        start = start.transform(Layer.FRONT, false);
        showDistance(showAllDistances, start);

        // uncomment to make into 11 turns
        // start = start.transform(Layer.FRONT, false);
        // showDistance(showAllDistances, start);

        // uncomment to make into 12 turns
        // start = start.transform(Layer.RIGHT, true);
        // showDistance(showAllDistances, start);
        // up to here the fastest way to solve is going back the way you came (11 turns)

        return start;
    }

    public static RubiksCube exampleWith3Turns(boolean showAllDistances) {

        RubiksCube start = complete();

        start = start.transform(Layer.BACK, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.LEFT, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.DOWN, false);
        showDistance(showAllDistances, start);

        return start;
    }

    public static RubiksCube allEdgesOpposite(boolean showAllDistances) {

        RubiksCube start = complete();

        start = start.transform(Layer.UP, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.UP, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.DOWN, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.DOWN, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.FRONT, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.FRONT, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.BACK, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.BACK, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.LEFT, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.LEFT, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.RIGHT, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.RIGHT, true);
        showDistance(showAllDistances, start);

        return start;

    }

    public static RubiksCube exampleWithCorrectEdgesOnly(boolean showDistance) {

        RubiksCube start = complete();

        start.topC.set(0, new Corner(Color.G, Color.W, Color.R));
        start.topC.set(1, new Corner(Color.G, Color.Y, Color.R));
        start.topC.set(2, new Corner(Color.O, Color.Y, Color.B));
        start.topC.set(3, new Corner(Color.Y, Color.G, Color.O));

        start.bottomC.set(0, new Corner(Color.B, Color.O, Color.W));
        start.bottomC.set(1, new Corner(Color.R, Color.W, Color.B));
        start.bottomC.set(2, new Corner(Color.G, Color.W, Color.O));
        start.bottomC.set(3, new Corner(Color.B, Color.Y, Color.R));

        showDistance(true, start);

        return start;
    }

    public static RubiksCube superFlip(boolean showAllDistances) {

        // R' U2 B L' F U' B D F U D' L D2 F' R B' D F' U' B' U D'
        RubiksCube start = complete();

        // R' U2 B L'
        start = start.transform(Layer.RIGHT, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.UP, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.UP, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.BACK, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.LEFT, false);
        showDistance(showAllDistances, start);

        // F U' B D F
        start = start.transform(Layer.FRONT, false);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.UP, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.BACK, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.DOWN, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.FRONT, false);
        showDistance(showAllDistances, start);

        // U D' L D2
        start = start.transform(Layer.UP, false);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.DOWN, false);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.LEFT, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.DOWN, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.DOWN, true);
        showDistance(showAllDistances, start);

        // F' R B' D F'
        start = start.transform(Layer.FRONT, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.RIGHT, false);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.BACK, false);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.DOWN, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.FRONT, true);
        showDistance(showAllDistances, start);

        // U' B' U D'
        start = start.transform(Layer.UP, true);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.BACK, false);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.UP, false);
        showDistance(showAllDistances, start);
        start = start.transform(Layer.DOWN, false);
        showDistance(showAllDistances, start);

        return start;
    }

    public static RubiksCube randomExample(int numTurns, boolean showAllDistances) {

        RubiksCube rc = complete();

        for (int i = 0; i < numTurns; i++) {
            rc = rc.transform(randLayer(), rand.nextBoolean());
            showDistance(showAllDistances, rc);
        }

        return rc;
    }

    private static Layer randLayer() {
        return LAYERS[rand.nextInt(6)];
    }

    public static void showDistance(boolean show, RubiksCube rc) {
        if (show) {
            System.out.println("total: " + rc.estimatedDistanceToGoal() + ", edge:" + rc.edgeDist()
                    + ", corner: " + rc.cornerDist());
        }
    }

    /**
     * Using only edge distance:
     * fast when minimum number of moves 10 or less, takes a very long time for 11 moves,
     * otherwise takes forever;
     * however, very fast with exampleAllEdgesOpposite;
     * downside is that may not be correct.
     * <br>
     * Using only corner distance:
     * becomes slow when minimum number of moves reaches 9;
     * may not be correct.
     * <br>
     * Using both:
     * basically no improvement upon using only edge distance, but guarantees correctness.
     */
    public static void main(String[] args) {

        RubiksCube start = randomExample(10, true);

        Solver solver = new Solver(start);

        StdOut.println("Minimum number of moves = " + solver.moves());
        for (WorldState ws : solver.solution()) {
            StdOut.println(ws);
        }

    }

}
