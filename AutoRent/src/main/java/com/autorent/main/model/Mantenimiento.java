package com.autorent.main.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "mantenimiento")
public class Mantenimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmant")
    Integer id;

    @Column(name = "fechamant")
    LocalDate fecha;

    @Column(name = "tipomant")
    String tipo;

    @Column(name = "descripcion")
    String descripcion;

    @Column(name = "costomant")
    Double costo;

    @Column(name = "responsable")
    String responsable;

    @Column(name = "estmant")
    Boolean estado;

    @Column(name = "fotomant")
    String foto;

    @Transient
    private MultipartFile archivoFoto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idveh")
    Vehiculo vehiculo;
}