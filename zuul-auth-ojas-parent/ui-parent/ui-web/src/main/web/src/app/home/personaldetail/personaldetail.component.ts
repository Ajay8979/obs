import { Component, OnInit } from '@angular/core';
import { HrmsService } from '../services/hrms.service';

@Component({
  selector: 'app-personaldetail',
  templateUrl: './personaldetail.component.html',
  styleUrls: ['./personaldetail.component.scss']
})
export class PersonaldetailComponent implements OnInit {
  empbasic: any;
  empbasicinfo: any;

  constructor(private hrms:HrmsService) { }

  ngOnInit() {
   this. getbaicdetil();
  }
  getbaicdetil(){
 var empinfo =
  {

    "employeeInfo": [{
     

    }],
    "transactionType": "getAll"

  }
  this.hrms.getempinfo(empinfo).subscribe(res => {
    this.empbasic = res;
    this.empbasicinfo = this.empbasic.employeeInfo;
    console.log(this.empbasicinfo);
  })
}
}
