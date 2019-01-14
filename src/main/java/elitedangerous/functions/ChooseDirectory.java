package elitedangerous.functions;
 
import java.io.File;
 
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static elitedangerous.utils.Constants.*;
 
public class ChooseDirectory extends Application {
 
    @Override
    public void start(Stage primaryStage) throws Exception {
 
        final javafx.stage.DirectoryChooser directoryChooser = new javafx.stage.DirectoryChooser();
        configuringDirectoryChooser(directoryChooser);
 
        TextArea textArea = new TextArea();
        textArea.setMinHeight(70);
 
        Button button = new Button("Choose Elite Dangerous Log folder");
 
        button.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                File dir = directoryChooser.showDialog(primaryStage);
                if (dir != null) {
                    textArea.setText(dir.getAbsolutePath());
                } else {
                    textArea.setText(null);
                }
            }
        });
 
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(5);
 
        root.getChildren().addAll(textArea, button);
 
        Scene scene = new Scene(root, 400, 200);
 
        primaryStage.setTitle(PROGRAMM_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    private void configuringDirectoryChooser( javafx.stage.DirectoryChooser directoryChooser) {
        // Set title for ChooseDirectory
        directoryChooser.setTitle("Select");
 
        // Set Initial Directory
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }
 
    public static void main(String[] args) {
        Application.launch(args);
    }
 
}
