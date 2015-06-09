package edu.ucsb.cs.cs185.afarcilla.senioritis;

public class ClassStruct {

    public String className;
    public Float units;
    public String desiredGrade;
    public Float homeworkPercent;
    public Float midTermPercent;
    public Float finalPercent;
    public Float projectsPercent;
    public Float otherPercent;

    public ClassStruct(String className,
                        Float units,
                        String desiredGrade,
                        Float homeworkPercent,
                        Float midTermPercent,
                        Float finalPercent,
                        Float projectsPercent,
                        Float otherPercent){

        this.className = className;
        this.units = units;
        this.desiredGrade = desiredGrade;
        this.homeworkPercent = homeworkPercent;
        this.midTermPercent = midTermPercent;
        this.finalPercent = finalPercent;
        this.projectsPercent = projectsPercent;
        this.otherPercent = otherPercent;
    }
}
