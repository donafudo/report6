package jp.ac.uryukyu.ie.e185761;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


        Maze maze=new Maze();

        maze.CreateMaze(99,99);
        for(int y=0;y<99;y++){
            for(int x=0;x<99;x++){
                if(maze.GetMaze()[y][x]==1)System.out.print("＠");
                else
                    System.out.print("　");
            }
            System.out.println();
        }
     }


    public static void main(String[] args) {
        launch(args);
    }
}
