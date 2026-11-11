// Represents a lesson recording students can watch later.
public class RecordedLesson extends LearningContent {
    public RecordedLesson(String title, String subject, String presenter,
            String duration, String description, String link) {
        super(title, subject, presenter, duration, description, link);
    }

    @Override
    public String getContentType() { 
        return "Recorded Lesson";
     }
}
