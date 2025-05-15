import {Component} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ApointmentsService, Appointment} from "../services/apointments.service";
import {CommonModule} from "@angular/common";
import {Patient, PatientsService} from "../services/patients.service";
import {Doctor, DoctorsService} from "../services/doctors.service";
import {
    ApointmentsHistoryService,
    AppointmentHistory,
    CreateAppointmentHistoryReq
} from "../services/apointments-history.service";

@Component({
    selector: 'app-appointment-history-crud',
    imports: [CommonModule, ReactiveFormsModule, FormsModule],
    standalone: true,
    templateUrl: './appointment-history-crud.component.html',
    styleUrl: './appointment-history-crud.component.css'
})
export class AppointmentHistoryCrudComponent {

    form: FormGroup;
    response: { success: boolean; message: string; } | null = null;
    appointments: Appointment[] = [];
    idAppointmentSelected: number = 0;
    patients: Patient[] = [];
    doctors: Doctor[] = [];
    searchBy: string = 'doctor';
    attendedAppointments: AppointmentHistory[] = [];
    selectedPatient: number = 0;

    constructor(
        private appointmentService: ApointmentsService, // Replace with actual service
        private appointmentHistoryService: ApointmentsHistoryService,
        private doctorService: DoctorsService,
        private patientService: PatientsService,
        private fb: FormBuilder
    ) {
        this.form = this.fb.group({
            appointmentId: [''],
            diagnosis: [''],
            prescription: [''],
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

    getAttendedAppointments() {
        this.appointmentHistoryService.getAll().subscribe({
            next: (response) => {
                this.attendedAppointments = response.reverse();
            },
            error: (error) => {
                console.error('Error fetching attended appointments:', error);
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

    attendAppointment(a: number) {
        this.idAppointmentSelected = a;
    }

    onSubmit() {
        const appointmentHistory: CreateAppointmentHistoryReq = {
            appointmentId: this.idAppointmentSelected,
            diagnosis: this.form.value.diagnosis,
            treatment: this.form.value.prescription
        };

        //check if exists id appointment in attended appointments
        const exists = this.attendedAppointments.some(appointment => appointment.appointmentId === this.idAppointmentSelected);
        if (exists) {
            this.response = {success: false, message: 'Ya existe una historia de cita para esta cita'};
            return;
        }

        this.appointmentHistoryService.register(appointmentHistory).subscribe({
            next: (response) => {
                this.response = {success: true, message: 'Registro de historia de cita exitoso'};
                this.getAllAppointments()
                this.form.reset();
            },
            error: (error) => {
                console.error('Error al registrar historia de cita:', error);
                this.response = {
                    success: false, message: 'Error al registrar historia de cita'
                };
            }
        });
    }

}