// Represents a study resource such as notes, a video or a reference website.
public class Resources extends LearningContent {
    public Resources(String title, String subject, String resourceType,
            String description, String link) {
        super(title, subject, resourceType, "Self-paced", description, link);
    }

    public Resources(String title, String subject, String link) {
        super(title, subject, link);
    }

    @Override
    public String getContentType() { 
        return "Resource";
     }
}
