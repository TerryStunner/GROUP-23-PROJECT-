// Stores basic student details.
public class Student {
    // These fields describe the student
    private String studentName;
    private String studentId;

    // Creates a student with a name and ID.
    public Student(String studentName, String studentId) {
        this.studentName = studentName;
        this.studentId = studentId;
    }

    // getter for  the student's name.
    public String getStudentName() {
        return studentName;
    }

    // setter for the Student Name
    public void setStudentName(String studentName) {
        if (studentName != null && !studentName.trim().isEmpty()) {
            this.studentName = studentName;
        }
    }

    // getter for the student's ID.
    public String getStudentId() {
        return studentId;
    }

    // setter for the student Id
    public void setStudentId(String studentId) {
        if (studentId != null && !studentId.trim().isEmpty()) {
            this.studentId = studentId;
        }
    }

    // Combines the student's name and ID for display.
    public String getStudentDetails() {
        return "Student Name: " + studentName
        + "\nStudent ID: " + studentId;
    }
}
