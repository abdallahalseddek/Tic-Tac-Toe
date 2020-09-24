package alseddiq;

import java.util.*;

public class GameBoard {

    private final char [] [] Board = {{' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-', }, {' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-', }, {' ', '|', ' ', '|', ' '}};
    private ArrayList <Integer> player_positions = new ArrayList<>();
    private ArrayList <Integer>  cpu_positions = new ArrayList<>();


    private void printBoard(){
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                System.out.print(Board[i][j]);
            }
            System.out.println();
        }
    }

    private void players(String player , int playerIndex){
        char symbol ;
        if (player.equals("player")){
            symbol = 'O';
            player_positions.add(playerIndex);
        } else {
            symbol = 'X';
            cpu_positions.add(playerIndex);
        }
        switch (playerIndex) {
            case 1 -> Board[0][0] = symbol;
            case 2 -> Board[0][2] = symbol;
            case 3 -> Board[0][4] = symbol;
            case 4 -> Board[2][0] = symbol;
            case 5 -> Board[2][2] = symbol;
            case 6 -> Board[2][4] = symbol;
            case 7 -> Board[4][0] = symbol;
            case 8 -> Board[4][2] = symbol;
            case 9 -> Board[4][4] = symbol;
        }
    }

    private String winner (){
        List r1 = Arrays.asList(1, 2, 3);
        List r2 = Arrays.asList(4, 5, 6);
        List r3 = Arrays.asList(7, 8, 9);
        List c1 = Arrays.asList(1, 4, 7);
        List c2 = Arrays.asList(2, 5, 8);
        List c3 = Arrays.asList(3, 6, 9);
    List cross1 = Arrays.asList(1, 5, 9);
    List cross2 = Arrays.asList(3, 5, 7);
        List <List> win = new ArrayList<>();
        win.add(r1);
        win.add(r2);
        win.add(r3);
        win.add(c1);
        win.add(c2);
        win.add(c3);
        win.add(cross1);
        win.add(cross2);

        for (List l : win){
            if (player_positions.containsAll(l))
                return "Congratulations, you won";
            else if (cpu_positions.containsAll(l))
                return "Cpu won, sorry";
            else if (player_positions.size() + cpu_positions.size() ==9)
                return "Game ended!";
        }
        return " ";
    }

    public void GameFlow(){
        printBoard();
        while (true) {
            Scanner input = new Scanner(System.in);
            Random random = new Random();
            int index = input.nextInt();
            while (player_positions.contains(index) ||cpu_positions.contains(player_positions))
            {
                System.out.println("position token, enter another");
                 index = input.nextInt();
            }
            players("player", index);
            printBoard();
            String res = winner();
            if (res.length()>0)
            {
                System.out.println(res);
                break;
            }
            int ran = random.nextInt(9) + 1;
            while (cpu_positions.contains(ran) || player_positions.contains(cpu_positions))
            {ran = random.nextInt(9) + 1;}

            players("cpu", ran);
            printBoard();
            res = winner();
            if (res.length()>0)
            {
                System.out.println(res);
                break;
            }
        }

    }
}
