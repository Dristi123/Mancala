package com.company;

public class Board {
    int[] array=new int[14];
    int last_move;
    int capture;
    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }
    public Board generate1stplayer(int pit_number,String player) {
        int[] array=new int[14];
        int temp=0;
        int temp2=0;
        //int p=-1;
        for(int i=0; i<14; i++)
        {
            array[i]=this.array[i];
        }
        int count;
        int idx;
        if(player.equalsIgnoreCase("P1")) {
            count=array[6-pit_number];
            idx=6-pit_number;
        }
        else {
            count=array[6+pit_number];
            idx=6+pit_number;
        }
        if(array[idx]==0)  return this;
        array[idx]=0;
        idx++;
        int stored=0;
        while(true) {
            if(idx>13) idx=0;
            if((player.equalsIgnoreCase("P1") && idx!=13) || (player.equalsIgnoreCase("P2") && idx!=6)) {
                array[idx]=array[idx]+1;
                stored++;
            }

            idx++;
            if(stored==count) break;
        }
        if((player.equalsIgnoreCase("P1") && idx==7)||(player.equalsIgnoreCase("P2") && idx==14)) {
            //last_move=1;
            temp=1;
            //System.out.println("1 set");
        }
        //last_move=idx-1;
        if(array[idx-1]==1 && idx!=7 && idx!=14) {
            //System.out.println("Good Steal");
            int empty=idx-1;
            if((player.equalsIgnoreCase("P1")) && idx>=1 && idx<=6) {
                array[6]=array[6]+array[12-empty]+array[empty];
                array[12-empty]=0;
                array[empty]=0;
                temp2=1;
            }
            else if((player.equalsIgnoreCase("P2")) && idx>=8 && idx<=13) {
                array[13]=array[13]+array[12-empty]+array[empty];
                array[12-empty]=0;
                array[empty]=0;
                temp2=1;
            }

        }
        Board newboard=new Board(array);
        newboard.last_move=temp;
        newboard.capture=temp2;
        return  newboard;
    }
    public boolean GameEnded(String player) {
        //int flag = 0;
        if (player.equalsIgnoreCase("P1")) {
            for (int i = 0; i <= 5; i++) {
                if (array[i] != 0) {
                    return false;
                }
            }

            showarray();
            int player1_score = array[6];
            int player2_score = 0;
            for (int i = 7; i < 14; i++) {
                player2_score = player2_score + array[i];
            }
            if (player1_score > player2_score) {
                System.out.println("Game Over!Player 1 Wins!!");
                Game.player1=Game.player1+1;
            } else if(player1_score < player2_score){
                System.out.println("Game Over!Player 2 Wins!!");
                Game.player2=Game.player2+1;
            }
            else {
                System.out.println("Draw!");
                Game.draw=Game.draw+1;
            }
            return true;

        }
    else {
        for (int i = 7; i <= 12; i++) {
            if (array[i] != 0) {
                return false;
            }
        }
        showarray();
        int player2_score = array[13];
        int player1_score = 0;
        for (int i = 0; i < 7; i++) {
            player1_score = player1_score + array[i];
        }
        if (player1_score > player2_score) {
            System.out.println("Game Over!Player 1 Wins!!");
            Game.player1=Game.player1+1;
        } else if(player1_score < player2_score){
            System.out.println("Game Over!Player 2 Wins!!");
            Game.player2=Game.player2+1;
        }
        else {

            System.out.println("Draw!");
            Game.draw=Game.draw+1;
        }
        return true;
    }
    }
    public boolean hasExtraMove() {
//        if(player.equalsIgnoreCase("P1")) {
//            if(last_move==6) {
//                System.out.println("You have earned an Extra Move");
//                return true;
//            }
//        }
//        else {
//            //System.out.println("here");
//            //System.out.println(last_move);
//            if(last_move==13)  {
//                System.out.println("You have earned an Extra Move");
//                return true;
//            }
//        }
//        return false;
        //System.out.println(last_move);
        if(this.last_move==1) {
            return true;
        }
        return false;
    }
    public  boolean hasAcapture() {
        if(this.capture==1) {
            System.out.println("Good Steal");
            return true;
        }
        return false;
    }

    public Board(int[] array) {
        this.array = array;
    }
    public  void showarray() {
        System.out.println("        Player 1");
        System.out.print("      ");
        for(int i=5;i>=0;i--) {
            System.out.print(" "+array[i]);
        }
        System.out.println();
        System.out.println(array[6]+"                         "+array[13]);
        System.out.print("      ");
        for(int i=7;i<=12;i++) {
            System.out.print(" "+array[i]);
        }
        System.out.println();
        System.out.println("        Player 2");
    }
    public int heuristic_1(String player) {
        int val;
        //System.out.println(player);
        if(player.equalsIgnoreCase("P1")) {
            //System.out.println("here");
            val=array[6]-array[13];
        }
        else val=array[13]-array[6];
        //System.out.println(val);
        return val;
    }
    public int getstone(String player) {
        int sum=0;
        if(player.equalsIgnoreCase("P1")) {
            for(int i=0;i<6;i++) {
                sum=sum+array[i];
            }
        }
        else {
            for(int i=7;i<13;i++) {
                sum=sum+array[i];
            }
        }
        return sum;
    }
    public int heuristic_2(String player) {
        int val;
        if(player.equalsIgnoreCase("P1")) {
            val=5*heuristic_1("P1")+10*(getstone("P1")-getstone("P2"));

        }
        else val=(Game.w1*heuristic_1("P2"))+(Game.w2*(getstone("P2")-getstone("P1")));
        return val;
    }
    public int anyfreemove(String player) {
        int total=0;
        if(player.equalsIgnoreCase("P1")) {
            for(int i=1;i<=6;i++) {
                Board newboard=this.generate1stplayer(i,"P1");
                total=total+newboard.last_move;
            }
        }
        else {
            for(int i=1;i<=6;i++) {
                Board newboard=this.generate1stplayer(i,"P2");
                total=total+newboard.last_move;
            }
        }
        return total;
    }
    public int heuristic_3(String player) {
        //int val;
        return heuristic_2(player)+((Game.w3)*anyfreemove(player));

    }
    public int anycapture(String player) {
        int total=0;
        if(player.equalsIgnoreCase("P1")) {
            for(int i=1;i<=6;i++) {
                Board newboard=this.generate1stplayer(i,"P1");
                total=total+newboard.capture;
            }
        }
        else {
            for(int i=1;i<=6;i++) {
                Board newboard=this.generate1stplayer(i,"P2");
                total=total+newboard.capture;
            }
        }
        return total;
    }
    public int heuristic_4(String player) {
        return heuristic_2(player)+(Game.w4)*anycapture(player);
    }
    public int heuristic_5(String player) {
        int total=0;
        if(player.equalsIgnoreCase("P1")) {
            for(int i=0;i<6;i++) {
                int count=array[i];
                int idx=i;
                for(int j=0;j<count;j++) {
                    idx++;
                    if(idx<7) total=total+1;
                }
            }
        }
        return total;
    }
    public int heuristic_6(String player) {
        int val;
        if(player.equalsIgnoreCase("P1"))  {
            val=(array[6]/24)*100;
        }
        else val=(array[13]/24)*100;
        return val;
    }
}
