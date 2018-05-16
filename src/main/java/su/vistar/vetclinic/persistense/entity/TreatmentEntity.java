package su.vistar.vetclinic.persistense.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "treatment", schema = "vetclinic", catalog = "")
public class TreatmentEntity {
    private int idTreatment;
    private String name;
    private int price;
    private Timestamp duration;
    private DiagnosisEntity diagnosis;
    private UserEntity doctor;
    private AnimalEntity animal;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_treatment", nullable = false)
    public int getIdTreatment() {
        return idTreatment;
    }

    public void setIdTreatment(int idTreatment) {
        this.idTreatment = idTreatment;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "duration", nullable = false)
    public Timestamp getDuration() {
        return duration;
    }

    public void setDuration(Timestamp duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreatmentEntity that = (TreatmentEntity) o;
        return idTreatment == that.idTreatment &&
                price == that.price &&
                Objects.equals(name, that.name) &&
                Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idTreatment, name, price, duration);
    }

    @ManyToOne
    @JoinColumn(name = "diagnosis_id_diagnosis", referencedColumnName = "id_diagnosis", nullable = false)
    public DiagnosisEntity getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(DiagnosisEntity diagnosis) {
        this.diagnosis = diagnosis;
    }

    @ManyToOne
    @JoinColumn(name = "user_id_doctor", referencedColumnName = "id_user", nullable = false)
    public UserEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(UserEntity doctor) {
        this.doctor = doctor;
    }

    @ManyToOne
    @JoinColumn(name = "animal_id_animal", referencedColumnName = "id_animal", nullable = false)
    public AnimalEntity getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalEntity animal) {
        this.animal = animal;
    }
}
