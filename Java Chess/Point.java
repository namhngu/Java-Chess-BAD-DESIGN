// Nam Nguyen
// Point object used for the coordinates
public class Point {
   
   // x coordinate
   private int x;
   
   // y coordinate
   private int y;

   // Pre:
   //    x: x-coordinate
   //    y: y-coordinate
   //
   // Post:
   // Constructs a point with a specific x and y coordinate
   public Point(int x, int y) {
      this.x = x;
      this.y = y;
   }
   
   // Compares this Point object with another Point "p2" and returns true if the two have the same coords
   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Point) {
         return (this.x == ((Point) obj).x && this.y == ((Point) obj).y);
      }
      return false;
   }

   // Pre:
   //    addX: Integer you want to add to the x of the point
   //    addY: Integer you want to add to the y of the point
   //
   // Post:
   // Returns a point representing the sum of this point plus the additions
   public Point sum(int addX, int addY) {
      return new Point(this.x + addX, this.y + addY);
   }

   // Pre:
   //    p2: The point you want to add to this point
   //
   // Post:
   // Returns a point representing the sum of this point plus the given point
   public Point sum(Point p2) {
      return new Point(this.x + p2.x, this.y + p2.y);
   }

   // Pre:
   //    p2: The point you want to minus this point with
   //
   // Post:
   // Returns a point representing the difference between this point minus the given
   public Point difference(Point p2) {
      return new Point(this.x - p2.x, this.y - p2.y);
   }


   // Post:
   // Returns the x coordinate of this point
   public int getX() {
      return this.x;
   }


   // Post:
   // Returns the y coordinate of this point
   public int getY() {
      return this.y;
   }


   // Post:
   // Returns the string representation of this point
   public String toString() {
      return ("(" + x + "," + y + ")");
   }
}