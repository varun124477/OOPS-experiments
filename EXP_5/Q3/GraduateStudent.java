
public class GraduateStudent extends Student {
    private String researchTopic;

    public GraduateStudent(String name, int age, String address, int studentId, String course, String researchTopic) {
        super(name, age, address, studentId, course);
        this.researchTopic = researchTopic;
    }

    public void submitThesis() {
        System.out.println(name + " is submitting a thesis on " + researchTopic);
    }
}
