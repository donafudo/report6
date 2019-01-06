package jp.ac.uryukyu.ie.e185761;

import java.util.*;

public class Maze {
    int mazeWidth;
    int mazeHeight;

    class mazeCoord {
        int x;
        int y;

        mazeCoord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void Set(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (o.getClass() != this.getClass()) return false;

            mazeCoord coord = (mazeCoord) o;
            return x == coord.x && y == coord.y;
        }
    }

    private int[][] mazeInfo;

    //壁が伸びていない柱のリスト
    private List<mazeCoord> nodes = new ArrayList<mazeCoord>();
    //探索中の柱のスタック
    private Deque<mazeCoord> paths = new LinkedList<mazeCoord>();

    public void CreateMaze(int width, int height) {
        mazeWidth = width;
        mazeHeight = height;

        InitializeMaze();

        //探索されていない柱がなくなるまで探索を繰り返す
        while (nodes.size() > 0) {
            System.out.println(nodes.size());

            chooseNode(nodes.get(new Random().nextInt(nodes.size())));
        }
    }

    private void InitializeMaze() {
        mazeInfo = new int[mazeHeight][mazeWidth];

        for (int i = 1; i < mazeHeight / 2; i++) {
            for (int j = 1; j < mazeWidth / 2; j++) {
                nodes.add(new mazeCoord(j * 2, i * 2));

                mazeInfo[i * 2][j * 2] = 1;
            }
        }

        for (int j = 0; j < mazeHeight; j++) {
            mazeInfo[0][j] = 1;
            mazeInfo[mazeWidth - 1][j] = 1;
        }
        for (int i = 0; i < mazeWidth; i++) {
            mazeInfo[i][0] = 1;
            mazeInfo[i][mazeHeight - 1] = 1;
        }
    }

    private int chooseNode(mazeCoord coord) {
        int r = 0;
        mazeCoord path_1;
        mazeCoord path_2;

        List<mazeCoord> movedirList = new ArrayList<mazeCoord>(Arrays.asList(
                new mazeCoord(2, 0),
                new mazeCoord(-2, 0),
                new mazeCoord(0, 2),
                new mazeCoord(0, -2)));

        if (paths.contains(coord)) {
            return 1;
        } else {

            paths.push(coord);
            boolean includenode = nodes.contains(coord);
            if (includenode) {
                nodes.remove(coord);
                Collections.shuffle(movedirList);

                for (mazeCoord dir : movedirList) {
                    r = chooseNode(new mazeCoord(coord.x + dir.x, coord.y + dir.y));
                    if (r == 0) break;
                }
                if (r == 1) {
                    nodes.add(paths.pop());
                }
                return r;
            } else {
                path_2 = paths.pop();
                do {
                    path_1 = path_2;
                    path_2 = paths.pop();
                    mazeInfo[(path_1.y + path_2.y) / 2][(path_1.x + path_2.x) / 2] = 1;
                } while (paths.size() > 0);
                return 0;
            }
        }
    }

    public int[][] GetMaze() {
        return mazeInfo;
    }
}
