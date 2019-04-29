


// import { Component, OnInit } from '@angular/core';

// @Component({
//   selector: 'app-employee',
//   templateUrl: './employee.component.html',
//   styleUrls: ['./employee.component.scss']
// })
// export class EmployeeComponent implements OnInit {

//   constructor() { }

//   ngOnInit() {
//   }
//  value:boolean;
//  data;
//  isEditable:boolean=false;
//  reverse:boolean=false;

//  id:any;
//  idList;
//  idlist:any;

//  name:any;
//  nameList;
//  namelist:any;

//  fixedctc:any;
//  fixedctcList;
//  fixedctclist:any;

//  ctc: any;
//  ctcList;
//  ctclist:any;

//  totalctc:any;
//  totalctcList;
//  totalctclist:any;




// }





import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeClass } from './employee.model';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss']
})
export class EmployeeComponent implements OnInit {
  addEmpTemplate:boolean=false

  value:boolean;
  data:any;
  employee:any;
 Employee_Id:any;       
Employee_Name:any
Fixed_CTC:any;
CTC:any;
Total_CTC:any;


 empData :any= [{Id:19106,
           Name:"BALAJI",
           Fixed_CTC:12000,
           CTC:31000 ,
           Total_CTC:27000}];
  location: any;


   constructor(private _router: Router) { }
   onBackButtonClick(): void 
   {
    this._router.navigate(['/employeedetails']);
    //this._router.navigateByUrl('/employeedetails');
  }


  cancel() {
     this.location.back(); 
  }



  ngOnInit() {
  }
  addTemplate(){
    this.addEmpTemplate=true
  }
 
  updateData(data:any){
    this.empData.push(data)
  }

  employee_edit(id){
   this._router.navigateByUrl('/employeeedit');
  }

  savedata(){
    var data ={
      
      "Id":this.Employee_Id ,
      "Name":this.Employee_Name,
      "Fixed_CTC":this.Fixed_CTC,
      "CTC":this.CTC,
      "Total_CTC":this.Total_CTC
             
  
    }
    this.empData.push(data);
  }
  


deleteFieldValue(index) {
  debugger;     
this.empData.splice(index, 1);
 }
 

}






