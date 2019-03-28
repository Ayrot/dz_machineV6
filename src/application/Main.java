package application;
	
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Animation");
			Group root = new Group();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			Canvas canvas = new Canvas(750,420);
			root.getChildren().add(canvas);
			
			GraphicsContext gc = canvas.getGraphicsContext2D();
			
			Image car = new Image("machine.jpg",138,250,false,false,false);
			ImageView carView = new ImageView(car);
			carView.setRotate(90);
			carView.setX(180);
			carView.setY(80);
			
			Image[] arrayImages = new Image[3];
			for(int i=1;i<4;i++)
			{
				arrayImages[i-1] = new Image("doroga"+i+".jpg");
			}
			AnimatedImage man = new AnimatedImage();
			man.frames = arrayImages;
			man.duration = 0.05;
			
			final long startTime = System.nanoTime();
			
			new AnimationTimer() 
			{
				@Override
				public void handle(long now) {
					double dd = (now-startTime)/1000000000.0;
					
					gc.drawImage(man.geFrame(dd), 0, 0);
					
				}
				
			}.start();
			
			root.getChildren().add(carView);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
