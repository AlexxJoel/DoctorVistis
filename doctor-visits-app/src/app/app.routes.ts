import {Routes} from '@angular/router';
import {PatientListComponent} from "./patients/patient-list/patient-list.component";
import {AppointmentListComponent} from "./appointments/appointment-list/appointment-list.component";
import {AppointmentFormComponent} from "./appointments/appointment-form/appointment-form.component";
import {DoctorCrudComponent} from "./doctors/doctor-crud.component";

const routesDoctors: Routes = [
    {
        path: 'doctors',
        component: DoctorCrudComponent,
        title: 'Doctor',
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
    ...routesDoctors,
    ...routesPatients,
    ...routesAppointments,
    ...routesHistoryAppointments,
    { path: '', redirectTo: 'appointments', pathMatch: 'full' },
    { path: '**', redirectTo: 'appointments' }
];
