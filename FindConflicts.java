import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class FindConflicts {

    public static class AppointmentComparator implements Comparator<Appointment>{
        
        @Override
        public int compare(Appointment apptOne, Appointment apptTwo) {
            int startTimeCompare = Long.compare(apptOne.startTime, apptTwo.startTime);            
        
            return startTimeCompare == 0 ? Long.compare(apptOne.getDuration(), apptTwo.getDuration()) : startTimeCompare;
        }
    }

    public static final class Appointment {
        final long startTime;
        final long endTime;
        
        public Appointment(long startTime, long endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public long getDuration() {
            return endTime - startTime;
        }

        @Override
        public String toString() {
            return String.format("%1$s : %2$s", startTime, endTime);

        }
    }

    public static void main(String[] args) {
        List<Appointment> appointments = new ArrayList<>();

        appointments.add(new Appointment(34533, 45665));        
        appointments.add(new Appointment(54333, 67776));        
        appointments.add(new Appointment(75555, 79883));        
        appointments.add(new Appointment(83333, 98756));        
    
        Collections.sort(appointments, new AppointmentComparator());

        for(Appointment appt : appointments) {
            System.out.println(appt.toString());        
        }

        System.out.println(hasConflicts(appointments));
    }

    private static boolean hasConflicts(List<? extends Appointment> appts) {
        long lastEndTime = -1;

        for(Appointment appt : appts) {
            if(appt.startTime < lastEndTime) {
                return true;
            }

            lastEndTime = appt.endTime;
        }

        return false;
    }

}
