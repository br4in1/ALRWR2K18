/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.GestionArticles;

import Entities.Article;
import Services.ArticleCrud;
import com.jfoenix.controls.JFXButton;
import java.awt.Font;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author raiiz
 */
public class ShowArticlesFrontController implements Initializable {

    @FXML
    private FlowPane homeContent;
    private StackPane[] stackpanes;
    private GridPane[] gridPanes;
    private GridPane gridPaneContent;
    @FXML
    private ScrollPane contentPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.control.Label?>
<?import javafx.scene.text.Font?>


<Label layoutX="29.0" layoutY="16.0" prefHeight="26.0" prefWidth="124.0" text="Recent News" xmlns="http://javafx.com/javafx/8.0.141" xmlns:fx="http://javafx.com/fxml/1">
   <font>
      <Font size="15.0" />
   </font>
</Label>*/

        gridPaneContent = new GridPane();
        /*ArticleCrud.getRepository().findAll().forEach(e -> {
            int i = 0;
            //gp.setId("gp");
            Label lbTitre = new Label(e.getTitre());
            Label lbDescription = new Label(e.getContenu());
            Label lbDate = new Label(e.getDatePublication().toString());
            ImageView articleImage = new ImageView(e.getArticleImage());
            articleImage.setFitHeight(200);
            articleImage.setFitWidth(200);
            JFXButton readbt = new JFXButton("Read [+]");
            readbt.setStyle("-fx-background-color: #66ae2e;");
            readbt.setAlignment(Pos.CENTER_RIGHT);
            
            gridPaneContent.add(lbTitre, 2,i);
            gridPaneContent.add(lbDate, 2, i+1);
            gridPaneContent.add(lbDescription, 2, i+2);
            gridPaneContent.add(articleImage, 1, i+2);
            gridPaneContent.add(readbt, 3, i+4);
            //homeContent.getChildren().add(gridPaneContent);
                    
            //gridPaneContent.add(gp, 0, i);
            i++;
        });*/
 /*List<Article> a = ArticleCrud.getRepository().findAll();
        for (int i = 0; i < a.size(); i++) {
            Label lbTitre = new Label(a.get(i).getTitre());
            Label lbDescription = new Label(a.get(i).getContenu());
            Label lbDate = new Label(a.get(i).getDatePublication().toString());
            ImageView articleImage = new ImageView(a.get(i).getArticleImage());
            articleImage.setFitHeight(200);
            articleImage.setFitWidth(200);
            JFXButton readbt = new JFXButton("Read [+]");
            readbt.setStyle("-fx-background-color: #66ae2e;");
            readbt.setAlignment(Pos.CENTER_RIGHT);

            gridPaneContent.add(lbTitre, 1, i+1);
            gridPaneContent.add(lbDate, 1, i + 2);
            gridPaneContent.add(lbDescription, 1, i + 3);
            gridPaneContent.add(articleImage, 0, i + 2);
            gridPaneContent.add(readbt, 2, i + 2);
            //homeContent.getChildren().add(gridPaneContent);

            //gridPaneContent.add(gp, 0, i);
        }
        contentPane.setContent(gridPaneContent);*/
        // create a grid with some sample data.
        GridPane grid = new GridPane();
        List<Article> a = ArticleCrud.getRepository().findAll();

        for (int i = 0; i < a.size(); i++) {
            ImageView iv = new ImageView(a.get(i).getArticleImage());
            iv.setFitWidth(150);
            iv.setFitHeight(150);
            //grid.addRow(i, new Label(), new Label(a.get(i).getTitre()), new Label());
            //grid.addRow(i + 1, iv, new Label(a.get(i).getContenu()), new Label());
            //grid.addRow(i + 2, new Label(), new JFXButton("Read [+]"));
            grid.add(new Label(a.get(i).getTitre()), 2, i);
            grid.add(new Label(a.get(i).getDatePublication().toString()),2,i+1);
            grid.add(iv,0,i+1);
            grid.add(new Label(a.get(i).getContenu()),2,i+2);
            grid.add(new JFXButton("Read [+]"), 1, i+3);
            
        };
        //grid.addRow(1, new Label("A"), new Label("B"), new Label("C"));

        // make all of the Controls and Panes inside the grid fill their grid cell, 
        // align them in the center and give them a filled background.
        // you could also place each of them in their own centered StackPane with 
        // a styled background to achieve the same effect.
        /*for (Node n : grid.getChildren()) {
            if (n instanceof JFXButton) {
            
                n.setStyle("-fx-background-color: #FFFFFF; -fx-alignment: center;");
            }
            if (n instanceof Label) {
             
                n.setStyle("-fx-background-color: cornsilk; -fx-alignment: center;");
            }
        }

        ColumnConstraints oneThird = new ColumnConstraints();
        oneThird.setPercentWidth(100 / 3.0);
        oneThird.setHalignment(HPos.LEFT);
        grid.getColumnConstraints().addAll(oneThird, oneThird, oneThird);
       */

        contentPane.setContent(grid);
        // can be uncommented to show the grid lines for debugging purposes, but not particularly useful for styling purposes.
        //grid.setGridLinesVisible(true);

    }

}
