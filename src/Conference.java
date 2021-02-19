//Papadopoulos Xenofon-Rafail 4141

import java.util.ArrayList;

public class Conference extends Entry {

    private String name;
    private ArrayList<Paper> paperList = new ArrayList<>();

    public Conference(String name){
        this.name = name;
    }

    public void addPaper(Paper paper){
        paperList.add(paper);
    }

    private String papers(){
        String papers = "";
        for (Paper paper: paperList) {
            papers += "  "+paper +"\n";
        }
        return papers;
    }

    public String toString(){
        return name;
    }

    public void display(){
        System.out.println("Conference: " + name);
        System.out.println("Papers: \n" + papers());
    }

    public int computerScore(String question){
        if (question.equals(this.name.toLowerCase())){
            this.sum += 100;
            return sum;
        }
        else {return sum;}
    }
}
