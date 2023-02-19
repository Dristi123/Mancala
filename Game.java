package com.company;

import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Game {
    int choice;
    int player = 1;
    static int MAX = 1000;
    static int MIN = -1000;
    static int move=-1;
    static Board optimal = null;
    static  int player1=0;
    static int player2=0;
    static int draw=0;
    static int w1=5;
    static int w2=10;
    static int w3=20;
    static int w4=20;
    boolean move_ordering=true;
    int[] arr = new int[14];
    Board board;
    Board startboard;
    Random random;
    Vector<Integer> idx_vec;
    Scanner scanner = new Scanner(System.in);

    public Game(int choice) {
        this.choice = choice;
        for (int i = 0; i < 14; i++) {
            if (i == 6 | i == 13) arr[i] = 0;
            else arr[i] = 4;
        }
        board = new Board(arr);
        startboard=new Board(arr);
        random=new Random();
        idx_vec=new Vector<>();
        for(int i=0;i<6;i++) idx_vec.add(i+1);
    }

    public void Game_play() {
        if (choice == 1) {
            while (true) {
                int pit_number;
                if (player == 1) {
                    board.showarray();
                    System.out.println("Player 1's turn:");
                    System.out.println("Choose the pit(1-6) you want to empty");
                    pit_number = scanner.nextInt();
                    //Board oldboard = board;
                    board = board.generate1stplayer(pit_number, "P1");
                    //System.out.println("Heuristic value "+board.heuristic_1("P1"));
                    if (board.GameEnded("P1")) {
                        //board.showarray();
                        break;
                    }
                    board.hasAcapture();
                    if (!board.hasExtraMove()) {
                        //System.out.println("here");
                        player = 2;
                    }
                    else {
                        System.out.println("You have earned an extra move");
                    }
                }
                if (player == 2) {
                    board.showarray();
                    System.out.println("Player 2's turn:");
                    System.out.println("Choose the pit(1-6) you want to empty");
                    pit_number = scanner.nextInt();
                    //Board oldboard = board;
                    board = board.generate1stplayer(pit_number, "P2");
                    //System.out.println("Heuristic value "+board.heuristic_1("P2"));
                    if (board.GameEnded("P2")) {
                        break;
                    }
                    board.hasAcapture();
                    if (!board.hasExtraMove()) {
                        player = 1;
                    }
                    else {
                        System.out.println("You have earned an extra move");
                    }
                }
            }
        }
        if (choice == 2) {
            while (true) {
                if (player == 1) {
                    assert board != null;
                    board.showarray();
                    System.out.println("Computer's turn:");

                    board = minimax(board, 8, true, MIN, MAX, 1, "P1").getBoard();
                    if (board != null) {
                        if (board.GameEnded("P1")) {
                            //board.showarray();
                            break;
                        }
                        board.hasAcapture();
                        if (!board.hasExtraMove()) {
                            player = 2;

                        } else {
                            System.out.println("You have earned an extra move");
                        }
                    }
                }
                if (player == 2) {
                    assert board != null;
                    board.showarray();
                    System.out.println("Player 2's turn:");
                    System.out.println("Choose the pit(1-6) you want to empty");
                    int pit_number = scanner.nextInt();
                    board = board.generate1stplayer(pit_number, "P2");
                    if (board != null) {
                        if (board.GameEnded("P2")) {
                            break;
                        }
                        board.hasAcapture();
                        if (!board.hasExtraMove()) {
                            player = 1;
                        } else {
                            System.out.println("You have earned an extra move");
                        }
                    }
                }
            }
        }
        else if(choice==3){
                while (true) {
                    if (player == 1) {
                        assert board != null;
                        board.showarray();
                        System.out.println("Computer 1's turn:");
                        //optimal = null;
                        board = minimax(board, 3, true, MIN, MAX, 3, "P1").getBoard();
                        //board = optimal;
                        if (board != null) {
                            if (board.GameEnded("P1")) {
                                //board.showarray();
                                break;
                            }
                            board.hasAcapture();
                            if (!board.hasExtraMove()) {
                                player = 2;

                            } else {
                                System.out.println("You have earned an extra move");
                            }
                        }
                    }
                    if (player == 2) {
                        assert board != null;
                        board.showarray();
                        System.out.println("Computer 2's turn:");

                        board = minimax(board, 10, true, MIN, MAX, 3, "P2").getBoard();

                        if (board != null) {
                            if (board.GameEnded("P2")) {
                                break;
                            }
                            board.hasAcapture();
                            if (!board.hasExtraMove()) {
                                player = 1;
                            } else {
                                System.out.println("You have earned an extra move");
                            }
                        }
                    }
                }
            }
        else {
            for(int i=0;i<100;i++) {
                board=startboard;
                w1=random.nextInt(1000);
                w2=random.nextInt(1000);
                w3=random.nextInt(1000);
                w4=random.nextInt(1000);
                Collections.shuffle(idx_vec);
                while (true) {
                    if (player == 1) {
                        board = minimax(board, 5, true, MIN, MAX, 5, "P1").getBoard();
                        //board = optimal;
                        if (board != null) {
                            if (board.GameEnded("P1")) {
                                break;
                            }
                            if (!board.hasExtraMove()) {
                                player = 2;
                            }
                        }
                    }
                    if (player == 2) {
                        board = minimax(board, 5, true, MIN, MAX, 5, "P2").getBoard();
                        //board = optimal;
                        if (board != null) {
                            if (board.GameEnded("P2")) {
                                break;
                            }
                            //board.hasAcapture();
                            if (!board.hasExtraMove()) {
                                player = 1;
                            }
//                            } else {
//                                System.out.println("You have earned an extra move");
//                            }
                        }
                    }
                }
            }
            System.out.println("Player 1 "+player1);
            System.out.println("Player 2 "+player2);
            System.out.println("Draw "+draw);
            float ratio= ((float)player1)/player2;
            System.out.println("Ratio "+ratio);
        }
        }

    public Node minimax(Board board, int depth, Boolean MaxPlayer, int alpha, int beta, int heuristic, String player) {
        int[] arr2=new int[14];
        for(int i=0;i<14;i++) arr2[i]=0;
        Board newboard=new Board(arr2);
        for(int i=0;i<14;i++) {
            newboard.array[i]=board.array[i];
        }
        if (depth == 0) {
            if (heuristic == 1) return new Node(newboard, newboard.heuristic_1(player));
            else if(heuristic==2) return new Node(newboard, newboard.heuristic_2(player));
            else if(heuristic==3) return new Node(newboard, newboard.heuristic_3(player));
            else if(heuristic==4) return new Node(newboard, newboard.heuristic_4(player));
            else if(heuristic==5) return new Node(newboard, newboard.heuristic_5(player));
            else return new Node(newboard, newboard.heuristic_6(player));
        }
        if (MaxPlayer) {
            Node best = new Node(null, MIN);

            //optimal=null;
            for (int i = 1; i <= 6; i++) {
                int j;
                if(move_ordering) j=idx_vec.get(i-1);
                else j=i;
                if (newboard.getArray()[6 - j]!= 0) {
                    Board childboard = null;
                    childboard=newboard.generate1stplayer(j,player);
                    if (childboard != null) {
                        Node newnode = null;
                        if (!childboard.hasExtraMove())
                            newnode = new Node(childboard, minimax(childboard, depth - 1, false, alpha, beta, heuristic, player).getValue());
                        else
                            newnode = new Node(childboard, minimax(childboard, depth - 1, true, alpha, beta, heuristic, player).getValue());

                        best = maxnode(best, newnode);
                        alpha = Math.max(alpha, best.getValue());
                        if (beta <= alpha)
                            break;
                    }
                }
            }
            return best;
        } else {
            Node best=new Node(null,MAX);
            for (int i = 1; i <= 6; i++) {
//                int j;
//                if(move_ordering) j=7-i;
//                else j=i;
                int j;
                if(move_ordering) j=idx_vec.get(i-1);
                else j=i;
                if (newboard.getArray()[6 + j] != 0) {
                    //board.showarray();
                    Board childboard = null;
                    if (player.equalsIgnoreCase("P1")) childboard = newboard.generate1stplayer(j, "P2");
                    else childboard = newboard.generate1stplayer(j, "P1");
                    if (childboard != null) {
                        Node newnode = null;
                        if (!childboard.hasExtraMove())
                            newnode = new Node(childboard, minimax(childboard, depth - 1, true, alpha, beta, heuristic, player).getValue());
                        else
                            newnode = new Node(childboard, minimax(childboard, depth - 1, false, alpha, beta, heuristic, player).getValue());

                        best = minnode(best, newnode);
                        beta = Math.min(beta, best.getValue());
                        if (beta <= alpha)
                            break;
                    }
                }
            }
//            if(best.getBoard()==null) {
//                best.setBoard(newboard);
//            }
            return best;

        }
    }
    public Node maxnode(Node n1,Node n2) {
        if(n1.getBoard()==null) return n2;
        if(n2.getBoard()==null) return n1;
        if(n1.getValue()>=n2.getValue()) return n1;
        else return n2;
    }
    public Node minnode(Node n1,Node n2) {
        if(n1.getBoard()==null) return n2;
        if(n2.getBoard()==null) return n1;
        if(n1.getValue()<=n2.getValue()) return n1;
        else return n2;
    }
}

