/*GROUP MEMBERS
NAME------------------   ||REGISTRATION NUMBER ||STUDENT NUMBER
1.OCEN ANDREW DESIRE.    || 26/U/32117         || 2600732117
2/MUWANGUZI TERRY VIJAYA || 26/U/0308          || 2600700308
3.NANSUBUGA CAROL        || 26/U/03615/PS      || 2600703615
4.NALUKENGE MARIA        || 26/U/03601/PSA     || 2600703601
5.MUHWEZI ANDREW         || 26/U/03570/PSA     || 2600703570
 */

import java.awt.*;
import java.net.URI;
import javax.swing.*;

// Builds the USSA student portal.
public class USSAApp extends JFrame {

    // Main panel containing the portal tabs.
    private final JPanel portalPanel = new JPanel(new BorderLayout());
    private final JTextField nameField = new JTextField(20);
    private String userName;

   //list of lessons and study materials
    private final LearningContent[] contents = {
        //information about live lessons
        new LiveLesson("Structured & Object-Oriented Programming", "CSC 1102", "Dr.Lillian Muyama", "Monday 10AM", "Practise planning a small program and separating its work into classes and methods.", "https://meet.google.com/"),
        new LiveLesson("Computer Organization & Architecture", "CSC 1104", "Dr.Nasser Kimbugwe", "Wednesday 8 AM", "Explore how data is represented and how the main parts of a computer work together.", "https://meet.google.com/"),
        new LiveLesson("Mathematics for Computer Science", "CSC 1105", "Dr.Dennish Azamuke", "Tuesday 10AM", "Work through introductory logic, sets, counting, and other mathematics used in computing.", "https://meet.google.com/"),
        new LiveLesson("Digital Innovation & Computational Thinking", "CSC 1109", "Dr.Micheal Kizito", "Tuesday 8PM", "Practise breaking a problem into smaller steps before writing a solution.", "https://meet.google.com/"),
        new LiveLesson("Communication Skills", "CSK 1101", "Dr.Ruth", "Friday 12PM", "Practise clear written and spoken communication for academic and technical work.", "https://meet.google.com/"),

        // information about recorded lessons
        new RecordedLesson("Structured & Object-Oriented Programming", "CSC 1102", "Dr.Lillian Muyama", "2 hours", "Search for beginner lessons about structured programming, classes, and objects.", "https://www.youtube.com/watch?v=TmIaFjrqOvk&feature=youtu.be"),
        new RecordedLesson("Computer Organization & Architecture", "CSC 1104", "Dr.Nasser Kimbugwe", "2 hours", "Search for introductory explanations of computer hardware and how a computer works.", "https://www.youtube.com/watch?v=MzoqkBmvSPg&feature=youtu.be"),
        new RecordedLesson("Mathematics for Computer Science", "CSC 1105", "Dr.Dennish Azamuke", "1 hour", "Search for introductory discrete mathematics lessons for computer science students.", "https://youtu.be/z8HKWUWS-lA?si=GwAK-_qZf1Aro-wH"),
        new RecordedLesson("Digital Innovation & Computational Thinking", "CSC 1109", "Dr.Micheal Kizito", "2 hours", "Search for beginner lessons on computational thinking and problem decomposition.", "https://www.youtube.com/watch?v=_uEzSiUWmrw&pp=ygUjZGlnaXRhbCBpbm5vdmF0aW9uIGNvbXB1dGVyIHNjaWVuY2U%3D"),
        new RecordedLesson("Communication Skills", "CSK 1101", "Dr.Ruth", "2 hours", "Search for lessons on academic writing, presentations, and technical communication.", "https://www.youtube.com/watch?v=zA01_mwV5CU&pp=ygUcY29tbXVuaWNhdGlvbiBza2lsbHMgbGVjdHVyZQ%3D%3D"),

        // information about available resources
        new Resources("Technical Writing and Communication", "CSK 1101", "Writing guide", "Practise clear academic and technical writing.", "https://owl.purdue.edu/owl/subject_specific_writing/professional_technical_writing/index.html"),
        new Resources("Java Learning Materials", "CSC 1102", "Programming tutorials", "A Java introduction.", "https://dev.java/learn/"),
        new Resources("Build a Computer from First Principles", "CSC 1104", "Interactive course", "Free lectures and projects about computer hardware and the software layers built on it.", "https://www.nand2tetris.org/"),
        new Resources("Mathematics for Computer Science", "CSC 1105", "MIT OpenCourseWare", "Lectures, exercises, and exams covering discrete mathematics for computer science.", "https://ocw.mit.edu/courses/6-042j-mathematics-for-computer-science-fall-2010/"),
        new Resources("Computer Science Unplugged", "CSC 1109", "Activities", "Try activities that explain computer science ideas through problem-solving.", "https://csunplugged.org/en/"),
        new Resources("Computer Literacy Basics", "CSC 1100 (Audited)", "Beginner video search", "Search for basic lessons on using computers, files, and common applications.", "https://www.youtube.com/results?search_query=computer+literacy+basics+beginner")
    };

