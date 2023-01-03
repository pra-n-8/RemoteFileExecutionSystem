import { ServerDetailsService } from './../server-details.service';
import { HttpClient, HttpParams, HttpRequest } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-upload-component',
  templateUrl: './upload-component.component.html',
  styleUrls: ['./upload-component.component.css']
})
export class UploadComponentComponent implements OnInit {
  type:string ="";
  form:FormData = new FormData();
  flag: boolean = false;
  showAlert: boolean = false;
  output:string = "";
  loading:boolean = false;
  constructor(
    private http: HttpClient, 
    private activatedRoute: ActivatedRoute, 
    private serverService: ServerDetailsService,
    private router: Router
    ) { }

  ngOnInit(): void {
    if(this.serverService.getServer().trim().length == 0){
      this.router.navigate(["home"]);
    }else{
      this.activatedRoute.queryParams.subscribe(params => {
        this.type = this.activatedRoute.snapshot.params['type']
      })
      console.log(this.serverService.getServer());
    }
  }

  fileChange(event:any) {
    let attachedFile: FileList  = event.target.files;
    if(attachedFile.length > 0) {
        this.flag = true;
        let file: File = attachedFile[0];
        this.form.append('file', file);
    }
  }

  onSubmit(){
    if(!this.flag){
      this.showAlert = true;
    }else{
      this.loading = true;
      // change your endpoint here for submit button
      this.http.post(this.serverService.getServer(),this.form).subscribe(
          (data:any)=>{
            this.output = data;
            this.loading = false;
          },
          (error:any)=>{

          }
        );
    }
  }  

}
