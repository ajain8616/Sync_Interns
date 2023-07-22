package Online_Survey_System;

import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                OnlineSurveySystem surveySystem = new OnlineSurveySystem();
                surveySystem.showForm();
            }
        });
    }
}
