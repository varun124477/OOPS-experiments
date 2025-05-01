// demonstrate polymorphism
public class University {
    public static void main(String[] args) {
        Person[] people = {
            new Professor("Dr. Smith", 45, "New York", 201, "Physics", "Quantum Mechanics"),
            new GraduateStudent("Alice", 24, "Boston", 501, "Computer Science", "AI Ethics")
        };

        for (Person p : people) {
            p.displayInfo();
            if (p instanceof Professor) {
                ((Professor) p).conductLecture();
            } else if (p instanceof GraduateStudent) {
                ((GraduateStudent) p).submitThesis();
            }
        }
    }
}
