package jp.ac.uryukyu.ie.e185761;

class Node {
    enum NodeState {
        None,
        Open,
        Close
    }

    public NodeState nodeState = NodeState.None;

    public Node parentNode;

    //迷路内の座標
    public mazeCoord pos;

    //実コスト
    public int C_Cost;
    //推定コスト
    public int H_Cost;

    public int Score;

    Node(mazeCoord position) {
        pos = position;
    }

    /**
     * ノードをオープンする
     * @param parent
     * @param cCost　実コスト(スタート地点からのコスト)
     * @param hCost　仮コスト(このノードの位置からゴールノードまでの最小コスト)
     */
    public void Open(Node parent, int cCost, int hCost) {
        nodeState = NodeState.Open;
        parentNode=parent;

        C_Cost = cCost;
        H_Cost = hCost;
        Score = cCost + hCost;
    }

    public void Close() {
        nodeState = NodeState.Close;
    }
}
