import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import { HrmsService } from '../../services/hrms.service';
import { identifierModuleUrl } from '@angular/compiler';
import swal from 'sweetalert';
import {NgxPaginationModule} from 'ngx-pagination';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
declare var $: any;

@Component({
  selector: 'app-employeeedit',
  templateUrl: './employeeedit.component.html',
  styleUrls: ['./employeeedit.component.scss']
})
export class EmployeeeditComponent implements OnInit {
 public dependent_details:any;
  dependents: any;
  formdata:any;
  dependent_Name: any;
  relation:any;
  date_Of_Birth:any;
  created_By:any;
  employee_Id:any;
  updated_by:any;
  updateRes:any;
  updated_By:number;
  

  deleteRes:any;
  employee_bankdetails:any;
  bankdt: any;
  bankuppdate:any;
  banksave:any;
  bank_account_no:any;
  bank_name:any;
  bank_city:any;
  bank_branch:any;
  bank_ifsc_code:any;
  bank_account_status:any;
  is_active:boolean;
  employee_id:any;
  created_by: any;
  is_bank_update:boolean;
  is_bank_save:boolean;
  employee_bankdetailsById:any;
  bank_details:any;
  employee_dependentdetailsById:any;
  dependent_dtdetails:any;
  employmentdetailsss:any;
 
  Createdby:boolean;
  onboarding:any;
  //onboarding
  employeeId:any;
  joiningDate:Date;
  resourceType:any;
  bondStatus:boolean;
  flag:boolean;
  createdBy:any;
  onboardingdetails:any;
  onbordingArr:any;
  deleteonboarding:any;
  onboard_details:any;;
  onboard_details_by_id:any;
  onboard_details_final:any;
  resignationDate:Date;
  exitDate:Date;
  separationType:any;
  updatedBy:any
  onboardRes:any;
  id:any;
  isupdate:any;
  isSaved:any;
  //project starts
    //project
  
    projectdelete: any;
    projectGetById: any;
    //createdBy: any;
    isInternal: boolean;
    bdmContact: any;
    projectStatus: any;
    projectType: any;
    gstApplicable: any;
    location: any;
    customer: any;
    projectTechStack: any;
    billingId: any;
    endDate: any;
    startDate: any;
    //employeeId: any;
    rateId: any;
    contractId: any;
    projectName: any;
    projectsave: any;
    projectDetailsLi: any;
    projectDetails: any;
    projectDetailsList: any;
    projectdetailsupdate:any;
  
  
  
  //project end
 

  constructor(private hrms:HrmsService,NgxPaginationModule:NgxPaginationModule,Ng2SearchPipeModule:Ng2SearchPipeModule) { }

  ngOnInit() {
    this.getdependentData();
    this.getbankdetails();
   this.getEmpExp();
  this.getEmpKye();
     this.getempTiltle();
     this.getEmpEdu();
    this.getContactInformation();
    this.getCertificationDetails();
     this.getSkillInfo();
     this.getbusines();
     this.savebusinesunit();
     this.getempdata();
	   this.getdependentData();
    this.getbankdetails();
   this.getemploymentdetails();
   this.getProject();
   this.getSkillInfomaster();
   this.getStatus(); 
  }
//  OnSave()
//  {
   
//   this.createdByDependent=true;
//    this.isupdateDependent=false;
//  }


  onSubmitbu(form: NgForm)
  {
    if (form.valid) 
    {
      console.log(form.value);
    }
    
  }
  onSubmit1(form: NgForm)
  {
    if (form.valid) 
    {
      console.log(form.value);
    }
  }
  onSubmittitle(form: NgForm)
  {
    if (form.valid) 
    {
      console.log(form.value);
    }
  }
  onSubmitDep(form: NgForm)
  {
    //this.formdata=JSON.stringify(form.value);
    if (form.valid) 
    {
      // this.hrms.savedependentdetails(this.formdata).subscribe(response =>{
      //   this.dependent_details = response;
      //       this.dependents = this.dependent_details.saveDependentDetailsList;
     
      //        console.log(this.dependent_details);
      //    })
         
    //  console.log(form.value);
    }
  }
  onSubmit(form: NgForm) 
  {
    if (form.valid) 
    {
      console.log(form.value);
    }
  }
  onSubmitBank(form: NgForm) 
  {
    if (form.valid) 
    {
      console.log(form.value);
    }
  }
  onSubmitCer(form: NgForm) 
  {
    if (form.valid) 
    {
      console.log(form.value);
    }
  }

  onSubmitIns(form: NgForm) 
  {
    if (form.valid) 
    {
      console.log(form.value);
    }
  }

  

//skill starts
skill_id:any;
level_id:any;
skillid:any;
// employee_id:any;
// created_by:any;
skillReq:any;
SkillArr :any;
skillinfolist:any;
skillInfoList:any;
deleteSkilldetails:any;
deleteSkillarr:any;
skillbyid:any;
SkillDetails:any;
update_by:any;
updateArr:any;

skillmasterlist:any;
skillmasterArray;
//skillMasterName:[];
//skillName:[];

//method save skillInfo
setSkillInfo(){

var reqData=
{
        
"skillInfoModel":
[{
                

                
"skill_id":this.SKILLinfo.skill_id,
          
  "level_id" :this.SKILLinfo.level_id,
           
 "employee_id" :this.SKILLinfo.employee_id,
           
 "created_by" : this.SKILLinfo.created_by
        
}],

        
  
  "sessionId" :"14",
        
"transactionType" : "save"
  
  
}

this.hrms.setSkill(reqData).subscribe(responce=>{
  this.skillReq = responce;
  console.log(this.skillReq);
  if(this.skillReq.statusMessage =="Successfully record added"){
   
    swal(this.skillReq.statusMessage, "","success");
   // this.getSkillInfo();
   }
   //this.SkillArr = this.skillReq.getSkillInfoList;
   this.getSkillInfo();
  
 });
 
 
 }

//method get skillInfo

getSkillName:any;
getSkillInfo(){

var Requestdata={
      
  "skillInfoModel" :[ {
  
                  
  
  }],
          "sessionId" : "1234",
  
  "transactionType" : "getAll"
  
  }
this.hrms.getSkill(Requestdata).subscribe(responce=>{
this.skillinfolist=responce;
this.skillInfoList=this.skillinfolist.getSkillInfoList;
console.log(this.skillInfoList);

for(let i=0;i<=this.skillInfoList.length;i++){

  for(let j=0;j<this.skillmasterArray.length;j++){
  if(this.skillmasterArray[j].skill_id==this.skillInfoList[i].skill_id){
  this.getSkillName=this.skillmasterArray[j].skill_name;
  console.log("Skill name details");
  console.log(this.getSkillName);
  } 
  }
  this.skillInfoList[i].skill_id=this.getSkillName;
  console.log("skill Details Array");
  console.log(this.skillinfolist);
  }



})

}

//masterskill get method 
getSkillInfomaster(){

  var Requestdata={
    "listOfSkill" : [{
    
            
    }],
    "transactionType" : "getAll"
  }
  this.hrms.getSkillmaster(Requestdata).subscribe(responce=>{
  this.skillmasterlist=responce;
  this.skillmasterArray=this.skillmasterlist.listOfSkill;
  // this.skillName=this.skillMasterName
  console.log(this.skillmasterArray)
  })
  
  }

addSkillvalue(newUserForm1){
  newUserForm1.reset()
this.isUPDATEDBY=false;
this.CREATEDBY=true;
}
isUPDATEDBY=false;
CREATEDBY=true;

public SKILLinfo={
"skill_id":"",
          
    "level_id" :"",
             
   "employee_id" :"",
             
   "created_by" : "",
              
    "update_by" :"",
     "flag":"",
     "id":""
}

//method edit skillInfo
getdatabyID(tableData){

this.isUPDATEDBY=true;
this.CREATEDBY=false;

console.log(tableData);

 var skillid= tableData.id;

var GetUpdateData ={
      
  "skillInfoModel" :[ {
  
                  "id" :skillid
  
  }],
          "sessionId" : "1234",
  
  "transactionType" : "getById"
  
  }

this.hrms.getSkilbyId(GetUpdateData).subscribe(res =>{
 this.skillbyid=res;
 this.SkillDetails=this.skillbyid.getSkillInfoList;
 this.SKILLinfo=this.SkillDetails[0];
 console.log("this.SKILLinfo",this.SKILLinfo)
})
}

//method update skillInfo
updateSkilldetails(SKILLinfo){

var updaterequestData={
      
  "skillInfoModel":[{
                  

    "skill_id":this.SKILLinfo.skill_id,
          
    "level_id" :this.SKILLinfo.level_id,
             
   "employee_id" :this.SKILLinfo.employee_id,
   "created_by" :this.SKILLinfo.created_by, 
   "update_by" : this.SKILLinfo.update_by,
   "flag":this.SKILLinfo.flag,
   "id":this.SKILLinfo.id
            
  }],
          
      
  "sessionId" :"14",
          
  "transactionType" : "update"
      
  
  }
  

  
      this.hrms.updateSkill(updaterequestData).subscribe(res =>{
           this.updateRes= res;
              
          console.log(this.updateRes);
          if(this.updateRes.statusMessage == "Successfully record updated"){
            swal(this.updateRes.statusMessage, "","success");
            this.getSkillInfo();
          }
               this.getSkillInfo();
          })
     
    
}
//skill Info ends




