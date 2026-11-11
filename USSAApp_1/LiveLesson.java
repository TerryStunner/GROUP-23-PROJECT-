// Represents a lesson students can attend online at a set time.
public class LiveLesson extends LearningContent {
    public LiveLesson(String title, String subject, String presenter,
            String schedule, String description, String link) {
        super(title, subject, presenter, schedule, description, link);
    }

    @Override
    public String getContentType() { 
        return "Live Lesson"; 
    }
}
