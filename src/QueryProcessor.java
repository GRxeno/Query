//Papadopoulos Xenofon-Rafail 4141

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class QueryProcessor {

    private Index indexDB;

    public QueryProcessor(Index indexDB){
        this.indexDB = indexDB;
    }

    public void run(){
        System.out.print("***Query: ");
        Scanner scanner = new Scanner(System.in);
        String question = scanner.nextLine();
        //String question = "Data Mining";
        question = question.toLowerCase();
        System.out.println("----");
        String[] parts = question.split(" ");
        ArrayList<Entry> entryList = new ArrayList<>();
        ArrayList<Integer> scoreList = new ArrayList<>();
        HashMap<Entry, Integer> myMap = new HashMap<>();
        int counter = 1;
        int valid = 0;
        for (String word : parts){
            for (Entry entry: indexDB.retrieve(word)){
                if (!myMap.containsKey(entry)) {
                    myMap.put(entry, 1);
                }
                else{
                    myMap.put(entry,myMap.get(entry)+1);
                }
            }
            valid += 1;
        }
        for(Entry entry: myMap.keySet()){
            if (myMap.get(entry) == valid){
                entryList.add(entry);
                scoreList.add(entry.computeScorer(question) + valid);
            }
        }
        for (int r = 0; r < entryList.size(); r++){
            for(int c = 0; c < entryList.size(); c++){
                if (scoreList.get(c) < scoreList.get(r)){
                    Entry temp1 = entryList.get(r);
                    int temp2 = scoreList.get(r);
                    entryList.set(r, entryList.get(c));
                    entryList.set(c, temp1);
                    scoreList.set(r, scoreList.get(c));
                    scoreList.set(c, temp2);
                }
            }
        }
        for (int r = 0; r < entryList.size(); r++){
            System.out.println("Result: " + counter + " (Score: " + scoreList.get(r) + ")");
            (entryList.get(r)).display();
            counter++;
        }
    }
}
