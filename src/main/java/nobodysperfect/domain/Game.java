package nobodysperfect.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String title;

    private String description;

    public Game() {}

    public Game(String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return String.format(
                // TODO find out how we can convert the object into JSON more conveniently
                "{\"id\" : \"%s\", \"title\" : \"%s\", \"description\" : \"%s\"]",
                id, title, description
        );
    }

}
