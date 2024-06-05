package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
В качестве задачи предлагаю вам реализовать код для
демонстрации парадокса Монти Холла и наглядно убедиться в верности парадокса
(запустить игру в цикле на 1000 и вывести итоговый счет).
Необходимо:
● Создать свой Java Maven или Gradle проект;
● Самостоятельно реализовать прикладную задачу;
● Сохранить результат в HashMap<шаг теста, результат>
● Вывести на экран статистику по победам и поражениям
 */
public class Main {
    static Random random = new Random();
    static final int COUNT_OF_DOORS = 3;

    public static void main(String[] args) {
        HashMap<Integer, Boolean> changeMap = new HashMap<>();
        HashMap<Integer, Boolean> staticMap = new HashMap<>();

        System.out.println("Игра где игрок меняет решение.");
        for (int i = 1; i <= 1000; i++) {

            System.out.println("Раунд " + i);
            boolean[] game = fillGame();
            showDoors(game);

            int participantSelection = random.nextInt(0, COUNT_OF_DOORS);
            System.out.println("Участник выбрал дверь " + (participantSelection + 1));
            staticMap.put(i, game[participantSelection]);

            int hostSelection;
            while (true) {
                hostSelection = random.nextInt(0, COUNT_OF_DOORS);
                if (hostSelection != participantSelection && !game[hostSelection]) {
                    System.out.println("Ведущий открывает дверь " + (hostSelection + 1));
                    break;
                }
            }

            while (true) {
                int newSelection = random.nextInt(0, COUNT_OF_DOORS);
                if (newSelection != participantSelection && newSelection != hostSelection) {
                    participantSelection = newSelection;
                    System.out.println("Игрок меняет решение и выбирает дверь " + (participantSelection + 1));
                    System.out.println(game[participantSelection] ? "ПОБЕДА" : "ПРОИГРЫШ");
                    break;
                }
            }
            changeMap.put(i, game[participantSelection]);
        }


        int countWin = 0;
        int countLose = 0;
        for (Map.Entry<Integer, Boolean> map : changeMap.entrySet()) {
            if (map.getValue()) countWin++;
            else countLose++;
        }
        System.out.println("Число выигрышей = " + countWin);
        System.out.println("Число проигрышей = " + countLose);

        countWin = 0;
        countLose = 0;

        System.out.println("Если игрок не меняет решения.");
        for (Map.Entry<Integer, Boolean> map : staticMap.entrySet()) {
            if (map.getValue()) {
                countWin++;
            } else countLose++;
        }
        System.out.println("Число выигрышей = " + countWin);
        System.out.println("Число проигрышей = " + countLose);
    }

    private static boolean[] fillGame() {
        boolean[] game = new boolean[COUNT_OF_DOORS];
        game[random.nextInt(0, COUNT_OF_DOORS)] = true;
        return game;
    }

    private static void showDoors(boolean[] game) {
        int count = 1;
        for (boolean g : game) {
            System.out.print("Дверь - " + count++ + " " + g + ". ");
        }
        System.out.println();
    }
}