  //contact info strats
  contactList:any;
  contactInfolist:any;
  contactinfoReq:any;
  transactionType:any;
  //updateRes:any;
  deletedcontactDetails:any;
  Email:any;
  personal_mobileNo:any;
  current_Address_Line2:any;
  current_Address_Line1:any;
  alternate_mobileNo:any;
  current_City:any;
  current_State:any;
  current_Pin:any;
  permanent_Address_Line_1:any;
  emp_Id:any;
  //created_By:any;
  contactlist:any;
  savecontactres:any;
  //value: boolean;
  contactinfoArr:any;

  contactgetbyid:any;
  contactInfo:any;
  contactInfoDetails:any;
  empinfoarr:any;


  StateDetails:any;
  StateList:any;
  getcontactontable:any;
// Method for Save Contact Information
    setContactInformation() {
     
      
      var RequestData = {

        "empInfo" :  [{ 
                 "email":this.ContactInfo.email,
                 "personal_mobileNo":this.ContactInfo.personal_mobileNo,
                 "alternate_mobileNo": this.ContactInfo.alternate_mobileNo,
                 "current_Address_Line1": this.ContactInfo.current_Address_Line1,
                 "current_Address_Line2": this.ContactInfo.current_Address_Line2,
                 "current_City": this.ContactInfo.current_City,
                "current_State": this.ContactInfo.current_State,
                 "current_Pin": this.ContactInfo.current_Pin,
                 "permanent_Address_Line_1":this.ContactInfo.permanent_Address_Line_1,
                 "emp_Id": this.ContactInfo.emp_Id,
                 "created_By":"user"
             }],
       "transactionType" : "save"
     }
this.hrms.setContactInfo(RequestData).subscribe(responce=>{
  this.contactinfoReq = responce;
  console.log(this.contactinfoReq);
  if(this.contactinfoReq.statusMessage == "employee contact info saved successfully"){
    
    swal(this.contactinfoReq.statusMessage, "","success");
     this.getContactInformation();
   }
  this.contactinfoArr = this.contactinfoReq.empInfo;
  this.getContactInformation();
})
  
    }
//method for get contact info
    getContactInformation() {
      var request = {
        "transactionType" : "getAll" 
      }
      this.hrms.getContactInfo(request).subscribe(res =>{
     this.contactInfolist = res;
     this.contactList = this.contactInfolist.empContacts;
     console.log(this.contactList);


     for(let i=0;i<=this.contactList.length;i++){

      for(let j=0;j<this.StateList.length;j++){
      if(this.StateList[j].id==this.contactList[i].current_State){
      this.getcontactontable=this.StateList[j].stateName;
      console.log("Qualification details");
      console.log(this.getcontactontable);
      } 
      }
      this.contactList[i].current_State=this.getcontactontable;
      console.log("Final Educational ");
      console.log(this.contactList);
      }

      })
    }

   
   







 

//master get method for stateslist
getStatus() {
  var request =
    {
       
      "states":
       [{
        "stateName":"Hariyana",
        "id":4
       }],
            
      "sessionId":"1234",
       "transactionType": "getall"
       
      }
this.hrms.getStatusList(request).subscribe(res =>{
  this.StateDetails = res;
  this.StateList = this.StateDetails.statesList;
  console.log(this.StateList);
})
}

valueAdd(newUserFormcontact){
  newUserFormcontact.reset()
  this.isUpdate=false;
  this.createdby= true;
}

     //isUpdate = true;
     createdby= false;

     public ContactInfo= {
       "id":"",
       "email": "",
       "personal_mobileNo":"",
       "alternate_mobileNo": "",
       "current_Address_Line1":"",
       "current_Address_Line2": "",
       "current_City":"",
       "current_State": "",
       "current_Pin":"",
       "permanent_Address_Line_1": "",
       "emp_Id": "",
       "created_By": "",
       "updated_By": "",
       "created_date":"",
       "updated_date":"",
       "flag":""
     }
    
    //method for edit  contact Info
       getdatabyId(Cilist){
         this.isUpdate = true;
    
        this.createdby = false;

         console.log(Cilist);
    
    
     var cid= Cilist.id;
     var getupdatedata = {
      
    
         "transactionType" : "getAll",
        "empInfo" : [{
                   "id" : cid
                     }]
        
      
    }
    
    this.hrms.getcontactbyId(getupdatedata).subscribe(res =>{
      this.contactgetbyid=res;
      this.contactInfoDetails=this.contactgetbyid.empContacts;
      this.ContactInfo=this.contactInfoDetails[0];
      console.log("this.ContactInfo",this.ContactInfo)
    })
    
  }
  //method for update contantInfo

updateContactdetails(){

  var updateRequestData={
    

      "empInfo" : [{
               "email" : this.ContactInfo.email ,
              "personal_mobileNo" : this.ContactInfo.personal_mobileNo ,
              "alternate_mobileNo" : this.ContactInfo.alternate_mobileNo,
              "current_Address_Line1" : this.ContactInfo.current_Address_Line1,
              "current_Address_Line2" :  this.ContactInfo.current_Address_Line2,
              "current_City" : this.ContactInfo.current_City,
        
        "current_Pin" :  this.ContactInfo.current_Pin,
        "current_State" :this.ContactInfo.current_State,
        "permanent_Address_Line_1" : this.ContactInfo.permanent_Address_Line_1 ,
        "emp_Id" : this.ContactInfo.emp_Id ,
        "id" : this.ContactInfo.id,
        "updated_By" : "user45"
      }],
         
        "transactionType" : "update"
               
      }
        this.hrms.updateContactInfo(updateRequestData).subscribe(res =>{
             this.updateRes = res;
                
            console.log(this.updateRes);
            if(this.updateRes.statusMessage == "employee contact info updated successfully"){
              swal(this.updateRes.statusMessage, "","success");
              this.getContactInformation();
            }
                 this.getContactInformation();
            })
       
      
  }

