package jp.ac.uryukyu.ie.e185761;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MazeTest {

    @Test
    void createMaze() throws IllegalAccessException {
        int sizeX = 3;
        int sizeY = 11;

        try{
            Maze maze = new Maze();
            maze.CreateMaze(sizeX, sizeY);
        }catch (IllegalAccessException e){
            assertTrue((sizeX<3||sizeY<3||sizeX%2==0||sizeY%2==0));
        }
        assertTrue((sizeX>=3||sizeY>=3||sizeX%2!=0||sizeY%2!=0));
    }
}
