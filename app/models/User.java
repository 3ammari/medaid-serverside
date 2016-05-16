package models;

import com.avaje.ebean.Model;
import org.joda.time.DateTime;
import play.data.format.Formats;
import play.data.validation.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by abdul-rahman on 14/05/16.
 */

@Entity
@Table(name="users")
public class User extends Model {
    public User() {
    }

    @Id
    @Constraints.Min(10)
    private Long id;

    private String name;

    @Constraints.Email
    private String email;

    private String password;

    @Formats.DateTime(pattern = "YYYY-MM-DD : hh:mm:ss")
    private DateTime createdAt;


    private String authToken;

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Finder<Long, User> find = new Finder<>(User.class);

}
