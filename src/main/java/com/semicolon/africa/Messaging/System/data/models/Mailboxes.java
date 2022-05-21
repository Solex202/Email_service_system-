package com.semicolon.africa.Messaging.System.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor

public class Mailboxes {

    @Id
    private String email;
    private List<Mailbox> mailboxes;

    public Mailboxes() {
        this.mailboxes = new ArrayList<>();
    }

}
