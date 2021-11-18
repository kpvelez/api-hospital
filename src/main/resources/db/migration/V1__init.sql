CREATE TABLE doctor(
  idDoctor serial primary key,
  nombre VARCHAR(45) NOT NULL,
  apellido VARCHAR(45) NULL,
  cedula VARCHAR(45) NULL,
  especialidad VARCHAR(45) NULL
  );

  CREATE TABLE paciente(
    idPaciente serial primary key,
    nombre VARCHAR(45) NOT NULL,
    apellido VARCHAR(45) NULL,
    cedula VARCHAR(45) NULL,
    enfermedad VARCHAR(45) NULL,
    doctor_idDoctor int not null,
    FOREIGN KEY (doctor_idDoctor) references doctor(idDoctor)
    );

  CREATE TABLE medicina(
    idMedicina serial primary key,
    medicamento VARCHAR(45) NOT NULL,
    via VARCHAR(45) NULL,
    dosis VARCHAR(45) NULL,
    fecha VARCHAR(45) NULL,
    paciente_idPaciente int not null,
    FOREIGN KEY (paciente_idPaciente) references paciente(idPaciente)
    );