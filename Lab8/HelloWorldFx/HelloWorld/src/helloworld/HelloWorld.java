/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld;

import java.awt.MultipleGradientPaint;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Dell
 */
public class HelloWorld extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Node
        StackPane pane = new StackPane();
        
        Text text = new Text("Hello JavaFX");
        text.setFont(Font.font(32));
        text.setFill(Color.RED);
        
        Reflection reflection = new Reflection(); 
        reflection.setBottomOpacity(0.0); 
      
        //setting the top opacity of the reflection 
        reflection.setTopOpacity(0.5); 
      
        //setting the top offset of the reflection 
        reflection.setTopOffset(0.0);
      
        //Setting the fraction of the reflection 
        reflection.setFraction(0.7); 

        text.setEffect(reflection);
        
        VBox vbox = new VBox();
        pane.getChildren().add(vbox);
        
        Stop[] stops = {new Stop(1, Color.BLACK),
            new Stop(0, Color.WHITE)};

        Rectangle firstRect = new Rectangle(0, 0, 300, 200);
        Rectangle secondRect = new Rectangle(0, 200, 300, 200);
        
        LinearGradient lg = new LinearGradient(0, 1, 0, 0, true, CycleMethod.REFLECT, stops);
        LinearGradient lg2 = new LinearGradient(0, 0, 0, 1, true, CycleMethod.REFLECT, stops);
        firstRect.setFill(lg);
        secondRect.setFill(lg2);

        // Root node
        //StackPane rootPane = new StackPane(text);
        vbox.getChildren().add(firstRect);
        vbox.getChildren().add(secondRect);
        
        pane.getChildren().add(text);
        

        // Scene
        Scene scene = new Scene(pane, 300, 400);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
