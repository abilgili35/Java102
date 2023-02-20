package first;

import java.util.ArrayList;

class NumberList{

    private ArrayList<Integer> numbersList;
    private ArrayList<Integer> oddNumbersList;
    private ArrayList<Integer> evenNumbersList;

    public NumberList(ArrayList<Integer> numbersList){
        this.numbersList = numbersList;
        this.oddNumbersList = new ArrayList<>();
        this.evenNumbersList = new ArrayList<>();
    }

    public  ArrayList<Integer> getOddNumbersList() {
        return oddNumbersList;
    }


    public ArrayList<Integer> getEvenNumbersList() {
        return evenNumbersList;
    }

    private synchronized void addOddNumber(Integer num){
            oddNumbersList.add(num);
    }

    private synchronized void addEvenNumber(Integer num){
            evenNumbersList.add(num);
        
    }

    public void addNumber(Integer num){

        if((num % 2) == 1)
        {
            addOddNumber(num);
        }
        else{
            addEvenNumber(num);
        }
        
    
    }

    public synchronized Integer readNumber(int offset, int index) throws IndexOutOfBoundsException{
            return numbersList.get(offset + index);
    }



}

class RacingThread extends Thread{
    private NumberList numList;
    private int subListStartIndex;
    private int subListEndIndex;
    private int index;
    private boolean running;

    public RacingThread(int subListStartIndex, int subListEndIndex, NumberList numList) {
        this.subListStartIndex = subListStartIndex;
        this.subListEndIndex = subListEndIndex;
        this.numList = numList;
        this.index = 0;
        this.running = true;
    }

    public ArrayList<Integer> getOddNumbersList(){
        return numList.getOddNumbersList();
    }

    public ArrayList<Integer> getEvenNumbersList(){
        return numList.getEvenNumbersList();
    }

    @Override
    public void run() {
        while(running){

            try{
                Thread.sleep(40);
            }catch(Exception e){
                e.printStackTrace();
            }

            Integer num = numList.readNumber(this.subListStartIndex, this.index);
            index++;

            try{
                Thread.sleep(40);
            }catch(Exception e){
                e.printStackTrace();
            }

            numList.addNumber(num);
            

            
            if((subListStartIndex + index) == (subListEndIndex - 1)){
                running = false;
            }
            
        }
        
    }



}

public class RacingThreads {
    public static void main(String[] args) throws InterruptedException{
        int listSize = 10000;
        ArrayList<Integer> numbers = new ArrayList<>();
        
        for(int i=1; i<=listSize; i++){
            numbers.add(i);
        }

        NumberList numberList = new NumberList(numbers);

        RacingThread t1 = new RacingThread((0 * numbers.size() / 4), (1 * numbers.size() / 4) , numberList);
        RacingThread t2 = new RacingThread((1 * numbers.size() / 4), (2 * numbers.size() / 4), numberList);
        RacingThread t3 = new RacingThread((2 * numbers.size() / 4), (3 * numbers.size() / 4), numberList);
        RacingThread t4 = new RacingThread((3 * numbers.size() / 4), (4 * numbers.size() / 4), numberList);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t4.join();
        t1.join();
        t2.join();
        t3.join();
        
        
        System.out.println("Odd numbers:");
        for(Integer i : numberList.getOddNumbersList()){
            System.out.print(i + " ");
        }

        System.out.println();
        System.out.println();

        System.out.println("Even numbers:");
        for(Integer i : numberList.getEvenNumbersList()){
            System.out.print(i + " ");
        }
    

        
    }
}
