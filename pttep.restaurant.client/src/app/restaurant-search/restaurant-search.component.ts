import { Component, OnInit } from '@angular/core';

import { Observable, Subject } from 'rxjs';

import {
   debounceTime, distinctUntilChanged, switchMap
 } from 'rxjs/operators';

import { Restaurant } from '../restaurant';
import { RestaurantService } from '../restaurant.service';

//for csv
import { ExportToCsv } from 'export-to-csv';

@Component({
  selector: 'app-restaurant-search',
  templateUrl: './restaurant-search.component.html',
  styleUrls: [ './restaurant-search.component.css' ]
})
export class RestaurantSearchComponent implements OnInit {
  restaurantes$: Observable<Restaurant[]>;
  private searchTerms = new Subject<string>();

  constructor(private restaurantService: RestaurantService) {}
  
  // Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(term);
  }

  save(term: string): void {
      this.searchTerms.next(term);

      this.restaurantService.searchRestaurantes(term)
      .subscribe(results => {

        console.log(results);
            
          const options = { 
            fieldSeparator: ',',
            quoteStrings: '"',
            //decimalSeparator: '.',
            showLabels: false, 
            showTitle: false,
            //title: 'My Awesome CSV',
            useTextFile: true,
            useBom: true,
            useKeysAsHeaders: false,
            // headers: ['Column 1', 'Column 2', etc...] <-- Won't work with useKeysAsHeaders present!
          };
        
        const csvExporter = new ExportToCsv(options);
        
        csvExporter.generateCsv(results);

      });


  }

  ngOnInit(): void {
    this.restaurantes$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      //distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.restaurantService.searchRestaurantes(term)),
    );

    //console.info(this.restaurantes$); 
  }
}
