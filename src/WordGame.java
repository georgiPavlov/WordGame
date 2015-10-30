import com.sun.xml.internal.fastinfoset.util.CharArrayString;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Georgi on 10/29/2015.
 */
public class WordGame {
    private char[][] mass ;
    private char[] w=null;
    private String word=null;
    private int z=0;
    private int counter=0;
    private int xX,yY;
    private void input(){
        Scanner scanner = new Scanner(System.in);
        Scanner fileScanner=null;

        System.out.println(" 1 Enter the matrix in .txt file" + " On first line enter x and then y with space between them " +
                "\n On every another line enter letters with space between the letters" +
        "\n default file enter - Rado ");
        System.out.println("2 Enter custom directory (use \\ !)");
        String directory = scanner.nextLine();
        System.out.println("3 Enter the word you will find");
        word=scanner.nextLine();
        w = word.toCharArray();
        //for (int i = 0; i <w.length ; i++) {
            //System.out.print(w[w.length-1] + " " );
        //}
        //System.out.println();
        try{
            File file = new File(directory);
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e){
            System.out.print("Wrong file ");
        }
        String c = fileScanner.nextLine();
        String[] arr= c.split("[ ]+");
         xX = Integer.parseInt(arr[0]);
         yY = Integer.parseInt(arr[1]);
        mass = new char[yY][xX];
        String temp=null;
        while (fileScanner.hasNext()){
            for (int y = 0; y < yY; y++) {
                for (int x = 0; x < xX; x++) {
                    temp = fileScanner.next();
                    mass[y][x]= temp.charAt(0);
                    //System.out.print("Y: " + y + " x: " + x + "" +  mass[y][x] + " " + "\n");
                }
            }
        }


        for (int col = 0; col < yY; col++) {
            for (int row = 0; row < xX; row++) {

               // System.out.print("Y: " + col + " x: " + row + "" + mass[col][row] + " " + "\n");
                operation(row, col, w[0],0, -1); //up
                operation(row, col, w[0],0, +1); //down
                operation(row, col, w[0],-1, 0); //left
                operation(row, col, w[0],+1, 0); //right
                operation(row, col, w[0],+1, -1); //upRight
                operation(row, col, w[0],+1, +1); //downRight
                operation(row, col, w[0],-1,-1); //upLeft
                operation(row, col, w[0],-1,+1); //downLeft
               // System.out.println("new loop");


           }
        }

    }





    private void operation(int x,int y,char letter,int xAdd,int yAdd){

        if ((x<0) || (y<0) || (y>=yY) || (x >= xX)) {
            //
            z=0;
            return;
        }

        if(w[w.length-1] == letter){
            counter++;
            //System.out.println(counter );
            z=0;
            return;
        }

        try{if (mass[y][x] == letter){

            System.out.println(mass[y][x] + " " + w[z]);
            z++;
            operation(x + xAdd, y + yAdd, w[z], xAdd, yAdd);

        }}
        catch (ArrayIndexOutOfBoundsException e){
            System.out.print("err");
            return;
        }

        z=0;
        return;

    }



    public int getCounter() {
        return counter ;
    }

    public static void main(String[] args){
        WordGame wordGame= new WordGame();
        wordGame.input();
        System.out.println("Times: " + wordGame.getCounter());
    }
}
