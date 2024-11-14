package ServiceClasses.Database;

import java.util.ArrayList;

import ServiceClasses.AppointmentOutcome.AppoinmentOutcomeControl;
import ServiceClasses.AppointmentOutcome.AppointmentOutcome;
import ServiceClasses.AppointmentOutcome.AppointmentOutcomeBuild;
import input.CSVParse;
import input.HashParse;

public class OutcomeFileHandler extends FileHandler<AppointmentOutcome> {

    public OutcomeFileHandler(){
        super("src\\data\\Appointment_Outcome_List.csv");
    }

    @Override
    public ArrayList<AppointmentOutcome> retrieveData() {

        ArrayList <AppointmentOutcome> results = new ArrayList<>();
        try{
            ArrayList <String[]> data = CSVParse.read(getFilePath(), false);
            results = (new AppointmentOutcomeBuild()).buildMany(data);
            AppoinmentOutcomeControl.getInstance().setAppointmentOutcomes(results);

            
        } catch (Exception e) {}

        return results;

    }

    @Override
    public void saveData() {
        ArrayList <String> data = new ArrayList <String> ();
        for (AppointmentOutcome outcome : AppoinmentOutcomeControl.getInstance().getAppointmentOutcomes()) {

            data.add(
                outcome.getAppointmentID() + "," +
                outcome.getPatientName() + "," +
                outcome.getDoctorName() + "," +
                outcome.getServiceType() + "," +
                outcome.getDateTime() + "," +
                HashParse.toString(outcome.getPrescribedMedications()) + "," +
                outcome.getConsultationNotes() + "," +
                outcome.getStatus().toString()
            );
        }
        try {
            CSVParse.write(getFilePath(), data);
        } catch (Exception e) {}

    }

}