 //method for delete contactInfo

    deleteContactInformation(Cilist) {
      var deleteReq = {
 
        "empInfo" : [{
         "id" :Cilist.id,
         "updated_By" : "ojas35"
        }
      ],
         
         
         "transactionType" : "delete"
         
       }
    this.hrms.deleteContactInfo(deleteReq).subscribe(res =>{
    this.deletedcontactDetails = res;
    this.empinfoarr = this.deletedcontactDetails.empInfo;
    console.log(this.deletedcontactDetails);
    if(this.deletedcontactDetails.statusMessage == "employee contact info deleted successfully"){
      swal(this.deletedcontactDetails.statusMessage, "","success");
      this.getContactInformation();
    }
    this.getContactInformation();
    })
    }
//contacytInfo ends

//certtification starts
    certificationName:any;
    issuedBy:any;
    dateOfIssue:any;
    // employeeId:any;
    // updatedBy:any;
    // flag:boolean;
    // createdBy:any;
    certificationReq:any;
    certificationArr:any;
    certificationDetailslist:any;
    cerDetailsList:any;
    deletedcertificationDetails:any;
    certificationDetailsListarr:any;
    CertificationDetails:any;
    certificationbyid:any;
  //  id:any;
    updateRequest:any;


    //method for save certification details
    setCertificationDetails(){

      var requestData = {
  
       
          "certificationDetailsModel":[
           {
                         
          "certificationName":this.Certification.certificationName,
          "issuedBy":this.Certification.issuedBy,
          "dateOfIssue":this.Certification.dateOfIssue,
          "createdBy":this.Certification.createdBy,
          "employeeId":this.Certification.employeeId,
          "flag":this.Certification.flag
           }
          ],
                
           "sessionId":"fk21",
           "transactionType": "save"
          }
          

          this.hrms.setCertification(requestData).subscribe(responce=>{
            this.certificationReq = responce;
            console.log(this.certificationReq );

            if(this.certificationReq.statusMassage =="Employee Certification detail saved successfuly"){
    
              swal(this.certificationReq.statusMassage, "","success");
              //this.getCertificationDetails();
             }
            //this.certificationArr = this.certificationReq.certificationDetailsModel;
            this.getCertificationDetails();
           
          });
        }


        //method for get cerfification details
    getCertificationDetails(){

            var request =  {
       
              "certificationDetailsModel":[
               {
              
               
               }],
                    
               "sessionId":"fk21",
               "transactionType": "get"
              }
            this.hrms.getCertification(request).subscribe(res =>{
           this.certificationDetailslist = res;
           this.cerDetailsList = this.certificationDetailslist.certificationDetailsList;
           console.log(this.cerDetailsList);
           
            })
          }

//method for delete certification
          deleteCertificationDetails(certifications){
            var Deletereq =  {
       
              "certificationDetailsModel":[
               {
              "id":certifications.id,
              "updatedBy":certifications.updatedBy
               
               }],
                    
               "sessionId":"fk21",
               "transactionType": "delete"
              }
              this.hrms.deleteCertification(Deletereq).subscribe(res =>{
                this.deletedcertificationDetails = res;
                this.certificationDetailsListarr = this.deletedcertificationDetails.certificationDetailsList;
                console.log(this.deletedcertificationDetails);

                if(this.deletedcertificationDetails.statusMassage == "Employee Certification detail deleted successfuly"){
                  swal(this.deletedcertificationDetails.statusMassage, "","success");
                  //this.getCertificationDetails();
                }
               this.getCertificationDetails();
                });
          }


Addvalue(newUserFormCer){
  newUserFormCer.reset()
  this.isUpdateBy=false;
  this.createdBY=true;
}
isUpdateBy=false;
createdBY=true;

public Certification = {
  "certificationName":"",
  "issuedBy":"",
  "dateOfIssue":"",
"id":"",
  "createdBy":"",
  "updatedBy":"",
  "employeeId":"",
  "flag":""
  
}

//edit certification details
getDatabyId(certifications){
  this.isUpdateBy=true;
  this.createdBY=false;
  console.log(certifications);
   var Cerid= certifications.id;
  
  var getUpdatedata =  {
       
    "certificationDetailsModel":[
     {
    "id":Cerid
   }
  ],
          
     "sessionId":"fk21",
     "transactionType": "getbyid"
    }
    
 
 this.hrms.getCertificationbyId(getUpdatedata).subscribe(res =>{
   this.certificationbyid=res;
   this.CertificationDetails=this.certificationbyid.certificationDetailsList;
   this.Certification=this.CertificationDetails[0];
   console.log("this.Certification",this.Certification)
 })
}

//method for update certification details
updateCertificationdetails(){

  var updaterequestData= {
       
    "certificationDetailsModel":[
     {
                   
    "certificationName":this.Certification.certificationName,
    "issuedBy":this.Certification.issuedBy,
    "dateOfIssue":this.Certification.dateOfIssue,
    "id":this.Certification.id,
    "employeeId":this.Certification.employeeId,
    "updatedBy":this.Certification.updatedBy,
    "createdBy":this.Certification.createdBy,
    "flag":this.Certification.flag
     }],
          
     "sessionId":"fk21",
     "transactionType": "update"
    }
    
        this.hrms.updateCertification(updaterequestData).subscribe(res =>{
             this.updateRequest = res;
                
            console.log(this.updateRequest);
            if(this.updateRequest.statusMassage == "Employee Certification detail updated successfuly"){
              swal(this.updateRequest.statusMassage, "","success");
              //this.getCertificationDetails();
            }
                 this.getCertificationDetails();
            })
       
      
  }
//certificatiion details ends


// bu starts-------
bu_empid:any;
  bu_sbumodel:any;
  bu_status:any;
  bu_createdby:any;
  bu_flag:any;
  business_data:any;
  empbasic:any;
  empbasicinfo:any;

  empbu:any;
  empbuinfo:any;
  businesinfo:any;
  updatebusines:any;
  updated_emp_info:any;
  updatebu:any;
  isUPDATEDBY1:any;
  CREATEDBY1:any;
  businesbyid:any;
  businesDetails:any;
  createdby1:any;
  isempid:any;

