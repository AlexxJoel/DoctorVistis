import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map, Observable} from 'rxjs';
import {environment} from "../../enviroments/enviroments";
import {ResponseApiDoctorVisits} from "../models/ResponseApiDoctorVisits";


export interface AppointmentHistory {
    appointmentId: number;
    diagnosis: string;
    prescription: string;
}

export interface CreateAppointmentHistoryReq {
    appointmentId: number;
    diagnosis: string;
    treatment: string;
}

@Injectable({
    providedIn: 'root'
})
export class ApointmentsHistoryService {

    constructor(private http: HttpClient) {

    }

    register(appointmentHistory: CreateAppointmentHistoryReq): Observable<AppointmentHistory> {
        const url = `${environment.apiUrl}/history-appointments`;
        const response = this.http.post<ResponseApiDoctorVisits<AppointmentHistory>>(url, appointmentHistory);
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

    getAll(): Observable<AppointmentHistory[]> {
        const url = `${environment.apiUrl}/history-appointments`;
        return this.http.get<ResponseApiDoctorVisits<AppointmentHistory[]>>(url).pipe(
            map((response) => {
                if (response.success) {
                    return response.data;
                } else {
                    throw new Error(response.message);
                }
            })
        );
    }


}
