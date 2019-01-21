package jp.ac.uryukyu.ie.e185761;

import java.util.List;
import java.lang.IllegalAccessException;

public class Main  {

    public void start() throws IllegalAccessException{



        int sizeX = 31;
        int sizeY = 31;

        Maze maze = new Maze();
        maze.CreateMaze(sizeX, sizeY);

        MazeRouteSearch search = new MazeRouteSearch(maze.GetMaze(), new mazeCoord(1, 1), new mazeCoord(sizeX - 2, sizeY - 2));

        while (search.RouteSarchOnce() == false) {
        }

        List<mazeCoord> path = search.getPath(search.CloseList.get(search.CloseList.size() - 1));


        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                if (maze.GetMaze()[y][x] == 1) {
                    System.out.print("＠");
                } else {
                    if (path.contains(new mazeCoord(x, y))) {
                        System.out.print("８");
                    }else{
                        System.out.print("　");
                    }
                }
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws IllegalAccessException
    {
        Main main =new Main();
        main.start();
    }
}
