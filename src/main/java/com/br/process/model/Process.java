package com.br.process.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data // toString, equals, hashCode, getters e setters.
@Entity(name = "process")
@SQLDelete(sql = "UPDATE process SET deleted=true WHERE id=?") //delete seta status deleted para true
@Where(clause = "deleted = false") //retorna apenas os registros com status deleted = false (não deletado)
public class Process {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name_requester;
    private String zip_code;
    private String city;
    private String district;
    private String street;
    private String num_house;
    private Date date_process;
    private String filename_process; //name process
    @Column(columnDefinition = "DEFAULT FALSE")
    private Boolean deleted;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date created_at; //data da criação
}
