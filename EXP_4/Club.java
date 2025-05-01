public class Club {
    static String clubName = "Tech Innovators";
    String memberName;

    public Club(String memberName) {
        this.memberName = memberName;
    }

    // Static method to display the club name
    public static void displayClubName() {
        System.out.println("Club Name: " + clubName);
    }

    // Non-static method to display member details
    public void displayMemberInfo() {
        System.out.println("Member Name: " + memberName + ", Club Name: " + clubName);
    }

    public static void main(String[] args) {
        Club.displayClubName();

        Club member1 = new Club("Blue");
        Club member2 = new Club("Delta");
        Club member3 = new Club("Charlie");

        member1.displayMemberInfo();
        member2.displayMemberInfo();
        member3.displayMemberInfo();
    }
}

