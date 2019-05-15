

import customNode.FilteredComboBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test extends Application {

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        List<String> authors = getList();

        FilteredComboBox<String> fCombo = new FilteredComboBox<>();

        fCombo.set(authors);

        HBox root = new HBox();
        root.getChildren().add(fCombo);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public List<String> getList(){
        List<String> authors = new ArrayList<>();

        Date date = new Date(1546988400000L);


        authors.add("KG");
        authors.add("CACACAC");
        authors.add("KW");

        return authors;
    }
}
