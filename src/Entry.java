//Papadopoulos Xenofon-Rafail 4141

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public abstract class Entry {

	// Holds how many times a word has appeared
	HashMap<String, Integer> tokenMap = new HashMap<>();
    int sum = 0;

    public abstract void display();

    
    public void addText(String text){
        text = text.toLowerCase();
        String[] parts = text.split(" "); //Split text
        // If not in hashmap put it ELSE increment by one the times it appeared
        for (String name: parts) {
            if(!tokenMap.containsKey(name)){
                tokenMap.put(name, 1);
            }
            else{
                tokenMap.replace(name, tokenMap.get(name)+1);
            }
        }
    }

    // return all the words and the time they appeared
    public HashSet<String> getTokens() {
        HashSet<String> mySet = new HashSet<>();
        Iterator<Map.Entry<String,Integer>> it = tokenMap.entrySet().iterator();
        while (it.hasNext()){
            mySet.add(it.next().getKey());
        }
        return mySet;
    }

    public int computeScorer(String question){
        String[] parts = question.split(" ");
            for (String token: tokenMap.keySet()){
                for(String word: parts ){
                if (token.equals(word)){
                     this.sum += tokenMap.get(token);
                     return sum;
                }
            }
        }
        return sum;
    }
}

