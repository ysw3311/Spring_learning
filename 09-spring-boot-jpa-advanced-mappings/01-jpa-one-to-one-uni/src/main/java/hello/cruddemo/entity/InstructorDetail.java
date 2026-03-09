package hello.cruddemo.entity;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="instructor_detail")
@Getter

public class InstructorDetail {
    //annotate the class as an entity and map to db table

    // define the fields

    //annote the fields with db column names

    //creat constructors

    //generate getter/setter methods

    //generate toString() method


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private String id;

    @Column(name="youtube_channel")
    private String youtubeChannel;

    @Column(name="hobby")
    private String hobby;

}
