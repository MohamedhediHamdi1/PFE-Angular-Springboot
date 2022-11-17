package Cryptoo.com.example.Cryptoo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "groups")
public class GroupEntity implements Serializable {

    private static final long serialVersionUID = -5501778061888615253L;

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", length = 30)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "groups_users",joinColumns = {@JoinColumn(name = "groups_id")},inverseJoinColumns = {@JoinColumn(name = "users_id")})
    private Set<UserEntity> users = new HashSet<>();

}
