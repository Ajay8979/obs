import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert';
import { HrmsService } from 'src/app/home/services/hrms.service';



@Component({
  selector: 'app-certification',
  templateUrl: './certification.component.html',
  styleUrls: ['./certification.component.scss']
})
export class CertificationComponent implements OnInit {

  constructor(private hrms: HrmsService) { }

  ngOnInit() {
    this.getCertificationDetails();
  }
  
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
          this.getCertificationDetails();
         }
        this.certificationArr = this.certificationReq.certificationDetailsModel;
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
              this.getCertificationDetails();
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
          this.getCertificationDetails();
        }
             this.getCertificationDetails();
        })
   
  
}
//certificatiion details ends




}
