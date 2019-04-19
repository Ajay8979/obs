import { Component, OnInit } from '@angular/core';
import{EmployeeEducation} from './employeeeducation.model';
import { HrmsService } from '../services/hrms.service';

@Component({
  selector: 'app-employeeeducation',
  templateUrl: './employeeeducation.component.html',
  styleUrls: ['./employeeeducation.component.scss']
})
export class EmployeeeducationComponent implements OnInit {

  value: boolean;
  deletedEduDetails: any;
  EmpEduUpdateDetails: any;
  EmpEduSaveList: any;
  EmpEduSaveDetails: any;
  EmpEducationDetails: any;
  pg_stream: any;
  degree_stream: any;
  pg: any;
  degree: any;
//   public data1=[];
//   public show:boolean = false;
//   public buttonName:any = 'Show';

  
//   //new code starts here
//   value: boolean;
//   data; 
//   employeeeducation:any;
//   employeeEducation;
//   serialno:any;
//   educationType:any;
//   empEducation:any;
//   isEditable:boolean = false;
//   reverse: boolean = false;
//   order: string = 'name';

//   employeeEducationlist:EmployeeEducation[]=[{
     
//     "sno": "1",
//     "educationType":"Post Graduation",
//     "education":"MTech"
//    },
//  {
//    "sno": "2",
//     "educationType":"Graduation",
//     "education":"BTech"
//  },
//  {
//    "sno": "3",
//     "educationType":"Post Graduation",
//     "education":"MTech"
//  },
//  {
//   "sno": "4",
//    "educationType":"Post Graduation",
//    "education":"MTech"
// },
//  {
//   "sno": "5",
//    "educationType":"Graduation",
//    "education":"BTech"
// }]





  constructor(private hrms:HrmsService) {
   }
    
  ngOnInit() {
    this.getEmpEducation();
  }
  
   
//   handleClick(event: Event) {
//     console.log('Button clicked', event)
//   }
//   toggle() {
//     this.show = !this.show;

//     // CHANGE THE NAME OF THE BUTTON.
//     if(this.show)  
//       this.buttonName = "Hide";
//     else
//       this.buttonName = "Show";
//   }
//   // deleteFieldValue(index) {
//   //   debugger; 
//   //   this.data.splice(index, 1);
//   //   }


//   saveBu(){
//     debugger;
//     this.value = false;
//     this.data = {
//       "sno":this.serialno,
//       "educationType":this.educationType,
//       "education":this.empEducation,
//     }
//     this.employeeEducationlist.unshift(this.data);

//     this.employeeEducation='';
    
  
//   }
//   editData(blist){
//     console.log(blist);
//     //this.listDetails = blist;
//     this.serialno = blist.serialno;
//     this.educationType = blist.educationType; 
//     this.empEducation = blist.empEducation;
    
//   }
//   saveData(){
//     var editD = {
//       "sno":this.serialno,
//       "educationType":this.educationType,
//       "education":this.empEducation,
      
//     }
//     console.log(editD);
//   }
//   deleterow(index){
//     debugger;
//     if(index !== -1){
//       this.employeeEducationlist.splice(0,1);
//     }
//     else {
//     this.employeeEducationlist.splice(index,1);
//     }
// }

// setOrder(value: string) {
//   if (this.order === value) {
//     this.reverse = !this.reverse;
//   }

//   this.order = value;

// }
 setEmpEducation(){
   var request = {
        
    "employeeEducation":{
            "degree"                :this.degree,
            "pg"                        :this.pg,
            "degree_stream"        :this.degree_stream,
            "pg_stream"                :this.pg_stream
    },
    "sessionID":"12321",
    "transactionType":"save"

}
this.hrms.setEmployeeEducation(request).subscribe(data =>{
  this.EmpEducationDetails = data,
  console.log(this.EmpEducationDetails);
  if(this.EmpEducationDetails.statusMessage == "Success fully Education record saved"){
    this.value =false;
    swal(this.EmpEducationDetails.statusMessage, "","success"),
    this.getEmpEducation();
  }
})
 }
 getEmpEducation(){
   var responseData = 
   {
   "employeeEducation":{
   },
   "sessionID":"12321"
   }
this.hrms.getEmployeeEducation(responseData).subscribe(res =>{
  this.EmpEduSaveDetails = res,
  this.EmpEduSaveList = this.EmpEduSaveDetails.listCourse;
  console.log(this.EmpEduSaveDetails);
})
 }
 saveUpdatedValues(bulist){
   var udatedvalue = {
        
    "employeeEducation":{
            "id":bulist.id,
            "degree"                :bulist.degree,
            "pg"                        :bulist.pg,
            "degree_stream"        :bulist.degree_stream,
            "pg_stream"                :bulist.pg_stream
    },
    "sessionID":"12321",
    "transactionType":"update"

}
this.hrms.updatedEmployeeEducation(udatedvalue).subscribe(res =>{
  this.EmpEduUpdateDetails = res,
  console.log(this.EmpEduUpdateDetails);
  if(this.EmpEduUpdateDetails.statusMessage == "Success fully Education record updated"){
    swal(this.EmpEduUpdateDetails.statusMessage, "","success")
    this.getEmpEducation();
  }
})
 }
 deleteEmpEdu(bulist){
   var deletevalue = {
        
    "employeeEducation":{
            "id":bulist.id,
            "degree"                :bulist.degree,
            "pg"                        :bulist.pg,
            "degree_stream"        :bulist.degree_stream,
            "pg_stream"                :bulist.pg_stream
    },
    "sessionID":"12321",
    "transactionType":"delete"

}
this.hrms.deleteEmployeeEducation(deletevalue).subscribe(res =>{
  this.deletedEduDetails = res,
console.log(this.deletedEduDetails);
if(this.deletedEduDetails.statusMessage == "Success fully Education record deleted"){
  swal(this.deletedEduDetails.statusMessage, "","success"),
  this.getEmpEducation();
}
})
 }

}