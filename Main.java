package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        int [] temp={2,1,4,9,8,6,9,4,0,0,0,1,1,3};
//        Board tempboard=new Board(temp);
//        System.out.println(tempboard.heuristic_3("P1"));
        System.out.println("Please choose game mode");
        System.out.println("1.Human player vs Human player");
        System.out.println("2.Human player vs Computer");
        System.out.println("3.Computer vs Computer");
        System.out.println("4.Computer vs Computer Autoplay");
        Scanner sc=new Scanner(System.in);
        int choice=sc.nextInt();
        Game game=new Game(choice);
        game.Game_play();


    }
}
