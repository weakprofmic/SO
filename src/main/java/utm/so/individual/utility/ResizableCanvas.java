package utm.so.individual.utility;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;

public class ResizableCanvas extends Canvas {

    public ResizableCanvas() {
        // Redraw canvas when size changes.
        widthProperty().addListener(evt -> draw());
        heightProperty().addListener(evt -> draw());
    }

    private void draw() {
        double width = getWidth();
        double height = getHeight();

        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);

        // gc.setStroke(Color.RED);
        // gc.strokeLine(0, 0, width, height);
        // gc.strokeLine(0, height, width, 0)
        // Set line width
        gc.setLineWidth(2.0);
        // Set fill color
        gc.setFill(Color.RED);
        // Draw a rounded Rectangle
        // gc.fillRoundRect(width/2 - 150, height/2 - 75, 300, 150, 10, 10);

        gc.fillOval(width / 2 - width / 8, height / 2 - width / 8, width / 4, width / 4);
        gc.setFill(Color.BLACK);

        gc.save();
        // Transform the origin of the coordinate system to the center of the dial
        gc.translate(width / 2, height / 2);
        // Coordinates rotate counterclockwise by angle -90
        // gc.rotate(-90);
        double r = width / 8;
        Image image1 = new Image(getClass().getClassLoader().getResourceAsStream("img/aristo.jpg"), 100, 100, false,
                true);
        ImagePattern imagePattern = new ImagePattern(image1);


        for (int i = 0; i < 10; ++i) {
            gc.rotate(360 / 10);
            gc.setFill(Color.BLACK);
            gc.strokeLine(50, 0, r - 50, 0);

            gc.save();
            gc.rotate(-360 / 20);
            // The current coordinate is switched to (250,0), which is the left boundary
            // position of the scale
            gc.translate(r + 100, 0);
            // Set the table number position relative to the desktop should be vertical
            // gc.rotate(360 / 10);
            // gc.fillRect(0, 0, 50, 50);
           
            // gc.rotate(-(i + 1) * 360 / 5);
            // gc.rotate(180);
            gc.rotate(90);
            gc.drawImage(image1, -100/2,-100/2,100,100);
            
            gc.rotate(-90);
            gc.translate(100, 0);
            gc.rotate(90);
            gc.setFill(Color.BLACK);
            gc.fillRoundRect(-150/2,0,150,50, 20, 20);
            gc.setFill(Color.WHITE);
            gc.setFont(Font.font(48));
            gc.fillText("I am thinking", -120/2,40,120);
            gc.restore();
            

            // gc.fillText(i + "", width / 2 + width / 6 * Math.cos(2 * Math.PI / 5 * (i +
            // 0.5)),
            // height / 2 + width / 6 * Math.sin(2 * Math.PI / 5 * (i + 0.5)));
            // gc.setFill(Color.WHITE);
            // gc.fillRect(width / 2 + (width / 6 + 50) * Math.cos(2 * Math.PI / 5 * (i +
            // 0.5)),
            // height / 2 + (width / 6 + 50) * Math.sin(2 * Math.PI / 5 * (i + 0.5)), 50,
            // 50);

        }
        gc.restore();

        // for (int i = 0; i < 5; ++i) {
        // gc.setFill(Color.BLACK);
        // gc.strokeLine(width / 2, height / 2, width / 2 + width / 10 * Math.cos(2 *
        // Math.PI / 5 * i),
        // height / 2 + width / 10 * Math.sin(2 * Math.PI / 5 * i));
        // gc.strokeLine(width / 2, height / 2, width / 2 + width / 10 * Math.cos(2 *
        // Math.PI / 5 * i),
        // height / 2 + width / 10 * Math.sin(2 * Math.PI / 5 * i));
        // gc.fillText(i + "", width / 2 + width / 6 * Math.cos(2 * Math.PI / 5 * (i +
        // 0.5)),
        // height / 2 + width / 6 * Math.sin(2 * Math.PI / 5 * (i + 0.5)));
        // gc.setFill(Color.WHITE);
        // gc.fillRect(width / 2 + (width / 6 + 50) * Math.cos(2 * Math.PI / 5 * (i +
        // 0.5)),
        // height / 2 + (width / 6 + 50) * Math.sin(2 * Math.PI / 5 * (i + 0.5)), 50,
        // 50);

        // }
        // gc.strokeLine(width/2, height/2, width/2 - width/8, height/2);

        // gc.setFill(Color.RED);
        // gc.fillOval(width / 2 - width / 32, height / 2 - width / 32, width / 16,
        // width / 16);
        // Clear the rectangular area from the canvas
    
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }
}
