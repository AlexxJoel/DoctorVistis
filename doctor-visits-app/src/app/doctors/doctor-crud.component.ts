import {Component} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {Doctor, DoctorReq, DoctorsService} from "../services/doctors.service";
import {CommonModule} from "@angular/common";

@Component({
    selector: 'app-doctor-crud',
    imports: [CommonModule, ReactiveFormsModule],
    templateUrl: './doctor-crud.component.html',
    standalone: true,
    styleUrl: './doctor-crud.component.css'
})
export class DoctorCrudComponent {
    formDoctor: FormGroup;
    response: { success: boolean; message: string; } | null = null;
    doctors: Doctor[] = [];

    constructor(
        private doctorService: DoctorsService,
        private fb: FormBuilder
    ) {
        this.formDoctor = this.fb.group({
            name: [''],
            specialty: [''],
        });
    }

    ngOnInit() {
        this.getAllDoctors();
    }

    onSubmit() {
        if (this.formDoctor.valid) {
            this.onSubmitDoctor();
        }
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
                this.getAllDoctors();
            },
            error: (error) => {
                const {status} = error;
                console.log(status);
                if (status === 409) {
                    this.response = {
                        success: false,
                        message: 'Upss! El doctor ya existe'
                    }
                } else if (status === 400) {
                    this.response = {
                        success: false,
                        message: 'Error! El doctor no se ha podido registrar'
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

    getAllDoctors() {
        this.doctorService.getAll().subscribe({
            next: (response) => {
                console.log(response);
                this.doctors = response.reverse();
            },
            error: (error) => {
                this.response = {
                    success: false,
                    message: 'Error! No se ha podido obtener los doctores'
                }
                console.error(error);
            }
        });
    }
}
