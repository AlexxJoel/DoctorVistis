import {Component} from '@angular/core';
import {CommonModule} from "@angular/common";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {PatientsService} from "../services/patients.service";

@Component({
    selector: 'app-patient-crud',
    imports: [CommonModule, ReactiveFormsModule],
    templateUrl: './patient-crud.component.html',
    standalone: true,
    styleUrl: './patient-crud.component.css'
})
export class PatientCrudComponent {

    form: FormGroup;
    response: { success: boolean; message: string; } | null = null;
    patients: any[] = [];

    constructor(
        private patientService: PatientsService,
        private fb: FormBuilder
    ) {
        this.form = this.fb.group({
            name: [''],
            age: [''],
            medicalHistory: [''],
        });
    }

    ngOnInit() {
        this.getAllPatients();
    }

    onSubmit() {
        if (this.form.valid) {
            this.registerPatient();
        }
    }

    registerPatient() {
        const patientReq = {
            name: this.form.get('name')?.value,
            age: this.form.get('age')?.value,
            medicalHistory: this.form.get('medicalHistory')?.value,
        };

        this.patientService.register(patientReq).subscribe({
            next: (response) => {
                this.response = {
                    success: true,
                    message: 'Correcto! Paciente registrado correctamente'
                }
                console.log(response);
                this.form.reset();
                this.getAllPatients();
            },
            error: (error) => {
                const {status} = error;
                console.log(status);
                if (status === 409) {
                    this.response = {
                        success: false,
                        message: 'Error! Paciente ya registrado'
                    }
                } else {
                    this.response = {
                        success: false,
                        message: 'Error! No se pudo registrar el paciente'
                    }
                }
            }
        });
    }

    getAllPatients() {
        this.patientService.getAll().subscribe({
            next: (response) => {
                this.patients = response.reverse();
            },
            error: (error) => {
                console.error(error);
            }
        });
    }

}
