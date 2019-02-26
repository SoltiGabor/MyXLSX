package poseidon.importing;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ScheduleEntry {
    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd. H:mm:ss");

    private Date start;
    private Date end;
    private String subjectName;
    private String subjectCode;
    private String course;
    private String teacher;
    private String room;

    public ScheduleEntry(List<String> row) {
        try {
            start = dateFormat.parse(row.get(0));
            end = dateFormat.parse(row.get(1));

            String classString = row.get(2);
            if (classString.endsWith(" - Órarend")) {
                classString = classString.replace(" - Órarend", "");
            }
            String subject = classString.split(" - \\d{2} - ")[0];
            teacher = classString.split(" - \\d{2} - ")[1];
            subjectName = subject.substring(0, subject.lastIndexOf('(') - 1);
            subjectCode = subject.substring(subject.lastIndexOf('(') + 1, subject.length() - 2);
            course = classString.substring(
                    classString.indexOf(teacher) - 5, classString.indexOf(teacher) - 3);

            room = row.get(3);

        } catch (Exception e) {
            Log.e("ScheduleEntry", e.toString(), e);
        }
    }

    @Override
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("Start: \t" + start + "\n");
        ret.append("End: \t" + end + "\n");
        ret.append("Subject name : \t" + subjectName + "\n");
        ret.append("Subject code : \t" + subjectCode + "\n");
        ret.append("Course : \t\t\t\t\t" + course + "\n");
        ret.append("Teacher : \t\t\t\t" + teacher + "\n");
        ret.append("Room : \t\t\t\t\t\t" + room + "\n");
        return ret.toString();
    }
}
