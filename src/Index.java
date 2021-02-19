//Papadopoulos Xenofon-Rafail 4141

import java.util.*;

public class Index {

    // String : Words , Hashet : Where the word appeared
	private HashMap <String, HashSet<Entry>> index = new HashMap<>();

   // Finds all the words in the database and puts them in the HashSet
	public void indexDB(Database base){
        ArrayList<Entry> entriesList = base.getEntries();

        for (Entry entry: entriesList) {
            for (String token: entry.getTokens()) {
                if (!index.containsKey(token)){
                    index.put(token, new HashSet<>());
                    index.get(token).add(entry);
                }
                else{
                    for(String name: index.keySet()){
                        if(name.equals(token)) {
                            (index.get(name)).add(entry);
                        }
                    }
                }
            }
        }
    }

    public void printIndex(){
        for(String name: index.keySet()){
            System.out.println("Token: " + name);
            int counter = 1;
            for (Entry i:index.get(name)){
                System.out.print(counter+". ");
                i.display();
                counter++;
            }
            System.out.println("----");
        }

    }

    public HashSet<Entry> retrieve(String word){
        HashSet<Entry> ent = new HashSet<>();
        for (String token: index.keySet()){
            if (token.equals(word)){
                ent = index.get(word);
                return ent;
            }
        }
        return ent;
    }
}
