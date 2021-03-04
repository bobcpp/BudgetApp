package demo.swing.entity;

public enum Sex {
    Male("Male"), Female("Female");
    private String sex;
    Sex(String sex){
        this.sex = sex;
    }
    public String getSex(){
        return sex;
    }
}