  addbusinessUnit(){
    this.isUpdate = false;
    
    this.createdby1 = true;
  }
public businessUnitObj=
{
  "employeeId": "",
  "sbu": "",
  
  "createddate": "",
  "updateddate": "",
  "createdby": "",
  "updatedby": "",
  "id": "",
  "status": "Active"
}





getbusines(){
  var businesinfo = {
    "employeeBUDeatils": [{

    }],
      "transactionType" :"getall"
}
   this.hrms.getBusinessunit(businesinfo).subscribe(res =>{
  this.empbu =res;
  this.empbuinfo= this.empbu.listCourse;
   console.log(this.empbu);
   for(let i=0;i<=this.empbuinfo.length;i++){

    for(let j=0;j<this.subBusinessUnitlist.length;j++){
    if(this.empbuinfo[i].gender==this.subBusinessUnitlist[j].id){
    this.getgenderdata=this.subBusinessUnitlist[j].gender;
    console.log("GEnder details");
    console.log(this.getgenderdata);
    } 
    }
    this.empbuinfo[i].gender=this.getgenderdata;
    console.log("Final Educational Details Array");
    console.log(this.empbuinfo);
    }
   })
  
}
savebusinesunit(){
  
  var savebusines={
    "employeeBUDeatils": [{
            "employeeId" : this.businessUnitObj.employeeId,
            "sbu" : this.businessUnitObj.sbu,
            "status" : this.businessUnitObj.status,
            "createdby" : this.businessUnitObj.createdby,
    
    }
    ],
      "transactionType" :"save"
}
this.hrms.savebusinesunit(savebusines).subscribe(res =>{
  this.businesinfo =res;
  //this.businesdetails= this.businesinfo.listCourse;
  console.log(this.businesinfo);
  if(this.businesinfo.statusMessage == "Successfully BusinessUnit record saved"){
    swal(this.businesinfo.statusMessage, "","success");
  }

    this.getbusines();

   })
  
}
empdelete(empbu){
  var delrequest={
    "employeeBUDeatils": [{
            "id" : empbu.id
    }
    ],
      "transactionType" :"delete"
  }
  this.hrms.deletebusinessunit(delrequest).subscribe(response=>{
    this.business_data=response;
    console.log(this.business_data);
    if(this.business_data.statusMessage =="Successfully BusinessUnit record deleted"){
       swal(this.business_data.statusMessage, "","success");

       this.getbusines();
     }
  })
  
  }
  updatebusinesunitint(){
    this.isempid = false;
 
    var updatebusinesunitkt={
      "employeeBUDeatils": [{
              "id" : this.businessUnitObj.id,
              "employeeId" : this.businessUnitObj.employeeId,
              "sbu" : this.businessUnitObj.sbu,
              "status" : this.businessUnitObj.status,
              "updatedby" :this.businessUnitObj.updatedby
      }
      ],
        "transactionType" :"update"
}

    
    this.hrms.updatebusinesunit(updatebusinesunitkt).subscribe(res =>{
      this.updatebusines = res;
      console.log(this.updatebusines);
         if(this.updatebusines.statusMessage == "Successfully BusinessUnit record updated"){
           swal(this.updatebusines.statusMessage, "","success");
          
         this.getbusines();
         }
    })
   }
   
     //bulist getdata
     subbusinessunitDetails:any;
     subBusinessUnitlist:any;
     getSubBusinessUnit(){
      var request = {
           "subBusinessUnitModel": [
           {
           }
      ],
          "transactionType" : "getAll"
  }
       this.hrms.getSubbusinessUnit(request).subscribe(res =>{
        this.subbusinessunitDetails = res;
        this.subBusinessUnitlist = this.subbusinessunitDetails.subBusinessUnitList;
        console.log(this.subbusinessunitDetails);
       })
    }
   
   getbusiness_data_byid(empbu){
    this.isUpdate = true;
    
    
    this.createdby1 = false;
    var buId= empbu.id;
   
    
    var GetUpdatebusines ={
      "employeeBUDeatils": [{
              "id" :buId
              
      }],
        "transactionType" :"getbyid"
}          
    
    
    this.hrms.getbyIdbusines(GetUpdatebusines).subscribe(res =>{
     this.businesbyid=res;
     this.businesDetails=this.businesbyid.listCourse;
     
     this.businessUnitObj=this.businesDetails[0];
     console.log("this.businesinfo",this.businessUnitObj);
    })
    }


// bu ends ///
 // emp basic starts //

 emp_fname:any;
 emp_mname:any;
 emp_lname:any;
 emp_dob:any;
 emp_status:any;
 emp_id:any;
 emp_password:any;
 emp_created:any;
 emp_gender:any;
 basicinfo:any;
 delete_data:any;
 emp_basic:any;
 basicInfobyid:any;
 updateempdata:any;
 basicInfoDetails:any;
 basicUnitObj:any;
 created:any;
 CREATEDBY2:any;
 createdby2:any;
 isUpdate1:any;
 basicempstatus:any;
 empbasicstatus:any;
 empgender:any;
 empgenderinfo:any;
 
 isUPDATEDBY2:any;

 addempUnit(){
  this.isUpdate = false;
  
  this.createdby1 = true;
}


 public empinobj=
{
            "id":"",
                "firstname" : "",
                "middlename" : "",
                "lastname" : "",
                "status" : "",
             "dob":"",
                "gender" : "",
                "password" : "",
                "employeeId" : "",
                        "updatedBy" : "",
                        "createdBy":""

}

//Get Employee Basic Info
getgenderdata:any;
getstatusdata:any;
 getempdata(){
  var empinfo = {
  
    "employeeInfo" :[{
         

    }],
    "transactionType" : "getall"

    
} 
   this.hrms.getempinfo(empinfo).subscribe(res =>{
  this.empbasic =res;
  this.empbasicinfo= this.empbasic.employeeInfo;
   console.log(this.empbasicinfo);
   for(let i=0;i<=this.empbasicinfo.length;i++){

    for(let j=0;j<this.empgenderinfo.length;j++){
    if(this.empbasicinfo[i].gender==this.empgenderinfo[j].id){
    this.getgenderdata=this.empgenderinfo[j].gender;
    console.log("GEnder details");
    console.log(this.getgenderdata);
    } 
    }
    this.empbasicinfo[i].gender=this.getgenderdata;
    console.log("Final Educational Details Array");
    console.log(this.empbasicinfo);
    }
    for(let i=0;i<=this.empbasicinfo.length;i++){

      for(let j=0;j<this.empbasicstatus.length;j++){
      if(this.empbasicinfo[i].status==this.empbasicstatus[j].id){
      
        this.getstatusdata=this.empbasicstatus[j].status;
      console.log("GEnder And Status details");
      console.log(this.getstatusdata);
      } 
      }
      this.empbasicinfo[i].status=this.getstatusdata;
      console.log("Final Educational Details Array");
      console.log(this.empbasicinfo);
      }

   })
  
}

saveemployeeInfo(){
  var request={
    "employeeInfo" :[{
         
           
                   
                    "firstname" : this.empinobj.firstname,
                    "middlename" : this.empinobj.middlename,
                    "lastname" : this.empinobj.lastname,
                    "status" : this.empinobj.status,
                 "dob":this.empinobj.dob,
                    "gender" : this.empinobj.gender,
                    "password" : this.empinobj.password,
                    "createdBy":this.empinobj.createdBy,
                    "employeeId" : this.empinobj.employeeId,
                  
    }],
    "transactionType" : "save"
  }
  this.hrms.saveempinfo(request).subscribe(res =>{
    this.basicinfo =res;
    //this.businesdetails= this.businesinfo.listCourse;

    console.log(this.basicinfo);
  if(this.basicinfo.statusMessage == "Success fully record added"){
  swal(this.basicinfo.statusMessage, "", "success");
  
     
  }
  this.getempdata();
     })
    
  }


