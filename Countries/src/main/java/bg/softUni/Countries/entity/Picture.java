package bg.softUni.Countries.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String url;

    @ManyToOne(optional = false)
    private User author;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Country country;



    public Picture() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Country getRoute() {
        return country;
    }

    public void setRoute(Country country) {
        this.country = country;
    }

}
