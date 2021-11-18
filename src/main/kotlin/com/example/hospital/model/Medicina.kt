package com.example.hospital.model

import javax.persistence.*

@Entity
@Table(name = "medicina")

class Medicina {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var  idmedicina: Long? = null
    var medicamento: String? = null
    var via: String? = null
    var dosis: String? = null
    var fecha: String? = null
    @Column(name="paciente_idpaciente")
    var pacienteIdPaciente: Long? = null
}