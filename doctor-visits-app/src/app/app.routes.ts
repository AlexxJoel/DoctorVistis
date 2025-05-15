import {Routes} from '@angular/router';
import {DoctorListComponent} from "./doctors/doctor-list/doctor-list.component";
import {PatientListComponent} from "./patients/patient-list/patient-list.component";
import {AppointmentListComponent} from "./appointments/appointment-list/appointment-list.component";
import {AppointmentFormComponent} from "./appointments/appointment-form/appointment-form.component";
import {DoctorsPatientsComponent} from "./register-doctors-patients/doctors-patients.component";

const routesDoctors: Routes = [
    {
        path: 'doctors',
        component: DoctorListComponent,
        title: 'Doctor List',
    },
]

const routesPatients: Routes = [
    {
        path: 'patients',
        component: PatientListComponent,
        title: 'Patient List',
    },
]

const routesAppointments: Routes = [
    {
        path: 'appointments',
        component: AppointmentListComponent,
        title: 'Appointment List',
    },
    {
        path: 'appointment/register',
        component: AppointmentFormComponent,
        title: 'Register Appointment',
    }
]

const routesHistoryAppointments: Routes = [
    {
        path: 'history',
        component: AppointmentListComponent,
        title: 'History',
    }
]



export const routes: Routes = [
    { path: 'register-doctor-patient', component: DoctorsPatientsComponent, title: 'Register Doctor/Patient' },
    ...routesDoctors,
    ...routesPatients,
    ...routesAppointments,
    ...routesHistoryAppointments,
    { path: '', redirectTo: 'appointments', pathMatch: 'full' },
    { path: '**', redirectTo: 'appointments' }
];
