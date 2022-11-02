package edu.rosette.architecturebackend.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("0")
public class Doctor extends Employee {
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WorkingWindow> workingWindows = new ArrayList<>();
}
