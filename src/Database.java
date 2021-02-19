//Papadopoulos Xenofon-Rafail 4141

import java.io.FileInputStream;
import java.util.*;

public class Database {

    private ArrayList<Entry> entriesList = new ArrayList<>();
    private HashMap<String, Entry> myHashMap = new HashMap<>();


    public void createDB(String fileName){
        Scanner in = null;
        try{
            in = new Scanner(new FileInputStream(fileName));
        }
        catch(Exception e){
            System.out.println("Could not find file!");
        }

        while (in.hasNext()){
            String line1 = in.nextLine(); //title
            String line2 = in.nextLine(); //authors
            String line3 = in.nextLine(); //conference
            String line4 = in.nextLine(); //year
            String[] parts = line2.split(","); //Split Authors

            Paper paper = new Paper(line1,line4);
            Conference conf = new Conference(line3);
            paper.setConference(conf);
            entriesList.add(paper);

            paper.addText(line1);
            paper.addText(line3);
            paper.addText(line4);

            if (!myHashMap.containsKey(line3)) {
                conf.addText(line3);

                conf.addPaper(paper);
                entriesList.add(conf);
                myHashMap.put(line3, conf);
            }
            else {
                for (int c = 0; c < entriesList.size(); c++) {
                    if (entriesList.get(c).getClass().equals(conf.getClass())) {
                        if ((entriesList.get(c)).toString().equals(conf.toString())) {
                            ((Conference) entriesList.get(c)).addPaper(paper);

                            entriesList.get(c).addText(line3);
                        }
                    }
                }
            }

            for (String name: parts) {
                Researcher author = new Researcher(name.trim());
                paper.addAuthor(author);
                author.addPaper(paper);

                paper.addText(name.trim());

                if (!myHashMap.containsKey(name.trim())){
                    author.addText(name.trim());
                    author.addText(line1);
                    author.addText(line3.trim());

                    entriesList.add(author);
                    myHashMap.put(name.trim(), author);
                }
                else{
                    for (int c = 0; c < entriesList.size(); c++){
                        if(entriesList.get(c).getClass().equals(author.getClass())) {
                            if ((entriesList.get(c)).toString().equals(author.toString())) {
                                ((Researcher) entriesList.get(c)).addPaper(paper);

                                entriesList.get(c).addText(name.trim());
                                entriesList.get(c).addText(line1);
                                entriesList.get(c).addText(line3);
                            }
                        }
                    }
                }
            }
        }
    }

    public void printDB(){
        for (Entry entry: entriesList) {
            System.out.println("----");
            entry.display();
        }
    }

    public ArrayList<Entry> getEntries(){
        return entriesList;
    }

    public static void main(String[] args){
        Database test = new Database();
        test.createDB("DB.txt");
        test.printDB();

        Index db1 = new Index();
        db1.indexDB(test);
        db1.printIndex();
    }
}
