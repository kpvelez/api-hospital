package com.example.hospital.model

import javax.persistence.*

    @Entity
    @Table(name = "paciente")

    class Paciente {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        @Column(updatable = false)
        var idpaciente: Long? = null
        var nombre: String? = null
        var apellido: String? = null
        var cedula: String? = null
        var enfermedad: String? = null
        @Column(name="doctor_iddoctor")
        var doctorIdDoctor: Long? = null
    }