  deleteemp(empbasic){
    var reguest1={
      "employeeInfo" :[{
             "id":empbasic.id

      }],
      "transactionType" : "delete"

    }
      this.hrms.deleteempinfo(reguest1).subscribe(response=>{
        this.delete_data=response;
        console.log(this.delete_data);
        if(this.delete_data.statusMessage == "Success fully record deleted"){
           swal(this.delete_data.statusMessage, "","success");
          
          
          }
          this.getempdata();
      })
      
      }
      updateempinfo(){
        this.isupdate = false;
        var updateempinfo={
          "employeeInfo" :[{
                 "id":this.empinobj.id,
                  "firstname" : this.empinobj.firstname,
                  "middlename" : this.empinobj.middlename,
                  "lastname" : this.empinobj.lastname,
                  "status" : this.empinobj.status,
               "dob":this.empinobj.dob,
                  "gender" : this.empinobj.gender,
                  "password" : this.empinobj.password,
                  "employeeId" : this.empinobj.employeeId,
                      
          }],
          "transactionType" : "update"
  
          
  }
        this.hrms.updateempinfo(updateempinfo).subscribe(res =>{
          this.updateempdata = res;
          console.log(this.updateempdata);
           if(this.updateempdata.statusMessage == "Success fully record updated")
           {
              swal(this.updateempdata.statusMessage, "","success");
            //   this.getdependentData();
             
        this.getempdata();
           }
        })
      
        
      
       }

      //  addBasic()
      //  {
         
      //  }

       getbyIdbasicdata(empbasic){

        this.createdby=false;
        this.isUpdate = true;
        
       
    
    
        var empdataid= empbasic.id;
       
        
        var GetUpdatebasicInfo ={
          "employeeInfo" :[{
                 "id":empdataid
                 
                 
          }],
          "transactionType" : "getById"
  
          
  }
        
        
        this.hrms.getbyIdempinfo(GetUpdatebasicInfo).subscribe(res =>{
         this.basicInfobyid=res;
         this.basicInfoDetails=this.basicInfobyid.employeeInfo;
         
         this.empinobj=this.basicInfoDetails[0];
         console.log("this.basicUnitObj",this.empinobj);
        })
        }
getemplostatus(){
          var empstatus = {
            "employeeStatus": [
                {
                        
                }
         
            ],
            "transactionType": "getall"
         }
           this.hrms.getempstatus(empstatus).subscribe(res =>{
          this.basicempstatus =res;
          this.empbasicstatus= this.basicempstatus.employeeStatusList;
           console.log(this.empbasicstatus);
           })
          
        }
        getgender(){
          var genderinfo={
       
            "gender":[
             {
             
             }
            ],
            
             "transactionType": "getall"
            }
            this.hrms.getGenderinfo(genderinfo).subscribe(res =>{
              this.empgender=res;
              this.empgenderinfo=this.empgender.genderList;
              console.log(this.empgenderinfo);
            })
          

        }
    

 //emp Basic ends//
    

// ----Employee Experience Details Starts---------------------------------------------
empexp : any;
empexpdetails :any;
saveRes :any;

//---getting employee details---------------------------------------------------
getEmpExp()
{
  var requestObj = 
  {
    "employeeExperienceDetails" :[ {

            
    }],
             "transactionType":"getall",
            "sessionId" : "any String" 
  }

  this.hrms.getEmployeeExperienceDetails(requestObj).subscribe(response =>{
    this.empexp = response;
    this.empexpdetails = this.empexp.employeeExperienceDetails;
    console.log(this.empexp);

  })
}

//---adding employee experience----------------------------------------------------

company_name:any;
joining_date:any;
exit_date:any;
salary:any;
//location:any;
is_current_company:any;
first_Reference_Name:any;
first_Reference_Contact:any;
second_Reference_Name:any;
second_Reference_Contact:any;
sessionId:any;
created_date:any;
updated_date:any;


isCreated:boolean=false;

booleanValue = true;
EmpExpArr:any;

  saveEmpExp(){
    var savReqObj =
    
      {
        "employeeExperienceDetails" : 
        
        [ 
          {
                 "company_name":this.editemparrlist.company_name,
                 "joining_date":this.editemparrlist.joining_date,
                 "exit_date":this.editemparrlist.exit_date,
                 "salary":this.editemparrlist.salary,
                 "location":this.editemparrlist.location,
                 "is_current_company":this.editemparrlist.is_current_company,
                 "employee_Id":this.editemparrlist.employee_Id,
                 "first_Reference_Name":this.editemparrlist.first_Reference_Name,
                 "first_Reference_Contact":this.editemparrlist.first_Reference_Contact,
                 "second_Reference_Name":"phani",
                 "second_Reference_Contact":1234567890,
                 "created_by":19196,
                 "created_date":this.editemparrlist.created_date,
                  "updated_by": "123",
                  "updated_date": "2019-04-22T06:29:14.000+0000"
                 
              
          }
        ],
        
            "transactionType" : "save",
             "sessionId" : "any String" 
    }

    this.hrms.saveEmployeeExperienceDetails(savReqObj).subscribe(res =>{
      this.saveRes = res;
      console.log(this.saveRes);
      
        if(this.saveRes.statusMessage == "EmployeeExperienceDetails saved successfully")
        {
         swal(this.saveRes.statusMessage,"","success");
         //this.getEmpExp();
       }
     this.getEmpExp();
    })

  }

//---deleting employee experience---------------------------
deleteres:any;
deleteEmpExpArr:any;


deleteEmpExp(emp)
{
var deleteReqObj =
{
"employeeExperienceDetails" :
[{
"id":emp.id,
 "updated_by":emp.updated_by
}],
  "transactionType":"delete",
  "sessionId" : "any String" 
}
this.hrms.deleteEmployeeExperienceDetails(deleteReqObj).subscribe(res => {
this.deleteres = res;

this.deleteEmpExpArr = this.deleteres.employeeExperienceDetails;
console.log(this.deleteres);

 if(this.deleteres.statusMessage == "EmployeeExperienceDetails deleted sucesfully")
 {
  swal(this.deleteres.statusMessage,"","success");
  this.getEmpExp();
 }
//this.getEmpExp();
})
}

//---editing employee experience------------------------------------------
editemp:any;
editemparr:any;

public editemparrlist = 
{
           "id":"",
            "company_name":"",
            "salary":"",
            "location":"",
            "joining_date":"",
            "exit_date":"",
            "is_current_company":"",
            "employee_Id":"",
            "first_Reference_Name":"",
            "first_Reference_Contact":"",
            "second_Reference_Name":"",
            "second_Reference_Contact":"",
            "updated_by":"",
            "created_by":"",
            "created_date":"",
            "updated_date":""
          
}


addempexp(newUserFormEmpExp)
{
newUserFormEmpExp.reset();
this.isUpdate = false;
this.isCreated = true;

}


editempexpbyid(emp)
{

this.isUpdate = true;
this.isCreated = false;
var empid = emp.id;
var editempexpobj = 
{
"employeeExperienceDetails" :[ {
  "id":empid


  
}],
   "transactionType":"getById",
  "sessionId" : "any String" 
}

this.hrms.editEmpExpDetails(editempexpobj).subscribe(res => {
this.editemp = res;
this.editemparr = this.editemp.employeeExperienceDetails;
this.editemparrlist = this.editemparr[0];
console.log("this.editemparrlist",this.editemparrlist);

})
}



//---updating employee experience------------------------

