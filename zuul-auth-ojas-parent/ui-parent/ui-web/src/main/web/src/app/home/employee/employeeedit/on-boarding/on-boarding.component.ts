import { Component, OnInit } from '@angular/core';
import { HrmsService } from './../../../services/hrms.service';
import swal from 'sweetalert';

@Component({
  selector: 'app-on-boarding',
  templateUrl: './on-boarding.component.html',
  styleUrls: ['./on-boarding.component.scss']
})
export class OnBoardingComponent implements OnInit {

  constructor(private hrms:HrmsService) { }

  ngOnInit() {
  }

//employment details starts
employmentdetailsss:any;
onboarding:any
onboardingdetails:any;
deleteonboarding:any;

getemploymentdetails(){
  var employmentdetailss={
    "employmentDetails" :[ {
                  }
              
               ],
          
          "transactionType":"getAll"
  }
   
 this.hrms.getonboardingdetails(employmentdetailss).subscribe(response =>{
   this.employmentdetailsss = response;
   this.onboarding = this.employmentdetailsss.employmentDetailsList;
 console.log(this.onboarding);
 
 })
 }

 saveemploymentdetails(){

  
    var saveemploymentdetailss = 
  
    {
      "employmentDetails":[ {
        "employeeId":this. onboarddetailsss.employeeId,
        "joiningDate":this.onboarddetailsss.joiningDate,
        "resourceType":this.onboarddetailsss.resourceType,
        "bondStatus": this.onboarddetailsss.bondStatus,
        "resignationDate":this.onboarddetailsss.resignationDate,
        "exitDate":this.onboarddetailsss.exitDate,
        "separationType":this.onboarddetailsss.separationType,
        "flag":this.onboarddetailsss.flag,
        "createdBy":this.onboarddetailsss.createdBy
    }],
    
    "transactionType":"save"
}
  this.hrms.saveonboardingdetails(saveemploymentdetailss).subscribe(response =>{
    this.onboardingdetails = response;
          console.log(this.onboardingdetails);

  
       if(this.onboardingdetails.statusMessage == "Data is inserted successfully"){
          swal(this.onboardingdetails.statusMessage, "","success");
          this.onboardingdetails();
         }

     })
     
     this.getemploymentdetails();

}
deleteOnbording(onboard){
var deleteemployee={
  "employmentDetails":[ {
    "id":onboard.id,
   "updatedBy":onboard.updatedBy

    
}],

"transactionType":"delete"
}
this.hrms.deleteOnboardingdetails(deleteemployee).subscribe(res=>{
  this.deleteonboarding=res;
   if(this.deleteonboarding.statusMessage == "Data is deleted successfully"){
     swal(this.deleteonboarding.statusMessage, "","success");
     this.getemploymentdetails();
   }
 

})

}

isupdateDependent:boolean;
createdByDependent:boolean;
onboard_details:any;
onboard_details_by_id:any;
onboardRes:any;

getboardingdetailsbyId(onboard){
  this.isupdateDependent = true;
  this.createdByDependent = false;
   var onboardid = onboard.id;
   var boardingdetailsbyid={"employmentDetails":[ {
    "id":onboardid
  

}],

"transactionType":"getAll"
}


  this.hrms.getonboardingdetails(boardingdetailsbyid).subscribe(response =>{
    this.  onboard_details = response;
    this. onboard_details_by_id = this.onboard_details.employmentDetailsList;
    this.onboarddetailsss = this.onboard_details_by_id[0];
    console.log("this.onboarddetailsss",this.onboarddetailsss);
      
  
  })

 }
 updateonboardingdetailss(){
  var updatenboarddetails=  {"employmentDetails":[ {
            "id":this.onboarddetailsss.id,
            "employeeId":this. onboarddetailsss.employeeId,
            "resourceType":this. onboarddetailsss.resourceType,
            "bondStatus": this.onboarddetailsss.bondStatus,
            "resignationDate":this.onboarddetailsss.resignationDate,
            "exitDate":this.onboarddetailsss.exitDate,
            "separationType":this.onboarddetailsss.separationType,
            "flag":this.onboarddetailsss.flag,
            "updatedBy":this.onboarddetailsss.updatedBy

            
        }],
        
        "transactionType":"update"
}
this.hrms. updateonboardingdetails(updatenboarddetails).subscribe(res =>{
  this.onboardRes = res;
  console.log(this.onboardRes);
     if(this.onboardRes.statusMessage == "Data is updated successfully"){
       swal(this.onboardRes.statusMessage, "","success");
       this.getemploymentdetails();
     }
})
//this.getemploymentdetails();
}


public onboarddetailsss={
  "id":"",
  "employeeId": "",
  "joiningDate": "",
  "resourceType": "",
  "bondStatus": "",
  "resignationDate": "",
  "exitDate": "",
  "separationType": "",
  "flag":"",
  "createdBy": "",
  "updatedBy": ""
}



// ends onboarding details 


  
}
