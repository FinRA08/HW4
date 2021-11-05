package com.example.demo;

import java.util.Random;

public class Game {

    public static class BullCowCont{
        private final int bulls;
        private final int cows;

        public BullCowCont(int bulls, int cows) {
            this.bulls = bulls;
            this.cows = cows;
        }

        public int getBulls() {
            return bulls;
        }

        public int getCows() {
            return cows;
        }
    }

    private final  int[] guessNum;//переменная в которой будет хранится массив с числом загаданным компьютером

    public Game() {
        guessNum = generateNumber();// определяем число с помощью метода с массивом
    }

    public String getGuessNumber(){
        return "" + guessNum[0] + guessNum[1] + guessNum[2] + guessNum[3];//"" в начале для того что бы наши значения не сложились
    }

    private int[] generateNumber(){
        final Random random = new Random();
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < 100; i++){// в этом цикле перемешиваем массив
            int i1 = random.nextInt(10);
            int i2 = random.nextInt(10);
            int c = a[i1];
            a[i1] = a[i2];
            a[i2] = c;
        }
        return new int[]{a[0], a[1], a[2], a[3]};//возврат четырех элементов
    }

    public BullCowCont calcBullsAndCows(String playerNum){
        int bulls = 0, cows = 0;
        for (int i = 0; i < 4; i++){
            if (guessNum[i] == playerNum.charAt(i) - '0'){
                bulls++;
            }else if (playerNum.contains(String.valueOf(guessNum[i]))){
                cows++;
            }
        }
        return new BullCowCont(bulls, cows);
    }
}
