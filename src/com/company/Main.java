package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        String ip=in.nextLine();
        try {
            Socket server=new Socket(ip,1488);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(true) {
                            String msg = in.nextLine();
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));
                            bw.write(msg);
                            bw.newLine();
                            bw.flush();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(true) {

                            BufferedReader br=new BufferedReader(new InputStreamReader(server.getInputStream()));
                            System.out.println(br.readLine());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
