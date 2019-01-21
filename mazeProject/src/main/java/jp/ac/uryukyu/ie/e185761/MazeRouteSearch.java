package jp.ac.uryukyu.ie.e185761;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MazeRouteSearch {
    private List<mazeCoord> searchDir = new ArrayList<mazeCoord>(Arrays.asList(
            new mazeCoord(1, 0),
            new mazeCoord(-1, 0),
            new mazeCoord(0, 1),
            new mazeCoord(0, -1)));

    List<Node> OpenList = new ArrayList<Node>();
    List<Node> CloseList = new ArrayList<Node>();

    private Node[][] mMazeNodeData;

    private mazeCoord mStartPos;
    private mazeCoord mGoalPos;

    /**
     * 迷路をA*アルゴリズムで探索する
     * @param mazeData 0-road,1-wall
     * @param startIndex
     * @param goalIndex
     */
    MazeRouteSearch(int[][] mazeData, mazeCoord startIndex, mazeCoord goalIndex) {
        mStartPos = startIndex;
        mGoalPos = goalIndex;

        //迷路の道の部分にNodeを格納する
        mMazeNodeData = new Node[mazeData.length][mazeData[0].length];
        for (int i = 1; i < mazeData.length; i++) {
            for (int j = 1; j < mazeData[0].length; j++) {
                if (mazeData[i][j] != 1) {
                    mMazeNodeData[i][j] = new Node(new mazeCoord(j, i));
                }
            }
        }

        Node rootNode = mMazeNodeData[startIndex.y][startIndex.x];
        rootNode.Open(null, 0, CalcHCost(rootNode.pos));
        OpenList.add(rootNode);
    }

    public boolean RouteSarchOnce() {

        //OpenListをScoreを基準に昇順ソート
        Collections.sort(OpenList, new NodeSortComp());

        //スコアがもっとも小さいノードを基準ノードとし
        //四方のノードをOpenしていく
        Node baseNode = OpenList.get(0);
        for (mazeCoord dir : searchDir) {
            mazeCoord serchIndex = new mazeCoord(dir.x + baseNode.pos.x, dir.y + baseNode.pos.y);
            if (insideMaze(serchIndex)) {
                Node node = mMazeNodeData[serchIndex.y][serchIndex.x];
                if (node != null && node.nodeState == Node.NodeState.None) {
                    node.Open(baseNode, CalcCCost(serchIndex), CalcHCost(serchIndex));
                    OpenList.add(node);
                }
            }
        }

        //探索し終わったbaseNodeをCloseListに追加
        OpenList.remove(baseNode);
        CloseList.add(baseNode);

        if (baseNode.pos.equals(mGoalPos)) return true;

        //OpenListがなくなった時点で探索失敗とし探索を終了する
        if(OpenList.size()==0)return true;
        return false;
    }

    /**
     * スタート地点から目標ノードまでの経路(index)のリスト
     * @param leafNode 目標ノード
     * @return 目標地点までのmazeのindexのリスト
     */
    public List<mazeCoord> getPath(Node leafNode) {
        List<mazeCoord> path = new ArrayList<mazeCoord>();

        path.add(leafNode.pos);
        Node parent = leafNode.parentNode;

        while (parent != null) {
            path.add(parent.pos);
            parent = parent.parentNode;
        }

        Collections.reverse(path);

        return path;
    }

    private int CalcCCost(mazeCoord pos) {
        return Math.abs((mStartPos.x - pos.x) + (mStartPos.y - pos.y));
    }

    private int CalcHCost(mazeCoord pos) {
        return Math.abs((mGoalPos.x - pos.x) + (mGoalPos.y - pos.y));
    }

    private boolean insideMaze(mazeCoord index) {
        if (index.x < 0 || index.x > mMazeNodeData[0].length - 1) return false;
        if (index.y < 0 || index.y > mMazeNodeData.length - 1) return false;
        return true;
    }


}