 updateres:any;
 updateEmpExpArr:any;
 newUserFormExp:any;
 


 updateEmpExp(emp)
 {
   
  var updateReqObj =
  {
  "employeeExperienceDetails" :
  [{
    
          "id":this.editemparrlist.id,
          "company_name":this.editemparrlist.company_name,
          "salary":this.editemparrlist.salary,
          "location":this.editemparrlist.location,
          "joining_date":this.editemparrlist.joining_date,
          "exit_date":this.editemparrlist.exit_date,
          "is_current_company":this.editemparrlist.is_current_company,
          "employee_Id":this.editemparrlist.employee_Id,
          "first_Reference_Name":this.editemparrlist.first_Reference_Name,
          "first_Reference_Contact":this.editemparrlist.first_Reference_Contact,
          "second_Reference_Name":"phani",
          "second_Reference_Contact":"gpv",
          "updated_by":this.editemparrlist.updated_by,
        
          
  }],
            "transactionType":"update",
           "sessionId" : "any String" 
 
      
}
this.hrms.updateEmployeeExperienceDetails(updateReqObj).subscribe(res => {
  this.updateres = res;
  console.log(this.updateres);
  // this.updateEmpExpArr = this.updateres.employeeExperienceDetails;
  // console.log(this.updateEmpExpArr);
   if(this.updateres.statusMessage == "EmployeeExperienceDetails updated successfully")
       {
        swal(this.updateres.statusMessage,"","success");
        this.getEmpExp();
      }
     //this.getEmpExp();
})

}



//---Employee KYE details starts--------------

//--- getting Employee KYE details-------------------
Location:any;
empkye:any;
empkyearr:any;
savepassport:any;
getEmpKye()
{
var reqObj = 
{
        "kye" :
        [{

        }],
        "transactionType"     :  "getAll"
}

this.hrms.getEmployeeKyeDetails(reqObj).subscribe(res =>{
this.empkye = res;
console.log(this.empkye);
this.empkyearr = this.empkye.kyeList;
//console.log("welcome to KYE");
 //console.log(this.empkye.kyeList[5].place_of_issue);
 for(let i=0;i<=this.empkyearr.length;i++){
   for(let j=0;j<this.passportCenterList.length;j++){
    if(this.passportCenterList[j].id==this.empkyearr[i].place_of_issue){
     this.savepassport=this.passportCenterList[j].centerName;
     console.log("Loop for Place");
     console.log(this.savepassport);
    }
   }
   this.empkyearr[i].place_of_issue=this.savepassport;
 }


})
}


//-- Getting Passport Deatails -------------
passportCenterDetails:any;
passportCenterList:any;
passport:any;
sample:any;
getPassportCenter() {
  var request =
  {
    "passportList":[
   ],

"sessionId" : "323",
"transaactionType" : "getall"
}
  this.hrms.getPassportCeneter(request).subscribe(res =>{
    this.passportCenterDetails = res;
    console.log(this.passportCenterDetails.body.passportList);
    //this.sample= this.passportCenterDetails.passportList;
    this.passportCenterList = this.passportCenterDetails.body.passportList;
    console.log(this.passportCenterList);
  })
}


//num = this.passport.id



//-- Getting Passport Deatails -------------


//--- saving Employee KYE details-------------------
savekyeres:any;
savekyeresarr:any;
kYE_Type:any;
uan:any;
kYE_address:any;
passport_no:any;
passport_date_of_Issue:any;
passport_date_of_expiry:any;
place_of_issue:any;
passport_address:any;
//employee_Id:any;
///created_by:any;
//value:boolean




saveEmpKye()
{
var savereqObj =
{
"kye" :[{
           "id"                      :  this.empkyedetails.id,
           "kYE_Type"                :  this.empkyedetails.kYE_Type,
           "uan"                     :  this.empkyedetails.uan,
           "kYE_address"             :  this.empkyedetails.kYE_address,
           "passport_no"             :  this.empkyedetails.passport_no,
           "passport_date_of_Issue"  :  this.empkyedetails.passport_date_of_Issue,
           "passport_date_of_expiry" :  this.empkyedetails.passport_date_of_expiry,
           "place_of_issue"          :  this.empkyedetails.place_of_issue,
           "passport_address"        :  this.empkyedetails.passport_address,
           "employee_Id"             :  this.empkyedetails.employee_Id,
           "created_by"              :  this.empkyedetails.created_by
}],
           "transactionType"     :  "save"
}
this.hrms.saveEmployeeKyeDetails(savereqObj).subscribe(res => {
this.savekyeres = res;
console.log(this.savekyeres);


if(this.savekyeres.statusMessage == "record added successfully")
    {
     swal(this.savekyeres.statusMessage,"","success");
     this.getEmpKye();
   }
})

}

//--- deleting Employee KYE details-------------------
deleteKye;any;
deleteKyearr:any;

deleteEmpKye(kye)
{
var deleteReqKye = 
{
"kye" :[{
      "id"                      :  kye.id,
      
      "updated_by"              :  kye.updated_by
}],
      "transactionType"     :  "delete"
}

this.hrms.deleteEmployeeKyeDetails(deleteReqKye).subscribe (res => {
this.deleteKye = res;
console.log(this.deleteKye);
this.deleteKyearr = this.deleteKye.kye;

 if(this.deleteKye.statusMessage == "record deleted successfully")
 {
  swal(this.deleteKye.statusMessage,"","success");
  this.getEmpKye();
 }
//  this.getEmpKye();
})
}

//--- Updating Employee KYE details-------------------
isUpdate:boolean=false;
editkye:any;
editkyearr:any;
kye:any;

addeditkye(newUserFormExp)
{
  newUserFormExp.reset();
this.isUpdate=false;

this.isCreated = true;

}

public empkyedetails = 
{
"id"                      :  "",
"kYE_Type"                :  "",
"uan"                     :  "",
"kYE_address"             :  "",
"passport_no"             :  "",
"passport_date_of_Issue"  :  "",
"passport_date_of_expiry" :  "",
"place_of_issue"          :  "",
"passport_address"        :  "",
"employee_Id"             :  "",
"created_by"              :  "",
"updated_by"              :  ""
}


//----edit kye details----------------------------------------------------
editkyeDetails:any;
editkyebyid(kye)
{
this.isUpdate = true;
var kyeid = kye.id;
var editempkyeobj = 
{
"kye" :[{
                "id"                      :  kyeid
             
     }],
             "transactionType"     :  "getAll"
}

this.hrms.editEmployeeKyeDetails(editempkyeobj).subscribe(res => {
this.editkye = res;
this.editkyeDetails = this.editkye.kyeList;
this.empkyedetails = this.editkyeDetails[0];
console.log("this.empkyedetails",this.empkyedetails);

})
}

//----update kye details----------------------------------------------------
updatekyeres:any;
updatekyeresarr:any;

updatekye(kye)
{
var updatekyereq =
{
     "kye" :[{
    "id"                      :  this.empkyedetails.id,
    "kYE_Type"                :  this.empkyedetails.kYE_Type,
    "uan"                     :  this.empkyedetails.uan,
   "kYE_address"             :  this.empkyedetails.kYE_address,
   "passport_no"             :  this.empkyedetails.passport_no,
    "passport_date_of_Issue"  :  this.empkyedetails.passport_date_of_Issue,
   "passport_date_of_expiry" :  this.empkyedetails.passport_date_of_expiry,
   "place_of_issue"          :  this.empkyedetails.place_of_issue,
    "passport_address"        :  this.empkyedetails.passport_address,
   "employee_Id"             :  this.empkyedetails.employee_Id,
   "updated_by"              :  this.empkyedetails.updated_by
}],
"transactionType"     :  "update"
}
this.hrms.updateEmployeeKyeDetails(updatekyereq).subscribe(updateres =>{
this.updatekyeres = updateres;
console.log(this.updatekyeres);
this.updatekyeresarr = this.updatekyeres.kye;


if(this.updatekyeres.statusMessage == "record updated successfully")
    {
     swal(this.updatekyeres.statusMessage,"","success");
     this.getEmpKye();
   }
// this.getEmpKye();
})
}


//---- KYE details  Ends----------------------------------------------


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



//----Employee Title Ends-------------------




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
  this.getdependentData();
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


public bankdetailss ={
  "id":"",
  "bank_account_no":"",
  "bank_name"   :"",
  "bank_city"     :"",
  "bank_branch"    :"",
  "bank_ifsc_code":"",
  "bank_account_status":"",
  "employee_id":"",
  "is_active"  :"",
  "created_by":"",
  "updated_by":""
}


