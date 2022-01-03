import { Component, OnInit } from '@angular/core';
import {BankService} from '../bank.service';
import {Bank} from '../bank';
import {ListOfBanks} from '../listOfBanks';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-banklist',
  templateUrl: './banklist.component.html',
  styleUrls: ['./banklist.component.css']
})
export class BanklistComponent implements OnInit {
  private allBanks: Bank[];
  public banks: Bank[];

  constructor(private bankService: BankService) { }

  ngOnInit() {
    this.bankService.getBanks().subscribe((data: ListOfBanks) => {
      console.log(data);
      this.allBanks = data.banks;
      this.banks = this.allBanks;
    }, (error: HttpErrorResponse) => {
      console.error(error.message);
    });
  }

  searchBanks(searchText: string) {
    if (!searchText) {
      this.banks = this.allBanks;
      return;
    }

    this.banks = this.allBanks.filter(bank => {
      return bank.name.toLowerCase().indexOf(searchText.toLowerCase()) !== -1
        || bank.country.toLowerCase().indexOf(searchText.toLowerCase()) !== -1;
    });
  }
}
