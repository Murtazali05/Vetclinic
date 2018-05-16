package su.vistar.vetclinic.persistense.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "animal", schema = "vetclinic", catalog = "")
public class AnimalEntity {
    private int idAnimal;
    private String name;
    private Date birthday;
    private String breed;
    private String gender;
    private String photo;
    private UserEntity owner;
    private UserEntity doctor;
    private Collection<TreatmentEntity> treatmentsByAnimal;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_animal", nullable = false)
    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
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
    @Column(name = "birthday", nullable = false)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "breed", nullable = false, length = 45)
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Basic
    @Column(name = "gender", nullable = false, length = 45)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "photo", nullable = false, length = 45)
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalEntity that = (AnimalEntity) o;
        return idAnimal == that.idAnimal &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(breed, that.breed) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(photo, that.photo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAnimal, name, birthday, breed, gender, photo);
    }

    @ManyToOne
    @JoinColumn(name = "user_id_owner", referencedColumnName = "id_user", nullable = false)
    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    @ManyToOne
    @JoinColumn(name = "user_id_doctor", referencedColumnName = "id_user", nullable = false)
    public UserEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(UserEntity doctor) {
        this.doctor = doctor;
    }

    @OneToMany(mappedBy = "animal")
    public Collection<TreatmentEntity> getTreatmentsByAnimal() {
        return treatmentsByAnimal;
    }

    public void setTreatmentsByAnimal(Collection<TreatmentEntity> treatmentsByAnimal) {
        this.treatmentsByAnimal = treatmentsByAnimal;
    }
}