 getbankdetails(){
 var bankdetails={
  "bankDetails":[{
         
  
}],
"transactionType":"getall"

}
this.hrms.getbankserverdetails(bankdetails).subscribe(response =>{
  this.employee_bankdetails = response;
  this.bankdt = this.employee_bankdetails.listBankDetails;
console.log(this.bankdt);

})
}

getbankdetailsbyId(bank){
  
  this.isupdateDependent = true;
  this.createdByDependent = false;
  var bbid = bank.id;
  var bankdetailsByid={
    "bankDetails":[{
            "id":bbid
    
}],
"transactionType":"getall"

}
 this.hrms.getbankserverdetails(bankdetailsByid).subscribe(response =>{
   this.employee_bankdetailsById = response;
   this.bank_details = this.employee_bankdetailsById.listBankDetails;
   this.bankdetailss = this.bank_details[0]
 console.log("this.bankdetailss",this.bankdetailss);
 
 })
 }

// Add Bank details Button 

AddBank(newUserFormBank){
  newUserFormBank.reset();
  this.createdByDependent=true;
  this.isupdateDependent=false;
}


Onsavebank(){
  var user  ="user";
  
    var savebank = 
  
    {
      "bankDetails":[{
              "bank_account_no":this.bankdetailss.bank_account_no,
              "bank_name"   :this.bankdetailss.bank_name,
              "bank_city"     :this.bankdetailss.bank_city,
              "bank_branch"    :this.bankdetailss.bank_branch,
              "bank_ifsc_code":this.bankdetailss.bank_ifsc_code,
              "bank_account_status":this.bankdetailss.bank_account_status,
              "employee_id":this.bankdetailss.employee_id,
              "is_active"  :this.bankdetailss.is_active,
              "created_by":user
              }
     
      ],
      "transactionType":"save"
  }
  this.hrms.savebankdetails(savebank).subscribe(response =>{
    this.employee_bankdetails = response;
          console.log(this.employee_bankdetails);

         this.getbankdetails();
         if(this.employee_bankdetails.statusMessage == "BankDetails record is saved Successfully"){
          swal(this.employee_bankdetails.statusMessage, "","success");
          this.getbankdetails();
        }
     })
}
deletebanktddetails(bank){
  var delebank= {
    "bankDetails":[{
            "id"  :bank.id
    }
    
    ],
    "transactionType":"delete"
}
this.hrms.savebankdetails(delebank).subscribe(res=>{
  this.deleteRes=res;
  if(this.deleteRes.statusMessage == "BankDetails record is deleted Successfully"){
   swal(this.deleteRes.statusMessage, "","success");
    this.getbankdetails();
  }

})
}
updatebankdetails(){
  var user="user";
  var updatebankdetails= {
    "bankDetails":[{
            "bank_account_no"    :this.bankdetailss.bank_account_no,
            "bank_name"          :this.bankdetailss.bank_name,
            "bank_city"          :this.bankdetailss.bank_city,
            "bank_branch"        :this.bankdetailss.bank_branch,
            "bank_ifsc_code":this.bankdetailss.bank_ifsc_code,
            "bank_account_status":this.bankdetailss.bank_account_status,
            "employee_id"        :this.bankdetailss.employee_id,
            "is_active"          :this.bankdetailss.is_active,
            "created_by":user
            
   
    }

    ],
    "transactionType":"update"
}
this.hrms.updatebankdetails(updatebankdetails).subscribe(res =>{
  this.updateRes = res;
  console.log(this.updateRes);
    if(this.updateRes.statusMessage == "BankDetails record is updated Successfully"){
      swal(this.updateRes.statusMessage, "","success");
      this.getbankdetails();
    }
})

}
//bank details ends


//employment details starts

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


//dependent starts 
//dependent starts 


OnSaveonboard(newUserFormonboard)
{
  newUserFormonboard.reset();
 this.createdByDependent=true;
  this.isupdateDependent=false;
}
Onsavedep(newUserFormDependent){
  newUserFormDependent.rest();
  this.createdByDependent=true;
  this.isupdateDependent=false;

}

getdependentData(){
  var requestData ={
    "dependentDetails" : [
                                            {
                                              
                                            }
                   ], 
                   "transactionType":"getall",
                  "sessionId" : "any String" 
  }

    this.hrms.getdependentdetails(requestData).subscribe(response =>{
      this.dependent_details = response;
      this.dependents = this.dependent_details.getDependentDetailsList;

      console.log(this.dependent_details);
    
    })
  }



  onsavedependentdata(){
    var user="user";
    this.isupdateDependent=false;
    //this.isupdateDependent=false;
    var requestData = {
      "dependentDetails" : [
                                    {
                                    "dependent_Name":this.dependentdetailss.dependent_Name,
                                    "relation":this.dependentdetailss.relation,
                                    "date_Of_Birth":this.dependentdetailss.date_Of_Birth,
                                    "employee_Id":this.dependentdetailss.employee_Id,
                                   
                                     "created_By":user

                         }
                           ], 
                     "transactionType":"save",
                    "sessionId" : "any String" 
    }
    
    this.hrms.savedependentdetails(requestData).subscribe(response =>{
   this.dependent_details = response;
       
       if(this.dependent_details.statusMessage == "DependentDetails have saved successfully"){
       swal(this.dependent_details.statusMessage, "","success");
        this.getdependentData();
      }
       
    })
    
 }
 createdByDependent= true;
 
 isupdateDependent = false;
 public dependentdetailss={
  
          
            "id":"",
            "dependent_Name":"",
            "relation":"",
            "date_Of_Birth":"",
            "employee_Id":"",
            "updated_By":"",
            "created_By":""

 }

