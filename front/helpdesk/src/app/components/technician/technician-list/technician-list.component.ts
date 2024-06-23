import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Technician } from 'src/app/models/technician';

@Component({
  selector: 'app-technician-list',
  templateUrl: './technician-list.component.html',
  styleUrls: ['./technician-list.component.css']
})
export class TechnicianListComponent implements OnInit {

  ELEMENT_DATA: Technician[] = [
    {
      id: 1,
      name: 'Valdir Cezar',
      cpf: '123.456.789-10',
      email: 'valdir@email.com',
      password: '123',
      profiles: ['0'],
      criationDate: '15/08/2022'
    }
  ]

  displayedColumns: string[] = ['id', 'name', 'cpf', 'email', 'actions'];
  dataSource = new MatTableDataSource<Technician>(this.ELEMENT_DATA);


  constructor() { }

  ngOnInit(): void {
  }
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

}
