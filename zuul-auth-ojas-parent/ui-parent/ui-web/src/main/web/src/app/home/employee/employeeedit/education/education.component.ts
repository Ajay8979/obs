import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert';
import { HrmsService } from 'src/app/home/services/hrms.service';


@Component({
  selector: 'app-education',
  templateUrl: './education.component.html',
  styleUrls: ['./education.component.scss']
})
export class EducationComponent implements OnInit {

  constructor(private hrms:HrmsService) { }

  ngOnInit() {
  }

// //----Getting Employee Education Details--------------------
// getempedu:any;
// getempeduarr:any;

// getEmpEdu()
// {
// var reqtitle = 
// {
// "employeeEducationDetailsList" :[],

// "transaactionType":"getAll",
// "pageNo" : 2,
// "pageSize" : 2
// }

// this.hrms.getEmpEduDetails(reqtitle).subscribe(res =>{
// this.getempedu = res;
// this.getempeduarr = this.getempedu.employeeEducationDetailsList;
// console.log("Employee Education get");
// console.log(this.getempedu);
// })
// }

// //--Saving Employee Education  Details----------------

// isCreatedEduby:boolean=false;
// isUpdatedEduby:boolean=false;
// saveEmpEduObj:any;
// saveEmpEduarr:any;

// employeeIdEdu: any;
// qualification:any;
// year_of_passing :any;
// percentage_marks: any;
// institution_name: any;
// flagEdu: boolean;
// createdByEdu:any;
// updatedByEdu: any;
// createdDate: any;
// updatedDate: any;

// clickaddEdu()
// {
// this.isCreatedEduby=true;
// this.isUpdatedEduby=false;
// }

// saveEmpEdu()

// {
//    var saveempeduobj = 
//   {
//    "employeeEducationDetailsList" :[
// {
      
// "employeeId": this.editEduEmp.employeeId,
// "qualification": this.editEduEmp.qualification,
// "year_of_passing": this.editEduEmp.year_of_passing,
// "percentage_marks": this.editEduEmp.percentage_marks,
// "institution_name": this.editEduEmp.institution_name,
// "flag":this.editEduEmp.flagEdu,
// "createdBy": this.editEduEmp.createdbyEdu,
// "updatedBy": this.editEduEmp.updatedbyEdu,
// "createdDate": this.editEduEmp.createdDate,
// "updatedDate": this.editEduEmp.updatedDate,
// "id":this.editEduEmp.id
// }
// ],

// "transaactionType":"save",
// "pageNo" : 2,
// "pageSize" : 2

// }
// this.hrms.saveEmpEduDetails(saveempeduobj).subscribe(res => {
// this.saveEmpEduObj = res;
// this.saveEmpEduarr = this.saveEmpEduObj.employeeEducationDetailsList;
// console.log(this.saveEmpEduObj);
// this.getEmpEdu();
// })

// }


// //--Deleting Employee Education Details ----------------

// deleteEmpObj:any;
// deleteEmpEduarr:any;
// deleteEmpEdu(edu)
// {
// var deleteEmpEduObj = 
// {
  
//   "employeeEducationDetailsList" :[
//     {
//         "id": edu.id
       
//     }
//     ],
    
//     "transaactionType":"delete",
//     "pageNo" : 2,
//     "pageSize" : 2
// }


//   this.hrms.deleteEmpEduDetails(deleteEmpEduObj).subscribe (res => {
//   this.deleteEmpObj = res;
//   console.log(this.deleteEmpObj);
//   this.deleteEmpEduarr = this.deleteEmpObj.employeeEducationDetailsList;
//   this.getEmpEdu();
// })
// }


// // //----Editing Employee Education  Details-----------------------

// public editEduEmp = 
// {
// "id" : "",
// "employeeId" : "",
// "qualification": "",
// "year_of_passing":"",
// "percentage_marks":"",
// "institution_name":"",
// "flagEdu":"",
// "updatedbyEdu" : "",
// "createdbyEdu":"",
// "createdDate":"",
// "updatedDate":""
// }

// editEmpEdu:any;
// editEmpEduarr:any;
// empEmpEdudetails:any;
// updatedEduby:any;

// editEmpEduId(edu)
// {
// this.isUpdatedEduby = true;
// this.isCreatedEduby = false;

// var eduid = edu.id;
// var editEmpEduobj = 
// {

//     "employeeEducationDetailsList" : [{
//    "id":eduid

//  }],

// "transaactionType":"getall",
// "pageNo" : 2,
// "pageSize" : 2
// }

// this.hrms.editEmpEduDetails(editEmpEduobj).subscribe(res => {
// this.editEmpEdu = res;
// this.editEmpEduarr = this.editEmpEdu.employeeEducationDetailsList;
// this.editEduEmp = this.editEmpEduarr[0];
// console.log("this.empEmpEdudetails",this.editEduEmp);

// })
// }

// //----Updating Employee Education details-----------------------
// updateedures:any;
// updateEduarr:any;

// updateEdu()
// {
// var updateEduObj = 
// {
// "employeeEducationDetailsList" :[
//   {
 
//     "employeeId": this.editEduEmp.employeeId,
//     "qualification": this.editEduEmp.qualification,
//     "year_of_passing": this.editEduEmp.year_of_passing,
//     "percentage_marks": this.editEduEmp.percentage_marks,
//     "institution_name": this.editEduEmp.institution_name,
//     "flag":this.editEduEmp.flagEdu,
//     "updatedBy": this.editEduEmp.updatedbyEdu,
//     "id":this.editEduEmp.id
// }
// ],

// "transaactionType":"update",
// "pageNo" : 2,
// "pageSize" : 2
// }



// this.hrms.updateEmpEduDetails(updateEduObj).subscribe(updateEdu =>{
//  this.updateedures = updateEdu;
//  console.log(this.updateedures);
//  this.updateEduarr = this.updateedures.employeeEducationDetailsList;
//  this.getEmpEdu();
// })
// }

// //----Employee Education details Ends--------------------

//----Getting Employee Education Details--------------------
getempedu:any;
getempeduarr:any;
Qualification_value:any;

getEmpEdu()
{
var reqtitle = 
{
"employeeEducationDetailsList" :[],

"transaactionType":"getAll",
"pageNo" : 2,
"pageSize" : 2
}

this.hrms.getEmpEduDetails(reqtitle).subscribe(res =>{
this.getempedu = res;
this.getempeduarr = this.getempedu.employeeEducationDetailsList;
console.log("Employee Education get");
console.log(this.getempeduarr);
for(let i=0;i<=this.getempeduarr.length;i++){

  for(let j=0;j<this.EmpQualArray.length;j++){
      if(this.getempeduarr[i].qualification==this.EmpQualArray[j].id){
        this.Qualification_value=this.EmpQualArray[j].educationType;
        console.log("Qualification details");
        console.log(this.Qualification_value);
     
      }   
  }
  this.getempeduarr[i].qualification=this.Qualification_value;
  console.log("Final Educational Details Array");
  console.log(this.getempeduarr);
   
 
}



})
}

//--Saving Employee Education  Details----------------

isCreatedEduby:boolean=false;
isUpdatedEduby:boolean=false;
saveEmpEduObj:any;
saveEmpEduarr:any;

employeeIdEdu: any;
qualification:any;
year_of_passing :any;
percentage_marks: any;
institution_name: any;
flagEdu: boolean;
createdByEdu:any;
updatedByEdu: any;
createdDate: any;
updatedDate: any;

clickaddEdu(EmpEducationForm)
{
  EmpEducationForm.reset();
this.isCreatedEduby=true;
this.isUpdatedEduby=false;
}

saveEmpEdu()

{
  var user="user";
   var saveempeduobj = 
  {
   "employeeEducationDetailsList" :[
{
      
"employeeId": this.editEduEmp.employeeId,
"qualification": this.editEduEmp.qualification,
"year_of_passing": this.editEduEmp.year_of_passing,
"percentage_marks": this.editEduEmp.percentage_marks,
"institution_name": this.editEduEmp.institution_name,
"flag":this.editEduEmp.flagEdu,
"createdBy": user,
"updatedBy": user,
// "createdDate": this.editEduEmp.createdDate,
// "updatedDate": this.editEduEmp.updatedDate,
"id":this.editEduEmp.id
}
],

"transaactionType":"save",
"pageNo" : 2,
"pageSize" : 2

}
this.hrms.saveEmpEduDetails(saveempeduobj).subscribe(res => {
this.saveEmpEduObj = res;
this.saveEmpEduarr = this.saveEmpEduObj.employeeEducationDetailsList;
if(this.saveEmpEduObj.statusMessage == "Employee Education Details have been saved"){
  swal(this.saveEmpEduObj.statusMessage, "","success");
}
console.log(this.saveEmpEduObj);
this.getEmpEdu();
})

}


//--Deleting Employee Education Details ----------------

deleteEmpObj:any;
deleteEmpEduarr:any;
deleteEmpEdu(edu)
{
var deleteEmpEduObj = 
{
  
  "employeeEducationDetailsList" :[
    {
        "id": edu.id
       
    }
    ],
    
    "transaactionType":"delete",
    "pageNo" : 2,
    "pageSize" : 2
}


  this.hrms.deleteEmpEduDetails(deleteEmpEduObj).subscribe (res => {
  this.deleteEmpObj = res;
  console.log(this.deleteEmpObj);
  this.deleteEmpEduarr = this.deleteEmpObj.employeeEducationDetailsList;
  if(this.deleteEmpObj.statusMessage == "Employee Education Details have been deleted"){
    swal(this.deleteEmpObj.statusMessage, "","success");
    
  }
  this.getEmpEdu();
})
}

// Employee Qualification Details 

EmpQualObject:any;
EmpQualArray;
EmpQualification: [];
Qualification: [];

// Qualification_n: [];

getEmployeeQualification(){
  var QualificationRequest=
  {

        
    "transactionType" : "getall"
}
this.hrms.getEmpEduQualification(QualificationRequest).subscribe(response=>{
this.EmpQualObject=response;

this.EmpQualArray = this.EmpQualObject.listCourse;
// for(let i=0; i< this.EmpQualArray.length; i++){
//   //console.log(this.EmpQualArray[i].degree); //use i instead of 0
//   this.EmpQualification = this.EmpQualArray[i];
//  // this.Qualification_n.push(this.EmpQualArray[i]);
//   //console.log("List of EmpQualification");
//   console.log(this.EmpQualification['id']);
//   console.log(this.EmpQualification['degree']);
// }
this.Qualification=this.EmpQualification;
//console.log(this.EmpQualObject);
console.log(this.EmpQualArray);
})
}


// //----Editing Employee Education  Details-----------------------

public editEduEmp = 
{
"id" : "",
"employeeId" : "",
"qualification": "",
"year_of_passing":"",
"percentage_marks":"",
"institution_name":"",
"flagEdu":"",
"updatedbyEdu" : "",
"createdbyEdu":"",
"createdDate":"",
"updatedDate":""
}

editEmpEdu:any;
editEmpEduarr:any;
empEmpEdudetails:any;
updatedEduby:any;

editEmpEduId(edu)
{
this.isUpdatedEduby = true;
this.isCreatedEduby = false;

var eduid = edu.id;
var editEmpEduobj = 
{
  "employeeEducationDetailsList" :[
      {
      "id": eduid
     
  }    
  ],
  
  "transaactionType":"getAll",
  "pageNo" : 2,
  "pageSize" : 2
}

this.hrms.editEmpEduDetails(editEmpEduobj).subscribe(res => {
this.editEmpEdu = res;
this.editEmpEduarr = this.editEmpEdu.employeeEducationDetailsList;
this.editEduEmp = this.editEmpEduarr[0];

console.log("this.empEmpEdudetails",this.editEduEmp);

})
}

//----Updating Employee Education details-----------------------
updateedures:any;
updateEduarr:any;

updateEdu(EmpEducationForm)
{
  var user="user";
var updateEduObj = 
{
"employeeEducationDetailsList" :[
  {
 
    "employeeId": this.editEduEmp.employeeId,
    "qualification": this.editEduEmp.qualification,
    "year_of_passing": this.editEduEmp.year_of_passing,
    "percentage_marks": this.editEduEmp.percentage_marks,
    "institution_name": this.editEduEmp.institution_name,
    "flag":true,
    "updatedBy": user,
    "id":this.editEduEmp.id
}
],

"transaactionType":"update",
"pageNo" : 2,
"pageSize" : 2

}

this.hrms.updateEmpEduDetails(updateEduObj).subscribe(updateEdu =>{
 this.updateedures = updateEdu;
 console.log(this.updateedures);
 this.updateEduarr = this.updateedures.employeeEducationDetailsList;
 if(this.updateedures.statusMessage == "Employee Education Details have been updated"){
  swal(this.updateedures.statusMessage, "","success");
  
}
 this.getEmpEdu();

})
EmpEducationForm.reset();

}

//----Employee Education details Ends--------------------






}
