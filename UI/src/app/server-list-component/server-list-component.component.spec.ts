import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServerListComponentComponent } from './server-list-component.component';

describe('ServerListComponentComponent', () => {
  let component: ServerListComponentComponent;
  let fixture: ComponentFixture<ServerListComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ServerListComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ServerListComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
