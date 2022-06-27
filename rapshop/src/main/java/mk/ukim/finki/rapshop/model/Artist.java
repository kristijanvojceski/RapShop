package mk.ukim.finki.rapshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "artist_about")
    private String about;

    public Artist() {
    }

    public Artist(String name, String about) {
        this.name = name;
        this.about = about;
    }
}
