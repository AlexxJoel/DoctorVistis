import {Injectable} from '@angular/core';
import {environment} from "../../enviroments/enviroments";
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {ResponseApiDoctorVisits} from "../models/ResponseApiDoctorVisits";

export interface DoctorReq {
    name: string;
    specialty: string;
}

export interface Doctor {
    id: number;
    name: string;
    specialty: string;
}

@Injectable({
    providedIn: 'root'
})
export class DoctorsService {

    constructor(private http: HttpClient) {
    }

    register(doctor: DoctorReq): Observable<Doctor> {
        const response: Observable<ResponseApiDoctorVisits<Doctor>> = this.http.post<ResponseApiDoctorVisits<Doctor>>(`${environment.apiUrl}/doctors`, doctor);
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
}
