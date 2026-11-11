// Stores the information shared by lessons and learning resources.
public abstract class LearningContent {
    private String module;
    private String courseUnit;
    private String link;
    private String presenter;
    private String schedule;
    private String description;

    // three argument constructor used in the LiveLesson, RecordedLesson, Resources classes
    public LearningContent(String module, String courseUnit, String link) {
        //calling the super constructor with all six parameters
        this(module, courseUnit, "Not specified", "Not scheduled", "", link);
    }

    // Saves the details displayed for a lesson or resource.
    public LearningContent(String module, String courseUnit, String presenter,
            String schedule, String description, String link) {
        this.module = module;
        this.courseUnit = courseUnit;
        this.presenter = presenter;
        this.schedule = schedule;
        this.description = description;
        this.link = link;
    }

    //getters for the attributes since they're private
    public String getModule() { 
        return module; 
    }
    public String getCourseUnit() { 
        return courseUnit;
     }
    public String getLink() { 
        return link; 
    }
    public String getPresenter() {
         return presenter; 
        }
    public String getSchedule() { 
        return schedule; 
    }

    // Builds a readable description for the viewDetails button.
    public String getDescription() {
        return "Title: " + module + "\nSubject: " + courseUnit
                + "\nPresenter: " + presenter + "\nSchedule/Duration: " + schedule
                + "\nType: " + getContentType() + "\n\n" + description
                + (link.isEmpty() ? "" : "\n\nLink: " + link);
    }

    public abstract String getContentType();
}
