package edu.ucsb.cs.cs185.afarcilla.senioritis;

public class AssignmentStruct {

    public String assignmentTitle;
    public String category;
    public Integer score;

    public AssignmentStruct(String assignmentTitle, String category, int score){

        this.assignmentTitle = assignmentTitle;
        this.category = category;
        this.score = score;
    }
}
