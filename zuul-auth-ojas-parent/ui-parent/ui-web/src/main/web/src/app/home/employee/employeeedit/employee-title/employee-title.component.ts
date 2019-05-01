import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert';
import { HrmsService } from 'src/app/home/services/hrms.service';


@Component({
  selector: 'app-employee-title',
  templateUrl: './employee-title.component.html',
  styleUrls: ['./employee-title.component.scss']
})
export class EmployeeTitleComponent implements OnInit {

  constructor(private hrms:HrmsService) { }

  ngOnInit() {
    this.getempTiltle();
    this.getRole();
  }

//----Employee Title Details Starts---------------

//----Getting Title Details--------------------
gettitle:any;
gettitlearr:any;

saveRole:any;

getempTiltle()
{
var reqtitle = 
{
"model": [{
    
}],
  "transactionType" :"getall"
}

this.hrms.getEmpTitleDetails(reqtitle).subscribe(res =>{
this.gettitle = res;
this.gettitlearr = this.gettitle.listCourse;
console.log(this.gettitlearr);

for(let i=0;i<=this.gettitlearr.length;i++){

  for(let j=0;j<this.roleManagemenListArr.length;j++){
  if(this.gettitlearr[i].role==this.roleManagemenListArr[j].id){
  this.saveRole=this.roleManagemenListArr[j].roleName;
  console.log("Role Management details");
  console.log(this.saveRole);
  } 
  }
  this.gettitlearr[i].role=this.saveRole;
  console.log("Final Role Details Array");
  console.log(this.gettitlearr);
  }

})

}



//----getRoleManagement Master API Call-------------------
getRoleDetails:any;
roleManagemenListArr:any;
getRole(){
  var request = {
    
        
      "roleManagement" : [{
        
      }
     ],
      "transactionType" : "getall"
      


    
}
this.hrms.getRoleManagement(request).subscribe(res=>{
  this.getRoleDetails = res;
  this.roleManagemenListArr = this.getRoleDetails.roleManagementList;
})
}
//----getRoleManagement ends------------------



//--Saving employee title ----------------

isCreatedby:boolean=false;
isUpdatedby:boolean=false;
saveEmpObj:any;
savetitlearr:any;
role:any;
title:any;
//employeeId:any;
//createdby:any;
//flag:boolean;


clickadd(newUserFormtitle)
{
  newUserFormtitle.reset();
this.isCreatedby=true;
this.isUpdatedby=false;
}

//save Title 

saveempTitle()

{
  var saveempobj = 
  {
    "model": [{
      "role": this.edittitle.role,
      "title" : this.edittitle.title,
      "employeeId" : this.edittitle.employeeId,
      "createdby" : this.edittitle.createdby,
       "flag" : true
}],
"transactionType" :"save"
  }
  this.hrms.saveEmpTitleDetails(saveempobj).subscribe(res => {
  this.saveEmpObj = res;
  this.savetitlearr = this.saveEmpObj.model;
  console.log(this.savetitlearr);

   if(this.saveEmpObj.statusMessage == "Successfully Title record saved")
     {
      swal(this.saveEmpObj.statusMessage,"","success");
      //this.getempTiltle();
    }

   this.getempTiltle();
})



}

//--Deleting employee title ----------------

deletetitle:any;
deletetitlearr:any;

deleteempTitle(title)
{
var deleteReqtitle = 
{
  "model": [{
    "id" : title.id
}],
"transactionType" :"delete"
}

this.hrms.deleteEmpTitleDetails(deleteReqtitle).subscribe (res => {
  this.deletetitle = res;
  console.log(this.deletetitle);
  //this.deletetitlearr = this.deletetitle.listCourse;

  if(this.deletetitle.statusMessage == "Successfully Title record deleted")
      {
       swal(this.deletetitle.statusMessage,"","success");
      // this.getempTiltle();
     }
  this.getempTiltle();
})
}


//----Editing Employee title-----------------------

public edittitle = 
{
"id" : "",
"role": "",
"title" : "",
"employeeId" : "",
"updatedby" : "",
"createdby":"",
"flag":""
}

editTitle:any;
editTitlearr:any;
emptitledetails:any;
updatedby:any;

editempTitleid(title)
{
this.isUpdatedby = true;
this.isCreatedby = false;

var titleid = title.id;
var editempTitleobj = 
{

  "model": [{
         "id": titleid
  }],
    "transactionType" :"getbyid"

}

this.hrms.editEmpTitleDetails(editempTitleobj ).subscribe(res => {
this.editTitle = res;
this.editTitlearr = this.editTitle.listCourse;
this.edittitle = this.editTitlearr[0];
console.log("this.emptitleEdit",this.edittitle);

})
}

//----Updating Employee title-----------------------
updatetitlearr:any;
updateres:any;
updateempTitle(title)
{
var updatetileObj = 
{
"model": [{
  "id" : this.edittitle.id,
  "role": this.edittitle.role,
  "title" : this.edittitle.title,
  "employeeId" : this.edittitle.employeeId,
  "updatedby" : this.edittitle.updatedby
}],
"transactionType" :"update"
}
this.hrms.updateEmpTitleDetails( updatetileObj).subscribe(updaterestitle =>{
this.updateres = updaterestitle;
console.log(this.updateres);
this.updatetitlearr = this.updateres.model;

if(this.updateres.statusMessage == "Successfully Title record updated")
      {
       swal(this.updateres.statusMessage,"","success");
       //this.getempTiltle();
     }
 this.getempTiltle();
})
}

}

//----Employee Title Ends-------------------


