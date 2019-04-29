import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert';
import { HrmsService } from 'src/app/home/services/hrms.service';


@Component({
  selector: 'app-basic-info',
  templateUrl: './basic-info.component.html',
  styleUrls: ['./basic-info.component.scss']
})
export class BasicInfoComponent implements OnInit {

  constructor(private hrms:HrmsService) { }

  ngOnInit() {
  }

//Get Employee Basic Info
empbasic:any
empbasicinfo:any
empgenderinfo:any;
getgenderdata:any;
getstatusdata:any;
empbasicstatus:any;
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
//--Save Basic info--------------------
empinobj:any;
basicinfo:any;
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

//--Delete--------------
delete_data:any
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

      //---Update------------------
      isupdate:boolean
      updateempdata:any;
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
//--Add Button--------
createdby:boolean;
isUpdate:boolean;

basicInfobyid:any
basicInfoDetails:any;


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

//Get employee----------
basicempstatus:any;
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
        //--get Gender------------------
        empgender:any;
        getgender(){
          var genderinfo={
       
            "gender":[
             {
             
            
             }],
                  
            "sessionId":"1234",
             "transactionType": "getall"
            }
            this.hrms.getGenderinfo(genderinfo).subscribe(res =>{
              this.empgender=res;
              this.empgenderinfo=this.empgender.genderList;
              console.log(this.empgenderinfo);
            })
          

        }
    

 //emp Basic ends//
    



}
