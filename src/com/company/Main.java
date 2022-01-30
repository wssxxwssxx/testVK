package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите строку: ");
        String text = in.nextLine();

        System.out.println("Введите длину смс");
        int lengthSms = in.nextInt();

        System.out.println(getSms(text,lengthSms));
    }

    public static List getSms(String stroke, int lenghtStroke){
        int suffixLength;
        String space = " ";
        String slash = "/";

        AtomicInteger smsNumber = new AtomicInteger(1); //для счета текущего смс

        String[] initialArraySms = stroke.split(space);// разбиаем текст на пробелы
        List listOfResultSms = new ArrayList();// конечный результат

        if(initialArraySms.length > 1) { // если > 1 смс
            for(int i = 0; i < initialArraySms.length;i++){
                initialArraySms[i] = initialArraySms[i] + space + smsNumber.getAndIncrement() + slash + initialArraySms.length; //добаляем суффикс
                suffixLength = initialArraySms[i].split(space).length; // узнает длину суффикса
                if( initialArraySms[i].length() + suffixLength <= lenghtStroke){ // длина смс < lenghtStroke
                    listOfResultSms.add(initialArraySms[i]);//добавяем в список результатов
                }
            }

            smsNumber.set(1);


            for(int i = 0; i < listOfResultSms.size();i++){
                String resultStroke = listOfResultSms.get(i).toString().substring(0,listOfResultSms.get(i).toString().indexOf(space));//меняем суффикс в соотвестие с кол-ом смс
                listOfResultSms.set(i,resultStroke + space + smsNumber.getAndIncrement() + slash + listOfResultSms.size());//изменяем смс
            }

        }

        return listOfResultSms;
    }

}

