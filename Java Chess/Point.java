// Point object used for the coordinates of the pieces
public class Point {
   
   // x coord
   private int x;
   
   // y coord
   private int y;

   // Constructs a Point object with "x" x-coord and "y" y-coord
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
   
   // 
   public Point update(int x, int y) {
      return new Point(x, y);
   }
   
   public Point sum(int addX, int addY) {
      return new Point(this.x + addX, this.y + addY);
   }
   
   public Point sum(Point p2) {
      return new Point(this.x + p2.x, this.y + p2.y);
   }
   
   public Point difference(Point p2) {
      return new Point(this.x - p2.x, this.y - p2.y);
   }
   
   public int getX() {
      return this.x;
   }
   
   public int getY() {
      return this.y;
   }
   
   public String toString() {
      return ("(" + x + "," + y + ")");
   }
}