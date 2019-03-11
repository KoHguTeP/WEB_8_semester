package com.main;

import com.DOM.ReaderDOM;
import com.DOM.WriterDOM;

import java.util.Scanner;

public class Main_3  {
    public static void main(String[] args) throws NumberFormatException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите название исходного файла (.xml)");
        String pathToFileIn = sc.nextLine();
        System.out.println("Введите название результирующего файла (.xml)");
        String pathToFileOut = sc.nextLine();

        ReaderDOM readerDOM = new ReaderDOM(pathToFileIn);
        WriterDOM writerDOM = new WriterDOM(pathToFileIn, pathToFileOut);

        writerDOM.writeAverage(readerDOM.checkAverage());

        /*switch (readerDOM.checkAverage()) {
            case -1:
                System.out.println("Оценка записана верно");
                break;
            case -2:
                System.out.println("В файле нет предметов");
            default: {
                writerDOM.writeAverage(readerDOM.checkAverage());
                System.out.println("Оценка переписана");
            }
        }*/
    }
}
