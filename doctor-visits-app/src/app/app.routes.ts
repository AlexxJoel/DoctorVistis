import {Routes} from '@angular/router';
import {DoctorCrudComponent} from "./doctors/doctor-crud.component";
import {PatientCrudComponent} from "./patients/patient-crud.component";
import {AppointmentCrudComponent} from "./appointments/appointment-crud.component";
import {HistoryListComponent} from "./history/history-list/history-list.component";

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
        component: PatientCrudComponent,
        title: 'Patient ',
    },
]

const routesAppointments: Routes = [
    {
        path: 'appointments',
        component: AppointmentCrudComponent,
        title: 'Appointment List',
    },

]

const routesHistoryAppointments: Routes = [
    {
        path: 'history',
        component: HistoryListComponent,
        title: 'History',
    }
]


export const routes: Routes = [
    ...routesDoctors,
    ...routesPatients,
    ...routesAppointments,
    ...routesHistoryAppointments,
    {path: '', redirectTo: 'appointments', pathMatch: 'full'},
    {path: '**', redirectTo: 'appointments'}
];
