/**
 * Created by charly on 26/03/16.
 */
public class Visit {
    private String visitDateTime;
    private String patientName;

    public Visit() {
        // TODO: 26/03/16
    }
    public Visit(String visitDateTime) {
        this.visitDateTime=visitDateTime;
        // TODO: 26/03/16
    }
    public Visit(String visitDateTime, String patientName) {
        this.visitDateTime=visitDateTime;
        this.patientName=patientName;
    }

    @Override
    public int hashCode() {
        return visitDateTime != null ? visitDateTime.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Visit visit = (Visit) o;

        return !(visitDateTime != null ? !visitDateTime.equals(visit.visitDateTime) : visit.visitDateTime != null);

    }

    public String getVisitDateTime() {
        return visitDateTime;
    }
    public String getPatientName() {
        return patientName;
    }
}
