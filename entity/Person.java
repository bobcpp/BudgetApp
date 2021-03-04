package demo.swing.entity;

public class Person {
    private final String firstName;
    private final String lastName;
    private Integer age;
    private Sex sex;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, Integer age, Sex sex){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge(){
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(firstName + " " + lastName);
        if (age != null){
            stringBuilder.append(" " + age);
        }
        if (sex != null){
            stringBuilder.append(" " + sex);
        }
        return stringBuilder.toString();
    }
}