    // Simple arrays to store payment references and discussion posts
    private final String[] paymentRecords = new String[100];
    private int paymentCount = 0;

    private final String[] discussions = new String[100];
    private int discussionCount = 0;

    //preparing the discussion screen
    private final JTextArea discussionArea = new JTextArea();
    private final JTextField messageField = new JTextField();

    // Sets up the window and asks for the visitor's name.
    public USSAApp() {
        //attributes of the main window
        setTitle("USSA Student Portal");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(createNameEntryPanel());
    }

    // Creates the first screen where the visitor enters their name.
    private JPanel createNameEntryPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel form = new JPanel(new GridLayout(0, 1, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(25, 50, 25, 50));

        JLabel heading = new JLabel(
            "Welcome to USSA Student Portal",
            SwingConstants.CENTER
        );
        heading.setFont(new Font("Arial", Font.BOLD, 22));

        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(event -> enterPortal());
        nameField.addActionListener(event -> enterPortal());

        form.add(heading);
        form.add(new JLabel("What is your name?"));
        form.add(nameField);
        form.add(continueButton);
        panel.add(form);
        return panel;
    }

    private void enterPortal() {
        String enteredName = nameField.getText().trim();
        if (enteredName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your name.");
            return;
        }

        userName = enteredName;
        showPortal();
    }

    // Builds the portal tabs.
    private void showPortal() {
        portalPanel.removeAll();

        //for creating the different tabs
        JTabbedPane tabs = new JTabbedPane();
        //the Home tab which shows the home panel
        tabs.addTab("Home", createHomePanel());
        //the lessons tab 
        tabs.addTab("Lessons", createContentPanel("Live Lesson"));
        //the recordings tab 
        tabs.addTab("Recordings", createContentPanel("Recorded Lesson"));
        //the recources tab
        tabs.addTab("Resources", createContentPanel("Resource"));
        //the payments tab
        tabs.addTab("Payments", createPaymentPanel());
        //thr discussions tab
        tabs.addTab("Discussions", createDiscussionPanel());
        //the Help tab
        tabs.addTab("About & Help", createAboutPanel());

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        header.add(new JLabel("Welcome, " + userName),
                BorderLayout.WEST);

        portalPanel.add(header, BorderLayout.NORTH);
        portalPanel.add(tabs, BorderLayout.CENTER);
        setContentPane(portalPanel);
        portalPanel.revalidate();
        portalPanel.repaint();
    }

    // Shows an introduction to the portal.
    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        //setting the Heading for the home panel
        JLabel heading = new JLabel(
            "THE UNIVERSE SCIENCE SCHOLARS ACADEMY",
            SwingConstants.CENTER
        );
        heading.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel motto = new JLabel(
            "Let's explore the universe together.",
            SwingConstants.CENTER
        );

