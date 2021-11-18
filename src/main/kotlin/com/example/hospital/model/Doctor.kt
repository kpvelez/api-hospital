package com.example.hospital.model

import javax.persistence.*

@Entity
@Table(name = "doctor")

class Doctor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var iddoctor: Long? = null
    var nombre: String? = null
    var apellido: String? = null
    var cedula: String? = null
    var especialidad: String? = null
}