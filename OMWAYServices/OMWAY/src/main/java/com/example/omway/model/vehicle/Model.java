package com.example.omway.model.vehicle;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name="Model",schema = "Vehicle")
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name="Model.all",query="select e from Model e")
})
public class Model {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey=@ForeignKey(name="FK_make_model_makeid"))
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Make make;

    @OneToMany(mappedBy = "model",cascade = CascadeType.ALL
            ,fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private List<Car> modelCars;
}