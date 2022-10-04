// Nam Nguyen
// Sprite represent a sprite of specific coordinate and picture
import java.awt.*;
import javax.swing.ImageIcon;

public abstract class Sprite {

   // The display coordinate of the sprite
   protected Point coord;

   // The String representing picture/address of the sprite
   private String spritePicName;

   // The Image of the sprite's image
   protected Image image;

   // Pre:
   //    x: x-coordinate
   //    y: y-coordinate
   //    spritePicName: the String representing picture/address of the sprite
   //
   // Post:
   // Creates a Sprite of a specific image and type at coordinate x and y.
   public Sprite(int x, int y, String spritePicName) {
      coord = new Point(x, y);
      this.spritePicName = spritePicName;
      loadImage();
   }

   // Post:
   // Loads the image of sprite
   public void loadImage() {
      ImageIcon icon = new ImageIcon("Sprites\\" + spritePicName);
      image = icon.getImage();
      image = image.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
   }

   // Post:
   // Returns the image of the sprite
   public Image getImage() {
      return image;
   }

   // Pre:
   //    x: x-coordinate
   //    y: y-coordinate
   //
   // Post:
   // Updates the display coordinate to specific (x,y)
   public void update(int x, int y) {
      coord = new Point(x,y);
   }

   // Pre:
   //    x: x-coordinate
   //    y: y-coordinate
   //
   // Post:
   // Adds an x,y value to the display coordinate
   public void addXY(int x, int y) {
      if (inBounds(coord.sum(x, y))){ 
         coord = coord.sum(x, y);
      }
   }

   // Pre:
   //    p: A point
   //
   // Post:
   // Returns whether the point is inbounds or not
   protected boolean inBounds(Point p) { 
      return ((p.getX() >= 0 && p.getX() <= 7) && (p.getY() >= 0 && p.getY() <= 7));
   }

   // Post:
   // Returns the display coordinate of the sprite
   public Point getCoord() {
      return coord;
   }
}