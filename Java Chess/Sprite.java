import java.awt.*;
import javax.swing.ImageIcon;

public abstract class Sprite {
   
   protected Point coord;
   private String spritePicName;
   private Image image;
   
   public Sprite(int x, int y, String spritePicName) {
      coord = new Point(x, y);
      this.spritePicName = spritePicName;
      loadImage();
   }
   
   public void loadImage() {
      ImageIcon icon = new ImageIcon("Sprites\\" + spritePicName);
      image = icon.getImage();
      image = image.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
   }
   
   public Image getImage() {
      return image;
   }
   
   public void update(int x, int y) {
      coord = new Point(x,y);
   }
   
   public void addXY(int x, int y) {
      if (inBounds(coord.sum(x, y))){ 
         coord = coord.sum(x, y);
      }
   }
   
   protected boolean inBounds(Point p) { 
      return ((p.getX() >= 0 && p.getX() <= 7) && (p.getY() >= 0 && p.getY() <= 7));
   }
   
   public Point getCoord() {
      return coord;
   }
}