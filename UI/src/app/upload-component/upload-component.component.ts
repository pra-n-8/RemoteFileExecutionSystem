import { HttpClient, HttpParams, HttpRequest } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

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
  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.type = this.activatedRoute.snapshot.params['type']
    })
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
      this.form.append('type',this.type);
      console.log(this.form);
      // change your endpoint here for submit button
      this.http.post("http://localhost:8080/runfile",this.form).subscribe(
          (data:any)=>{
            this.output = data;
          },
          (error:any)=>{

          }
        );
    }
  }  

}
