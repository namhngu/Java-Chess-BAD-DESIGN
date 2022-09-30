import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class ChessBoard extends JPanel implements ActionListener {
   
   private Image boardPic;
   private Image takeFrame;
   private Image moveFrame;
   private Image shadow;
   private Image whiteWins;
   private Image blackWins;
   private Image stalemate;
   private boolean whiteTurn;
   private Piece whiteKing;
   private Piece blackKing;
   private ArrayList<Piece> blackPieces = new ArrayList<Piece>();
   private ArrayList<Piece> whitePieces = new ArrayList<Piece>();;
   private ArrayList<Point> moves = new ArrayList<Point>();
   private ArrayList<Point> specialMoves = new ArrayList<Point>();
   private SelectionTool selectTool;
   private Piece[][] pieces;
   private Timer timer;
   private boolean selected;
   private Piece selectedPiece;
   private boolean endGame = false;
   private final int DELAY = 2;
   private final int PIX_SIZE = 480;
   private Pawn pawnDoubleJumped;

   public ChessBoard() {
      initBoard();
   }
   
   private void initBoard() {
      whiteTurn = true;
      addKeyListener(new TAdapter());
      setFocusable(true);
      
      loadBoard();
      
      loadSquares();
      
      loadShadow();
      
      selectTool = new SelectionTool(0, 0);
      
      loadPieces();
      
      loadEndScreens();
      
      int w = boardPic.getWidth(this);
      int h = boardPic.getHeight(this);
      setPreferredSize(new Dimension(PIX_SIZE, PIX_SIZE));
      
      selected = false;
      
      timer = new Timer(DELAY, this);
      timer.start(); 
   }
   
   private void loadBoard() {
      ImageIcon boardIcon = new ImageIcon("Sprites\\board.png");
      boardPic = boardIcon.getImage();
      boardPic = boardPic.getScaledInstance(PIX_SIZE, PIX_SIZE, Image.SCALE_DEFAULT);
   }
   
   private void loadSquares() {
      ImageIcon moveIcon = new ImageIcon("Sprites\\moveFrame.png");
      moveFrame = moveIcon.getImage();
      
      ImageIcon takeIcon = new ImageIcon("Sprites\\takeFrame.png");
      takeFrame = takeIcon.getImage();
   }
   
   private void loadShadow() {
      ImageIcon shadowIcon = new ImageIcon("Sprites\\shadow.png");
      shadow = shadowIcon.getImage();
   }
   
   private void loadEndScreens() {
      ImageIcon whiteWIcon = new ImageIcon("Sprites\\whiteWins.png");
      whiteWins = whiteWIcon.getImage();
      
      ImageIcon blackWIcon = new ImageIcon("Sprites\\blackWins.png");
      blackWins = blackWIcon.getImage();
      
      ImageIcon staleIcon = new ImageIcon("Sprites\\stalemate.png");
      stalemate = staleIcon.getImage();
   }
   
   private void loadPieces() {
      whiteKing = new King(4,7,'W');
      blackKing = new King(4,0,'B');
      pieces = new Piece[][] {
         {new Rook(0,0,'B'), new Knight(1,0,'B'), new Bishop(2,0,'B'), new Queen(3,0,'B'), blackKing          , new Bishop(5,0,'B'), new Knight(6,0,'B'), new Rook(7,0,'B')},
         
         {new Pawn(0,1,'B'), new Pawn(1,1,'B')  , new Pawn(2,1,'B')  , new Pawn(3,1,'B')  , new Pawn(4,1,'B')  , new Pawn(5,1,'B')  , new Pawn(6,1,'B')  , new Pawn(7,1,'B')},
         
         {null             , null               , null               , null               , null               , null               , null               , null},
         
         {null             , null               , null               , null               , null               , null               , null               , null},
         
         {null             , null               , null               , null               , null               , null               , null               , null},
         
         {null             , null               , null               , null               , null               , null               , null               , null},
         
         {new Pawn(0,6,'W'), new Pawn(1,6,'W')  , new Pawn(2,6,'W')  , new Pawn(3,6,'W')  , new Pawn(4,6,'W')  , new Pawn(5,6,'W')  , new Pawn(6,6,'W')  , new Pawn(7,6,'W')},
         
         {new Rook(0,7,'W'), new Knight(1,7,'W'), new Bishop(2,7,'W'), new Queen(3,7,'W'), whiteKing          , new Bishop(5,7,'W'), new Knight(6,7,'W'), new Rook(7,7,'W')}
      };
      
      for (int i = 0; i < pieces.length; i++) {
         for (int j = 0; j < pieces[0].length; j++) {
            if (pieces[i][j] != null) {
               if (pieces[i][j].getColor() == 'W') {
                  whitePieces.add(pieces[i][j]);
               } else {
                  blackPieces.add(pieces[i][j]);
               }
            }
         }
      }
   }
   
   
   @Override
   public void paintComponent(Graphics g) {
      g.drawImage(boardPic, 0, 0, this);
      if (selected) {
         drawSquares(g);
      }
      drawSelectionTool(g);
      drawPieces(g);
      
      if(endGame) {
         drawEndGame(g);
      }
   }
   
   public void actionPerformed(ActionEvent e) {
      repaint();
   }
   
   private void drawSquares(Graphics g) {
      for (Point m : moves) {
         if (pieces[m.getY()][m.getX()] == null) {
            g.drawImage(moveFrame, coordToPix(m.getX()), coordToPix(m.getY()), this);
         } else {
            g.drawImage(takeFrame, coordToPix(m.getX()), coordToPix(m.getY()), this);
         }
      }
      
      for (Point m : specialMoves) {
         if (selectedPiece instanceof Pawn) {
            g.drawImage(takeFrame, coordToPix(m.getX()), coordToPix(m.getY()), this);
         } else {
            g.drawImage(moveFrame, coordToPix(m.getX()), coordToPix(m.getY()), this);
         }
      }
   }
   
   private void drawSelectionTool(Graphics g) {
      if (selectTool.cycleBlink()) {
         g.drawImage(selectTool.getImage(), coordToPix(selectTool.getCoord().getX()), coordToPix(selectTool.getCoord().getY()), this);
      }
   }
   
   // Selected put the frame drawings with the new selectedpiece drawings also use array coordinates instead of display coordinates
   private void drawPieces(Graphics g) {
      
      for (Piece p : whitePieces) {
         if (!(selected && p == selectedPiece)) {
            g.drawImage(p.getImage(), coordToPix(p.getCoord().getX()), coordToPix(p.getCoord().getY()), this);
         }
      }
      
      for (Piece p : blackPieces) {
         if (!(selected && p == selectedPiece)) {
            g.drawImage(p.getImage(), coordToPix(p.getCoord().getX()), coordToPix(p.getCoord().getY()), this);
         }
      }
      if (selected) {
         g.drawImage(selectedPiece.getImage(), coordToPix(selectedPiece.getCoord().getX()), coordToPix(selectedPiece.getCoord().getY()) - PIX_SIZE/16, this);
         g.drawImage(shadow, coordToPix(selectedPiece.getCoord().getX()), coordToPix(selectedPiece.getCoord().getY()), this);
      }
   }
   
   private void drawEndGame(Graphics g) {
      if (whiteTurn && isInDanger(whiteKing, pieces)) {
         g.drawImage(blackWins, 0, 0, this);
      } else if (isInDanger(blackKing, pieces)) {
         g.drawImage(whiteWins, 0, 0, this);
      } else {
         g.drawImage(stalemate, 0, 0, this);
      }
   }
   
   private int coordToPix(int coord) {
      return PIX_SIZE / 8 * coord;
   }
   
   // Need to work on how to keep track whether its the objects first move or not
   // Perhaps in the piece ovject we have animatedCoord and realCoord 
   // work on reducing number of if statements
   private class TAdapter extends KeyAdapter {

      @Override
      public void keyPressed(KeyEvent e) {
         if (!endGame) {
            int key = e.getKeyCode();
            Piece pieceUnderSelect = pieces[selectTool.getCoord().getY()][selectTool.getCoord().getX()];
            selectTool.keyPressed(key, rightPiece(pieceUnderSelect));
            
            if (key == KeyEvent.VK_SPACE && rightPiece(pieceUnderSelect)) {
               selected = !selected;
               
               if (selected) {
                  selectedPiece =  pieceUnderSelect;
                  moves = getCleanMoves(selectedPiece);
                  if (selectedPiece instanceof Pawn) {
                     specialMoves = getCleanPassant((Pawn) selectedPiece);
                  } else if (selectedPiece instanceof King) {
                     specialMoves = getCleanCastle((King) selectedPiece);
                  }
                  // get special moves of piece
               } else {
                  if (moves.contains(selectedPiece.getCoord())) {
                     regularMove();
                     changeTurn();
                     passantUpdate();
                  } else if (specialMoves.contains(selectedPiece.getCoord())) {
                     if (selectedPiece instanceof Pawn) {
                        passantMove();
                        changeTurn();
                     } else {
                        castleMove();
                        changeTurn();
                     }
                     passantUpdate();   // add special moves condition
                  } else {
                     selectedPiece.returnToOrigin();
                  }
                  moves = new ArrayList<Point>();
                  specialMoves = new ArrayList<Point>();
               }
            } 
            if (selected) {
               selectedPiece.keyPressed(key);
            }
         }
      }
      
      private void regularMove() {
         Piece pieceReplaced = pieces[selectedPiece.getCoord().getY()][selectedPiece.getCoord().getX()];
         whitePieces.remove(pieceReplaced);
         blackPieces.remove(pieceReplaced);
         pieces[selectedPiece.getOrigin().getY()][selectedPiece.getOrigin().getX()] = null;
         pieces[selectedPiece.getCoord().getY()][selectedPiece.getCoord().getX()] = selectedPiece;
         selectedPiece.updateOrigin(); 
      }
      
      private void passantMove() {
         pieces[selectedPiece.getOrigin().getY()][selectedPiece.getOrigin().getX()] = null;
         Piece pieceReplaced = pieces[selectedPiece.getCoord().getY() + 1][selectedPiece.getCoord().getX()];
         whitePieces.remove(pieceReplaced);
         blackPieces.remove(pieceReplaced);
         pieces[selectedPiece.getCoord().getY() + 1][selectedPiece.getCoord().getX()] = null;
         pieces[selectedPiece.getCoord().getY()][selectedPiece.getCoord().getX()] = selectedPiece; 
         selectedPiece.updateOrigin();
      }
      
      private void castleMove() {
         pieces[selectedPiece.getOrigin().getY()][selectedPiece.getOrigin().getX()] = null;
         if (selectedPiece.getCoord().getX() > 3) {
            Piece rook = pieces[7][7];
            pieces[selectedPiece.getCoord().getY()][selectedPiece.getCoord().getX() - 1] = rook;
            rook.update(selectedPiece.getCoord().getX() - 1, selectedPiece.getCoord().getY());
            rook.updateOrigin();
            pieces[7][7] = null;
         } else {
            Piece rook = pieces[7][0];
            pieces[selectedPiece.getCoord().getY()][selectedPiece.getCoord().getX() + 1] = rook;
            rook.update(selectedPiece.getCoord().getX() + 1, selectedPiece.getCoord().getY());
            rook.updateOrigin();
            pieces[7][0] = null;
         }
         pieces[selectedPiece.getCoord().getY()][selectedPiece.getCoord().getX()] = selectedPiece; 
         selectedPiece.updateOrigin();
      }
      
      private boolean rightPiece(Piece piece) {
         if (piece != null) {
            if (piece.getColor() == 'W' && whiteTurn || piece.getColor() == 'B' && !whiteTurn) {
               return true;
            }
         }
         return false;
      }
      
      private void passantUpdate() {
         if (pawnDoubleJumped != null) {
            pawnDoubleJumped.notVulnerable();
            pawnDoubleJumped = null;
         }
         if (selectedPiece instanceof Pawn) {
            pawnDoubleJumped = (Pawn) selectedPiece;
         }
      }
      
      private void changeTurn() {
         whiteTurn = !whiteTurn;
         pieces = reverseArray(pieces);
         endGame = isMate();
      }
      
      private boolean isMate() {
         Piece king;
         ArrayList<Piece> allies;
         if (whiteTurn) {
            king = whiteKing;
            allies = whitePieces;
         } else {
            king = blackKing;
            allies = blackPieces;
         }
         
         for (Piece p : allies) {
            if (!getCleanMoves(p).isEmpty()) {
               return false;
            }
         }
         System.out.println("GAME OVER BOYS");
         return true;
      }
      
      // getcleanMoves of specialmoves
      
      // Issue with reversing array and then checking if the pawns can move or not
      private ArrayList<Point> getCleanMoves(Piece selectedPiece) {
         ArrayList<Point> m = selectedPiece.getPossibleMoves(pieces);
         Piece king;
         ArrayList<Piece> enemies;
         if (whiteTurn) {
            king = whiteKing;
            enemies = blackPieces;
         } else {
            king = blackKing;
            enemies = whitePieces;
         }
         
         Piece[][] testPieces = new Piece[pieces.length][pieces[0].length];
         for (int j = 0; j < pieces.length; j++) {
            testPieces[j] = Arrays.copyOf(pieces[j], pieces[j].length);
         }
         
         int lastX = selectedPiece.getOrigin().getX();
         int lastY = selectedPiece.getOrigin().getY();
         Piece pieceReplaced = null;
         for (int i = 0; i < m.size();) {
            Point p = m.get(i);
            testPieces[lastY][lastX] = pieceReplaced;
            pieceReplaced = testPieces[p.getY()][p.getX()];
            enemies.remove(pieceReplaced);
            testPieces[p.getY()][p.getX()] = selectedPiece;
            selectedPiece.update(p.getX(), p.getY());
            lastY = p.getY();
            lastX = p.getX();
            if (isInDanger(king, testPieces)) {
               m.remove(p);
            } else {
               i++;
            }
            if (pieceReplaced != null) {
               enemies.add(pieceReplaced);
            }
            selectedPiece.returnToOrigin();
         }
         return m;
      }
      
      private ArrayList<Point> getCleanPassant(Pawn pawn) {
         ArrayList<Point> m = pawn.getPassantMoves(pieces);
         Piece king;
         ArrayList<Piece> enemies;
         if (whiteTurn) {
            king = whiteKing;
            enemies = blackPieces;
         } else {
            king = blackKing;
            enemies = whitePieces;
         }
         
         Piece[][] testPieces = new Piece[pieces.length][pieces[0].length];
         for (int j = 0; j < pieces.length; j++) {
            testPieces[j] = Arrays.copyOf(pieces[j], pieces[j].length);
         }
         
         int lastX = pawn.getOrigin().getX();
         int lastY = pawn.getOrigin().getY();
         for (int i = 0; i < m.size();) {
            Point p = m.get(i);
            testPieces[lastY][lastX] = null;
                        // remove the 
            Piece pieceReplaced = testPieces[p.getY() + 1][p.getX()];
            testPieces[p.getY() + 1][p.getX()] = null;
            enemies.remove(pieceReplaced);
            testPieces[p.getY()][p.getX()] = selectedPiece;
            selectedPiece.update(p.getX(), p.getY());
            if (isInDanger(king, testPieces)) {
               m.remove(p);
            } else {
               i++;
            }
            if (pieceReplaced != null) {
               enemies.add(pieceReplaced);
            }
            selectedPiece.returnToOrigin();
         }
         return m;
      }

      private ArrayList<Point> getCleanCastle(King king) {
         
         ArrayList<Point> m = king.getCastleMoves(pieces);
         
         Piece[][] testPieces = new Piece[pieces.length][pieces[0].length];
         for (int j = 0; j < pieces.length; j++) {
            testPieces[j] = Arrays.copyOf(pieces[j], pieces[j].length);
         }
         
         if (!isInDanger(king, testPieces)) {
            int lastX = king.getOrigin().getX();
            int lastY = king.getOrigin().getY();
            int lastKingY = king.getOrigin().getY();
            int lastKingX = king.getOrigin().getX();
            Piece pieceReplaced = null;
            for (int i = 0; i < m.size();) {
               Point p = m.get(i);
               testPieces[lastY][lastX] = pieceReplaced;
               // remove where king was last
               if (p.difference(king.getOrigin()).equals(new Point(0,-2))) {
                  testPieces[p.getY()][p.getX() + 1] = testPieces[7][0];
                  pieceReplaced = testPieces[7][0];
                  testPieces[7][0] = null;
                  lastY = p.getY();
                  lastX = p.getX() + 1;
               } else {
                  testPieces[p.getY()][p.getX() - 1] = testPieces[7][7];
                  pieceReplaced = testPieces[7][7];
                  testPieces[7][7] = null;
                  lastY = p.getY();
                  lastX = p.getX() - 1;
               }
               testPieces[lastKingY][lastKingX] = null;
               testPieces[p.getY()][p.getX()] = selectedPiece;
               selectedPiece.update(p.getX(), p.getY());
               lastKingX = p.getX();
               lastKingY = p.getY();
               if (isInDanger(king, testPieces)) {
                  m.remove(p);
               } else {
                  i++;
               }
               selectedPiece.returnToOrigin();
            } 
         } else {
            m = new ArrayList<Point>();
         }
         return m;
      }
     
   }
   
   private boolean isInDanger(Piece p, Piece[][] pieces) {
      pieces = reverseArray(pieces);
      ArrayList<Piece> enemies;
      if (whiteTurn) {
         enemies = blackPieces;
      } else {
         enemies = whitePieces;
      }
      for (Piece p2 : enemies) {
         if (p2.getPossibleMoves(pieces).contains(p.getCoord())) {
            pieces = reverseArray(pieces);
            return true;
         }
      }
      pieces = reverseArray(pieces);
      return false;
   }

   
   private Piece[][] reverseArray(Piece[][] original) {
      Piece[][] reversed = new Piece[8][8];
      for (int i = 7; i >= 0; i--) {
         for (int j = 7; j >= 0; j--) {
            Piece p = original[7 - i][7 - j];
            if (p != null) {
               p.flipPoint();
            }
            reversed[i][j] = p;
         }
      }
      return reversed;
   }
}