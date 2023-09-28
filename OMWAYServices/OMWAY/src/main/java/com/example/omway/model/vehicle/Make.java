package com.example.omway.model.vehicle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name="Make",schema = "Vehicle")
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name="Make.all",query="select e from Make e")
})
public class Make {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

   @OneToMany(mappedBy = "make",cascade = CascadeType.ALL
            ,fetch = FetchType.LAZY)
   @JsonIgnore
    private List<Model> makeModels;

}
