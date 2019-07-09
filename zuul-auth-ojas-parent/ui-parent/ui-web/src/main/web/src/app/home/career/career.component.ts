import { Component, OnInit } from '@angular/core';
import { HrmsService } from '../services/hrms.service';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-career',
  templateUrl: './career.component.html',
  styleUrls: ['./career.component.scss']
})
export class CareerComponent implements OnInit {
  projectDetails: any;
  projectDetailsLi: any;
  private pageSize: number = 5;
  constructor(private hrms:HrmsService, private dataservice:DataService) { }

  ngOnInit() {
    this.getProject();
  }
  getProject(){
    var jsonData =
    {
      "projectDetailsList" : [], 
          "transactionType":"getAll"
     }
  //    {
     
  //     "transactionType":"getAll"
  // }
    this.hrms.getProjectDetails(jsonData).subscribe(response =>{
      this.projectDetails = response;
      this.projectDetailsLi = this. projectDetails.projectDetailsList;
      console.log("Project Details List", this.projectDetailsLi);
    })
    
    }
}
