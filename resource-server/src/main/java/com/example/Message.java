package com.example;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jun on 2016/06/29.
 */
@Entity
public class Message {
    @Id
    @GeneratedValue
    public Integer id;
    public String text;
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdAt;
    public String username;
}