        JTextArea welcome = new JTextArea(
            "Welcome, " + userName + "!\n\n"
            + "Your academic resource platform for centralized learning\n"
            + "Use the tabs above to access:\n"
            + "- Live lessons\n"
            + "- Recorded lessons\n"
            + "- Learning resources\n"
            + "- Payment records\n"
            + "- Student discussions\n"
            + "- Academy contacts and guidelines"
        );

        //text area can't be edited
        welcome.setEditable(false);
        welcome.setFont(new Font("Arial", Font.PLAIN, 16));
        welcome.setBackground(panel.getBackground());
        welcome.setLineWrap(true);
        welcome.setWrapStyleWord(true);
        //panel that holds the heading and motto
        JPanel top = new JPanel(new GridLayout(2, 1, 5, 5));
        top.add(heading);
        top.add(motto);

        //adding to the main panel
        panel.add(top, BorderLayout.NORTH);
        panel.add(welcome, BorderLayout.CENTER);

        return panel;
    }

    // content panel which builds the lessons, recordings and resources tabs
    private JPanel createContentPanel(String type) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Count matching items so the arrays are sized to this tab's contents.
        int visibleContentCount = 0;
        for (LearningContent content : contents) {
            if (content.getContentType().equals(type)) {
                visibleContentCount++;
            }
        }

        // Keep each displayed label beside its matching content object.
        //array that stores the actual content abjects
        LearningContent[] visibleContents =
                new LearningContent[visibleContentCount];
        //array that stores the labels
        String[] labels = new String[visibleContentCount];
        int index = 0;
        for (LearningContent content : contents) {
            if (content.getContentType().equals(type)) {
                visibleContents[index] = content;
                labels[index] =
                        content.getCourseUnit() + " - " + content.getModule();
                index++;
            }
        }

        // JList displays the labels as selectable rows
        JList<String> list = new JList<>(labels);

        //for veiwing details
        JButton detailsButton = new JButton("View Details");
        //for opening the attached link
        JButton openButton = new JButton("Open Link");

        // Shows the selected item's description in a dialog.
        detailsButton.addActionListener(event -> {
            //getting which row was clicked from the list
            int selectedIndex = list.getSelectedIndex();
            //if no row was selected
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(
                    this, "Please select an item first."
                );
                return;
            }
            LearningContent selected = visibleContents[selectedIndex];

            JTextArea details = new JTextArea(selected.getDescription(), 8, 35);
            details.setEditable(false);
            details.setLineWrap(true);
            details.setWrapStyleWord(true);
            details.setCaretPosition(0);
            JOptionPane.showMessageDialog(
                this,
                new JScrollPane(details),
                "Content Details",
                JOptionPane.INFORMATION_MESSAGE
            );
        });

        // Opens the selected item's web link 
        openButton.addActionListener(event -> {
            //gets the index of the selected item to get an appropriate link
            int selectedIndex = list.getSelectedIndex();
            //no item is slecetd
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(
                    this, "Please select an item first."
                );
                return;
            }
            LearningContent selected = visibleContents[selectedIndex];
            //if link cannot be opened
            try {
                Desktop.getDesktop().browse(
                    new URI(selected.getLink())
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Could not open the link. Check your internet "
                    + "connection and the URL."
                );
            }
        });

        panel.add(new JLabel("Select " + type + " (meeting links are sample destinations):"), BorderLayout.NORTH);
        panel.add(new JScrollPane(list), BorderLayout.CENTER);
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        //panel that contains the detailsButton and open link Button
        actions.add(detailsButton);
        actions.add(openButton);
        panel.add(actions, BorderLayout.SOUTH);

        return panel;
    }

    // Lets a student record a payment plan and its reference number.
    private JPanel createPaymentPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 50, 25, 50));

        panel.add(new JLabel("Select a payment plan:"));

        String[] plans = {
            "Daily - UGX 3000",
            "Weekly - UGX 12000",
            "Monthly - UGX 30000"
        };

        //provides a drop down menu for selecting one option
        JComboBox<String> planBox = new JComboBox<>(plans);
        JTextField referenceField = new JTextField();

        panel.add(planBox);
        panel.add(new JLabel("Transaction reference:"));
        panel.add(referenceField);

        JButton submitButton = new JButton("Record Payment");

        submitButton.addActionListener(event -> {
            String reference = referenceField.getText().trim();

            //checking for the reference entered
            if (reference.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this, "Enter your payment reference."
                );
            } else if (paymentCount >= paymentRecords.length) {
                JOptionPane.showMessageDialog(
                    this, "Payment record storage is full."
                );
            } else {
                paymentRecords[paymentCount] =
                    planBox.getSelectedItem()
                    + " | Reference: " + reference
                    + " | Status: Pending verification";

                paymentCount++;

                JOptionPane.showMessageDialog(
                    this,
                    "Payment reference recorded.\n"
                    + "Status: Pending verification.\n"
                    + "This does not confirm that payment was received."
                );

                referenceField.setText("");
            }
        });

        panel.add(submitButton);

        return panel;
    }

    // Lets students post messages and see messages from this app session.
    private JPanel createDiscussionPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        discussionArea.setEditable(false);
        discussionArea.setLineWrap(true);
        discussionArea.setWrapStyleWord(true);
        discussionArea.setText(
            "Sample discussions:\n\n"
            + "Amina: Has anyone found a helpful way to practise Java classes?\n\n"
            + "Brian: I am reviewing the CSC 1104 notes on data representation.\n\n"
            + "Sarah: Breaking a problem into smaller steps is helping me "
            + "with computational thinking.\n\n"
        );
        for (int i = 0; i < discussionCount; i++) {
            discussionArea.append(discussions[i] + "\n\n");
        }

        JButton sendButton = new JButton("Post Message");

        sendButton.addActionListener(event -> {
            String message = messageField.getText().trim();

            if (message.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this, "Please type a message first."
                );
            } else if (discussionCount >= discussions.length) {
                JOptionPane.showMessageDialog(
                    this, "Discussion storage is full."
                );
            } else {
                String post = userName + ": " + message;

                discussions[discussionCount] = post;
                discussionCount++;

                discussionArea.append(post + "\n\n");
                messageField.setText("");
            }
        });

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        panel.add(new JLabel("USSA Student Discussions"), BorderLayout.NORTH);
        panel.add(new JScrollPane(discussionArea), BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);

        return panel;
    }

    // Shows academy information and basic instructions for using the app.
    private JPanel createAboutPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JTextArea information = new JTextArea(
            "ABOUT USSA\n\n"
            + "The Universe Science Scholars Academy\n"
            + "Motto: Let's explore the universe together.\n\n"
            + "CONTACTS\n"
            + "Telephone: 0766396719\n"
            + "Email: theuniversesciencescholarsacad@gmail.com\n\n"
            + "HOW TO USE THE APP\n"
            + "1. Open Lessons to find live class links.\n"
            + "2. Open Recordings to find recorded lessons.\n"
            + "3. Open Resources to access learning materials.\n"
            + "4. Open Payments to record a transaction reference.\n"
            + "5. Open Discussions to post a message.\n"
            + "6. Select an item and use Open Link where available.\n\n"
            + "IMPORTANT\n"
            + "Payment references remain pending until verified.\n"
            + "This prototype does not provide online messaging "
            + "between different computers."
        );

        information.setEditable(false);
        information.setLineWrap(true);
        information.setWrapStyleWord(true);
        information.setFont(new Font("Arial", Font.PLAIN, 15));

        panel.add(new JScrollPane(information), BorderLayout.CENTER);

        return panel;
    }

    // Starts the app on Swing's event-handling thread.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            USSAApp app = new USSAApp();
            app.setVisible(true);
        });
    }

}
