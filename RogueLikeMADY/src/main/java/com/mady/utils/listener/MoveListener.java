package com.mady.utils.listener;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by djb on 2015/06/03.
 */
public class MoveListener {
    public MoveListener() {
    }

    static long rand = 10000;

    public static void main(String args[]) {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        File f = new File("./temp");
        try {

            Runnable keyPressThread = new MoveListener.KeyPressThread();
            Thread t = new Thread(keyPressThread);
            t.start();

            BufferedReader br = new BufferedReader(new FileReader(f));

            String line;

            while ((line = br.readLine()) != null) {

                try {
                    final String copy = line;

                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                System.out.println(rand);
                                Thread.sleep(rand);
                                System.out.println(copy);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }




    public static class KeyPressThread implements Runnable {

        Scanner inputReader = new Scanner(System.in);

        //Method that gets called when the object is instantiated
        public KeyPressThread() {
        }

        public void run()
        {
            while(true)
            {
                if (inputReader.hasNext())
                {
                    String input = inputReader.next();
                    if (input.equals("["))
                    {
                        rand+=100;
                        System.out.println("Pressed [");
                    }
                    if (input.equals("]"))
                    {
                        rand-=100;
                        System.out.println("Pressed ]");
                    }
                    if (input.equalsIgnoreCase("Q"))
                    {
                        break; // stop KeyPressThread
                    }
                }
            }
        } }  }