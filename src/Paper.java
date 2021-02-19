//Papadopoulos Xenofon-Rafail 4141

import java.util.ArrayList;

public class Paper extends Entry {

    private String title;
    private String year;
    private ArrayList<Researcher> authorList = new ArrayList<>();
    private Conference conf;

    public Paper(String title, String year) {
        this.title = title;
        this.year = year;
    }

    public void addAuthor(Researcher author) {
        authorList.add(author);
    }

    private String authors() {
        String authors = " ";
        for (Researcher author : authorList) {
            authors += author + ", ";
        }
        return authors;
    }

    public void setConference(Conference conf) {
        this.conf = conf;
    }

    public String toString() {
        return "\"" + title + "\"" + "." + authors() + conf + " " + year;
    }

    public void display() {
        System.out.println("Paper: " + title);
        System.out.println("Authors: " + authors());
        System.out.println("Conference: " + conf + " " + year);
        System.out.println();
    }

   public int computerScore(String question) {
        String title = this.title.toLowerCase();
        if (question.contains(title)){
            this.sum += 50;
            return sum;
        } else if (title.equals(question)) {
            this.sum += 100;
            return sum;
        } else{return sum;}
    }
}
