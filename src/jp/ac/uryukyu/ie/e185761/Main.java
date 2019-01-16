package jp.ac.uryukyu.ie.e185761;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("MazeRunner");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


        int sizeX = 5;
        int sizeY = 5;

        Maze maze = new Maze();
        maze.CreateMaze(sizeX, sizeY);

        MazeRouteSearch search = new MazeRouteSearch(maze.GetMaze(), new mazeCoord(1, 1), new mazeCoord(sizeX - 2, sizeY - 2));

        while (search.RouteSarchOnce() == false) {
        }

        List<mazeCoord> path = search.getPath(search.CloseList.get(search.CloseList.size() - 1));

        List<Rectangle> rectList = new ArrayList<Rectangle>();

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                if (maze.GetMaze()[y][x] == 1) {
                    rectList.add(new Rectangle(x * 5, y * 5, 5, 5));
                    rectList.get(rectList.size() - 1).setFill(Color.GRAY);
                } else {
                    if (path.contains(new mazeCoord(x, y))) {
                        rectList.add(new Rectangle(x * 5, y * 5, 5, 5));
                        rectList.get(rectList.size() - 1).setFill(Color.GREEN);
                    }else{
                        rectList.add(new Rectangle(x * 5, y * 5, 5, 5));
                        rectList.get(rectList.size() - 1).setFill(Color.WHITE);
                    }
                }
            }
        }


        Pane pane = new Pane();
        pane.getChildren().setAll(rectList);
        primaryStage.setScene(new Scene(pane, 1000, 1000));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
