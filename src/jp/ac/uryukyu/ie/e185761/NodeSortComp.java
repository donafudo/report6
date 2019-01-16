package jp.ac.uryukyu.ie.e185761;

import java.util.Comparator;

public class NodeSortComp implements Comparator<Node> {
    public int compare(Node c1, Node c2) {
        if (c1.Score < c2.Score) {
            return -1;
        } else if (c1.Score > c2.Score) {
            return 1;
        } else if (c1.C_Cost < c2.C_Cost) {
            return -1;
        } else {
            return 1;
        }
    }
}
