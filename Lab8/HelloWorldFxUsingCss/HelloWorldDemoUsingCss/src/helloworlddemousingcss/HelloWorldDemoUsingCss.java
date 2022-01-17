/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworlddemousingcss;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Dell
 */
public class HelloWorldDemoUsingCss extends Application {
        
    @Override
    public void start(Stage primaryStage) {

        // Root node
        StackPane root = new StackPane();
         VBox box = new VBox();
        
       
        // Scene
       Scene scene = new Scene(root, 400, 400);
       scene.getStylesheets().add("helloworlddemousingcss/style.css");
       
       
        
       // Nodes
       
       Rectangle rect = new Rectangle(0, 0, 400, 400);
        
       Text text = new Text("Hello JavaFX using CSS");
       Text reflectedtext = new Text("Hello JavaFX using CSS");
       reflectedtext.getStyleClass().add("reflectedText");
       text.getStyleClass().add("text");
     
       rect.getStyleClass().add("linear-grad-to-bottom");     
       box.getChildren().add(rect);
       
       root.getChildren().add(box);
       root.getChildren().add(text);
       root.getChildren().add(reflectedtext);
     

    

        
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
