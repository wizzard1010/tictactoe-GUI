
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class tictactoe {
    int boardWidth = 500;
    int boardHeight = 550;

    JFrame frame = new JFrame("Tic Tac Toe Game");
    JLabel label = new JLabel();
    JPanel panel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "0";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;

    public tictactoe() {
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        label.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setText("Tic Tac Toe");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        
        panel.setLayout(new BorderLayout());
        panel.add(label);
        frame.add(panel,BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.DARK_GRAY);
        frame.add(boardPanel);

        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                JButton button = new JButton();
                board[r][c] = button;
                boardPanel.add(button);

                button.setBackground(Color.DARK_GRAY);
                button.setForeground(Color.BLACK);
                button.setFont(new Font("Arial", Font.BOLD, 50));
                button.setFocusable(false);

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        JButton button = (JButton)e.getSource();
                        if(gameOver) return;
                        if(button.getText() == ""){
                            button.setText(currentPlayer);
                            turns ++;
                            checkWinner();
                            if(!gameOver){
                                currentPlayer = currentPlayer == playerX? playerO:playerX;
                                label.setText(currentPlayer + "'s turn");
                            }
                        }
                    }
                    void checkWinner(){
                        //horizontal
                        for(int r=0;r<3;r++){
                            if(board[r][0].getText() == ""){
                                continue;
                            }else{
                                if(board[r][0].getText()==board[r][1].getText() &&
                            board[r][1].getText()==board[r][2].getText()){
                                for(int i=0; i < 3; i ++){
                                    setWinner(board[r][i]);
                                }
                                gameOver = true;
                                return;
                            }
                            }  
                        }
                        //vertically
                        for(int c=0;c<3;c++){
                            if(board[0][c].getText() == ""){
                                continue;
                            }else{
                                if(board[0][c].getText() == board[1][c].getText() &&
                                board[1][c].getText() == board[2][c].getText()){
                                    for(int i =0; i<3;i++){
                                        setWinner(board[i][c]);
                                    }
                                    gameOver=true;
                                    return;
                                }
                            }
                        }
                        //cross1
                        if(board[0][0].getText() == board[1][1].getText() &&
                        board[1][1].getText() == board[2][2].getText()){
                            for (int i=0;i<3;i++){
                                setWinner(board[i][i]);
                            }
                            gameOver=true;
                            return;
                        }

                        //cross2
                        if(board[0][2].getText() == board[1][1].getText() &&
                        board[1][1].getText() == board[2][0].getText() &&
                        board[0][2].getText() != ""){
                            setWinner(board[0][2]);
                            setWinner(board[1][1]);
                            setWinner(board[2][0]);
                            gameOver = true;
                            return;
                        }

                        if(turns >= 9){
                            for(int r=0;r<3;r++){
                                for(int c=0;c<3;c++){
                                    setTie(board[r][c]);
                                }
                            }
                            gameOver=true;
                        }

                    }
                    void setWinner(JButton button){
                        button.setForeground(Color.GREEN);
                        button.setBackground(Color.GRAY);
                        label.setText(currentPlayer + " is the winner");
                    }

                    void setTie(JButton button){
                        button.setForeground(Color.RED);
                        button.setBackground(Color.GRAY);
                        label.setText("TIE");
                    }
                });
            }
        }
    }
    
}
