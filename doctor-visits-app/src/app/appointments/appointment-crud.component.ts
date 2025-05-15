import {Component} from '@angular/core';
import {CommonModule} from "@angular/common";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {ApointmentsService, Appointment, AppointmentReq} from "../services/apointments.service";
import {Doctor, DoctorsService} from "../services/doctors.service";
import {Patient, PatientsService} from "../services/patients.service";

@Component({
    selector: 'app-appointment-crud',
    imports: [CommonModule, ReactiveFormsModule],
    standalone: true,
    templateUrl: './appointment-crud.component.html',
    styleUrl: './appointment-crud.component.css'
})
export class AppointmentCrudComponent {
    form: FormGroup;
    response: { success: boolean; message: string; } | null = null;
    appointments: Appointment[] = [];
    doctors: Doctor[] = [];
    patients: Patient  [] = [];

    constructor(
        private doctorService: DoctorsService,
        private patientService: PatientsService,
        private appointmentService: ApointmentsService, // Replace with actual service
        private fb: FormBuilder
    ) {
        this.form = this.fb.group({
            date: [''],
            hour: [''],
            doctorId: [''],
            patientId: [''],
        })
    }

    ngOnInit() {
        this.getAllAppointments();
        this.getAllDoctors();
        this.getAllPatients();
    }

    getAllAppointments() {
        this.appointmentService.getAll().subscribe({
            next: (response) => {
                this.appointments = response.reverse();
            },
            error: (error) => {
                console.error('Error fetching appointments:', error);
            }
        });
    }

    getAllDoctors() {
        this.doctorService.getAll().subscribe({
            next: (response) => {
                this.doctors = response.reverse();
            },
            error: (error) => {
                console.error('Error fetching doctors:', error);
            }
        });
    }

    getAllPatients() {
        this.patientService.getAll().subscribe({
            next: (response) => {
                this.patients = response.reverse();
            },
            error: (error) => {
                console.error('Error fetching patients:', error);
            }
        });
    }

    onSubmit() {
        if (this.form.valid) {
            this.onSubmitAppointment();
        }
    }

    onSubmitAppointment() {
        const appointment: AppointmentReq = {
            appointmentDateTime: `${this.form.get('date')?.value}T${this.form.get('hour')?.value}`,
            doctorId: this.form.get('doctorId')?.value,
            patientId: this.form.get('patientId')?.value,
        };

        this.appointmentService.register(appointment).subscribe({
            next: (response) => {
                this.response = {
                    success: true,
                    message: 'Correcto! Cita registrada correctamente'
                }
                console.log(response);
                this.form.reset();
                this.getAllAppointments();
            },
            error: (error) => {
                const {status} = error;
                console.log(status);
                if (status === 409) {
                    this.response = {
                        success: false,
                        message: 'Error! Appointment already exists'
                    }
                } else if (status === 400) {
                    this.response = {
                        success: false,
                        message: 'Error! No se pudo registrar la cita, verifique los datos'
                    }
                } else {
                    this.response = {
                        success: false,
                        message: 'Error! No se ha podido registrar la cita'
                    }
                }
            }
        });
    }
}