 updatedependentdata(){

 var user="user";
  var updatedependentdataobj ={
    "dependentDetails" : [
                                            {
                                                                    "id":this.dependentdetailss.id,
                                  "dependent_Name":this.dependentdetailss.dependent_Name,
                                  "relation":this.dependentdetailss.relation,
                                  "date_Of_Birth":this.dependentdetailss.date_Of_Birth,
                                  "employee_Id":this.dependentdetailss.employee_Id,
                                  "updated_By":user
                       }
                       
                      
                   ], 
                   "transactionType":"update",
                  "sessionId" : "any String" 
  }
  
  this.hrms.updatedependentdetails(updatedependentdataobj).subscribe(res =>{
    this.updateRes = res;
  
      if(this.updateRes.statusMessage == "DependentDetails are updated successfully"){
        swal(this.updateRes.statusMessage, "","success");
    
        
        
      }
      this.getdependentData();
  })

}
 deletedependentdata(dependent){

  var deletedependentdata ={
    "dependentDetails" : [
                      {
                           "id":dependent.id,
                           "updated_By":dependent.updated_By
                       },
                       
                      
                   ], 
                   "transactionType":"delete",
                  "sessionId" : "any String" 
  }
this.hrms.deletedependentdetails(deletedependentdata).subscribe(res=>{
  this.deleteRes=res;
  if(this.deleteRes.statusMessage == "DependentDetails are deleted successfully"){
    swal(this.deleteRes.statusMessage, "","success");
    this.getdependentData();
  }

})
  
 }




 getdependentdetailsById(dependent){
  this.isupdateDependent = true;
  this.createdByDependent = false;
   var ddid = dependent.id;
   var dependentdetailsByid={
    "dependentDetails" : [
                                            {
                                                    "id": ddid
                       }
                   ], 
                   "transactionType":"getbyid",
                  "sessionId" : "any String" 
  }
  this.hrms.getdependentdetails(dependentdetailsByid).subscribe(response =>{
    this.  employee_dependentdetailsById = response;
    this. dependent_dtdetails = this.employee_dependentdetailsById.getDependentDetailsList;
    this.dependentdetailss = this.dependent_dtdetails[0]
      
  
  })

 }
//dependent detils ends
//project details starts


getProject(){




  var jsonData = {
   
    "transactionType":"getAll"
}
  this.hrms.getProjectDetails(jsonData).subscribe(response =>{
    this.projectDetails = response;
    this.projectDetailsLi = this. projectDetails.projectDetailsList;
    console.log(this.projectDetailsList);
  })
  
  }
  setProject(){

    var user="user";
    var requestObj = {
      "projectDetailsList" : [{
                    "projectName":this.projectDetailss.projectName,
                                    "contractId":this.projectDetailss.contractId,
                                    "rateId":this.projectDetailss.rateId,
                                    "employeeId":this.projectDetailss.employeeId,
                                    "startDate":this.projectDetailss.startDate,
                                    "endDate":this.projectDetailss.endDate,
                                    "billingId":this.projectDetailss.billingId,
                                    "projectTechStack":this.projectDetailss.projectTechStack,
                                    "customer":this.projectDetailss.customer,
                                    "location":this.projectDetailss.location,
                                    "gstApplicable":this.projectDetailss.gstApplicable,
                                    "projectType":this.projectDetailss.projectType,
                                    "projectStatus":this.projectDetailss.projectStatus,
                                    "bdmContact":this.projectDetailss.bdmContact,
                                    "isInternal":this.projectDetailss.isInternal,
                                    "createdBy":user
                                
                                   
                         }], 
                     "transactionType":"save"
    }
    this.hrms.setProjectDetails(requestObj).subscribe(response =>{
      this.projectsave = response;
      this.projectDetailsList = this.projectsave.projectDetailsList;
      console.log(this.projectsave);
      if(this.projectsave.statusMessage == "ProjectDetails has saved successfully"){
        swal(this.projectsave.statusMessage,"","success");
        this.getProject();
      }
      
    })
  }
  
 // isUpdate = false;
  public projectDetailss = {
    "id":"",
    "projectName":"",
    "contractId":"",
    "rateId":"",
    "employeeId":"",
    "startDate":"",
    "endDate":"",
    "billingId":"",
    "projectTechStack":"",
    "customer":"",
    "location":"",
    "gstApplicable":"",
    "projectType":"",
    "projectStatus":"",
    "bdmContact":"",
    "isInternal":"",
    "createdBy":"",
    "updatedBy":""
  };
  getdatabyId1(user){
    this.isUpdate = true;
  
    console.log(user);
  
   
    var pid = user.id;
    var getupdatedata = {
      "projectDetailsList" : [{
              "id": pid
                   
                         }], 
                     "transactionType":"getAll",
                   
    }
    this.hrms.getprojectbyId(getupdatedata).subscribe(res =>{
      this.projectGetById = res;
      this.projectDetails = this.projectGetById.projectDetailsList;
      this.projectDetailss = this.projectDetails[0];
      console.log("this.projectDetailss",this.projectDetailss);
      
      
    })
  }
  updateprojdata(){
    var user="user";
    var updatereq = {
      "projectDetailsList" : [{
              "id":this.projectDetailss.id,
                   "projectName":this.projectDetailss.projectName,
                                    "contractId":this.projectDetailss.contractId,
                                    "rateId":this.projectDetailss.rateId,
                                    "employeeId":this.projectDetailss.employeeId,
                                    "startDate":this.projectDetailss.startDate,
                                    "endDate":this.projectDetailss.endDate,
                                    "billingId":this.projectDetailss.billingId,
                                    "projectTechStack":this.projectDetailss.projectTechStack,
                                    "customer":this.projectDetailss.customer,
                                    "location":this.projectDetailss.location,
                                    "gstApplicable":this.projectDetailss.gstApplicable,
                                    "projectType":this.projectDetailss.projectType,
                                    "projectStatus":this.projectDetailss.projectStatus,
                                    "bdmContact":this.projectDetailss.bdmContact,
                                    "isInternal":this.projectDetailss.isInternal,
                                    "updatedBy":user
                         }], 
                     "transactionType":"update",
                    "sessionId" : "any String" 
    }
    this.hrms.updateproject(updatereq).subscribe(response =>{
      this.projectdetailsupdate = response;
      console.log(this.projectdetailsupdate);
      if(this.projectdetailsupdate.statusMessage == "ProjectDetails has updated successfully")
      swal(this.projectdetailsupdate.statusMessage,"","success")
      this.getProject();
    })
  }

  deleteproj(user){
  var deletep ={
    "projectDetailsList" : [{
                                            "id":user.id,
                                  "updatedBy":user.updatedBy
                                 
                                  
                      
                       }], 
                   "transactionType":"delete",
                  "sessionId" : "any String" 
  }
  this.hrms.deleteproject(deletep).subscribe(response =>{
    this.projectdelete = response;
    console.log(this.projectdelete);
    if(this.projectdelete.statusMessage == "ProjectDetails has deactivated successfully"){
     swal(this.projectdelete.statusMessage, "","success");
      this.getProject();
    }
   
  })
  }
  
 //project ends 








}
  

