package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;
import play.data.format.Formats;
import play.data.validation.*;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by abdul-rahman on 14/05/16.
 */

@Entity
@Table(name="users")
public class User extends Model {
    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Constraints.Email
    private String email;

    @Transient
    @JsonIgnore
    private String password;


    @Column(length = 64, nullable = false)
    private byte[] shaPassword;

    public byte[] getShaPassword() {
        return shaPassword;
    }

    public void setShaPassword(byte[] shaPassword) {
        this.shaPassword = shaPassword;
    }

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

    public void setPassword(String password) {this.password = password;
        shaPassword = getSha512(password);}

    public static Finder<Long, User> find = new Finder<>(User.class);


    public static byte[] getSha512(String value) {

        try {
            return MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8"));
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }



}
