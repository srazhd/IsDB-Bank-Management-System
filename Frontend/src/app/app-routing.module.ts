import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/pages/auth/login/login.component';

import { HomeComponent } from './home/home.component';
import { CustomersComponent } from './customers/customers.component'; 
 
import { DepositComponent } from './components/admin/deposit/deposit.component';
import { WithdrawComponent } from './components/admin/withdraw/withdraw.component';
import { BalanceComponent } from './balance/balance.component';
import { StatementComponent } from './statement/statement.component';
import { AdminGuard } from './services/admin.guard';
import { DashboardComponent } from './components/admin/dashboard/dashboard.component';
import { ProfileComponent } from './components/admin/profile/profile.component';
import { UserComponent } from './components/admin/user/user.component';
import { AdduserComponent } from './components/admin/user-add/adduser.component';
import { AddbranchComponent } from './components/admin/branch-add/addbranch.component';
import { BranchComponent } from './components/admin/branch/branch.component';
import { CustomerAddComponent } from './components/admin/customer-add/customer-add.component';
import { CustomerComponent } from './components/admin/customer/customer.component';
import { AccountAddComponent } from './components/admin/account-add/account-add.component';
import { AccountComponent } from './components/admin/account/account.component';
import { DepositaddComponent } from './components/admin/depositadd/depositadd.component';
import { WithdrawaddComponent } from './components/admin/withdrawadd/withdrawadd.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'customers', component: CustomersComponent },
  { path: 'balance', component: BalanceComponent },
  { path: 'statement', component: StatementComponent },
  {
    path: 'admin',
    component: DashboardComponent,
    canActivate: [AdminGuard],
    children: [
      {
        path: 'profile',
        component: ProfileComponent,
      },
      {
        path: 'user',
        component: UserComponent,
      },
      {
        path: 'useradd',
        component: AdduserComponent,
      },{
        path: 'branchadd',
        component: AddbranchComponent,
      },{
        path: 'branch',
        component: BranchComponent,
      },{
        path: 'customeradd',
        component: CustomerAddComponent,
      },{
        path: 'customers',
        component: CustomerComponent,
      },{
        path: 'accountcreate',
        component: AccountAddComponent,
      },{
        path: 'accounts',
        component: AccountComponent,
      },{
        path: 'depositadd',
        component: DepositaddComponent,
      },{
        path: 'deposits',
        component: DepositComponent,
      },{
        path: 'withdrawadd',
        component: WithdrawaddComponent,
      },{
        path: 'withdraws',
        component: WithdrawComponent,
      }
      
    ],
  },
 
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
