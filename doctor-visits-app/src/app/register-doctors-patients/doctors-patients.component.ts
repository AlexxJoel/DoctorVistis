import {Component} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {DoctorsService, DoctorReq} from "../services/doctors.service";
import {HttpClient} from "@angular/common/http";

@Component({
    selector: 'app-doctors-patients',
    imports: [CommonModule, ReactiveFormsModule],
    templateUrl: './doctors-patients.component.html',
    standalone: true,
    styleUrl: './doctors-patients.component.css'
})
export class DoctorsPatientsComponent {

    formDoctor: FormGroup;
    formPatient: FormGroup;
    response: { success: boolean; message: string; } | null = null;

    constructor(
        private doctorService: DoctorsService,
        private fb: FormBuilder
    ) {
        this.formDoctor = this.fb.group({
            name: [''],
            specialty: [''],
        });

        this.formPatient = this.fb.group({
            name: [''],
            age: [''],
            medicalHistory: [''],
        });
    }

    onSubmit(id: "PATIENT" | "DOCTOR") {
        if (id === "PATIENT") {
            if (this.formPatient.valid) {
                this.onSubmitPatient();
            }
        } else if (id === "DOCTOR") {
            if (this.formDoctor.valid) {
                this.onSubmitDoctor();
            }
        }
    }

    onSubmitPatient() {
        this.formPatient.reset();
    }

    onSubmitDoctor() {
        const doctorReq: DoctorReq = {
            name: this.formDoctor.get('name')?.value,
            specialty: this.formDoctor.get('specialty')?.value,
        };

        this.doctorService.register(doctorReq).subscribe({
            next: (response) => {
                this.response = {
                    success: true,
                    message: 'Correcto! Doctor registrado correctamente'
                }
                console.log(response);
                this.formDoctor.reset();
            },
            error: (error) => {
                const status = error.status;
                console.log(status);
                if (status === 409) {
                    this.response = {
                        success: false,
                        message: 'Upss! El doctor ya existe'
                    }
                } else {
                    this.response = {
                        success: false,
                        message: 'Error! No se ha podido registrar el doctor'
                    }
                }
                console.error(error);
            }
        });
    }

}
