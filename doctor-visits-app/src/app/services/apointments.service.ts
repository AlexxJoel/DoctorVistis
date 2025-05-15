import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map, Observable} from 'rxjs';
import {environment} from "../../enviroments/enviroments";
import {ResponseApiDoctorVisits} from "../models/ResponseApiDoctorVisits";
import {Doctor, DoctorsService} from "./doctors.service";
import {Patient, PatientsService} from "./patients.service";


export interface AppointmentReq {
    appointmentDateTime: string; // YYYY-MM-DDTHH:mm:ss
    doctorId: number;
    patientId: number;
}

export interface Appointment {
    id: number;
    date: string;
    hour: string;
    doctorId: number;
    patientId: number;
    doctor: Doctor | null;
    patient: Patient | null;
}


@Injectable({
    providedIn: 'root'
})
export class ApointmentsService {

    constructor(
        private doctorService: DoctorsService,
        private patientService: PatientsService,
        private http: HttpClient) {
    }

    register(appointment: AppointmentReq): Observable<Appointment> {
        const url = `${environment.apiUrl}/appointments`;
        const response = this.http.post<ResponseApiDoctorVisits<Appointment>>(url, appointment);
        return response.pipe(
            map((response) => {
                if (response.success) {
                    return response.data;
                } else {
                    throw new Error(response.message);
                }
            })
        );
    }

    getAll(): Observable<Appointment[]> {
        const url = `${environment.apiUrl}/appointments`;
        return this.http.get<ResponseApiDoctorVisits<Appointment[]>>(url).pipe(
            map((response) => {
                if (response.success) {
                    const appointments = response.data;
                    appointments.forEach((appointment) => {
                        appointment.doctor = null;
                        appointment.patient = null;

                        this.doctorService.getById(appointment.doctorId).subscribe({
                            next: (doctor) => {
                                appointment.doctor = doctor;
                            },
                            error: (error) => {
                                console.error('Error fetching doctor:', error);
                            }
                        });

                        this.patientService.getById(appointment.patientId).subscribe({
                            next: (patient) => {
                                appointment.patient = patient;
                            },
                            error: (error) => {
                                console.error('Error fetching patient:', error);
                            }
                        });
                    });
                    return appointments;
                } else {
                    throw new Error(response.message);
                }
            })
        );
    }

}
