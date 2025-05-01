
public class Professor extends Staff {
    private String specialization;

    public Professor(String name, int age, String address, int staffId, String department, String specialization) {
        super(name, age, address, staffId, department);
        this.specialization = specialization;
    }

    public void conductLecture() {
        System.out.println(name + " is conducting a lecture on " + specialization);
    }
}
