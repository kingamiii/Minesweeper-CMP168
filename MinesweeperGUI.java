
	  import javax.swing.; import java.awt.; import java.awt.event.ActionEvent;
	  import java.awt.event.ActionListener;
	  
	  public class MinesweeperGUI extends JFrame {
	  
	  private JButton[][] buttons; private Grid grid;
	  
	  public MinesweeperGUI(Grid grid) { this.grid = grid; int numRows =
	  grid.getNumRows(); int numColumns = grid.getNumColumns();
	  
	  setTitle("Minesweeper"); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  setLayout(new GridLayout(numRows, numColumns));
	  
	  buttons = new JButton[numRows][numColumns];
	  
	  // Create buttons and add ActionListener to each for (int i = 0; i < numRows;
	  i++) { for (int j = 0; j < numColumns; j++) { JButton button = new JButton();
	  button.setPreferredSize(new Dimension(50, 50)); // Adjust button size
	  button.addActionListener(new ButtonClickListener(i, j)); add(button);
	  buttons[i][j] = button; } }
	  
	  pack(); setLocationRelativeTo(null); // Center the frame setVisible(true); }
	  
	  // ActionListener for each button private class ButtonClickListener
	  implements ActionListener { private int row, col;
	  
	  public ButtonClickListener(int row, int col) { this.row = row; this.col =
	  col; }
	  
	  @Override public void actionPerformed(ActionEvent e) { JButton button =
	  (JButton) e.getSource();
	  
	  if (grid.isBombAtLocation(row, col)) { // Game over, reveal all cells
	  gameOver(false); } else { // Check if this is the last cell without a bomb to
	  be revealed if (grid.getCountAtLocation(row, col) == 0) { // Game won
	  gameOver(true); } else { // Reveal cell content int count =
	  grid.getCountAtLocation(row, col); button.setText(Integer.toString(count));
	  button.setEnabled(false); // Disable button after revealing content } } } }
	  
	  // Show game over message private void gameOver(boolean won) { String message
	  = won ? "Congratulations! You won!" : "Game over! You lost!"; int option =
	  JOptionPane.showConfirmDialog(this, message + " Play again?", "Game Over",
	  JOptionPane.YES_NO_OPTION);
	  
	  if (option == JOptionPane.YES_OPTION) { // Restart the game dispose(); //
	  Close current window new MinesweeperGUI(new Grid()); // Start new game } else
	  { // Exit the application System.exit(0); } }
	  
	  public static void main(String[] args) { SwingUtilities.invokeLater(() -> new
	  MinesweeperGUI(new Grid())); } }
}
