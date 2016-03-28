/**
 * Created by charly on 26/03/16.
 */
import org.joda.time.DateTime;
import java.util.HashSet;

public class Surgery {

    private String doctorName;
    private String speciality;
    private HashSet<Visit> visits;

    public Surgery() {
        // TODO: 26/03/16
    }
    public Surgery(String doctorName, String especiality) {
        visits = new HashSet<>();
        this.doctorName=doctorName;
        this.speciality=especiality;
    }

    public boolean isProgrammable(Visit visit) {
        if (visit.getPatientName() == null || visit.getPatientName().isEmpty())
            return false;

        DateTime visita = new DateTime(JodaDT.parseDDMMYYYYhhmm(visit.getVisitDateTime()));

        if (visits.contains(visita))
            return false;
        for (Visit v:visits) {
            DateTime consulta = new DateTime(JodaDT.parseDDMMYYYYhhmm(v.getVisitDateTime()));
            if (visita.isAfter(consulta.plusMinutes(30)) || visita.isBefore(consulta.minusMinutes(30))) {
                //continue; // No es necessari el continue;
            }
            else {
                return false;
            }
        }
        return true;
    }

    public boolean program(Visit visit) {
        if (!isProgrammable(visit))
            return false;
        visits.add(visit);
        return true;
    }

    public int countVisits() {
        return visits.size();
    }
    public int countVisits(String date1, String date2) {
        DateTime ini = new DateTime(JodaDT.parseDDMMYYYYhhmm(date1));
        DateTime fi = new DateTime(JodaDT.parseDDMMYYYYhhmm(date2));
        int count=0;
        for(Visit v:visits) {
            if (JodaDT.isInInterval(JodaDT.parseDDMMYYYYhhmm(v.getVisitDateTime()),ini, fi)) {
                count++;
            }
        }
        return count;
    }

    public boolean cancel(Visit visit) {
        return visits.remove(visit);
    }

    public void cancelAll() {
        visits.clear();
    }

    @Override
    public int hashCode() {
        return doctorName != null ? doctorName.hashCode() : 0;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Surgery surgery = (Surgery) o;

        return !(doctorName != null ? !doctorName.equals(surgery.doctorName) : surgery.doctorName != null);

    }

    public String getDoctorName() {
        return doctorName;
    }

    public static void main(String[] args) {
        // DD/MM/YYYY-hh:mm
        Surgery s = new Surgery("Manolo", "Dentista");
        Visit[] v = new Visit[8];
        v[0] = new Visit("20/04/2016-11:00", "Paloma"); // Correcte
        v[1] = new Visit("28/03/2016-10:15", "Juana");  // Correcte
        v[2] = new Visit("28/03/2016-9:50", "Pepita");  // No es possible ja que hi ha una en menys de 30 minuts abans
        v[3] = new Visit("28/03/2016-10:15", "Carmen"); // No es possible ja que n'hi ha una a la mateixa hora
        v[4] = new Visit("28/03/2016-10:40", "Yeni");   // No es possible ja que n'hi ha una en menys de 30 minuts despres
        v[5] = new Visit("28/03/2016-11:00", "Ari");    // Correcte
        v[6] = new Visit("04/04/2016-11:00", "Irina");  // Correcte
        v[7] = new Visit("20/03/2016-11:00", "Laura");  // Correcte


        for (Visit i:v) {
            System.out.println(s.program(i));
        }

        System.out.println(s.countVisits());

        System.out.println(s.countVisits("25/03/2016-11:00", "15/04/2016-11:00"));

        System.out.println(s.cancel(v[4]));     // No existeix ( no s'ha afegit abans )
        System.out.println(s.cancel(v[6]));     // Correcte

        System.out.println(s.countVisits());

        s.cancelAll();
        System.out.println(s.countVisits());
    }
}