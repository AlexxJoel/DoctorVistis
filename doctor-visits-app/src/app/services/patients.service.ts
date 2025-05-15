import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {ResponseApiDoctorVisits} from "../models/ResponseApiDoctorVisits";
import {environment} from "../../enviroments/enviroments";


export interface PatientReq {
    name: string;
    age: number
    medicalHistory: string;
}

export interface Patient {
    id: number;
    name: string;
    age: number;
    medicalHistory: string;
}

@Injectable({
    providedIn: 'root'
})
export class PatientsService {

    constructor(private http: HttpClient) {
    }

    register(patient: PatientReq): Observable<Patient> {
        const endpoint = `${environment.apiUrl}/patient`;
        const response = this.http.post<ResponseApiDoctorVisits<Patient>>(endpoint, patient);
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

    getAll(): Observable<Patient[]> {
        const endpoint = `${environment.apiUrl}/patient`;
        return this.http.get<ResponseApiDoctorVisits<Patient[]>>(endpoint).pipe(
            map((response) => {
                if (response.success) {
                    return response.data;
                } else {
                    throw new Error(response.message);
                }
            })
        );
    }

    getById(id: number): Observable<Patient | null> {
        const endpoint = `${environment.apiUrl}/patient/${id}`;
        return this.http.get<ResponseApiDoctorVisits<Patient>>(endpoint).pipe(
            map((response) => {
                if (response.success) {
                    return response.data;
                } else {
                    return null
                }
            })
        );
    }
}
